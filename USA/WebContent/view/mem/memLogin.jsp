<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- 컨텍스트 패스(절대위치) 값 변수에 저장 -->
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="로그인하는 페이지">
<title>로그인</title>

<!-- css파일 읽어오기 -->
<link rel="stylesheet" href="${contextPath }/css/memLogin.css">

</head>
<body>
<!-- jstl include -->
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<jsp:include page="/view/main/money.jsp" />
<div id="memLogin">

	<!-- form 태그 -->
	<form action="${contextPath }/memLoginPro.do">
	<input type="hidden" name="idChk" id="idHidden">
	<div class="inputBack" id="idDiv">
		<!-- onfocusout: 커서밖에 있을때 실행 / onfocusin: 커서안에 있을때 실행 -->
		<input type="text" name="mem_id" placeholder="아이디" value="${cookie_mem_id }" class="inputReal" id="mem_id" onfocusout="idValidate()" onfocusin="basic('#idDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<br>
	<div class="inputBack" id="pwdDiv">
		<input type="password" name="mem_pwd" placeholder="비밀번호" class="inputReal" id="mem_pwd" onfocusout="pwdValidate()" onfocusin="basic('#pwdDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<!-- 파라미터 값(outmsg)가 비어있지 않다면 if문 실행 -->
	<c:if test="${not empty param.outmsg }">
	<div id="idPwdMsg">
		아이디 또는 비밀번호를 다시 확인하세요.<div id="br"></div>
		사이트에 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
	</div>
	</c:if>
	<div id="menu">
		<span id="idChkSpan">
		<!-- 파라미터 값(cookie_mem_id)가 비어있다면 if문 실행 -->
		<c:if test="${empty cookie_mem_id }">
			<input type="checkbox" name="idChk" id="idChk" onclick="isCookie()">		
		</c:if>
		<!-- 파라미터 값(cookie_mem_id)가 비어있지 않다면 if문 실행 -->
		<c:if test="${not empty cookie_mem_id }">
			<input type="checkbox" name="idChk" id="idChk" onclick="isCookie()" checked="checked">		
		</c:if>
		</span><span id="idChkName">아이디 저장</span>
		<a href="${contextPath }/memIdForm.do">아이디 찾기</a> | <a href="${contextPath }/memPwdForm.do">비밀번호 찾기</a> | <a href="${contextPath }/memWriterForm.do">회원가입</a>
	</div>

	<input type="submit" value="로그인"><br>
	<!-- 네이버 로그인 연동 URL을 가진 a태그 -->
	<a href="${apiURL }"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
	
	</form>
</div>

<!-- jstl include -->
<jsp:include page="/view/main/footer.html" />

</body>

<!-- javascript 실행 -->
<script type="text/javascript" src="${contextPath }/view/mem/js/memLogin.js"></script>

</html>