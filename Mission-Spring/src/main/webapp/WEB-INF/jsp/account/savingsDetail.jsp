<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://www.gstatic.com/charts/loader.js'></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/assets/css/table.css">  
<script>
google.charts.load('49', {'packages': ['vegachart']}).then(drawChart);

function drawChart() {
  const dataTable = new google.visualization.DataTable();
  dataTable.addColumn({type: 'string', 'id': 'category'});
  dataTable.addColumn({type: 'number', 'id': 'amount'});
  dataTable.addRows(
	${str}	  
  );


  
  const options = {
		  		  
    "vega": {
      "$schema": "https://vega.github.io/schema/vega/v4.json",
      "width": 900,
      "height": 200,
      "padding": 5,

      'data': [{'name': 'table', 'source': 'datatable'}],

      "signals": [
        {
          "name": "tooltip",
          "value": {},
          "on": [
            {"events": "rect:mouseover", "update": "datum"},
            {"events": "rect:mouseout",  "update": "{}"}
          ]
        }
      ],

      "scales": [
        {
          "name": "xscale",
          "type": "band",
          "domain": {"data": "table", "field": "category"},
          "range": "width",
          "padding": 0.05,
          "round": true
        },
        {
          "name": "yscale",
          "domain": {"data": "table", "field": "amount"},
          "nice": true,
          "range": "height"
        }
      ],

      "axes": [
        { "orient": "bottom", "scale": "xscale" },
        { "orient": "left", "scale": "yscale" }
      ],

      "marks": [
        {
          "type": "rect",
          "from": {"data":"table"},
          "encode": {
            "enter": {
              "x": {"scale": "xscale", "field": "category"},
              "width": {"scale": "xscale", "band": 0.6},
              "y": {"scale": "yscale", "field": "amount"},
              "y2": {"scale": "yscale", "value": 0}
            },
            "update": {
              "fill": {"value": "#48BDC5"}
            },
            "hover": {
              "fill": {"value": "#319299"}
            }
          }
        },
        {
          "type": "text",
          "encode": {
            "enter": {
              "align": {"value": "center"},
              "baseline": {"value": "bottom"},
              "fill": {"value": "#319299"}
            },
            "update": {
              "x": {"scale": "xscale", "signal": "tooltip.category", "band": 0.5},
              "y": {"scale": "yscale", "signal": "tooltip.amount", "offset": -2},
              "text": {"signal": "tooltip.amount"},
              "fillOpacity": [
                {"test": "datum === tooltip", "value": 0},
                {"value": 1}
              ]
            }
          }
        }
      ]
    }
  };

  const chart = new google.visualization.VegaChart(document.getElementById('chart-div'));
  chart.draw(dataTable, options);
}

</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/include/header.jsp" /> 


<br><br>

<div class="container">
  <h2>&nbsp;</h2>
  <div class="section-title text-center">
            <h3>${ savingsAccount.getBankBookKey() }
                <span>상세 내역입니다</span>
           	</h3> 
            <p>총 잔액 : ${ String.format( "%,d",savingsAccount.getBalance() ) }원<br>
                              계좌 번호 : ${ savingsAccount.getAccountNumber() }<br>
              	별칭 : ${ savingsAccount.getNickName() }     
            </p>
  </div>
</div>

<div align="center">
 <div id="chart-div"></div>
</div>

<hr>

<!-- Contact Section -->
<section class="blog-section section style-three pb-0">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="contact-area style-two">
                	<br>
                	<br>
                	<br>
                	<div class="section-title">
                        <h3>월 평균 입금액은<br> <span>${ String.format("%,d", savingsAccount.avgAmount ) } 원입니다.</span></h3>
                    </div>
                	<br>
                	
                    <div class="section-title">
                        <h3>만기일까지<br> <span>${ savingsAccount.getSavingMonth() }개월 남았습니다!</span></h3>
                    </div>
                    
                    <form name="rform" class="default-form contact-form">
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                            
                                
                                                           
                            </div>
                        </div>
                    </form>
                </div>                      
            </div>
            <div class="col-md-6 col-sm-12 col-xs-12">
                
                 
                <div class="container table-wrapper">
                 <table class="table table-hover fl-table" style="width:50%">
                  <thead>
                  	<tr>
                  		<th>날짜</th>
                  		<th>회차</th>
                  		<th>입금액</th>
                  	</tr>
                  </thead>
                  	
                  <tbody>
                   	
                   	<c:forEach items="${ savingsDetailList }" var="savingsDetail" varStatus="loop">
                   		<tr>
	                   		<td>${savingsDetail.logDate }</td>
	                   		<td>${savingsDetail.logPk }회차</td>
	                   		<td>+ ${ String.format("%,d", savingsDetail.amount ) } 원</td>
	                   	</tr>
                   	</c:forEach>
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