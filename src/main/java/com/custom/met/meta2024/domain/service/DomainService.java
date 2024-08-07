package com.custom.met.meta2024.domain.service;

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
public class DomainService {

	@Autowired
	private DomainDao domainDao;
	
	@Transactional
	public CustomMap insertDomainInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap requestMap = new CustomMap();
		
		try {
			CustomMap domainRegAbleYnMap = domainDao.selectDomainRegAbleYn(customMap);
			if ("N".equals(domainRegAbleYnMap.getString("domainRegAbleYn"))) {
				throw new CustomException(CustomExceptionCode.ERR999, new String[] {"도메인명 중복 ::: " + customMap.getString("domainName")});
			}
			CustomMap domainSnoMap = domainDao.selectDomainSno();
			requestMap.put("domainSno", domainSnoMap.getString("domainSno"));
			requestMap.put("domainName", customMap.getString("domainName"));
			requestMap.put("domainType", customMap.getString("domainType"));
			requestMap.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			
			
			domainDao.insertDomain(requestMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new CustomException(CustomExceptionCode.ERR521, new String[] {"도메인정보"}, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getDomainInfoList(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			customMap.put("pageNum", customMap.getString("pageNum", "1"));
			customMap.put("rowAmountPerPage", customMap.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> domainInfoList = domainDao.selectDomainList(customMap);
			
			resultMap.put("domainInfoList", domainInfoList);
			resultMap.put("count", domainInfoList.size() > 0 ? domainInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"도메인정보목록"}, e);
		}
		
		
		return resultMap;
	}
	
	public CustomMap getDomainScInfoList(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			
			List<CustomMap> domainScInfoList = domainDao.selectDomainScList(customMap);
			
			resultMap.put("domainScInfoList", domainScInfoList);
			resultMap.put("count", domainScInfoList.size() > 0 ? domainScInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"도메인정보검색"}, e);
		}
		
		
		return resultMap;
	}
}
