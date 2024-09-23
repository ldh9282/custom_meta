<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>시퀀스 조회 | 메타관리시스템</title>
	
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
    	<section class="section">
    		<div class="container mt-5">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">시퀀스 조회</h5>
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
								<input type="hidden" id="theSeqMetaSno" name="seqMetaSno" value="${requestMap.seqMetaSno}">
								<input type="hidden" id="theSeqName" name="seqName" value="${requestMap.seqName}">
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
									    <span class="input-group-text">시퀀스메타일련번호</span>
									    <input type="text" class="form-control" id="seqMetaSno" name="seqMetaSno" value="${requestMap.seqMetaSno}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">시퀀스명</span>
									    <input type="text" class="form-control" id="seqName" name="seqName" value="${requestMap.seqName}">
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
									    <button type="button" class="btn btn-sm btn-primary" id="btnKeywordSearch">검색</button>
									</div>
								</div>
							</div>
							</form> <!-- // END searchForm  -->
							<table class="table mt-3">
								<colgroup>
									<col style="width: 15%;"/>
									<col style="width: 25%;"/>
									<col style="width: 15%;"/>
									<col style="width: 20%;"/>
									<col style="width: auto;"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col" class="text-center">시퀀스메타일련번호</th>
										<th scope="col" class="text-center">시퀀스명</th>
										<th scope="col" class="text-center">스키마명</th>
										<th scope="col" class="text-center">테이블명</th>
										<th scope="col" class="text-center">테이블설명</th>
									</tr>
								</thead>
		
								<tbody id="tbody">
									<c:forEach var="seqMetaInfo" varStatus="seqMetaInfoStatus" items="${seqMetaInfoList}">
										<tr>
											<td class="text-center" >
												<c:out value="${seqMetaInfo.seqMetaSno}" />
											</td>
											<td class="text-center">
												<c:out value="${seqMetaInfo.seqName}" />
											</td>
											<td class="text-center">
												<c:out value="${seqMetaInfo.schemaName}" />
											</td>
											<td class="text-center">
												<c:out value="${seqMetaInfo.tableName}" />
											</td>
											<td class="text-center">
												<c:out value="${seqMetaInfo.tableDesc}" />
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
			<jsp:include page="/WEB-INF/jsp/include/pagingCreator.jsp"></jsp:include>
			<!-- End pagingCreator-->
			</div>
		</section>

    </main><!-- End #main -->

   
	
	<script>
	

		$(document).ready(function() {
			
			$("#theRowAmountPerPage").change(function() {
				
				$("#thePageNum").val(1)
				$('#defaultForm').attr('method', 'POST')
				formSubmit($('#defaultForm'), 'METSE01');
			})

			
			// 클릭 이벤트
			$("#btnKeywordSearch").click(function() {
				$("#pageNum").val(1)
				$('#searchForm').attr('method', 'POST')
				formSubmit($('#searchForm'), 'METSE01');
			})
			
			

		});
		
		// 페이징 처리
		function goToPaging(pageNum) {
			$("#thePageNum").val(pageNum)
			$('#defaultForm').attr('method', 'POST')
			formSubmit($('#defaultForm'), 'METSE01');
		}
	</script>


	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>

	 <!-- ======= Footer ======= -->
    <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	<!-- End Footer -->
	
</body>

</html>