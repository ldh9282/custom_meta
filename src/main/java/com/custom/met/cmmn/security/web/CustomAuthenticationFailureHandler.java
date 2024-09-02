package com.custom.met.cmmn.security.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.custom.met.cmmn.constant.URLConstant;

import lombok.extern.log4j.Log4j2;

@Component @Log4j2
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if (log.isInfoEnabled()) {log.info("Start CustomAuthenticationFailureHandler.onAuthenticationFailure");}
		String username = request.getParameter("username");
		if (log.isInfoEnabled()) {log.info("request ::: username ::: " + username);}
		String redirectUrl = request.getContextPath() + URLConstant.LOGIN_PAGE_URL;
		if (exception.getCause() instanceof DisabledException) {
			if (log.isInfoEnabled()) {log.info("exception ::: cause ::: " + exception.getCause());}
			redirectUrl += "?unenabled&username=" + username;
		} else {
			if (log.isInfoEnabled()) {log.info("exception ::: cause ::: " + "Incorrect Password");}
			redirectUrl += "?error";
		}
		
		if (log.isInfoEnabled()) {log.info("End CustomAuthenticationFailureHandler.onAuthenticationFailure");}
		if (log.isInfoEnabled()) {log.info("redirect ::: " + redirectUrl);}
		response.sendRedirect(redirectUrl);
	}
}
