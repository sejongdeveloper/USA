<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>locWrite</title>
</head>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/locstyle.css">
<body>
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />

<div class="body">
<div class="loc_writeform">
<form action="locListWritePro.do" method="post" enctype="multipart/form-data">
<table>
<input type="hidden" value="${ loc_regname }" name="loc_regname">
	<tr class="loc_writetitle">
		<td colspan="2" class="loc_writetitle">관광지 등록 양식</td>
	</tr>
	<tr class="loc_writename">
		<td><p>관광지명</td><td><input type="text" name="loc_name" class="loc_writenameinput"></p></td>
	</tr>
	<tr class="loc_writecontents">
		<td><p>설명</td><td><textarea rows="10" cols="100" name="loc_contents"></textarea></p></td>
	</tr>
	<tr class="loc_writefile">
		<td><p>관광지 사진</td><td><input type="file" name="loc_picture"></p></td>
	</tr>
	<tr class="loc_writesubmit">
		<td colspan="2"><p><input type="submit" value="등록"></p></td>
	</tr>

</table>
</form>
</div>
</div>
<jsp:include page="/view/main/footer.html" />
</body>
</html>