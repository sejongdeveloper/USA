<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  errorPage="../error/error404.jsp" %>
	
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>

<title>게시판</title>
</head>

<body>
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<Br><Br><Br><Br>
<div style="height: 700">


	<center>
	  보기 > 
	  <div style="display: inline-block;"><a href="QAlist.do?pagesize=${pagesize }&condition=${condition}" style="text-decoration: none; font-size: 20; margin-right: 30;">전체 목록</a></div>
	  <!-- 10개씩 보기 20개씩 보기에는 검색조건도 유지시켜주면서 pagesize도 유지해야하니 opt <-- 제목 내용 제목+내용 글쓴이 인지 condition은 자세한 내용 -->
	  <div style="display: inline-block;"><a href="QAlist.do?pagesize=10&condition=${condition}&opt=${opt}" style="text-decoration: none; font-size: 20; margin-right: 20;">10개씩보기 </a></div>
	  <div style="display: inline-block;"><a href="QAlist.do?pagesize=20&condition=${condition}&opt=${opt}" style="text-decoration: none; font-size: 20;">20개씩보기 </a></div>
<br><Br><Br>
	
		<b><br>
		Q & A 글 목록(전체 글 : ${ listCount })
		</b><br>

	 <table width="700">
		<tr>
		<!-- 로그인해야만 글쓰기 가능 -->
		<c:if test="${sessionScope.member!=null }">
			<td  align="right">
				<a href="QAwriteForm.do" style="text-decoration: none;">글쓰기</a>
			</td>
			</c:if>
		</tr>
	</table>
	<!-- 게시글이 하나도 없다면 게시글이 없다고 설명. -->
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
			</tr>
			
		<!-- 게시글글 목록은 여러개이기떄문에  list에 foreach로 돌림. -->
	 <c:forEach var="list"  items="${ list }">    		
		<tr height="30">
			<td align="center" width="50">
			<!--  게시판글번호. --> 
				<c:out value="${ list.qa_num }" />
			</td>
			<td width="250">
			

	   		<!-- 어느페이지의 어떤글인지 글선택을 했을시 넘김. --> 
	  <a 	href="QAcontent.do?num=${list.qa_num }&page=${ currentPage }" style="text-decoration: none; color: black;">
					${ list.qa_subject }</a> 
	
	
			</td>
			<td align="center" width="100">${ list.qa_writer } </td>
			<td align="center" width="150">${ list.qa_sysdate }</td>
			<td align="center" width="150">${ list.qa_readcount}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>  
  

<br>















<%-- 게시글이 하나도 없을수있으니 >0으로 구분. --%>
 <c:if test="${ listCount > 0 }"> <!--  전체 페이지의 수를 연산 -->
		
		
		
		
<%-- 총페이지보다 마지막페이지가 크면  총페이지를 마지막페이지로 설정. --%>
		<c:if test="${ endPage > pageCount }" >
			<c:set  var="endPage"  value="${ pageCount }" />
		</c:if>
		<%--  --%>
		<c:choose>
		<%-- opt가 null이 아니라는것은 검색조건이 있다는것. --%>
		<c:when test="${opt!=null }">
		<%-- 그래서 이전 이후 다음 그리고 몇번쨰 페이지로 갈지를 정할떄 검색조건 검색내용 페이지를 몇개볼건지 같이 넘김.--%>
		<c:if test="${startPage >5 }" >
			<a href="QAlist.do?page=${ startPage-1  }&condition=${condition}&opt=${opt}&pagesize=${pagesize}" style="text-decoration: none; color: black;">[이전] </a>
		</c:if>

		<c:forEach  var="i" begin="${startPage }" end="${ endPage }">
			<a href="QAlist.do?page=${i}&condition=${condition}&opt=${opt}&pagesize=${pagesize}" style="text-decoration: none; color: black;">[${ i }] </a>
	   </c:forEach>
	
	<c:if test="${ endPage < pageCount }" >
		<a href="QAlist.do?page=${ startPage+pageBlock }&condition=${condition}&opt=${opt}&pagesize=${pagesize}" style="text-decoration: none; color: black;">[다음] </a>
	</c:if>
	</c:when>
	<%-- 여기까지가 검색조건이 있을떄. --%>


					<%-- 검색조건이 null이면. 몇번쨰 페이지인지 몇개씩 볼건지  분류는 어떤건지만 서버로 값을 넘겨줌. --%>
	<c:when test="${opt==null }">
		<c:if test="${startPage >5 }" >
			<a href="QAlist.do?page=${ startPage-1  }&pagesize=${pagesize}" style="text-decoration: none; color: black;">[이전] </a>
		</c:if>

		<c:forEach  var="i" begin="${startPage }" end="${ endPage }">
			<a href="QAlist.do?page=${i}&pagesize=${pagesize}" style="text-decoration: none; color: black;">[${ i }] </a>
	   </c:forEach>
	
	<c:if test="${ endPage < pageCount }" >
		<a href="QAlist.do?page=${ startPage+pageBlock }&pagesize=${pagesize}" style="text-decoration: none; color: black;">[다음] </a>
	</c:if>

	</c:when>
	</c:choose>
	
	
</c:if> 
<br>
<br><br>
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
</div>
</center>
<jsp:include page="/view/main/footer.html" />
</body>
<script>
//서버로부터 값을 가져올떄 삽니다 팝니다등의 값을 유지하기위해. 자바스크립트로 처리.


</script>
</html>