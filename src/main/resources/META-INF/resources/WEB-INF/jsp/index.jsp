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
	String username = SecurityUtils.getUsername();

	if (username == null) {
		response.sendRedirect("/METLG04");
	} else {
		response.sendRedirect("/METTB02");
	}
%>
</body>
</html>