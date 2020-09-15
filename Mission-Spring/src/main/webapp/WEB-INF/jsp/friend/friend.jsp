<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />



<!--Service Section-->
<section class="service-section bg-gray section">
    <div class="container">
        <div class="section-title text-center">
            <h3>친구
                <span>와 도전 공유하기 </span>
            </h3>
            <p style="color:red">친구들과 자신의 도전을 공유하세요. 목표는 공유할수록 성공 확률이 높아집니다! </p>
        </div>
        <div class="row items-container clearfix">
           
            <div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='${ pageContext.request.contextPath }/friend/sendRequest'">
                        <h6>요청 보내기</h6>
                        <p>도전을 공유하고 싶은 친구에게 <br> 요청을 보내세요</p>
                    </div>
                </div>
            </div>
            
            <div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='${ pageContext.request.contextPath }/friend/requestManage'">
						<h6>요청 관리하기</h6>
                        <p>자신이 보낸 요청과 받은 요청을 <br> 관리하세요</p>
                    </div>
                </div>
            </div>
            
            <div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='${ pageContext.request.contextPath }/friend/compare'">
						<h6>공유된 도전 보기</h6>
                        <p>친구들이 도전에 성공할 수 있게<br> 모니터링하세요</p>
                    </div>
                </div>
            </div>
           
        </div>
    </div>
</section>
<!--End Service Section--> 



<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>