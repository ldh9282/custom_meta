<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<div class="row">
				<div class="col-lg-12">
					<nav aria-label="Page navigation example">
					  <ul class="pagination" style="justify-content:center;">
					    <c:if test="${pagingCreator.prev}">
							<li class="page-item">
								<a class="page-link" href="javascript:goToPaging('1');">처음</a>
							</li>
						</c:if>
						<c:if test="${pagingCreator.prev}">
							<li class="page-item">
								<a class="page-link" href="javascript:goToPaging('${pagingCreator.startPagingNum - 1}');">이전</a>
							</li>
						</c:if>
						
						<c:forEach var="thePageNum" begin="${pagingCreator.startPagingNum}" end="${pagingCreator.endPagingNum}">
							<li class="page-item">
								<a class="page-link ${thePageNum == pagingCreator.pageNum ? 'active' : ''}" href="javascript:goToPaging('${thePageNum}');">${thePageNum}</a>
							</li>
						</c:forEach>
						
						<c:if test="${pagingCreator.next}">
							<li class="page-item">
								<a class="page-link" href="javascript:goToPaging('${pagingCreator.endPagingNum + 1}');">다음</a>
							</li>
						</c:if>
						
						<c:if test="${pagingCreator.next}">
							<li class="page-item">
								<a class="page-link" href="javascript:goToPaging('${pagingCreator.lastPageNum}');">끝</a>
							</li>
						</c:if>
					  </ul>
					</nav>
				</div>
			</div>

