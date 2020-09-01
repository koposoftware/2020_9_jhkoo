<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />




<div class="container" align="center">
  
<div class="section-title">
    <h3>계좌
        <span>상세 정보입니다.</span>
     </h3>
</div> 
  
     
 <form action="${ pageContext.request.contextPath }/depositManageChange" method="post">
  <input type="hidden" name="accountNumber" value="${depositAccount.getAccountNumber()}">
  <table class="table table-hover" style="width:50%;">
    <thead>
      
      <tr>
        <th>통장 이름</th>
        <th>${ depositAccount.getBankBookKey()}</th>
      </tr>
    </thead>
    <tbody>
    	<tr>
    		<td>계좌 번호</td>
    		<td>${ depositAccount.getAccountNumber()}</td>
    	</tr>
    	<tr>
    		<td>개설 일자</td>
    		<td>${ depositAccount.getRegDate()}</td>
    	</tr>
    	<tr>
    		<td>보유 잔액</td>
    		<td>${ String.format("%,d", depositAccount.getBalance()) }원</td>
    	</tr>
    	<tr>
    		<td>상태</td>
    		
    		<c:if test="${ depositAccount.getMainAccount() == 'Y'}">
	    		<td>
	       			<span style="color:blue;">현재 메인 계좌입니다.</span>
	    			<input type="radio" name="mainAccount" value="Y" checked="checked" /> 메인 계좌
					<input type="radio" name="mainAccount" value="N" /> 서브계좌
	       		</td>
       		</c:if>
       		<c:if test="${ depositAccount.getMainAccount() == 'N'}">
	       		<td>
	       			<span style="color:green;">현재 서브 계좌입니다.</span>
	    			<input type="radio" name="mainAccount" value="Y" /> 메인 계좌
					<input type="radio" name="mainAccount" value="N" checked="checked" /> 서브계좌
	       		</td>
       		</c:if>
       		
    	</tr>
    	<tr>
    		<td>별칭</td>
    		<td>
    			<input name="nickName" placeholder="${ depositAccount.getNickName()}">
    		</td>
    	</tr>
    </tbody>
  </table>
  
  <button type="submit" class="btn-style-one">수정</button>
  <button onclick="${ pageContext.request.contextPath }/${ depositAccount.getAccountNumber()}">취소</button>
  
</form>         
</div>






<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>