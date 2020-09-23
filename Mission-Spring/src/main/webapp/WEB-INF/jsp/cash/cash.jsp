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
					str += '<tr>'
					str +=  '<td>' + this.logDate + '</td>'
					str +=  '<td>' + this.content + '</td>'
					str +=  '<td>' + this.cash + '원</td>'
					str +=  '<td><button class="delBtn" id='+ this.no +'> 삭제 </button><td>'
					str += '</tr>'
										
					
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


<!-- Contact Section -->
<section class="blog-section section style-three pb-0">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="contact-area style-two">
                    <div class="section-title">
                        <h3>현금 사용 내역 <span>을 입력하세요</span></h3>
                    </div>
                    <form name="rform" class="default-form contact-form">
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                            
                                <input type="hidden" name="id" value="${ loginVO.id }">
                                
                                <div class="form-group">
                                    <input type="text" name="cash" placeholder="현금 변동금액을 입력하세요">
                                </div>
                                                           
                            </div>
                            
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="form-group">
                                    <textarea name="content" placeholder="설명" ></textarea>
                                </div>
                                <div class="form-group text-center">
                                    <button type="submit" class="btn-style-one" id="cashAddBtn">내역 추가하기</button>
                                    
                                </div>                            
                            </div>
                        </div>
                    </form>
                </div>                      
            </div>
            <div class="col-md-6 col-sm-12 col-xs-12">
                <br><br><br>
              <!--   <div id = "cashList"></div>   --> 
                 
                <div class="container">
                 <table class="table table-hover" style="width:50%">
                  <thead>
                  	<tr>
                  		<th>기록 날짜</th>
                  		<th>내용</th>
                  		<th>보유 현금</th>
                  		<th>&nbsp;</th>
                  	</tr>
                  </thead>
                  
                  <tbody id="cashList">
                   
                  </tbody>
                 
                 </table>
                </div>                  
                                   
            </div>
        </div>                    
    </div>
</section>
<!-- End Contact Section --> 
 
 <br>
 
 <div align="center">
 <button type="submit" class="btn-style-one" 
         onclick="location.href='${ pageContext.request.contextPath }/account '"
         >돌아가기</button>
 </div>
	
<br>
<br>
<br>
<br>

 	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 
</body>
</html>