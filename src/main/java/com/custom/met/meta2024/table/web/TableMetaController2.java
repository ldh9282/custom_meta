package com.custom.met.meta2024.table.web;

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
import com.custom.met.meta2024.table.service.TableMetaService;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: TableMetaController2
 * 설명: TableMetaController v2
 * ========================================
 * 프론트: JSP(asis) => React(tobe)
 * </pre>
 */
@Controller @Log4j2
@CrossOrigin(origins = "${allowed-cross-origin}")
public class TableMetaController2 extends CustomController {

	@Autowired
	private TableMetaService tableMetaService;
	
	/**
	 * <pre>
	 * 메서드명: mettb01
	 * 설명: 테이블메타 목록
	 * </pre>
	 * @param map
	 * @return
	 * @throws CustomException 
	 */
	@RequestMapping("/v2/METTB01")
	@ResponseBody
	public Object mettb01(@RequestParam Map<String, Object> map) throws CustomException {
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METTB01 ::: " + requestMap); }
		
		try {
			CustomMap tableMetaInfoListMap = tableMetaService.getTableMetaInfoList(requestMap);
			
			List<CustomMap> tableMetaInfoList = tableMetaInfoListMap.getCustomMapList("tableMetaInfoList");
			requestMap.put("count", tableMetaInfoListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			
			resultMap.put("pagingCreator", pagingCreator);
			resultMap.put("tableMetaInfoList", tableMetaInfoList);
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
	 * 메서드명: mettb02
	 * 설명: 테이블메타 등록페이지
	 * </pre>
	 * @param map
	 * @return
	 * @throws CustomException 
	 */
	@GetMapping("/v2/METTB02")
	@ResponseBody
	public Object mettb02(@RequestParam Map<String, Object> map) throws CustomException {
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(map);
		try {
			resultMap.put("schemaNameInfo", tableMetaService.getSchemaNameInfo(requestMap));
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		return getResponse(resultMap);
	}
	/**
	 * <pre>
	 * 메서드명: mettb03
	 * 설명: 테이블메타 상세페이지
	 * </pre>
	 * @param tableMetaSno
	 * @param modelAndView
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/v2/METTB03")
	@ResponseBody
	public Object mettb03(@RequestParam String tableMetaSno) throws CustomException {
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap();
		try {
			requestMap.put("tableMetaSno", tableMetaSno);
			CustomMap detail = tableMetaService.getTableMetaDetail(requestMap);
			resultMap.put("detail", detail);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: mettb04
	 * 설명: 테이블메타 등록요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METTB04")
	@ResponseBody
	public Object mettb04(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			tableMetaService.insertTableMetaInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] {e.getMessage()});
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	/**
	 * <pre>
	 * 메서드명: mettb05
	 * 설명: 테이블메타 삭제요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METTB05")
	@ResponseBody
	public Object mettb05(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			tableMetaService.deleteTableMetaInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: mettb06
	 * 설명: 테이블메타 수정요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/v2/METTB06")
	@ResponseBody
	public Object mettb06(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			tableMetaService.updateTableMetaInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
}
