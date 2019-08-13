<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/USA/css/style.css">
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<title>header</title>
</head>
<body>

	<div class="header">
    <ul class="dropmenu">
      <li class="headercenter"><a href="main.do" class="headertitle">sitetitle</li></a>
      <a href="#" class="headercontent"><li class="headercenter"><p>Information</p></a>
         <ul>
           <a href="regMap.do" class="headercontentmap"><li><p>지도</p></li></a>
           <a href="#" class="headercontentreg"><li><p>지역</p>
           		<ul class="dropmenu3">
           			<a href="regMain.do?regname=뉴욕" class="headercontentin"><li><p>뉴욕</p></li></a>
           			<a href="regMain.do?regname=하와이" class="headercontentin"><li><p>하와이</p></li></a>
           			<a href="regMain.do?regname=캘리포니아" class="headercontentin"><li><p>캘리포니아</p></li></a>
           			<a href="regMain.do?regname=델라웨어" class="headercontentin"><li><p>델라웨어</p></li></a>
           			<a href="regMain.do?regname=펜실베이니아" class="headercontentin"><li><p>펜실베이니아</p></li></a>
           			<a href="regMain.do?regname=워싱턴" class="headercontentin"><li><p>워싱턴</p></li></a>
           			<a href="regMain.do?regname=뉴저지" class="headercontentin"><li><p>뉴저지</p></li></a>
           			<a href="regMain.do?regname=조지아" class="headercontentin"><li><p>조지아</p></li></a>
           			<a href="regMain.do?regname=코네티컷" class="headercontentin"><li><p>코네티컷</p></li></a>
           			<a href="regMain.do?regname=매사추세츠" class="headercontentin"><li><p>매사추세츠</p></li></a>
           			<a href="regMain.do?regname=메릴랜드" class="headercontentin"><li><p>메릴랜드</p></li></a>
           			<a href="regMain.do?regname=사우스캐롤라이나" class="headercontentin"><li><p>사우스캐롤라이나</p></li></a>
           		</ul>
           </li></a>
         </ul>
      </li>
      <li class="headercenter"><a href="#" class="headercontent">Community</a>
          <ul>
           <a href="#" class="headercontentqa"><li><p>Q & A</p></li></a>
           <a href="list.do" class="headercontenttra"><li><p>거래</p></li></a>
         </ul>
      </li>
      <a href="event.do" class="headercontent"><li class="headercenter"><p>Event</p></li></a>
    </ul>
	<c:if test="${empty member }">
      <div class="headerSignin"><a href="/USA/memLoginForm.do" class="signin">Sign In</a></div>
    </c:if>
    <c:if test="${not empty member }">
      <div class="headerSignin"><span class="signin"><span class="member">${nickname }님 환영합니다^^ <a id="me" href="memUpdateForm.do">내정보</a></span><a href="/USA/memLogout.do" class="signin">Sign Out</a></div>
    </c:if>
	</div>
	
</body>
</html>