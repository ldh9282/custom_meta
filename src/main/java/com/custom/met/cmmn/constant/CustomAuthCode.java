package com.custom.met.cmmn.constant;

/***
 * <pre>
 * 클래스명: CustomAuthCode
 * 설명: 권한코드
 * </pre>
 */
public enum CustomAuthCode {

	/**
	 * <pre>
	 * 구분코드: A001 구분코드명: ADMIN
	 * </pre>
	 */
	A001("A001", "ADMIN"),
	/**
	 * <pre>
	 * 구분코드: M001 구분코드명: MEMBER
	 * </pre>
	 */
	M001("M001", "MEMBER"),
	;
	private final String code;
	private final String codeName;
	
	CustomAuthCode(String code, String codeName) {
		this.code = code;
		this.codeName = codeName;
	}

	public String getCode() {
		return code;
	}

	public String getCodeName() {
		return codeName;
	}
}
