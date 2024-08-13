package com.custom.met.cmmn.exception;

/**
 * <pre>
 * 클래스명: CustomExceptionCode
 * 설명: 예외코드
 * </pre>
 */
public enum CustomExceptionCode {
	
	/**
	 * 시스템 내부 처리중 오류가 발생했습니다.
	 */
	ERR500("ERR500", "시스템 내부 처리중 오류가 발생했습니다."),
	/**
	 * [{0}] 시스템 내부 처리중 오류가 발생했습니다.
	 */
	ERR501("ERR501", "[{0}] 시스템 내부 처리중 오류가 발생했습니다."),
	/**
	 * [{0}] 데이터 조회 중 오류가 발생했습니다.
	 */
	ERR511("ERR511", "[{0}] 데이터 조회 중 오류가 발생했습니다."),
	/**
	 * [{0}] 데이터 등록 중 오류가 발생했습니다.
	 */
	ERR521("ERR521", "[{0}] 데이터 등록 중 오류가 발생했습니다."),
	/**
	 * [{0}] 데이터 수정 중 오류가 발생했습니다.
	 */
	ERR531("ERR531", "[{0}] 데이터 수정 중 오류가 발생했습니다."),
	/**
	 * [{0}] 데이터 삭제 중 오류가 발생했습니다.
	 */
	ERR541("ERR541", "[{0}] 데이터 삭제 중 오류가 발생했습니다."),
	/**
	 * {0}
	 */
	ERR999("ERR999", "{0}"),
	;
	
	private final String errorCode;
	private final String errorMessage;
	
	CustomExceptionCode(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	
}
