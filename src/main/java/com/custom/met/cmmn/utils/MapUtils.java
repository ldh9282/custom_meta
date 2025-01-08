package com.custom.met.cmmn.utils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;

public class MapUtils {

	/**
	 * <pre>
	 * 메서드명: isAllNVL
	 * 설명:  map 의 모든 값이 null 또는 empty 인지 체크 (엑셀 빈행 체크)
	 * </pre>
	 * @param map
	 * @return boolean
	 */
    public static boolean isAllNVL(Map<String, Object> map) {
        for (Object value : map.values()) {
            if (value != null && !value.toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * <pre>
     * 메서드명: objectToMap
	 * 설명: 객체를 맵으로 변환
     * </pre>
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new LinkedHashMap<>();
        Class<?> theClass = obj.getClass();

        Field[] fields = theClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                map.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
            	e.printStackTrace();
			}
        }
        return map;
    }
    
    /**
     * <pre>
     * 메서드명: paramsValidation
	 * 설명: 파라미터 유효성 검사
     * </pre>
     * @param params
     * @param validationList new String[][] { "key", "format" }
     * @throws CustomException
     */
    public static void paramsValidation(CustomMap params, String[][] validationList) throws CustomException {
    	for (String key : params.keySet()) {
    		params.put(key, params.getString(key).trim());
    	}
    	
    	if (validationList != null) {
    		for (String[] validation : validationList ) {
    			String key = validation[0];
    			String format = "";
    			if (validation.length == 2) {
    				format = validation[1];
    			}
    			
    			if ("required".equals(format)) {
    				if ("".equals(params.getString(key))) {
    					throw new CustomException(CustomExceptionCode.ERR600, new String[] { key });
    				}
    			}
    			
    			if ("number".equals(format)) {
    				if (!"".equals(params.getString(key))) {
    					if (!StringUtils.isNumber(params.getString(key))) {
    						throw new CustomException(CustomExceptionCode.ERR601, new String[] { key, params.getString(key) });
    					}
    				}
    			}
    		}
    	}
    }

}
