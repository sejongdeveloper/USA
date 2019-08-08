<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>

<link rel="stylesheet" href="${contextPath }/view/mem/css/memPwd3.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	var isPwd = false;
	var isPwdChk = false;
	
	function pwdIn(data) {
		$("#" + data).attr("style","border: 2px solid lightblue;");
	}
	function pwdValidate(data,inputData) {
		var pwdChk = /^[a-zA-Z0-9!@#$%^&*()-_=+]{8,16}$/;
		var pwd = $("#mem_pwd").val();
		
		if(pwd == "") {
			$("#" + data).attr("style","border: 1px solid red;");
			isPwd = false;	
			$("#pwdMsg").html("필수 정보입니다.");
		
		} else if(pwdChk.test(pwd)){
			$("#pwdMsg").html("");
			$("#" + data).attr("style","border: 1px solid #dadada;");
			isPwd = true;
			pwdChkValidate("pwdChkDiv","mem_pwdChk");
			
		} else {
			$("#pwdMsg").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
			$("#" + data).attr("style","border: 1px solid red;");
			isPwd = false;
			
		}

		
	}
	
	function pwdChkValidate(data,inputData) {
		var pwdChk = $("#mem_pwdChk").val();	
		var pwd = $("#mem_pwd").val();
		
		if(pwdChk == "") {
			$("#pwdChkMsg").html("필수 정보입니다.");
			$("#" + data).attr("style","border: 1px solid red;");
			isPwdChk = false;
			
		} else if(pwd == pwdChk) {
			$("#pwdChkMsg").html("");
			$("#" + data).attr("style","border: 1px solid #dadada;");
			isPwdChk = true;
			
		} else {
			$("#pwdChkMsg").html("비밀번호가 일치하지 않습니다.");
			$("#" + data).attr("style","border: 1px solid red;");
			isPwdChk = false;
		}
	}
	
	function chk() {
		if(isPwd && isPwdChk) {
			
		} else {
			alert("비밀번호를 조건에 맞게 입력해주세요");
			return false;
		}
		
	}
	
</script>

</head>
<body>

<div id="memPwd3">

<div class="subject">비밀번호 재설정</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">
비밀번호를 변경해 주세요.<br>
다른 아이디나 사이트에서 사용한 적 없는 안전한 비밀번호로 변경해 주세요.
</div>

<form action="${pageContext.request.contextPath }/memPwdPro.do" onsubmit="return chk()">
<input type="hidden" name="mem_id" value="${param.mem_id }">
<div class="main">
	<div id="top"></div>
	<h4>네이버 아이디&nbsp; &nbsp;:&nbsp; &nbsp;</h4><span id="mem_id">${param.mem_id }</span>
	<div class="data" id="pwdDiv"><input type="text" name="mem_pwd" id="mem_pwd" placeholder="새 비밀번호" onfocusout="pwdValidate('pwdDiv','mem_pwd')" onfocusin="pwdIn('pwdDiv')"></div>
	<div class="idPwdMsg" id="pwdMsg"></div>
	<div class="data" id="pwdChkDiv"><input type="text" name="mem_pwdChk" id="mem_pwdChk" placeholder="새 비밀번호 확인" onfocusout="pwdChkValidate('pwdChkDiv','mem_pwdChk')" onfocusin="pwdIn('pwdChkDiv')"></div>
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

</body>
</html>
