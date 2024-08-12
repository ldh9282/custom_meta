package com.custom.met;

import com.custom.met.cmmn.exception.CustomException;

public class Test {

	public static void main(String[] args) {
		System.out.println(decryptWithRetry("test") + "|");

	}
	
	public static String decryptWithRetry(String theEncrypted) {
		String resultStr = "";
		
		try {
			String theDecrypted = decrypt1(theEncrypted);
			resultStr = theDecrypted;
		} catch (Exception e) {
			try {
				String theDecrypted = decrypt2(theEncrypted);
				resultStr = theDecrypted;
			} catch (Exception e2) {
				resultStr = "";
			}

		}
		
		return resultStr;
	}
	
	public static String decrypt1(String theEncrypted) throws Exception {
		throw new Exception("");
//		return theEncrypted + "1";
	}
	public static String decrypt2(String theEncrypted) throws Exception {
		throw new Exception("");
//		return theEncrypted + "2";
	}

}
