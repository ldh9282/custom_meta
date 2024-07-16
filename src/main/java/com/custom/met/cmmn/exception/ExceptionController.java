package com.custom.met.cmmn.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionController {

	@GetMapping("/METER01")
	public ModelAndView METER01(ModelAndView modelAndView) {
		modelAndView.setViewName("internalServiceError");
		
		return modelAndView;
	}
	@GetMapping("/METER02")
	public ModelAndView METER02(ModelAndView modelAndView) {
		modelAndView.setViewName("accessForbiddenError");
		
		return modelAndView;
	}
}
