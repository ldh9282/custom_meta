<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<jsp:include page="/WEB-INF/jsp/cmmn/alertModal.jsp"></jsp:include>
	
	<script>
	$(document).ready(function() {
		 $(document).on('keydown', function(event) {
            if (event.altKey && event.key === '1') {
				gotoURL('METTB02');                	
            } else if (event.altKey && event.key === '2') {
				gotoURL('METCU01');                	
            } else if (event.altKey && event.key === '3') {
				gotoURL('METCE01');                	
            }
		 });
		 
		$("#tbody tr").hover(function() {
			$(this).find('.rownum').css({ "text-decoration": "underline" });
// 			$(this).css({ "background-color": "lightgray" })
			
		}, function() {
			$(this).find('.rownum').css({ "text-decoration-line" : "none" });
			
// 			$(this).css({ "background-color": "" });
		});
	});
	
	function gotoURL(url) {
		window.location.href = '${pageContext.request.contextPath}/' + url; 
	}
	
	function formSubmit(form, url) {
		$(form).attr('action', '${pageContext.request.contextPath}/' + url)
		       .submit();
	}
	
	const ajax = function(url, data, successCb, errorCb) {
		$.ajax({
	        type: 'POST',
	        url: '${pageContext.request.contextPath}/' + url,
	        data: JSON.stringify(data),
	        contentType: 'application/json',
	        async: false,
	        success: function(result) {
				if (successCb) {
		        	successCb(result);
				}
	        },
	        error: function(a, b, c) {
				if (errorCb) {
					errorCb(a, b, c);
				} else {
					alertUtils.showAlert('시스템 접속 에러가 발생했습니다.');
				}
	        }
	    });
	};
	
	</script>

    <!-- Excel Export JS File-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.full.min.js"></script>


	<!-- toast-grid-pagination -->
	<script src="https://uicdn.toast.com/tui.code-snippet/v1.5.0/tui-code-snippet.js"></script>
    <script src="https://uicdn.toast.com/tui.pagination/v3.3.0/tui-pagination.js"></script>
    <!-- toast-grid -->
    <script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>

    <!-- Vendor JS Files -->
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/chart.js/chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/quill/quill.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/tinymce/tinymce.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/php-email-form/validate.js"></script>

    <!-- NiceAdmin: Template Main JS File -->
    <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/js/main.js"></script>
    
    <!-- TimerElement.js -->
    <script type="module" src="${pageContext.request.contextPath}/resources/cmmn/js/element/TimerElement.js?2024042014"></script>

