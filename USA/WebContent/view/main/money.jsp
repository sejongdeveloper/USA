<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="/USA/css/style.css">
</head>
<body>
<div class="exchangeframe">
<div class="title">환율 정보</div>
<div class="country">국가</div>
<div class="exchangeRate">금액</div>
<div class="unit">단위</div>
</div>
</body>
<script type="text/javascript">
		var index = 0;
		
		$(document).ready(function(){
			slideExchage();
		});
	   
	   function slideExchage() {

	      var url = "./money.do";
		  
	      $.ajax({
	         type : "get",
	         async : false,
	         
	         url: url,
	         success : function(data) {
	            var info = $.parseJSON(data);
	            
	            $(".country").html(info[index].cur_nm);
	            $(".exchangeRate").html(info[index].bkpr);
	            $(".unit").html(info[index].cur_unit);
	            
	            index++;
	            
				if (index > data.length) {
					index = 0;
				}
				
				}
			});
		      	
			setTimeout(slideExchage, 2000);
		}
</script>
</html>