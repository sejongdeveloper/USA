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
비밀번호 : ${mem_pwd }
<form action="${pageContext.request.contextPath }/memPwdPro.do">
아이디 <input type="text" name="mem_id"><br>
이름 <input type="text" name="mem_name"><br>
연락처 <input type="text" name="mem_ph"><br>
<input type="submit" value="찾기">
</form>
</body>
</html>