package com.custom.met.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private DataSource dataSource;
	
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
    
    
    private static final String STATIC_RESOURCE_PATH = "/resources/**";
    private static final String LOGIN_PAGE_URL = "/METLG01";
    private static final String LOGIN_PROC_URL = "/login";
    private static final String LOGIN_SUCC_URL = "/";
    
    private static final String LOGOUT_PROC_URL = "/logout";
    private static final String LOGOUT_SUCC_URL = LOGIN_PAGE_URL;
    
    private static final String REGISTER_PAGE_URL = "/METLG02";
    private static final String REGISTER_PROC_URL = "/METLG03";
	
    private static final String[] whiteList = {
    		STATIC_RESOURCE_PATH
    		, LOGIN_PAGE_URL
    		, REGISTER_PAGE_URL
    		, REGISTER_PROC_URL
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
			
		// formLogin
		http.formLogin()
			.loginPage(LOGIN_PAGE_URL)
			.loginProcessingUrl(LOGIN_PROC_URL)
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl(LOGIN_SUCC_URL)
			.failureHandler(authenticationFailureHandler) // CustomAuthenticationFailureHandler
			.permitAll();
			
		// logout
		http.logout()
			.logoutUrl(LOGOUT_PROC_URL)
			.logoutSuccessUrl(LOGOUT_SUCC_URL)
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
