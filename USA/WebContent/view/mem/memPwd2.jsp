<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="비밀번호 찾기 위해 회원정보를 입력하는 페이지">
<title>비밀번호 찾기</title>

<link rel="stylesheet" href="${contextPath }/css/memPwd2.css">

</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<!-- 전달받은 회원정보와 일치하는 DB가 없을때 받는 err 파라미터 값이 있으면 javascript 실행 -->
<c:if test="${not empty param.err }">
<script type="text/javascript">
	alert("회원정보를 정확하게 입력해 주세요");
</script>
</c:if>

<div id="memPwd2">

<div class="subject">비밀번호 찾기</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">비밀번호를 찾고자 하는 회원정보를 입력해 주세요.</div>

<form action="${contextPath }/memPwdForm3.do">
<!-- 전달할 id 값을 클라이언트한테 숨김 -->
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
<jsp:include page="/view/main/footer.html" />
</body>
</html>
