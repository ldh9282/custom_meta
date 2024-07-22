<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <%
    	
    	response.setHeader("Pragma", "no-cache");
    	response.setDateHeader("Expires", 0);
    	if ("HTTP/1.1".equals(request.getProtocol())) {
    		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    	}
    %>