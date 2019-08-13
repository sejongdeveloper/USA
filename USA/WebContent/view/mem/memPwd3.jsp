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
<meta name="description" content="회원의 비밀번호를 재설정하는 페이지">
<title>비밀번호 재설정</title>

<!-- css파일 읽어오기 -->
<link rel="stylesheet" href="${contextPath }/css/memPwd3.css">

</head>
<body>
<!-- jstl include -->
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<jsp:include page="/view/main/money.jsp" />
<div id="memPwd3">

<div class="subject">비밀번호 재설정</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">
비밀번호를 변경해 주세요.<br>
다른 아이디나 사이트에서 사용한 적 없는 안전한 비밀번호로 변경해 주세요.
</div>

<!-- form 태그 onsubmit: submit 버튼을 클릭하여 return true일 경우에 실행 -->
<form action="${contextPath }/memPwdPro.do" onsubmit="return chk()">
<!-- 사용자한테는 않보이는 hidden으로 파라미터 값(mem_id) 저장 -->
<input type="hidden" name="mem_id" value="${param.mem_id }">
<div class="dataLine">
	<div id="top"></div>
	<h4>네이버 아이디&nbsp; &nbsp;:&nbsp; &nbsp;</h4><span id="mem_id">${param.mem_id }</span>
	<!-- placeholder: 텍스트 안에 설명글 넣기 / onfocusout: 커서 밖에 있을때 실행 / onfocusin: 커서 안에 있을때 실행 -->
	<div class="data" id="pwdDiv"><input type="password" name="mem_pwd" id="mem_pwd" placeholder="새 비밀번호" onfocusout="pwdValidate()" onfocusin="pwdIn('pwdDiv')"></div>
	<div class="idPwdMsg" id="pwdMsg"></div>
	<div class="data" id="pwdChkDiv"><input type="password" name="mem_pwdChk" id="mem_pwdChk" placeholder="새 비밀번호 확인" onfocusout="pwdChkValidate()" onfocusin="pwdIn('pwdChkDiv')"></div>
	<div class="idPwdMsg" id="pwdChkMsg"></div>
	<div id="data_bottom">
	<hr>
	<div class="idDetail">
	<ul>
		<li>영문, 숫자, 특수문자를 함께 사용하면(8자 이상 16자 이하)보다 안전합니다.</li>
		<li>다른 사이트와 다른 <h4>네이버 아이디만의 비밀번호</h4>를 만들어 주세요.</li>
	</ul>
	</div>
	</div>
</div>
	<input type="submit" value="확인">
</form>

</div>

<!-- jstl include -->
<jsp:include page="/view/main/footer.html" />

</body>

<!-- javascript 실행 -->
<script type="text/javascript" src="${contextPath }/view/mem/js/memPwd3.js"></script>

</html>
