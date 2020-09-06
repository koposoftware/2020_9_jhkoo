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



<section class="team-section section">
    <div class="container">
        <div class="section-title text-center">
            <h3>입출금이 자유로운 예금 상품을 선택해주세요</h3>
            <p>입출금이 자유로운 예금 상품을 선택해주세요</p>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/1.png" alt="doctor" class="img-responsive">
                    <div class="contents text-center">
                        <h4>영하나 플러스 통장</h4>
                        <p>젊은 그대, 당신을 위한 Must Have 통장</p>
                        <a href="${pageContext.request.contextPath }/product/depositFreeExplain2" class="btn btn-main">개설하기</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/2.png" alt="doctor" class="img-responsive">
                    <div class="contents text-center">
                        <h4>하나 플러스 통장</h4>
                        <p>우대 금리 받고, 수수료는 무제한 면제되는 통장</p>
                        <a href="${pageContext.request.contextPath }/product/depositFreeExplain1" class="btn btn-main">개설하기</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/3.png" alt="doctor" class="img-responsive">
                    <div class="contents text-center">
                        <h4>주거래하나 통장</h4>
                        <p>수수료면제서비스가 제공되는 생활비 통장</p>
                        <a href="${pageContext.request.contextPath }/product/depositFreeExplain3" class="btn btn-main">개설하기</a>
                    </div>
                </div>
            </div>
            
            
             
            
        </div>
    </div>
</section>









<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />

</body>
</html>