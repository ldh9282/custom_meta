package com.custom.met.meta2024.seq.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class SeqMetaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	
	/***
	 * <pre>
	 * 메서드명: createSeq
	 * 설명: 시퀀스생성
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int createSeq(CustomMap customMap) {
		return sst.update("SeqMeta.createSeq", customMap);
	}
	
	/***
	 * <pre>
	 * 메서드명: selectSeqMetaSno
	 * 설명: 시퀀스메타일련번호조회
	 * </pre>
	 * @return
	 */
	public CustomMap selectSeqMetaSno() {
		return sst.selectOne("SeqMeta.selectSeqMetaSno");
	}
	/***
	 * <pre>
	 * 메서드명: insertSeqMeta
	 * 설명: 시퀀스메타기본정보등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertSeqMeta(CustomMap customMap) {
		return sst.insert("SeqMeta.insertSeqMeta", customMap);
	}

	/**
	 * <pre>
	 * 메서드명: selectSeqMetaInfoList
	 * </pre>
	 * 설명: 시퀀스메타기본정보목록조회
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectSeqMetaInfoList(CustomMap customMap) {
		return sst.selectList("SeqMeta.selectSeqMetaInfoList", customMap);
	}

	/***
	 * <pre>
	 * 메서드명: dropSeq
	 * 설명: 시퀀스삭제
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int dropSeq(CustomMap customMap) {
		return sst.update("SeqMeta.dropSeq", customMap);
	}

	/***
	 * <pre>
	 * 메서드명: deleteSeqMeta
	 * 설명: 시퀀스메타기본정보삭제
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int deleteSeqMeta(CustomMap customMap) {
		return sst.update("SeqMeta.deleteSeqMeta", customMap);
		
	}

	/***
	 * <pre>
	 * 메서드명: selectSeqMetaInfoDetail
	 * 설명: 시퀀스메타기본정보조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectSeqMetaInfoDetail(CustomMap customMap) {
		return sst.selectOne("SeqMeta.selectSeqMetaInfoDetail", customMap);
		
	}
}
