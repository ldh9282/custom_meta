package com.custom.met.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.custom.met.cmmn.interceptor.CustomSqlLoggingInterceptor;

@Configuration
public class CustomMybatisConfig {

	@Bean
	public CustomSqlLoggingInterceptor customSqlLoggingInterceptor() {
		return new CustomSqlLoggingInterceptor();
	}
	
}
