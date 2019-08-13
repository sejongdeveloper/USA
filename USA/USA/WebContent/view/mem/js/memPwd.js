/**
 * 
 */
function pwdIn() {
	document.getElementsByClassName("data")[0].style.border = "medium solid lightblue";
}

function pwdOut() {
	document.getElementsByClassName("data")[0].style.border = "1px solid #dadada";
}

function next() {
	var mem_id = document.getElementById("mem_id").value;
	if(mem_id == "") {
		alert("아이디를 정확하게 입력해 주세요");
		return false;
	}
	
	var idChk = /^[a-zA-Z0-9-_]{5,20}$/;
	if(!idChk.test(mem_id)){
		alert("아이디를 정확하게 입력해 주세요");
		return false;
	}
	
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
	var url = "/USA/memIdValidate.do?idInfo=" + mem_id;
	
	httpRequest.onreadystatechange = callback;
	httpRequest.open("get", url, true);
	httpRequest.send(null);
	
	function callback() {
		if(httpRequest.readyState == 4 && httpRequest.status == 200) {
			var info = JSON.parse(httpRequest.responseText);
			if(info.result === false) {
				alert("입력하신 아이디를 찾을 수 없습니다.");
				$("#mem_id").val("");
			} else {
				document.sub.submit();
			}
		}
	}
	return false;
}