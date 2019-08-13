<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="/USA/css/style.css">
<script type="text/javascript">
   var i = 0;
   
  $(document).ready(function(){
     async function slideWeather(i) {
        while (true) {
          await new Promise(resolve => setTimeout(resolve, 2000));
          var city = [ "Hawaii", "New%20York", "California" ];
         
         var url = "h";
          $.getJSON(url+"&callback=?", function(data) {
       		$(".weacountry").html(data.name);
       		$(".weatemp").html((data.main.temp- 273.15).toFixed(1)+'˚C');
       		$(".weaweather").html(data.weather[0].main);
          });
          
          i++;
          
          if(i == city.length) i = 0;  
        }
      }
     slideWeather(i)
     
   });
   

   
         

</script>
</head>
<body>
<div class="weatherframe">
<div class="weatitle">날씨 정보</div>
<div class="weacountry">국가</div>
<div class="weatemp">온도</div>
<div class="weaweather">날씨</div>
</div>
</body>
</html>