<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function goWriteForm(){
		location.href = "${ pageContext.request.contextPath}/board/write"; 
	}
	
	
	function doAction(boardNo){	//가상의 url 찾을 때는  ${ pageContext.request.contextPath } 써야함!	
	//	location.href = "${ pageContext.request.contextPath }/board/detail?no=" + boardNo;	
		location.href = "${ pageContext.request.contextPath }/board/" + boardNo;	//이런 방식으로 보내주기
	}
</script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 


	<section>
		<div align = "center">
		<hr width="80%">
		<h2>게시판 목록</h2>
		<hr width="80%">		
		<table border="1" style="width: 80%">
			<tr>
				<th width="7%">번호</th>
				<th>제목</th>
				<th width="16%">글쓴이</th>
				<th width="20%">등록일</th>
			</tr>
			
			<c:forEach items="${ boardList }" var="board" varStatus="loop">
				<tr <c:if test="${ loop.count mod 2 eq 0 }">class="even"</c:if>>
					<td>${ board.no }</td>
					<td>

						<a href="javascript:doAction(${ board.no })">
							<c:out value= "${ board.title }" />
						</a>
	
					</td>
					
					<td>${ board.writer }</td>
					<td>${ board.regDate }</td>
				</tr>
			</c:forEach>
			

			
			
		</table>
		<br>
		
		<%-- <button>새글등록</button>  이렇게 버튼을 통해서 가게 해주려면 javascript나 jquery 문법 필요함 --%>
		<!-- 로그인 되어있을 때만 새 글 등록하게! -->
		<c:if test="${ not empty loginVO }">
			<button onclick="goWriteForm()">새 글 등록</button>  
		</c:if>
	
	</div>
	</section>
	
	
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 
	
</body>
</html>