package com.custom.met.meta2024.column.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class ColumnExcelDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: updateColumnMetaInfo
	 * 설명: 컬럼메타정보수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int updateColumnMetaInfo(CustomMap customMap) {
		return sst.update("ColumnExcel.updateColumnMetaInfo", customMap);
	}
}
