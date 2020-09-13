<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />


<!-- Contact Section -->
<section class="blog-section section style-three pb-0">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="contact-area style-two">
                    <div class="section-title">
                        <h3>친구에게 <span>도전 <br> 공유를 요청하세요</span></h3>
                    </div>
                    <form:form commandName="friendVO" method="post" class="default-form contact-form">
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                            	<div class="form-group">
                            		<form:hidden path="id" value="${ loginVO.id }" />
                                    <h4>보내는 이 : ${ loginVO.id }</h4>
                                </div>
                                                           
                            </div>
                            
                            <div class="col-md-12 col-sm-12 col-xs-12">
                            	<div class="form-group">
                            		<form:input path="friendId" placeholder="친구 아이디를 입력하세요"/>
                            	</div>
                            </div>
                            
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="form-group">
                                    <form:textarea path="content" placeholder="친구에게 보낼 한 마디" />
                                </div>
                                <div class="form-group text-center">
                                    <button type="submit" class="btn-style-one">요청 보내기</button>
                                    
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