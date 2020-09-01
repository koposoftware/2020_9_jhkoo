<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script>

	function goCashManage(){
		location.href = "${ pageContext.request.contextPath }/cashManage";
	}

	function doManage(param){
		location.href = "${ pageContext.request.contextPath }/depositManage/" + param;
	}
	

	
	
	function goDepositDetail(accountNumber){

		 location.href = "${ pageContext.request.contextPath}/depositDetail/" + accountNumber;
	}
	
</script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<!-- 입출금 자유 -->

<div class="container">
  <h2>&nbsp;</h2>
  <p>입출금 계좌 총액  &nbsp; : &nbsp; <span style="color:red">${ String.format("%,d", depositTotalBalance) }</span> &nbsp; 원</p> 
  <hr>           
  <table class="table table-hover">
    <thead>
      <tr>
        <th>통장 이름</th>
        <th>내용 </th>
        <th>주거래 계좌</th>
        <th>잔액</th>
        <th>상세 내역</th>
      </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${ depositAccountList }" var="account">
      <tr>
       <td>
        <a href="javascript:doManage('${ account.getAccountNumber() }')">
        	${ account.getBankBookKey() }
        </a>
       </td>
       
       <td>${ account.getNickName() }</td>
       
       <c:if test="${ account.getMainAccount() == 'Y'}">
       	<td><span style="color:blue;">주거래 계좌</span></td>
       </c:if>
       <c:if test="${ account.getMainAccount() == 'N'}">
       	<td><span>&nbsp;</span></td>
       </c:if>

       <td>${ String.format("%,d", account.getBalance()) }원</td>
       
       <td>
       	<input type="button" value="상세보기" class="btn-style-one" onclick="goDepositDetail('${account.getAccountNumber()}')">
       </td>
       
       </tr>
     </c:forEach>

    </tbody>
  </table>
</div>


<br>
<br>

<!-- 적금 -->

<div class="container">
  <h2>&nbsp;</h2>
  <p>적금 계좌 총액  &nbsp; : &nbsp; <span style="color:red">${ String.format("%,d", savingsTotalBalance) }</span> &nbsp; 원</p>  
  <hr>          
  <table class="table table-hover">
    <thead>
      <tr>
        <th>적금 이름</th>
        <th>내용</th>
        <th>잔액</th>
        <th>자동 이체 계좌</th> 
        <th>상세 내역</th>
      </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${ savingsAccountList }" var="account">
      <tr>
       <td>${ account.getBankBookKey() } </td>
       <td>${ account.getNickName() }</td>
       <td>${ String.format("%,d", account.getBalance()) }원</td>
       
       <c:if test="${ account.getAutoSaving() == null}">
       	<td><input type="button" value="설정" class="btn-style-one"></td>
       </c:if>
       <c:if test="${ account.getAutoSaving() != null}" >
       	<td>${ account.getAutoSaving() }</td>
       </c:if>
       
       <td>
       	<input type="button" value="상세보기" class="btn-style-one" onclick="#">
       </td>
       
       
      </tr>
     </c:forEach>

    </tbody>
  </table>
</div>


<br>
<br>

<!--현금  -->

<div class="container">
	<table class="table table-hover">
    <tbody>
      <tr>
      	<td><input id="cashId" type="text" value='보유 현금 : ${ String.format("%,d", cash) } 원' readonly="readonly" ></input></td>
       <%-- <td>보유 현금  &nbsp; : &nbsp; <span style="color:red" >${ cash }</span> &nbsp; 원 </td> --%>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       
       <td><input type="button" value="현금 내역 관리" class="btn-style-one" onclick="goCashManage()"></td>
    </tbody>
    </table>
</div>







<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />

</body>
</html>