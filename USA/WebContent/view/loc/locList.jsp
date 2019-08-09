<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/locstyle.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<div>
<div class="body">
<div class="locList_reg">${ loc_nameFileList[0].loc_regname }</div>

<c:set value="${ loc_scoreList }" var="score" />
<c:forEach items="${ loc_nameFileList }" var="list" varStatus="status">
<a href="locView.do?loc_name=${ list.loc_name }"><div class="locList_list">
<img class="locList_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ list.loc_filename }">
<div class="locList_locname">${ list.loc_name }</div><div class="locList_locscore"><fmt:formatNumber value="${ score[status.index] }" pattern=".0" /></div>
</div></a>
</c:forEach>

</div>
<jsp:include page="/view/main/footer.html" />
</div>
</body>
</html>