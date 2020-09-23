<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function doAutoTransfer(sform){
		location.href = "${ pageContext.request.contextPath }/autoTransferConfirm/${ savingsAccount.getAccountNumber()}" ;
	}
	
	function deleteAutoTransfer(param){
		location.href = "${ pageContext.request.contextPath }/autoTransferDelete/${ savingsAccount.getAccountNumber()}" ;
	}
	
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />




<div class="container" align="center">

<br><br><br><br>
  
<div class="section-title">
    <h3>적금
        <span>상세 정보입니다.</span>
     </h3>
</div> 
  
<br><br>    
 <form action="${ pageContext.request.contextPath }/savingsManageChange" method="post">
  <input type="hidden" name="accountNumber" value="${savingsAccount.getAccountNumber()}">
  <table class="table table-hover" style="width:50%;">
    <thead>
      
      <tr>
        <th>적금 상품 이름</th>
        <th>${ savingsAccount.getBankBookKey()}</th>
      </tr>
    </thead>
    <tbody>
    	<tr>
    		<td>계좌 번호</td>
    		<td>${ savingsAccount.getAccountNumber()}</td>
    	</tr>
    	<tr>
    		<td>만기일</td>
    		<td>${ savingsAccount.getSavingDate()}</td>
    	</tr>
    	
    	<tr>
    		<td>보유 잔액</td>
    		<td>${ String.format("%,d", savingsAccount.getBalance()) }원</td>
    	</tr>
    	<tr>
    		<td>금리</td>
    		<td>${savingsAccount.getRate()}</td>
    	</tr>
    	<tr>
    		<td>별칭</td>
    		<td><input name="nickName" placeholder="${ savingsAccount.getNickName()}"></td>
    	</tr>
    	<tr>
    		<td>입금 일자</td>
    		<td>
	    		<select name="savingDay">
				    <option selected>현재 입금 일 : 매월 &nbsp; ${ savingsAccount.getSavingDay()}일 </option>
				    <option value=1>매월 1일</option>
				    <option value=15>매월 15일</option>
				    <option value=28>매월 28일</option>
				</select>
			</td>
    	</tr>
    	
    	<tr>
    		<td>출금 계좌</td>
    		<td>
    			<select name="autoSaving">
    				<option selected>현재 출금 계좌 : ${ savingsAccount.getAutoSaving() }</option>
    				<c:forEach items="${ depositAccountNumList }" var="accountNumber">
    					<option value="${accountNumber}">${accountNumber}</option>
    				</c:forEach>
    			</select>
    		</td>
    	</tr>
    	
    	<tr>
    		<td>자동 이체 </td>
    		<c:if test="${ savingsAccount.getAutoSavingBool() == 'N' }">
    			<td>미사용</td>
    		</c:if>
    		<c:if test="${ savingsAccount.getAutoSavingBool() == 'Y' }">
    			<td>사용중</td>
    		</c:if>
    	</tr>
    	
    	
    	
    </tbody>
  </table>
  
  <button type="submit" class="btn-style-one">수정</button>
  <c:if test="${ savingsAccount.getAutoSavingBool() == 'N' }">
	  <input type="button" value="예약 이체 설정" class="btn-style-one" data-toggle="modal" data-target="#myModal">
  </c:if>
  <c:if test="${ savingsAccount.getAutoSavingBool() == 'Y' }">
	  <input type="button" value="예약 이체 해지" class="btn-style-one" data-toggle="modal" data-target="#myModal2">
  </c:if>
  
</form>         
</div>
 <br>  <br> 

<!-- 예약이체 등록 modal -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">예약 이체를 설정하시겠습니까?</h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         	 매월 &nbsp; ${ savingsAccount.getSavingDay()}일에 등록하신 출금계좌로 부터의 이체가 예약됩니다.
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-style-one" data-dismiss="modal">취소</button>
          <button type="button" class="btn-style-one" onclick="doAutoTransfer()">확인</button>
        </div>
        
      </div>
    </div>
  </div>

<!-- 예약이체 해지 modal -->
<div class="modal fade" id="myModal2">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">예약 이체를 해지하시겠습니까?</h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         	  등록하신 출금계좌로 부터의 예약 이체가 해지됩니다.
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-style-one" data-dismiss="modal">취소</button>
          <button type="button" class="btn-style-one" onclick="deleteAutoTransfer()">확인</button>
        </div>
        
      </div>
    </div>
  </div>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>