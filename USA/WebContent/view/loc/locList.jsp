<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="locstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<div class="loclist locList_reg">${ loc_regname }</div>
<c:forEach items="loc_nameFileList" var="picture">
<div class="loclist locList_list">사진<div class="locList_locname">내용</div></div>
</c:forEach>
</body>
</html>
