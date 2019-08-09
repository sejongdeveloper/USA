<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/USA/css/style.css">
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
</head>
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<body>
	<div class="body">
	<div class="locpictureframe">
		<c:forEach var="picture" items="${ loc_allNameFileList }" >
		<div class="locpicture">
			<img class="reg_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ picture.loc_filename }">
		<div class="regMain_regname">${ picture.loc_name }</div>
		</div>
		</c:forEach>
		</div>
	</div>
	<div class="tablezone">
		<div class="buytable">
			<table>
				<tr>
					<td>삽니다</td>
				</tr>
				<tr>
					<td>내용</td>
				</tr>
			</table>
		</div>
		<div class="celltable">
			<table>
				<tr>
					<td>팝니다</td>
				</tr>
				<tr>
					<td>내용</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
<jsp:include page="/view/main/footer.html" />
</body>
<script type="text/javascript">
	$(document).ready(function(){
	    slideShow();
	});
	
	function slideShow() {
	var i;
	var x = document.getElementsByClassName("locpicture");
	for (i = 0; i < x.length; i++) {
	   x[i].style.display = "none";
	}
	index++;
	if (index > x.length) {
	    index = 1;
	}   
	x[index-1].style.display = "inline-block";
	setTimeout(slideShow, 2000);
		
	}
	
</script>
</html>