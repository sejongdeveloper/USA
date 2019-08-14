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
	  	startWeather();
     	async function slideWeather(i) {
        while (true) {
          await new Promise(resolve => setTimeout(resolve, 2000));
          var city = [ "Hawaii", "California", "Delaware", "Pennsylvania", "New%20Jersey", "Georgia", "Connecticut", "Massachusetts", "Maryland",
        	 "South Carolina", "New Hampshire", "Virginia", "North Carolina", "Rhode Island", "Vermont", "Kentucky", "	Tennessee", "Ohio", "Louisiana", 
        	 "Indiana", "Mississippi", "Illinois", "Alabama", "Maine", "Missouri", "Arkansas", "Michigan", "Florida", "Texas", "Iowa", "Wisconsin", "Minnesota",
        	 "Oregon", "Kansas", "West Virginia", "Nevada", "Nebraska", "Colorado", "North Dakota", "South Dakota", "Montana", "Washington", "Idaho",
        	 "Wyoming", "Utah", "Oklahoma", "New Mexico", "Arizona", "	Alaska" ];
         
         var url = "http://api.openweathermap.org/data/2.5/weather?q=" + city[i] + "&appid=03b991ee6df0d0733ada010a51bacf08";
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
      
  function startWeather(){
	  var city = "New%20York";
      
      var url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=03b991ee6df0d0733ada010a51bacf08";
       $.getJSON(url+"&callback=?", function(data) {
    		$(".weacountry").html(data.name);
    		$(".weatemp").html((data.main.temp- 273.15).toFixed(1)+'˚C');
    		$(".weaweather").html(data.weather[0].main);
       });
       
       i++;
  }

</script>
</head>
<body>
<div class="weatherframe">
<div class="weatitle">날씨 정보</div>
<div class="weacountry">Loading</div>
<div class="weaweather">Loading</div>
<div class="weatemp">Loading</div>
</div>
</body>
</html>