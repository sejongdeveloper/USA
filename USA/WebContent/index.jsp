<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
<c:if test="${not empty member }">
	${member }님 환영합니다^^
</c:if> 
<a href="test.html">테스트 이동</a>
<header>
	<jsp:include page="view/headfoot/header.jsp" flush="false" />
</header>

<section>
<c:choose>
	<c:when test="${empty contents }">
		<jsp:include page="view/main/main.jsp" flush="false" />
	</c:when>
	
	<c:otherwise>
		<jsp:include page="${contents }" flush="false" />	
	</c:otherwise>
</c:choose>
</section>

<footer>
	<jsp:include page="view/main/footer.jsp" flush="false" />
</footer>
</body>
</html>

