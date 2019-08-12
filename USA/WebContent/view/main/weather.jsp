<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
   var i = 0;
   
  $(document).ready(function(){
	  slideWeather(i);
   });
   
   function slideWeather(i) {

		var city = [ "Hawaii", "New%20York", "California" ];
		console.log(city[i]);
		var url = "http://api.openweathermap.org/data/2.5/weather?q=" + city[i] + "&appid=03b991ee6df0d0733ada010a51bacf08";
	    $.getJSON(url+"&callback=?", function(data) {
	    	$("#result").html("도시이름 : " + data.name + "<br>현재온도 : " + (data.main.temp- 273.15).toFixed(1) + "<br>날씨 : " + data.weather[0].main);
	 
	    });
	    
	    i++;
	    
	    if(i == city.length) i = 0;
	   	setTimeout(slideWeather(i), 2000);
   }

</script>
</head>
<body>
<div id="result"></div>
</body>
</html>