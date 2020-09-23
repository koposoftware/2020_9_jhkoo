<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap Example</title>

<script>

	function checkForm(){
		let f = document.lform


		if (f.id.value == '') {
			alert("아이디를 입력하세요")
			f.id.focus()
			return false
		}
		if (f.password.value == '') {
			alert("비밀번호를 입력하세요")
			f.password.focus()
			return false
		}

		return true
	}
</script>

</head>
<body>



<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<br>
<br>
<section class="appoinment-section section">
    <div class="container">
        <div class="row">
            
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="contact-area">
    <div class="section-title">
        <h3>로그인
            <span>해주세요</span>
        </h3>
    </div>
    
    
    <!-- 로그인 form 태그.   parameter : id, password -->
    <!-- onsubmit 모달로 하기! 모달에 비밀번호찾기, 아이디 찾기 버튼 만들기? -->
    <form name="contact_form" class="default-form contact-form" 
    	  action="${ pageContext.request.contextPath }/login" method="post"
    	  onsubmit="return checkForm()">
    		
        <div class="row">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="form-group">
                    <input type="text" name="id" placeholder="ID" value="user1">
                </div>
            </div>
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="form-group">
                    <input type="password" name="password" placeholder="password" value="1234">
                </div>
            </div>
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="form-group text-center">
                    <button type="submit" class="btn-style-one">로그인</button>
                </div>
            </div>
        </div>
    </form>
</div>                        
            </div>
        </div>                    
    </div>
</section>
<br>
<br>
<br>

		

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />



</body>
</html>
