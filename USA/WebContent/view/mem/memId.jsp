<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>

<link rel="stylesheet" href="${contextPath }/view/mem/css/memId.css">

</head>
<body>
<c:if test="${mem_id }">
	아이디 : ${mem_id }
</c:if>
<form action="${pageContext.request.contextPath }/memIdPro.do">
  <div class="main">
  <div class="detail">회원정보를 검색하기 위해서는, 본인 확인 후 아이디를 찾아드립니다.</div>
  <div class="name"><label for="mem_name">이름</label></div>
	<div class="data"><input type="text" name="mem_name" id="mem_name"></div>
  <div class="br"></div>
	<div class="name"><label for="mem_addr">주소</label></div>
	<div class="data"><input type="text" name="mem_addr" id="mem_addr"></div>
  <div class="br"></div>
  </div>
  <div id="submit"><input type="submit" value="다음"></div>
</form>
</body>
</html>
