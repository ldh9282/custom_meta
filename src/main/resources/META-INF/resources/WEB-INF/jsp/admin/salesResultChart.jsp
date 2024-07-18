<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>통계: IYF 영업관리시스템</title>
	<jsp:include page="/WEB-INF/jsp/cmmn/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/cmmn/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/cmmn/scriptHeader.jsp"></jsp:include>
</head>

<body>

    <!-- ======= Header ======= -->
    <jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
    <!-- End Header -->

    <!-- ======= Sidebar ======= -->
    <jsp:include page="/WEB-INF/jsp/sidebar.jsp"></jsp:include>
    <!-- End Sidebar-->

    <main id="main" class="main">

        <section>
			<div class="d-flex mb-5 justify-content-between">
				<div class="dropdown">
					<select class="form-select" id="defaultMaxData" name="defaultMaxData">
						<option value="">- Y축 기준설정 -</option>
						<option value="100000000">100,000천 원</option>
						<option value="500000000">500,000천 원</option>
						<option value="1000000000">1,000,000천 원</option>
						<option value="5000000000">5,000,000천 원</option>
						<option value="10000000000">10,000,000천 원</option>
					</select>
				</div>
			  
				<div class="float-end mb-5">
					<input type="text" name="keyword" id="keyword" placeholder="해당년도">
					<button type="button" id="btnKeywordSearch">검색</button>
				</div>
			</div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card" style="width: 80vw;">
                        <div class="card-body">
                            <h5 class="card-title">IYCNC 연간 매출실적</h5>

                            <ul class="nav nav-pills mb-3" id="pills-tab1" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="pills-iycnc_ito-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-iycnc_ito" type="button" role="tab" aria-controls="pills-iycnc_ito"
                                        aria-selected="true">ITO</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="pills-iycnc_idc-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-iycnc_idc" type="button" role="tab"
                                        aria-controls="pills-iycnc_idc" aria-selected="false" tabindex="-1">IDC</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="pills-iycnc_conversion-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-iycnc_conversion" type="button" role="tab"
                                        aria-controls="pills-iycnc_conversion" aria-selected="false" tabindex="-1">컨버전스</button>
                                </li>
                            </ul><!-- End pills-tab1 -->
                            <div class="tab-content pt-2" id="myTabContent1">
                                <div class="tab-pane fade show active" id="pills-iycnc_ito" role="tabpanel"
                                    aria-labelledby="iycnc_ito-tab">
                                    <div id="iycncChart1"></div>
                                </div>

                                <div class="tab-pane fade" id="pills-iycnc_idc" role="tabpanel"
                                    aria-labelledby="iycnc_idc-tab">
                                    <div id="iycncChart2"></div>
                                </div>

                                <div class="tab-pane fade" id="pills-iycnc_conversion" role="tabpanel"
                                    aria-labelledby="iycnc_conversion-tab">
                                    <div id="iycncChart3"></div>
                                </div>

                            </div><!-- End myTabContent1 -->

                        </div>
                    </div>

                </div>
            </div><!-- End IYCNC row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="card" style="width: 80vw;">
                        <div class="card-body">
                            <h5 class="card-title">IBTS 연간 매출실적</h5>

                            <ul class="nav nav-pills mb-3" id="pills-tab2" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="pills-ibts_ito-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-ibts_ito" type="button" role="tab" aria-controls="pills-ibts_ito"
                                        aria-selected="true">ITO</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="pills-ibts_idc-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-ibts_idc" type="button" role="tab"
                                        aria-controls="pills-ibts_idc" aria-selected="false" tabindex="-1">IDC</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="pills-ibts_conversion-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-ibts_conversion" type="button" role="tab"
                                        aria-controls="pills-ibts_conversion" aria-selected="false" tabindex="-1">컨버전스</button>
                                </li>
                            </ul><!-- End pills-tab2 -->
                            <div class="tab-content pt-2" id="myTabContent2">
                                <div class="tab-pane fade show active" id="pills-ibts_ito" role="tabpanel"
                                    aria-labelledby="ibts_ito-tab">
                                    <div id="ibtsChart1"></div>
                                </div>

                                <div class="tab-pane fade" id="pills-ibts_idc" role="tabpanel"
                                    aria-labelledby="ibts_idc-tab">
                                    <div id="ibtsChart2"></div>
                                </div>

                                <div class="tab-pane fade" id="pills-ibts_conversion" role="tabpanel"
                                    aria-labelledby="ibts_conversion-tab">
                                    <div id="ibtsChart3"></div>
                                </div>

                            </div><!-- End myTabContent2 -->

                        </div>
                    </div>

                </div>
            </div><!-- End IBTS row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="card" style="width: 80vw;">
                        <div class="card-body">
                            <h5 class="card-title">IYS 연간 매출실적</h5>

                            <ul class="nav nav-pills mb-3" id="pills-tab3" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="pills-iys_ito-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-iys_ito" type="button" role="tab" aria-controls="pills-iys_ito"
                                        aria-selected="true">ITO</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="pills-iys_idc-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-iys_idc" type="button" role="tab"
                                        aria-controls="pills-iys_idc" aria-selected="false" tabindex="-1">IDC</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="pills-iys_conversion-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-iys_conversion" type="button" role="tab"
                                        aria-controls="pills-iys_conversion" aria-selected="false" tabindex="-1">컨버전스</button>
                                </li>
                            </ul><!-- End pills-tab3 -->
                            <div class="tab-content pt-2" id="myTabContent3">
                                <div class="tab-pane fade show active" id="pills-iys_ito" role="tabpanel"
                                    aria-labelledby="iys_ito-tab">
                                    <div id="iysChart1"></div>
                                </div>

                                <div class="tab-pane fade" id="pills-iys_idc" role="tabpanel"
                                    aria-labelledby="iys_idc-tab">
                                    <div id="iysChart2"></div>
                                </div>

                                <div class="tab-pane fade" id="pills-iys_conversion" role="tabpanel"
                                    aria-labelledby="iys_conversion-tab">
                                    <div id="iysChart3"></div>
                                </div>

                            </div><!-- End myTabContent3 -->

                        </div>
                    </div>

                </div>
            </div><!-- End IYS row -->
            
            
            
        </section>

    </main><!-- End #main -->

    <!-- ======= Footer ======= -->
    <jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
    <!-- END Footer -->
    
    <script>
    	$(document).ready(function() {
    		
        	
    		const params = new URLSearchParams(window.location.search);
    		

        	$("#keyword").val(params.get("year") !== null ? params.get("year") : new Date().getFullYear());
    		const defaultMaxData = params.get('defaultMaxData') !== null ? params.get('defaultMaxData') : 100000000; // (100,000천 원)
    		
    		
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IYCNC', 'ITO', 'iycncChart1', $("#keyword").val());
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IYCNC', 'IDC', 'iycncChart2', $("#keyword").val());
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IYCNC', 'CONVERSION', 'iycncChart3', $("#keyword").val());
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IBTS', 'ITO', 'ibtsChart1', $("#keyword").val());
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IBTS', 'IDC', 'ibtsChart2', $("#keyword").val());
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IBTS', 'CONVERSION', 'ibtsChart3', $("#keyword").val());
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IYS', 'ITO', 'iysChart1', $("#keyword").val());
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IYS', 'IDC', 'iysChart2', $("#keyword").val());
    		displaySalesResultChartByCompanyAndDepartmentAndYear('IYS', 'CONVERSION', 'iysChart3', $("#keyword").val());
    		
    		// 해당년도 회사 및 부서별 실적차트
    		function displaySalesResultChartByCompanyAndDepartmentAndYear(company, department, elementId, year) {
    			$.ajax({
                    url: "${pageContext.request.contextPath}/admin/salesYearResultByCompanyAndDepartmentAndYear.ajax/company/" + company + "/department/" + department + "/year/" + year,
                    method: "GET",
                    dataType: "json",
                    async: false,
                    success: function (response) {
                    	const yyyy = year;
                    	const yyyMMList = Array.from({ length: 12 }, (value, i) => {
                    	    return String(yyyy) + String(i + 1).padStart(2, '0');
                    	});

                    	const labelList = [];
                    	const totalSalesList = [];
                    	const totalPurchaseList = [];
                    	const totalMarginList = [];
                    	
                    	yyyMMList.forEach(yyyyMM => {
                    	    const foundItem = response.find(item => item.해당년월 === yyyyMM);

                    	    labelList.push(yyyyMM);
                    	    if (foundItem) {
                    	        totalSalesList.push(Number(foundItem.총매출가));
                    	        totalPurchaseList.push(Number(foundItem.총매입가));
                    	        totalMarginList.push(Number(foundItem.총매출가) - Number(foundItem.총매입가));
                    	    } else {
                    	        totalSalesList.push(0);
                    	        totalPurchaseList.push(0);
                    	        totalMarginList.push(0);
                    	    }
                    	});
						const maxData = Math.max(...totalSalesList, defaultMaxData); // 차트 y축 max 값, 최소 defaultMaxData 
						const ceilMaxData = calculateCeilMax(maxData); // 차트 y축 max 값 올림처리(정수 두번째자리에서)
						
                        new ApexCharts(document.querySelector("#" + elementId), {
                            series: [{
                                name: '총 매출가',
                                data: totalSalesList
                            }, {
                                name: '총 매입가',
                                data: totalPurchaseList
                            }, {
                                name: '총 수익',
                                data: totalMarginList
                            }],
                            chart: {
                                type: 'bar',
                                height: 350,
                                toolbar: {
                                    export: {
                                        csv: {
                                    	   filename: company + ' ' + department + ' ' + '연간 매출실적',
                                        	headerCategory: '해당년월',
                                    }
                                },
                                }
                            },
                            plotOptions: {
                                bar: {
                                    horizontal: false,
                                    columnWidth: '55%',
                                    endingShape: 'rounded'
                                },
                            },
                            dataLabels: {
                                enabled: false
                            },
                            stroke: {
                                show: true,
                                width: 2,
                                colors: ['transparent']
                            },
                            xaxis: {
                           		categories: labelList
                            },
                            yaxis: {
                                title: {
                                    text: '(천 원)'
                                },
                                labels: {
                                    formatter: function(val) {
                                    	return moneyUtils.commaFormatting(Math.floor(Number(val)/1000));
                                    }
                                },
                                max: ceilMaxData
                            },
                            fill: {
                                opacity: 1
                            },
                            tooltip: {
                                y: {
                                    formatter: function (val) {
                                    	if (!val) {
                                    		return String(0) + "천 원";
                                    	} else {
	                                        return moneyUtils.commaFormatting(Math.floor(Number(val)/1000)) + "천 원"
                                    	}
                                    }
                                }
                            },
                        }).render();
                    }
                })
    		}
    		
    		
    		// 차트 y축 max 값 올림처리
    		function calculateCeilMax(maxValue) {
    		    // 숫자를 정수로 변환하고 문자열로 변환
    		    const numStr = String(Math.floor(maxValue));
    		    
    		    // 문자열의 길이를 사용하여 올림 단위 결정 (정수 두번째자리에서)
    		    const roundingUnit = Math.pow(10, numStr.length - 1);

    		    // 올림 처리
    		    return Math.ceil(maxValue / roundingUnit) * roundingUnit;
    		}
    		
    		// 차트 Y축 기준설정
    		$('#defaultMaxData').change(function() {
    	        const url = new URL(window.location.href);
    	        url.searchParams.set('defaultMaxData', $(this).val());
    	        window.location.href = url.href;
    	    });
    		
    		$("#btnKeywordSearch").click(function() {
    			const regex = /^(19|20)\d\d$/;

                if (regex.test($('#keyword').val())) {
        	        const url = new URL(window.location.href);
        	        url.searchParams.set('year', $('#keyword').val());
        	        window.location.href = url.href;
                } else {
                	alert('올바른 해당년도 키워드를 입력한 뒤 다시 검색해주세요');
                }
    		});
    		
       		// 사이드바 접을 때 그리드 리사이징
            $('i.toggle-sidebar-btn').click(function() {
            	if ($('body').attr('class') === 'toggle-sidebar') {
            		$('.card').attr('style', 'width: 93vw;');
            		$('#footer').attr('style', 'width: 93vw;');
            	} else {
            		$('.card').attr('style', 'width: 80vw;')
            		$('#footer').attr('style', 'width: 80vw;');
            	}
            	

            });
    	});
    </script>


    <jsp:include page="/WEB-INF/jsp/cmmn/scriptBody.jsp"></jsp:include>
</body>

</html>