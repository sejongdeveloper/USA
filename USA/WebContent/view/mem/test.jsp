<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP</title>
</head>
<body>
<input type="number" name="name1" class="class1" maxlength="2" oninput="maxLengthCheck(this)">
</body>

<script type="text/javascript">

function maxLengthCheck(object){
	alert("aaaa");
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }    
}


</script>

</html>