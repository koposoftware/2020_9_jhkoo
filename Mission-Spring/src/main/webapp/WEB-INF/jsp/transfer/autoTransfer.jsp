<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script>
	
	$(document).ready(function(){
		/* 즐겨찾는 계좌 클릭 시 바로 계좌번호 입력-------------------------------------- */
		$('.favoriteBtn').click(function(){
			let btn = this;
			let element = $(this).attr('id');

			$('#transferAccountNumber').val(element);	
		})
		
		
		$('.addMoneyBtn').click(function(){
			let btn = this;
			let addAmount = Number( $(this).attr('id') ) ;	/* Number() string을 int로 해주는 jquery 함수 */
			let originAmount = Number( $('#toAmount').val() );
			
			$('#toAmount').val(addAmount + originAmount);
		})
	})
	
	
	function goAutoTransferToDeposit(){
		location.href = "${ pageContext.request.contextPath}/" + ${depositAccount.getAccountNumber()}
	}
	
</script>
</head>
<body>


<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 


      
<section class="appoinment-section section">
    <div class="container">
        <div class="row">
        
<div class="col-md-6 col-sm-12 col-xs-12">
   <div class="accordion-section">
    <div class="section-title">
        <br><br>
        <h3>정기 예약 이체 서비스</h3> 
    </div>
    <div class="accordion-holder">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            	주의사항
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        	본인 이외에 서비스 사용을 금지합니다.
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                            aria-controls="collapseTwo">
                            	정기 예약 이체 설명
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
                            	서비스 상세 설명
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
</div>
            
<div class="col-md-6 col-sm-12 col-xs-12">
    <div class="contact-area">
    
	


	<!-- form 태그. 백엔드 체크. 중요 정보인 상대 계좌 번호르 모달 띄워 상기 시킴 -->
	<form:form commandName="transferVO" method="post" onsubmit="doModal()">
	        <div class="row">
	            
	            <div class="section-title">
	   				 <h3>${ depositAccount.getBankBookKey() }</h3>
	   				 <p>&nbsp;&nbsp;&nbsp;하나은행 계좌 ${ depositAccount.getAccountNumber() }  
	   				 &nbsp;&nbsp;&nbsp;잔액 :  ${ String.format("%,d", depositAccount.getBalance()) }원</p>
					 
				</div>
	            <br>
	            
	            <div class="col-md-12 col-sm-12 col-xs-12">
	            	<!-- 계좌번호와, 타입(이체)는 입력받지 않으므로 hidden으로 넘김 -->
	            	<form:hidden path="accountNumber" value="${ depositAccount.getAccountNumber() }"/>	
	                <form:hidden path="toType" value="2"  />
	                 
	                 
	                <div class="form-group">
	                	<label>예약 일</label> 
                    	<form:input path="autoTransDay" class="form-control"  placeholder="숫자(일)만 입력하세요 "/>
               		 </div> 
	                   
					
	
	                <div class="form-group">
	                	<label>계좌 비밀번호 &nbsp;</label> 
	                	<c:if test="${ depositAccount.getAccountPassword() != '1234' }">
	                		<form:errors path="accountPassword" class="error" /> 
	                	</c:if>
	                    <form:password path="accountPassword" class="form-control" placeholder="계좌 비밀번호 4자리를 입력하세요"/> 
	                </div>
	                
	                <div class="form-group">
	                	<label>이체 보낼 계좌번호 </label> <form:errors path="toAccountNumber" class="error" />
	                	<form:input path="toAccountNumber" class="form-control" placeholder="'-'을 포함하여 입력하세요"/>
	                </div>	                
	                
	                <div class="form-group">                	
	                	<div class="dropdown">
	  						<button class="btn btn-secondary dropdown-toggle btn-style-one" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	   							 즐겨찾기 목록에서 계좌를 선택하세요
	  						</button>
							  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							    
							  <c:forEach items="${ favoriteList }" var = "favorite">
								<button type="button" class="btn btn-style-one favoriteBtn" id="${ favorite.toAccountNumber }">
									${ favorite.toAccountNumber }(${ favorite.toName })</button>						    
							  </c:forEach>
							    
							  </div>
  						</div>
						
	                </div>
	                
	                
	                <div class="form-group">
	                	<label>이체 금액 &nbsp;</label> <form:errors path="toAmount" class="error" />
	                	<form:input id = "toAmount" path="toAmount" class="form-control"  placeholder="0원 "/>
	                </div>  
	                
	       			<div class="form-group">
	       				<div class="dropdown">
	  						<button class="btn btn-secondary dropdown-toggle btn-style-one" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	   							 간편 이체금액 선택
	  						</button>
							  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							    <button type="button" class="btn btn-style-one addMoneyBtn" id="100000" >+10만원</button>
			                	<button type="button" class="btn btn-style-one addMoneyBtn" id="70000" >+7만원</button>	
			                	<button type="button" class="btn btn-style-one addMoneyBtn" id="50000" >+5만원</button>
			                	<button type="button" class="btn btn-style-one addMoneyBtn" id="30000" >+3만원</button>
			                	<button type="button" class="btn btn-style-one addMoneyBtn" id="10000" >+1만원</button>		    
							  </div>
  						</div>
	       			
	       				
	                </div>
	                    
					<div class="form-group">
						<label> &nbsp;</label> 
					</div>
	                
	                <div class="form-group text-center">
	                    <button type="submit" class="btn-style-one" >이체하기</button>
	                </div>   
	
	            </div>
	            
	            
	        </div>
	    </form:form>
	</div>                        
</div>

        </div>                    
    </div>
</section>








	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 


</body>
</html>