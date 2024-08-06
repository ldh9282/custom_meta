package com.custom.met.meta2024.term.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.utils.SecurityUtils;
import com.custom.met.cmmn.utils.StringUtils;

@Service
public class TermService {

	@Autowired
	private TermDao termDao;
	
	@Transactional
	public CustomMap insertTermInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap requestMap = new CustomMap();
		
		try {
			requestMap.put("domainSno", customMap.getString("domainSno"));
			
			CustomMap termSnoMap = termDao.selectTermSno();
			requestMap.put("termSno", termSnoMap.getString("termSno"));
			requestMap.put("termName", customMap.getString("termName"));
			requestMap.put("termCamelName", customMap.getString("termCamelName"));
			requestMap.put("termSnakeName", customMap.getString("termSnakeName"));
			requestMap.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			
			
			termDao.insertTerm(requestMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new CustomException(CustomExceptionCode.ERR521, new String[] {"용어정보"}, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getTermInfoList(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			customMap.put("pageNum", customMap.getString("pageNum", "1"));
			customMap.put("rowAmountPerPage", customMap.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> termInfoList = termDao.selectTermList(customMap);
			
			resultMap.put("termInfoList", termInfoList);
			resultMap.put("count", termInfoList.size() > 0 ? termInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"용어정보목록"}, e);
		}
		
		
		return resultMap;
	}
	
	public CustomMap getTermScInfoList(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			
			List<CustomMap> termScInfoList = termDao.selectTermScList(customMap);
			
			resultMap.put("termScInfoList", termScInfoList);
			resultMap.put("count", termScInfoList.size() > 0 ? termScInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"용어정보목록"}, e);
		}
		
		
		return resultMap;
	}
}
