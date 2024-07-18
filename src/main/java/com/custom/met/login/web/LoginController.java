package com.custom.met.login.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.model.CustomMap;
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
		
		resultMap = loginService.insertMemberDetail(customMap);
		
		return getResponse(resultMap);
	}
}
