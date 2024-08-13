package com.custom.met.cmmn.security.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.custom.met.cmmn.constant.CustomAuthCode;

/**
 * <pre>
 * 클래스명: SecurityUtils
 * 설명: 시큐리티 유틸
 * </pre>
 */
public class SecurityUtils {

	/**
	 * <pre>
	 * 메소드명: isAuthenticated
	 * 설명: 로그인여부 조회
	 * </pre>
	 * @return
	 */
	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null 
		           && authentication.isAuthenticated()
		           && !(authentication.getPrincipal() instanceof String 
		                && "anonymousUser".equals(authentication.getPrincipal()));
	}
	
	/**
	 * <pre>
	 * 메소드명: hasAuthorized
	 * 설명: 권한여부 조회
	 * </pre>
	 * @param customAuthCode
	 * @return
	 */
	public static boolean hasAuthorized(CustomAuthCode customAuthCode) {
		List<String> authList = SecurityUtils.getAuthorities();
		
		boolean isAuthorized = false;
		
		for (String auth : authList) {
			if (auth.equals(customAuthCode.getCodeName())) {
				isAuthorized = true;
			}
		}
		
		return isAuthorized;
	}
	
	/**
	 * <pre>
	 * 메소드명: getUsername
	 * 설명: 로그인 아이디 조회
	 * </pre>
	 * @return
	 */
    public static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }

        return null;
    }
    
    /**
     * <pre>
     * 메소드명: getAuthorities
     * 설명: 권한 목록조회
     * </pre>
     * @return
     */
    public static List<String> getAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorities = new ArrayList<>();

        if (authentication != null && authentication.isAuthenticated()) {
            authentication.getAuthorities().forEach(authority -> authorities.add(authority.getAuthority()));
        }

        return authorities;
    }
}
