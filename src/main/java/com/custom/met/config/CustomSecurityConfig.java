package com.custom.met.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.custom.met.cmmn.constant.URLConstant;
import com.custom.met.cmmn.security.filer.CustomJwtRequestFilter;
import com.custom.met.cmmn.security.utils.JwtUtils;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // enable @PreAuthorize
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter { // spring boot 2 can use WebSecurityConfigurerAdapter, but cannot be used in spring boot 3

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler; 
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private JwtUtils jwtUtils;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
    
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public CustomJwtRequestFilter customJwtRequestFilter() {
        return new CustomJwtRequestFilter(jwtUtils, userDetailsService);
    }
    
	
    private static final String[] whiteList = {
    		URLConstant.STATIC_RESOURCE_PATH
    		, URLConstant.LOGIN_PAGE_URL
    		, URLConstant.LOGIN_PAGE_URL2
    		, URLConstant.LOGIN_PROC_URL2
    		, URLConstant.REGISTER_PAGE_URL
    		, URLConstant.REGISTER_PROC_URL
    		, "/v2/*"
    };
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// csrf disable
		http.csrf().disable();
		
		// authorize request
		http.authorizeRequests()
			.antMatchers(whiteList).permitAll()
			.anyRequest().authenticated();
			
		// jwt filter
		http.addFilterBefore(customJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// formLogin
		http.formLogin()
//			.loginPage(URLConstant.LOGIN_PAGE_URL)
			.loginPage(URLConstant.LOGIN_PAGE_URL2)
			.loginProcessingUrl(URLConstant.LOGIN_PROC_URL)
			.usernameParameter("username")
			.passwordParameter("password")
//			.defaultSuccessUrl(URLConstant.LOGIN_SUCC_URL)
			.successHandler(authenticationSuccessHandler) // CustomAuthenticationSuccessHandler
			.failureHandler(authenticationFailureHandler) // CustomAuthenticationFailureHandler
			.permitAll();
			
		// logout
		http.logout()
			.logoutUrl(URLConstant.LOGOUT_PROC_URL)
			.logoutSuccessUrl(URLConstant.LOGOUT_SUCC_URL)
			.invalidateHttpSession(true)
			.permitAll();
		
		// rememberMe
		http.rememberMe()
			.tokenValiditySeconds(2592000)
			.rememberMeParameter("remember-me")
			.userDetailsService(userDetailsService) // CustomUserDetailsService
			;
//			.tokenRepository(persistentTokenRepository());
			
		// accessDeniedHandler
		http.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler); // CustomAccessDeniedHandler
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService) // CustomUserDetailsService
			.passwordEncoder(passwordEncoder());
	}


}
