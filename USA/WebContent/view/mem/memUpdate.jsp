<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- 컨텍스트 패스(절대위치) 값 변수에 저장 -->
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="회원정보를 수정하는 페이지">
<title>내정보</title>

<!-- css파일 읽어오기 -->
<link rel="stylesheet" href="${contextPath }/css/memUpdate.css">

</head>
<body>
<!-- jstl include -->
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<div id="memUpdate">

<table>
	<tr>
		<th><div class="inputName">사용자 이름</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData" id="calcmem_name">${vo.mem_name }</p>
			<p class="explanation">실명 정보(이름, 생년월일, 성별, 개인 고유 식별 정보)가 변경된 경우 본인 확인을 통해 정보를 수정하실 수 있습니다.</p>
			<!-- onclick: 클릭할 때 실행 -->
			<p><input type="button" class="btn" id="nameBtn" value="수정" onclick="updateForm(this,'nameInput')"></p>
			<div class="inputNone" id="nameInput">
				<h4>변경할 이름</h4>
				<input type="text" class="update" name="mem_name" id="mem_name" value="${vo.mem_name }"><br>
				<!-- onclick: 클릭할 때 실행 -->
				<input type="button" class="btn" value="수정취소" onclick="updateFormCancel(this,'nameBtn')">
				<input type="button" class="btnOk" value="수정완료" onclick="update(this,'mem_name','nameBtn')">
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">전화번호</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData" id="calcmem_ph">${vo.mem_ph }</p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			<p><input type="button" class="btn" id="phBtn" value="수정" onclick="updateForm(this,'phInput')"></p>
			<div class="inputNone" id="phInput">
				<h4>변경할 전화번호</h4>
				<input type="text" class="update" name="mem_ph" id="mem_ph" value="${vo.mem_ph }"><br>
				<input type="button" class="btn" value="수정취소" onclick="updateFormCancel(this,'phBtn')"><input type="button" class="btnOk" value="수정완료" onclick="update(this,'mem_ph','phBtn')">
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">주소</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData" id="calcmem_addr">${vo.mem_addr }</p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			<p><input type="button" class="btn" id="addrBtn" value="수정" onclick="updateForm(this,'addrInput')"></p>
			<div class="inputNone" id="addrInput">
				<h4>변경할 주소</h4>
				<input type="text" class="update" name="mem_addr" id="mem_addr" value="${vo.mem_addr }"><br>
				<input type="button" class="btn" value="수정취소" onclick="updateFormCancel(this,'addrBtn')"><input type="button" class="btnOk" value="수정완료" onclick="update(this,'mem_addr','addrBtn')">
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">파일명</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData"><img alt="프로필 사진" src="${contextPath }/view/mem/upload/${vo.mem_filename }"></p> 
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			<p><input type="button" class="btn" id="filenameBtn" value="수정" onclick="updateForm(this,'filenameInput')"></p>
			<div class="inputNone" id="filenameInput">
				<h4>변경할 파일명</h4>
				<!-- 파일전송을 위해 post방식과 multipart/form-data 타입을 적용 -->
				<form action="${contextPath }/memFilenamePro.do" method="post" id="aaaa" enctype="multipart/form-data">
				<input type="hidden" id="mem_id" value="${member }">
				<input type="file" class="update" name="mem_filename" id="mem_filename" value="${vo.mem_filename }"><br>
				<input type="button" class="btn" value="수정취소" onclick="fileUpdateFormCancel()"><input type="submit" class="btnOk" value="수정완료">
				</form>
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">비밀번호 변경</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData" id="calcmem_pwd">${vo.mem_addr }</p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			<p><input type="button" class="btn" id="pwdBtn" value="수정" onclick="updateForm(this,'pwdInput')"></p>
			<div class="inputNone" id="pwdInput">
				<h4>변경할 비밀번호</h4>
				<input type="text" class="update" name="mem_pwd" id="mem_pwd"><br>
				<input type="button" class="btn" value="수정취소" onclick="updateFormCancel(this,'pwdBtn')"><input type="button" class="btnOk" value="수정하기" onclick="pwdUpdate()">
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">포인트</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData">${vo.mem_point }</p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			</div>
		</td>
	</tr>
	
 	</table>
	<span id="del">사이트를 더 이상 이용하지 않는다면 </span><a id="delLink" onclick="del()">회원탈퇴 바로가기</a>
</div>

<!-- jstl include -->
<jsp:include page="/view/main/footer.html" />

</body>

<!-- javascript 실행 -->
<script type="text/javascript" src="${contextPath }/view/mem/js/memUpdate.js"></script>
	
</html>