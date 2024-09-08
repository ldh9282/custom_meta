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
import com.custom.met.cmmn.utils.ArrayUtils;
import com.custom.met.cmmn.utils.StringUtils;
import com.custom.met.meta2024.domain.service.DomainDao;

@Service
public class TermService {

	@Autowired
	private DomainDao domainDao;
	
	@Autowired
	private TermDao termDao;
	
	@Transactional
	public CustomMap insertTermInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap requestMap = new CustomMap();
		
		try {
			requestMap.put("domainSno", customMap.getString("domainSno"));
			
			String termSno = termDao.selectTermSno();
			requestMap.put("termSno", termSno);
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
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"용어정보검색"}, e);
		}
		
		
		return resultMap;
	}

	public CustomMap deleteTermInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			
			termDao.deleteTermInfo(customMap);
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR541, new String[] {"용어정보"}, e);
		}
		
		return resultMap;
	}

	public CustomMap exceluploadTermInfo(CustomMap customMap) throws CustomException  {
		CustomMap resultMap = new CustomMap();
		
		List<CustomMap> dataList = customMap.getCustomMapList("dataList");
		
		try {
			for (CustomMap item : dataList) {
				boolean checkRequiredValues = ArrayUtils.checkRequiredValues(new String[] {
						item.getString("용어명")
						, item.getString("용어영문명")
						, item.getString("도메인명")
				});
				
				if (checkRequiredValues) {
					
					CustomMap requestMap = new CustomMap();
					requestMap.put("termName", item.getString("용어명"));
					if (item.getString("용어영문명").contains("_")) {
						requestMap.put("termCamelName", StringUtils.snake2Camel(item.getString("용어영문명")));
						requestMap.put("termSnakeName", item.getString("용어영문명"));
					} else {
						requestMap.put("termCamelName", item.getString("용어영문명"));
						requestMap.put("termSnakeName", StringUtils.camel2Snake(item.getString("용어영문명")));
					}
					requestMap.put("domainName", item.getString("도메인명"));
					requestMap.put("sysCreator", SecurityUtils.getUsername());
					
					CustomMap termRegCheck = termDao.selectTermRegCheck(requestMap);
					
					if ("Y".equals(termRegCheck.getString("domainNameAbleYn")) && "Y".equals(termRegCheck.getString("termNameAbleYn"))) {
						
						String termSno = termDao.selectTermSno();
						requestMap.put("domainSno", termRegCheck.getString("domainSno"));
						requestMap.put("termSno", termSno);
						
						
						
						termDao.insertTerm(requestMap);
					}
				}
			}
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR521, new String[] {"용어정보"}, e);
		}
		
		return resultMap;
		
	}
}
