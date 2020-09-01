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
            <h3>입출금 계좌를 선택해주세요</h3>
            <p>입출금 계좌를 선택해주세요</p>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/하나플러스통장.jfif" class="img-responsive">
                    <div class="contents text-center">
                        <h4>하나 플러스 통장</h4>
                        <p>우대 금리 받고 수수료는 무제한 면제 되는 통장</p>
                        <a href="${pageContext.request.contextPath }/depositBankBookAdd" class="btn btn-main">개설하기</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/급여하나통장.jpg" alt="doctor" class="img-responsive">
                    <div class="contents text-center">
                        <h4>급여 하나 통장</h4>
                        <p>급여 하나면 된다! 직장인 월급 통장</p>
                        <a href="${pageContext.request.contextPath }/depositBankBookAdd" class="btn btn-main">개설하기</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath }/resources/assets/images/영하나플러스통장.jpg" alt="doctor" class="img-responsive">
                    <div class="contents text-center">
                        <h4>영하나 플러스 통장</h4>
                        <p>젊은 그대, 당신을 위한 Must Have통장</p>
                        <a href="${pageContext.request.contextPath }/depositBankBookAdd" class="btn btn-main">개설하기</a>
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