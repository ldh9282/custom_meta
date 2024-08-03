package com.custom.met.meta2024.schema.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2024.schema.service.SchemaMetaService;
import com.custom.met.meta2024.seq.web.SeqMetaController2;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: SchemaMetaController2
 * 설명: SchemaMetaController v2
 * ========================================
 * 프론트: JSP(asis) => React(tobe)
 * </pre>
 */
@Controller @Log4j2
@CrossOrigin(origins = "${allowed-cross-origin}")
public class SchemaMetaController2 extends CustomController {

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
	@GetMapping("/v2/METSC02")
	public Object metsc02(@RequestParam Map<String, Object> map) throws CustomException {
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		
		
		return getResponse(resultMap);
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
	@PostMapping("/v2/METSC04")
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
