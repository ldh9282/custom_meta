package com.custom.met.meta2024.schema.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2024.schema.service.SchemaMetaService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class SchemaMetaController extends CustomController {

	@Autowired
	private SchemaMetaService schemaMetaService;
	
	/**
	 * <pre>
	 * 메서드명: metsc02
	 * 설명: 스키마메타 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 * @throws CustomException 
	 */
	@GetMapping("/METSC02")
	public ModelAndView metsc02(@RequestParam Map<String, Object> map, ModelAndView modelAndView) throws CustomException {
		
		CustomMap requestMap = new CustomMap(map);
		
		modelAndView.setViewName("meta2024/schema/schemaMetaReg");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: metsc04
	 * 설명: 스키마메타 등록요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/METSC04")
	@ResponseBody
	public Object metsc04(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			schemaMetaService.insertOwnerMetaInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
}
