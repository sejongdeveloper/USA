<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/locstyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
<div class="body">

<div class="locView_loc">
	<div class="locView_picture"><img class="locView_img" alt="사진" src="${ pageContext.request.contextPath }/upload/${ loc_data.loc_filename }"></div><!-- 사진 파일명 -->
	<div class="locView_locname">${ loc_data.loc_writer }님의 추천 ▷ '${ loc_data.loc_name }'</div><!-- 작성자, 관광지명 -->
	<div class="locView_contents">${ loc_data.loc_contents }</div><!-- 관광지 내용 -->
</div>

<div class="locView_rev">
<div class="locView_allCount">리뷰&nbsp;${ rev_allCount }</div><!-- 리뷰 개수 -->
<div class="locView_allScore">${ rev_allScore } / 5.0</div><!-- 리뷰 총 평균 -->
<div class="locView_count"><!-- 각 리뷰 개수 -->
<div class="locView_count5">5점 <progress value="${ rev_count5 }" max="${ rev_allCount }"></progress> ${ rev_count5 }</div>
<div class="locView_count4">4점 <progress value="${ rev_count4 }" max="${ rev_allCount }"></progress> ${ rev_count4 }</div>
<div class="locView_count3">3점 <progress value="${ rev_count3 }" max="${ rev_allCount }"></progress> ${ rev_count3 }</div>
<div class="locView_count2">2점 <progress value="${ rev_count2 }" max="${ rev_allCount }"></progress> ${ rev_count2 }</div>
<div class="locView_count1">1점 <progress value="${ rev_count1 }" max="${ rev_allCount }"></progress> ${ rev_count1 }</div>
</div>

<div class="locView_revWriteForm"><div class="locView_revWriteframe">
<c:if test="${ sessionScope.member != null }" ><!-- 로그인 되있으면 -->
<form action="locWritePro.do" method="post" onsubmit="return revWrite()">
<input type="hidden" value="${ loc_data.loc_name }" name="rev_locname"><!-- 관광지명 -->

<div class="locView_revTitle">평점을 선택해주세요.</div>
<div class="locView_revWriteScoreFrame"><!-- 평점 선택 -->
<div class="locView_revWriteScore"><label><input type="radio" value="1" name="rev_score" class="radio" id="rev_score"><img alt="1" src="${ pageContext.request.contextPath }/upload/1.jpg" class="laimg"></label></div>
<div class="locView_revWriteScore"><label><input type="radio" value="2" name="rev_score" class="radio" id="rev_score"><img alt="1" src="${ pageContext.request.contextPath }/upload/2.jpg" class="laimg"></label></div>
<div class="locView_revWriteScore"><label><input type="radio" value="3" name="rev_score" class="radio" id="rev_score"><img alt="1" src="${ pageContext.request.contextPath }/upload/3.jpg" class="laimg"></label></div>
<div class="locView_revWriteScore"><label><input type="radio" value="4" name="rev_score" class="radio" id="rev_score"><img alt="1" src="${ pageContext.request.contextPath }/upload/4.jpg" class="laimg"></label></div>
<div class="locView_revWriteScore"><label><input type="radio" value="5" name="rev_score" class="radio" id="rev_score"><img alt="1" src="${ pageContext.request.contextPath }/upload/5.jpg" class="laimg"></label></div>
</div>

<div class="locView_revWrite">
<div class="locView_revWriteImg"><img class="locView_faceimg" alt="사진" src="${ pageContext.request.contextPath }/upload/사람이미지.jpeg"></div><!-- 좌측 사람 이미지 -->
<div class="locView_revWriteContents"><textarea rows="10" cols="100" name="rev_contents" id="rev_contents" class="locView_revWriteContents"></textarea></div><!-- 리뷰 작성 영역 -->
<div class="locView_revWriteSubmit"><input type="submit" value="등록" class="locView_revWriteSubmit"></div>
</div></form></c:if></div>

<c:if test="${ sessionScope.member == null }" ><!-- 로그인 안되있으면 -->
<div class="locView_loginwant">
<marquee width="300" direction="right" behavior="alternate">리뷰를 원하시면 회원가입해주세요.</marquee>
</div></c:if></div>

<c:forEach items="${ rev_list }" var="list">
<table>
	<tr>
		<td rowspan="2" class="locView_revWriter">${ list.rev_writer }</td>
		<td class="locView_revContents">
		<form action="locModifyPro.do" method="post" onsubmit="return revModi()"> <!-- 수정 양식 -->
		<input type="hidden" value="${ list.rev_num }" name="rev_num"><!-- 리뷰 번호 -->
		<input type="hidden" value="${ loc_data.loc_name }" name="rev_locname"><!-- 리뷰 관광지명 -->
		<div id="revView_contents${ list.rev_num }" class="revView_contents">${ list.rev_contents }</div><!-- 리뷰 내용 -->
		<div id="revView_contentsmodiform${ list.rev_num }" class="revView_contentsmodiform"><textarea cols="100" rows="8" name="rev_contents" id="rev_modicontents">${ list.rev_contents }</textarea></div>
		</td><!-- 리뷰 수정 내용 -->
		<td rowspan="2" class="locView_revScore">
		<div id="locView_revScorein${ list.rev_num }" class="locView_revScorein">${ list.rev_score }</div>
		<div id="locView_revScoreinmodiform${ list.rev_num }" class="locView_revScoreinmodiform">
			<div class="locView_revScoremodi"><label><input type="radio" value="1" name="rev_modiscore" class="modiradio" id="rev_modiscore"><img alt="1" src="${ pageContext.request.contextPath }/upload/1.jpg" class="laimg modiimg"></label></div>
			<div class="locView_revScoremodi"><label><input type="radio" value="2" name="rev_modiscore" class="modiradio" id="rev_modiscore"><img alt="1" src="${ pageContext.request.contextPath }/upload/2.jpg" class="laimg modiimg"></label></div>
			<div class="locView_revScoremodi"><label><input type="radio" value="3" name="rev_modiscore" class="modiradio" id="rev_modiscore"><img alt="1" src="${ pageContext.request.contextPath }/upload/3.jpg" class="laimg modiimg"></label></div>
			<div class="locView_revScoremodi"><label><input type="radio" value="4" name="rev_modiscore" class="modiradio" id="rev_modiscore"><img alt="1" src="${ pageContext.request.contextPath }/upload/4.jpg" class="laimg modiimg"></label></div>
			<div class="locView_revScoremodi"><label><input type="radio" value="5" name="rev_modiscore" class="modiradio" id="rev_modiscore"><img alt="1" src="${ pageContext.request.contextPath }/upload/5.jpg" class="laimg modiimg"></label></div>
		</div>
		</td>
		<td rowspan="2">
		<input type="submit" value="완료" class="modifyend" id="modifyend${ list.rev_num }">
		</form>
		
		<c:if test="${ list.rev_writer == sessionScope.member }">
			<input type="button" name="modifystart" class="modifystart" value="수정" onclick="modify('${ list.rev_num }', this)" id="modifystart${ list.rev_num }">
			<form action="locDeletePro.do" method="post">
			<input type="hidden" value="${ list.rev_num }" name="rev_num">
			<input type="hidden" value="${ loc_data.loc_name }" name="rev_locname">
			<input type="submit" value="삭제">
			</form></c:if>
		</td>
	</tr>
	<tr>
		<td class="locView_revDate">
		<fmt:formatDate value="${ list.rev_date }" pattern="yyyy / MM / dd" var="date"/>
		${ date }
		</td>
	</tr>
</table>
</c:forEach>
</div>
</div>
<jsp:include page="/view/main/footer.html" />
</body>
<script type="text/javascript">
	function revWrite(){
		var contents = $("#rev_contents").val();
		
		if(contents == null || contents == ""){
			alert("내용을 입력해주세요.");
			$("#rev_contents").focus();
			return false;
		}
		if($(':radio[name="rev_score"]:checked').length < 1){
			alert("평점을 선택해주세요.");
			$("#rev_score").focus();
			return false;
		}
		
		return true;
	}
	
	function revModi(){
		var contents = $("#rev_modicontents").val();
		
		if(contents == null || contents == ""){
			alert("내용을 입력해주세요.");
			return false;
		}
		if($(':radio[name="rev_modiscore"]:checked').length < 1){
			alert("평점을 선택주세요.");
			return false;
		}
		
		return true;
	}
	
	function modify(writer, a){	    
	        var obj = document.getElementsByName("modifystart");
			
	        for(var i=0; i<obj.length; i++){
	            if(obj[i] != a){
	            	<c:forEach items="${ rev_list }" var="list">
	    	        if('${ list.rev_num }' != writer) {
	    	        	document.getElementById('revView_contentsmodiform'+'${ list.rev_num }').style.display='none';
	            	    document.getElementById('modifyend'+'${ list.rev_num }').style.display='none';
	            	    document.getElementById('locView_revScoreinmodiform'+'${ list.rev_num }').style.display='none';
	            	    document.getElementById('revView_contents'+'${ list.rev_num }').style.display='inline';
		        	    document.getElementById('modifystart'+'${ list.rev_num }').style.display='inline';
		        	    document.getElementById('locView_revScorein'+'${ list.rev_num }').style.display='table-cell';
	    	        } 
	    	        </c:forEach>
	            } else {
	        		document.getElementById('revView_contentsmodiform'+writer).value=document.getElementById('revView_contents'+writer).innerHTML;
	        	    document.getElementById('revView_contents'+writer).style.display='none';
	        	    document.getElementById('modifystart'+writer).style.display='none';
	        	    document.getElementById('locView_revScorein'+writer).style.display='none';
	        	    document.getElementById('revView_contentsmodiform'+writer).style.display='inline';
	        	    document.getElementById('modifyend'+writer).style.display='inline';
	        	    document.getElementById('locView_revScoreinmodiform'+writer).style.display='inline';
	            }
	        }
	        
	}
</script>
</html>