<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>용어 조회 | 메타관리시스템</title>
	
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
							<h5 class="card-title">용어 조회</h5>
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
								<input type="hidden" id="theDomainSno" name="domainSno" value="${requestMap.domainSno}">
								<input type="hidden" id="theTermSno" name="termSno" value="${requestMap.termSno}">
								<input type="hidden" id="theTermName" name="termName" value="${requestMap.termName}">
								<input type="hidden" id="theTermCamelName" name="termCamelName" value="${requestMap.termCamelName}">
								<input type="hidden" id="theTermSnakeName" name="termSnakeName" value="${requestMap.termSnakeName}">
							</form>
							<form id="searchForm">
							<input type="hidden" id="pageNum" name="pageNum" value="1">
							<input type="hidden" id="rowAmountPerPage" name="rowAmountPerPage" value="${pagingCreator.rowAmountPerPage}">
							<div class="row">
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">도메인일련번호</span>
									    <input type="text" class="form-control" id="domainSno" name="domainSno" value="${requestMap.domainSno}">
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">용어일련번호</span>
									    <input type="text" class="form-control" id="termSno" name="termSno" value="${requestMap.termSno}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">용어명</span>
									    <input type="text" class="form-control" id="termName" name="termName" value="${requestMap.termName}">
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">용어카멜명</span>
									    <input type="text" class="form-control" id="termCamelName" name="termCamelName" value="${requestMap.termCamelName}">
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">용어스네이크명</span>
									    <input type="text" class="form-control" id="termSnakeName" name="termSnakeName" value="${requestMap.termSnakeName}">
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
									<col style="width: 15%;"/>
									<col style="width: 15%;"/>
									<col style="width: 15%;"/>
									<col style="width: 15%;"/>
									<col style="width: 15%;"/>
									<col style="width: auto;"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col" class="text-center">용어일련번호</th>
										<th scope="col" class="text-center">용어명</th>
										<th scope="col" class="text-center">용어카멜명</th>
										<th scope="col" class="text-center">용어스네이크명</th>
										<th scope="col" class="text-center">도메인명</th>
										<th scope="col" class="text-center">도메인타입</th>
										<th scope="col" class="text-center">용어삭제</th>
									</tr>
								</thead>
		
								<tbody id="tbody">
									<c:forEach var="termInfo" varStatus="termInfoStatus" items="${termInfoList}">
										<tr>
											<td class="rownum text-center" >
												<input type="hidden" name="domainSno" value="${termInfo.domainSno}">
												<input type="hidden" name="termSno" value="${termInfo.termSno}">
												<%-- 
												<c:out value="${(param.pageNum - 1) * param.rowAmountPerPage + seqMetaInfoStatus.index+1}" />
												--%>
												<c:out value="${termInfo.termSno}" />
											</td>
											<td class="text-center">
												<c:out value="${termInfo.termName}" />
											</td>
											<td class="text-center">
												<c:out value="${termInfo.termCamelName}" />
											</td>
											<td class="text-center">
												<c:out value="${termInfo.termSnakeName}" />
											</td>
											<td class="text-center">
												<c:out value="${termInfo.domainName}" />
											</td>
											<td class="text-center">
												<c:out value="${termInfo.domainType}" />
											</td>
											<td class="text-center">
												<button type="button" class="btn btn-danger" onclick="deleteTerm('${termInfo.domainSno}', '${termInfo.termSno}')">삭제</button>
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
			

			$("#theRowAmountPerPage").change(function() {
				
				$("#thePageNum").val(1)
				$('#defaultForm').attr('method', 'POST')
				formSubmit($('#defaultForm'), 'METTM03');
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
				formSubmit($('#searchForm'), 'METTM03');
			})
			
			

		});
		
		// 페이징 처리
		function goToPaging(pageNum) {
			$("#thePageNum").val(pageNum)
			$('#defaultForm').attr('method', 'POST')
			formSubmit($('#defaultForm'), 'METTM03');
		}
		
		// 용어 삭제처리
		function deleteTerm(theDomainSno, theTermSno) {
			alertUtils.showConfirm('삭제하시겠습니까?', function() {
				var requestMap = {
					domainSno: theDomainSno
					, termSno: theTermSno
		    	};
		    	
		    	ajax('METTM05', requestMap, function(response) {
		    		if (response.header && response.header.status == '0000') {
		    			alertUtils.showAlert('삭제되었습니다', function() {
		    				gotoURL('METTM03');
		    			});
		    		} else {
		    			alertUtils.showAlert(response.header.errorMsg);
		    		}
		    	});
				
			})
		}
	</script>


	<jsp:include page="/WEB-INF/jsp/cmmn/scriptBody.jsp"></jsp:include>

	 <!-- ======= Footer ======= -->
    <jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
	
</body>

</html>