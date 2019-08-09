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
<script>
function calcTime(city, offset) {
    // create Date object for current location
    var d = new Date();

    // convert to msec
    // subtract local time zone offset
    // get UTC time in msec
    var utc = d.getTime() + (d.getTimezoneOffset() * 60000); // 우리지역시간(밀리초) + (세계표준시(분) * 60000) = 

    // create new Date object for different city
    // using supplied offset
    var nd = new Date(utc + (3600000*offset)); // (1시간 * 시차)

    // return time as a string
    document.getElementById("time").html = city +" is "+ nd.toLocaleString();
}

setInterval(calcTime('NewYork', '-4'),1000);
</script>
<span id="time"></span>

</body>
</html>