<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>

<link rel="stylesheet" href="${contextPath }/view/mem/css/memId.css">

</head>
<body>

<div id="memId">

<div class="subject">아이디 찾기</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">회원 정보를 입력해 주세요.</div>

<form action="${pageContext.request.contextPath }/memIdPro.do">
<div class="main">
	<h4>본인 확인 후 아이디를 찾아드립니다.</h4>
	<div class="name"><label for="mem_name">이름</label></div>
	<div class="data"><input type="text" name="mem_name" id="mem_name"></div><br>

	<div class="name"><label for="mem_addr">주소</label></div>
	<div class="data"><input type="text" name="mem_addr" id="mem_addr"></div><br>
</div>
	<input type="submit" value="다음">
</form>

</div>

</body>
</html>
