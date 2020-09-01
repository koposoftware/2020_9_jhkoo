<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.error {
		color:red;
	}
</style>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 

      <div align="center">
         <hr width="80%">
         <h2>게시물 등록폼</h2>
         <hr width="80%">
         <br>
         <!-- spring제공 form태그 : 백앤드에서 validation체크를 해줌으로써 보안적으로 효과를 가짐-->
         <!-- 보안적인 측면에서 유효성 검사란 올바르지 않은 데이터가 서버로 전송되거나, DB에 저장되지 않도록 하는 것 -->
         <!-- action없으면 자동으로 똑같은 메소드의 post한테 날라감! -->
         <form:form commandName="boardVO" method="post">
             <table border="1" style="width: 80%;">
                <tr>
                   <th width="23%">제목</th>
                   <td>
                      <form:input path="title"/><form:errors path="title" class="error" />
                   </td>
                </tr>
                <tr>
                   <th>글쓴이 </th>
                   <td>
                      <form:input path="writer"/><form:errors path="writer" class="error" />
                   </td>
                </tr>
                <tr>
                   <th>내용</th>
                   <td>
                      <form:textarea path="content" rows="7" cols="50"/><form:errors path="content" class="error" />
                   </td>
                </tr>
             </table>
             <button type="submit">등록</button>
         </form:form>
         
         
      </div>

	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 
</body>
</html>