<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	function category(param){
		location.href = "${ pageContext.request.contextPath}/categoryDetail/${depositAccount.getAccountNumber()}/${month}/" + param;
	}
	
	function goMonthAgo(){
		location.href = "${ pageContext.request.contextPath}/goMonthAgo/${depositAccount.getAccountNumber()}/${month}";
	}
	
	function goMonthAhead(){
		location.href = "${ pageContext.request.contextPath}/goMonthAhead/${depositAccount.getAccountNumber()}/${month}";
	}

</script>

<style>
	tr.even {	
		background-color: #F7F9FC;	
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />


<div class="container">
  <h2>&nbsp;</h2>
  <div class="section-title text-center">
            <h3>${ depositAccount.getBankBookKey() }
                <span>상세 내역입니다</span>
            </h3>
            <p>총 잔액 : ${ String.format( "%,d",depositAccount.getBalance() ) }원<br>
                              계좌 번호 : ${ depositAccount.getAccountNumber() }<br>
               <c:if test="${ depositAccount.getMainAccount() == 'Y' }">
               		<span style="color:blue">대표 통장</span>
               </c:if>
            	    
            </p>
  </div>
  
  
  
  
<div class="container">
  <h3>${ month }월 내역 조회중입니다</h3>
  <div class="btn-group">
  	<button type="button" class="btn btn-style-one" onclick="goMonthAgo()">${ month - 1 }월 내역 보기</button>
    <button type="button" class="btn btn-style-one" onclick="goMonthAhead()">${ month + 1 }월 내역 보기</button>
   
    <button type="button" class="btn btn-style-one" onclick="category('1')">입금</button>
    <button type="button" class="btn btn-style-one" onclick="category('2')">이체</button>
    <button type="button" class="btn btn-style-one" onclick="category('3')">식비</button>
    <button type="button" class="btn btn-style-one" onclick="category('4')">카페,간식</button>
    <button type="button" class="btn btn-style-one" onclick="category('5')">편의점,마트</button>
    <button type="button" class="btn btn-style-one" onclick="category('6')">술,유흥</button>
    <button type="button" class="btn btn-style-one" onclick="category('7')">쇼핑</button>
    <button type="button" class="btn btn-style-one" onclick="category('8')">취미,여가</button>
    <button type="button" class="btn btn-style-one" onclick="category('9')">미용</button>
    <button type="button" class="btn btn-style-one" onclick="category('10')">주거,통신</button>
    
  
  </div>
</div>
  
<!-- 달력 form -->
      
                       


  
  
  
  <hr>          
  <div id="detailList">
  <table class="table table-hover">
    <thead>
      <tr class="scrollLocation">
        <th>거래 날짜</th>
        <th>거래 대상 </th>
        <th>카테고리</th>
        <th>거래 금액</th>
      </tr>
    </thead>
    <tbody>
    
  
     <c:forEach items="${ depositDetailListMonthAgo }" var="depositDetail" varStatus="loop">
      <tr >
       <td >
       	${ depositDetail.getLogDate() } 
       </td>
       <td>${ depositDetail.getToName() }</td>
       <td>${ depositDetail.getLogTypeKey() }</td>
       <c:choose>
       	<c:when test="${depositDetail.getLogTypeKey() eq '입금'}">
       		<td><span style="color:green;">+ ${ String.format("%,d", depositDetail.getAmount() ) } 원</span></td>
       	</c:when>
       	<c:otherwise>
       		<td><span style="color:red;">- ${ String.format("%,d", depositDetail.getAmount()) } 원</span></td>
       	</c:otherwise>
       </c:choose>

      </tr>
     </c:forEach>

    </tbody>
  </table>
  </div> 
  
</div>





<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>