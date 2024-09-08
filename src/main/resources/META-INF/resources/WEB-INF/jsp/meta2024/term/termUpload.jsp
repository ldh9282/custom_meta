<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   	<title>용어 업로드 | 메타관리시스템</title>
	
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
			        <div class="card-header">
			            <h1 class="card-title">용어 업로드</h1>
			        </div>
			        <div class="card-body">
			            <form>
			                <div class="row m-3">
			                    <div class="col-12">
			                    	<label for="file" class="form-label">엑셀 파일을 선택하세요:</label>
			                    </div>
			                </div>
			                <div class="row m-3">
			                    <div class="col-8">
			                        <input type="file" name="file" id="file" class="form-control" required>
			                    </div>
			                    <div class="col-4">
			                        <button type="button" id="btnUpload" class="btn btn-primary btn-block">업로드</button>
			                    </div>
			                </div>
			            </form>
			        </div>
			        <div class="card-footer">
			            <div class="row mt-3">
			                <p class="text-muted" style="font-style: italic;">파일 업로드 확장자는 .xlsx 만 가능합니다.</p>
			            </div>
			        </div>
			    </div>
			</div>
			
		</section>
   	</main>
    
	
	<script>
		$(document).ready(function() {
			// 엑셀 파일 업로드
			let isBtnUploadClicked = false; // 중복 클릭 방지
			$("#file").change(function() {
				isBtnUploadClicked = false;
				$('#btnUpload').prop("disabled", false);
			})
			$('#btnUpload').click(function() {
			    if (isBtnUploadClicked) {
			        return; // 이미 클릭된 경우 아무 작업도 하지 않음
			    }
				 // 버튼 딤 처리 (클릭 중복 방지)
				isBtnUploadClicked = true;
				$('#btnUpload').prop("disabled", true);
				
				const formData = new FormData();
				if (!$('#file')[0].files[0]) {
					alert("업로드할 엑셀 파일을 선택해주세요.")
					return;
				} else if (!$('#file')[0].files[0].name.endsWith('.xlsx')) {
					alert("올바른 파일 확장자가 아닙니다.")
					return;
				}
				
				formData.append("file", $('#file')[0].files[0]);
				
				
				$.ajax({
			        url: "${pageContext.request.contextPath}/METTM07", 
			        type: "POST",
			        data: formData,
			        async: false,
			        contentType: false,
			        processData: false,
			        success: function (response) {
						if (response.header && response.header.status == '0000') {
			    			alertUtils.showAlert('등록되었습니다', function() {
			    				gotoURL('METTM03');
			    			});
			    		} else {
			    			alertUtils.showAlert(response.header.errorMsg);
			    			isBtnUploadClicked = false;
							$('#btnUpload').prop("disabled", false);
			    		}
			        }
				});
				
				
			})
			
			
		})
	</script>
	
	

	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>
	
	<!-- ======= Footer ======= -->
    <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>
