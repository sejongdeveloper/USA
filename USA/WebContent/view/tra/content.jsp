<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>MVC 게시판</title>
</head>

<body>
<!-- 게시판 수정 -->
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목&nbsp;&nbsp;</div>
		</td>
		
		<td style="font-family:돋음; font-size:12">
		${vo.board_subject }
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td style="font-family:돋음; font-size:12">
			<table border=0 width=490 height=250 style="table-layout:fixed">
				<tr>
					<td valign=top style="font-family:돋음; font-size:12">
					<c:if test="${vo.board_file!=null}">
					<img src="${pageContext.request.contextPath}/UploadFolder/${vo.board_file}" />
					</c:if>
					${vo.board_content }
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">첨부파일</div>
		</td>
		<td style="font-family:돋음; font-size:12">
		<c:if test="${vo.board_file!=null }">
	
		<a href="FileDownload.do?file_name=${vo.board_file}">
			${vo.board_file }
		</a>
		</c:if>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<
	<tr align="right" valign="middle">
		<td colspan="5">
			<font size=2>
			
			<%-- <c:if test="${sessionScope.id!=null }"> --%>
			<a href="./TradeBoardModifyFormAction.do?num=${vo.board_num }">
			[수정]
			</a>&nbsp;&nbsp;
			<a href="./BoardDelete.do?num=${vo.board_num }">
			[삭제]
			<%-- </c:if> --%>
			
			</a>&nbsp;&nbsp;
			<a href="./list.do">[목록]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>

<br><br><br>


<!-- 로그인 했을 경우만 댓글 작성가능 -->
		<%-- 	<c:if test="${sessionScope.sessionID !=null}"> --%>
			<tr bgcolor="#F5F5F5">
			<form id="writeCommentForm">
				<input type="hidden" name="comment_board" value="${board.board_num}">
				<input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
				<!-- 아이디-->
				<td width="150">
					<div>
						${sessionScope.sessionID}
					</div>
				</td>
				<!-- 본문 작성-->
				<td width="550">
					<div>
						<textarea name="comment_content" rows="4" cols="70" ></textarea>
					</div>
				</td>
				<!-- 댓글 등록 버튼 -->
				<td width="100">
					<div id="btn">
						
						<p><a href="#" onclick="writeCmt()">[댓글등록]</a></p>	
					</div>
				</td>
			</form>
			</tr>
			<%-- </c:if> --%>
	
		</table>

</body>
</html>