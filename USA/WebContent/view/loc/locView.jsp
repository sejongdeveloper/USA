<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<link rel="stylesheet" href="${ pageContext.request.contextPath }/view/loc/locstyle.css">
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<div class="locView_loc">
	<div class="locView_picture"><img class="locView_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ loc_data.loc_filename }"></div>
	<div class="locView_locname">${ loc_data.loc_name }</div>
	<div class="locView_contents">${ loc_data.loc_contents }</div>
</div>
<div class="locView_rev">
<div class="locView_allCount">리뷰&nbsp;${ rev_allCount }</div>
<div class="locView_allScore">${ rev_allScore }</div>
<div class="locView_count">
<div class="locView_count5">5점: <progress value="${ rev_count5 }" max="${ rev_allCount }"></progress> ${ rev_count5 }</div>
<div class="locView_count4">4점: <progress value="${ rev_count4 }" max="${ rev_allCount }"></progress> ${ rev_count4 }</div>
<div class="locView_count3">3점: <progress value="${ rev_count3 }" max="${ rev_allCount }"></progress> ${ rev_count3 }</div>
<div class="locView_count2">2점: <progress value="${ rev_count2 }" max="${ rev_allCount }"></progress> ${ rev_count2 }</div>
<div class="locView_count1">1점: <progress value="${ rev_count1 }" max="${ rev_allCount }"></progress> ${ rev_count1 }</div>
</div>

<c:if test="${ sessionScope.member != null }" >
<form action="locWritePro.do" method="post">
<input type="hidden" value="${ loc_data.loc_name }" name="loc_name">
<div class="locView_revTitle">평점을 선택해주세요.</div>
<div class="locView_revWriteScoreFrame">
<div class="locView_revWriteScore"><input type="radio" value="1" name="rev_score"></div>
<div class="locView_revWriteScore"><input type="radio" value="2" name="rev_score"></div>
<div class="locView_revWriteScore"><input type="radio" value="3" name="rev_score"></div>
<div class="locView_revWriteScore"><input type="radio" value="4" name="rev_score"></div>
<div class="locView_revWriteScore"><input type="radio" value="5" name="rev_score"></div>
</div>

<div class="locView_revWrite">
<div class="locView_revWriteImg">사진</div>
<div class="locView_revWriteContents"><textarea rows="10" cols="100" name="rev_contents" class="locView_revWriteContents"></textarea></div>
<div class="locView_revWriteSubmit"><input type="submit" value="등록" class="locView_revWriteSubmit"></div>
</div>
</form>
</c:if>
<c:if test="${ sessionScope.member == null }" >
<div class="locView_loginwant">
리뷰를 원하시면 회원가입해주세요.
</div>
</c:if>

<c:forEach items="${ rev_list }" var="list">
<div class="locView_revFrame">
<div class="locView_revWriter">${ list.rev_writer }</div>
<div class="locView_revContents">${ list.rev_contents }</div>
<fmt:formatDate value="${ list.rev_date }" pattern="yyyy / MM / dd" var="date"/>
<div class="locView_revDate">${ date }</div>
<div class="locView_revModify">
<form action="locModifyPro.do">
<input type="hidden" value="${ list.rev_num }" name="rev_num">
<input type="hidden" value="${ loc_data.loc_name }" name="loc_name">
<input type="submit" value="수정">
</form>
</div>
<div class="locView_revDelete">
<form action="locDeletePro.do">
<input type="hidden" value="${ list.rev_num }" name="rev_num">
<input type="hidden" value="${ loc_data.loc_name }" name="loc_name">
<input type="submit" value="삭제">
</form>
</div>
<div class="locView_revScore">${ list.rev_score }</div>
</div>
</c:forEach>

</div>
</body>
</html>