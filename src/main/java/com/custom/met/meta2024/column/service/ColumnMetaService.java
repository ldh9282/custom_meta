package com.custom.met.meta2024.column.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.utils.SecurityUtils;

@Service
public class ColumnMetaService {

	@Autowired
	private ColumnMetaDao columnMetaDao;
	
	public CustomMap getColumnMetaInfoList(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			customMap.put("pageNum", customMap.getString("pageNum", "1"));
			customMap.put("rowAmountPerPage", customMap.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> columnMetaInfoList = columnMetaDao.selectColumnMetaInfoList(customMap);
			
			resultMap.put("columnMetaInfoList", columnMetaInfoList);
			resultMap.put("count", columnMetaInfoList.size() > 0 ? columnMetaInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"컬럼메타정보목록"}, e);
		}
		
		
		return resultMap;
	}
	
	public CustomMap getColumnMetaInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap requestMap = new CustomMap();
		
		try {
			requestMap.put("tableMetaSno", customMap.getString("tableMetaSno"));
			requestMap.put("columnMetaSno", customMap.getString("columnMetaSno"));
			
			resultMap = columnMetaDao.selectColumnMetaInfo(requestMap);
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"컬럼메타정보상세"}, e);
		}
		
		
		return resultMap;
	}
	
	@Transactional
	public CustomMap updateColumnMetaInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		
		try {
			CustomMap columnMeta = columnMetaDao.selectColumnMetaInfo(customMap);
			customMap.put("sysModifier", SecurityUtils.getUsername());
			customMap.put("oldColumnName", columnMeta.getString("columnName"));
			
			if (!customMap.getString("oldColumnName").equals(customMap.getString("columnName"))) {
				columnMetaDao.alterColumnName(customMap);
			}
			
			columnMetaDao.updateColumnMetaInfo(customMap);
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new CustomException(CustomExceptionCode.ERR531, new String[] {"컬럼메타정보상세"}, e);
		}
		
		
		return resultMap;
	}

}
