<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function idValidate() {
		var mem_id = $("#mem_id").val();
		$.ajax({
			type:"post",
			async:false,
			url:"../../memIdValidate.do",
			data:{idInfo:mem_id},
			success:function(data, textStatus){
				var jsonInfo = JSON.parse(data);
				var memberInfo = "회원 정보<br>";
				memberInfo += "======<br>";
				console.log(memberInfo);
				for(var i in jsonInfo.members) {
					memberInfo += "이름: " + jsonInfo.members[i].name + "<br>";
					memberInfo += "나이: " + jsonInfo.members[i].age + "<br>";
				}
				console.log(memberInfo);
				$("#output").html(memberInfo);
				console.log(memberInfo);
				
			}
		});
	}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/memWritePro.do" method="post" enctype="multipart/form-data">
	아이디 : <input type="text" name="mem_id" id="mem_id"> <input type="button" value="아이디확인" onclick="idValidate()"><br>
	<div id="output"></div>
	비밀번호 : <input type="password" name="mem_pwd"><br>
	이름 : <input type="text" name="mem_name"><br>
	전화번호 : <input type="text" name="mem_ph"><br>
	주소 : <input type="text" name="mem_addr"><br>
	파일 : <input type="file" name="mem_filename"><br>
	<input type="submit" value="회원가입">
</form>
</body>
</html>