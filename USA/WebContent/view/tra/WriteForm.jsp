<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gogo</title>
</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
	<br><br><br><br><br>
	<div style="display: inline-block; margin-left: 350px; width: 1250px; margin-bottom: 130px;">
	<b style="margin-left: 530px;"><font size="6" color="gray">글쓰기 양식</font></b>
	<br><br><br>
	
	<form method="post" action="TradeBoardWriteAction.do" name="TradeBoardForm" enctype="multipart/form-data">
	<input type="hidden" name="board_id" value="${sessionScope.nickname}">
	
	<table width="700" border="3" bordercolor="lightgray" align="center">
		<tr>
			<td rowspan="2" height="20px"> <select name='tra_head' >
				<option value="팝니다" selected="selected">팝니다</option>
				<option value="삽니다">삽니다</option>
</select>
		</td>
		</tr>
		<tr>
		<td>분류</td>
		</tr>
		<tr>
			<td id="title">작성자</td>
			<td>${sessionScope.nickname}</td>
		</tr>
			<tr>
			<td id="title">
				제 목
			</td>
			<td>
				<input name="board_subject" type="text" size="70" maxlength="100" value=""/>
			</td>		
		</tr>
		<tr>
			<td id="title">
				내 용
			</td>
			<td>
				<textarea name="board_content" cols="72" rows="20"></textarea>			
			</td>		
		</tr>
		<tr>
			<td id="title">
				파일첨부
			</td>
			<td>
				<input type="file" name="board_file" />
			</td>	
		</tr>

		<tr align="center" valign="middle">
			<td colspan="5">
				<input type="submit" value="등록" >
				<input type="button" value="목록" onclick="location.href='list.do'" >			
			</td>
		</tr>
	</table>	
	</form>
	</div>
<jsp:include page="/view/main/footer.html" />
</body>
</html>