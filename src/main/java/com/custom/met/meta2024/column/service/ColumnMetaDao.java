package com.custom.met.meta2024.column.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class ColumnMetaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectColumnMetaSno
	 * 설명: 컬럼메타일련번호조회
	 * </pre>
	 * @return
	 */
	public CustomMap selectColumnMetaSno() {
		return sst.selectOne("ColumnMeta.selectColumnMetaSno");
	}
	/**
	 * <pre>
	 * 메서드명: insertColumnMeta
	 * 설명: 컬럼메타기본정보등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertColumnMeta(CustomMap customMap) {
		return sst.insert("ColumnMeta.insertColumnMeta", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectColumnMetaInfoList
	 * 설명: 컬럼메타기본정보목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectColumnMetaInfoList(CustomMap customMap) {
		return sst.selectList("ColumnMeta.selectColumnMetaInfoList", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectColumnMetaInfo
	 * 설명: 컬럼메타기본정보상세조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectColumnMetaInfo(CustomMap customMap) {
		return sst.selectOne("ColumnMeta.selectColumnMetaInfo", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: updateColumnMetaInfo
	 * 설명: 컬럼메타기본정보수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int updateColumnMetaInfo(CustomMap customMap) {
		return sst.update("ColumnMeta.updateColumnMetaInfo", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: alterColumnName
	 * 설명: 컬럼명 수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int alterColumnName(CustomMap customMap) {
		return sst.update("ColumnMeta.alterColumnName", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: alterColumnType
	 * 설명: 컬럼타입 수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int alterColumnType(CustomMap customMap) {
		return sst.update("ColumnMeta.alterColumnType", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: deleteColumnMetaInfo
	 * 설명: 컬럼메타기본정보삭제
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int deleteColumnMetaInfo(CustomMap customMap) {
		return sst.update("ColumnMeta.deleteColumnMetaInfo", customMap);
	}
}
