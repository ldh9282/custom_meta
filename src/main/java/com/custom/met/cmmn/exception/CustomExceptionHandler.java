package com.custom.met.cmmn.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.web.CustomController;

@ControllerAdvice
public class CustomExceptionHandler extends CustomController {

	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public Object handleException(HttpServletRequest request, CustomException e) {
		
		boolean isAjaxRequest = "application/json".equals(request.getHeader("Content-Type"));

        if (isAjaxRequest) {
            // AJAX 요청일 경우, response.header.status != 0000
            return getErrorResponse(e.getMessage());
        } else {
            // AJAX 요청이 아닐 경우, 에러 페이지로 리다이렉션
        	ModelAndView modelAndView = new ModelAndView();
        	modelAndView.setViewName("internalServiceError");
            return modelAndView;
        }
	}
	
}
