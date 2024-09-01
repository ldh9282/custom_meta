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
        	modelAndView.setViewName("pageNotFoundError");
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
        	modelAndView.setViewName("internalServiceError");
        } else {
        	modelAndView.setViewName("internalServiceError");
        }
        
		
		return modelAndView;
	}
	@GetMapping("/METER02")
	public ModelAndView meter02(ModelAndView modelAndView) {
		modelAndView.setViewName("accessForbiddenError");
		
		return modelAndView;
	}
}
