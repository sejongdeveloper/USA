<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function idValidate() {
		var mem_id = $("#mem_id").val();
		$.ajax({
			type:"post",
			async:false,
			url:"../../memIdValidate.do",
			data:{idInfo:mem_id},
			success:function(data, textStatus){
				var jsonInfo = JSON.parse(data);
				var memberInfo = "?Œì› ?•ë³´<br>";
				memberInfo += "======<br>";
				console.log(memberInfo);
				for(var i in jsonInfo.members) {
					memberInfo += "?´ë¦„: " + jsonInfo.members[i].name + "<br>";
					memberInfo += "?˜ì´: " + jsonInfo.members[i].age + "<br>";
				}
				console.log(memberInfo);
				$("#output").html(memberInfo);
				console.log(memberInfo);
				
			}
		});
	}
</script>
</head>
<body>
?„ì´?? : ${mem_id }
<form action="${pageContext.request.contextPath }/memIdPro.do">
	?´ë¦„ <input type="text" name="mem_name"><br>
	ì£¼ì†Œ <input type="text" name="mem_addr"><br>
	<input type="submit" value="ì°¾ê¸°">

<form action="${pageContext.request.contextPath }/memWritePro.do" method="post" enctype="multipart/form-data">
	?„ì´?? : <input type="text" name="mem_id" id="mem_id"> <input type="button" value="?„ì´?”í™•??" onclick="idValidate()"><br>
	<div id="output"></div>
	ë¹„ë?ë²ˆí˜¸ : <input type="password" name="mem_pwd"><br>
	?´ë¦„ : <input type="text" name="mem_name"><br>
	?„í™”ë²ˆí˜¸ : <input type="text" name="mem_ph"><br>
	ì£¼ì†Œ : <input type="text" name="mem_addr"><br>
	?Œì¼ : <input type="file" name="mem_filename"><br>
	<input type="submit" value="?Œì›ê°€??">
</form>
</body>
</html>
</head>
<body>
?„ì´?? : ${mem_id }
<form action="${pageContext.request.contextPath }/memIdPro.do">
	?´ë¦„ <input type="text" name="mem_name"><br>
	ì£¼ì†Œ <input type="text" name="mem_addr"><br>
	<input type="submit" value="ì°¾ê¸°">
</form>
</body>
</html>