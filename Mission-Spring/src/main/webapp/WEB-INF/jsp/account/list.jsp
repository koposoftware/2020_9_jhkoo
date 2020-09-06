<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js" ></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/table.css"> 
<script>

	function goCashManage(){
		location.href = "${ pageContext.request.contextPath }/cashManage";
	}

	function doDepositManage(param){
		location.href = "${ pageContext.request.contextPath }/depositManage/" + param;
	}
	
	function doSavingsManage(param){
		location.href = "${ pageContext.request.contextPath }/savingsManage/" + param;
	}

	
	
	function goDepositDetail(accountNumber){

		 location.href = "${ pageContext.request.contextPath}/depositDetail/" + accountNumber;
	}
	
	function goSavingsDetail(accountNumber){
		location.href = "${ pageContext.request.contextPath}/savingsDetail/" + accountNumber;
	}
	
	
	function goChallenge(){
		location.href = "${ pageContext.request.contextPath }/product/depositFreeExplain" + ageGroupDepositAccountType;
	}
	
</script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" />


<br>
<br>
<br>

	<div class="section-title text-center">
         <h3>${ loginVO.getName() }님의 ${month}월<br>
            <span> 순자산은 ${ String.format("%,d", totalBalance) }원입니다 </span><br>
            
         </h3>
         <c:if test="${ totalBalanceChange < 0 }">
        	 <h4 style="color:red">지난 달 대비 총 자산이 ${ String.format("%,d", totalBalanceChange) }원 감소했습니다. </h4>
         </c:if>
         <c:if test="${ totalBalanceChange > 0 }">
        	 <h4 style="color:red">지난 달 대비 총 자산이 ${ String.format("%,d", totalBalanceChange) }원 증가했습니다. </h4>
         </c:if>
    </div>

<br>

<!-- 입출금 자유 ----------------------------------------------------------------------------------------------------------->

<div class="container table-wrapper">
  <h2>&nbsp;</h2>
  <p>입출금 계좌 총액  &nbsp; : &nbsp; <span style="color:red">${ String.format("%,d", depositTotalBalance) }</span> &nbsp; 원</p> 
  <hr>           
  <table class="table table-hover fl-table">
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
        <a href="javascript:doDepositManage('${ account.getAccountNumber() }')">
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



<!-- 적금 ------------------------------------------------------------------------------------------------------------------>

<div class="container table-wrapper">
  <h2>&nbsp;</h2>
  <p>적금 계좌 총액  &nbsp; : &nbsp; <span style="color:red">${ String.format("%,d", savingsTotalBalance) }</span> &nbsp; 원</p>  
  <hr>          
  <table class="table table-hover fl-table">
    <thead>
      <tr>
        <th>적금 이름</th>
        <th>내용</th>
        <th>출금 계좌</th>
        <th>잔액</th>
        <th>상세 내역</th>
      </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${ savingsAccountList }" var="account">
      <tr>
      	
       <td>
         <a href="javascript:doSavingsManage('${ account.getAccountNumber() }')">
      	   ${ account.getBankBookKey() } 
         </a>
       </td>
      
       <td>${ account.getNickName() }</td>
       
       <td>${ account.getAutoSaving()}</td>
       

       <td>${ String.format("%,d", account.getBalance()) }원</td>
       
       <td>
       	<input type="button" value="상세보기" class="btn-style-one" onclick="goSavingsDetail('${account.getAccountNumber()}')">
       </td>
       
       
      </tr>
     </c:forEach>

    </tbody>
  </table>
</div>

<br>
<br>

<!--현금  ------------------------------------------------------------------------------------------------------------------->

<div class="container ">

	<table class="table table-hover ">
    <tbody>
      <tr>
       <td id="myCash">보유 현금  &nbsp; : &nbsp; <span style="color:red" >${ String.format("%,d", cash) }</span> &nbsp; 원 </td> 
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
       
       <td><input type="button" value="내역관리" class="btn-style-one" onclick="goCashManage()"></td>
    </tbody>
    </table>
</div>


<!----------------------------------------------------------------------------------------------------------------->
<section class="service-section bg-gray section">
    <div class="container">
        <div class="section-title text-center">
            <h3>${ loginVO.name }님
                <span>에게 필요한 정보</span>
            </h3>
        </div>
        <div class="row items-container clearfix">
        
        
       
        	<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center">
                        <h6>${month}월  고객님이<br>진행 중인 도전 </h6>
                        <button class="btn-style-one" onclick="#">보러가기</button>
                    </div>
                </div>
            </div>
        
        	<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center">
                        <h6>${month}월 '${ loginVO.ageGroup }'가 <br>
                                                        가장 많이 가입한 입출금 자유 계좌는<br>
                            <c:if test="${ ageGroupDepositAccountType.equals(\"1\") }">하나 플러스통장입니다</c:if>
                            <c:if test="${ ageGroupDepositAccountType.equals(\"2\") }">영하나 플러스통장입니다</c:if>
                            <c:if test="${ ageGroupDepositAccountType.equals(\"3\") }">주거래 하나 통장입니다</c:if>
                        </h6>
                        <button class="btn-style-one" onclick="goChallenge()">보러가기</button>
                    </div>
                </div>
            </div>

			<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center">
                        <h6>${month}월 '${ loginVO.jobKey }'이(가) <br>가장 많이 가입한 적금</h6>
                        <button class="btn-style-one" onclick="#">보러가기</button>
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