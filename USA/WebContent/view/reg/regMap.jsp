<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/regstyle.css">
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<title>RegMap</title>
</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<div class="body">
<div class="reg_mapframe">
<div class="reg_maptop">§ 미국 지도 §</div>
<a href="regMain.do?regname=뉴욕"><img alt="미국지도" src="${ pageContext.request.contextPath }/upload/미국지도.jpg" class="reg_usamapimg"></a>
<div class="reg_maptitle"><marquee width="300" direction="right" behavior="alternate">보고싶은 지역을 눌러주세요.</marquee></div>

</div>
</div>
<jsp:include page="/view/main/footer.html" />
</body>
</html>
