<%@page import="com.custom.met.cmmn.security.utils.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<%
	if (SecurityUtils.isAuthenticated()) {
		response.sendRedirect("/METTB02");
	} else {
		response.sendRedirect("/METLG04");
	}
%>
</body>
</html>