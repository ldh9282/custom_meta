package com.custom.met.meta2024.seq.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.paging.PagingCreator;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2024.seq.service.SeqMetaService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class SeqMetaController extends CustomController {

	@Autowired
	private SeqMetaService seqMetaService;
	/**
	 * <pre>
	 * 메서드명: metse01
	 * 설명: 시퀀스메타 목록페이지
	 * </pre>
	 * @param modelAndView
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping("/METSE01")
	public ModelAndView metse01(ModelAndView modelAndView, @RequestParam Map<String, Object> map) throws CustomException {
		
		
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METSE01 ::: " + requestMap); }
		
		try {
			CustomMap seqMetaInfoListMap = seqMetaService.getSeqMetaInfoList(requestMap);
			
			List<CustomMap> seqMetaInfoList = seqMetaInfoListMap.getCustomMapList("seqMetaInfoList");
			requestMap.put("count", seqMetaInfoListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			modelAndView.addObject("pagingCreator", pagingCreator);
			modelAndView.addObject("seqMetaInfoList", seqMetaInfoList);
			modelAndView.addObject("requestMap", requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		modelAndView.setViewName("meta2024/seq/seqMetaList");
		return modelAndView;
	}
}
