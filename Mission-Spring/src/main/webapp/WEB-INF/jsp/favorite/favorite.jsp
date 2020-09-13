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

	/* 즐겨찾기 추가 ajax--------------------------------------------------------------- */
	$(document).ready(function(){
		$('.addFavoriteBtn').click(function(){
			let btn = this;
			let elements = $(this).attr('id').split('.');
			
			$.ajax({
				url : '${ pageContext.request.contextPath }/favorite/updateFavorite',
				type : 'get',
				data : {
					id : elements[0],
					toAccountNumber : elements[1],
					toName : elements[2]
				},
				success : function(){
					$(btn).attr('disabled', true)
					$(btn).val("  등록 완료   ")
				},
				error : function(){
					alert('fail')
				}
			})
		})
	})
	/*----------------------------------------------------------------------  */
	
	/* 즐겨찾기 해지 ajax-------------------------------------------------------- */
	$(document).ready(function(){
		$('.deleteFavoriteBtn').click(function(){
			let btn = this;
			let elements = $(this).attr('id').split('.');
			
			$.ajax({
				url : '${ pageContext.request.contextPath }/favorite/deleteFavorite',
				type : 'get',
				data : {
					id : elements[0],
					toAccountNumber : elements[1],
					toName : elements[2]
				},
				success : function(){
					$(btn).attr('disabled', true)
					$(btn).val("  해지 완료   ")
				},
				error : function(){
					alert('fail')
				}
			})
		})
	})
	/*----------------------------------------------------------------------  */
</script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<br>
<br>
	<div class="section-title text-center">
         <h3>최근 이체 목록<span>입니다 </span>
         </h3>
         
         <h4 style="color:red">자주 찾는 계좌를 즐겨찾기에 추가하세요</h4>
    </div>
<br>
<div class="container table-wrapper" style="width:60%">

  <hr>   
  <!-- 즐겨찾기 미등록 -->        
  <table class="table table-hover fl-table">
    <thead>
      <tr>
        <th>거래 대상</th>
        <th>거래 계좌번호</th>
        <th>즐겨찾기</th>
      </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${ recentList }" var="account">
     <c:if test="${ !account.toName.equals(loginVO.name) and account.favoriteFlag.equals(\"0\")}">
      <tr>
       <td>${ account.toName }</td>
       
       <td>${ account.toAccountNumber }</td>
      
  		<td>
  		  <input type="button" class="addFavoriteBtn btn-style-one" id="${ loginVO.id }.${ account.toAccountNumber }.${ account.toName }" 
  		     <c:if test="${account.favoriteFlag.equals(\"1\") }">disabled </c:if>
  		     <c:if test="${ account.favoriteFlag.equals(\"1\") }">value="즐겨찾기 등록 완료"</c:if>
  		     <c:if test="${ account.favoriteFlag.equals(\"0\") }">value="즐겨찾기  등록하기"</c:if> 
  		     >
  		</td>
  
       </tr>
     </c:if>  
     </c:forEach>

    </tbody>
  </table>
  
  <br>
  <hr> 
  <br>
  
  <!-- 즐겨찾기 등록된 것들 -->
  
  <div class="section-title text-center">
 
         <h4>즐겨찾는 계좌</h4>
    </div>
  
  <br>
  <table class="table table-hover fl-table">
    <thead>
      <tr>
        <th>거래 대상</th>
        <th>거래 계좌번호</th>
        <th>즐겨찾기</th>
      </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${ recentList }" var="account">
     <c:if test="${ !account.toName.equals(loginVO.name) and account.favoriteFlag.equals(\"1\")}">
      <tr>
       <td>${ account.toName }</td>
       
       <td>${ account.toAccountNumber }</td>
      
  		<td>
  		  <input type="button" class="deleteFavoriteBtn btn-style-one" id="${ loginVO.id }.${ account.toAccountNumber }.${ account.toName }" 
  		     <c:if test="${account.favoriteFlag.equals(\"0\") }">disabled </c:if>
  		     <c:if test="${ account.favoriteFlag.equals(\"0\") }">value="즐겨찾기 해지 완료"</c:if>
  		     <c:if test="${ account.favoriteFlag.equals(\"1\") }">value="즐겨찾기  해지하기"</c:if> 
  		     >
  		</td>
  
       </tr>
     </c:if>  
     </c:forEach>

    </tbody>
  </table>
  
   
  <!--  -->
</div>


<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />

</body>
</html>