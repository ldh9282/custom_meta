<%@page import="com.custom.met.cmmn.utils.DateUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.custom.met.cmmn.security.utils.SecurityUtils"%>
<%@page import="com.custom.met.cmmn.utils.StringUtils"%>
<%@page import="org.slf4j.MDC"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<%

	String url = StringUtils.NVL(MDC.get("identifier"), "");
	String username = StringUtils.NVL(SecurityUtils.getUsername(), "");
	request.setAttribute("serverTime", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	request.setAttribute("loginTime", StringUtils.NVL((String) session.getAttribute("loginTime"), ""));

%>


	<c:set var="url" value="<%= url %>" />
	<c:set var="username" value="<%= username %>" />
	
	<aside id="sidebar" class="sidebar">
		<div class="card info-card sidebar-card">
			<div class="card-body">
				<div class="card-title">
					<div class="h6 mb-4">${username}님 안녕하세요</div>
					<div class="h6">현재시각 <span class="h6 text-dark"><timer-element></timer-element></span></div>
					<div class="h6">접속시간 <span class="h6 text-dark" id="connectTime"></span></div>
				</div>
				<div onclick="logout();">
			        <i class="bi bi-box-arrow-right"></i>
			        <span class="sidebar-logout">로그아웃</span>
		        </div>
			</div>
		</div>
		<ul class="sidebar-nav" id="sidebar-nav">
			
			<li class="nav-heading">도메인</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METDM01' ? '' : 'collapsed'}" href="javascript:gotoURL('METDM01');">
					<span>도메인생성</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METDM03' ? '' : 'collapsed'}" href="javascript:gotoURL('METDM03');">
					<span>도메인조회</span>
				</a>
			</li>
			<li class="nav-heading">용어</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METTM01' ? '' : 'collapsed'}" href="javascript:gotoURL('METTM01');">
					<span>용어생성</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METTM03' ? '' : 'collapsed'}" href="javascript:gotoURL('METTM03');">
					<span>용어조회</span>
				</a>
			</li>
			<li class="nav-heading">스키마</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METSC02' ? '' : 'collapsed'}" href="javascript:gotoURL('METSC02');">
					<span>스키마생성</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METTB02' ? '' : 'collapsed'}" href="javascript:gotoURL('METTB02');">
					<span>테이블생성</span>
				</a>
			</li>
			<li class="nav-heading">DB오브젝트관리</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METTB01' ? '' : 'collapsed'}" href="javascript:gotoURL('METTB01');">
					<span>테이블조회</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METCU01' ? '' : 'collapsed'}" href="javascript:gotoURL('METCU01');">
					<span>컬럼조회</span>
				</a>
			</li>
			<li class="nav-item">				
				<a class="nav-link ${url == 'METSE01' ? '' : 'collapsed'}" href="javascript:gotoURL('METSE01');">
					<span>시퀀스조회</span>
				</a>
			</li>
			<%--
			<li class="nav-heading">엑셀</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METCE01' ? '' : 'collapsed'}" href="javascript:gotoURL('METCE01');">
					<span>컬럼영문명</span>
				</a>
			</li>
			 --%>
		</ul>
	</aside>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			var currentTime = new Date('${serverTime}').getTime();
			var interval = 1000;
			
			updateConnectTime(currentTime);
			setInterval(function() {
				currentTime += interval;
				updateConnectTime(currentTime)
			}, interval);
		    
		});
		
		const updateConnectTime = function(theCurrentTime) {
			let diffMillis = theCurrentTime - new Date('${loginTime}').getTime();
			let displayTime = timerUtils.getDisplayTime(diffMillis);
			
			$('#connectTime').text(displayTime.hour + ':' + displayTime.min + ':' + displayTime.sec);
		};
	</script>