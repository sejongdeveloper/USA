<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="아이디  찾은 결과를 보여주는 페이지">
<title>아이디 목록 : 아이디 찾기</title>

<link rel="stylesheet" href="${contextPath }/css/memIdPro.css">

</head>
<body>
<div id="memIdPro">

<div class="subject">아이디 찾기</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">고객님의 정보와 일치하는 아이디 목록입니다.</div>

<div class="main">
<!-- 전달받은 회원정보와 일치하는 아이디 목록을 확장for문을 이용하여 출력 -->
<c:forEach var="vo" items="${mem_list }">
	<div class="name"><label for="mem_name">${vo.mem_id }</label></div>
	<hr>
	<!-- fmt 디렉티브 태그를 이용하여 날짜형식을 pattern에 지정한 형식으로 변환 -->
	<div class="data">가입 : <fmt:formatDate value="${vo.mem_cdate }" pattern="yyyy.MM.dd"/></div><br>
</c:forEach>
</div>

<!-- 버튼을 클릭하면(onclick) 해당주소(window.location.href)로 이동 -->
<input type="button" id="login" value="로그인하기" onclick="window.location.href='${contextPath}/memLoginForm.do'">
<input type="button" value="비밀번호 찾기" onclick="window.location.href='${contextPath}/memPwdForm.do'">

</div>
<jsp:include page="/view/main/footer.html" />
</body>
</html>



