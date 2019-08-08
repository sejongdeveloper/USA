<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/view/reg/regstyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegMain</title>
</head>
<body>
<div class="regMain_picture regMain_div">
<c:forEach var="picture" items="${ reg_filenameList }" >
<img class="reg_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ picture.reg_filename }">
</c:forEach>
<div class="regMain_regname">${ reg_filenameList[0].reg_name }</div>
</div>

<div class="regMain_map regMain_div" id="map">지도</div>
<div class="regMain_info regMain_div"><div class="regMain_infotitle">날씨</div>
<div class="regMain_infocontents" id="reg_weather">내용</div>
<div class="regMain_infocontents" id="reg_weather2">내용</div></div>

<div class="regMain_info2 regMain_div"><div class="regMain_infotitle">현재시간</div>
<div class="regMain_infocontents" id="reg_time">시간</div></div>

<div class="regMain_info3 regMain_div"><div class="regMain_infotitle ">비행시간</div><div class="regMain_infocontents3">(직항)${ regname_vo.regFlight }시간</div></div>
<div class="regMain_moreinfo regMain_div">
	<div class="regMain_infotitle">더 많은 정보</div>
	<a href="regView.do?reg_name=${ reg_filenameList[0].reg_name }"><div class="regMain_one">기본정보</div></a>
	<a href="locList.do?loc_regname=${ reg_filenameList[0].reg_name }"><div class="regMain_two">관람명소</div></a>
</div>

</body>

<script type="text/javascript">
    var index = 0;   //이미지에 접근하는 인덱스
    $(document).ready(function(){
        slideShow();
        calcTime();
    })
    
    function slideShow() {
    var i;
    var x = document.getElementsByClassName("reg_img");  //slide1에 대한 dom 참조
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";   //처음에 전부 display를 none으로 한다.
    }
    index++;
    if (index > x.length) {
        index = 1;  //인덱스가 초과되면 1로 변경
    }   
    x[index-1].style.display = "block";  //해당 인덱스는 block으로
    setTimeout(slideShow, 2000);   //함수를 2초마다 호출
 	
	}
    
    function calcTime() {
		var time = '${ regname_vo.regGmt }';
        var sicha = eval(19 + time);
        // create Date object for current location
        var d = new Date();

        // convert to msec
        // subtract local time zone offset
        // get UTC time in msec
        var utc = d.getTime() + (d.getTimezoneOffset() * 60000);

        // create new Date object for different city
        // using supplied offset
        var nd = new Date(utc + (3600000*time));

        // return time as a string
        var result = nd.toLocaleString();
        document.getElementById("reg_time").innerHTML=result + "<br><br><font size=5>시차</font><br>" + sicha + "시간";
        setTimeout(calcTime, 1000);
    }
    
    $(document).ready(function(){
        var city = "${ regname_vo.regNameEng }";
        var url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=03b991ee6df0d0733ada010a51bacf08";
        $.getJSON(url+"&callback=?", function(data) {
           $("#reg_weather").html(data.weather[0].main);
           $("#reg_weather2").html("<br>"+(data.main.temp- 273.15).toFixed(1)+"ºC");
        });
     
     });
    
    function initMap() { 
      var reg = '${ reg_filenameList[0].reg_name }';
      var uluru;
      if(reg == '뉴욕'){
    	uluru = {lat: 40.713026, lng: -74.006230};
  	  } else if(reg == '하와이') {
  		uluru = {lat: 19.559595, lng: -155.694037};
  	  } else if(reg == '캘리포니아') {
  		uluru = {lat: 36.484303, lng: -119.760251};
      }
      var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        center: uluru
      });
      var marker = new google.maps.Marker({
        position: uluru,
        map: map
      });
    }
</script>
<script async defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC6DBIjjrf8gvxa2LtZgeXngddkxQw79D8&callback=initMap">
</script>

</html>