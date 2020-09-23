<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
/* 추천 ******************************************************************************************/
function goDepositRecommend(){
	location.href = "${ pageContext.request.contextPath }/product/depositFreeExplain" + ${ageGroupDepositAccountBankBook};
}

function goSavingsRecommend(){
	location.href = "${ pageContext.request.contextPath }/product/savingsExplain${jobSavingsAccountBankBook} ";
}

function goChallenge(){
	location.href = "${ pageContext.request.contextPath }/challengeChoose";
}
function goSavings(){
	location.href = "${ pageContext.request.contextPath }/product/savingsChoose";
}


$(document).ready(function(){
	
	/* 잦은 지출 상세 내역********************************************************************************** */
	$('.frequentDetailBtn').click(function(){
		let btn = this;
		let elements = $(this).attr('id').split('.');
		let str = '';
		
		$.ajax({
			url : '${ pageContext.request.contextPath }/frequentDetail',
			type : 'get',
			data : {
				accountNumber : elements[0],
				toName : elements[1]
			},
			success : function(data){
				
				$('#modalDiv').empty();
				let list = JSON.parse(data);
				
				$(list).each(function(){

					str += '<hr>';
					str += '<div>';
					str += '<strong>' + this.logDate + '</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' + this.amount + '원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + this.toName + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '</div>';
				})
				
				$("#modalDiv").append(str);
				$("#frequentModal").modal('show');

			}
		})
	})
	
	/* 카테고리별 지출 내역 *****************************************************************************************************************/
	$('.categoryBtn').click(function(){
		let btn = this;
		let str = '';
		$.ajax({
			url : '${ pageContext.request.contextPath }/categoryDetail',
			type : 'get',
			data : {
				logTypeKey : $(this).attr('id')
			},
			success : function(data){
				
				$('#modalDiv2').empty();
				let list = JSON.parse(data);
	
				$(list).each(function(){

					str += '<hr>';
					str += '<div>';
					str += '<strong>' + this.logDate + '</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + this.sumAmount +'원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + this.toName + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '</div>';
				})
				
				$("#modalDiv2").append(str);
				$('#categoryModal').modal('show'); 
			},
			error : function(){
				alert('fail');
			}
		})
	})
 
})

	/* 최대 지출 카테고리 최근 2개월 추이 그래프 **************************************************************************************************************** */
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable( ${str} );

        var options = {
          title: '지출이 가장 많은 카테고리의 최근 2개월 추이',
          curveType: 'function',
          legend: { position: 'bottom' },
          hAxis: {textPosition : 'none'},
          legend:'none',
          vAxis: {
              viewWindow: {
                  min: 0
              }
       	}

        };
        
        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
      /* 최근 3개월 수입, 지출 영역 그래프  *********************************************************************************************************************/
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart2);

      function drawChart2() {
        var data = google.visualization.arrayToDataTable([
          ['월', '수입', '지출'],
          [${month-2} + '월',${depositByLast3Month.get(2)},${withdrawByLast3Month.get(2)}],
          [${month-1} + '월',${depositByLast3Month.get(1)},${withdrawByLast3Month.get(1)}],
          [${month} + '월',  ${depositByLast3Month.get(0)},${withdrawByLast3Month.get(0)}]
        ]);

        var options = {
          title: '최근 3개월 수입, 지출 추이',
          hAxis: {title: '',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
      
	/* 주별 지출액 그래프  *******************************************************************/
 
      
      google.charts.load('49', {'packages': ['vegachart']}).then(drawChart3);

      function drawChart3() {
        const dataTable = new google.visualization.DataTable();
        dataTable.addColumn({type: 'string', 'id': 'category'});
        dataTable.addColumn({type: 'number', 'id': 'amount'});
        dataTable.addRows(
      	${str2}	  
        );

        const options = {
          hAxis: {title: '',  titleTextStyle: {color: '#333'}},		
        		
          "vega": {
            "$schema": "https://vega.github.io/schema/vega/v4.json",
            "padding": 3,

            'data': [{'name': 'table', 'source': 'datatable'}],

            "signals": [
              {
                "name": "tooltip",
                "value": {},
                "on": [
                  {"events": "rect:mouseover", "update": "datum"},
                  {"events": "rect:mouseout",  "update": "{}"}
                ]
              }
            ],

            "scales": [
              {
                "name": "xscale",
                "type": "band",
                "domain": {"data": "table", "field": "category"},
                "range": "width",
                "padding": 0.5,
                "round": true
              },
              {
                "name": "yscale",
                "domain": {"data": "table", "field": "amount"},
                "nice": true,
                "range": "height"
              }
            ],

            "axes": [
              { "orient": "bottom", "scale": "xscale" },
              { "orient": "left", "scale": "yscale" }
            ],

            "marks": [
              {
                "type": "rect",
                "from": {"data":"table"},
                "encode": {
                  "enter": {
                    "x": {"scale": "xscale", "field": "category"},
                    "width": {"scale": "xscale", "band": 1},
                    "y": {"scale": "yscale", "field": "amount"},
                    "y2": {"scale": "yscale", "value": 0}
                  },
                  "update": {
                    "fill": {"value": "steelblue"}
                  },
                  "hover": {
                    "fill": {"value": "red"}
                  }
                }
              },
              {
                "type": "text",
                "encode": {
                  "enter": {
                    "align": {"value": "center"},
                    "baseline": {"value": "bottom"},
                    "fill": {"value": "#333"}
                  },
                  "update": {
                    "x": {"scale": "xscale", "signal": "tooltip.category", "band": 0.5},
                    "y": {"scale": "yscale", "signal": "tooltip.amount", "offset": -2},
                    "text": {"signal": "tooltip.amount"},
                    "fillOpacity": [
                      {"test": "datum === tooltip", "value": 0},
                      {"value": 1}
                    ]
                  }
                }
              }
            ]
          }
        };

        const chart = new google.visualization.VegaChart(document.getElementById('week_chart'));
        chart.draw(dataTable, options);
      }
/* 메일 서비스 신청, 취소 ***************************************************************************/
function goAddMailService(){
	location.href = "${ pageContext.request.contextPath }/addMailService";
}

function goDeleteMailService(){
	location.href = "${ pageContext.request.contextPath }/deleteMailService";
}
      
      
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />








<!---상품 추천----------------------------------------------------------------------------------------------------------->
<section class="service-overview section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="content-block">
                    <h2>${ loginVO.name }님에게 추천합니다.</h2>
                    <p>고객님의 나이와 직업 정보를 이용해 다음 상품을 추천드립니다.
                    </p>
                    <ul>
                        <li><i class="fa fa-caret-right"></i>
                        	'${ loginVO.ageGroup }' Pick 입출금 자유 예금 상품은
                        	<c:if test="${ ageGroupDepositAccountBankBook.equals(\"1\") }">'하나 플러스통장'입니다.</c:if>
                            <c:if test="${ ageGroupDepositAccountBankBook.equals(\"2\") }">'영하나 플러스통장'입니다.</c:if>
                            <c:if test="${ ageGroupDepositAccountBankBook.equals(\"3\") }">'주거래 하나 통장'입니다.</c:if>
                            <input type="button" value="보러가기" class="btn-style-one" onclick="goDepositRecommend()">
                        </li>
                        <li><i class="fa fa-caret-right"></i>
                        	'${ loginVO.jobKey }' Pick 정기 적금 상품은
                        	<c:if test="${ jobSavingsAccountBankBook.equals(\"1\") }">'하나 원큐 적금'입니다.</c:if>
                            <c:if test="${ jobSavingsAccountBankBook.equals(\"2\") }">'내집마련 적금'입니다.</c:if>
                            <c:if test="${ jobSavingsAccountBankBook.equals(\"3\") }">'꿈하나 적금'입니다.</c:if>
                        	<br>
                        	<input type="button" value="보러가기" class="btn-style-one" onclick="goSavingsRecommend()">
                        </li>
                        
                    </ul>
                    
                </div>
            </div>
            <div class="col-md-6">
                <div class="accordion-section">
                    <div class="accordion-holder">
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingOne">
                                    <h4 class="panel-title">
                                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            	하나은행의 고객 맞춤 상품 추천 서비스
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="panel-body">
                                        	한명 한명의 고객을 이해하고 상품을 추천합니다. 
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingTwo">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                                            aria-controls="collapseTwo">
                                            	하나은행 입출금 자유 예금 상품 특징
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                    <div class="panel-body">
                                        	내용
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingThree">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false"
                                            aria-controls="collapseThree">
                                         	   하나은행 정기 적금 상품 특징
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                    <div class="panel-body">
                                     	내용
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            
            <a href="${pageContext.request.contextPath }/product" class="btn btn-style-one">전체 상품 보러가기</a>
            </div>
            
 
    <!-- 지출액 Top3, 잦은 거래  ------------------------------------------------------------->        
            <div class="service-box col-md-12">
            <br><br>
                <div class="row">
                    <div class="col-md-6">
                    
                        <div class="contents">
                            <div class="section-title">
                                <h3>${ month }월 지출 Top3</h3>
                            </div>
                            <div class="text">
                                <p>이번 달 지출 금액이 컸던 내역을 확인하세요.<br> 충동적 소비는 경계하시길 바랍니다!</p>
                                <p></p>
                            </div>
                            <ul class="content-list">
                            	<c:forEach items="${ expenditureTop3List}" var="account">
	                                <li>
	                                    <i class="fa fa-caret-right"></i>
	                                    '${ account.logDate }'에 <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	                                    '${ account.toName }'에서 ${ String.format("%,d", account.amount) }원을 지출하였습니다.
	                                </li>
                                </c:forEach> 
                            </ul>
                        </div>
                        
                        
                        
                    </div>
                    <div class="col-md-6">
                        <div class="contents">
                            <div class="section-title">
                                <h3>${ month }월 잦은 지출 </h3>
                            </div>
                            <div class="text">
                                <p>이번 달에 잦은 지출을 했던 곳을 확인하시고,<br> 습관적으로 방문하는 것은 아닌지 생각해 보는 것은 어떨까요?</p>
                                <p></p>
                            </div>
                            <ul class="content-list">
                            	<c:forEach items="${ frequentExpenditureList}" var="account">
                                <c:if test='${ !account.toName.equals(loginVO.name) && account.count >=4 }'>
                                
	                                <li>
	                                    <i class="fa fa-caret-right"></i>
	                                    '${ account.toName }'에서 총  ${ account.count }번의 지출했습니다.<br>
	                                </li>
                                </c:if>
                                </c:forEach>
                            </ul>
                            
                            <c:forEach items="${ frequentExpenditureList}" var="account">
                            <c:if test='${ !account.toName.equals(loginVO.name) && account.count >=4 }'>
                                <input type="button" value="내역확인" class="frequentDetailBtn btn-style-one" id="${ account.accountNumber }.${ account.toName }"> 
                            </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            
            <br><br>
            
            <div class="service-box col-md-12">
            <br><br>
                <div class="row">
                <!-- 이번 주 지출 ------------------------------------------------------------------------------------------------->
                    <div class="col-md-6">
                    
					    <div class="contents">
					        <div class="section-title">
						        <h3>주별 지출액 </h3>
					
									<ul class="content-list">
						            <c:forEach items="${ expenditureByWeekList }" var="expenditureByWeek">                
						              <li><i class="fa fa-caret-right"></i>${ expenditureByWeek.week - 35 }주차 지출액은 ${ String.format("%,d", expenditureByWeek.sumAmount ) }원입니다.</li>
						            </c:forEach>
					                </ul>
					                
					                <ul class="content-list">
					                	<li><i class="fa fa-check-circle-o"></i>주 평균 지출액은 ${ String.format("%,d",avgExpenditureByWeek) }원입니다. </li>
							            <c:if test="${ avgExpenditureByWeek > expenditureByWeekList.get(expenditureByWeekList.size()-1).sumAmount }">
											<li><i class="fa fa-check-circle-o"></i><span style="color:red">평균 지출액 보다 현재까지 ${ avgExpenditureByWeek - expenditureByWeekList.get(expenditureByWeekList.size()-1).sumAmount }원 덜 지출하셨습니다!</span></li>
							            </c:if>
						            </ul>
						        <br>
					        </div>       
							<div id="week_chart" style="width: 370px; height: 150px"></div>
					    </div>
                        
                    </div>
                    <div class="col-md-6">
					    <div class="contents">
					        <div class="section-title">
						        <h3>최근 3개월 <span>잔여금 추이</span></h3>
						        <p>
						        	<c:if test="${ depositByLast3Month.get(0) - withdrawByLast3Month.get(0) > depositByLast3Month.get(1) - withdrawByLast3Month.get(1) &&
						        	               depositByLast3Month.get(1) - withdrawByLast3Month.get(1) > depositByLast3Month.get(2) - withdrawByLast3Month.get(2) }">
						        	<span style="color:red">최근 3개월 잔여금이 연속 증가했습니다!</span><br>	               
						        	</c:if>
						        	
						        	<ul class="content-list">
										<li><i class="fa fa-caret-right"></i>${ month-2 }월 수입 : ${ String.format("%,d", (depositByLast3Month.get(2)))}원, 지출 : ${ String.format("%,d", (withdrawByLast3Month.get(2)))}원</li>
										<li><i class="fa fa-caret-right"></i>${ month-1 }월 수입 : ${ String.format("%,d", (depositByLast3Month.get(1)))}원, 지출 : ${ String.format("%,d", (withdrawByLast3Month.get(1)))}원</li>
										<li><i class="fa fa-caret-right"></i>${ month-0 }월 수입 : ${ String.format("%,d", (depositByLast3Month.get(0)))}원, 지출 : ${ String.format("%,d", (withdrawByLast3Month.get(0)))}원 </li>
							        </ul>
									
									
					                <ul class="content-list">
					                  <li><i class="fa fa-check-circle-o"></i>${ month-2 }월 잔여금은 ${ String.format("%,d", (depositByLast3Month.get(2) - withdrawByLast3Month.get(2))) }원입니다.</li>
					                  <li><i class="fa fa-check-circle-o"></i>${ month-1 }월 잔여금은 ${ String.format("%,d", (depositByLast3Month.get(1) - withdrawByLast3Month.get(1))) }원입니다.</li>
					                  <li><i class="fa fa-check-circle-o"></i>${ month-0 }월 잔여금은 ${ String.format("%,d", (depositByLast3Month.get(0) - withdrawByLast3Month.get(0))) }원입니다.</li>
					                </ul>
						        <br>
					        </div>
					        
					        <div id="chart_div" style="width: 370px; height: 150px"></div>
							<input type="button" value="적금 상품 보러 가기" class="btn-style-one" onclick="goSavings()">
					    </div>
                    </div>
                </div>
            </div>            
            
            <!--  -->
            
        </div>
    </div>

</section>




<!-- 최근 3개월 수입, 지출 추이----------------------------------------------------------------------------------------- -->
<section class="team-section section">
    <div class="container">
        <div class="section-title text-center">
	        <h3>최근 3개월 <span>잔여금 추이</span></h3>
	        <p>
	        	<c:if test="${ depositByLast3Month.get(0) - withdrawByLast3Month.get(0) > depositByLast3Month.get(1) - withdrawByLast3Month.get(1) &&
	        	               depositByLast3Month.get(1) - withdrawByLast3Month.get(1) > depositByLast3Month.get(2) - withdrawByLast3Month.get(2) }">
	        	<span style="color:red">최근 3개월 잔여금이 연속 증가했습니다!</span><br>	               
	        	</c:if>
				<i class="fa fa-caret-right"></i>${ month-2 }월 수입 : ${ String.format("%,d", (depositByLast3Month.get(2)))}원, 지출 : ${ String.format("%,d", (withdrawByLast3Month.get(2)))}원  &nbsp;&nbsp;&nbsp;&nbsp;
				<i class="fa fa-caret-right"></i>${ month-1 }월 수입 : ${ String.format("%,d", (depositByLast3Month.get(1)))}원, 지출 : ${ String.format("%,d", (withdrawByLast3Month.get(1)))}원  &nbsp;&nbsp;&nbsp;&nbsp;
				<i class="fa fa-caret-right"></i>${ month-0 }월 수입 : ${ String.format("%,d", (depositByLast3Month.get(0)))}원, 지출 : ${ String.format("%,d", (withdrawByLast3Month.get(0)))}원  &nbsp;&nbsp;&nbsp;&nbsp;
	        	
				
				
                <ul class="content-list">
                  <li><i class="fa fa-check-circle-o"></i>${ month-2 }월 잔여금은 ${ String.format("%,d", (depositByLast3Month.get(2) - withdrawByLast3Month.get(2))) }원입니다.</li>
                  <li><i class="fa fa-check-circle-o"></i>${ month-1 }월 잔여금은 ${ String.format("%,d", (depositByLast3Month.get(1) - withdrawByLast3Month.get(1))) }원입니다.</li>
                  <li><i class="fa fa-check-circle-o"></i>${ month-0 }월 잔여금은 ${ String.format("%,d", (depositByLast3Month.get(0) - withdrawByLast3Month.get(0))) }원입니다.</li>
                </ul>
	        <br>
	        <input type="button" value="적금 상품 보러 가기" class="btn-style-one" onclick="goSavings()">
        </div>
        
        <div id="chart_div" style="width: 1200px; height: 300px"></div>

    	</div>

</section>


<!--이번 달 카테고리별 지출 퍼센트--------------------------------------------------------------------------------------------->

<section class="team-section section">
    <div class="container">
        <div class="section-title text-center">
            <h3>카테고리별<span>지출 비율</span></h3>
            <p>${ month }월 고객님의 카테고리별 지출 비율을 확인하세요.<br>
           	     지출이 가장 많은 카테고리는 <span style="color:red">${ biggestCategory }</span>입니다.</p>
            <input type="button" value="카테고리별 지출 관리하기" class="btn-style-one" onclick="goChallenge()">
        </div>
        
        <div id="curve_chart" style="width: 1200px; height: 300px"></div>
        <div class="row">
        <c:forEach items="${ amountByTypeList }" var="amount">
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <div class="contents text-center">
                        <h4>${ String.format( "%.2f",(amount.totalAmountByType / amount.totalThisMonth) * 100)  }%</h4>
                        <p>'${ amount.category }'에<br> ${ String.format("%,d", amount.totalAmountByType) }원을 지출하셨습니다.</p>
                        <button class="categoryBtn btn btn-main" id="${ amount.logTypeKey }" >지출 내역 보기</button>
                    </div>
                </div>
            </div>
        </c:forEach>  
        </div>
    	</div>
    	
    	<br>
    	<br>
    	<br>
    	<br>
    	
         <div class="section-title text-center">
            <h3>분석 메일링 서비스</h3>
            <c:if test="${ mailServiceBool == 0 }">
	            <p>회원가입 시 등록한 이메일로 매월 말 분석 내용을 이메일로 제공받으세요!<br></p>
	            <input type="button" value="신청하기" class="btn-style-one" data-toggle="modal" data-target="#addMailServiceModal">
            </c:if>
            <c:if test="${ mailServiceBool == 1 }">
	            <p>분석 내용 이메일 구독 서비스를 취소하실 수 있습니다.<br></p>
	            <input type="button" value="취소하기" class="btn-style-one" data-toggle="modal" data-target="#deleteMailServiceModal">
            </c:if>
        </div>   	
</section>




<!--모달  -->
<!-- 잦은 지출 상세내역 모달-------------------------------------------------------------------------------------------->
<div class="modal fade" id="frequentModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">잦은 지출 내역보기</h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body" id= "modalDiv">
         	
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-style-one" data-dismiss="modal">확인</button>
        </div>
        
      </div>
    </div>
  </div>



<!-- 카테고리별 내역 그래프 모달 --------------------------------------------------------------------------------------->
<div class="modal fade" id="categoryModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">카테고리 일별 지출 내역 </h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body" id= "modalDiv2">
         	
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-style-one" data-dismiss="modal">확인</button>
        </div>
        
      </div>
    </div>
  </div>

<!-- 메일 서비스 구독 모달 ------------------------------------------------------------------------------>
 <div class="modal fade" id="addMailServiceModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">분석내용 이메일 서비스를 신청하시겠습니까?</h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         	 매월 28일에 내역을 분석한 내용이 이메일로 보내집니다.
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-style-one" data-dismiss="modal">돌아가기</button>
          <button type="button" class="btn-style-one" onclick="goAddMailService()">신청하기</button>
        </div>
        
      </div>
    </div>
  </div>

<!-- 메일 서비스 구독 취소 모달 ------------------------------------------------------------------------------>
 <div class="modal fade" id="deleteMailServiceModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">분석내용 이메일 서비스를 취소하시겠습니까?</h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         	 매월 28일에 내역을 분석한 내용이 이메일로 보내지던 서비스가 종료됩니다.
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-style-one" data-dismiss="modal">돌아가기</button>
          <button type="button" class="btn-style-one" onclick="goDeleteMailService()">최소하기</button>
        </div>
        
      </div>
    </div>
  </div>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>