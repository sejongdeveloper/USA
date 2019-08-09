<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
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

	<c:if test="${not empty param.contents }">
		<jsp:include page="${param.contents }" flush="false" />
	</c:if>
	<c:if test="${not empty contents }">
		<jsp:include page="${contents }" flush="false" />	
	</c:if>
	<c:if test="${empty contents && empty param.contents }">
		<jsp:include page="view/main/main.jsp" flush="false" />
	</c:if>

</section>

<footer>
	<jsp:include page="view/main/footer.html" flush="false" />
</footer>
</body>
</html>

