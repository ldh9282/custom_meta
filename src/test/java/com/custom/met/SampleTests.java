package com.custom.met;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.sample.service.SampleDao;

import lombok.extern.log4j.Log4j2;

@SpringBootTest @Log4j2
public class SampleTests {

	@Autowired
	private SampleDao sampleDao;
	
	@Test
	public void test1() {
		
		CustomMap customMap = new CustomMap();
		customMap.put("userInfo", "12");
		if (log.isDebugEnabled()) {log.debug(customMap);}
		CustomMap selectTest = sampleDao.selectTest(customMap);
		if (log.isDebugEnabled()) {log.debug(selectTest);}
		
		CustomMap customMap2 = new CustomMap();
		if (log.isDebugEnabled()) {log.debug(customMap2);}
		customMap2.put("abc_user", "1");
		customMap2.put("BCD_USER", "1");
		customMap2.put("edfUser", "1");
		if (log.isDebugEnabled()) {log.debug(customMap2);}
	}
}
