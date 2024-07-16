$(document).ready(function() {
	
	// 드래그 복사중에 클릭이벤트 방지
    $.fn.preventClickWhileDrag = function (callback) {
        let isDragging = false;

        this.on('mousedown', function() {
            isDragging = false;
        }).on('mousemove', function() {
            isDragging = true;
        }).on('mouseup', function() {
            setTimeout(function() { isDragging = false; }, 0);
        }).on('click', function(event) {
            if (isDragging) {
                event.preventDefault();
                event.stopImmediatePropagation();
                return;
            }
            if (typeof callback === 'function') {
                callback.call(this, event);
            }
        })

        return this;
    };

	// 드래그 복사중에 클릭이벤트 방지적용
    $('tr').preventClickWhileDrag(function() {
    });
});