<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>테이블 생성 | 메타관리시스템</title>
	
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
				        <h5 class="card-title">테이블 생성</h5>
				        <div class="row g-3">
				            <div class="col-md-12">
				            	<div class="col-md-4">
					                <div class="form-floating mb-3">
					                    <select class="form-control" id="schemaName" name="schemaName">
					                    <option value=""></option>
									    <c:forEach var="item" items="${schemaNameInfo.list}">
									    	<option value="${item.schemaName}">${item.schemaName}</option>
									    </c:forEach>
										</select>
					                    <label for="schemaName">스키마명</label>
					                </div>
				                </div>
				            </div>
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="tableName" name="tableName" oninput="this.value = this.value.toUpperCase()">
				                    <label for="tableName">테이블명</label>
				                </div>
				            </div>
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="tableDesc" name="tableDesc">
				                    <label for="tableDesc">테이블설명</label>
				                </div>
				            </div>
				            
				             <!-- 버튼 추가 -->
				            <div class="row">
				                <div class="col-md-12">
				                    <button type="button" class="btn btn-primary mt-3 mb-3" id="btnAddPkColumn">+ PK 컬럼 추가</button>
					             	<div id="pkColumnInputs"></div>
				                </div>
				            </div>
				            
				             <!-- 버튼 추가 -->
				            <div class="row">
				                <div class="col-md-12">
				                    <button type="button" class="btn btn-primary mt-3 mb-3" id="btnAddColumn">+ 컬럼 추가</button>
					             	<div id="columnInputs"></div>
				                </div>
				            </div>
				            
				            <div class="row">
					            <div class="text-end">
							        <button type="button" class="btn btn-primary" id="btnRegister">등록</button>
							        <button type="button" class="btn btn-secondary mx-3" id="btnList" onclick="gotoURL('METTB01')">목록</button>
					            </div>
				            </div>
				            
				        </div>
				        
				    </div>
				</div>
			</div>
			
        </section>

    </main><!-- End #main -->
	
	
	
	<script>
	
		$(document).ready(function () {
			// + 버튼을 클릭했을 때 컬럼 입력 필드를 추가
		    $('#btnAddPkColumn').click(function() {
		    	var html = '<div class="row g-3 pkColumnInput">';
		    	html    += '	<div class="text-end">'
		    	html    += '		<i class="bi bi-search btnSearchColumn"></i>'
		    	html    += '		<i class="bi bi-dash-square btnRemoveColumn"></i>'
	    		html    += '	</div>'
		    	html    += '	<div class="col-md-4">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnName" name="column" oninput="this.value = this.value.toUpperCase()">'
	    		html    += '			<label>PK 컬럼명</label>'
    			html    += '		</div>'
    			html    += '	</div>'
   				html    += '	<div class="col-md-4">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnCamelName" name="columnCamelName">'
	    		html    += '			<label>PK 컬럼카멜명</label>'
    			html    += '		</div>'
   				html    += '	</div>'
 				html    += '	<div class="col-md-4">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnSnakeName" name="columnSnakeName" readonly>'
	    		html    += '			<label>PK 컬럼스네이크명</label>'
    			html    += '		</div>'
   				html    += '	</div>'
    			html    += '</div>'
		    	
		        $('#pkColumnInputs').append(html);
		    });
			// + 버튼을 클릭했을 때 컬럼 입력 필드를 추가
		    $('#btnAddColumn').click(function() {
		    	var html = '<div class="row g-3 columnInput">';
		    	html    += '	<div class="text-end">'
		    	html    += '		<i class="bi bi-search btnSearchColumn"></i>'
		    	html    += '		<i class="bi bi-dash-square btnRemoveColumn"></i>'
	    		html    += '	</div>'
		    	html    += '	<div class="col-md-4">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnName" name="column" oninput="this.value = this.value.toUpperCase()">'
	    		html    += '			<label>컬럼명</label>'
    			html    += '		</div>'
    			html    += '	</div>'
		    	html    += '	<div class="col-md-4">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnCamelName" name="columnCamelName">'
	    		html    += '			<label>컬럼카멜명</label>'
    			html    += '		</div>'
    			html    += '	</div>'
		    	html    += '	<div class="col-md-4">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnSnakeName" name="columnSnakeName" readonly>'
	    		html    += '			<label>컬럼스네이크명</label>'
    			html    += '		</div>'
    			html    += '	</div>'
    			html    += '</div>'
		    	
		        $('#columnInputs').append(html);
		    });
			
		    // x 버튼을 클릭했을 때 해당 컬럼 입력 필드를 제거
		    $(document).on('click', '.btnRemoveColumn', function() {
		        $(this).closest('.pkColumnInput, .columnInput').remove();
		    });
		    // 검색 버튼을 클릭했을 때 해당 컬럼 입력 필드를 검색
		    $(document).on('click', '.btnSearchColumn', function() {
		    	alertUtils.showAlert('검색 미구현');
		    });
		    // 컬럼카멜명 입력시 컬럼스네이크명 자동입력 
		    $(document).on('input', '.columnCamelName', function() {
		    	$(this).val($(this).val().charAt(0).toLowerCase() + $(this).val().slice(1))
		    	$(this).closest('.pkColumnInput, .columnInput').find('.columnSnakeName').val(camel2Snake($(this).val()))
		    	
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
		    	
		    	// 테이블 pk 컬럼 목록
		    	var thePkColumnList = [];
		    	$('div.pkColumnInput').each(function() {
		    		var item = {
		    			columnName: $(this).find('.columnName').val()
		    			, columnCamelName: $(this).find('.columnCamelName').val()
		    			, columnSnakeName: $(this).find('.columnSnakeName').val()
		    		}
	    			thePkColumnList.push(item);
		    	});
		    	// 테이블 컬럼 목록
		    	var theColumnList = [];
		    	$('div.columnInput').each(function() {
		    		var item = {
		    			columnName: $(this).find('.columnName').val()
		    			, columnCamelName: $(this).find('.columnCamelName').val()
		    			, columnSnakeName: $(this).find('.columnSnakeName').val()
		    		}
		    		theColumnList.push(item);
		    	});
		    	var requestMap = {
	    			schemaName: $('#schemaName').val()
	    			, tableName: $('#tableName').val()
	    			, tableDesc: $('#tableDesc').val()
	    			, pkColumnList: thePkColumnList
	    			, columnList: theColumnList
		    	};
		    	
		    	ajax('METTB04', requestMap, function(response) {
		    		if (response.header && response.header.status == '0000') {
		    			alertUtils.showAlert('등록되었습니다', function() {
		    				gotoURL('METTB01');
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