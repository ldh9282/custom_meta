package com.custom.met.meta2024.domain.web;

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

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: DomainController2
 * 설명: DomainController v2
 * ========================================
 * 프론트: JSP(asis) => React(tobe)
 * </pre>
 */
@Controller @Log4j2
@CrossOrigin(origins = "${allowed-cross-origin}")
public class DomainController2 extends CustomController {

	@Autowired
	private DomainService domainService;
	
	/**
	 * <pre>
	 * 메서드명: metdm01
	 * 설명: 도메인 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 * @throws CustomException 
	 */
	@GetMapping("/v2/METDM01")
	@ResponseBody
	public Object metdm01(@RequestParam Map<String, Object> map) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("METDM01 ::: " + requestMap); }
		
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
	 * 메서드명: metdm02
	 * 설명: 도메인 등록요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METDM02")
	@ResponseBody
	public Object metdm02(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			domainService.insertDomainInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: metdm03
	 * 설명: 도메인 목록페이지
	 * </pre>
	 * @param modelAndView
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping("/v2/METDM03")
	@ResponseBody
	public Object metdm03(@RequestParam Map<String, Object> map) throws CustomException {
		
		
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METDM03 ::: " + requestMap); }
		
		try {
			CustomMap domainInfoListMap = domainService.getDomainInfoList(requestMap);
			
			List<CustomMap> domainInfoList = domainInfoListMap.getCustomMapList("domainInfoList");
			requestMap.put("count", domainInfoListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			resultMap.put("pagingCreator", pagingCreator);
			resultMap.put("domainInfoList", domainInfoList);
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
	 * 메서드명: metdm04
	 * 설명: 도메인 검색
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METDM04")
	@ResponseBody
	public Object metdm04(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			CustomMap domainScInfoListMap = domainService.getDomainScInfoList(customMap);
			List<CustomMap> domainScInfoList = domainScInfoListMap.getCustomMapList("domainScInfoList");
			resultMap.put("domainScInfoList", domainScInfoList);
			resultMap.put("count", domainScInfoListMap.getString("count"));
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: metdm05
	 * 설명: 도메인 삭제요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METDM05")
	@ResponseBody
	public Object metdm05(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			domainService.deleteDomainInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
}
