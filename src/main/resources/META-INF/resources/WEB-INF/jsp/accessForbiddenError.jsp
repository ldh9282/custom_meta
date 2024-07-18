<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

  	<title>권한 없음 (접속실패) | 메타관리시스템</title>
  	<jsp:include page="/WEB-INF/jsp/cmmn/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/cmmn/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/cmmn/scriptHeader.jsp"></jsp:include>
</head>

<body>

	<main>
		<div class="container">
		
			<section class="section error-404 min-vh-100 d-flex flex-column align-items-center justify-content-center">
				<h1>404</h1>
				<br><br>
				<h2>권한 없음 (접속 실패)</h2>
				<a class="btn" href="${pageContext.request.contextPath}/">홈페이지로 가기</a>
			</section>
		
		</div>
	</main><!-- End #main -->

	<script>
	</script>
  
	<jsp:include page="/WEB-INF/jsp/cmmn/scriptBody.jsp"></jsp:include>

</body>

</html>