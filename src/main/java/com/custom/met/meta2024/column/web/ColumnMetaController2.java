package com.custom.met.meta2024.column.web;

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
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.paging.PagingCreator;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2024.column.service.ColumnMetaService;
import com.custom.met.meta2024.table.web.TableMetaController2;

import lombok.extern.log4j.Log4j2;

/***
 * <pre>
 * 클래스명: ColumnMetaController2
 * 설명: ColumnMetaController v2
 * ========================================
 * 프론트: JSP(asis) => React(tobe)
 * </pre>
 */
@Controller @Log4j2
@CrossOrigin(origins = "${allowed-cross-origin}")
public class ColumnMetaController2 extends CustomController {

	@Autowired
	private ColumnMetaService columnMetaService;
	
	/***
	 * <pre>
	 * 메서드명: metcu01
	 * 설명: 컬럼메타 목록
	 * </pre>
	 * @param modelAndView
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping("/v2/METCU01")
	@ResponseBody
	public Object metcu01(@RequestParam Map<String, Object> map) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METCU01 ::: " + requestMap); }
		
		try {
			CustomMap columnMetaInfoListMap = columnMetaService.getColumnMetaInfoList(requestMap);
			
			List<CustomMap> columnMetaInfoList = columnMetaInfoListMap.getCustomMapList("columnMetaInfoList");
			requestMap.put("count", columnMetaInfoListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			resultMap.put("pagingCreator", pagingCreator);
			resultMap.put("columnMetaInfoList", columnMetaInfoList);
			resultMap.put("requestMap", requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		
		return getResponse(resultMap);
	}
	
	/***
	 * <pre>
	 * 메서드명: metcu02
	 * 설명: 컬럼메타 상세페이지
	 * </pre>
	 * @param modelAndView
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/v2/METCU02")
	@ResponseBody
	public Object metcu02(@RequestParam Map<String, Object> map) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METCU02 ::: " + requestMap); }
		
		try {
			CustomMap columnMetaInfo = columnMetaService.getColumnMetaInfo(requestMap);
			
			
			resultMap.put("columnMetaInfo", columnMetaInfo);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		
		return getResponse(resultMap);
	}
	
	/***
	 * <pre>
	 * 메서드명: metcu03
	 * 설명: 컬럼메타 수정요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METCU03")
	@ResponseBody
	public Object metcu03(@RequestBody CustomMap requestMap) throws CustomException {
		if (log.isDebugEnabled()) { log.debug("METCU03 ::: " + requestMap); }
		
		CustomMap resultMap = new CustomMap();
		try {
			
			resultMap = columnMetaService.updateColumnMetaInfo(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		return getResponse(resultMap);
	}
}
