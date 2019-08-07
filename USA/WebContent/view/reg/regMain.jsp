<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 
<link rel="stylesheet" href="regstyle.css">
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegMain</title>
</head>
<body>
<div class="regMain_picture regMain_div">
<c:forEach var="picture" items="${ reg_filenameList }" >
<img class="reg_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ picture }">
</c:forEach>
</div>
<div class="regMain_regname regMain_div">${ reg_name }</div>

<div class="regMain_map regMain_div">지도</div>
<div class="regMain_info regMain_div"><div class="regMain_infotitle">날씨</div><div class="regMain_infocontents">API</div></div>

<div class="regMain_info regMain_div"><div class="regMain_infotitle">시차</div><div id=time class="regMain_infocontents">시간</div></div>

<div class="regMain_info regMain_div"><div class="regMain_infotitle ">비행시간</div><div class="regMain_infocontents">내용</div></div>
<div class="regMain_moreinfo regMain_div">
	<div class="regMain_infotitle">더 많은 정보</div>
	<div class="regMain_one"><a href="regView.do?reg_name=${ reg_name }">기본정보</a></div>
	<div class="regMain_two"><a href="locList.do?loc_regname=${ reg_name }">관람명소</a></div>
</div>
</body>
<script>
    var index = 0;   //이미지에 접근하는 인덱스
    $(document).ready(function(){
        slideShow();
        timeShow();
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
	
</script>
</html>