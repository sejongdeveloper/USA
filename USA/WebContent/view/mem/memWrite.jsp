<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<title>JSP</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/memWritePro.do" method="post" enctype="multipart/form-data">
	아이디 : <input type="text" name="mem_id"><br>
	비밀번호 : <input type="password" name="mem_pwd"><br>
	전화번호 : <input type="text" name="mem_ph"><br>
	이름 : <input type="text" name="mem_name"><br>
	주소 : <input type="text" name="mem_addr"><br>
	파일 : <input type="file" name="mem_filename"><br>
	<input type="submit" value="회원가입">
</form>
</body>
</html>