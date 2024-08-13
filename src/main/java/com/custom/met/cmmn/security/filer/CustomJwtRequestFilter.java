package com.custom.met.cmmn.security.filer;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.utils.JwtUtils;
import com.custom.met.cmmn.security.utils.SecurityUtils;

import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: CustomJwtRequestFilter
 * 설명: JWT 요청필터
 * </pre>
 */
@Log4j2
public class CustomJwtRequestFilter extends OncePerRequestFilter  {

	@Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    public CustomJwtRequestFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        MDC.put("identifier", "Authorization");
        String username = null;
        String jwtToken = null;
        

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            
        	username = jwtUtils.extractUsername(jwtToken);
        }

        if (username != null && !SecurityUtils.isAuthenticated()) {
            if (jwtUtils.validateToken(jwtToken, username)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                CustomMap authMap = new CustomMap();
                authMap.put("username", SecurityUtils.getUsername());
                authMap.put("authList", SecurityUtils.getAuthorities());
                
                if (log.isDebugEnabled()) { log.debug("JWT authMap ::: " + authMap); }
            }
        }
        
        

        chain.doFilter(request, response);
    }
}
