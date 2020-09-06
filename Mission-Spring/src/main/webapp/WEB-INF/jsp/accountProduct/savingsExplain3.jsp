<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function goJoin(){
		location.href = "${pageContext.request.contextPath }/product/savingsJoin/" + "3"; 
	}
</script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" />


<!-- Contact Section -->



<section class="appoinment-section section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="accordion-section">
    <div class="section-title">
        <h3>꿈하나 적금 가입하기</h3>
    </div>
    
    
    
    <div class="accordion-holder">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            	최대금리 받는 방법
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
			                         아래 항목을 충족한 경우, 최대 연0.8%의 우대금리를 제공<br>

						가입우대 : 이 예금을 최초 가입한 경우 연 0.2%<br>
						(해지후 재가입하는 경우 제공하지 않음)<br>
						 청약우대 : 가입일(또는 재예치일)에 주택청약종합저축을 보유한 경우 연 0.4%<br>
						자동이체우대 : 계약기간중 (주)하나은행 입출금통장을 통하여 6회이상 월 자동이체로 적립한 경우 연 0.2%<br>
						 카드우대 : 이 예금 가입후(또는 재예치 후)3개월이 되는 달의 말일까지 (주)하나은행 본인명의 입출금통장에서<br>
						하나(현대)카드사의 체크카드 결제실적을 보유한 경우 연0.2%<br>
						 아동수당우대 : 가입후(또는 재예치후) 만기전전월말 기준, ㈜하나은행 계좌로 아동수당법에서 정한 아동수당을 1회이상<br>
						수령한 경우 연 0.2%<br>
						(단, 보호자계좌로 수령시 수급계좌 정보를 만기(또는 재예치)전일까지 ㈜하나은행에 제공한 경우에 한함)<br>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                            aria-controls="collapseTwo">
                            	상품특징
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
			                        하나(현대)카드사의 체크카드 결제실적을 보유한 경우 연0.2%<br>
						 아동수당우대 : 가입후(또는 재예치후) 만기전전월말 기준, ㈜하나은행 계좌로 아동수당법에서 정한 아동수당을 1회이상<br>
						수령한 경우 연 0.2%<br>
						(단, 보호자계좌로 수령시 수급계좌 정보를 만기(또는 재예치)전일까지 ㈜하나은행에 제공한 경우에 한함)<br>       
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                            aria-controls="collapseTwo">
                            	가입대상
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                       	 이 예금 가입일에 ㈜하나은행의 주택청약종합저축을 신규 가입한 실명의 개인 또는 개인사업자 (1인 1계좌)
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false"
                            aria-controls="collapseThree">
                            	특별 금리
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                     	   아래 요건을 충족하는 경우 동일 계약기간별 최대 연0.3%<br>
						- 출생 후 1년이내, 만7세, 만13세, 만16세가 되는 해당 계약기간별 1년간 또는 동일기관, 학교 등 단체신규<br>
						10인 이상 가입한 경우 가입 후1년간 최대 연0.3% (단체신규는 동일 영업점에서 확인하고 신규하는 경우 제공)<br>
						
						희망대학입학 축하금리 : 만기 전 1년간 연2.0% 우대 (만 14세까지 희망대학 등록 가능)<br>
						※우대금리 및 특별금리는 만기해지시 최종 적용되며, 중도해지시는 적용되지 않습니다.<br>
                    </div>
                </div>
            </div>
            
            
            
        </div>
    </div>
    
    		
            	<div class="col-md-12 col-sm-12 col-xs-12">         
            	    <div class="form-group text-center">
                    	<button onclick="goJoin()" class="btn-style-one">가입하기</button>
             	   </div>
            	</div>
            
			</div>
            
            </div>
        </div>                    
    </div>
</section>
<!-- End Contact Section -->


<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />

</body>
</html>