/**
 * 
 */
function isCookie() {
	var idChk = document.getElementById("idChk").checked;
	var idHidden = document.getElementById("idHidden");
	
	if(idChk){
		idHidden.value = true;
	} else {
		idHidden.value = false;
	}
}