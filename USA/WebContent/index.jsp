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
<a href="view/tra/list.do">리스트로가자</a>
<footer>
	<jsp:include page="view/main/footer.jsp" flush="false" />
</footer>
</body>
</html>

