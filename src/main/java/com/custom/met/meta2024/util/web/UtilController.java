package com.custom.met.meta2024.util.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.model.CustomMap;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class UtilController {

	/***
	 * <pre>
	 * 메서드명: metut01
	 * 설명: sql2ExcelConverter 페이지
	 * </pre>
	 * @param modelAndView
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/METUT01")
	public ModelAndView metut01(ModelAndView modelAndView, @RequestParam Map<String, Object> map) throws CustomException {
		
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METUT01 ::: " + requestMap); }
		
		
		
		
		modelAndView.setViewName("meta2024/util/sql2ExcelConverter");
		return modelAndView;
	}
	
	/***
	 * <pre>
	 * 메서드명: metut02
	 * 설명: camel2Snake 페이지
	 * </pre>
	 * @param modelAndView
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/METUT02")
	public ModelAndView metut02(ModelAndView modelAndView, @RequestParam Map<String, Object> map) throws CustomException {
		
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METUT02 ::: " + requestMap); }
		
		
		
		
		modelAndView.setViewName("meta2024/util/camel2Snake");
		return modelAndView;
	}
}
