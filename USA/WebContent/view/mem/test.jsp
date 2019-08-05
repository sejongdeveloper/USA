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
테스트1 : ${test }
테스트2 : ${test2 }
<form action="${pageContext.request.contextPath }/test.do">
	<input type="submit">
</form>
</body>
</html>