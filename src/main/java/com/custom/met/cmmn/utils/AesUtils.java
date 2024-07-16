package com.custom.met.cmmn.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/***
 * <pre>
 * 클래스명: AesUtil
 * 설명: AES 양방향 암호화유틸
 * </pre>
 */
public class AesUtils {
	
	private static String ALGORITHM = "AES";
	private static Charset CHARSET = StandardCharsets.UTF_8;
	
	/***
	 * <pre>
	 * 메서드명: createKey
	 * 설명: 암호화 및 복호화에 사용하는 키생성
	 * </pre>
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static SecretKey createKey(String key) throws Exception {
		byte[] keyBytes = key.getBytes(CHARSET);
		byte[] the256bits = new byte[32];
		System.arraycopy(keyBytes, 0, the256bits, 0, Math.min(keyBytes.length, 32));
		
		return new SecretKeySpec(the256bits, ALGORITHM);
	}
	
	/***
	 * <pre>
	 * 메서드명: encrypt
	 * 설명: 키값으로 암호화
	 * </pre>
	 * @param plain
	 * @param key
	 * @return
	 */
	public static String encrypt(String plain, String key) {
		String resultStr = null;
		try {
			SecretKey theKey = createKey(key);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, theKey);
			
			byte[] theEncrypted = cipher.doFinal(plain.getBytes(CHARSET));
			resultStr = Base64.getEncoder().encodeToString(theEncrypted);
		} catch (NoSuchAlgorithmException e) {
			resultStr = "";
		} catch (NoSuchPaddingException e) {
			resultStr = "";
		} catch (IllegalBlockSizeException e) {
			resultStr = "";
		} catch (BadPaddingException e) {
			resultStr = "";
		} catch (InvalidKeyException e) {
			resultStr = "";
		} catch (Exception e) {
			resultStr = "";
		}
		return resultStr;
	}
	
	/***
	 * <pre>
	 * 메서드명: decrypt
	 * 설명: 키값으로 복호화
	 * </pre>
	 * @param theEncrypted
	 * @param key
	 * @return
	 */
	public static String decrypt(String theEncrypted, String key) {
		
		String resultStr = null;
		try {
			SecretKey theKey = createKey(key);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, theKey);
			
			byte[] theDecrypted = cipher.doFinal(Base64.getDecoder().decode(theEncrypted));
			resultStr = new String(theDecrypted, CHARSET);
		} catch (NoSuchAlgorithmException e) {
			resultStr = theEncrypted;
		} catch (NoSuchPaddingException e) {
			resultStr = theEncrypted;
		} catch (IllegalBlockSizeException e) {
			resultStr = theEncrypted;
		} catch (BadPaddingException e) {
			resultStr = theEncrypted;
		} catch (InvalidKeyException e) {
			resultStr = theEncrypted;
		} catch (Exception e) {
			resultStr = theEncrypted;
		}
		return resultStr;
		
	}

}
