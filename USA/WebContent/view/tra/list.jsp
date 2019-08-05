<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"  errorPage="../error/error404.jsp" %>
	
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>

<title>게시판</title>
</head>
<body >
ddddddddddddddddddddddd
${listCount } 카운트있니<br>
${condition } 컨디션있니
	<center>
	
		<b>글 목록(전체 글 : ${ listCount })
		</b>

	 <table width="700">
		<tr>
			<td  align="right">
				<a href="writeForm.do">글쓰기</a>
			</td>
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
			<td align="center" width="50">번 호</td>
			<td align="center" width="50">제 목</td>
			<td align="center" width="50">작성자</td>
			<td align="center" width="50">작성날짜</td>
			<td align="center" width="50">조회수</td>
			<td align="center" width="50">I P</td>
			</tr>
			
	
	 <c:forEach var="list"  items="${ list }">    		
		<tr height="30">
			<td align="center" width="50">
				<c:out value="${ list.board_num }" />
				<c:set  var="number"   value="${ listCount - 1 }" />
			</td>
			<td width="250">
			

	   
	  <a 	href="content.do?num=${list.board_num }&page=${ currentPage }">
					${ list.board_subject }</a> 
	
	<c:if test="list.readcount >= 3">
	 	
	 	
	</c:if>
			</td>
			<td align="center" width="100"><a
				href="mailto:${ list.board_id }">${ list.board_id }</a></td>
			<td align="center" width="150">dddddd</td>
			<td align="center" width="150">dddddd</td>
		</tr>
	</c:forEach>
	</table>
</c:if>  
  

 <c:if test="${ listCount > 0 }"> <!--  전체 페이지의 수를 연산 -->
	    <c:set  var="pageCount"  value="${ listCount / pageSize + (listCount % pageSize ==0 ? 0 : 1) }" />
		<c:set  var="startPage"  value="${ 1 }" />  <!-- 차후 수정!! -->
		<c:set  var="pageBlock"  value="${ 2 }" />
		
		
	 	<fmt:parseNumber var="result"  value="${ spage / pageBlock }" integerOnly="true" />
		<c:if  test="${ spage % pageBlock != 0 }" > 
			<c:set var="startPage" value="${ result * pageBlock + 1 }" />
		</c:if>
		
		<c:if  test="${ spage % pageBlock == 0 }" > 
			<c:set var="startPage" value="${ (result - 1) * pageBlock + 1 }" />
		</c:if>
		
		<c:set  var="endPage"  value="${ startPage + pageBlock -1 }" />

		<c:if test="${ endPage > pageCount }" >
			<c:set  var="endPage"  value="${ pageCount }" />
		</c:if>
		
		<c:choose>
		<c:when test="${opt!=null }">
		<c:if test="${startPage >2 }" >
			<a href="list.do?page=${ startPage-1  }&condition=${condition}&opt=${opt}">[이전] </a>
		</c:if>

		<c:forEach  var="i" begin="${startPage }" end="${ endPage }">
			<a href="list.do?page=${i}&condition=${condition}&opt=${opt}">[${ i }] </a>
	   </c:forEach>
	
	<c:if test="${ endPage < pageCount }" >
		<a href="list.do?page=${ startPage+2 }&condition=${condition}&opt=${opt}">[다음] </a>
	</c:if>
	</c:when>
	<c:when test="${opt==null }">
		<c:if test="${startPage >2 }" >
			<a href="list.do?page=${ startPage-1  }">[이전] </a>
		</c:if>

		<c:forEach  var="i" begin="${startPage }" end="${ endPage }">
			<a href="list.do?page=${i}">[${ i }] </a>
	   </c:forEach>
	
	<c:if test="${ endPage < pageCount }" >
		<a href="list.do?page=${ startPage+2 }">[다음] </a>
	</c:if>
	
	
	
	</c:when>
	
	
	
	
	
	
	
	</c:choose>
</c:if> 
<br>
<form>
			<select name="opt">
				<option value="0">제목</option>
				<option value="1">내용</option>
				<option value="2">제목+내용</option>
				<option value="3">글쓴이</option>
			</select>
			<input type="text" size="20" name="condition"/>&nbsp;
			<input type="submit" value="검색"/>
		</form>	


</body>
</center>
</html>