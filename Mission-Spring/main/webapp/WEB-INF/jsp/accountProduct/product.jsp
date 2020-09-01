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
            <h3>계좌
                <span>개설하기</span>
            </h3>
            <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Lorem ipsum dolor sit amet. qui suscipit atque <br>
                fugiat officia corporis rerum eaque neque totam animi, sapiente culpa. Architecto!</p>
        </div>
        <div class="row items-container clearfix">
        
        	<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='#'">
                        <span>Better Service At Low Cost</span>
                       
                            <h6>정기 예금</h6>
                        
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Suscipit, vero.</p>
                    </div>
                </div>
            </div>
        
            <div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='${ pageContext.request.contextPath }/product/depositFreeChoose'">
                        <span>Better Service At Low Cost</span>
                        
                            <h6>입출금 자유 예금</h6>
                        
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Suscipit, vero.</p>
                    </div>
                </div>
            </div>
            
            <div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='${ pageContext.request.contextPath }/product/savingsChoose'">
                        <span>Better Service At Low Cost</span>
                       
                            <h6>적금</h6>
                        
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Suscipit, vero.</p>
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