package com.custom.met.meta2024.schema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;

@Service
public class SchemaMetaService {

	@Autowired
	private SchemaMetaDao schemaMetaDao;
	
	@Transactional
	public CustomMap insertOwnerMetaInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap requestMap = new CustomMap();
		
		try {
			requestMap.put("schemaName", customMap.getString("schemaName"));
			
			schemaMetaDao.createSchema(requestMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new CustomException(CustomExceptionCode.ERR521, new String[] {"소유자메타기본정보"}, e);
		}
		
		return resultMap;
	}
}
