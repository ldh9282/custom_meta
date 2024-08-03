package com.custom.met.meta2024.term.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class TermDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectTermSno
	 * 설명: 용어일련번호조회
	 * </pre>
	 * @return
	 */
	public CustomMap selectTermSno() {
		return sst.selectOne("Term.selectTermSno");
	}
	
	/**
	 * <pre>
	 * 메서드명: insertTerm
	 * 설명: 용어정보등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertTerm(CustomMap customMap) {
		return sst.insert("Term.insertTerm", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectTerm
	 * 설명: 용어정보조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectTerm(CustomMap customMap) {
		return sst.selectOne("Term.selectTerm", customMap);
		
	}
	
	/**
	 * <pre>
	 * 메서드명: selectTermList
	 * 설명: 용어정보목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectTermList(CustomMap customMap) {
		return sst.selectList("Term.selectTermList", customMap);
		
	}
}
