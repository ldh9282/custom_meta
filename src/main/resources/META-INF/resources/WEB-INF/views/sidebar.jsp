<%@page import="com.custom.met.cmmn.security.utils.SecurityUtils"%>
<%@page import="com.custom.met.cmmn.utils.StringUtils"%>
<%@page import="org.slf4j.MDC"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<%

	String url = StringUtils.NVL(MDC.get("identifier"), "");

%>
	<aside id="sidebar" class="sidebar">
		<c:set var="url" value="<%= url %>" />
	
		<ul class="sidebar-nav" id="sidebar-nav">
			
			<li class="nav-heading">
				<timer-element name="<%= SecurityUtils.getUsername() %>"></timer-element>
				<br><br>
				스키마
			</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METTB02' ? '' : 'collapsed'}" href="#none" onclick="gotoURL('METTB02');">
					<i class="bi bi-grid"></i>
					<span>소유자생성</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METTB02' ? '' : 'collapsed'}" href="#none" onclick="gotoURL('METTB02');">
					<i class="bi bi-grid"></i>
					<span>테이블생성</span>
				</a>
			</li>
			<li class="nav-heading">DB오브젝트관리</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METTB01' ? '' : 'collapsed'}" href="#none" onclick="gotoURL('METTB01');">
					<i class="bi bi-grid"></i>
					<span>테이블조회</span>
				</a>
				<a class="nav-link ${url == 'METCU01' ? '' : 'collapsed'}" href="#none" onclick="gotoURL('METCU01');">
					<i class="bi bi-grid"></i>
					<span>컬럼조회</span>
				</a>
				<a class="nav-link ${url == 'METSE01' ? '' : 'collapsed'}" href="#none" onclick="gotoURL('METSE01');">
					<i class="bi bi-grid"></i>
					<span>시퀀스조회</span>
				</a>
			</li>
			<li class="nav-heading">엑셀유틸</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'METCE01' ? '' : 'collapsed'}" href="#none" onclick="gotoURL('METCE01');">
					<i class="bi bi-grid"></i>
					<span>컬럼영문명</span>
				</a>
				<a class="nav-link ${url == 'METUT01' ? '' : 'collapsed'}" href="#none" onclick="gotoURL('METUT01');">
					<i class="bi bi-grid"></i>
					<span>sql2ExcelConverter</span>
				</a>
				<a class="nav-link ${url == 'METUT02' ? '' : 'collapsed'}" href="#none" onclick="gotoURL('METUT02');">
					<i class="bi bi-grid"></i>
					<span>camel2Snake</span>
				</a>
			</li>
		</ul>
	</aside>
	