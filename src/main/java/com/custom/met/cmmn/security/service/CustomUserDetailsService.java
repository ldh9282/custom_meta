package com.custom.met.cmmn.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.model.CustomUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberDao memberDao;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomMap requestMap = new CustomMap();
		requestMap.put("memId", username);
		
		CustomMap member = memberDao.selectMember(requestMap);
		List<CustomMap> memberAuthList = memberDao.selectMemberAuthList(requestMap);
		
		CustomMap theMember = new CustomMap();
		
		theMember.put("memId", member.getString("memId"));
		theMember.put("memPw", member.getString("memPw"));
		
		List<String> authList = new ArrayList<>();
		for (CustomMap memberAuth : memberAuthList) {
			authList.add(memberAuth.getString("memAuth"));
		}
		theMember.put("memAuthList", authList);
		
		if ("".equals(theMember.getString("memId"))) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUser(theMember);
	}

}
