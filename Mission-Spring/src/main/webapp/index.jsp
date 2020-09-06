<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>Main</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9df8e3a9d46c166c7d34b0e997579ad3"></script>  
<script>

/* 
window.onload = function(){
	 var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	 var options = { //지도를 생성할 때 필요한 기본 옵션		
	 	center: new kakao.maps.LatLng(37.474309, 126.868428), //지도의 중심좌표.
	 	level: 3 //지도의 레벨(확대, 축소 정도)
	 };

	 var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
}

function go(){
	 var container = document.getElementById('map'); 
	 var options = { 
	 	center: new kakao.maps.LatLng(37.556325, 126.624533), //하나금융티아이 위도 경도
	 	level: 3 
	 };

	 var map = new kakao.maps.Map(container, options);
}
 */
 
</script>
</head>
<body>


<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 


<div class="hero-slider">
    <!-- Slider Item -->
    <div class="slider-item slide1" style="background-image:url(${pageContext.request.contextPath }/resources/assets/images/slider/배경2.jpg)">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Slide Content Start -->
                    <div class="content style text-center">
                        <h2 class="text-white text-bold mb-2">하나은행 계좌관리 서비스</h2>
                        <p class="tag-text mb-5">친구 같은 '금융비서'와 함께 현명한 계좌관리 하세요!</p>
                        
                    </div>
                    <!-- Slide Content End -->
                </div>
            </div>
        </div>
    </div>
    <!-- Slider Item -->
    <div class="slider-item" style="background-image:url(${pageContext.request.contextPath }/resources/assets/images/slider/배경2.jpg);">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Slide Content Start-->
                    <div class="content style text-right">
                        <h2 class="text-white">하나은행 계좌관리 서비스</h2>
                        <p class="tag-text">친구 같은 '금융비서'와 함께 현명한 계좌관리 하세요! </p>
                        
                    </div>
                    <!-- Slide Content End-->
                </div>
            </div>
        </div>
    </div>
    <!-- Slider Item -->
    <div class="slider-item" style="background-image:url(${pageContext.request.contextPath }/resources/assets/images/slider/배경2.jpg)">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Slide Content Start -->
                    <div class="content text-center style">
                        <h2 class="text-white text-bold mb-2">하나은행 계좌관리 서비스</h2>
                        <p class="tag-text mb-5">친구 같은 '금융비서'와 함께 현명한 계좌관리 하세요!</p>
                        
                    </div>
                    <!-- Slide Content End -->
                </div>
            </div>
        </div>
    </div>
</div>

<!--====  End of Page Slider  ====-->

<section class="cta">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="cta-block">
                    <div class="emmergency item">
                        <i class="fa fa-phone"></i>
                        <h2>연결</h2>
                      
                        <p>대표전화: 02-2151-6400<br>
							팩스: 02-2151-6410</p>
                    </div>
                    <div class="top-doctor item">
                        <i class="fa fa-stethoscope"></i>
                        <h2>찾아오시는 길</h2>
                        <p>주소: (22742) 인천 서구 에코로 181 하나금융그룹 통합데이터센터 비전센터 5층</p>
                        
                        <button type="button" class="btn btn-primary btn btn-main" data-toggle="modal" data-target="#myModal">
    						지도보기
  						</button>
                    </div>
                    <div class="working-time item">
                        <i class="fa fa-hourglass-o"></i>
                        <h2>상세 연결</h2>
                        <ul class="w-hours">
                            <li>전자금융상담 전용<span>1588-3555</span></li>
                            <li>은행업무<span>1588-1111 </span></li>
                            <li>해외 <span>+82-42-520-2500</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<br>
<br>
<br>



<!--  
<div class="container">
  
  The Modal
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        Modal Header
        <div class="modal-header">
          <h4 class="modal-title">지도 보기</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        Modal body
        <div class="modal-body">
          <div id="map" style="width:500px;height:400px;"></div>
				<br>
				<input type="button" class="btn btn-primary btn-lg" value="하나금융티아이" onclick="go()">
        </div>
        
        Modal footer
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
</div>

 -->
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 



</body>

</html>

