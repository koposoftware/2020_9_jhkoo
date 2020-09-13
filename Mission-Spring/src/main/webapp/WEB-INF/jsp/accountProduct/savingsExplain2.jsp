<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function goJoin(){
		location.href = "${pageContext.request.contextPath }/product/savingsJoin/" + "2"; 
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
        <h3>내집 마련 적금 가입하기</h3>
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
			                        주택청약종합저축 가입하고<br>
			                        같은 날 내집마련 더블업 적금 가입하면<br>
			                        적금 만기때 까지 청약저축 유지함녀 금리가 두 배!
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
			                        주택청약종합저축을 신규 가입한 날, 단 하루 가입할 수 있는 특별한 적금 ! <br>
			                        적금 만기시 주택청약종합저축을 보유하고 있다면 금리가 두~배 되는 적금 !       
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
                            	내집마련 더블업 적금 가입 사례 
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        1. 주택청약 종합저출을 오늘 가입했어요!<br>
                        2. 주택청약 종합저축에 아직 가입하지 않았어요!<br>
                        3. 주택청약 종합저축을 어제 이미 가입했어요!(불가)<br>
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