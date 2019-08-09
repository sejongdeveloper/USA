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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>    
<script type="text/javascript">
	$(document).ready(function(){
		var obj = jQuery.parseJSON( '{ "name": "John" }' );
		alert( obj.name === "John" );	
	});
</script>
<div id="result"></div>
</body>
</html>