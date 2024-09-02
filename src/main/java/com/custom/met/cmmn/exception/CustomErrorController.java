package com.custom.met.cmmn.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.utils.HttpUtils;

@Controller
public class CustomErrorController implements ErrorController {

	@GetMapping("/METER01")
	public ModelAndView meter01(HttpServletRequest request, ModelAndView modelAndView) {
		HttpStatus status = HttpUtils.getStatus(request);
    	
        if (status == HttpStatus.NOT_FOUND) {
        	modelAndView.setViewName("cmmn/error-404");
        } else if (status == HttpStatus.FORBIDDEN) {
        	modelAndView.setViewName("cmmn/error-403");
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
        	modelAndView.setViewName("cmmn/error-500");
        } else {
        	modelAndView.setViewName("cmmn/error-500");
        }
        
		
		return modelAndView;
	}
	
	@GetMapping("/METER02")
	public ModelAndView meter02(ModelAndView modelAndView) {
		modelAndView.setViewName("cmmn/error-403");
		
		return modelAndView;
	}
	@GetMapping("/METER03")
	public ModelAndView meter03(ModelAndView modelAndView) {
		modelAndView.setViewName("cmmn/error-404");
		
		return modelAndView;
	}
	@GetMapping("/METER99")
	public ModelAndView meter99(ModelAndView modelAndView) {
		modelAndView.setViewName("cmmn/error-500");
		
		return modelAndView;
	}
}
