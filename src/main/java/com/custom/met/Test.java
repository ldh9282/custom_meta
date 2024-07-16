package com.custom.met;

import java.util.Date;

import com.custom.met.cmmn.utils.DateUtils;

public class Test {

	public static void main(String[] args) {
		
		System.out.println(DateUtils.formatString(new Date(), "YYYY.MM.dd HH:mm:ss"));
	}
}
