/* 테두리 디자인 변경 */
function pwdIn() {
	document.getElementsByClassName("data")[0].style.border = "medium solid lightblue";
}

/* 테두리 디자인 변경 */
function pwdOut() {
	document.getElementsByClassName("data")[0].style.border = "1px solid #dadada";
}

function next() {
	/* 빈값 체크 */
	var mem_id = document.getElementById("mem_id").value;
	if(mem_id == "") {
		alert("아이디를 정확하게 입력해 주세요");
		return false;
	}
	
	/* 유효성 검사 */
	var idChk = /^[a-zA-Z0-9-_]{5,20}$/;
	if(!idChk.test(mem_id)){
		alert("아이디를 정확하게 입력해 주세요");
		return false;
	}
	
	/* ajax를 이용하여 아이디 유무판단 */
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
				/* sub이라는 name값을 가진 DOM의 submit실행 */
				document.sub.submit();
			}
		}
	}
	return false;
}