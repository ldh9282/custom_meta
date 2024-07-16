package com.custom.met.cmmn.utils;

public class StringUtils {

	/***
	 * <pre>
	 * 메서드명: NVL
	 * 설명: Null or Empty 이면 기본값 반환
	 * </pre>
	 * @param input
	 * @param defaultValue
	 * @return String
	 */
	public static String NVL(String input, String defaultValue) {
	    if (input == null || input.isEmpty()) {
	        return defaultValue;
	    } else {
	        return input;
	    }
	}
}
