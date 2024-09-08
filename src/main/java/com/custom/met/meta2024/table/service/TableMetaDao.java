package com.custom.met.meta2024.table.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class TableMetaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectTableMetaSno
	 * 설명: 테이블메타일련번호 조회
	 * </pre>
	 * @return
	 */
	public String selectTableMetaSno() {
		return sst.selectOne("TableMeta.selectTableMetaSno");
	}
	
	/**
	 * <pre>
	 * 메서드명: insertTableMeta
	 * 설명: 테이블메타기본정보 등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertTableMeta(CustomMap customMap) {
		return sst.insert("TableMeta.insertTableMeta", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectTableMeta
	 * 설명: 테이블메타기본정보 상세조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectTableMeta(CustomMap customMap) {
		return sst.selectOne("TableMeta.selectTableMeta", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectTableColumnList
	 * 설명: 테이블컬럼 목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectTableColumnList(CustomMap customMap) {
		return sst.selectList("TableMeta.selectTableColumnList", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: createTable
	 * 설명: 테이블생성
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int createTable(CustomMap customMap) {
		return sst.update("TableMeta.createTable", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: addTableComment
	 * 설명: 테이블코멘트생성
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int addTableComment(CustomMap customMap) {
		return sst.update("TableMeta.addTableComment", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: createPkIndex
	 * 설명: Pk인덱스생성
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int createPkIndex(CustomMap customMap) {
		return sst.update("TableMeta.createPkIndex", customMap);
	}
	
	
	/**
	 * <pre>
	 * 메서드명: selectSchemaNameList
	 * 설명: 스키마명 목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectSchemaNameList(CustomMap customMap) {
		return sst.selectList("TableMeta.selectSchemaNameList", customMap);
	}

	/**
	 * <pre>
	 * 메서드명: selectTableMetaInfoList
	 * 설명: 테이블메타 목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectTableMetaInfoList(CustomMap customMap) {
		return sst.selectList("TableMeta.selectTableMetaInfoList", customMap);
	}

	/**
	 * <pre>
	 * 메서드명: dropTable
	 * 설명: 테이블삭제
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int dropTable(CustomMap customMap) {
		return sst.update("TableMeta.dropTable", customMap);
	}

	/**
	 * <pre>
	 * 메서드명: deleteTableMetaInfo
	 * 설명: 테이블메타기본정보삭제
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int deleteTableMetaInfo(CustomMap customMap) {
		return sst.update("TableMeta.deleteTableMetaInfo", customMap);
	}
	
}
