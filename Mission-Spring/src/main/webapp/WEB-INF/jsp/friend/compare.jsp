<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://www.gstatic.com/charts/loader.js'></script>
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


<br><br>

 <div id="chart_div"></div>
 

<br>
<br>
<br>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
<jsp:include page="/WEB-INF/jsp/include/scroll.jsp" />
</body>
</html>