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
           		</ul>
           		<ul class="dropmenu4">	
           			<a href="regMain.do?regname=메릴랜드" class="headercontentin"><li><p>메릴랜드</p></li></a>
           			<a href="regMain.do?regname=사우스캐롤라이나" class="headercontentin"><li><p>사우스캐롤라이나</p></li></a>
           			<a href="regMain.do?regname=뉴햄프셔" class="headercontentin"><li><p>뉴햄프셔</p></li></a>
           			<a href="regMain.do?regname=버지니아" class="headercontentin"><li><p>버지니아</p></li></a>
           			<a href="regMain.do?regname=노스캐롤라이나" class="headercontentin"><li><p>노스캐롤라이나</p></li></a>
           			<a href="regMain.do?regname=로드아일랜드" class="headercontentin"><li><p>로드아일랜드</p></li></a>
           			<a href="regMain.do?regname=버몬트" class="headercontentin"><li><p>버몬트</p></li></a>
           			<a href="regMain.do?regname=켄터키" class="headercontentin"><li><p>켄터키</p></li></a>
           			<a href="regMain.do?regname=테네시" class="headercontentin"><li><p>테네시</p></li></a>
           			<a href="regMain.do?regname=오하이오" class="headercontentin"><li><p>오하이오</p></li></a>
           		</ul>
           		<ul class="dropmenu5">
           			<a href="regMain.do?regname=인디애나" class="headercontentin"><li><p>인디애나</p></li></a>
           			<a href="regMain.do?regname=미시시피" class="headercontentin"><li><p>미시시피</p></li></a>
           			<a href="regMain.do?regname=일리노이" class="headercontentin"><li><p>일리노이</p></li></a>
           			<a href="regMain.do?regname=앨라배마" class="headercontentin"><li><p>앨라배마</p></li></a>
           			<a href="regMain.do?regname=메인" class="headercontentin"><li><p>메인</p></li></a>
           			<a href="regMain.do?regname=미주리" class="headercontentin"><li><p>미주리</p></li></a>
           			<a href="regMain.do?regname=아칸소" class="headercontentin"><li><p>아칸소</p></li></a>
           			<a href="regMain.do?regname=미시간" class="headercontentin"><li><p>미시간</p></li></a>
           			<a href="regMain.do?regname=플로리다" class="headercontentin"><li><p>플로리다</p></li></a>
           			<a href="regMain.do?regname=텍사스" class="headercontentin"><li><p>텍사스</p></li></a>
           		</ul>
           		<ul class="dropmenu6">
           			<a href="regMain.do?regname=아이오와" class="headercontentin"><li><p>아이오와</p></li></a>
           			<a href="regMain.do?regname=위스콘신" class="headercontentin"><li><p>위스콘신</p></li></a>
           			<a href="regMain.do?regname=미네소타" class="headercontentin"><li><p>미네소타</p></li></a>
           			<a href="regMain.do?regname=오리건" class="headercontentin"><li><p>오리건</p></li></a>
           			<a href="regMain.do?regname=캔자스" class="headercontentin"><li><p>캔자스</p></li></a>
           			<a href="regMain.do?regname=네바다" class="headercontentin"><li><p>네바다</p></li></a>
           			<a href="regMain.do?regname=네브래스카" class="headercontentin"><li><p>네브래스카</p></li></a>
           			<a href="regMain.do?regname=콜로라도" class="headercontentin"><li><p>콜로라도</p></li></a>
           			<a href="regMain.do?regname=노스다코타" class="headercontentin"><li><p>노스다코타</p></li></a>
           			<a href="regMain.do?regname=사우스다코타" class="headercontentin"><li><p>사우스다코타</p></li></a>
           		</ul>
           		<ul class="dropmenu7">
           			<a href="regMain.do?regname=몬태나" class="headercontentin"><li><p>몬태나</p></li></a>
           			<a href="regMain.do?regname=워싱턴" class="headercontentin"><li><p>워싱턴</p></li></a>
           			<a href="regMain.do?regname=아이다호" class="headercontentin"><li><p>아이다호</p></li></a>
           			<a href="regMain.do?regname=와이오밍" class="headercontentin"><li><p>와이오밍</p></li></a>
           			<a href="regMain.do?regname=유타" class="headercontentin"><li><p>유타</p></li></a>
           			<a href="regMain.do?regname=오클라호마" class="headercontentin"><li><p>오클라호마</p></li></a>
           			<a href="regMain.do?regname=뉴멕시코" class="headercontentin"><li><p>뉴멕시코</p></li></a>
           			<a href="regMain.do?regname=애리조나" class="headercontentin"><li><p>애리조나</p></li></a>
           			<a href="regMain.do?regname=알래스카" class="headercontentin"><li><p>알래스카</p></li></a>
           		</ul>
           </li></a>
         </ul>
      </li>
      <li class="headercenter"><a href="#" class="headercontent">Community</a>
          <ul>
           <a href="QAlist.do" class="headercontentqa"><li><p>Q & A</p></li></a>
           <a href="Tradelist.do" class="headercontenttra"><li><p>거래</p></li></a>
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