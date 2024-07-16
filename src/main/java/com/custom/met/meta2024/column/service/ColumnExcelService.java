package com.custom.met.meta2024.column.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.utils.SecurityUtils;

@Service
public class ColumnExcelService {

	@Autowired
	private ColumnExcelDao columnExcelDao;
	
	
	public CustomMap updateColumnMetaInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		
		try {
			List<CustomMap> dataList = customMap.getCustomMapList("dataList");
			for (CustomMap data : dataList) {
				CustomMap columnMap = new CustomMap();
				columnMap.put("columnName", data.getString("columnName"));
				columnMap.put("columnCamelName", data.getString("columnCamelName"));
				columnMap.put("columnSnakeName", data.getString("columnSnakeName"));
				columnMap.put("sysModifier", SecurityUtils.getUsername());
				
				columnExcelDao.updateColumnMetaInfo(columnMap);
			}
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] {"컬럼메타정보목록"});
		}
		
		
		return resultMap;
	}

}
