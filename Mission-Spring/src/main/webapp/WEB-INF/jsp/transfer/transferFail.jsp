<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function goAccount(){
		location.href = "${pageContext.request.contextPath }/transferChoose";
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 

	<!--about section-->
<section class="feature-section section bg-gray">
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-xs-12">
				<div class="image-content">
					<div class="section-title text-center">
						<h3>이체에
							<span>실패하였습니다.</span>
						</h3>
						<p></p>
						
					</div>
					
						
				<div class="col-md-12 col-sm-12 col-xs-12">         
				    <div class="form-group text-center">
				        <button onclick="goAccount()" class="btn-style-one">이체 페이지 가기</button>
				    </div>
				</div>		
						
			
				</div>
			</div>
		</div>
	</div>
</section>
<!--End about section-->


	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 

</body>
</html>