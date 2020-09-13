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


<!--team section-->
<section class="team-section section">
    <div class="container">
        <div class="section-title text-center">
            <h3>적금 상품을 선택해주세요</h3>
            <p>적금 상품을 선택해주세요</p>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/1.png" alt="doctor" class="img-responsive">
                    <div class="contents text-center">
                        <h4>하나 원큐 적금</h4>
                        <p>간편하게 우대금리를 제공하는 스마트폰적금</p>
                        <a href="${pageContext.request.contextPath }/product/savingsExplain1" class="btn btn-main">개설하기</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/2.png" alt="doctor" class="img-responsive">
                    <div class="contents text-center">
                        <h4>내집마련 적금</h4>
                        <p>내집마련의 꿈을 하나은행이 응원합니다</p>
                        <a href="${pageContext.request.contextPath }/product/savingsExplain2" class="btn btn-main">개설하기</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/3.png" alt="doctor" class="img-responsive">
                    <div class="contents text-center">
                        <h4>꿈하나 적금</h4>
                        <p>보물단지 우리아이를 위한 적금</p>
                        <a href="${pageContext.request.contextPath }/product/savingsExplain3" class="btn btn-main">개설하기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--End team section-->








<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />

</body>
</html>