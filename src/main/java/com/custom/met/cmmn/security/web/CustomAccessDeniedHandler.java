package com.custom.met.cmmn.security.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component @Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (log.isInfoEnabled()) {log.info("Start CustomAccessDeniedHandler.handle");}
		if (log.isInfoEnabled()) {log.info("request ::: remote ip ::: " + request.getRemoteAddr());}
		if (log.isInfoEnabled()) {log.info("request ::: remote user ::: " + request.getRemoteUser());}
		if (log.isInfoEnabled()) {log.info("request ::: uri ::: " + request.getRequestURI());}
		if (log.isInfoEnabled()) {log.info("End CustomAccessDeniedHandler.handle");}
		if (log.isInfoEnabled()) {log.info("redirect ::: " + request.getContextPath() + "/METER02");}
		response.sendRedirect(request.getContextPath() + "/METER02");
	}

}
