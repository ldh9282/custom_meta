package com.custom.met;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.meta2024.table.service.TableMetaDao;

import lombok.extern.log4j.Log4j2;

@SpringBootTest @Log4j2
public class TableMetaTests {

	@Autowired
	private TableMetaDao tableMetaDao;
	
//	@Test
	public void test1() {
		CustomMap requestMap = new CustomMap();
		requestMap.put("scope", "");
		requestMap.put("keyword", "");
		requestMap.put("pageNum", 1);
		requestMap.put("rowAmountPerPage", 10);
	}
	
}
