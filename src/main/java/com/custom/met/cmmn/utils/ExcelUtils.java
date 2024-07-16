package com.custom.met.cmmn.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.custom.met.cmmn.model.CustomMap;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ExcelUtils {

    /***
     * <pre>
     * 메서드명: convertExceltoDataList
     * 설명: 엑셀행들을 데이터리스트로 변환
     * 
     * --------------------------------------
     * 반환 프로퍼티
     * --------------------------------------
     * sheetName 시트명
     * headerList 헤더리스트 {@code List<String>}
     * dataList 데이터리스트 {@code List<CustomMap>}
     * --------------------------------------
     * </pre>
     * @param file
     * @return
     */
    public static CustomMap convertExceltoDataList(MultipartFile file) {
    	if (log.isInfoEnabled()) {log.info("ExcelUtils.convertExceltoMap ::: file.getOriginalFilename ::: " + file.getOriginalFilename());}
    	
    	CustomMap returnMap = new CustomMap();
    	
        if (file.isEmpty()) {
        	if (log.isInfoEnabled()) {log.info("ExcelUtils.convertExceltoMap ::: file.isEmpty");}
            return returnMap;
        }

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            List<String> headers = new ArrayList<>();
            List<CustomMap> dataList = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                CustomMap rowData = new CustomMap();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (row.getRowNum() == 0) {
                        headers.add(cell.toString());
                    } else {
                    	String key = headers.get(cell.getColumnIndex());
						if (cell.getCellType() == CellType.NUMERIC) {
                    		if (DateUtil.isCellDateFormatted(cell)) {
                    			Date cellValue = cell.getDateCellValue();
                    			String value = DateUtils.formatString(cellValue, "yyyy-MM-dd");
                            	rowData.put(key, value);

                    		} else {
                    			double value = cell.getNumericCellValue();
								rowData.put(key, value);
                    		}
                    	} else {
                    		String cellValue = cell.getStringCellValue();
                    		rowData.put(key, cellValue);
                    	}
                    }
                }

                if (!MapUtils.isAllNVL(rowData)) {
                    dataList.add(rowData);
                }
            }

            returnMap.put("sheetName", sheet.getSheetName());
            returnMap.put("headerList", headers);
            returnMap.put("dataList", dataList);
            if (log.isInfoEnabled()) {log.info("ExcelUtils.convertExceltoMap ::: sheetName ::: " + sheet.getSheetName());}
            if (log.isInfoEnabled()) {log.info("ExcelUtils.convertExceltoMap ::: headerList ::: " + headers);}
            if (log.isInfoEnabled()) {log.info("ExcelUtils.convertExceltoMap ::: dataList.size ::: " + dataList.size());}
            return returnMap;
        } catch (IOException e) {
        	if (log.isInfoEnabled()) {log.info("ExcelUtils.convertExceltoMap ::: IOException ::: " + e);}
            e.printStackTrace();
            return returnMap;
        } catch (Exception e) {
        	if (log.isInfoEnabled()) {log.info("ExcelUtils.convertExceltoMap ::: Exception ::: " + e);}
            e.printStackTrace();
            return returnMap;
		}

    }
}
