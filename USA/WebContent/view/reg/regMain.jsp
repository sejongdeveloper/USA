<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/regstyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegMain</title>
</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<div class="body">
<div class="bodyin">
<div class="regMain_picture regMain_div">
<c:forEach var="picture" items="${ reg_filenameList }" >
<img class="reg_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ picture.reg_filename }"><!-- 사진 파일 가져오기 -->
</c:forEach>
<div class="regMain_regname">${ reg_filenameList[0].reg_name }</div><!-- 지역이름 -->
</div>

<div class="regMain_map regMain_div" id="map">지도</div><!-- 구글지도 -->

<div class="regMain_info regMain_div"><div class="regMain_infotitle">날씨</div><!-- 날씨 -->
<div class="regMain_infocontents" id="reg_weather">내용</div>
<div class="regMain_infocontents" id="reg_weather2">내용</div></div>

<div class="regMain_info2 regMain_div"><div class="regMain_infotitle">현재시간</div><!-- 시간 -->
<div class="regMain_infocontents" id="reg_time">시간</div></div>

<div class="regMain_info3 regMain_div"><div class="regMain_infotitle ">비행시간</div><div class="regMain_infocontents3">(직항)${ regname_vo.regFlight }시간</div></div>
<div class="regMain_moreinfo regMain_div">
	<div class="regMain_infotitle">더 많은 정보</div>
	<a href="regView.do?reg_name=${ reg_filenameList[0].reg_name }"><div class="regMain_one">기본 정보</div></a>
	<a href="locList.do?loc_regname=${ reg_filenameList[0].reg_name }"><div class="regMain_two">관광지 정보</div></a>
</div>
</div>
</div>
<jsp:include page="/view/main/footer.html" />
</body>
<script type="text/javascript">
    var pic = 0;   //이미지에 접근하는 인덱스
    $(document).ready(function(){
        slideShow();
        calcTime();
        weatherShow();
    });
    
    // 사진 슬라이드쇼
    function slideShow() {

	    var x = document.getElementsByClassName("reg_img");  // class가 reg_img인 것들 가져옴 
	    for (i = 0; i < x.length; i++) {
	       x[i].style.display = "none";   // 처음에 전부 display를 none으로
	    }
	    
	    x[pic].style.display = "block";  // 해당 인덱스는 block으로
	    
	    pic++; // 보여질 사진 순서 증가
	    
	    if (pic == x.length) {
	        pic = 0;  //인덱스가 초과되면 0로 변경
	    }
	    
	    setTimeout(slideShow, 2000);   //함수를 2초마다 호출
 	
	}
    
    // 시차 계산
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
    
    // 현재 날씨
    function weatherShow(){
        var city = "${ regname_vo.regNameEng }";
        var url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=03b991ee6df0d0733ada010a51bacf08";
        $.getJSON(url+"&callback=?", function(data) {
           $("#reg_weather").html(data.weather[0].main);
           $("#reg_weather2").html("<br>"+(data.main.temp- 273.15).toFixed(1)+"ºC");
        });
     
    }
    
    // 구글 지도
    function initMap() { 
      var reg = '${ reg_filenameList[0].reg_name }';
      var uluru; // 위도,경도 값
      if(reg == '뉴욕'){
    	uluru = {lat: 40.713026, lng: -74.006230};
  	  } else if(reg == '하와이') {
  		uluru = {lat: 19.559595, lng: -155.694037};
  	  } else if(reg == '캘리포니아') {
  		uluru = {lat: 36.484303, lng: -119.760251};
      }
      var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        center: uluru // 지도 시작위치
      });
      var marker = new google.maps.Marker({
        position: uluru, // 포인트 위치
        map: map
      });
    }
</script>
<script async defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC6DBIjjrf8gvxa2LtZgeXngddkxQw79D8&callback=initMap">
</script>

</html>