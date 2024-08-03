package com.custom.met.cmmn.web;

import com.custom.met.cmmn.model.CustomMap;

/**
 * <pre>
 * 클래스명: CustomController
 * 설명: 공통처리를 위해 상속한다
 * </pre>
 */
public class CustomController {

	/**
	 * <pre>
	 * 메서드명: getResponse
	 * 설명: ajax 성공응답
	 * --------------------------------------
     * 반환 프로퍼티
     * --------------------------------------
	 * response.header.status == 0000
	 * response.body
	 * </pre>
	 * @param object
	 * @return
	 */
	public static CustomMap getResponse(Object object) {
		
		CustomMap response = new CustomMap();
		
		CustomMap header = new CustomMap();
		
		header.put("status", "0000");
		response.put("header", header);
		response.put("body", object);
		
		return response;
        
	}
	/**
	 * <pre>
	 * 메서드명: getErrorResponse
	 * 설명: ajax 실패응답
	 * --------------------------------------
     * 반환 프로퍼티
     * --------------------------------------
	 * response.header.status != 0000 
	 * response.header.errorMsg
	 * </pre>
	 * @param errorMsg
	 * @return
	 */
	public static CustomMap getErrorResponse(String errorMsg) {
		
		CustomMap response = new CustomMap();
		
		CustomMap header = new CustomMap();
		header.put("status", "9999");
		header.put("errorMsg", errorMsg);
		response.put("header", header);
		
		return response;
		
	}
}
