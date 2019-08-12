<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  errorPage="../error/error404.jsp" %>
	
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>

<title>게시판</title>
</head>

<body id='54321' onload="tra_headcheck()">
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<Br><Br><Br>
<div>


	<center>
	  
	  <a href="list.do?tra_head=전체&pagesize=${pagesize }&condition=${condition}">전체 </a>
	  <a href="list.do?tra_head=팝니다&pagesize=${pagesize }&condition=${condition}">팝니다. </a>
	  <a href="list.do?tra_head=삽니다&pagesize=${pagesize }&condition=${condition}">삽니다 </a>  
<br>
	<a href="list.do?pagesize=10&condition=${condition}&opt=${opt}&tra_head=${tra_head}">10개씩보기 </a>
	<a href="list.do?pagesize=20&condition=${condition}&opt=${opt}&tra_head=${tra_head}">20개씩보기 </a>
<br>
	
		<b>
		${tra_head }글 목록(전체 글 : ${ listCount })
		</b>

	 <table width="700">
		<tr>
		<c:if test="${sessionScope.member!=null }">
			<td  align="right">
				<a href="writeForm.do">글쓰기</a>
			</td>
			</c:if>
		</tr>
	</table>
	
<c:if test="${ listCount == 0 }">	
	<table width="700" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table>	
</c:if>

	
<c:if test="${ listCount > 0 }">	
	<table width="700" border="1" cellpadding="0" cellspacing="0"
		align="center">
		<tr >
			<td align="center" width="50">글 번 호</td>
			<td align="center" width="50">제 목</td>
			<td align="center" width="50">작성자</td>
			<td align="center" width="50">작성날짜</td>
			<td align="center" width="50">조회수</td>
			<td align="center" width="50">분 류 </td>
			</tr>
			
		
	 <c:forEach var="list"  items="${ list }">    		
		<tr height="30">
			<td align="center" width="50">
				<c:out value="${ list.tra_num }" />
			</td>
			<td width="250">
			

	   
	  <a 	href="content.do?num=${list.tra_num }&page=${ currentPage }">
					${ list.tra_subject }</a> 
	
	
			</td>
			<td align="center" width="100">${list.tra_writer} </td>
			<td align="center" width="150">${list.tra_sysdate }</td>
			<td align="center" width="150">${ list.tra_readcount}</td>
			<td align="center" width="150">${ list.tra_head}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>  
  


















 <c:if test="${ listCount > 0 }"> <!--  전체 페이지의 수를 연산 -->
		
		
		
		

		<c:if test="${ endPage > pageCount }" >
			<c:set  var="endPage"  value="${ pageCount }" />
		</c:if>
		
		<c:choose>
		<c:when test="${opt!=null }">
		<c:if test="${startPage >5 }" >
			<a href="list.do?page=${ startPage-1  }&condition=${condition}&opt=${opt}&pagesize=${pagesize}">[이전] </a>
		</c:if>

		<c:forEach  var="i" begin="${startPage }" end="${ endPage }">
			<a href="list.do?page=${i}&condition=${condition}&opt=${opt}&pagesize=${pagesize}">[${ i }] </a>
	   </c:forEach>
	
	<c:if test="${ endPage < pageCount }" >
		<a href="list.do?page=${ startPage+pageBlock }&condition=${condition}&opt=${opt}&pagesize=${pagesize}">[다음] </a>
	</c:if>
	</c:when>
	<c:when test="${opt==null }">
		<c:if test="${startPage >5 }" >
			<a href="list.do?page=${ startPage-1  }&pagesize=${pagesize}&tra_head=${tra_head}">[이전] </a>
		</c:if>

		<c:forEach  var="i" begin="${startPage }" end="${ endPage }">
			<a href="list.do?page=${i}&pagesize=${pagesize}&tra_head=${tra_head}">[${ i }] </a>
	   </c:forEach>
	
	<c:if test="${ endPage < pageCount }" >
		<a href="list.do?page=${ startPage+pageBlock }&pagesize=${pagesize}&tra_head=${tra_head}">[다음] </a>
	</c:if>

	</c:when>
	</c:choose>
	
	
</c:if> 
<br>
<form>
	<select id="tra_head" >
				<option value="전체">전체</option>
				<option value="팝니다">팝니다</option>
				<option value="삽니다">삽니다</option>
	</select>
			<select name="opt">
				<option value="0">제목</option>
				<option value="1">내용</option>
				<option value="2">제목+내용</option>
				<option value="3">글쓴이</option>
			</select>
			<input type="text" size="20" name="condition"/>&nbsp;
			<input type="submit" value="검색"/>
		</form>	
</div>
</center>
<jsp:include page="/view/main/footer.html" />
</body>
<script>
function tra_headcheck(){
	var check= "${tra_head}";
	if(check=="전체"){
		 document.getElementById("tra_head").options[0].selected = true;
	}else if(check=="팝니다"){
		document.getElementById("tra_head").options[1].selected = true;
		
	}else if(check=="삽니다"){
		document.getElementById("tra_head").options[2].selected = true;
		
	}
	console.log(check);
	
}
</script>
</html>