<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EventMain</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="/view/main/header.jsp" flush="false" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
	<table align="center" position="relative">
		
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
	
		<tr bgcolor="white" top="130px" width="70" height="30">
			<td><font weight="bold">이벤트</font></td>
		</tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<Tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></Tr>

		<tr bgcolor="white" top="130px" width="70" height="30">
			<td align="center"><font weight="bold">콘서트&nbsp; > &nbsp;아이유</font></td>
			<td align="center"><font weight="bold">뮤지컬&nbsp; > &nbsp;영웅</font></td>
		</tr>
		
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		
		<tr align="center">
			<td><img src="${ pageContext.request.contextPath }/upload/IUConcert.jpg" align="center" width="270" height="380"></td>
			<td><img src="${ pageContext.request.contextPath }/upload/HeroMusical.jpg" align="center" width="270" height="380"></td>
		</tr>
		
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		
		<tr></tr>
		<tr bgcolor="white" top="130px" width="70" height="30">
			<td align="center"><font color="gray">1인 2매 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
														 &nbsp; 잠실 종합경기장</font></td>
			<td align="center"><font color="gray">&nbsp; 1인 2매 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
												 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 예술의 전당
								</font></td>
		</tr>
		
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		
		<tr bgcolor="white" top="130px" width="70" height="30">
			<td align="center"><font color="gray">응모기간 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
													 2019.08.01 ~ 08.14</font></td>
			<td align="center"><font color="gray">&nbsp; 응모기간 &nbsp; &nbsp; &nbsp; &nbsp;
			 										&nbsp; &nbsp; 2019.08.01 ~ 08.14</font></td>
		</tr>
		
		<tr bgcolor="white" top="130px" width="70" height="30">
			<td align="center"><font color="gray">당첨자 발표 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
													 &nbsp; &nbsp; &nbsp; &nbsp; 2019.08.15</font></td>
			<td align="center"><font color="gray">&nbsp;당첨자 발표 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
												 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 2019.08.15
												 </font></td>
		</tr>
		
		<tr bgcolor="white" top="130px" width="70" height="30">
			<td align="center"><font color="gray">공연날짜 &nbsp; &nbsp; &nbsp; &nbsp; 2019.08.22(목) 
													오후 8시</font></td>
			<td align="center"><font color="gray">&nbsp; 공연날짜 &nbsp; &nbsp; &nbsp; &nbsp;
													2019.08.22(목)오후 8시</font><td>
		</tr>
		
		<tr bgcolor="white" top="130px" width="70" height="30">
			<td align="center"><font color="gray">총 응모 &nbsp; &nbsp; &nbsp; &nbsp; 243 &nbsp; &nbsp;
													 &nbsp;</font></td>
			<td align="center"><font color="gray">&nbsp; &nbsp; &nbsp; 총 응모 &nbsp; &nbsp; &nbsp;
													 &nbsp; 243&nbsp; &nbsp; &nbsp;</font></td>
		</tr>
		
		<tr top="130px" width="70" height="30">
			 <td align="center" bgcolor="D8D8D8" onclick="ajaxevent()")><font size="2" weight="bold">&nbsp; &nbsp;응모하기</font></td>
			<td align="center" bgcolor="D8D8D8" onClick="ajaxevent()")><font size="2" weight="bold">&nbsp; &nbsp;응모하기</font></td>
		</tr>
		
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		<tr><td align="center"><font color="white" size="1" weight="bold">#</font></td></tr>
		
	</table>

<jsp:include page="/view/main/footer.html" flush="false" />
<script>
 
 function ajaxevent(){
	  sessionid="${sessionScope.member}";
	 if(sessionid==null||sessionid==""){
		 alert("로그인하셔야 이용할수 있어요~");
		 return ;
	 }  
	 
	 obj = new Object();
	 obj.session=sessionid;
	 $.ajax({ 
		type:"post",  
		url:"./Eventcheck.do",
		data:{data : JSON.stringify(obj)},
		success:function(data2){
			var data2 = JSON.parse(data2)
			alert(data2.result);
		}
	 });
  
 }
 

</script>
</body>
</html>