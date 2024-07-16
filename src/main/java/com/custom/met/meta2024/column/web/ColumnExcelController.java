package com.custom.met.meta2024.column.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.utils.ExcelUtils;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2024.column.service.ColumnExcelService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class ColumnExcelController extends CustomController {

	@Autowired
	private ColumnExcelService columnExcelService;
	
	/***
	 * <pre>
	 * 메서드명: metce01
	 * 설명: 컬럼메타엑셀업로드1페이지 (다건업데이트)
	 * </pre>
	 * @param modelAndView
	 * @param map
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/METCE01")
	public ModelAndView metce01(ModelAndView modelAndView, @RequestParam Map<String, Object> map) throws CustomException {
		
		CustomMap requestMap = new CustomMap(map);
		if (log.isDebugEnabled()) { log.debug("METCE01 ::: " + requestMap); }
		
		
		
		
		modelAndView.setViewName("meta2024/column/columnMetaExcelUpload1");
		return modelAndView;
	}
	
	/***
	 * <pre>
	 * 메서드명: metce01
	 * 설명: 컬럼메타엑셀다건업데이트요청
	 * </pre>
	 * @param file
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/METCE02")
	public Object metce02(@RequestParam("file") MultipartFile file) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap excelMap = ExcelUtils.convertExceltoDataList(file);
		
		try {
			resultMap = columnExcelService.updateColumnMetaInfo(excelMap);
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500);
		}
		return getResponse(resultMap);
	}
}
