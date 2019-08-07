<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../../jquery.ajax-cross-origin.min.js"></script> <!-- 크로스 도메인 이슈해결 -->
<script type="text/javascript">
   $(document).ready(function(){
      /* 
         뉴욕 : New%20York
         캘리포니아 : California
         하와이 : Hawaii
      */
      var city = "New%20York";
      var url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=03b991ee6df0d0733ada010a51bacf08";
      $.ajax({
         type : "get",
         datatype : "json",
         crossOrigin : true, // 크로스 도메인 이슈해결
         url: url,
         success : function(data) {
            var resp = JSON.parse(data);
            $("#result").html("현재온도 : " + (resp.main.temp - 273.15).toFixed(1) + "<br>현재날씨 : " + resp.weather[0].main);
         }
      });
   });
   
</script>
</head>
<body>
<span id="result"></span>
</body>
</html>