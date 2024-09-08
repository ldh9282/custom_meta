package com.custom.met.cmmn.utils;

public class ArrayUtils {

	/**
	 * <pre>
	 * 메서드명: checkRequiredValues
	 * 설명: 필수값 체크
	 * </pre>
	 * @param values
	 * @return
	 */
	public static boolean checkRequiredValues(String[] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        
        for (String value : values) {
            if (value == null || value.trim().isEmpty()) {
                return false;
            }
        }
        
        return true;
    }
}
