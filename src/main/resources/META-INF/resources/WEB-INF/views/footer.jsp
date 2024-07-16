<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<footer id="footer" class="footer">
	  <section>
	    <div class="container">
	      <div class="text-center my-3">
	        <span>&copy; <span id="currentYear"></span>. ldh9282 All rights reserved</span>
	      </div>
	      
	      <div class="text-center my-3">
			  <span class="text-muted">추가 요구사항 및 버그 제보:</span>
			  <a href="mailto:ldh9282@naver.com" class="text-dark ms-1">ldh9282@naver.com</a>
		  </div>
		  
<!-- 		  <div class="d-flex justify-content-end mt-3 mb-5"> -->
<!-- 		  	<a href="#" class="btn btn-outline-secondary">맨 위로 가기</a> -->
<!-- 		  </div> -->
	    </div>
	  </section>
	</footer>
	<script>
	  const currentYear = new Date().getFullYear();
	  document.getElementById("currentYear").textContent = currentYear;
	</script>