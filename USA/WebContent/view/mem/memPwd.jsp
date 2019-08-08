<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<link rel="stylesheet" href="${contextPath }/view/mem/css/memPwd.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	function pwdIn() {
		$(".data").attr("style","border: medium solid lightblue;");
	}
	function pwdOut() {
		$(".data").attr("style","border: 1px solid #dadada;");
	}
	function next() {
		var mem_id = $("#mem_id").val();
		if(mem_id == "") {
			alert("아이디를 정확하게 입력해 주세요");
			return false;
		}
		
		var idChk = /^[a-zA-Z0-9-_]{5,20}$/;
		if(!idChk.test(mem_id)){
			alert("아이디를 정확하게 입력해 주세요");
			return false;
		}
		
		var result = true;
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
					alert("입력하신 아이디를 찾을 수 없습니다.");
					$("#mem_id").val("");
					result = false;
				}
			}
		});
		return result;
	}	
	
</script>

</head>
<body>

<div id="memPwd">

<div class="subject">비밀번호 찾기</div>
<span id="id"><hr></span><span><hr></span>
<div id="idDetail">비밀번호를 찾고자 하는 아이디를 입력해 주세요.</div>

<form action="${pageContext.request.contextPath }/memPwdForm2.do" onsubmit="return next()">
<div class="main" style="height: 111px;">
	<div class="data"><input type="text" name="mem_id" id="mem_id" placeholder="아이디 입력" onfocusout="pwdOut()" onfocusin="pwdIn()"></div>
</div>
	<input type="submit" value="다음">
</form>

</div>

</body>
</html>