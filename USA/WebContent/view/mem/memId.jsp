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
아이디 : ${mem_id }
<form action="${pageContext.request.contextPath }/memIdPro.do">
	이름 <input type="text" name="mem_name"><br>
	주소 <input type="text" name="mem_addr"><br>
	<input type="submit" value="찾기">
</form>
</body>
</html>