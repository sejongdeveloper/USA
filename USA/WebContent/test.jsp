<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<c:set var="num1" value="${123 }"/>
<form action="">

</form>
<input type="button" value="테스트버튼" name="num${num1 }" id="num${num1 }" onclick="test('글자 ${num1}')">
<input type="button" value="테스트버튼2" name="num${num1 }" id="num2${num1 }" onclick="test('글자2 ' + ${num1})">
<input type="button" value="테스트버튼3" name="num${num1 }" id="num3${num1 }" onclick="test2()">
<input type="button" value="테스트버튼4" name="num${num1 }" id="num4${num1 }" onclick="test3()">
<input type="text" id="num2${123 }" value="1212121212">
<script type="text/javascript">
	function test(me) {
		
		alert(me);
	}
	function test2(){
		var tt =document.getElementById("num3" + ${num1}).value;
		alert(tt);
	};
	function test3(){
		var tt =document.getElementById('num4${num1}').value;
		alert(tt);
	};
</script>
</body>
</html>