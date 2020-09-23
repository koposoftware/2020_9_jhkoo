<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/include/header.jsp" />



<section class="appoinment-section section">
    <div class="container">
        <div class="row">
            

	    <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="contact-area">
                
	    <div class="section-title">
	        <h3>도전 금액
	            <span>을 설정하세요</span>
	        </h3>
	    </div>


<form class="default-form contact-form" method="post"
          action="${ pageContext.request.contextPath }/challengeProcess">

		<c:if test='${ challengeName.substring(0,2).equals("식비") }' >
			<input type="hidden" name="challengeType" value="3" />		
		</c:if>
		<c:if test='${ challengeName.substring(0,2).equals("카페") }' >
			<input type="hidden" name="challengeType" value="4" />		
		</c:if>
		<c:if test='${ challengeName.substring(0,3).equals("편의점") }' >
			<input type="hidden" name="challengeType" value="5" />		
		</c:if><c:if test='${ challengeName.substring(0,1).equals("술") }' >
			<input type="hidden" name="challengeType" value="6" />		
		</c:if>
		<c:if test='${ challengeName.substring(0,2).equals("쇼핑") }' >
			<input type="hidden" name="challengeType" value="7" />		
		</c:if>
		<c:if test='${ challengeName.substring(0,3).equals("미용") }' >
			<input type="hidden" name="challengeType" value="9" />		
		</c:if>	

	    
        <div class="row">
            
         
            <div class="col-md-12 col-sm-12 col-xs-12">
            	<div class="form-group">
            		<input type="hidden" name="challengeName" value="${ challengeName }" />
                    <input type="text" name="targetAmount" class="form-control" placeholder="도전할 금액을 입력하세요"/><span></span>
                </div>
            </div>

            <div class="col-md-12 col-sm-12 col-xs-12">         
                <div class="form-group text-center">
                    <button type="submit" class="btn-style-one">도전 시작하기</button>
                </div>
            </div>
        </div>
    </form>




   </div>                        
  </div>
            
      <div class="col-md-6 col-sm-12 col-xs-12">
        <div class="accordion-section">
		    <div class="section-title">
		        <h3>주의사항</h3>
		        <br>
		        <br>
		        <br>
		        <h4>지난 달 ${ challengeName.substring(0, challengeName.length()-3) } 총 소비액은<br>
		           <span style="color:red">${ String.format("%,d",lastMonthSumByCatesgory) }</span>원입니다.<br>
		           <br>해당 카테고리의 지난 달 지출액을 확인하고<br> 신중히 결정하세요!
		        </h4>
		    </div>
		</div>
      </div>      
            
            
   </div>                    
</div>
</section>

<br>
<br>
<br>
<br>
<br>
	
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>