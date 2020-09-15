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

	/* 받은 요청 승인 ajax-------------------------------------------------*/
	$(document).ready(function(){
		$('.agreeRequestBtn').click(function(){
			let btn = this;
			let elements = $(this).attr('id').split('.');
			
			$.ajax({
				url : '${ pageContext.request.contextPath }/friend/agreeRequest',
				type : 'get',
				data : {
					id : elements[0],
					friendId : elements[1]
				},
				success : function(){
					
					//모달 창 띄우기
					$("#agreeModal").modal('show')
					
					$(btn).attr('disabled', true)
					$(btn).val("승인 완료")
					$('#disAgreeModal').remove
				},
				error : function(){
					alrt('fail')
				}
			})
		})
	})
	
	
	/* 받은 요청 거절 ajax---------------------------------------------------*/
	$(document).ready(function(){
		$('.disAgreeRequestBtn').click(function(){
			let btn = this;
			let elements = $(this).attr('id').split('.');
			
			$.ajax({
				url : '${ pageContext.request.contextPath }/friend/disAgreeRequest',
				type : 'get',
				data : {
					id : elements[0],
					friendId : elements[1]
				},
				success : function(){
					$(btn).attr('disabled', true)
					$(btn).val("거절 완료")
				},
				error : function(){
					alrt('fail')
				}
			})
		})
	})	

</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<section class="team-section section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title text-center">
                    <h3>${ loginVO.name }님,
                        <span>도전 공유 요청을 관리하세요</span>
                    </h3>
                    <p>자신이 보낸 요청 상태를 확인하고,
                        <br>친구에게 받은 요청을 승인/보류 하세요</p>
                </div>
                <!-- Nav tabs -->
                <div class="tabs">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#doctor" data-toggle="tab">보낸 요청</a>
                        </li>
                        <li role="presentation">
                            <a href="#event-planning" data-toggle="tab">받은 요청</a>
                        </li>
                    </ul>
                </div>
                <div class="tab-content">
                    <!--Start single tab content-->
                    <div class="team-members tab-pane fade in active row" id="doctor">
                        
                      <c:forEach items="${requestList }" var="request">
                        <div class="col-md-4 col-sm-6">
                            <div class="team-person text-center">
                                <img src="${pageContext.request.contextPath }/resources/assets/images/친구.jpg" class="img-responsive" alt="team">
                                	<h6>${ request.friendName }님에게 보낸 요청</h6>
                                	<p>${ request.content }<br> ${ request.regDate }</p>
                                	<c:if test='${ request.agreeFlag.equals("0") }'>
                                		<input type="button" value="승인 대기중" class="btn-style-one" disabled="disabled">
                                	</c:if>
                                	<c:if test='${ request.agreeFlag.equals("1") }'>
                                		<input type="button" value="승인 완료" class="btn-style-one" disabled="disabled">
                                	</c:if>
                            </div>
                        </div>
                      </c:forEach>
                             
                    </div>
                    <!--End single tab content-->

                    <!--Start single tab content-->
                    <div class="team-members tab-pane fade in row" id="event-planning">
                        
                        <c:forEach items="${requestedList }" var="requested">
                        <div class="col-md-4 col-sm-6">
                            <div class="team-person text-center">
                                <img src="${pageContext.request.contextPath }/resources/assets/images/친구.jpg" class="img-responsive" alt="team">
                                	<h6>${ requested.friendName }님으로부터 받은 요청</h6>
                                	<p>${ requested.content }<br> ${ requested.regDate }</p>
                                	
                                	<c:if test='${ requested.agreeFlag.equals("0") }'>
                                		<input type="button" value="승인하기" class="agreeRequestBtn btn-style-one" id="${ requested.id }.${ requested.friendId }">
                                		<input type="button" value="거절하기" class="disAgreeRequestBtn btn-style-one" id="${ requested.id }.${ requested.friendId }">
                                	</c:if>
                                	
                                	<c:if test='${ requested.agreeFlag.equals("1") }'>
                                		<input type="button" value="승인 완료" class="btn-style-one" disabled="disabled">
                                	</c:if>
                            </div>
                        </div>
                      </c:forEach>
                      
                    </div>
                    <!--End single tab content-->

  
                </div>
            </div>
        </div>
    </div>
</section>



<!--승인할 때 정보 공유 동의 받는 모달 ----------------------------------------------------------------------------------------------- -->
		<div class="modal fade" id="agreeModal">
		    <div class="modal-dialog modal-dialog-centered">
		      <div class="modal-content">
  	          <!-- Modal Header -->
			  <div class="modal-header"><h4 class="modal-title">정보 제공에 동의서</h4></div>
			  <!-- Modal body -->
				<div class="modal-body">당신의 도전 정보가 상대방에게 보여집니다.<br>
										동의 시 서로의 도전 정보를 공유하며 피드백을 나눌 수 있습니다.<br>
										동의하시겠습니까?</div>
				<!-- Modal footer -->
				<div class="modal-footer">
				<button type="button" class="btn-style-one" data-dismiss="modal">동의</button>
				</div>
			</div>
		</div>
	</div>
<!-- ----------------------------------------------------------------------------------------------------------------------- -->

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>