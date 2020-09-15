<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' + this.sumAmount +'원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
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

	/* 그래프 **************************************************************************************************************** */
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
                                <c:if test='${ !account.toName.equals(loginVO.name) && account.count >=3 }'>
                                
	                                <li>
	                                    <i class="fa fa-caret-right"></i>
	                                    '${ account.toName }'에서 총  ${ account.count }번의 지출했습니다.<br>
	                                </li>
                                </c:if>
                                </c:forEach>
                            </ul>
                            
                            <c:forEach items="${ frequentExpenditureList}" var="account">
                            <c:if test='${ !account.toName.equals(loginVO.name) && account.count >=3 }'>
                                <input type="button" value="내역확인" class="frequentDetailBtn btn-style-one" id="${ account.accountNumber }.${ account.toName }"> 
                            </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<!--이번 달 카테고리별 지출 퍼센트--------------------------------------------------------------------------------------------->

<section class="team-section section">
    <div class="container">
        <div class="section-title text-center">
            <h3>카테고리별
                <span>지출 비율</span>
            </h3>
            <p>${ month }월 고객님의 카테고리별 지출 비율을 확인하세요.<br>
           	     지출이 가장 많은 카테고리는 <span style="color:red">${ biggestCategory }</span>입니다. 
            </p>
            
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
</section>




<!--모달!!  -->
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



<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>