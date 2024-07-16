package com.custom.met.cmmn.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MetCmmnUtils {

	/***
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
