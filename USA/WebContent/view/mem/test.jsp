<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
안녕하세요
<c:if test="${1<2 }">
<script type="text/javascript">
function {
	$("#idPwdMsg").html("ffffff");
}
</script>
</c:if>
<div id="idPwdMsg"></div>
</body>
</html>