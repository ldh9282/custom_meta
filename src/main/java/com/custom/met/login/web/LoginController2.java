package com.custom.met.login.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.constant.CustomAuthCode;
import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.utils.SecurityUtils;
import com.custom.met.cmmn.utils.DateUtils;
import com.custom.met.cmmn.utils.StringUtils;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.login.service.LoginServcie;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: LoginController2
 * 설명: LoginController v2
 * ========================================
 * 프론트: JSP(asis) => React(tobe)
 * </pre>
 */
@Controller @Log4j2
@CrossOrigin(origins = "${allowed-cross-origin}")
public class LoginController2 extends CustomController {

	@Autowired
	private LoginServcie loginService;
	
	@GetMapping("/v2/METLG01")
	public ModelAndView metlg01(ModelAndView modelAndView) {
		modelAndView.setViewName("/login/login1");
		
		return modelAndView;
	}
	
	
	@GetMapping("/v2/METLG02")
	public String metlg02(HttpServletRequest request) {
		return "/login/register1";
	}
	
	@PostMapping("/v2/METLG03")
	@ResponseBody
	public Object METLG03(@RequestBody CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		resultMap = loginService.insertMemberDetail(customMap);
		
		return getResponse(resultMap);
	}
	
	@GetMapping("/v2/METLG04")
	public Object metlg04() {
		CustomMap resultMap = new CustomMap();
		
		return getResponse(resultMap);
	}
	
	@PostMapping("/v2/METLG05")
	@ResponseBody
	public Object metlg05(@RequestBody CustomMap customMap, HttpServletRequest request) throws CustomException {
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(customMap);
		
		try {
			
			resultMap = loginService.loginJwt(requestMap);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginTime", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] {e.getMessage()}, e);
		}
		
		return getAuthResponse(resultMap);
	}
	
	@GetMapping("/v2/METLG06")
	@ResponseBody
	public Object metlg06(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws CustomException {
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("METLG06 ::: " + requestMap); }
		
		String jwtToken = (String) request.getAttribute("jwtToken");
		String jwtRefreshToken = (String) request.getAttribute("jwtRefreshToken");
		
		if (!StringUtils.isNVL(jwtToken)) {
			resultMap.put("jwtToken", jwtToken);
		}
		if (!StringUtils.isNVL(jwtRefreshToken)) {
			resultMap.put("jwtRefreshToken", jwtRefreshToken);
		}
		
		
		return getAuthResponse(resultMap, CustomAuthCode.A001);
	}
}
