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
	function getReplyList(){
		//ajax를 통해서 해당 게시물의 댓글 리스트를 조회 => <div id="replyList"> 에다가 조회 데이터 업데이트 해야함
		$.ajax({
			url : '${ pageContext.request.contextPath }/reply/${ board.no}',
			type : 'get',         /* 조회이므로 get */
			success : function(data){ /* 날라오는 데이터 data로 받음. ResponseTest를 대신하기 때문에 문자열의 형태로 날라옴. 객체문자열을 json객체로 바꿔야함! */
				
				/* 조회할때 이미 있던거 지우기 위해 empty 한번 해줌. append 해줘서 계속 추가되는 구조이므로... */
				$('#replyList').empty();	
				
				let list = JSON.parse(data);

				$(list).each(function(){
					
					let str = '';
					str += '<hr>';
					str += '<div>'
					str += '<strong>' + this.content + '</strong>';
					str += '(' + this.writer + ')'
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + this.regDate + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + '<button class="delBtn" id='+ this.no +'> 삭제 </button>'	/* 버튼이 여러개니 class로 해줌. */
					str += '</div>'
					
					$('#replyList').append(str);
				})
			},
			error : function(){
				alert('fail')
			}
		})
		
	}


	$(document).ready(function(){
		$('#replyAddBtn').click(function(){
			
			let content = document.rform.content.value;
			let writer = document.rform.writer.value;
			
			/* content, writer, boardNo 넘겨야함.어떤 게시글의 댓글인지... */
			
			/* /reply/insert?boardNo=12&content=aaa&writer=bbb */
			$.ajax({
				url : '${ pageContext.request.contextPath}/reply',
				type : 'post',
				data : {
					boardNo : ${ board.no}, 
					content : content,
					writer : writer
				}, 
				success : function(){
					getReplyList();	//댓글 insert이후, 댓글리스트 호출. 이렇게 해야 내가 쓴 글이 아래에 보임
				}, 
				error : function(){
					alert('fail');
				},
				complete : function(){
					document.rform.content.value = '';
					document.rform.writer.value ='';
				}
				
			})
			
		})
		
	})
	
	$(document).ready(function(){	/* 화면 실행되면 바로 댓글 리스트 호출 */
		getReplyList();		
	})
	
	
	
	/* 삭제 - 동적으로 만들어진 이벤트는 이벤트가 안붙음... => on 해야함!*/
	$(document).ready(function(){
		
		
		/* $('.delBtn').click(function(){
			alert('click...')
		}) */

		$(document).on('click', '.delBtn', function(){

			if(!confirm('댓글을 삭제하시겠습니까?')) return; /* 취소하면 그냥 다시 돌아옴 */
			
			let replyNo = $(this).attr('id');
				
			/* ajax 이후 또 ajax */
			/*
			 $.ajax({
				url : '${ pageContext.request.contextPath}/reply/' + replyNo,
				type: 'delete',	// put post get delete ,
				
				data : { boardNo : ${boardNo} },	// 첫 번째 방법 대신 boardNo를 db에서 가져오지않고 바로 보내주기!
				
				success :function(){
					getReplyList();	// 삭제 성공했을때 업데이트 해줘야하므로  호출! 
				},
				error : function(){
					alert('fail')
				}
			}) 
			*/
			
			/* 2번 방법 . 댓글 삭제시, 댓글 번호와, 댓글이 속한 게시글 번호도 보내줌!*/
			$.ajax({
				url : '${ pageContext.request.contextPath}/reply/' + replyNo + '/${board.no}',	
				type: 'delete',	/* put post get delete */	
				success :function(){
					getReplyList();	/* 삭제 성공했을때 업데이트 해줘야하므로  호출! */
				},
				error : function(){ 
					alert('fail')
				}
			})
			
			
		})
	})
</script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 

	<section>
		<div align = "center">
			<hr>
				<h2>게시판 상세 페이지</h2>
			<hr>
			<br>
			<table border="1">
				<tr>
					<th width="25%">번호</th>
					<td>${ board.no }</td>
				</tr>
				<tr>
					<th width="25%">제목</th>
					<td><c:out value="${ board.title }" /></td>
				</tr>
				<tr>
					<th width="25%">글쓴이</th>
					<td>${ board.writer }</td>
				</tr>
				<tr>
					<th width="25%">내용</th>
					<td>${ board.content }</td>
				</tr>
				<tr>
					<th width="25%">조회수</th>
					<td>${ board.viewCnt }</td>
				</tr>
				<tr>
					<th width="25%">등록일</th>
					<td>${ board.regDate }</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<c:forEach items="${ fileList }" var="file">
							<a href="/Mission-WEB/upload/${ file.fileSaveName }">
								${ file.fileOriName }
							</a>
							(${ file.fileSize } bytes)<br>
						</c:forEach>
					</td>
				</tr>
			</table>
			<br>
			<c:if test="${ loginVO.id eq board.writer }">
				<input type="button" value="수정" onclick="doAction('U')">&nbsp;&nbsp;
				<input type="button" value="삭제" onclick="doAction('D')">&nbsp;&nbsp;
			</c:if>
			<input type="button" value="목록" onclick="doAction('L')">&nbsp;&nbsp;
			
			<br>
			<hr>
			<!-- 댓글 -->
			<form name="rform">	<!-- name주기 -->
				댓글 : <input type="text" name="content" size="50">
				이름 : <input type="text" name="writer" size="10"> 
				<input type="button" value="댓글 추가" id="replyAddBtn">	<!-- 버튼에 id를 줘서, 버특 누르면 나 ~~할거야. 라고 표현! -->
			</form>
			
			<!-- ajax로 들어오는 div-->
			<div id="replyList"></div>
			
		</div>
	</section>
	
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 	
</body>
</html>