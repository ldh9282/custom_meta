<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div class="modal fade" id="alertModal" tabindex="-1" aria-labelledby="alertModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="alertModalLabel">알림</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="$('#btnCloseAlertModal').trigger('click');"></button>
	            </div>
	            <div class="modal-body" id="modal-body1">
	                <!-- 알림 메시지가 여기에 표시됩니다 -->
	            </div>
	            <div class="modal-footer">
	                <button type="button" id="btnCloseAlertModal" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	            </div>
	        </div>
	    </div>
	</div>
	
    <div class="modal fade" id="alertModal2" tabindex="-1" aria-labelledby="alertModalLabel2" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="alertModalLabel2">알림</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="$('#btnCloseAlertModal2').trigger('click');"></button>
	            </div>
	            <div class="modal-body" id="modal-body2">
	                <!-- 확인 메시지가 여기에 표시됩니다 -->
	            </div>
	            <div class="modal-footer">
	                <button type="button" id="btnConfirmAlertModal2" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
	                <button type="button" id="btnCloseAlertModal2" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	            </div>
	        </div>
	    </div>
	</div>
	