package com.custom.met.cmmn.interceptor;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.utils.DateUtils;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: CustomURLInterceptor
 * 설명: URL을 로깅 식별자로 사용하기 위한 인터셉터
 * </pre>
 */
@Log4j2
public class CustomURLInterceptor implements HandlerInterceptor {

	private long startTime = 0L;
	private long endTime = 0L;
	private String requsetUrl;
	private String requsetMethod;
	private String viewName;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		startTime = System.currentTimeMillis();
		
		
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		String method = request.getMethod();
		
		String ip = request.getRemoteAddr().toString();
		String session = request.getSession().getId();
		
		
		if (log.isDebugEnabled()
				&& url.indexOf(request.getContextPath() + "/resources") == -1
				&& url.indexOf(request.getContextPath() + "/error") == -1
				&& handler instanceof HandlerMethod
				) {
			
			try {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				String className = handlerMethod.getBeanType().getName();
				// 패키지 이름을 제외한 클래스 이름 추출
				int lastIndex = className.lastIndexOf(".");
				if (lastIndex != -1) {
				    className = className.substring(lastIndex + 1);
				}
				String methodName = handlerMethod.getMethod().getName();
				
				String theRequestUrl = request.getRequestURI().substring(1);
				requsetUrl = theRequestUrl + queryString;
				requsetMethod = method;
				
				String identifier = theRequestUrl.length() < 12 ? theRequestUrl : theRequestUrl.substring(0, 11);
				MDC.put("identifier", identifier);
				
				log.debug(">>> Start Controller ::: " + className + "." + methodName); 
//				log.debug("remote ip" + " ::: " + ip + " ::: " + "session" + " ::: " + session); 
				
				Enumeration<String> parameterNames = request.getParameterNames();
				while (parameterNames.hasMoreElements()) {
					String paramName = parameterNames.nextElement();
//					log.debug(">>> param ::: " + paramName + " ::: " + request.getParameter(paramName));
				}
				
			} catch (Exception e) {
				log.debug(">>> Exception ::: " + e.getMessage());
				log.debug(">>> error request ::: url ::: " + requsetUrl);
			}
		}
		
		
		return true;
		
	}
	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
        	viewName = modelAndView.getViewName();
        } else {
        	viewName = null;
        }
	}



	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		String url = request.getRequestURL().toString();
		String ip = request.getRemoteAddr().toString();
		String session = request.getSession().getId();
		
		endTime = System.currentTimeMillis();
		if (log.isDebugEnabled()
				&& url.indexOf(request.getContextPath() + "/resources") == -1
				&& url.indexOf(request.getContextPath() + "/error") == -1
				&& handler instanceof HandlerMethod
				) {
			
			try {
				
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				String className = handlerMethod.getBeanType().getName();
				// 패키지 이름을 제외한 클래스 이름 추출
				int lastIndex = className.lastIndexOf(".");
				if (lastIndex != -1) {
				    className = className.substring(lastIndex + 1);
				}
				String methodName = handlerMethod.getMethod().getName();
				
//				log.debug("remote ip" + " ::: " + ip + " ::: " + "session" + " ::: " + session);
				if (viewName != null) {
					log.debug("<<< viewName  ::: " + viewName);
				}
				log.debug("<<< execution time  ::: " + (endTime - startTime) + "ms");
				log.debug("<<< End Controller ::: " + className + "." + methodName);
				
			} catch (Exception e) {
				log.debug("<<< Exception ::: " + e.getMessage());
				log.debug("<<< error request ::: url ::: " + requsetUrl);
			}
		}
	}
	


}