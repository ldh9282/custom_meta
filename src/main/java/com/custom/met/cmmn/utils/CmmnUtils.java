package com.custom.met.cmmn.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

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
	
	@Value("${spring.profiles.active:default}")
    private String activeProfile;
	
	
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
		initMap.put("activeProfile", activeProfile);
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
	 * 메서드명: getActiveProfile
	 * 설명: 프로필 반환
	 * </pre>
	 * @return
	 */
	public static String getActiveProfile() {
		return initMap.getString("activeProfile").toLowerCase();
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
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					if (!StringUtils.NVL(networkInterface.toString(), "").contains("Virtual Ethernet Adapter")) {
						if (!inetAddress.isLoopbackAddress() && inetAddress instanceof java.net.Inet4Address) {
							serverIP = inetAddress.getHostAddress();
						}
					}
				}
			}
		} catch (UnknownHostException e) {
			serverIP = "";
		} catch (SocketException e) {
			serverIP = "";
		}
		
		
		return serverIP; 
	}
	
	/**
	 * <pre>
	 * 메서드명: isLocal
	 * 설명: 프로필 로컬서버 유무
	 * </pre>
	 * @return
	 */
	public static boolean isLocal() {
		boolean result = false;
		
		if ("default".equals(initMap.getString("activeProfile").toLowerCase())) {
			result = true;
		}
		return result;
	}
	
	/**
	 * <pre>
	 * 메서드명: isDev
	 * 설명: 프로필 개발서버 유무
	 * </pre>
	 * @return
	 */
	public static boolean isDev() {
		boolean result = false;
		
		if ("dev".equals(initMap.getString("activeProfile").toLowerCase())) {
			result = true;
		}
		return result;
	}
	
	/**
	 * <pre>
	 * 메서드명: isProd
	 * 설명: 프로필 운영서버 유무
	 * </pre>
	 * @return
	 */
	public static boolean isProd() {
		boolean result = false;
		
		if ("prod".equals(initMap.getString("activeProfile").toLowerCase())) {
			result = true;
		}
		return result;
	}
}
