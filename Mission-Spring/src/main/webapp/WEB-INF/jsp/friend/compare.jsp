<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://www.gstatic.com/charts/loader.js'></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = google.visualization.arrayToDataTable([
        ['', '',],
        ${ str }
      ]);

      var options = {
        title: '지출 비교 상황',
        chartArea: {width: '50%'},
        hAxis: {
          minValue: 0
        }
      };

      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

      chart.draw(data, options);
    }
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<!-- 
<br>
<br>
 <div id="chart_div"></div>
<br>
<br>
 -->
<!-- 도전 공유 ------------------------------------------------------------------------------------->
<section class="service-section bg-gray section">
    <div class="container">
        <div class="section-title text-center">
            <h3>친구들
                <span>의 공유된 도전</span>
            </h3>
            <p>${ month }월에 공유된 친구들의 도전 목록입니다.<br>
           	   <span style="color:red">목표는 공유할수록 달성할 확률이 높습니다. 
           	       더 많은 친구들과 도전을 공유하세요! </span>
            </p>
        </div>
        
        <div class="row items-container clearfix">
        
        <c:forEach items="${ challengeList }" var="challenge">
            <div class="item">
                <div class="inner-box">
                    <div class="img_holder">
                            <img src="${pageContext.request.contextPath }/resources/assets/images/친구.jpg" alt="images" class="img-responsive">                     
                    </div>
                    <div class="image-content text-center">
                        <span>${ challenge.challengeName }에 도전 중</span>
                        <h6>${ challenge.id }님</h6>
                        <p>
                        	목표금액은 ${ String.format("%,d", challenge.targetAmount) }이고,<br>
                                                        현재까지 지출금액은 ${ String.format("%,d", challenge.nowBalanceByType) }입니다.<br>
 						    <div class="progress">
			 					<div class="progress-bar " style="width:${(challenge.nowBalanceByType/challenge.targetAmount)*100}%">${Math.round((challenge.nowBalanceByType/challenge.targetAmount)*100)}%</div>
						    </div>                                                       
                            <c:if test="${ challenge.challengeFail == true }">
                            	<span style="color:red; font-weight:bold">다음에 친구들과의 약속을 꼭 지켜주세요!</span>
							</c:if>
							<c:if test="${ challenge.challengeFail == false }">
                            	<span style="color:red; font-weight:bold">도전 진행중입니다. </span>
							</c:if>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>        
                        
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>












