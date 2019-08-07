<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>

<style>
#main {
	border: 1px solid gray;
	width: 500px;
	height: 200px;
	padding:  50px 50px 50px 50px;
}
.data label {
	font-weight: bold;
	width: 100px;
}

.name, .data {
	display: inline-block;
}

</style>

</head>
<body>
<c:if test="${mem_id }">
	아이디 : ${mem_id }
</c:if>
<div id="main">
<form action="${pageContext.request.contextPath }/memIdPro.do">
	<div class="name"><label for="mem_name">이름</label></div>
	<div class="data"><input type="text" name="mem_name" id="mem_name"></div><br>

	<div class="name"><label for="mem_addr">주소</label></div>
	<div class="data"><input type="text" name="mem_addr" id="mem_addr"></div><br>
	<input type="submit" value="찾기">
</form>
</div>
</body>
</html>