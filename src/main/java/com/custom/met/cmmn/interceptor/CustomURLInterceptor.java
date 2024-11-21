package com.custom.met.cmmn.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: CustomURLInterceptor
 * 설명: URL을 로깅 식별자로 사용하기 위한 인터셉터
 * </pre>
 */
@Log4j2
public class CustomURLInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		
		
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		String method = request.getMethod();
		
		String ip = request.getRemoteAddr().toString();
		String session = request.getSession().getId();
		
		
		if (url.indexOf(request.getContextPath() + "/resources") == -1 && handler instanceof HandlerMethod) {
			
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
				String requestUrl = theRequestUrl + queryString;
				request.setAttribute("requestUrl", requestUrl);
				String requestMethod = method;
				request.setAttribute("requestMethod", requestMethod);
				
				String identifier = theRequestUrl.length() < 12 ? theRequestUrl : theRequestUrl.substring(0, 11);
				MDC.put("identifier", identifier);
				
				if (log.isDebugEnabled()) { log.debug(">>> Start Controller ::: " + className + "." + methodName); } 
//				log.debug("remote ip" + " ::: " + ip + " ::: " + "session" + " ::: " + session); 
				
				Enumeration<String> parameterNames = request.getParameterNames();
				while (parameterNames.hasMoreElements()) {
					String paramName = parameterNames.nextElement();
//					log.debug(">>> param ::: " + paramName + " ::: " + request.getParameter(paramName));
				}
				
			} catch (Exception e) {
				String requestUrl = (String) request.getAttribute("requestUrl");
				if (log.isDebugEnabled()) { log.debug(">>> Exception ::: " + e.getMessage()); }
				if (log.isDebugEnabled()) { log.debug(">>> error request ::: url ::: " + requestUrl); }
			}
		}
		
		
		return true;
		
	}
	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String viewName;
		
        if (modelAndView != null) {
        	viewName = modelAndView.getViewName();
        } else {
        	viewName = null;
        }
        
        request.setAttribute("viewName", viewName);
	}



	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		String url = request.getRequestURL().toString();
		String ip = request.getRemoteAddr().toString();
		String session = request.getSession().getId();
		
		long startTime = (long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		if (url.indexOf(request.getContextPath() + "/resources") == -1 && handler instanceof HandlerMethod) {
			
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
				String viewName = (String) request.getAttribute("viewName");
				if (viewName != null) {
					if (log.isDebugEnabled()) { log.debug("<<< viewName  ::: " + viewName); }
				}
				
				if (log.isDebugEnabled()) { log.debug("<<< execution time  ::: " + (endTime - startTime) + "ms"); }
				if (log.isDebugEnabled()) { log.debug("<<< End Controller ::: " + className + "." + methodName); }
				
			} catch (Exception e) {
				String requestUrl = (String) request.getAttribute("requestUrl");
				if (log.isDebugEnabled()) { log.debug("<<< Exception ::: " + e.getMessage()); }
				if (log.isDebugEnabled()) { log.debug("<<< error request ::: url ::: " + requestUrl); }
			}
		}
	}
	


}