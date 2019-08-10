<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="   UTF-8">
<title>Header</title>
<link rel="stylesheet" href="/USA/view/main/style.css">
</head>

   <div class="header">
    <ul class="dropmenu">
      <li class="headercenter"><a href="#" class="headertitle">sitetitle</a></li>
      <li class="headercenter"><a href="#" class="headercontent">Information</a>
         <ul>
           <li><a href="/USA/view/reg/regMap.do" class="headercontentmap">지도</a></li>
           <li><a href="#" class="headercontentreg">지역</a>
                 <ul class="dropmenu3">
                    <li><a href="#" class="headercontentin">뉴욕</a>
                    <li><a href="#" class="headercontentin">하와이</a>
                    <li><a href="#" class="headercontentin">캘리포니아</a>
                 </ul>
           </li>
           <li><a href="#" class="headercontentloc">관광지</a>
              <ul class="dropmenu3">
                  <li><a href="#" class="headercontentin">자유의 여신상</a>
                  <li><a href="#" class="headercontentin">타임스퀘어</a>
                  <li><a href="#" class="headercontentin">브로드웨이</a>
                  <li><a href="#" class="headercontentin">뉴욕 증권거래소</a>
              </ul>
           </li>
         </ul>
      </li>
      <li class="headercenter"><a href="#" class="headercontent">Community</a>
          <ul>
           <li><a href="#" class="headercontentqa">Q & A</a></li>
           <li><a href="#" class="headercontenttra">거래</a></li>
         </ul>
      </li>
      <li class="headercenter"><a href="#" class="headercontent">Event</a></li>
    <c:if test="${empty member }">
	   <div class="headerSignin"><a href="/USA/memLoginForm.do" class="signin">Sign In</a></div>
    </c:if>
    <c:if test="${not empty member }">
	   <div class="headerSignin"><span class="signin"><span class="member">${member }님 환영합니다^^ <a id="me" href="${contextPath }/memUpdateForm.do">내정보</a></span><a href="/USA/memLogout.do" class="signin">Sign Out</a></div>
    </c:if>
    </ul>
   </div>
	
</html>