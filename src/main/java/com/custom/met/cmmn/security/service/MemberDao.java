package com.custom.met.cmmn.security.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class MemberDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectMemSno
	 * 설명: 회원일련번호 조회
	 * </pre>
	 * @param
	 * @return
	 */
	public String selectMemSno() {
		return sst.selectOne("Member.selectMemSno");
	}
	
	/**
	 * <pre>
	 * 메서드명: selectMemAuthSno
	 * 설명: 회원권한일련번호 조회
	 * </pre>
	 * @param
	 * @return
	 */
	public String selectMemAuthSno() {
		return sst.selectOne("Member.selectMemAuthSno");
	}
	
	/**
	 * <pre>
	 * 메서드명: selectMember
	 * 설명: 회원 조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectMember(CustomMap customMap) {
		return sst.selectOne("Member.selectMember", customMap);
	}

	/**
	 * <pre>
	 * 메서드명: selectMemberAuthList
	 * 설명: 회원권한 목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectMemberAuthList(CustomMap customMap) {
		return sst.selectList("Member.selectMemberAuthList", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: insertMember
	 * 설명: 회원 등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertMember(CustomMap customMap) {
		return sst.insert("Member.insertMember", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: insertMember
	 * 설명: 회원권한 등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertMemberAuth(CustomMap customMap) {
		return sst.insert("Member.insertMemberAuth", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectMemberCnt
	 * 설명: 회원중복 조회
	 * </pre>
	 * @param
	 * @return
	 */
	public CustomMap selectMemberCnt(CustomMap customMap) {
		return sst.selectOne("Member.selectMemberCnt", customMap);
	}
}
