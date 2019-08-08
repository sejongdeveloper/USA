<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>

<link rel="stylesheet" href="${contextPath }/view/mem/css/memPwd2.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>

<input type="hidden" id="err" value="${param.err }">
<c:if test="${not empty param.err }">
<script type="text/javascript">
	alert($("#err").val());
</script>
</c:if>

<div id="memPwd2">

<div class="subject">비밀번호 찾기</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">비밀번호를 찾고자 하는 회원정보를 입력해 주세요.</div>

<form action="${pageContext.request.contextPath }/memPwdForm3.do">
<input type="hidden" name="mem_id" value="${param.mem_id }">
<div class="main">
	<h4>본인 확인 후 비밀번호를 변경해드립니다.</h4>
	<div class="name"><label for="mem_name">이름</label></div>
	<div class="data"><input type="text" name="mem_name" id="mem_name"></div><br>

	<div class="name"><label for="mem_ph">전화번호</label></div>
	<div class="data"><input type="text" name="mem_ph" id="mem_ph"></div><br>
</div>
	<input type="submit" value="다음">
</form>

</div>

</body>
</html>
