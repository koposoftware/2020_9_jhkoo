<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script>
	function getCashList(){
		
		$.ajax({
			url : '${ pageContext.request.contextPath }/cash',
			type : 'get',
			success : function(data){
				
				$('#cashList').empty();
				
				let list = JSON.parse(data);
				
				$(list).each(function(){
					let str = '';
					str += '<hr>';
					str += '<div>'
					str += '<strong>' + this.logDate + '</strong>';
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + this.content + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + this.cash + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + '<button class="delBtn" id='+ this.no +'> 삭제 </button>'	/* 버튼이 여러개니 class로 해줌. */
					str += '</div>'
					
					$('#cashList').append(str); 
				})
			},
			error : function(){
				alert('fail1')
			}
		})
	}
	
	$(document).ready(function(){
		$('#cashAddBtn').click(function(){
			
			let cash = document.rform.cash.value;
			let content = document.rform.content.value;
			let id = document.rform.id.value;
			
			$.ajax({
				url : '${pageContext.request.contextPath}/cash',
				type : 'post',
				data : {
					// 아이디, 내용, 현금 상태 보낸 후 insert
					id : id,
					content : content,
					cash : cash
				},
				success : function(){
					// insert 이후, 현금 내역 리스트 호출.
					getCashList();	
				},
				error : function(){
					alert('fail2');
				},
				complete : function(){
					// 입력창 비워주기
					document.rform.content.value = '';
					document.rform.cash.value ='';
				}
			})
			
		})
	})
	
	
	$(document).ready(function(){
		// 화면 실행과 동시에 내역 리스트 호출
		getCashList();
	})
	
	// 내역 삭제
	$(document).ready(function(){
		$(document).on('click', '.delBtn', function(){
			
			if(!confirm('내역을 삭제하시겠습니까?')) return;
			
			let cashNo = $(this).attr('id');
			
			$.ajax({	// 삭제 시 내역 번호와 아이디 보냄.
				url : '${ pageContext.request.contextPath}/cash/' + cashNo + '/${loginVO.id}',
				type : 'delete',
				success : function(){
					// 삭제 성공 후 리스트 업데이트
					getCashList();
				},
				error : function(){
					alert('fail3');
				}
			})
		})
	})

</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 

	<form name=rform>
		보유 현금 : <input type="text" name="cash">
		내용 : <input type="text" name="content">
		<input type="hidden" name="id" value="${ loginVO.id }">
		<input type="button" value="내역 남기기" id="cashAddBtn">
	</form>

	<div id = "cashList"></div>


 	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 
</body>
</html>