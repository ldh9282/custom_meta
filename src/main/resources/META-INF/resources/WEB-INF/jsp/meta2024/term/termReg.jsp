<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>용어 생성 | 메타관리시스템</title>
	
	<jsp:include page="/WEB-INF/jsp/cmmn/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/cmmn/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/cmmn/scriptHeader.jsp"></jsp:include>
</head>

<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<!-- End Header -->
	
	<!-- ======= Sidebar ======= -->
	<jsp:include page="/WEB-INF/jsp/sidebar.jsp"></jsp:include>
	<!-- End Sidebar-->
	
	<main id="main" class="main">
        <section class="section">
            <div class="container mt-5">
            	<div class="card">
				    <div class="card-body">
				        <h5 class="card-title">용어 생성</h5>
				        <div class="row g-3 mb-5">
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <select id="domainSno" name="domainSno" class="form-select">
				                    	<c:forEach var="domainScInfo" items="${domainScInfoList}">
				                    	<option value="${domainScInfo.domainSno}">${domainScInfo.domainName}</option>
				                    	</c:forEach>
				                    </select>
				                    <label for="domainSno">도메인명</label>
				                </div>
				            </div>
				        </div>
				        <div class="row g-3 mb-5">
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="termName" name="termName">
				                    <label for="termName">용어명</label>
				                </div>
				            </div>
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="termCamelName" name="termCamelName">
				                    <label for="termCamelName">용어카멜명</label>
				                </div>
				            </div>
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="termSnakeName" name="termSnakeName" readonly>
				                    <label for="termSnakeName">용어스네이크명</label>
				                </div>
				            </div>
				        </div>
			            <div class="row">
				            <div class="text-end">
						        <button type="button" class="btn btn-primary" id="btnRegister">등록</button>
						        <button type="button" class="btn btn-secondary mx-3" id="btnList" onclick="gotoURL('METDM03')">목록</button>
				            </div>
			            </div>
				        
				    </div>
				</div>
			</div>
			
        </section>

    </main><!-- End #main -->
	
	
	
	<script>
	
		$(document).ready(function () {
			// 컬럼카멜명 입력시 컬럼스네이크명 자동입력 
		    $(document).on('input', '#termCamelName', function() {
		    	$(this).val($(this).val().charAt(0).toLowerCase() + $(this).val().slice(1))
		    	$("#termSnakeName").val(camel2Snake($(this).val()))
		    	
		    });
			
		    // 등록버튼
		    $('#btnRegister').click(function() {
		    	
		    	
		    	// 필수값 입력체크
		    	var fieldList = [];
		    	$('input.form-control, select.form-control').each(function() {
		    		var fieldLabel = $(this).siblings('label').text();
		    		var fieldValue = $(this).val();
		    		var fieldObj = { label: fieldLabel, value: $(this).val() };
		    		fieldList.push(fieldObj);
		    	});
		    	if (!alertUtils.checkRequiredFields(fieldList)) {
		    		return;
		    	}
		    	
		    	var requestMap = {
		    		domainSno: $('#domainSno').val()
		    		, termName: $('#termName').val()
		    		, termCamelName: $('#termCamelName').val()
		    		, termSnakeName: $('#termSnakeName').val()
		    	};
		    	
		    	ajax('METTM02', requestMap, function(response) {
		    		if (response.header && response.header.status == '0000') {
		    			alertUtils.showAlert('등록되었습니다', function() {
		    				gotoURL('METTM03');
		    			});
		    		} else {
		    			alertUtils.showAlert(response.header.errorMsg);
		    		}
		    	});
		    	
		    });
		});
	
	
	
	</script>

	<jsp:include page="/WEB-INF/jsp/cmmn/scriptBody.jsp"></jsp:include>
	
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>

</html>