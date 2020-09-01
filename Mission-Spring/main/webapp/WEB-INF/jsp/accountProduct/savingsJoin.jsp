<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<style>
	.error {
		color:red;
	}
</style>
</head>
<body>


<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 

      
<section class="appoinment-section section">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="accordion-section">
    <div class="section-title">
        <h3>상품 정보</h3>
    </div>
    <div class="accordion-holder">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            Why Should I choose Medical Health
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute,
                        non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
                        sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh
                        helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                        vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't
                        heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                            aria-controls="collapseTwo">
                            What are the Centre’s visiting hours?
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute,
                        non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
                        sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh
                        helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                        vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't
                        heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false"
                            aria-controls="collapseThree">
                            How many visitors are allowed?
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute,
                        non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
                        sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh
                        helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                        vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't
                        heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
            </div>
            
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="contact-area">
    


<!-- form 태그 -->
<form:form commandName="savingsAccountVO" method="post">
        <div class="row">
            
            
            
            <div class="col-md-12 col-sm-12 col-xs-12">
               
                <form:hidden path="id" value="${ loginVO.id }"  /> 	
                <form:hidden path="bankBookKey" value="3"  />
                <form:hidden path="rate" value="1.5"  /> 
                

                <div class="form-group">
                	<label>비밀번호 &nbsp;</label> <form:errors path="accountPassword" class="error" /> 
                    <form:password path="accountPassword" class="form-control" placeholder="비밀번호 4자리를 설정하세요"/>
                    
                </div>
                
                <div class="form-group">
                	<label>신규 가입 금액 &nbsp;</label> <form:errors path="balance" class="error" />
                	<form:input path="balance" class="form-control" />
                	
                </div>
                
                      
                <div class="form-group">
                	<label>가입 기간</label> 
                	<form:select path="savingMonth">
                		<form:option value="12">1년</form:option>
                      	<form:option value="24">2년</form:option>
                      	<form:option value="36">3년</form:option>
                	</form:select>
                </div>
                
                <div class="form-group">
                	<label>납부 일</label> 
                	<form:select path="savingDay" class="form-control">
                      	<form:option value="1">매월 1일</form:option>
                      	<form:option value="15">매월 15일</form:option>
                      	<form:option value="25">매월 28일</form:option>
                    </form:select>
                </div>
                
                <div class="form-group">
                	<label>이자 지급 방법</label> 
                    <form:input path="" class="form-control" value="만기 일시 지급식" readonly="true"/> 
                </div>
                
                
                <div class="form-group">
                	<label>적금 닉네임 &nbsp;</label> <form:errors path="nickName" class="error" />
                    <form:input path="nickName" class="form-control" placeholder="닉네임을 입력하세요"/> 
                    
                </div>
                
                
                <div class="form-group">
                	<label>출금 계좌</label> 
                	<form:select path="autoSaving" class="form-control"> 
                   	  <form:options items="${ depositAccountNumList }" ></form:options>                      	
                   </form:select>
                </div>
                
                
                <div class="form-group text-center">
                    <button type="submit" class="btn-style-one">상품 가입</button>
                </div>

            </div>
            
            
        </div>
    </form:form>
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