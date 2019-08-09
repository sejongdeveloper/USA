<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="${ pageContext.request.contextPath }/view/reg/regstyle.css">
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegView</title>
</head>
<body>
<div class="regView_regname">&lt;&nbsp;${ reg_list[0].reg_name }&nbsp;&gt;</div>
<div class="regView_picture">
<c:forEach var="picture" items="${ reg_list }">
<img class="reg_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ picture.reg_filename }">
</c:forEach>
</div>
<c:forEach var="post" items="${ reg_list }">
	<div class="regView_posts">
	<div class="regView_title">${ post.reg_subject }</div>
	<div class="regView_contents">${ post.reg_contents }</div>
	</div>
</c:forEach>
</body>
<script>
    var index = 0;   //이미지에 접근하는 인덱스
    $(document).ready(function(){
        slideShow();
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