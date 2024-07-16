package com.custom.met;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.login.service.LoginServcie;

@SpringBootTest
public class MemberTests {

	@Autowired
	private LoginServcie loginServcie;
	@Test
	void test01() {
		CustomMap customMap = new CustomMap();
		customMap.put("memId", "admin");
		customMap.put("memPw", "admin");
		try {
			loginServcie.insertMemberDetail(customMap);
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
}
