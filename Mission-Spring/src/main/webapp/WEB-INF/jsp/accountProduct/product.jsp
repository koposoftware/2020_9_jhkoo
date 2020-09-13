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
            <h3>예금/적금
                <span>상품 가입</span>
            </h3>
            <p>개설할 상품의 종류를 선택하세요</p>
        </div>
        <div class="row items-container clearfix">
           
            <div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='${ pageContext.request.contextPath }/product/depositFreeChoose'">
                        <span>Checking account</span>
                        
                            <h6>입출금 자유 예금</h6>
                        
                        <p>입출금 자유 예금은 대표적인 요구불예금으로서 가입대상, 예치금액, 예치기간, 입출금 횟수 등에 아무런 제한 없이 자유롭게 거래할 수 있는 상품입니다.</p>
                    </div>
                </div>
            </div>
            
            <div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='${ pageContext.request.contextPath }/product/savingsChoose'">
                        <span>InstallMent saving</span>
                       
                            <h6>정기 적금</h6>
                        
                        <p>정기적금은 매월 일정금액을 정기적으로 납입하고 만기일에 원리금을 지급받는 상품으로 푼돈을 모아 목돈을 마련하는데 가장 보편적인 장기저축 상품입니다.</p>
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