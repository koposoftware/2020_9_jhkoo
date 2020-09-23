<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	 function goFavoriteList(){
		 location.href = "${ pageContext.request.contextPath}/favoriteList";
	 }
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />



<section class="service-section bg-gray section">
    <div class="container">
        <div class="section-title text-center">
            <h3>계좌이체
                <span>하기</span>
            </h3>
            <p>계좌이체할 통장을 선택하세요</p>
        </div>
        <div class="row items-container clearfix">
        
        
        <c:forEach items="${ depositAccountList }" var="account">
        	<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center" onclick="location.href='${pageContext.request.contextPath }/transfer/${ account.getAccountNumber() }'" >
                        <h6>${ account.getBankBookKey() }</h6>
                        <p>    번호 : ${ account.getAccountNumber() }<br>
                        	      잔액 : ${ String.format("%,d", account.getBalance()) }원 <br>
                                                             별칭 : ${ account.getNickName() }</p>
                    </div>

                </div>
            </div>
        </c:forEach> 

           
        </div>
    </div>
<div class="col-md-12 col-sm-12 col-xs-12">         
    <div class="form-group text-center">
        <button onclick="goFavoriteList()" class="btn-style-one">즐겨찾는 계좌 목록 관리</button>
    </div>
</div>
</section>



<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>