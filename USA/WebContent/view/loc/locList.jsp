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
<div class="locList_margin">
<div>
<div class="locList_reg">${ loc_regname }</div><!-- 지역이름 -->

<c:if test="${ sessionScope.member != null }"><!-- 로그인 되있다면 -->
<div class="locList_add"><a href="locListWriteForm.do?loc_regname=${ loc_nameFileList[0].loc_regname }">관광지 추가</a></div><!-- 관광지 추가하기 -->
</c:if>
</div>

<c:set value="${ loc_scoreList }" var="score" /><!-- 관광지들 평점 -->
<c:forEach items="${ loc_nameFileList }" var="list" varStatus="status">
<div class="locList_list"><a href="locView.do?loc_name=${ list.loc_name }">
<img class="locList_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ list.loc_filename }"><!-- 관광지 사진 -->
<div class="locList_locname">${ list.loc_name }<div class="locList_locscore"><fmt:formatNumber value="${ score[status.index] }" pattern=".0" />&nbsp;</div></div></a><!-- 관광지 이름 -->

<c:if test="${ sessionScope.member == list.loc_writer }"><!-- 작성자라면 -->
<div class="locList_modidel"><a href="locListModifyForm.do?loc_name=${ list.loc_name }">수정</a></div><!-- 관광지 수정하기 -->
<div class="locList_modidel"><a href="locListDeletePro.do?loc_regname=${ loc_nameFileList[0].loc_regname }&loc_name=${ list.loc_name }">삭제</a></div><!-- 관광지 삭제하기 -->
</c:if>
<c:if test="${ sessionScope.member != list.loc_writer }"><!-- 작성자가 아니라면 -->
<div class="locList_modidel">&nbsp;</div>
</c:if>
</div>
</c:forEach>

</div>
</div>
<jsp:include page="/view/main/footer.html" />
</div>
</body>
</html>