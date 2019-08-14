function isCookie() {
	/* idChk = 체크가 되어 있으면 true 아니면 false */
	var idChk = document.getElementById("idChk").checked;
	var idHidden = document.getElementById("idHidden");
	
	/* 체크여부에 따라 idHidden의 값을 변경 */
	if(idChk){
		idHidden.value = true;
	} else {
		idHidden.value = false;
	}
}