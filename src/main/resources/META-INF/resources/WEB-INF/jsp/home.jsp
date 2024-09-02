<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>메인홈페이지 | 메타관리시스템</title>
	
	<jsp:include page="/WEB-INF/jsp/include/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/scriptHeader.jsp"></jsp:include>

</head>

<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
	<!-- End Header -->
	
	<!-- ======= Sidebar ======= -->
	<jsp:include page="/WEB-INF/jsp/include/sidebar.jsp"></jsp:include>
	<!-- End Sidebar-->
	
	<main id="main" class="main">
	
	</main><!-- End #main -->
	
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	
	
	<script>
	
		$(document).ready(function () {
		
		});
	
	
	
	</script>


	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>

</body>

</html>