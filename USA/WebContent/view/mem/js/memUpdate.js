/**
 * 
 */
function updateForm(me, id) {
	document.getElementById(me.id).style.display = "none";
	document.getElementById(id).style.display = "block";
}

function updateFormCancel(me, id) {
	document.getElementById(me.parentNode.id).style.display = "none";
	document.getElementById(id).style.display = "block";
}

function fileUpdateFormCancel() {
	document.getElementById("filenameInput").style.display = "none";
	document.getElementById("filenameBtn").style.display = "block";
}

function update(me, id, btn) {
	var mem_id = document.getElementById("mem_id").value;
	var mem_value = document.getElementById(id).value;
	
	
	function getXMLHttpRequest() {
		if(window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if(window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			return null;
		}
	}
	
	var httpRequest = getXMLHttpRequest();
	var url = "/USA/memUpdatePro.do?mem_id=" + mem_id + "&colc=" + id + "&mem_value=" + encodeURI(mem_value, "utf-8");
	
	httpRequest.onreadystatechange = callback;
	httpRequest.open("get", url, true);
	httpRequest.send(null);
	
	function callback() {
		if(httpRequest.readyState == 4 && httpRequest.status == 200) {
			var xml = httpRequest.responseXML;
			var result = xml.getElementsByTagName("result")[0];
			if(result.innerHTML > 0) {
				document.getElementById(me.parentNode.id).style.display = "none";
				document.getElementById(btn).style.display = "block";
				document.getElementById("calc" + id).innerHTML = mem_value;
				
			} else {
				alert("회원수정 중에 오류가 발생했습니다");
			}
		}
	}	
}

function del() {
	var mem_id = document.getElementById("mem_id").value;
	var chk = confirm("회원탈퇴 변경 사항을 적용하시겠습니까?");
	if(chk === true) {
		window.location.href="/USA/memDelPro.do?mem_id=" + mem_id;
	}
}

function pwdUpdate() {
	function getXMLHttpRequest() {
		if(window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if(window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			return null;
		}
	}
	
	var httpRequest = getXMLHttpRequest();
	var url = "/USA/memIdPwdForm.do";
	var mem_id = document.getElementById("mem_id").value;
	var mem_pwd = document.getElementById("mem_pwd").value;
	
	httpRequest.onreadystatechange = function callback() {
		if(httpRequest.readyState == 4 && httpRequest.status == 200) {
			var json = JSON.parse(httpRequest.responseText);
			if(json.result) {
				window.location.href = "/USA/memPwd3Form.do?mem_id=" + mem_id;
			} else {
				alert("비밀번호를 정확하게 입력해 주세요.");
			}
		}
	};
	
	httpRequest.open("post", url, true);
	httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequest.send("mem_pwd=" + mem_pwd);
	
}