package com.custom.met.sample.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class SampleDao {
	@Autowired
	private SqlSessionTemplate sst;
	
	/***
	 * <pre>
	 * 메서드명: selectTableMetaSno
	 * 설명: 테이블메타일련번호 조회
	 * </pre>
	 * @return
	 */
	public CustomMap selectTest(CustomMap customMap) {
		return sst.selectOne("Sample.selectTest", customMap);
	}
}
