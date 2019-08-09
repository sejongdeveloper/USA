<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<link rel="stylesheet" href="${contextPath }/view/mem/css/memLogin.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	function test() {
		if($("#idChk").prop("checked")){
			$("#idHidden").val(true);
		} else {
			$("#idHidden").val(false);
		}
	}
</script>

</head>
<body>

<div id="memLogin">

	<form action="${pageContext.request.contextPath }/memLoginPro.do">
	<input type="hidden" name="idChk" id="idHidden" value="${false }">
	<div class="inputBack" id="idDiv">
		<input type="text" name="mem_id" placeholder="아이디" value="${cookie_id }" class="inputReal" id="mem_id" onfocusout="idValidate()" onfocusin="basic('#idDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<br>
	<div class="inputBack" id="pwdDiv">
		<input type="password" name="mem_pwd" placeholder="비밀번호" class="inputReal" id="mem_pwd" onfocusout="pwdValidate()" onfocusin="basic('#pwdDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>

	<c:if test="${not empty param.outmsg }">
	<div id="idPwdMsg">
		아이디 또는 비밀번호를 다시 확인하세요.<div id="br"></div>
		사이트에 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
	</div>
	</c:if>
	<div id="menu">
		<span id="idChkSpan"><input type="checkbox" name="idChk" id="idChk" onclick="test()"></span><span id="idChkName">아이디 저장</span>
		<a href="${contextPath }/memIdForm.do">아이디 찾기</a> | <a href="${contextPath }/memPwdForm.do">비밀번호 찾기</a> | <a href="${contextPath }/memWriterForm.do">회원가입</a>
	</div>

	<input type="submit" value="로그인">

	</form>

</div>

</body>
</html>