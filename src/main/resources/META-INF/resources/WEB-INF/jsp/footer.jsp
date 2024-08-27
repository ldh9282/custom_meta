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
		  	<a href="javascript:scrollToTop();" id="topBtn">TOP</a>
		  
				
	    </div>
		</section>
	</footer>
	<script>
		const currentYear = new Date().getFullYear();
		 	document.getElementById("currentYear").textContent = currentYear;
		 	window.onscroll = function() {
		 		
		   	var topButton = document.getElementById("topBtn");
		   	
		   	var threshold = 200;
		   	
		     	if (window.scrollY > threshold) {
		       	topButton.classList.add("show");
		    		} else {
		    			topButton.classList.remove("show");
		    		}
		 	};
		
		function scrollToTop() {
		    window.scrollTo({ top: 0, behavior: 'smooth' });
		}
	</script>