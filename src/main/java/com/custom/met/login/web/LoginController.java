package com.custom.met.login.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.utils.CmmnUtils;
import com.custom.met.cmmn.utils.DateUtils;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.login.service.LoginServcie;

@Controller
public class LoginController extends CustomController {

	@Autowired
	private LoginServcie loginService;
	
	@GetMapping("/METLG01")
	public ModelAndView metlg01(ModelAndView modelAndView) {
		modelAndView.setViewName("/login/login1");
		
		return modelAndView;
	}
	
	
	@GetMapping("/METLG02")
	public String metlg02(HttpServletRequest request) {
		return "/login/register1";
	}
	
	@PostMapping("/METLG03")
	@ResponseBody
	public Object METLG03(@RequestBody CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		if (CmmnUtils.isProd()) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { "운영서버는 당분간 회원가입이 보안상 불가합니다." });
		}
		resultMap = loginService.insertMemberDetail(customMap);
		
		return getResponse(resultMap);
	}
	
	@GetMapping("/METLG04")
	public ModelAndView metlg04(ModelAndView modelAndView) {
		modelAndView.setViewName("/login/login2");
		
		return modelAndView;
	}
	
	@PostMapping("/METLG05")
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
		
		return getResponse(resultMap);
	}
}
