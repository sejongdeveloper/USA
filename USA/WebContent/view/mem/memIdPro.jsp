<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 날짜 형식을 변환하기 위한 fmt태그 라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 컨텍스트 패스(절대위치) 값 변수에 저장 -->
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="아이디  찾은 결과를 보여주는 페이지">
<title>아이디 목록 : 아이디 찾기</title>

<!-- css파일 읽어오기 -->
<link rel="stylesheet" href="${contextPath }/css/memIdPro.css">

</head>
<body>
<!-- jstl include -->
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<jsp:include page="/view/main/money.jsp" />
<div id="memIdPro">

<div class="subject">아이디 찾기</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">고객님의 정보와 일치하는 아이디 목록입니다.</div>

<div class="main">
<c:forEach var="vo" items="${mem_list }">
	<div class="name"><label for="mem_name">${vo.mem_id }</label></div>
	<hr>
	<!-- fmt태그를 이용하여 pattern에 지정된 형식으로 날짜형식 변환 -->
	<div class="data">가입 : <fmt:formatDate value="${vo.mem_cdate }" pattern="yyyy.MM.dd"/></div><br>
</c:forEach>
</div>

<!-- 클릭하면 window.location.href를 이용하여 이동 -->
<input type="button" id="login" value="로그인하기" onclick="window.location.href='${contextPath}/memLoginForm.do'">
<input type="button" value="비밀번호 찾기" onclick="window.location.href='${contextPath}/memPwdForm.do'">

</div>

<!-- jstl include -->
<jsp:include page="/view/main/footer.html" />

</body>
</html>



