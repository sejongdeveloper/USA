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

<header>
	<jsp:include page="view/main/header.jsp" flush="false" />
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