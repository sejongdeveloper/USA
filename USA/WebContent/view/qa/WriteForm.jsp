<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gogo</title>
</head>
<body>
	<jsp:include page="/view/main/money.jsp" />
	<jsp:include page="/view/main/weather.jsp" />
	<br>
	<b><font size="6" color="gray">글쓰기</font></b>
	<br>

	<form method="post" action="QABoardWriteAction.do"
		name="QABoardForm" enctype="multipart/form-data">
		<input type="hidden" name="board_id" value="${sessionScope.nickname}">

		<table width="700" border="3" bordercolor="lightgray" align="center">
			
			
			<tr>
				<td id="title">작성자</td>
				<td>${sessionScope.nickname}</td>
			</tr>
			<tr>
				<td id="title">제 목</td>
				<td><input name="board_subject" type="text" size="70"
					maxlength="100" value="" /></td>
			</tr>
			<tr>
				<td id="title">내 용</td>
				<td><textarea name="board_content" cols="72" rows="20"></textarea>
				</td>
			</tr>
			<tr>
				<td id="title">파일첨부</td>
				<td><input type="file" name="board_file" /></td>
			</tr>

			<tr align="center" valign="middle">
				<td colspan="5"><input type="submit" value="등록"> <input
					type="button" value="목록" onclick="location.href='/QAlist.do'">
				</td>
			</tr>
		</table>
	</form>


</body>
</html>