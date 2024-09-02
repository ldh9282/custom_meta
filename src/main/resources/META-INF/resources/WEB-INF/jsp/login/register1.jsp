<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  	<title>회원가입 | 메타관리시스템</title>
   	<jsp:include page="/WEB-INF/jsp/include/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/scriptHeader.jsp"></jsp:include>

</head>

<body>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center py-4">
                <a href="${pageContext.request.contextPath}/" class="logo d-flex align-items-center w-auto">
                  <span class="d-none d-lg-block">메타관리시스템</span>
                </a>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">회원가입</h5>
                    <p class="text-center small">계정정보를 입력해주세요</p>
                  </div>

                  <form class="row g-3 needs-validation" novalidate
                        action="${pageContext.request.contextPath}/register" method="POST">
                    <div class="col-12">
                      <label for="memId" class="form-label">아이디</label>
                      <input type="text" name="memId" class="form-control" id="memId" required>
                      <div class="invalid-feedback">아이디를 입력해주세요!</div>
                    </div>

                    <div class="col-12">
                      <label for="memPw" class="form-label">비밀번호</label>
                      <input type="text" name="memPw" class="form-control" id="memPw" required>
<!--                       <label class="form-check-label" for="hide" class="form-label">비밀번호 감추기</label> -->
<!--                       <input class="form-check-input" type="checkbox" name="hide" id="hide"> -->
                      <div class="invalid-feedback">비밀번호를 입력해주세요!</div>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

<!--                     <div class="col-12"> -->
<!--                       <div class="form-check"> -->
<!--                         <input class="form-check-input" name="terms" type="checkbox" value="" id="acceptTerms" required> -->
<!--                         <label class="form-check-label" for="acceptTerms"><a id="instruction" title="회원가입 후 통합관리자에게 권한과 승인을 요청하세요">회원가입 시 안내사항</a></label> -->
<!--                         <div class="invalid-feedback">안내사항을 확인하셔야합니다.</div> -->
<!--                       </div> -->
<!--                     </div> -->
                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="button" id="btnRegister">회원가입</button>
                    </div>
                    <div class="col-12">
                      <p class="small mb-0">이미 계정이 존재하신가요? <a href="${pageContext.request.contextPath}/METLG01">로그인</a></p>
                    </div>
                  </form>

                </div>
              </div>

              <div class="credits">
                <!-- All the links in the footer should remain intact. -->
                <!-- You can delete the links only if you purchased the pro version. -->
                <!-- Licensing information: https://bootstrapmade.com/license/ -->
                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
                <a href="${pageContext.request.contextPath}/">홈페이지로 가기</a>
              </div>

            </div>
          </div>
        </div>

      </section>

    </div>
  </main><!-- End #main -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <script>

//     function checkUsername(username) {
//       $.ajax({
//         url: "${pageContext.request.contextPath}/member.ajax/username/" + username,
//         method: "GET",
//         success: function(member) {
//         	if (member) {
// 				alert("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
// 	        	$("input[name=username]").val("");
// 			} else {
				
// 			}
//         }
//       });
          
//     }

    $(document).ready(function() {

//       $("input[name=username]").change(function() {
//         if ($(this).val()) {
//         	checkUsername($(this).val());
//         }
//       });
      
      $('#btnRegister').click(function(e) {
    		// 필수값 입력체크
	    	var fieldList = [];
	    	$('input.form-control').each(function() {
	    		var fieldLabel = $(this).siblings('label').text();
	    		var fieldValue = $(this).val();
	    		var fieldObj = { label: fieldLabel, value: $(this).val() };
	    		fieldList.push(fieldObj);
	    	});
	    	if (!alertUtils.checkRequiredFields(fieldList)) {
	    		return;
	    	}
	    	
	    	const requestMap = {
	    		memId: $('#memId').val().trim()
	    		, memPw: $('#memPw').val().trim()
	    	};
	    	
	    	ajax('METLG03', requestMap, function(response) {
	    		if (response.header && response.header.status == '0000') {
	    			alertUtils.showAlert('회원등록이 완료되었습니다');
	    			location.href = '${pageContext.request.contextPath}/METLG01';
	    		} else {
	    			alertUtils.showAlert(response.header.errorMsg);
	    		}
	    	});
	    	
	    	
      })
      
      $('#instruction').tooltip();

    });
  </script>

	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>
</body>

</html>