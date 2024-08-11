package com.custom.met.cmmn.security.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

	/**
	 * <pre>
	 * 메소드명: isAuthenticated
	 * 설명: 로그인여부 조회
	 * </pre>
	 * @return
	 */
	public static boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication() != null; 
	}
	
	/**
	 * <pre>
	 * 메소드명: getUsername
	 * 설명: 회원명 조회
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
     * 설명: 회원권한 목록조회
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
