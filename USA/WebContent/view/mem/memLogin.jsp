<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/memLoginPro.do">
	아이디 <input type="text" name="mem_id"><br>
	비밀번호 <input type="password" name="mem_pwd"><br>
	<input type="submit" value="로그인">
	</form>
</body>
</html>