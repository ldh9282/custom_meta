const moneyUtils = {
	format: function(input) {
	    let numberValue = Number(input);
	    
        if (isNaN(numberValue) || !isFinite(numberValue)) {
        	return '-';
    	}
	    let numStr = '';
	    
	    if (Number.isInteger(numberValue)) {
			numStr = Number.parseInt(numberValue).toString();
		} else if ((numberValue.toFixed(1) == numberValue) || (numberValue.toFixed(2) == numberValue) || (numberValue.toFixed(3) == numberValue)) {
	    	
	        numStr = numberValue.toString();
	    } else {
	        numStr = numberValue.toFixed(3);
	        
	        if (Number.isInteger(Number(numStr))) {
				numStr = Number.parseInt(Number(numStr)).toString()
			}
	    }
		
		numStr = numStr.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		
		return numStr;
	},
}

const phoneUtils = {
  	format: function(input) {
    	let numStr = input.replace(/\D/g, '');

    	numStr = numStr.replace(/(\d{3})(\d{3,4})(\d{4})/, '$1-$2-$3');

    	return numStr;
  	},
};

const dateUtils = {
	format: function(date, format, isObject) {
		
	    if (!date || !(date instanceof Date)) {
	        return isObject ? { year: '', month: '', day: '', hour: '', minute: '', second: '' } : "";
	    }
	
	    const options = {hourCycle: 'h23'};
	    
	    const formatMapping = {
	        'yyyy': 'numeric',
	        'MM': '2-digit',
	        'dd': '2-digit',
	        'HH': '2-digit',
	        'mm': '2-digit',
	        'ss': '2-digit'
	    };
	
	    for (const key in formatMapping) {
	        if (format.includes(key)) {
	            const optionKey = key[0] === 'y' ? 'year' :
	                              key[0] === 'M' ? 'month' :
	                              key[0] === 'd' ? 'day' :
	                              key[0] === 'H' ? 'hour' :
	                              key[0] === 'm' ? 'minute' :
	                              key[0] === 's' ? 'second' : '';
	            options[optionKey] = formatMapping[key];
	        }
	    }
	
	    const formatter = new Intl.DateTimeFormat('ko-KR', options);
	    const parts = formatter.formatToParts(date);
	    let formattedDate = format;
	    let result = {}
	
	    parts.forEach(({ type, value }) => {
	        const key = type === 'year' ? 'yyyy' :
	                    type === 'month' ? 'MM' :
	                    type === 'day' ? 'dd' :
	                    type === 'hour' ? 'HH' :
	                    type === 'minute' ? 'mm' :
	                    type === 'second' ? 'ss' : '';
	        if (key) {
	            formattedDate = formattedDate.replace(key, value);
	            if (isObject) {
	                result[type] = value;
	            }
	        }
	    });
	
	    return isObject ? result : formattedDate;
	}
};

const floatUtils = {
	format: function(input) {
        let numberValue = Number(input);

        if (isNaN(numberValue) || !isFinite(numberValue)) {
            return '-';
        }

        if (Number.isInteger(numberValue)) {
            return Number.parseInt(numberValue);
        } else {
            let truncatedValue = Math.trunc(numberValue * 1000) / 1000;

            if (Number.isInteger(truncatedValue)) {
                return Number.parseInt(truncatedValue);
            }

            if (truncatedValue.toFixed(3) == truncatedValue) {
                return truncatedValue;
            }

            return truncatedValue;
        }
    }
}

const alertUtils = {
	checkRequiredFields: function(fieldList) {
		var missingFields = [];

	    for (var idx in fieldList) {
	        if (!fieldList[idx].value) {
	            missingFields.push(fieldList[idx]);
	        }
	    }
	
	    if (missingFields.length > 0) {
	        var message = '다음 필수값을 입력해주세요:\n\n';
	        missingFields.forEach(function(field) {
	            message += '- ' + field.label + '\n';
	        });
	        alertUtils.showAlert(message);
			
	        return false;
	    }
	
	    return true;
	},
	
	showAlert: function(message, callback) {
        var alertModal = $('#alertModal');

		if (!alertModal) {
			alert(message);
		} else {
	        $('#modal-body1').html(message.replace(/\n/g, '<br>'));
	        // 모달 밖 클릭 허용
	        var modal = new bootstrap.Modal(alertModal);
	        // 모달 밖 클릭 방지할경우
//	        var modal = new bootstrap.Modal(alertModal, {
//	        	backdrop: 'static'
//	        	, keyboard: false
//		    });
	        modal.show();
	        // 모달 사라질경우 콜백
	        if (callback) {
				$('#alertModal').off('hidden.bs.modal').on('hidden.bs.modal', function () {
	            	callback();
			    });
			}
		}
	},
	
	showConfirm: function(message, callback) {
        var alertModal = $('#alertModal2');

		if (!alertModal) {
			alert(message);
		} else {
	        $('#modal-body2').html(message.replace(/\n/g, '<br>'));
	        var modal = new bootstrap.Modal(alertModal);
	        modal.show();
	        // 확인 버튼시 콜백
	        if (callback) {
		        $('#btnConfirmAlertModal2').off('click').one('click', function() {
                	callback();
            	})
			}
		}
	}
	
}

const formToObject = function(form) {
    const formData = {};
    $(form).find('input, select, textarea').each(function() {
        const name = $(this).attr('name');
        const value = $(this).val();
        if (name) {
            formData[name] = value;
        }
    });
    return formData;
};

const timerUtils = {
	
	startTimer: function(startTimeStr, endTimeStr, callback, endCallback) {
		let delay = 1000;
		
		let diffMillis = new Date(endTimeStr).getTime() - new Date(startTimeStr).getTime();
		
		let displayTime = timerUtils.getDisplayTime(diffMillis);
		
		if (callback) { // setInterval 처음시작시 delay 보정 ::: callback 즉시실행 
			callback(displayTime);
		}
		if (diffMillis <= 0) { // 남은시간 0일때 ::: endCallback 즉시실행  
			if (endCallback) {
				endCallback(displayTime);
			}
		} else { // 남은시간 있을때 ::: setInterval
			diffMillis -= delay;
			
			let interval = setInterval(function() {
				
				
				let displayTime = timerUtils.getDisplayTime(diffMillis);
				
				
				if (callback) {
					callback(displayTime);
				}
				
				if (diffMillis <= 0) {
					clearInterval(interval);
					if (endCallback) {
						endCallback(displayTime);
					}
				}
				
				diffMillis -= delay;
			}, delay);
		}
	}
	, getDisplayTime: function(diffMillis) {
		let result = {};
		
		if (diffMillis <= 0) {
			result = {
				hour: '00'
				, min: '00'
				, sec: '00'
			};
			
			return result;
		}
		
		let diffSeconds = Math.floor(diffMillis / 1000);
		let diffMinutes = Math.floor(diffSeconds / 60);
		let diffHours = Math.floor(diffMinutes / 60);
		let diffDays = Math.floor(diffHours / 24);
		
		let displaySeconds = (diffSeconds % 60).toString().padStart(2, '0');
		let displayMinutes = (diffMinutes % 60).toString().padStart(2, '0');
		let displayHours = (diffHours).toString().padStart(2, '0');
		
		result = {
			hour: displayHours
			, min: displayMinutes
			, sec: displaySeconds
		};
		
		return result;
	}
}

function camel2Snake(str) {
    str = str.charAt(0).toLowerCase() + str.substr(1, str.length);
    return str.replace(/([A-Z])/g, (word) => '_' + word.toLowerCase()).toUpperCase();
}

function snake2Camel(str) {
    str = str.toLowerCase();
    return str.replace(/_./g, (word) => word.charAt(1).toUpperCase());
}

function changeCase(str) {
    if (str === str.toLowerCase()) {
        return str.toUpperCase();
    } else if (str === str.toUpperCase()) {
        return str.toLowerCase();
    } else {
        return str;
    }
}