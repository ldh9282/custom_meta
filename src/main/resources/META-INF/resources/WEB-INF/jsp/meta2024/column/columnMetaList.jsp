<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>컬럼 조회 | 메타관리시스템</title>
	
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
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">컬럼 조회</h5>
							<form id="defaultForm">
								<p>
								<input type="hidden" id="thePageNum" name="pageNum" value="${pagingCreator.pageNum}">
								<select id="theRowAmountPerPage" name="rowAmountPerPage">
									<option value="10" ${pagingCreator.rowAmountPerPage == '10' ? 'selected' : ''}>10개</option>
									<option value="20" ${pagingCreator.rowAmountPerPage == '20' ? 'selected' : ''}>20개</option>
									<option value="50" ${pagingCreator.rowAmountPerPage == '50' ? 'selected' : ''}>50개</option>
									<option value="100" ${pagingCreator.rowAmountPerPage == '100' ? 'selected' : ''}>100개</option>
								</select>
								</p>
								<input type="hidden" id="theColumnMetaSno" name="columnMetaSno" value="${requestMap.columnMetaSno}">
								<input type="hidden" id="theColumnName" name="columnName" value="${requestMap.columnName}">
								<input type="hidden" id="theColumnCamelName" name=columnCamelName value="${requestMap.columnCamelName}">
								<input type="hidden" id="theColumnSnakeName" name="columnSnakeName" value="${requestMap.columnSnakeName}">
								<input type="hidden" id="theschemaName" name="schemaName" value="${requestMap.schemaName}">
								<input type="hidden" id="theTableName" name="tableName" value="${requestMap.tableName}">
								<input type="hidden" id="theTableDesc" name="tableDesc" value="${requestMap.tableDesc}">
							</form>
							<form id="searchForm">
							<input type="hidden" id="pageNum" name="pageNum" value="1">
							<input type="hidden" id="rowAmountPerPage" name="rowAmountPerPage" value="${pagingCreator.rowAmountPerPage}">
							<div class="row">
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">컬럼메타일련번호</span>
									    <input type="text" class="form-control" id="columnMetaSno" name="columnMetaSno" value="${requestMap.columnMetaSno}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">컬럼명</span>
									    <input type="text" class="form-control" id="columnName" name="columnName" value="${requestMap.columnName}">
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">컬럼카멜명</span>
									    <input type="text" class="form-control" id="columnCamelName" name=columnCamelName value="${requestMap.columnCamelName}">
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">컬럼스네이크명</span>
									    <input type="text" class="form-control" id="columnSnakeName" name="columnSnakeName" value="${requestMap.columnSnakeName}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">스키마명</span>
									    <input type="text" class="form-control" id="schemaName" name="schemaName" value="${requestMap.schemaName}" oninput="this.value = this.value.toUpperCase()">
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">테이블명</span>
									    <input type="text" class="form-control" id="tableName" name="tableName" value="${requestMap.tableName}" oninput="this.value = this.value.toUpperCase()">
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">테이블설명</span>
									    <input type="text" class="form-control" id="tableDesc" name="tableDesc" value="${requestMap.tableDesc}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="text-end">
									    <button type="button" class="btn btn-primary" id="btnKeywordSearch">검색</button>
									</div>
								</div>
							</div>
							</form> <!-- // END searchForm  -->
							<table class="table mt-3">
								<colgroup>
									<col style="width: 15%;"/>
									<col style="width: 25%;"/>
									<col style="width: 15%;"/>
									<col style="width: 25%;"/>
									<col style="width: auto;"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col" class="text-center">컬럼메타일련번호</th>
										<th scope="col" class="text-center">컬럼명</th>
										<th scope="col" class="text-center">스키마명</th>
										<th scope="col" class="text-center">테이블명</th>
										<th scope="col" class="text-center">테이블설명</th>
									</tr>
								</thead>
		
								<tbody id="tbody">
									<c:forEach var="columnMetaInfo" varStatus="columnMetaInfoStatus" items="${columnMetaInfoList}">
										<tr>
											<td class="rownum text-center" >
												<input type="hidden" name="tableMetaSno" value="${columnMetaInfo.tableMetaSno}">
												<input type="hidden" name="columnMetaSno" value="${columnMetaInfo.columnMetaSno}">
												<%-- 
												<c:out value="${(param.pageNum - 1) * param.rowAmountPerPage + columnMetaInfoStatus.index+1}" />
												--%>
												<c:out value="${columnMetaInfo.columnMetaSno}" />
											</td>
											<td class="text-center">
												<c:out value="${columnMetaInfo.columnName}" />
											</td>
											<td class="text-center">
												<c:out value="${columnMetaInfo.schemaName}" />
											</td>
											<td class="text-center">
												<c:out value="${columnMetaInfo.tableName}" />
											</td>
											<td class="text-center">
												<c:out value="${columnMetaInfo.tableDesc}" />
											</td>
										</tr>
	
									</c:forEach>	
								</tbody>	
							</table>
						</div>
					</div>
		
		
		
				</div>
			</div>
			<!-- ======= pagingCreator ======= -->
			<jsp:include page="/WEB-INF/jsp/cmmn/pagingCreator.jsp"></jsp:include>
			<!-- End pagingCreator-->
			</div>
		</section>

    </main><!-- End #main -->

   
	
	<script>
	

		$(document).ready(function() {
			
			$("#tbody tr .rownum").click(function() {
				let tableMetaSno = $(this).find('input[name=tableMetaSno]').val();
				let columnMetaSno = $(this).find('input[name=columnMetaSno]').val();
				gotoURL('METCU02?tableMetaSno=' + tableMetaSno + '&columnMetaSno=' + columnMetaSno);
		   });


			$("#theRowAmountPerPage").change(function() {
				
				$("#thePageNum").val(1)
				$('#defaultForm').attr('method', 'POST')
				formSubmit($('#defaultForm'), 'METCU01');
			})


			// 엔터입력 이벤트
			$(document).keydown(function(e) {
				
		        if (e.keyCode === 13) { // Enter 키의 keyCode는 13입니다.
		            $("#btnKeywordSearch").trigger('click');
		        }
		    });
			
			// 클릭 이벤트
			$("#btnKeywordSearch").click(function() {
				$("#pageNum").val(1)
				$('#searchForm').attr('method', 'POST')
				formSubmit($('#searchForm'), 'METCU01');
			})
			
			

		});
		
		// 페이징 처리
		function goToPaging(pageNum) {
			$("#thePageNum").val(pageNum)
			$('#defaultForm').attr('method', 'POST')
			formSubmit($('#defaultForm'), 'METCU01');
		}
	</script>


	<jsp:include page="/WEB-INF/jsp/cmmn/scriptBody.jsp"></jsp:include>

	 <!-- ======= Footer ======= -->
    <jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
	
</body>

</html>