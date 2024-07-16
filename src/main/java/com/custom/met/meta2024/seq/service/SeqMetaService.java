package com.custom.met.meta2024.seq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;

@Service
public class SeqMetaService {

	@Autowired
	private SeqMetaDao seqMetaDao;

	public CustomMap getSeqMetaInfoList(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			customMap.put("pageNum", customMap.getString("pageNum", "1"));
			customMap.put("rowAmountPerPage", customMap.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> seqMetaInfoList = seqMetaDao.selectSeqMetaInfoList(customMap);
			
			resultMap.put("seqMetaInfoList", seqMetaInfoList);
			resultMap.put("count", seqMetaInfoList.size() > 0 ? seqMetaInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"시퀀스메타정보목록"}, e);
		}
		
		
		return resultMap;
	}
}
