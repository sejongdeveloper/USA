<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>    
<style type="text/css">
#header table {
	background-color: black;
	width: 1000px;
	height: 40px;
}

#header td {

}

#header td a {
	color: white;
	text-decoration: none;
}

#header td a:hover {
	text-decoration: underline;
	cursor: pointer;
}

#header #title {
	font-weight: bold;
	font-size: 25px;
	padding-left: 80px;
}

#header #login {
	padding-left: 100px;
	text-align: left;
	width: 100px;
	background-color: black;
}
</style>
	<div id="header">
	<table>
	<!-- 헤더 영역입니다. -->
	<tr>
	
		<td id="title"><a href="index.do">site title</a></td>
		<td><a href="">Information</a></td>
		<td><a href="">Community</a></td>
		<td><a href="">Event</a></td>
	<c:if test="${empty member }">
		<td id="login"><a href="${contextPath }/memLoginForm.do">Sign In</a></td>
	</c:if>
	<c:if test="${not empty member }">
		<td id="login"><a href="${contextPath }/memLogout.do">Sign Out</a></td>	
	</c:if>  
	
	</tr>
	
	</table>
	</div>
