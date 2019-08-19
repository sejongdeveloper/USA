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
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<div>
<div class="body">
<div>
<div class="locList_reg">${ loc_nameFileList[0].loc_regname }</div>
<c:if test="${ sessionScope.member != null }">
<div class="locList_add"><a href="locListWriteForm.do?loc_regname=${ loc_nameFileList[0].loc_regname }">관광지 추가</a></div>
</c:if>
</div>
<c:set value="${ loc_scoreList }" var="score" />
<c:forEach items="${ loc_nameFileList }" var="list" varStatus="status">
<div class="locList_list"><a href="locView.do?loc_name=${ list.loc_name }">
<img class="locList_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ list.loc_filename }">
<div class="locList_locname">${ list.loc_name }<div class="locList_locscore"><fmt:formatNumber value="${ score[status.index] }" pattern=".0" />&nbsp;</div></div></a>

<c:if test="${ sessionScope.member == list.loc_writer }">
<div class="locList_modidel"><a href="locListModifyForm.do?loc_regname=${ loc_nameFileList[0].loc_regname }&loc_name=${ list.loc_name }">수정</a></div>
<div class="locList_modidel"><a href="locListDeletePro.do?loc_regname=${ loc_nameFileList[0].loc_regname }&loc_name=${ list.loc_name }">삭제</a></div>
</c:if>
<c:if test="${ sessionScope.member != list.loc_writer }">
<div class="locList_modidel">&nbsp;</div>
</c:if>
</div>
</c:forEach>

</div>
<jsp:include page="/view/main/footer.html" />
</div>
</body>
</html>