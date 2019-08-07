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
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<form action="${contextPath }/memUpdatePro.do" method="post" enctype="multipart/form-data">
	아이디 <input type="text" name="mem_id" value="${vo.mem_id }"><br>
	비밀번호 <input type="password" name="mem_pwd" required="required"><br>
	이름 <input type="text" name="mem_name" value="${vo.mem_name }"><br>
	전화번호 <input type="text" name="mem_ph" value="${vo.mem_ph }"><br>
	주소 <input type="text" name="mem_addr" value="${vo.mem_addr }"><br>
	포인트 <input type="text" name="mem_point" value="${vo.mem_point }" readonly="readonly"><br>
	파일명 <input type="file" name="mem_filename" value="${vo.mem_filename }"><br>
	<input type="submit" value="수정">
	<input type="button" value="회원탈퇴" onclick="window.location.href='${contextPath}/memDelPro.do'">
</form>
</body>
</html>