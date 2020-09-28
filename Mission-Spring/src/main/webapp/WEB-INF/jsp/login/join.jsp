<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script>

function doIdCheck(){
	let id = document.lform.id.value;
	
	$.ajax({
		url : '${pageContext.request.contextPath}/idCheck/' + id,
		type : 'get',
		
		success : function(data){

			let idCheckBool = JSON.parse(data); 
			let str = '';
			if(idCheckBool == true){
				str = "사용 가능합니다"
			} else {
				str = "중복입니다"
			}
			
			$('#idCheck').text(str);
		}
	})
}

function doJoin(){
	let idCheck = document.getElementById("idCheck");

	if(idCheck.innerHTML == "중복입니다"){
		alert("아이디가 중복되어 회원가입이 불가능합니다")
		return false;
	}
	return true;
}



</script>
<style>
	.error {
		color:red;
	}
</style>
<script src="${pageContext.request.contextPath }/resources/assets/js/checkData.js"></script>

</head>
<body>



<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 



<section class="appoinment-section section">
    <div class="container">
        <div class="row">
            

	    <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="contact-area">
                
	    <div class="section-title">
	        <h3>회원가입
	            <span>해주세요</span>
	        </h3>
	    </div>


	<!-- 회원가입 form -->    
	<!-- Spring form태그 이용. -->
	<!-- 보안적인 측면에서 유효성 검사란 올바르지 않은 데이터가 서버로 전송되거나, DB에 저장되지 않도록 하는 것 -->
    <form:form commandName="memberVO" class="default-form contact-form"  method="post" name="lform">
        
        <div class="row">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="form-group">
                    <form:input type="text" path="id" placeholder="아이디" />
                    <form:errors path="id" class="error" />
                    
                </div>                         
               
               	<div class="form-group">
                    <form:input type="password" name="password" path="password" placeholder="비밀번호" />
                    <form:errors path="password"  class="error" />
                </div>
            </div>
            
            <div class="col-md-6 col-sm-12 col-xs-12">
               	<div class="form-group text-center">
                    <button type="submit" class="btn-style-one" onclick="doIdCheck()">중복확인</button>
                 	 <span id="idCheck" style="color:red"></span>
                    
                </div>
               	<div class="form-group">
                    <form:input type="text" path="name" placeholder="이름" />
                </div>
            </div>
            
            
            
            
            <div class="col-md-12 col-sm-12 col-xs-12">
            	<div class="form-group">
                    <form:input type="text" path="phone" placeholder="핸드폰 번호   ex)010-0000-0000" />
                    <form:errors path="phone"  class="error" />
                </div>
                <div class="form-group">
                    <form:input type="text" path="email" placeholder="이메일   ex)xxx@domain.com" />
                    <form:errors path="email"  class="error" />
                </div>
                <div class="form-group">
                    <form:input type="text" path="address" placeholder="주소   ex)서울시 마포구 상암동 " />
                
                </div>
            </div>
            
            
            <div class="col-md-6 col-sm-12 col-xs-12">
               	<div class="form-group">
                    <form:select path="ageGroup">
                    	<option value="">연령대</option>
                    	<option value="10대">10대</option>
                    	<option value="20대">20대</option>
                    	<option value="30대">30대</option>
                    	<option value="40대">40대</option>
                    	<option value="50대">50대</option>
                    	<option value="60대">60대</option>
                    	<option value="70대">70대</option>
                    	<option value="80대">80대</option>
                    	<option value="90대">90대</option>
                    </form:select>
                    <form:errors path="ageGroup"  class="error" />
                </div>
            </div>
            <div class="col-md-6 col-sm-12 col-xs-12">
               	<div class="form-group">
                    <form:select path="gender">
                    	<option value="">성별</option>
                    	<option value="M">남</option>
                    	<option value="F">여</option>
                    </form:select>
                    <form:errors path="gender"  class="error" />
                </div>
            </div>
            
            <div class="col-md-6 col-sm-12 col-xs-12">
            	<div class="form-group">
                    <form:select path="propertyStatus" >                    
                        <option value="">보유 재산 상황</option>
                        <option value="5천만원 미만">5천만원 미만</option>
                        <option value="1억원 미만">1억원 미만</option>
                        <option value="5억원 미만">5억원 미만</option>
                        <option value="10억원 미만">10억원 미만</option>
                        <option value="10억원 이상">10억원 이상</option>
                    </form:select>
                    <form:errors path="propertyStatus"  class="error" />
                </div>
            </div>
            
            <div class="col-md-6 col-sm-12 col-xs-12">
            	<div class="form-group">
                    <form:select path="jobKey" >                    
                        <option value="">직업 분류</option>
                        <option value="1">IT직</option>
                        <option value="2">주부</option>
                        <option value="3">자영업자</option>
                        <option value="4">금융직</option>
                        <option value="5">판매직</option>
                    </form:select>
                    <form:errors path="jobKey"  class="error" />
                </div>
            </div>
             
            <div class="col-md-12 col-sm-12 col-xs-12">         
                <div class="form-group text-center">
                    <button type="submit" class="btn-style-one">가입하기</button>
                </div>
            </div>
        </div>
    </form:form>

   </div>                        
  </div>
            
      <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="accordion-section">
    <div class="section-title">
        <h3>반갑습니다!</h3>
    </div>
    <br>
    <br>
    <img src="${pageContext.request.contextPath }/resources/assets/images/회원가입.jpg">
</div>
            </div>      
            
            
        </div>                    
    </div>
</section>


<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 







</body>
</html>