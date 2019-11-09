/* 디자인 변경 */
function pwdIn(data) {
	document.getElementById(data).style.border = "2px solid lightblue";
}

function pwdValidate() {
	var pwdChk = /^[a-zA-Z0-9!@#$%^&*()-_=+]{8,16}$/;
	var pwd = document.getElementById("mem_pwd").value;
	var pwdDiv = document.getElementById("pwdDiv");
	var pwdMsg = document.getElementById("pwdMsg"); 
	
	/* 빈값 체크 */
	if(pwd == "") {
		pwdDiv.style.border = "1px solid red";
		pwdMsg.innerHTML = "필수 정보입니다.";
	
	/* 유효성 검사 */
	} else if(pwdChk.test(pwd)){
		pwdMsg.innerHTML = "";
		pwdDiv.style.border = "1px solid #dadada";
		pwdChkValidate("pwdChkDiv");
		
	} else {
		pwdMsg.innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		pwdDiv.style.border = "1px solid red";
		
	}
	
}

function pwdChkValidate() {
	var pwd = document.getElementById("mem_pwd").value;	
	var pwdChk = document.getElementById("mem_pwdChk").value;	
	var pwdChkMsg = document.getElementById("pwdChkMsg");	
	var pwdChkDiv = document.getElementById("pwdChkDiv");
	
	/* 빈값 체크 */
	if(pwdChk == "") {
		pwdChkMsg.innerHTML = "필수 정보입니다.";
		pwdChkDiv.style.border = "border: 1px solid red";
		
	/* 유효성 검사 */
	} else if(pwd == pwdChk) {
		pwdChkMsg.innerHTML = "";
		pwdChkDiv.style.border = "border: 1px solid #dadada";
		
	} else {
		pwdChkMsg.innerHTML = "비밀번호가 일치하지 않습니다.";
		pwdChkDiv.style.border = "border: 1px solid red";
	}
}

/* 유효성 검사를 모두 만족했는지 여부판단 */
function chk() {
	var isPwd = document.getElementById("pwdMsg").innerHTML;
	var isPwdChk = document.getElementById("pwdChkMsg").innerHTML;
	if(isPwd != "" || isPwdChk != "") {
		alert("비밀번호를 조건에 맞게 입력해주세요");
		return false;
	}		
}