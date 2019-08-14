<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="회원정보를 입력하여 회원가입하는 페이지">
<title>회원가입</title>

<link rel="stylesheet" href="${contextPath }/css/memWrite.css">

</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<div id="memWrite">

<!-- 회원가입 폼 -->
<!-- 파일 업로드를 하기 위해 post방식과 multipart/form-data를 이용 -->
<!-- onsubmit란 submit을 하면 action 주소로 이동하기전에 check() 함수로 이동 -->
<form action="${contextPath }/memWritePro.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<div class="subject"><label for="mem_id">아이디</label></div>
	<div class="inputBack" id="idDiv">
		<!-- 포커스 벗어나면(onfocusout) 아이디 유효성 검사 함수(idValidate()) 실행 -->
		<!-- 포커스 중일때(onfocusin) 디자인 변경 함수(basic()) 실행 -->
		<input type="text" name="mem_id" class="inputReal" id="mem_id" onfocusout="idValidate()" onfocusin="basic('idDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<!-- 유효성 검사 결과값 출력(outmsg) -->
	<span class="outmsg" id="outIdMsg"></span>

	<div class="subject"><label for="mem_pwd">비밀번호</label></div>
	<div class="inputBack" id="pwdDiv">
		<input type="password" name="mem_pwd" class="inputReal" id="mem_pwd" onfocusout="pwdValidate()" onfocusin="basic('pwdDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outPwdMsg"></span>

	<div class="subject"><label for="mem_pwdChk">비밀번호 재확인</label></div>
	<div class="inputBack" id="pwdChkDiv" >
		<input type="password" name="mem_pwd" class="inputReal" id="mem_pwdChk" onfocusout="pwdChkValidate()" onfocusin="basic('pwdChkDiv')">
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outPwdChkMsg"></span>

	<div class="subject"><label for="mem_name">이름</label></div>
	<div class="inputBack" id="nameDiv">
		<input type="text" name="mem_name" class="inputReal" id="mem_name" maxlength="39" onfocusout="basicValidate(this.id, 'nameDiv', 'outNameMsg')" onfocusin="basic('nameDiv')" >
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outNameMsg"></span>

	<div class="subject"><label for="mem_ph">전화번호</label></div>
	<div class="inputBack" id="phDiv">
		<input type="tel" name="mem_ph" class="inputReal" id="mem_ph" maxlength="32" onfocusout="basicValidate(this.id,'phDiv', 'outPhMsg')" onfocusin="basic('phDiv')" >
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outPhMsg"></span>

	<div class="subject"><label for="mem_addr">주소</label></div>
	<div class="inputBack" id="addrDiv">
		<input type="text" name="mem_addr" class="inputReal" id="mem_addr" maxlength="66" onfocusout="basicValidate(this.id,'addrDiv', 'outAddrMsg')" onfocusin="basic('addrDiv')" >
		<div class="imgDiv"><img src="${contextPath }/view/mem/upload/test.svg"></div>
	</div>
	<span class="outmsg" id="outAddrMsg"></span>

	<div class="subject"><label for="mem_id">파일</label></div>
	<input type="file" name="mem_filename" id="mem_filename"><br>

	<input type="submit" value="회원가입" >
</form>

</div>
<jsp:include page="/view/main/footer.html" />
</body>

<script type="text/javascript" src="${contextPath }/view/mem/js/memWrite.js"></script>

</html>