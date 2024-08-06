package com.custom.met.meta2024.term.web;

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
import com.custom.met.meta2024.term.service.TermService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class TermController extends CustomController {

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
	@GetMapping("/METTM01")
	public ModelAndView mettm01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) throws CustomException {
		
		CustomMap requestMap = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("METTM01 ::: " + requestMap); }
		
		try {
			
			CustomMap domainScInfoListMap = domainService.getDomainScInfoList(requestMap);
			
			List<CustomMap> domainScInfoList = domainScInfoListMap.getCustomMapList("domainScInfoList");
			
			modelAndView.addObject("domainScInfoList", domainScInfoList);
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		modelAndView.setViewName("meta2024/term/termReg");
		
		return modelAndView;
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
	@PostMapping("/METTM02")
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
	@RequestMapping("/METTM03")
	public ModelAndView mettm03(ModelAndView modelAndView, @RequestParam Map<String, Object> map) throws CustomException {
		
		
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METTM03 ::: " + requestMap); }
		
		try {
			CustomMap termInfoListMap = termService.getTermInfoList(requestMap);
			
			List<CustomMap> termInfoList = termInfoListMap.getCustomMapList("termInfoList");
			requestMap.put("count", termInfoListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			modelAndView.addObject("pagingCreator", pagingCreator);
			modelAndView.addObject("termInfoList", termInfoList);
			modelAndView.addObject("requestMap", requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		modelAndView.setViewName("meta2024/term/termList");
		return modelAndView;
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
	@PostMapping("/METTM04")
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
}
