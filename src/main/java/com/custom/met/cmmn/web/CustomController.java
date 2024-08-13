package com.custom.met.cmmn.web;

import com.custom.met.cmmn.constant.CustomAuthCode;
import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.utils.SecurityUtils;

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
	
	/**
	 * <pre>
	 * 메소드명: getAuthResponse
	 * 설명: ajax 성공응답 (로그인 안하면 예외)
	 * --------------------------------------
     * 반환 프로퍼티
     * --------------------------------------
     * response.header.auth.authenticatedYn === '1'
     * response.header.auth.username
     * response.header.auth.authList
	 * response.header.status == 0000
	 * response.body
	 * </pre>
	 * @param object
	 * @return
	 * @throws CustomException
	 */
	public static CustomMap getAuthResponse(Object object) throws CustomException {
		if (!SecurityUtils.isAuthenticated()) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { "인증되지 않은 요청입니다." });
		}
		
		CustomMap response = new CustomMap();
		
		CustomMap header = new CustomMap();
		CustomMap auth = new CustomMap();
		auth.put("authenticatedYn", "1");
		auth.put("username", SecurityUtils.getUsername());
		auth.put("authList", SecurityUtils.getAuthorities());
		
		header.put("status", "0000");
		header.put("auth", auth);
		response.put("header", header);
		response.put("body", object);
		return response;
	}
	
	/**
	 * <pre>
	 * 메소드명: getAuthResponse
	 * 설명: ajax 성공응답 (권한 없으면 예외)
	 * --------------------------------------
     * 반환 프로퍼티
     * --------------------------------------
     * response.header.auth.authenticatedYn === '1'
     * response.header.auth.authCode === '1'
     * response.header.auth.authCodeName === '1'
     * response.header.auth.authorizedYn === '1'
     * response.header.auth.username
     * response.header.auth.authList
	 * response.header.status == 0000
	 * response.body
	 * </pre>
	 * @param object
	 * @return
	 * @throws CustomException
	 */
	public static CustomMap getAuthResponse(Object object, CustomAuthCode customAuthCode) throws CustomException {
		if (!SecurityUtils.isAuthenticated()) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { "인증되지 않은 요청입니다." });
		}
		
		if (!SecurityUtils.hasAuthorized(customAuthCode)) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { "허용되지 않은 권한입니다." });
		}
		
		CustomMap response = new CustomMap();
		
		CustomMap header = new CustomMap();
		CustomMap auth = new CustomMap();
		auth.put("authenticatedYn", "1");
		auth.put("authCode", customAuthCode.getCode());
		auth.put("authCodeName", customAuthCode.getCodeName());
		auth.put("authorizedYn", "1");
		auth.put("username", SecurityUtils.getUsername());
		auth.put("authList", SecurityUtils.getAuthorities());
		
		header.put("status", "0000");
		header.put("auth", auth);
		response.put("header", header);
		response.put("body", object);
		return response;
	}
}
