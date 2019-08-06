<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	function idValidate() {
		var mem_id = $("#mem_id").val();
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
					$("#outmsg").html("사용 가능한 아이디입니다.");
				} else {
					$("#outmsg").html("사용 불가능한 아이디입니다.");
				}
			}
		});
	}
	
	function check(){
		if(!$("#idBtn").prop("disabled")){
			return false;
		}
	}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/memWritePro.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
	아이디 : <input type="text" name="mem_id" id="mem_id"> <input type="button" id="idBtn" value="아이디확인" onclick="idValidate()">
	<span id="outmsg"></span><br>
	비밀번호 : <input type="password" name="mem_pwd"><br>
	이름 : <input type="text" name="mem_name"><br>
	전화번호 : <input type="text" name="mem_ph"><br>
	주소 : <input type="text" name="mem_addr"><br>
	파일 : <input type="file" name="mem_filename"><br>
	<input type="submit" value="회원가입" >
</form>
</body>
</html>