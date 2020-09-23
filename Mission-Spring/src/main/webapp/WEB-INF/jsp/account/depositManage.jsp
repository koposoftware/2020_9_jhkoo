<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function doDelete(){
		location.href = "${ pageContext.request.contextPath }/deleteDepositAccount/${ depositAccount.getAccountNumber()}/${depositAccount.getBalance()}";
	}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />



<section class="service-section bg-gray section">
<div class="container" align="center">
  
        <div class="section-title text-center">
		    <h3>계좌
		        <span>상세 정보</span>
		     </h3>
            <p style="color:red"></p>
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
  
  <input type="button" value="탈퇴" class="btn-style-one" data-toggle="modal" data-target="#deleteModal">
</form>         
</div>
</section>




<!-- 탈퇴 modal ---------------------------------------------------------------------------------------->
<div class="modal fade" id="deleteModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">${ depositAccount.getBankBookKey() } 을 그만 사용하시겠습니까?</h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         	 보유 잔액은 메인 계좌로 자동 이체 됩니다.
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-style-one" data-dismiss="modal">취소</button>
          <button type="button" class="btn-style-one" onclick="doDelete()">확인</button>
        </div>
        
      </div>
    </div>
  </div>



<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>