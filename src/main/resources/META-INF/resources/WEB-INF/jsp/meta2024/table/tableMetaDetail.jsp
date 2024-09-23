<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>테이블 상세 | 메타관리시스템</title>
	
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
				        <h5 class="card-title">테이블 상세</h5>
				        <div class="row g-3">
				            <div class="col-md-12">
				            	<div class="col-md-4">
					                <div class="form-floating mb-3">
					                	<input type="hidden" id="tableMetaSno" name="tableMetaSno" value="${detail.tableMetaInfo.tableMetaSno}">
					                    <input type="text" class="form-control" id="schemaName" name="schemaName" value=${detail.tableMetaInfo.schemaName} readonly>
					                    <label for="schemaName">스키마명</label>
					                </div>
				                </div>
				            </div>
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="tableName" name="tableName" value=${detail.tableMetaInfo.tableName} readonly>
				                    <label for="tableName">테이블명</label>
				                </div>
				            </div>
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="tableDesc" name="tableDesc" value=${detail.tableMetaInfo.tableDesc} readonly>
				                    <label for="tableDesc">테이블설명</label>
				                </div>
				            </div>
				            
				             <!-- 버튼 추가 -->
				            <div class="row">
				                <div class="col-md-12">
				                    <button type="button" class="btn btn-sm btn-primary mt-3 mb-3" id="btnAddPkColumn">+ PK 컬럼 추가</button>
					             	<div id="pkColumnInputs">
					             		<c:forEach var="item" items="${detail.tableColumnList}">
					             			<c:choose>
					             				<c:when test="${item.pkColumnYn == '1'}">
							             		<div class="row g-3 pkColumnInput">
				    								<div class="text-end">
				    									<i class="bi bi-search btnSearchColumn"></i>
				    									<i class="bi bi-dash-square btnRemoveColumn"></i>
			    									</div>
											    	<div class="col-md-3">
											    		<div class="form-floating mb-3">
												    		<input type="hidden" class="termSno" name="termSno" value="${item.termSno}">
												    		<input type="hidden" class="columnMetaSno" name="columnMetaSno" value="${item.columnMetaSno}">
												    		<input type="text" class="form-control columnName" name="columnName" value="${item.columnName}" oninput="this.value = this.value.toUpperCase()" readonly>
												    		<label>PK 컬럼명</label>
										    			</div>
									    			</div>
									   				<div class="col-md-3">
											    		<div class="form-floating mb-3">
												    		<input type="text" class="form-control columnCamelName" name="columnCamelName" value="${item.columnCamelName}" readonly>
												    		<label>PK 컬럼카멜명</label>
										    			</div>
									   				</div>
									 				<div class="col-md-3">
											    		<div class="form-floating mb-3">
												    		<input type="text" class="form-control columnSnakeName" name="columnSnakeName" value="${item.columnSnakeName}" readonly>
												    		<label>PK 컬럼스네이크명</label>
										    			</div>
									   				</div>
									 				<div class="col-md-3">
											    		<div class="form-floating mb-3">
												    		<input type="text" class="form-control columnType" name="columnType" value="${item.columnType}" readonly>
												    		<label>PK 컬럼타입</label>
										    			</div>
									   				</div>
								    			</div>
					             				</c:when>
					             			</c:choose>
					             		</c:forEach>
					             	</div>
				                </div>
				            </div>
				            
				             <!-- 버튼 추가 -->
				            <div class="row">
				                <div class="col-md-12">
				                    <button type="button" class="btn btn-sm btn-primary mt-3 mb-3" id="btnAddColumn">+ 컬럼 추가</button>
					             	<div id="columnInputs">
					             		<c:forEach var="item" items="${detail.tableColumnList}">
					             			<c:choose>
					             				<c:when test="${item.pkColumnYn == '0' and item.sysColumnYn == '0'}">
							             		<div class="row g-3 columnInput">
				    								<div class="text-end">
				    									<i class="bi bi-search btnSearchColumn"></i>
				    									<i class="bi bi-dash-square btnRemoveColumn"></i>
			    									</div>
											    	<div class="col-md-3">
											    		<div class="form-floating mb-3">
												    		<input type="hidden" class="termSno" name="termSno" value="${item.termSno}">
												    		<input type="hidden" class="columnMetaSno" name="columnMetaSno" value="${item.columnMetaSno}">
												    		<input type="text" class="form-control columnName" name="columnName" value="${item.columnName}" oninput="this.value = this.value.toUpperCase()" readonly>
												    		<label>컬럼명</label>
										    			</div>
									    			</div>
									   				<div class="col-md-3">
											    		<div class="form-floating mb-3">
												    		<input type="text" class="form-control columnCamelName" name="columnCamelName" value="${item.columnCamelName}" readonly>
												    		<label>컬럼카멜명</label>
										    			</div>
									   				</div>
									 				<div class="col-md-3">
											    		<div class="form-floating mb-3">
												    		<input type="text" class="form-control columnSnakeName" name="columnSnakeName" value="${item.columnSnakeName}" readonly>
												    		<label>컬럼스네이크명</label>
										    			</div>
									   				</div>
									 				<div class="col-md-3">
											    		<div class="form-floating mb-3">
												    		<input type="text" class="form-control columnType" name="columnType" value="${item.columnType}" readonly>
												    		<label>컬럼타입</label>
										    			</div>
									   				</div>
								    			</div>
					             				</c:when>
					             			</c:choose>
					             		</c:forEach>
					             	</div>
				                </div>
				            </div>
				            
				            <div class="row">
					            <div class="text-end">
							        <button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
							        <button type="button" class="btn btn-sm btn-secondary mx-3" id="btnList" onclick="gotoURL('METTB01')">목록</button>
					            </div>
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
                    <h5 class="modal-title" id="searchModalLabel">용어 검색</h5>
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
			var searchButtonClicked;  // 클릭된 버튼 저장
			
			// + 버튼을 클릭했을 때 컬럼 입력 필드를 추가
		    $('#btnAddPkColumn').click(function() {
		    	var html = '<div class="row g-3 pkColumnInput">';
		    	html    += '	<div class="text-end">'
		    	html    += '		<i class="bi bi-search btnSearchColumn"></i>'
		    	html    += '		<i class="bi bi-dash-square btnRemoveColumn"></i>'
	    		html    += '	</div>'
		    	html    += '	<div class="col-md-3">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="hidden" class="termSno" name="termSno">'
	    		html    += '			<input type="text" class="form-control columnName" name="columnName" oninput="this.value = this.value.toUpperCase()" readonly>'
	    		html    += '			<label>PK 컬럼명</label>'
    			html    += '		</div>'
    			html    += '	</div>'
   				html    += '	<div class="col-md-3">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnCamelName" name="columnCamelName" readonly>'
	    		html    += '			<label>PK 컬럼카멜명</label>'
    			html    += '		</div>'
   				html    += '	</div>'
 				html    += '	<div class="col-md-3">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnSnakeName" name="columnSnakeName" readonly>'
	    		html    += '			<label>PK 컬럼스네이크명</label>'
    			html    += '		</div>'
   				html    += '	</div>'
 				html    += '	<div class="col-md-3">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnType" name="columnType" readonly>'
	    		html    += '			<label>PK 컬럼타입</label>'
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
		    	html    += '	<div class="col-md-3">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="hidden" name="termSno">'
	    		html    += '			<input type="text" class="form-control columnName" name="column" oninput="this.value = this.value.toUpperCase()" readonly>'
	    		html    += '			<label>컬럼명</label>'
    			html    += '		</div>'
    			html    += '	</div>'
		    	html    += '	<div class="col-md-3">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnCamelName" name="columnCamelName" readonly>'
	    		html    += '			<label>컬럼카멜명</label>'
    			html    += '		</div>'
    			html    += '	</div>'
		    	html    += '	<div class="col-md-3">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnSnakeName" name="columnSnakeName" readonly>'
	    		html    += '			<label>컬럼스네이크명</label>'
    			html    += '		</div>'
    			html    += '	</div>'
		    	html    += '	<div class="col-md-3">';
	    		html    += '		<div class="form-floating mb-3">'
	    		html    += '			<input type="text" class="form-control columnType" name="columnType" readonly>'
	    		html    += '			<label>컬럼타입</label>'
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
		    	searchButtonClicked = $(this); 
		    	$('#searchModal').modal('show');
		    	$('#searchResults').html('');
		    });
		 	// 확인 버튼 클릭 시
            $('#btnConfirmSelection').click(function() {
                var termSno = $('#searchResults .list-group-item.selected .termSno').text().trim();
                var termName = $('#searchResults .list-group-item.selected .termName').text().trim();
                var termCamelName = $('#searchResults .list-group-item.selected .termCamelName').text().trim();
                var termSnakeName = $('#searchResults .list-group-item.selected .termSnakeName').text().trim();
                var domainType = $('#searchResults .list-group-item.selected .domainType').text().trim();
                var target = $(searchButtonClicked).closest('.pkColumnInput, .columnInput');
                $(target).find('.termSno').val(termSno);
                $(target).find('.columnName').val(termName);
                $(target).find('.columnCamelName').val(termCamelName);
                $(target).find('.columnSnakeName').val(termSnakeName);
                $(target).find('.columnType').val(domainType);
                
                $('#searchModal').modal('hide');
            });
		    // 검색 로직
		    $('#btnSearchResults').click(function() {
		    	var requestMap = {
		    		termName: $('#searchInput').val()
		    	}
		    	ajax('METTM04', requestMap, function(response) {
		    		if (response.header && response.header.status == '0000') {
		    			var body = response.body;
		    			if (body.count == '0') {
		    				alertUtils.showAlert('조회된 내용이 없습니다.')
		    			} else {
		    				var termScInfoList = body.termScInfoList;
		    				var theHtml = '';
		    				termScInfoList.forEach((item) => {
		    					
			    				theHtml += '<li class="list-group-item">';
			    				theHtml += '	<span class="searchResult">'
			    				theHtml += '		<span class="termSno">' + item.termSno +'</span>'
			    				theHtml += '		<span class="termName">' + item.termName +'</span>'
			    				theHtml += '		<span class="termCamelName">' + item.termCamelName +'</span>'
			    				theHtml += '		<span class="termSnakeName">' + item.termSnakeName +'</span>'
			    				theHtml += '		<span class="domainType">' + item.domainType +'</span>'
			    				theHtml += '	</span>'
			    				theHtml += '	<button type="button" class="btn btn-primary btn-sm float-end btnSelectColumn">선택</button>'
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
		    $(document).on('click', '.btnSelectColumn', function() {
		    	$('#searchResults .list-group-item').removeClass('selected'); // 이전 선택된 항목의 색상 제거
                $(this).closest('li').addClass('selected'); // 선택된 항목의 색상 변경
            });
		 	// 검색 입력 필드에서 엔터 키를 눌렀을 때 검색 수행
            $('#searchInput').on('keyup', function(event) {
                if (event.key === 'Enter') {
                	$('#btnSearchResults').trigger('click');
                }
            });
		    
		    // 컬럼카멜명 입력시 컬럼스네이크명 자동입력 
		    $(document).on('input', '.columnCamelName', function() {
		    	$(this).val($(this).val().charAt(0).toLowerCase() + $(this).val().slice(1))
		    	$(this).closest('.pkColumnInput, .columnInput').find('.columnSnakeName').val(camel2Snake($(this).val()))
		    	
		    });
		    // 등록버튼
		    $('#btnUpdate').click(function() {
		    	// 필수값 입력체크
		    	var fieldList = [];
		    	$('#main input.form-control, #main select.form-control').each(function() {
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
		    			, columnType: $(this).find('.columnType').val()
		    			, termSno: $(this).find('.termSno').val()
	    				, columnMetaSno: $(this).find('.columnMetaSno').val()
		    			, nullColumnYn: "0"
		    			, pkColumnYn: "1"
		    			, sysColumnYn: "0"
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
		    			, columnType: $(this).find('.columnType').val()
	    				, termSno: $(this).find('.termSno').val()
	    				, columnMetaSno: $(this).find('.columnMetaSno').val()
		    			, nullColumnYn: "1"
		    			, pkColumnYn: "0"
		    			, sysColumnYn: "0"
		    		}
		    		theColumnList.push(item);
		    	});
		    	var requestMap = {
	    			tableMetaSno: $('#tableMetaSno').val()
	    			, tableDesc: $('#tableDesc').val()
	    			, pkColumnList: thePkColumnList
	    			, columnList: theColumnList
		    	};
		    	
		    	ajax('METTB06', requestMap, function(response) {
		    		if (response.header && response.header.status == '0000') {
		    			alertUtils.showAlert('수정되었습니다', function() {
		    				gotoURL('METTB01');
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