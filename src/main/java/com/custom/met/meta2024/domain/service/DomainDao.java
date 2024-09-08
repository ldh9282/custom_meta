package com.custom.met.meta2024.domain.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class DomainDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectDomainSno
	 * 설명: 도메인일련번호조회
	 * </pre>
	 * @return
	 */
	public String selectDomainSno() {
		return sst.selectOne("Domain.selectDomainSno");
	}
	/**
	 * <pre>
	 * 메서드명: selectDomainRegAbleYn
	 * 설명: 도메인등록가능여부조회
	 * </pre>
	 * @return
	 */
	public CustomMap selectDomainRegAbleYn(CustomMap customMap) {
		return sst.selectOne("Domain.selectDomainRegAbleYn", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: insertDomainInfo
	 * 설명: 도메인정보등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertDomain(CustomMap customMap) {
		return sst.insert("Domain.insertDomain", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectDomain
	 * 설명: 도메인정보조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectDomain(CustomMap customMap) {
		return sst.selectOne("Domain.selectDomain", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectDomainList
	 * 설명: 도메인정보목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectDomainList(CustomMap customMap) {
		return sst.selectList("Domain.selectDomainList", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectDomainScList
	 * 설명: 도메인정보목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectDomainScList(CustomMap customMap) {
		return sst.selectList("Domain.selectDomainScList", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: deleteDomainInfo
	 * 설명: 도메인정보삭제
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int deleteDomainInfo(CustomMap customMap) {
		return sst.update("Domain.deleteDomainInfo", customMap);
	}
}
