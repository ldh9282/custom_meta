package com.custom.met.meta2024.domain.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.paging.PagingCreator;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2024.domain.service.DomainService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class DomainController extends CustomController {

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
	@GetMapping("/METDM01")
	public ModelAndView metdm01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) throws CustomException {
		
		CustomMap requestMap = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("METDM01 ::: " + requestMap); }
		
		try {
			
			CustomMap domainScInfoListMap = domainService.getDomainScInfoList(requestMap);
			
			List<CustomMap> domainScInfoList = domainScInfoListMap.getCustomMapList("domainScInfoList");
			
			modelAndView.addObject("domainScInfoList", domainScInfoList);
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		modelAndView.setViewName("meta2024/domain/domainReg");
		
		return modelAndView;
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
	@PostMapping("/METDM02")
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
	@RequestMapping("/METDM03")
	public ModelAndView metdm03(ModelAndView modelAndView, @RequestParam Map<String, Object> map) throws CustomException {
		
		
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METDM03 ::: " + requestMap); }
		
		try {
			CustomMap domainInfoListMap = domainService.getDomainInfoList(requestMap);
			
			List<CustomMap> domainInfoList = domainInfoListMap.getCustomMapList("domainInfoList");
			requestMap.put("count", domainInfoListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			modelAndView.addObject("pagingCreator", pagingCreator);
			modelAndView.addObject("domainInfoList", domainInfoList);
			modelAndView.addObject("requestMap", requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		modelAndView.setViewName("meta2024/domain/domainList");
		return modelAndView;
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
	@PostMapping("/METDM04")
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
}
