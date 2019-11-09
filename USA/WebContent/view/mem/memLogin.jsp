<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="로그인하는 페이지">
<title>로그인</title>

<link rel="stylesheet" href="${contextPath }/css/memLogin.css">

</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<div id="memLogin">

	<form action="${contextPath }/memLoginPro.do">
	<input type="hidden" name="idChk" id="idHidden">
	<div class="inputBack" id="idDiv">
		<!-- 포거스를 벗어날때(onfocusout) 유효성검사 함수(idValidate()) 실행 -->
		<!-- 포거스가 진행중일때(onfocusin) 초기화 함수(basic('#idDiv')) 실행 -->
		<input type="text" name="mem_id" placeholder="아이디" value="${cookie_mem_id }" class="inputReal" id="mem_id" onfocusout="idValidate()" onfocusin="basic('#idDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<br>
	<div class="inputBack" id="pwdDiv">
		<input type="password" name="mem_pwd" placeholder="비밀번호" class="inputReal" id="mem_pwd" onfocusout="pwdValidate()" onfocusin="basic('#pwdDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	
	<!-- DB에 전달받은 아이디와 비밀번호와 일치하는 값이 없으면 받는 outmsg 파라미터 값이 있으면 idPwdMsg의 div생성 -->
	<c:if test="${not empty param.outmsg }">
	<div id="idPwdMsg">
		아이디 또는 비밀번호를 다시 확인하세요.<div id="br"></div>
		사이트에 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
	</div>
	</c:if>
	<div id="menu">
		<!-- cookie_mem_id 변수명을 가진 쿠기값이 존재하면 checked속성을 가진 checkbox생성 -->
		<span id="idChkSpan">
		<c:if test="${empty cookie_mem_id }">
			<input type="checkbox" name="idChk" id="idChk" onclick="isCookie()">		
		</c:if>
		<c:if test="${not empty cookie_mem_id }">
			<input type="checkbox" name="idChk" id="idChk" onclick="isCookie()" checked="checked">		
		</c:if>
		</span>
		<span id="idChkName">아이디 저장</span>
		<a href="${contextPath }/memIdForm.do">아이디 찾기</a> | <a href="${contextPath }/memPwdForm.do">비밀번호 찾기</a> | <a href="${contextPath }/memWriterForm.do">회원가입</a>
	</div>

	<input type="submit" value="로그인"><br>
	
	<!-- 네이버 로그인 연동 URL의 주소를 가진 이미지 a태그를 클릭하면 관리자가 설정한 callback함수 주소로 회원정보 값을 전달받음 -->
	<a href="${apiURL }"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
	</form>
</div>
<jsp:include page="/view/main/footer.html" />
</body>

<script type="text/javascript" src="${contextPath }/view/mem/js/memLogin.js"></script>

</html>