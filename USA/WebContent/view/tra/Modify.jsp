<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>글 수정</title>
	
	
	
</head>
<body>

	<br>
	<b><font size="6" color="gray">글 수정</font></b>
	<br>
	
	<form method="post" action="TradeBoardModifyProAction.do?page=${pageNum}" name="boardForm" 
			enctype="multipart/form-data">

	<input type="hidden" name="board_num" value="${board.board_num}"/>
	<input type="hidden" name="existing_file" value="${board.board_file}"/>

	<table width="700" border="3" bordercolor="lightgray" align="center">
		<tr>
			<td id="title">작성자</td>
			<td>${board.board_id}</td>
		</tr>
			<tr>
			<td id="title">
				제 목
			</td>
			<td>
				<input name="board_subject" type="text" size="70" maxlength="100" 
					value="${board.board_subject}"/>
			</td>		
		</tr>
		<tr>
			<td id="title">
				내 용
			</td>
			<td>
				<textarea name="board_content" cols="72" rows="20">${board.board_content}</textarea>			
			</td>		
		</tr>
				
			<tr>
				<td id="title">
					기존 파일
				</td>
				<td>
					&nbsp;&nbsp; ${board.board_file}
				</td>	
			</tr>
			<tr>
				<td id="title">
					첨부파일
				</td>
				<td>
					<input type="file" name="board_file"/>
				</td>	
			</tr>
		
		
		<tr align="center" valign="middle">
			<td colspan="5">
				<input type="submit" value="수정" onclick="'window.location.href='TradeBoardModifyProAction'" >
				<input type="button" value="목록" onclick="window.location.href='list.do'" >			
			</td>
		</tr>
	</table>	
	</form>
	
</body>
</html>