package com.custom.met.cmmn.utils;

public class ObjectUtils {

	/***
	 * <pre>
	 * 메서드명: hasNVL
	 * 설명: 필수값 체크
	 * </pre>
	 * @param values
	 * @return boolean
	 */
	public static boolean hasNVL(Object... values) {
	    for (Object value : values) {
	        if (value == null) {
	            return true;
	        }

	        if (value instanceof String) {
	            if (((String) value).trim().isEmpty()) {
	                return true;
	            }
	        } else if (value instanceof Double) {
	            if ((Double) value == 0.0) {
	                return true;
	            }
	        } else if (value instanceof Integer) {
	            if ((Integer) value == 0) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	/***
	 * <pre>
	 * 메서드명: NVL
	 * 설명: Null 또는 value 없으면 defaultValue 반환
	 * </pre>
	 * @param value
	 * @param defaultValue
	 * @return Object
	 */
	public static Object NVL(Object value, Object defaultValue) {
	    if (value == null) {
	    	return defaultValue;
	    }
	    
	    if (value instanceof String && ((String) value).trim().isEmpty()) {
	    	return defaultValue;
		} else if (value instanceof Double && (Double) value == 0.0) {
			return defaultValue;
		} else if (value instanceof Integer && (Integer) value == 0) {
			return defaultValue;
		} else {
	        return value;
	    }
	}
}
