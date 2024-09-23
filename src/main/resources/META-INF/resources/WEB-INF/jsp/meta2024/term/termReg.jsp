<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>용어 생성 | 메타관리시스템</title>
	
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
            	<div class="card">
				    <div class="card-body">
				        <h5 class="card-title">용어 생성</h5>
				        <div class="row g-3 mb-5">
				            <div class="col-md-4">
						        <div class="text-end">
						    		<i class="bi bi-search" id="btnSearchDomain"></i>
						    		<i class="bx bx-revision" id="btnResetDomain"></i>
					    		</div>
				                <div class="form-floating">
				                	<div class="form-floating">
					                    <input type="hidden" id="domainSno" name="domainSno">
					                    <input type="text" class="form-control" id="domainName" name="domainName" readonly>
					                    <label for="domainName">도메인명</label>
					                </div>
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
						        <button type="button" class="btn btn-sm btn-primary" id="btnRegister">등록</button>
						        <button type="button" class="btn btn-sm btn-secondary mx-3" id="btnList" onclick="gotoURL('METDM03')">목록</button>
				            </div>
			            </div>
				        
				    </div>
				</div>
			</div>
			
        </section>

    </main><!-- End #main -->
	
	<!-- Search Modal -->
    <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="searchModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="searchModalLabel">도메인 검색</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- 검색 입력 -->
                   	<input type="text" class="form-control mb-3" id="searchInput" placeholder="검색어를 입력하세요" oninput="this.value = this.value.toUpperCase().trim()">
                    <!-- 검색 버튼 -->
                    <div class="row">
			            <div class="text-end">
		                    <button type="button" class="btn btn-sm btn-primary mb-3" id="btnSearchResults">검색</button>
			            </div>
		            </div>
                    <!-- 검색 결과 목록 -->
                    <ul class="list-group" id="searchResults">
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-primary" id="btnConfirmSelection">확인</button>
                    <button type="button" class="btn btn-sm btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>
	
	
	<script>
	
		$(document).ready(function () {
			// 컬럼카멜명 입력시 컬럼스네이크명 자동입력 
		    $(document).on('input', '#termCamelName', function() {
		    	$(this).val($(this).val().charAt(0).toLowerCase() + $(this).val().slice(1))
		    	$("#termSnakeName").val(camel2Snake($(this).val()))
		    	
		    });
			
		 	// 리셋 버튼을 클릭했을 때 해당 도메인 제거
		    $('#btnResetDomain').click(function() {
		        $('#domainSno').val('');
		        $('#domainName').val('');
		    });
		    // 검색 버튼을 클릭했을 때 해당 도메인 검색
		    $('#btnSearchDomain').click(function() {
		    	$('#searchModal').modal('show');
		    	$('#searchResults').html('');
		    });
		 	// 확인 버튼 클릭 시
            $('#btnConfirmSelection').click(function() {
                var domainSno = $('#searchResults .list-group-item.selected .domainSno').text().trim();
                var domainName = $('#searchResults .list-group-item.selected .domainName').text().trim();
                $('#domainSno').val(domainSno);
		        $('#domainName').val(domainName);
                
                $('#searchModal').modal('hide');
            });
		    // 검색 로직
		    $('#btnSearchResults').click(function() {
		    	var requestMap = {
		    		domainName: $('#searchInput').val()
		    	}
		    	ajax('METDM04', requestMap, function(response) {
		    		if (response.header && response.header.status == '0000') {
		    			var body = response.body;
		    			if (body.count == '0') {
		    				alertUtils.showAlert('조회된 내용이 없습니다.')
		    			} else {
		    				var domainScInfoList = body.domainScInfoList;
		    				var theHtml = '';
		    				domainScInfoList.forEach((item) => {
		    					
			    				theHtml += '<li class="list-group-item">';
			    				theHtml += '	<span class="searchResult">'
			    				theHtml += '		<span class="domainSno">' + item.domainSno +'</span>'
			    				theHtml += '		<span class="domainName">' + item.domainName +'</span>'
			    				theHtml += '		<span class="domainType">' + item.domainType +'</span>'
			    				theHtml += '	</span>'
			    				theHtml += '	<button type="button" class="btn btn-primary btn-sm float-end btnSelectDomain">선택</button>'
			    				theHtml += '</li>'
			    				
		    				});
		    				$('#searchResults').html(theHtml)
		    			}
		    		} else {
		    			alertUtils.showAlert(response.header.errorMsg);
		    		}
		    	});
		    });
		 	// 검색 결과 선택 버튼 클릭 시
		    $(document).on('click', '.btnSelectDomain', function() {
		    	$('#searchResults .list-group-item').removeClass('selected'); // 이전 선택된 항목의 색상 제거
                $(this).closest('li').addClass('selected'); // 선택된 항목의 색상 변경
            });
		 	// 검색 입력 필드에서 엔터 키를 눌렀을 때 검색 수행
            $('#searchInput').on('keyup', function(event) {
                if (event.key === 'Enter') {
                	$('#btnSearchResults').trigger('click');
                }
            });
			
		    // 등록버튼
		    $('#btnRegister').click(function() {
		    	
		    	
		    	// 필수값 입력체크
		    	var fieldList = [];
		    	$('#main input.form-control, #main select.form-select').each(function() {
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

	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>
	
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>

</html>