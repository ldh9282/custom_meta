package com.custom.met.meta2024.table.web;

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
import com.custom.met.meta2024.table.service.TableMetaService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class TableMetaController extends CustomController {

	@Autowired
	private TableMetaService tableMetaService;
	
	/***
	 * <pre>
	 * 메서드명: mettb01
	 * 설명: 테이블메타 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 * @throws CustomException 
	 */
	@RequestMapping("/METTB01")
	public ModelAndView mettb01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METTB01 ::: " + requestMap); }
		
		try {
			CustomMap tableMetaInfoListMap = tableMetaService.getTableMetaInfoList(requestMap);
			
			List<CustomMap> tableMetaInfoList = tableMetaInfoListMap.getCustomMapList("tableMetaInfoList");
			requestMap.put("count", tableMetaInfoListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			
			modelAndView.addObject("pagingCreator", pagingCreator);
			modelAndView.addObject("tableMetaInfoList", tableMetaInfoList);
			modelAndView.addObject("requestMap", requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		
		
		modelAndView.setViewName("meta2024/table/tableMetaList");
		return modelAndView;
	}
	/***
	 * <pre>
	 * 메서드명: mettb02
	 * 설명: 테이블메타 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 * @throws CustomException 
	 */
	@GetMapping("/METTB02")
	public ModelAndView mettb02(@RequestParam Map<String, Object> map, ModelAndView modelAndView) throws CustomException {
		
		CustomMap requestMap = new CustomMap(map);
		try {
			modelAndView.setViewName("meta2024/table/tableMetaReg");
			modelAndView.addObject("schemaNameInfo", tableMetaService.getSchemaNameInfo(requestMap));
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		return modelAndView;
	}
	/***
	 * <pre>
	 * 메서드명: mettb03
	 * 설명: 테이블메타 상세페이지
	 * </pre>
	 * @param tableMetaSno
	 * @param modelAndView
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/METTB03")
	public ModelAndView mettb03(@RequestParam String tableMetaSno, ModelAndView modelAndView) throws CustomException {
		
		CustomMap requestMap = new CustomMap();
		try {
			requestMap.put("tableMetaSno", tableMetaSno);
			CustomMap tableMetaInfo = tableMetaService.getTableMetaInfo(requestMap);
			modelAndView.addObject("tableMetaInfo", tableMetaInfo);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		modelAndView.setViewName("meta2024/table/tableMetaDetail");
		return modelAndView;
	}
	
	/***
	 * <pre>
	 * 메서드명: mettb04
	 * 설명: 테이블메타 등록요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/METTB04")
	@ResponseBody
	public Object mettb04(@RequestBody CustomMap customMap) throws CustomException {
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap resultMap = new CustomMap();
		
		try {
			tableMetaService.insertTableMetaInfo(customMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		
		return getResponse(resultMap);
	}
	/***
	 * <pre>
	 * 메서드명: mettb05
	 * 설명: 테이블메타 삭제요청
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/METTB05")
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
}
