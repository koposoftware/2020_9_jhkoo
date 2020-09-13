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

	/* 관리 */
	function goCashManage(){
		location.href = "${ pageContext.request.contextPath }/cashManage";
	}

	function doDepositManage(param){
		location.href = "${ pageContext.request.contextPath }/depositManage/" + param;
	}
	
	function doSavingsManage(param){
		location.href = "${ pageContext.request.contextPath }/savingsManage/" + param;
	}

	
	/* 내역 */
	function goDepositDetail(accountNumber){
		 location.href = "${ pageContext.request.contextPath}/depositDetail/" + accountNumber;
	}
	
	function goSavingsDetail(accountNumber){
		location.href = "${ pageContext.request.contextPath}/savingsDetail/" + accountNumber;
	}
	
	
	/* 추천 */
	function goDepositRecommend(){
		location.href = "${ pageContext.request.contextPath }/product/depositFreeExplain" + ${ageGroupDepositAccountBankBook};
	}
	
	function goSavingsRecommend(){
		location.href = "${ pageContext.request.contextPath }/product/savingsExplain${jobSavingsAccountBankBook} ";
	}
	
	function goChallenge(){
		location.href = "${ pageContext.request.contextPath }/challengeChoose";
	}
	
	
	/* 잦은 지출 상세 내역 */
	$(document).ready(function(){
		$('.frequentDetailBtn').click(function(){
			let btn = this;
			let elements = $(this).attr('id').split('.');
			let str = '';
			
			$.ajax({
				url : '${ pageContext.request.contextPath }/frequentDetail',
				type : 'get',
				data : {
					accountNumber : elements[0],
					toName : elements[1]
				},
				success : function(data){
					
					$('#modalDiv').empty();
					let list = JSON.parse(data);
					
					$(list).each(function(){
						
					
						str += '<hr>';
						str += '<div>';
						str += '<strong>' + this.logDate + '</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
						str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' + this.amount + '원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
						str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + this.toName + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
						str += '</div>';
					})
					
					$("#modalDiv").append(str);
					$("#frequentModal").modal('show');

				}
			})
			
		})
	})
	
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
<section class="service-section bg-gray section" style="height:420px">
    <div class="container">
        <div class="section-title text-center">
            <h3>${ loginVO.name }님
                <span>에게 추천합니다</span>
            </h3>
        </div>
        <div class="row items-container clearfix">
        
        
       
        	<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center">
                        <h6>고객님이<br>진행 중인 도전을 관리하세요! </h6><br>
                        <input type="button" value="보러가기" class="btn-style-one" onclick="goChallenge()">
                    </div>
                </div>
            </div>
        
        	<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center">
                        <h6>'${ loginVO.ageGroup }' Pick <br>
                                                         입출금 자유 예금 상품은<br>
                            <c:if test="${ ageGroupDepositAccountBankBook.equals(\"1\") }">'하나 플러스통장'입니다</c:if>
                            <c:if test="${ ageGroupDepositAccountBankBook.equals(\"2\") }">'영하나 플러스통장'입니다</c:if>
                            <c:if test="${ ageGroupDepositAccountBankBook.equals(\"3\") }">'주거래 하나 통장'입니다</c:if>
                        </h6>
                        <input type="button" value="보러가기" class="btn-style-one" onclick="goDepositRecommend()">
                    
                    </div>
                </div>
            </div>

			<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center">
                        <h6>'${ loginVO.jobKey }' Pick <br>
                        	  정기 적금 상품은<br>
                        	  <c:if test="${ jobSavingsAccountBankBook.equals(\"1\") }">'하나 원큐 적금'입니다</c:if>
                            <c:if test="${ jobSavingsAccountBankBook.equals(\"2\") }">'내집마련 적금'입니다</c:if>
                            <c:if test="${ jobSavingsAccountBankBook.equals(\"3\") }">'꿈하나 적금'입니다</c:if>
                         </h6>
                        <input type="button" value="보러가기" class="btn-style-one" onclick="goSavingsRecommend()">
                    </div>
                </div>
            </div>

           
        </div>
    </div>
</section>


<!-- 잦은 거래  --------------------------------------------------------------------------------- -->

<section class="service-section bg-gray section" style="height:420px">
    <div class="container">
        <div class="section-title text-center">
            <h3>${ month }월
                <span>잦은 지출 목록입니다</span>
            </h3>
        </div>
        <div class="row items-container clearfix">       
        
       	  <c:forEach items="${ frequentExpenditureList}" var="account">
       	  <c:if test='${ !account.toName.equals(loginVO.name) && account.count >=3 }'>
        	<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center">
                        <h6>${ account.toName }
                             <br> 총 ${ account.count }번 지출</h6><br>
                        <input type="button" value="내역보기" class="frequentDetailBtn btn-style-one" id="${ account.accountNumber }.${ account.toName }">
                    </div>
                </div>
            </div>
         </c:if>   
         </c:forEach>
        	  
        </div>
    </div>
</section>


<!-- 지출 Top3  --------------------------------------------------------------------------------- -->

<section class="service-section bg-gray section" style="height:420px">
    <div class="container">
        <div class="section-title text-center">
            <h3>${ month }월
                <span>지출 Top3입니다</span>
            </h3>
        </div>
        <div class="row items-container clearfix">       
        
       	  <c:forEach items="${ expenditureTop3List}" var="account">
        	<div class="item">
                <div class="inner-box">
                    
                    <div class="image-content text-center">
                        <h6>${ account.toName } 
                             <br> ${ String.format("%,d", account.amount) }원 지출
                             <br>${ account.logDate }</h6><br>
                    </div>
                </div>
            </div>
         </c:forEach>
        	  
        </div>
    </div>
</section>



<br>
<br>
<!-- 모달-----------------------------------------------------  -->
<div class="modal fade" id="frequentModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">잦은 지출 내역보기</h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body" id= "modalDiv">
         	
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-style-one" data-dismiss="modal">확인</button>
        </div>
        
      </div>
    </div>
  </div>



<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />

</body>
</html>