<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
<script>
	// 도전 신청하러~
	function goChallenge(param){
		location.href="${pageContext.request.contextPath }/challenge/" + param
	}

</script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 
	
<section class="team-section section">
    <div class="container">
    
        <div class="row">
            <div class="col-md-12">
            
                <div class="section-title text-center">
                    <h3>${ loginVO.getName() }님,
                        <span> ${month}월 도전을 응원합니다! </span>
                    </h3>
                    <p style="color:red">목표 지출을 설정하여 관리하세요. 매월 도전은 새로 갱신됩니다.</p>
                </div>
                
                <!-- Nav tabs -->
                <div class="tabs">
                    <ul class="nav nav-tabs" role="tablist">
                        
                        <li role="presentation">
                            <a href="#canSelectChallenge" data-toggle="tab">도전 가능한 목록</a>
                        </li>
                         
                        <li role="presentation">
                            <a href="#myChallenge" data-toggle="tab">진행 중인 나의 도전</a>
                        </li>
                        
                    </ul>
                </div>
                <div class="tab-content">
                    
                    <!--Start single tab content-->
                    <div class="team-members tab-pane fade in row" id="canSelectChallenge">
                        
                        <c:if test='${ !myChallengeNameList.contains("식비 지출 줄이기") }'>
	                        <div class="col-md-4 col-sm-6"
	                             onclick="goChallenge('식비 지출 줄이기')">
	                            <div class="team-person text-center">
	                                <img src="${pageContext.request.contextPath }/resources/assets/images/식비.png" class="img-responsive" alt="team">
	                                <h6>식비 지출 줄이기</h6>
	                                <p>이번 달 식비 지출을 관리하세요</p>
	                            </div>
	                        </div>
	                    </c:if>
	                        

                        <c:if test='${ !myChallengeNameList.contains("카페,간식 지출 줄이기") }'>
	                        <div class="col-md-4 col-sm-6"
	                             onclick="goChallenge('카페,간식 지출 줄이기')">
	                            <div class="team-person text-center">
	                                <img src="${pageContext.request.contextPath }/resources/assets/images/카페,간식.jfif" class="img-responsive" alt="team">
	                                <h6>카페,간식 지출 줄이기</h6>
	                                <p>이번 달 카페,간식 지출을 관리하세요</p>
	                            </div>
	                        </div>
	                    </c:if>
                        
                        
                        <c:if test='${ !myChallengeNameList.contains("편의점,마트 지출 줄이기") }'>
	                        <div class="col-md-4 col-sm-6"
	                             onclick="goChallenge('편의점,마트 지출 줄이기')">
	                            <div class="team-person text-center">
	                                <img src="${pageContext.request.contextPath }/resources/assets/images/편의점,마트.jpg" class="img-responsive" alt="team">
	                                <h6>편의점,마트 지출 줄이기</h6>
	                                <p>이번 달 편의점,마트 지출을 관리하세요</p>
	                            </div>
	                        </div>
	                    </c:if>

                        <c:if test='${ !myChallengeNameList.contains("술,유흥 지출 줄이기") }'>
	                        <div class="col-md-4 col-sm-6"
	                             onclick="goChallenge('술,유흥 지출 줄이기')">
	                            <div class="team-person text-center">
	                                <img src="${pageContext.request.contextPath }/resources/assets/images/술,유흥.jpg" class="img-responsive" alt="team">
	                                <h6>술,유흥 지출 줄이기</h6>
	                                <p>이번 달 술,유흥 지출을 관리하세요</p>
	                            </div>
	                        </div>
	                    </c:if>
	                    
                        <c:if test='${ !myChallengeNameList.contains("쇼핑 지출 줄이기") }'>
	                        <div class="col-md-4 col-sm-6"
	                             onclick="goChallenge('쇼핑 지출 줄이기')">
	                            <div class="team-person text-center">
	                                <img src="${pageContext.request.contextPath }/resources/assets/images/쇼핑.png" class="img-responsive" alt="team">
	                                <h6>쇼핑 지출 줄이기</h6>
	                                <p>이번 달 쇼핑 지출을 관리하세요</p>
	                            </div>
	                        </div>
	                    </c:if>

                        <c:if test='${ !myChallengeNameList.contains("미용 지출 줄이기") }'>
	                        <div class="col-md-4 col-sm-6"
	                             onclick="goChallenge('미용 지출 줄이기')">
	                            <div class="team-person text-center">
	                                <img src="${pageContext.request.contextPath }/resources/assets/images/미용.png" class="img-responsive" alt="team">
	                                <h6>미용 지출 줄이기</h6>
	                                <p>이번 달 미용 지출을 관리하세요</p>
	                            </div>
	                        </div>
	                    </c:if>  
                    </div>
                    <!--End single tab content-->
                    
                    
                    <!--Start single tab content-->
                    <div id="myChallenge">
                        
						<section class="service-section bg-gray section">
						    <div class="container">
						        <div class="section-title text-center">
						            <h3>도전
						                <span>진행 상황</span>
						            </h3>
						        </div>
						        <div class="row items-container clearfix">
						        
						        
						        <c:forEach items="${ myChallenge }" var="challenge">
						        	<div class="item">
						                <div class="inner-box">
						                    
						                    <div class="image-content text-center" style="background-color:#64BEC6">
						                        <h6 style="color:white">${ challenge.challengeName }</h6>
						                        
						                        <c:if test="${ challenge.challengeFail == true }">
						                        <p style="color:white">    도전 목표 : ${ String.format("%,d", challenge.targetAmount) }원 <br>
						                        	       이번 달 지출 : ${ String.format("%,d", challenge.nowBalanceByType) }원 <br>
						                                                               도전 마감일 : ${ challenge.challengeEndDate }
						                              <div class="progress">
													    <div class="progress-bar bg-danger" style="color:red; width:${(challenge.nowBalanceByType/challenge.targetAmount)*100}%">${Math.round((challenge.nowBalanceByType/challenge.targetAmount)*100)}%</div>
													  </div>
													 
							                    	   <span style="color:red; font-weight:bold">
							                    	       실패한 도전입니다.<br> 다음 달에 다시 도전하세요!</span>
							                    	
						                         </p>
						                        
						                        </c:if>
						                        
						                        <c:if test="${ challenge.challengeFail == false }">
						                        <p style="color:white">    도전 목표 : ${ String.format("%,d", challenge.targetAmount) }원 <br>
						                        	       이번 달 지출 : ${ String.format("%,d", challenge.nowBalanceByType) }원 <br>
						                                                               도전 마감일 : ${ challenge.challengeEndDate }
						                               
						                                                               
						                                                               
						                              <div class="progress" style="color:red">
													    <div class="progress-bar" style="width:${(challenge.nowBalanceByType/challenge.targetAmount)*100}%">${Math.round((challenge.nowBalanceByType/challenge.targetAmount)*100)}%</div>
													  </div>
							                    	   <span style="color:white; font-weight:bold">
							                    	       하루 평균 ${ String.format("%,d", challenge.expectedAmount) }원 미만 사용한다면,<br>
							                    	        도전에 성공하실 수 있습니다!</span>
						                         </p>												
						                         </c:if>
						                    </div>
						                </div>
						            </div>
						        </c:forEach> 
  
						        </div>
						    </div>
						</section>                        

                   </div>
                   <!--End single tab content-->
                    
                </div>
            </div>
        </div>
    </div>
</section>	
	
 	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
	<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" /> 
</body>
</html>