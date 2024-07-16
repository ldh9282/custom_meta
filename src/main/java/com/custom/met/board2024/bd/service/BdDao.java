package com.custom.met.board2024.bd.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;


@Repository
public class BdDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	public int insertBoardInfo(CustomMap customMap) {
		return sst.insert("Bd.insertBoardInfo", customMap);
	}
	
	public CustomMap selectBoardInfo(CustomMap customMap) {
		return sst.selectOne("Bd.selectBoardInfo", customMap);
	}
	
	public int updateBoardInfo(CustomMap customMap) {
		return sst.update("Bd.updateBoardInfo", customMap);
	}
	
	List<CustomMap> selectBoardInfoList(CustomMap customMap) {
		return sst.selectList("Bd.selectBoardInfoList", customMap);
	}
	
	public int updateBoardDelY(CustomMap customMap) {
		return sst.update("Bd.updateBoardDelY", customMap);
	}
}
