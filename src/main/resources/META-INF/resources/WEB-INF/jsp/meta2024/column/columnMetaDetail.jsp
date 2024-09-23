<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>${columnMetaInfo.columnName} | 메타관리시스템</title>
	
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
        <section>
            <div class="container">
            	<div class="card">
				    <div class="card-body">
				    	
				        <h5 class="card-title">컬럼수정</h5>
						
				        <form class="row g-3" id="frm" method="post">
				        	<input type="hidden" name="tableMetaSno" value="${columnMetaInfo.tableMetaSno}">
							<input type="hidden" name="columnMetaSno" value="${columnMetaInfo.columnMetaSno}">
				        	<div class="col-md-12 mb-3">
				            <div class="col-md-4">
			                    <div class="form-floating">
			                        <input type="text" class="form-control" id="columnName" name="columnName" value="${columnMetaInfo.columnName}">
			                        <label for="columnName">컬럼명</label>
			                    </div>
				            </div>
				            </div>
			            	<div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="schemaName" name="schemaName" value="${columnMetaInfo.schemaName}" readonly>
				                    <label for="schemaName">스키마명</label>
				                </div>
			                </div>
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="tableName" name="tableName" value="${columnMetaInfo.tableName}" readonly>
				                    <label for="tableName">테이블명</label>
				                </div>
				            </div>
				            <div class="col-md-4">
			                    <div class="form-floating">
			                        <input type="text" class="form-control" id="tableDesc" name="tableDesc" value="${columnMetaInfo.tableDesc}" readonly>
			                        <label for="tableDesc">테이블설명</label>
			                    </div>
				            </div>
				            <div class="col-md-4">
			                    <div class="form-floating">
			                        <input type="text" class="form-control" id="columnCamelName" name="columnCamelName" value="${columnMetaInfo.columnCamelName}">
			                        <label for="columnCamelName">컬럼카멜명</label>
			                    </div>
				            </div>
				            <div class="col-md-4">
			                    <div class="form-floating">
			                        <input type="text" class="form-control" id="columnSnakeName" name="columnSnakeName" value="${columnMetaInfo.columnSnakeName}">
			                        <label for="columnSnakeName">컬럼스네이크명</label>
			                    </div>
				            </div>
			                
			                
				            
				        </form><!-- End floating Labels Form -->
					    <div class="row">
				            <div class="text-end">
						        <button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
						        <button type="button" class="btn btn-sm btn-secondary mx-3" id="btnList" onclick="gotoURL('METCU01')">목록</button>
				            </div>
			            </div>
				
				    </div>
				</div>
            </div>
        </section>

    </main><!-- End #main -->

   
	
	<script>
	

		$(document).ready(function() {
			
			$('#btnUpdate').click(function() {
		    	var fieldList = [];
		    	$('#frm input').each(function() {
		    		var fieldLabel = $(this).siblings('label').text();
		    		var fieldValue = $(this).val();
		    		var fieldObj = { label: fieldLabel, value: $(this).val() };
		    		fieldList.push(fieldObj);
		    	});
		    	if (!alertUtils.checkRequiredFields(fieldList)) {
		    		return;
		    	}
				let params = formToObject('#frm');
				
				ajax('METCU03', params, function(response) {
					if (response.header && response.header.status == '0000') {
						let tableMetaSno = '${columnMetaInfo.tableMetaSno}';
						let columnMetaSno = '${columnMetaInfo.columnMetaSno}';
		    			alertUtils.showAlert('수정되었습니다.', function() {
		    				gotoURL('METCU02?tableMetaSno=' + tableMetaSno + '&columnMetaSno=' + columnMetaSno);
		    			});
		    		} else {
		    			alertUtils.showAlert(response.header.errorMsg);
		    		}
				});
				
				
				
			});

		});
	</script>


	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>

	<!-- ======= Footer ======= -->
    <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	<!-- End Footer -->
	
</body>

</html>