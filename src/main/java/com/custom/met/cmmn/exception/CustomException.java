package com.custom.met.cmmn.exception;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final CustomExceptionCode customExceptionCode;

	/**
	 * <pre>
	 * 메서드명: CustomException 
	 * 설명: 기본 에러메시지를 가지는 예외
	 * </pre>
	 * @param customExceptionCode 예외코드
	 */
    public CustomException(CustomExceptionCode customExceptionCode) {
        super(formatMessage(customExceptionCode.getErrorMessage()));
        this.customExceptionCode = customExceptionCode;
        if (log.isErrorEnabled()) { printStackTrace(); }
        
    }
    /**
     * <pre>
     * 메서드명: CustomException 
     * 설명: 기본 에러메시지를 가지며 원인 예외를 함께 출력하는 예외
     * </pre>
     * @param customExceptionCode 예외코드
     * @param Exception 예외
     */
    public CustomException(CustomExceptionCode customExceptionCode, Throwable e) {
        super(formatMessage(customExceptionCode.getErrorMessage()), e);
        this.customExceptionCode = customExceptionCode;
        if (log.isErrorEnabled()) { e.printStackTrace(); }
    }
    /**
     * <pre>
     * 메서드명: CustomException 
     * 설명: 변수 바인딩한 에러메시지를 가지는 예외
     * </pre>
     * @param customExceptionCode 예외코드
     * @param args 바인딩할 변수
     */
    public CustomException(CustomExceptionCode customExceptionCode, String[] args) {
    	super(formatMessage(customExceptionCode.getErrorMessage(), (Object[]) args));
    	this.customExceptionCode = customExceptionCode;
    	if (log.isErrorEnabled()) { printStackTrace(); }
    }
    /**
     * <pre>
     * 메서드명: CustomException 
     * 설명: 변수 바인딩한 에러메시지를 가지며 원인 예외를 함께 출력하는 예외
     * </pre>
     * @param customExceptionCode 예외코드
     * @param args 바인딩할 변수
     * @param Exception 예외
     */
    public CustomException(CustomExceptionCode customExceptionCode, String[] args, Throwable e) {
    	super(formatMessage(customExceptionCode.getErrorMessage(), (Object[]) args), e);
    	this.customExceptionCode = customExceptionCode;
    	if (log.isErrorEnabled()) { e.printStackTrace(); }
    }

    public CustomExceptionCode getCustomExceptionCode() {
        return this.customExceptionCode;
    }
    
    private static String formatMessage(String errorMessage, Object... args) {
        if (args == null || args.length == 0) {
            return removePlaceholders(errorMessage).trim();
        } else {
            return MessageFormat.format(errorMessage, (Object[]) args);
        }
    }
    
    private static String removePlaceholders(String message) {
        Pattern pattern = Pattern.compile("\\{\\d+\\}");
        Matcher matcher = pattern.matcher(message);
        return matcher.replaceAll("");
    }
}
