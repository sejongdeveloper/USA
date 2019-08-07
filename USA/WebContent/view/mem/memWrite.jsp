<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<link rel="stylesheet" href="css/memWrite.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	var chk = false;
	function idValidate() {
		var mem_id = $("#mem_id").val();
		
		if(mem_id == "") {
			$("#outIdMsg").html("필수 정보입니다.");
			$("#outIdMsg").attr("style","color: red;");
			$("#idDiv").attr("style","border: 1px solid red;");
			return;
		}
		
		var idChk = /^[a-zA-Z0-9-_]{5,20}$/;
		if(!idChk.test(mem_id)){
			$("#outIdMsg").html("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
			$("#outIdMsg").attr("style","color: red;");
			$("#idDiv").attr("style","border: 1px solid red;");
			return;
		}
		
		$.ajax({
			type : "post",
			async : false,
			datatype : "json",
			url : "../../memIdValidate.do",
			data : {
				idInfo : mem_id
			},
			success : function(data) {
				var info = JSON.parse(data);
				if(info.result === false){
					$("#idBtn").prop("disabled",true);
					$("#outIdMsg").html("멋진 아이디네요!");
					$("#outIdMsg").attr("style","color: green;");
					$("#idDiv").attr("style","border: 1px solid #dadada;");
				} else {
					$("#outIdMsg").html("이미 사용중이거나 탈퇴한 아이디입니다.");
					$("#outIdMsg").attr("style","color: red;");
					$("#idDiv").attr("style","border: 1px solid red;");
				}
			}
		});
		
		
	}
	
	
	function pwdValidate() {
		var pwdChk = /^[a-zA-Z0-9!@#$%^&*()-_=+]{8,16}$/;
		var pwd = $("#mem_pwd").val();
		
		if(pwd == "") {
			$("#outPwdMsg").html("필수 정보입니다.");
			$("#outPwdMsg").attr("style","color: red;");
			$("#pwdDiv").attr("style","border: 1px solid red;");
			return;
		}
		
		if(pwdChk.test(pwd)){
			$("#outPwdMsg").html("");
			$("#pwdDiv").attr("style","border: 1px solid #dadada;");
		} else {
			$("#outPwdMsg").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
			$("#outPwdMsg").attr("style","color: red;");
			$("#pwdDiv").attr("style","border: 1px solid red;");
		}
	}
	
	
	
	
	function pwdChkValidate(){
		var pwdChk = $("#mem_pwdChk").val();	
		if(pwdChk == "") {
			$("#outPwdChkMsg").html("필수 정보입니다.");
			$("#outPwdChkMsg").attr("style","color: red;");
			$("#pwdChkDiv").attr("style","border: 1px solid red;");
			return;
		}
		
		var pwd = $("#mem_pwd").val();
		if(pwd == pwdChk) {
			$("#outPwdChkMsg").html("");
			$("#pwdChkDiv").attr("style","border: 1px solid #dadada;");
		}else{
			$("#outPwdChkMsg").html("비밀번호가 일치하지 않습니다.");
			$("#outPwdChkMsg").attr("style","color: red;");
			$("#pwdChkDiv").attr("style","border: 1px solid red;");
		}
	}
	
	
	function basic(id) {
	  $(id).attr("style","border: 1px solid green;");
	}
	
	function basicValidate(input, div, msg) {
		var input = $("#"+input).val();
		if(input == "") {
			$("#"+msg).html("필수 정보입니다.");
			$("#"+msg).attr("style","color: red;");
			$("#"+div).attr("style","border: 1px solid red;");
		} else {
			$("#"+msg).html("");
			$("#"+div).attr("style","border: 1px solid #dadada;");
		}
	}
	
	function check() {
		var id = $("#outIdMsg").text();
		var pwd = $("#outPwdMsg").text();
		var pwdChk = $("#outPwdChkMsg").text();
		var name = $("#outNameMsg").text();
		var ph = $("#outPhMsg").text();
		var addr = $("#outAddrMsg").text();
		if(id != "멋진 아이디네요!") {
			alert("아이디를 확인하세요.");
			return false;
		} else if(pwd != "") {
			alert("비밀번호를 확인하세요.");
			return false;
		} else if(pwdChk != "") {
			alert("비밀번호 확인을 확인하세요.");
			return false;
		} else if(name != "") {
			alert("이름을 확인하세요.");
			return false;
		} else if(ph != "") {
			alert("전화번호를 확인하세요.");
			return false;
		} else if(addr != "") {
			alert("주소를 확인하세요.");
			return false;
		}
		return true;
	}
</script>

</head>
<body>
<div id="main">
<form action="${pageContext.request.contextPath }/memWritePro.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<div class="subject"><label for="mem_id">아이디</label></div>
	<div class="inputBack" id="idDiv">
		<input type="text" name="mem_id" class="inputReal" id="mem_id" onfocusout="idValidate()" onfocusin="basic('#idDiv')">
		<div class="imgDiv"><img src="upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outIdMsg"></span>

	<div class="subject"><label for="mem_pwd">비밀번호</label></div>
	<div class="inputBack" id="pwdDiv">
		<input type="password" name="mem_pwd" class="inputReal" id="mem_pwd" onfocusout="pwdValidate()" onfocusin="basic('#pwdDiv')">
		<div class="imgDiv"><img src="upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outPwdMsg"></span>
	
	<div class="subject"><label for="mem_pwdChk">비밀번호 재확인</label></div>
	<div class="inputBack" id="pwdChkDiv" >
		<input type="password" name="mem_pwd" class="inputReal" id="mem_pwdChk" onfocusout="pwdChkValidate()" onfocusin="basic('#pwdChkDiv')">
		<div class="imgDiv"><img src="upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outPwdChkMsg"></span>

	<div class="subject"><label for="mem_name">이름</label></div>
	<div class="inputBack" id="nameDiv">
		<input type="text" name="mem_name" class="inputReal" id="mem_name" onfocusout="basicValidate(this.id, 'nameDiv', 'outNameMsg')" onfocusin="basic('#nameDiv')">
		<div class="imgDiv"><img src="upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outNameMsg"></span>

	<div class="subject"><label for="mem_ph">전화번호</label></div>
	<div class="inputBack" id="phDiv">
		<input type="text" name="mem_ph" class="inputReal" id="mem_ph" onfocusout="basicValidate(this.id,'phDiv', 'outPhMsg')" onfocusin="basic('#phDiv')">
		<div class="imgDiv"><img src="upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outPhMsg"></span>

	<div class="subject"><label for="mem_addr">주소</label></div>
	<div class="inputBack" id="addrDiv">
		<input type="text" name="mem_addr" class="inputReal" id="mem_addr" onfocusout="basicValidate(this.id,'addrDiv', 'outAddrMsg')" onfocusin="basic('#addrDiv')">
		<div class="imgDiv"><img src="upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outAddrMsg"></span>

	<div class="subject"><label for="mem_id">파일</label></div>
	<input type="file" name="mem_filename" id="mem_filename"><br>
	
	<input type="submit" value="회원가입" >
</form>
</div>
</body>
</html>