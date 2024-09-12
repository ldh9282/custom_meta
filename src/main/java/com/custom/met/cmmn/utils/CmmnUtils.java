package com.custom.met.cmmn.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.custom.met.cmmn.model.CustomMap;

@Component
public class CmmnUtils {

	private static CustomMap initMap;
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${aes-key}")
	private String aesKey;
	
	
	/**
	 * <pre>
	 * 메서드명: init
	 * 설명: static 으로 인스턴스 변수 가져오기 위한 세팅
	 * </pre>
	 */
	@PostConstruct
	private void init() {
		initMap = new CustomMap();
		initMap.put("contextPath", contextPath);
		initMap.put("serverPort", serverPort);
		initMap.put("aesKey", aesKey);
	}
	
	/**
	 * <pre>
	 * 메서드명: getContextPath
	 * 설명: contextPath 반환
	 * </pre>
	 * @return
	 */
	public static String getContextPath() {
		return initMap.getString("contextPath");
	}
	
	/**
	 * <pre>
	 * 메서드명: getServerPort
	 * 설명: 서버 포트번호 반환
	 * </pre>
	 * @return
	 */
	public static String getServerPort() {
		return initMap.getString("serverPort");
	}
	
	/**
	 * <pre>
	 * 메서드명: getAesKey
	 * 설명: AES 양방향 암호화키 반환
	 * </pre>
	 * @return
	 */
	public static String getAesKey() {
		return initMap.getString("aesKey");
	}
	
	/**
	 * <pre>
	 * 메서드명: getServerIP
	 * 설명: 서버아이피 반환
	 * </pre>
	 * @return
	 */
	public static String getServerIP() {
		String serverIP = "";
		
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			serverIP = localhost.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		return serverIP; 
	}
	
}
