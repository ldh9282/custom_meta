package com.custom.met.meta2024.schema.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class SchemaMetaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: createSchema
	 * 설명: 스키마 생성
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int createSchema(CustomMap customMap) {
		return sst.update("SchemaMeta.createSchema", customMap);
	}

	
	
}
