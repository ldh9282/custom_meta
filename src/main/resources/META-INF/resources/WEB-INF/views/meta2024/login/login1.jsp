<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  	<title>로그인 | 메타관리시스템</title>
  	
  	<jsp:include page="/WEB-INF/views/cmmn/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/cmmn/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/cmmn/scriptHeader.jsp"></jsp:include>
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
                  <img src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/img/logo.png" alt="">
                  <span class="d-none d-lg-block">메타관리시스템</span>
                </a>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">로그인</h5>
                    <p class="text-center small">아이디와 비밀번호를 입력해주세요</p>
                  </div>

                  <form class="row g-3 needs-validation" novalidate
                        action="${pageContext.request.contextPath }/login" method="POST">
                    
                    <c:if test="${param.error != null }">
	
                        <i class="text-danger">아이디 또는 비밀번호가 틀립니다!</i>
                        
                    </c:if>
                    
                    
                    <div class="col-12">
                      <label for="yourUsername" class="form-label">아이디</label>
                      <div class="input-group has-validation">
                        <span class="input-group-text" id="inputGroupPrepend">ID</span>
                        <input type="text" name="username" class="form-control" id="yourUsername" required>
                        <div class="invalid-feedback">아이디를 입력해주세요!</div>
                      </div>
                    </div>

                    <div class="col-12">
                      <label for="yourPassword" class="form-label">비밀번호</label>
                      <input type="password" name="password" class="form-control" id="yourPassword" required>
                      <div class="invalid-feedback">비밀번호를 입력해주세요!</div>
                    </div>

                    <div class="col-12">
                      <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="remember-me" value="true" id="remember-me">
                        <label class="form-check-label" for="remember-me">아이디 저장</label>
                      </div>
                    </div>
                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="submit">로그인</button>
                      <security:csrfInput/>
                    </div>
                    <div class="col-12">
                      <p class="small mb-0">계정이 없으신가요? <a href="${pageContext.request.contextPath}/METLG02">계정만들기</a></p>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
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
    $(document).ready(function() {
    	showloginDisabledErrorAndRedirect();
    	
    	function showloginDisabledErrorAndRedirect() {
			
	    	const params = new URLSearchParams(window.location.search);
	    	if (params.get('unenabled') !== null) {
	    		const username = params.get('username');
				alert("해당계정(" + username +")은 통합관리자에게 회원가입 승인을 요청해야합니다.");
				window.location.href = '${pageContext.request.contextPath}/login';
			}
		}
    });
  </script>
  
  <jsp:include page="/WEB-INF/views/cmmn/scriptBody.jsp"></jsp:include>

</body>

</html>