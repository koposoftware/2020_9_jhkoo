<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/table.css">  
<script>

	function category(param){
		location.href = "${ pageContext.request.contextPath}/categoryDetail/${depositAccount.getAccountNumber()}/${month}/" + param;
	}

	function goMonthAgo(){
		location.href = "${ pageContext.request.contextPath}/goMonthAgo/${depositAccount.getAccountNumber()}/${month}";
	}
	
	function goMonthAhead(){
		location.href = "${ pageContext.request.contextPath}/goMonthAhead/${depositAccount.getAccountNumber()}/${month}";
	}

	// 검색 기능 부트스트랩
	$(document).ready(function(){
		  $("#myInput").on("keyup", function() {
		          var value = $(this).val().toLowerCase();
		          $("#myTable tr").filter(function() {
		            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		          });
		        });
		      }); 
</script>

<style>
	tr.even {	
		background-color: #F7F9FC;	
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />


<div class="container">
  <h2>&nbsp;</h2>
  <div class="section-title text-center">
            <h3>${ depositAccount.getBankBookKey() }
                <span>상세 내역입니다</span>
           	</h3> 
            <p>총 잔액 : ${ String.format( "%,d",depositAccount.getBalance() ) }원<br>
                              계좌 번호 : ${ depositAccount.getAccountNumber() }<br>
               <c:if test="${ depositAccount.getMainAccount() == 'Y' }">
               		<span style="color:blue">대표 통장</span>
               </c:if>
            	    
            </p>
  </div>
  
  
  
  
<div class="container">
  <h3>${ month }월 내역 조회중입니다</h3>
  
  <div class="btn-group" style="float:left;">
    <button type="button" class="btn btn-style-one" onclick="goMonthAgo()">${month- 1}월 내역 보기</button>
 	<button type="button" class="btn btn-style-one" onclick="goMonthAhead()">${ month + 1 }월 내역 보기</button>
  </div>
  
  
  <div class="dropdown" style="float:left;">
	  <button class="btn btn-secondary dropdown-toggle btn-style-one" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    카테고리별 내역
	  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
     <button type="button" class="btn btn-style-one" onclick="category('1')">입금</button>
    <button type="button" class="btn btn-style-one" onclick="category('2')">이체</button>
    <button type="button" class="btn btn-style-one" onclick="category('3')">식비</button>
    <button type="button" class="btn btn-style-one" onclick="category('4')">카페,간식</button>
    <button type="button" class="btn btn-style-one" onclick="category('5')">편의점,마트</button>
    <button type="button" class="btn btn-style-one" onclick="category('6')">술,유흥</button>
    <button type="button" class="btn btn-style-one" onclick="category('7')">쇼핑</button>
    <button type="button" class="btn btn-style-one" onclick="category('8')">취미,여가</button>
    <button type="button" class="btn btn-style-one" onclick="category('9')">미용</button>
    <button type="button" class="btn btn-style-one" onclick="category('10')">주거,통신</button>
  </div>
  </div>
  
  <div class="btn-group" style="float:left;">
  	 <input class="form-control" id="myInput" type="text" placeholder="search" style="width:150px; height:45px;">
  </div>
  
  <div align="center" style="float:right;">
	 <button type="submit" class="btn-style-one" 
	   onclick="location.href='${ pageContext.request.contextPath }/account '">돌아가기</button>
  </div>

  	<!-- 페이지 선택 -->
        <div class="styled-pagination" style="align:center">
            <ul>
                <li><a class="prev" href="#"><span class="fa fa-angle-left" aria-hidden="true"></span></a></li>
                <li><a href="#" class="active">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a class="next" href="#"><span class="fa fa-angle-right" aria-hidden="true"></span></a></li>
            </ul>
        </div> 

</div>


  
  <hr>          
  <div id="detailList" class="table-wrapper">
  <table class="table table-hover fl-table">
    <thead>
      <tr class="scrollLocation">
        <th>거래 날짜</th>
        <th>거래 대상 </th>
        <th>카테고리</th>
        <th>거래 금액</th>
      </tr>
    </thead>
    <tbody id="myTable">
    
  
     <c:forEach items="${ depositDetailList }" var="depositDetail" varStatus="loop">
      <tr >
       <td >
       	${ depositDetail.getLogDate() } 
       </td>
       <td>${ depositDetail.getToName() }</td>
       <td>${ depositDetail.getLogTypeKey() }</td>
       <c:choose>
       	<c:when test="${depositDetail.getLogTypeKey() eq '입금'}">
       		<td><span style="color:green;">+ ${ String.format("%,d", depositDetail.getAmount() ) } 원</span></td>
       	</c:when>
       	<c:otherwise>
       		<td><span style="color:red;">- ${ String.format("%,d", depositDetail.getAmount()) } 원</span></td>
       	</c:otherwise>
       </c:choose>

      </tr>
     </c:forEach>

    </tbody>
  </table>  
  </div> 
  
 
  
</div>







<!--  
<script>

 
 
 var lastScrollTop = 0;
 var easeEffect = 'easeInQuint';
 
 // 1. 스크롤 이벤트 최초 발생
 $(window).scroll(function(){
	 
	 var currentScrollTop = $(window).scrollTop();
	 
	 /*
	 	========== 다운 스크롤인 상태 ==============
	 */
	 if(currentScrollTop - lastScrollTop > 0){
		 //down-scroll : 현재 게시글 다음의 글을 불러온다.
		 console.log("down-scroll");
	 
	 
	// 2. 현재 스크롤의 top좌표가 > (게시글을 불러온 화면 height - 윈도우창의 height)되는 순간 
	//    현재스크롤의 위치가 화면의 보이는 위치보다 크다면
	if($(window).scrollTop() >= ($(document).height() - $(window).height())){
		// 3. class가 scrolling인 것의 요소 중 마지막인 요소를 선택한 다음 그것의 data-bno속성 값을 받아온다.
		//    즉, 현재 뿌려진 게시글의 마지막 bno값을 읽어오는 것(이 다음의 게시글들을 가져오기 위해 필요)
		var lastLogDate = $(".scrolling:last").attr("data-logDate");
		
		// 4. ajax를 이용하여 현재 뿌려진 게시글의 마지막 logDate를 서버로 보내어 그 다음 달의 게시물 데이터 받아온다.
		$.ajax({
			type:'post',
			url:'${ pageContext.request.contextPath }/infiniteScrollDown',
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"POST"
			},
			dataType:'json',
			data:JSON.stringify({
				logDate:lastLogDate,
				accountNumber:${ depositAccount.getAccountNumber() }
			}),
			success:function(data){
				
				var str="";
				
				//5. 받아온 데이터가 ""이거나 null이 아닌 경우에 DOM handling을 해준다
				if(data != ""){
					// 6. 서버로부터 받아온 data가 list이므로 이각각의 원소에 접근하려면 each문을 사용한다.
					$(data).each(
						// 7. 새로운 데이터를 갖고 html코드 형태의 문자열을 만들어준다.
						function(){
							console.log(this);
							str += "<tr class=" + "'listToChange'" + ">"
						    +      "<td class=" +"'scrolling'" + "data-logDate='" + this.logDate + "'>"
						    +          this.logDate
						    +       "</td>"
						    +       "<td>" + this.toName + "</td>"
						    +       "<td>" + this.logTypeKey + "</td>"
						    +       "<td>" + this.amount + "</td>"
						    +       "<td>" + this.amount + "</td>"
						    +   "</tr>";
						}
					);//each
					
					// 8. 이전까지 뿌려졌던 데이터를 비워주고, <th>헤더 바로 밑에 위에서 만든 str을 뿌려준다.
					$(".listToChange").empty();
					$(".scrollLocation").after(str);
				}
				else{	//데이터 없다면
					alert("더 불러올 ㄹ데이터가 없습니다.");
				}
			}
		});
	 
		//여기서 class가 listToChange인 것중 가장 처음인 것을 찾아서 그 위치로 이동
		var position = $(".listToChange:first").offset(); //위치값
		
		//이동 위로 부터 positioin.top px 위치로 스크롤 하는 것. 그걸 500ms 동안 애니메이션으로 이루어짐.
		$('html,body').stop().animate({scrollTop:position.top}, 600, easeEffect);
	 	
     } //if: 현재 스크롤의 top좌표가 > (게시글을 불러온 화면 height - 윈도우창의 height)되는 순간 
     	// lastScrollTop을 현재 currentScrollTop으로 갱신
     	lastScrollTop = currentScrollTop;
 	}//다운스크롤인 상태
     
 	/*
 			=============== 업 스트롤인 상태 ============
 	*/
 	else{
 		console.log("up-scroll");
 		
 		// 2
 		if($(window).scrollTop() <= 0){
 			
 			// 3
 			var firstLogDate = $(".scrolling:first").attr("data-logDate");
 			
 			// 4
 			$.ajax({
 				type:'post',
 				url:'${ pageContext.request.contextPath }/infiniteScrollUp',
 				headers:{
 					"Content-Type":"application/json",
 					"X-HTTP-Method-Ovrride":"POST",
 				},
 				dataType:'json',
 				data:JSON.stringify({
 					logDate:firstLogDate,
 					accountNumber:${ depositAccount.getAccountNumber() }
 				}),
 				success:function(data){
 					var str="";
 					
 					// 5
 					if(data != ""){
 						// 6
 						$(data).each(
 							// 7
 							function(){
 								console.log(this);
 								str += "<tr class=" + "'listToChange'" + ">"
 							    +      "<td class=" +"'scrolling'" + "data-logDate='" + this.logDate + "'>"
 							    +          this.logDate
 							    +       "</td>"
 							    +       "<td>" + this.toName + "</td>"
 							    +       "<td>" + this.logTypeKey + "</td>"
 							    +       "<td>" + this.amount + "</td>"
 							    +       "<td>" + this.amount + "</td>"
 							    +   "</tr>"; 
 							}	
 						)//each
 						// 8
 						$(".listToChange").empty();
 						$(".scrollLocation").after(str);
 					}//if
 					else{
 						// 9
 						alert("더 불러올 데이터가 없습니다.");
 					}
 				}
 			});
 			
 			var position=($(document).height() - $(window).height()) -10;
 			
 			$('html,body').stop().animate({scrollTop:position}, 600, easeEffect);
 		}//if 현재 스크롤의 top 좌표가 <=0 되는 순간
 		
 		// lastScrollTop을 현재 currentScrollTop으로 갱신
 		lastScrollTop = currentScrollTop;
 	}// else :업 스크롤인 상태
 	
 }); //scroll event

</script>

-->




<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>