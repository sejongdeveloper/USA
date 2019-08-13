<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/USA/css/style.css">
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
</head>
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<body>
	<div class="body">
	<div class="locpictureframe">
		<p class="loctitle">&lt; 인기관광지 TOP3 &gt;</p>
		<c:forEach var="picture" items="${ loc_allNameFileList }" >
		<div class="locpicture">
			<a href="locView.do?loc_name=${ picture.loc_name }">
			<img class="reg_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ picture.loc_filename }">
			</a>
		<div class="regMain_regname">${ picture.loc_name }</div>
		</div>
		</c:forEach>
		</div>

	<br><br>
	<div class="tablezone">
		<div class="buytable">
			<table>
				<tr>
					<td><p class="tabletitle">삽니다</p></td>
					<td><a href="list.do?tra_head=삽니다&pagesize=${pagesize }&condition=${condition}" style="text-decoration: none;">더보기</a></td>
				<c:forEach var="buyvo" items="${buyvo }">
				<tr>
				<td>
				<p class="tablecontents">
				<c:choose>
				<c:when test="${fn:length(buyvo.tra_subject) gt 3}">
				<a href="./content.do?num=${buyvo.tra_num }" style="color:black; display: 'block;  ' ">${fn:substring(buyvo.tra_subject, 0, 2)}...</a>
        		</c:when>
        		<c:otherwise>
        		<a href="./content.do?num=?${buyvo.tra_num }" style="color:black; display: 'block;  ' ">${buyvo.tra_subject}</a>
        		</c:otherwise>
        		</c:choose>
    			</p>
        		</td>
        		</tr>
        </c:forEach>
			</table>
		</div>


		<div class="celltable">
			<table>
				<tr>
				<td><p class="tabletitle">팝니다</p></td>
				<td><a href="list.do?tra_head=팝니다&pagesize=${pagesize }&condition=${condition}" style="text-decoration: none;">더보기</a></td>
				</tr>
				<c:forEach var="sellvo" items="${sellvo }">
		<tr>
		<td>
		<p class="tablecontents">
				<c:choose>
				<c:when test="${fn:length(sellvo.tra_subject) gt 10}">
				<a href="./content.do?num=${sellvo.tra_num }" style="color:black; display: 'block;  ' ">${fn:substring(sellvo.tra_subject, 0, 9)}...</a>
        		</c:when>
        		<c:otherwise>
        		<a href="./content.do?num=${sellvo.tra_num }" style="color:black; display: 'block; ' ">${sellvo.tra_subject}</a>
        		</c:otherwise>
        		</c:choose>
        </p>
        </td>
        </tr>
        		</c:forEach>
	</table>
	</div>
	</div>
	</div>
<jsp:include page="/view/main/footer.html" />
</body>
<script type="text/javascript">
	var abc = 0;
	$(document).ready(function(){
	    slideShow();
	});
	
	function slideShow() {
		var x = document.getElementsByClassName("locpicture");
		
		for (i = 0; i < x.length; i++) {
		   x[i].style.display = "none";
		}
		
		x[abc].style.display = "inline-block";
		
		abc++;
		
		if (abc == x.length) {
			abc = 0;
		}
		
		setTimeout(slideShow, 2000);	
	}
</script>
</html>