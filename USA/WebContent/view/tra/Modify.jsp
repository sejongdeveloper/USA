<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>글 수정</title>
	
	
	
</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
	<br><br><br><br><br>
	<div style="display: inline-block; margin-left: 350px; width: 1250px; margin-bottom: 130px;">
	<b style="margin-left: 550px;"><font size="6" color="gray">글 수정</font></b>
	<br><br><br>
	
	<form method="post" action="TradeBoardModifyProAction.do?num=${vo.tra_num}"  enctype="multipart/form-data">
	
 
	
	<input type="hidden" name="existing_file" value="${vo.tra_filename}"/>
	
	<table width="700" border="3" bordercolor="lightgray" align="center">
		<tr>
			<td id="title">작성자</td>
			<td>${vo.tra_writer}</td>
		</tr>
			<tr>
			<td id="title">
				제 목
			</td>
			<td>
				<input name="board_subject" type="text" size="70" maxlength="100" 
					value="${vo.tra_subject}"/>
			</td>		
		</tr>
		<tr>
			<td id="title">
				내 용
			</td>
			<td>
				<textarea name="board_content" cols="72" rows="20">${vo.tra_contents}</textarea>			
			</td>		
		</tr>
				
			<tr>
				<td id="title">
					기존 파일
				</td>
				<td>
					&nbsp;&nbsp; ${vo.tra_filename}
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
				<input type="submit" value="수정"  >
				<input type="button" value="목록" onclick="window.location.href='Tradelist.do'" >			
	
			</td>
		</tr>
	</table>	
	</form>
	</div>
<jsp:include page="/view/main/footer.html" />
</body>
</html>