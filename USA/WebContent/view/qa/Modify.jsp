<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>�� ����</title>



</head>
<body>
	<jsp:include page="/view/main/money.jsp" />
	<jsp:include page="/view/main/weather.jsp" />
	<br>
	<b><font size="6" color="gray">�� ����</font></b>
	<br> ${vo.tra_num}
	<form method="post"
		action="QABoardModifyProAction.do?num=${vo.tra_num}"
		enctype="multipart/form-data">



		<input type="hidden" name="existing_file" value="${vo.tra_filename}" />

		<table width="700" border="3" bordercolor="lightgray" align="center">
			<tr>
				<td id="title">�ۼ���</td>
				<td>${vo.tra_writer}</td>
			</tr>
			<tr>
				<td id="title">�� ��</td>
				<td><input name="board_subject" type="text" size="70"
					maxlength="100" value="${vo.tra_subject}" /></td>
			</tr>
			<tr>
				<td id="title">�� ��</td>
				<td><textarea name="board_content" cols="72" rows="20">${vo.tra_contents}</textarea>
				</td>
			</tr>

			<tr>
				<td id="title">���� ����</td>
				<td>&nbsp;&nbsp; ${vo.tra_filename}</td>
			</tr>
			<tr>
				<td id="title">÷������</td>
				<td><input type="file" name="board_file" /></td>
			</tr>


			<tr align="center" valign="middle">
				<td colspan="5"><input type="submit" value="����"> <input
					type="button" value="���" onclick="window.location.href='QAlist.do'">

				</td>
			</tr>
		</table>
	</form>

</body>
</html>