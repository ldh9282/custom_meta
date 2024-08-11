package com.custom.met.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.service.MemberDao;
import com.custom.met.cmmn.security.utils.JwtUtils;

@Service
public class LoginServcie {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Transactional
	public CustomMap insertMemberDetail(CustomMap customMap) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		
		try {
			
			CustomMap memberCntMap = memberDao.selectMemberCnt(customMap);
			
			if (memberCntMap.getInt("cnt") > 0) {
				throw new CustomException(CustomExceptionCode.ERR501, new String[] {"회원중복"});
			}
			
			
			CustomMap memberSnoMap = memberDao.selectMemSno();
			
			CustomMap memberMap = new CustomMap();
			memberMap.put("memSno", memberSnoMap.getString("memSno"));
			memberMap.put("memId", customMap.getString("memId"));
			memberMap.put("memPw", passwordEncoder.encode(customMap.getString("memPw")));
			memberMap.put("sysCreator", "SYSTEM");
			
			memberDao.insertMember(memberMap);
			
			
			List<String> authList = new ArrayList<>();
			authList.add("USER");
			authList.add("ADMIN");
			
			for (String auth : authList) {
				CustomMap authSnoMap = memberDao.selectMemAuthSno();
				CustomMap memberAuthMap = new CustomMap();
				
				memberAuthMap.put("memSno", memberSnoMap.getString("memSno"));
				memberAuthMap.put("memAuthSno", authSnoMap.getString("memAuthSno"));
				memberAuthMap.put("memId", customMap.getString("memId"));
				memberAuthMap.put("memAuth", auth);
				memberAuthMap.put("sysCreator", "SYSTEM");
				
				memberDao.insertMemberAuth(memberAuthMap);
			}
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR521, new String[] {"회원정보"}, e);
		}
		
		resultMap.put("result", "success");
		
		return resultMap;
	}


	public CustomMap loginJwt(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
					new UsernamePasswordAuthenticationToken(customMap.getString("username"), customMap.getString("password"));
			
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
            
			resultMap.put("jwtToken", jwtUtils.generateToken(authentication.getName()));
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] {"아이디 또는 비밀번호를 확인해주세요"}, e);
		}
		
		return resultMap;
	}
}
