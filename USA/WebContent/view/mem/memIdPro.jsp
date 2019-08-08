<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>



<link rel="stylesheet" href="${contextPath }/view/mem/css/memIdPro.css">

</head>
<body>
<div class="subject">아이디 찾기</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">고객님의 정보와 일치하는 아이디 목록입니다.</div>

<div class="main">
<c:forEach var="vo" items="${mem_list }">
	<div class="name"><label for="mem_name">${vo.mem_id }</label></div>
	<hr>
	<div class="data">가입 : <fmt:formatDate value="${vo.mem_cdate }" pattern="yyyy.MM.dd"/></div><br>
</c:forEach>
</div>

<input type="button" id="login" value="로그인하기" onclick="window.location.href='${contextPath}/memLoginForm.do'">
<input type="button" value="비밀번호 찾기" onclick="window.location.href='${contextPath}/memPwdForm.do'">

</body>
</html>



