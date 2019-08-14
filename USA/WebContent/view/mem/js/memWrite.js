/* 아이디 유효성 검사 */
function idValidate() {
	var mem_id = document.getElementById("mem_id").value;
	var outIdMsg = document.getElementById("outIdMsg");
	var idDiv = document.getElementById("idDiv");
	
	/* 빈값 체크 */
	if(mem_id == "") {
		outIdMsg.innerHTML = "필수 정보입니다.";
		outIdMsg.style.color = "red";
		idDiv.style.border = "1px solid red";
		return;
	}
	
	/* 유효성 검사 */
	var idChk = /^[a-zA-Z0-9-_]{5,20}$/;
	if(!idChk.test(mem_id)){
		outIdMsg.innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
		outIdMsg.style.color = "red";
		idDiv.style.border = "1px solid red";
		return;
	}
	
	/* ajax를 통하여 아이디가 존재하는지 판단 */
	function getXMLHttpRequest() {
		if(window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if(window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	var httpRequest = getXMLHttpRequest();
	var url = "/USA/memIdValidate.do";
	var args = "idInfo=" + mem_id;
	
	httpRequest.onreadystatechange = callback;
	httpRequest.open("post", url, true);
	
	/* post방식으로 데이터 전달하기 */
	httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequest.send(args);
	
	function callback() {
		if(httpRequest.readyState == 4 && httpRequest.status == 200) {
			/* json 방식 */
			var data = httpRequest.responseText;
			var json = JSON.parse(data);
			
			if(json.result === false){
				outIdMsg.innerHTML = "멋진 아이디네요!";
				outIdMsg.style.color = "green";
				idDiv.style.border = "1px solid #dadada";
				
			} else {
				outIdMsg.innerHTML = "이미 사용중이거나 탈퇴한 아이디입니다.";
				outIdMsg.style.color = "red";
				idDiv.style.border = "1px solid red";
					
			}
		}
	}
}

/* 비밀번호 유효성 검사 */
function pwdValidate() {
	var pwdChk = /^[a-zA-Z0-9!@#$%^&*()-_=+]{8,16}$/;
	var pwd = document.getElementById("mem_pwd").value;
	var outPwdMsg = document.getElementById("outPwdMsg");
	var pwdDiv = document.getElementById("pwdDiv");
		
	/* 빈값 체크 */
	if(pwd == "") {
		outPwdMsg.innerHTML = "필수 정보입니다.";
		outPwdMsg.style.color = "red";
		pwdDiv.style.border = "1px solid red";
		return;
	}
	
	/* 유효성 검사 */
	if(pwdChk.test(pwd)){
		outPwdMsg.innerHTML = "";
		pwdDiv.style.border = "1px solid #dadada";
	
	} else {
		outPwdMsg.innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		outPwdMsg.style.color = "red";
		pwdDiv.style.border = "1px solid red";
		return;
	}
	pwdChkValidate();
}

/* 비밀번호 재확인 */
function pwdChkValidate(){
	var pwdChk = document.getElementById("mem_pwdChk").value;
	var outPwdChkMsg = document.getElementById("outPwdChkMsg");
	var pwdChkDiv = document.getElementById("pwdChkDiv");

	/* 빈값 체크 */
	if(pwdChk == "") {
		outPwdChkMsg.innerHTML = "필수 정보입니다.";
		outPwdChkMsg.style.color = "red";
		pwdChkDiv.style.border = "1px solid red";
		return;
	}
	
	/* 비밀번호화 비밀번호 재확인 값이 일치하는지 판단 */
	var pwd = document.getElementById("mem_pwd").value;
	if(pwd == pwdChk) {
		outPwdChkMsg.innerHTML = "";
		pwdChkDiv.style.border = "1px solid #dadada";
	}else{
		outPwdChkMsg.innerHTML = "비밀번호가 일치하지 않습니다.";
		outPwdChkMsg.style.color = "red";
		pwdChkDiv.style.border = "1px solid red";
	}
}

/* 디자인 변경 */
function basic(id) {
  document.getElementById(id).style.border = "1px solid green";
}

/* 빈값인지 판단 */
function basicValidate(input, div, msg) {
	var input = document.getElementById(input).value;
	var div = document.getElementById(div);
	var msg = document.getElementById(msg);
	
	if(input == "") {
		msg.innerHTML = "필수 정보입니다.";
		msg.style.color = "red";
		div.style.border = "1px solid red";
	} else {
		msg.innerHTML = "";
		div.style.border = "1px solid #dadada";
	}
}

/* 모든 유효성 검사를 통과했는지 판단 */
function check() {
	var id = document.getElementById("outIdMsg").innerHTML;
	var pwd = document.getElementById("outPwdMsg").innerHTML;
	var pwdChk = document.getElementById("outPwdChkMsg").innerHTML;
	var name = document.getElementById("outNameMsg").innerHTML;
	var ph = document.getElementById("outPhMsg").innerHTML;
	var addr = document.getElementById("outAddrMsg").innerHTML;

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
/*
길이 조절 함수 (현재 사용x)
function maxLength(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }    
}
*/