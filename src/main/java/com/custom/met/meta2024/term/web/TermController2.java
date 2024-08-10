package com.custom.met.meta2024.term.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.paging.PagingCreator;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2024.domain.service.DomainService;
import com.custom.met.meta2024.term.service.TermService;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: TermController2
 * 설명: TermController v2
 * ========================================
 * 프론트: JSP(asis) => React(tobe)
 * </pre>
 */
@Controller @Log4j2
@CrossOrigin(origins = "${allowed-cross-origin}")
public class TermController2 extends CustomController {

	@Autowired
	private DomainService domainService;
	
	@Autowired
	private TermService termService;
	
	/**
	 * <pre>
	 * 메서드명: mettm01
	 * 설명: 용어 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 * @throws CustomException 
	 */
	@GetMapping("/v2/METTM01")
	@ResponseBody
	public Object mettm01(@RequestParam Map<String, Object> map) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("METTM01 ::: " + requestMap); }
		
		try {
			
			CustomMap domainScInfoListMap = domainService.getDomainScInfoList(requestMap);
			
			List<CustomMap> domainScInfoList = domainScInfoListMap.getCustomMapList("domainScInfoList");
			
			resultMap.put("domainScInfoList", domainScInfoList);
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: mettm02
	 * 설명: 용어 등록요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METTM02")
	@ResponseBody
	public Object mettm02(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			termService.insertTermInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: mettm03
	 * 설명: 용어 목록페이지
	 * </pre>
	 * @param modelAndView
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping("/v2/METTM03")
	@ResponseBody
	public Object mettm03(@RequestParam Map<String, Object> map) throws CustomException {
		
		
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METTM03 ::: " + requestMap); }
		
		try {
			CustomMap termInfoListMap = termService.getTermInfoList(requestMap);
			
			List<CustomMap> termInfoList = termInfoListMap.getCustomMapList("termInfoList");
			requestMap.put("count", termInfoListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			resultMap.put("pagingCreator", pagingCreator);
			resultMap.put("termInfoList", termInfoList);
			resultMap.put("requestMap", requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: mettm04
	 * 설명: 용어 검색
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METTM04")
	@ResponseBody
	public Object mettm04(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			CustomMap termScInfoListMap = termService.getTermScInfoList(customMap);
			List<CustomMap> termScInfoList = termScInfoListMap.getCustomMapList("termScInfoList");
			resultMap.put("termScInfoList", termScInfoList);
			resultMap.put("count", termScInfoListMap.getString("count"));
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: mettm05
	 * 설명: 용어 삭제요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METTM05")
	@ResponseBody
	public Object mettm05(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			termService.deleteTermInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
}
