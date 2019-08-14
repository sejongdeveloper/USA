<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="비밀번호를 찾기 위해 아이디를 입력받는 페이지">
<title>비밀번호 찾기</title>

<link rel="stylesheet" href="${contextPath }/css/memPwd.css">

</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<div id="memPwd">

<div class="subject">비밀번호 찾기</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">비밀번호를 찾고자 하는 아이디를 입력해 주세요.</div>

<!-- submit 버튼을 클릭하면 action 주소로 이동하기전에 onsubmit이 지정한 javascript함수로 이동(return false를 받으면 action 주소로 이동 못함) -->
<form action="${contextPath }/memPwdForm2.do" name="sub" onsubmit="return next()">
<div class="main" style="height: 111px;">
	<!-- input 설명글(placeholder) -->
	<!-- 포커스 벗어날때(onfocusout) 디자인 함수(pwdOut()) 실행 -->
	<!-- 포커스 중일때(onfocusin) 디자인 함수(pwdIn()) 실행 -->
	<div class="data"><input type="text" name="mem_id" id="mem_id" placeholder="아이디 입력" onfocusout="pwdOut()" onfocusin="pwdIn()"></div>
</div>
	<input type="submit" value="다음">
</form>

</div>
<jsp:include page="/view/main/footer.html" />
</body>

<script type="text/javascript" src="${contextPath }/view/mem/js/memPwd.js"></script>

</html>