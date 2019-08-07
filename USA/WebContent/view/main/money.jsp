<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.ajax-cross-origin.min.js"></script> <!-- 크로스 도메인 이슈해결 -->
<script type="text/javascript">
	$(document).ready(function(){
		var today = new Date();
		var dd = today.getDate() - 1; // 현재 날짜 못 얻는 경우 있음
		var mm = today.getMonth() + 1; 
		var yyyy = today.getFullYear();
		if(dd<10) {
		    dd='0'+dd
		} 

		if(mm<10) {
		    mm='0'+mm
		} 

		var tt = yyyy+mm+dd;
		var url = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=kRIhErX5mXekrgcMNZecYnVvmluclGMM&searchdate=" + tt + "&data=AP01";
		$.ajax({
			type : "get",
			async : false,
			datatype : "json",
			crossOrigin : true, // 크로스 도메인 이슈해결
			url: url,
			success : function(data) {
				var info = JSON.parse(data);
				$("#result").html("미국 환율 : " + info[21].bkpr);
			}
		});
	});
	
</script>
</head>
<body>
<span id="result"></span>
</body>
</html>