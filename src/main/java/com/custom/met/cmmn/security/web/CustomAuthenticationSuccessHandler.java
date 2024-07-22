package com.custom.met.cmmn.security.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.custom.met.cmmn.constant.URLConstant;
import com.custom.met.cmmn.utils.DateUtils;

import lombok.extern.log4j.Log4j2;

@Component @Log4j2
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("loginTime", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		String redirectUrl = request.getContextPath() + URLConstant.LOGIN_SUCC_URL;
		response.sendRedirect(redirectUrl);
		
	}
}
