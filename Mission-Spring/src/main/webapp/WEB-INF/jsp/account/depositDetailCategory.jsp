<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/table.css">    
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

	// 검색 기능 부트스트랩
	$(document).ready(function(){
		  $("#myInput").on("keyup", function() {
		          var value = $(this).val().toLowerCase();
		          $("#myTable tr").filter(function() {
		            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		          });
		        });
		      }); 
	
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
  
  <div class="btn-group" style="float:left;">
    <button type="button" class="btn btn-style-one" onclick="goMonthAgo()">${month- 1}월 내역 보기</button>
 	<c:if test="${ month != thisMonth }">
	 	<button type="button" class="btn btn-style-one" onclick="goMonthAhead()">${ month + 1 }월 내역 보기</button>  
 	</c:if>
  </div>
  
  <div class="dropdown" style="float:left;">
	  <button class="btn btn-secondary dropdown-toggle btn-style-one" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    카테고리별 내역
	  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
     <button type="button" class="btn btn-style-one" onclick="category('1')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;입금&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('2')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이체&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('3')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;식비&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('4')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;카페,간식&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('5')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;편의점,마트&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('6')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;술,유흥&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('7')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;쇼핑&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('8')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;취미,여가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('9')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;미용&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
    <button type="button" class="btn btn-style-one" onclick="category('10')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;주거,통신&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
  </div>
  </div>
  
  <div class="btn-group" style="float:left;">
    <input class="form-control" id="myInput" type="text" placeholder="search" style="width:150px; height:45px;">
  </div>
  
  <div align="center" style="float:right;">
	 <button type="submit" class="btn-style-one" 
	   onclick="location.href='${ pageContext.request.contextPath }/account '">돌아가기</button>
  </div>  
</div>


  
  <hr>          
  <div id="detailList" class="table-wrapper">
  <table class="table table-hover fl-table">
    <thead>
      <tr class="scrollLocation">
        <th>거래 날짜</th>
        <th>거래 대상 </th>
        <th>카테고리</th>
        <th>거래 금액</th>
      </tr>
    </thead>
    <tbody id="myTable">
    
  
     <c:forEach items="${ depositDetailCategoryList }" var="depositDetail" varStatus="loop">
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