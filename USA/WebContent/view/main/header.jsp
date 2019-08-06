<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="	UTF-8">
<title>Header</title>
<style type="text/css">
	a {
		text-decoration: none;
	}
	td > a {
		color: white;
	}
	.headcontent {
		font-size: 10px;
	}
	.header {
		background-color: black;
		height: 10px;
		width: 100%;
		margin: auto;
		text-align: center;
	}
	.headside {
		width: 20%;
	}
	.headcenter {
		width: 15%;
	}
	.signin {
		position: absolute;
		top: 10px;
		right: 20px;
		font-size: 10px;
		text-decoration: none;
		color: gray;
	}
</style>
</head>
<body>
	<table class=header>
		<tr>
			<td class="headside">&nbsp;</td>
			<td class="headcenter"><a href="">site title</a></td>
			<td class="headcenter"><a href="" class="headcontent">Information</a></td>
			<td class="headcenter"><a href="" class="headcontent">Community</a></td>
			<td class="headcenter"><a href="" class="headcontent">Event</a></td>
			<td class="headside">&nbsp;</td>
		</tr>
	</table>
	<div><a href="" class="signin">Sign In</a></div>
</body>
</html>



