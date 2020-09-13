<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function depositFreeJoin(){
		location.href = "${ pageContext.request.contextPath}/product/depositFreeJoin/" + 3;
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
        <h3>주거래하나 통장 가입하기</h3>
    </div>
    
    
    
    <div class="accordion-holder">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            	주거래하나 통장 특징
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        1. 요즘은 똑똑한 통장 하나로 생활비 관리 하나봐! 누구나 가입하는 통장<br>
                        2. 요즘은 이체 실적 하나로 수수료 우대 하나봐! 수수료 우대 무제한<br>
                        3. 요즘은 이체 실적 두개로 수수료 더 우대 하나봐! 수수료 우대 무제한 + 수수료 우래 월 10회<br>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                            aria-controls="collapseTwo">
                            	주거래하나 통장 정보
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                         	가입대상   :  실명의 개인 또는 개인사업자 (1인 1계좌)<br>
                         	예금 종류  :  저축예금<br>
                         	전환여부   :  저출예금에 한하여 동 상품으로 전환가능<br>
                         	(단, 기존 수수료면제통장에서 전환 시 수수료면제횟수는 전환월은 기존상품의 잔여횟수를 적용하고, 다음달부터는 동 상품의
								실적에 따라 수수료면제횟수 적용)
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                            aria-controls="collapseTwo">
                            	전환여부
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                       	저축예금에 한하여 동 상품으로 전환가능<br>
							(단, 기존 수수료면제통장에서 전환 시 수수료면제횟수는 전환월은 기존상품의 잔여횟수를 적용하고, 다음달부터는 동 상품의
								실적에 따라 수수료면제횟수 적용)
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false"
                            aria-controls="collapseThree">
                            	유의사항
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        	수수료우대서비스는 이 통장에 의한 거래에만 적용되며, 매월 요건 충족여부를 확인하여 다음월에 수수료우대 서비스를 제공합니다.<br>
                      	          우대서비스 내용 및 대상 요건은 변경 가능하며, 변경 시 재고시 합니다.
                    </div>
                </div>
            </div>
            
            
            
        </div>
    </div>
    
    		
            	<div class="col-md-12 col-sm-12 col-xs-12">         
            	    <div class="form-group text-center">
                    	<button onclick="depositFreeJoin()" class="btn-style-one">가입하기</button>
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