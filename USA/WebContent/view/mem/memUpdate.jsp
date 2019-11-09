<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="sejongDeveloper">
<meta name="description" content="회원정보를 수정하는 페이지">
<title>내정보</title>

<link rel="stylesheet" href="${contextPath }/css/memUpdate.css">

</head>
<body>
<jsp:include page="/view/main/header.jsp" />
<div id="memUpdate">

<table>
	<tr>
		<th><div class="inputName">사용자 이름</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData" id="calcmem_name">${vo.mem_name }</p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우  사용할 사용자 이름입니다.</p>
			<!-- 클릭하면(onclick) 클릭한 버튼은 사라지고 해당 div를 보여주는 updateForm() 함수 실행 -->
			<p><input type="button" class="btn" id="nameBtn" value="수정" onclick="updateForm(this,'nameInput')"></p>
			<div class="inputNone" id="nameInput">
				<h4>변경할 이름</h4>
				<input type="text" class="update" name="mem_name" id="mem_name" value="${vo.mem_name }"><br>
				<!-- 클릭하면(onclick) 클릭한 부모의 div는 사라지고 전달받은 버튼을 보여주는 updateFormCancel() 함수 실행 -->
				<input type="button" class="btn" value="수정취소" onclick="updateFormCancel(this,'nameBtn')">
				<!-- 클릭하면(onclick) 클릭한 부모의 div는 사라지고 전달받은 버튼을 보여주고 ajax를 통하여 DB를 수정하는 update() 함수 실행 -->
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
			<p class="explanation">비밀번호 찾기 등 본인확인이 필요한 경우  사용할 휴대전화입니다.</p>
			<p><input type="button" class="btn" id="phBtn" value="수정" onclick="updateForm(this,'phInput')"></p>
			<div class="inputNone" id="phInput">
				<h4>변경할 전화번호</h4>
				<input type="text" class="update" name="mem_ph" id="mem_ph" value="${vo.mem_ph }"><br>
				<input type="button" class="btn" value="수정취소" onclick="updateFormCancel(this,'phBtn')">
				<input type="button" class="btnOk" value="수정완료" onclick="update(this,'mem_ph','phBtn')">
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">주소</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData" id="calcmem_addr">${vo.mem_addr }</p>
			<p class="explanation">아이디 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 주소입니다.</p>
			<p><input type="button" class="btn" id="addrBtn" value="수정" onclick="updateForm(this,'addrInput')"></p>
			<div class="inputNone" id="addrInput">
				<h4>변경할 주소</h4>
				<input type="text" class="update" name="mem_addr" id="mem_addr" value="${vo.mem_addr }"><br>
				<input type="button" class="btn" value="수정취소" onclick="updateFormCancel(this,'addrBtn')">
				<input type="button" class="btnOk" value="수정완료" onclick="update(this,'mem_addr','addrBtn')">
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">파일명</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData"><img alt="프로필 사진" src="${contextPath }/view/mem/upload/${vo.mem_filename }"></p> 
			<p class="explanation">회원님의 프로필 사진을 변경할 수 있습니다.</p>
			<p><input type="button" class="btn" id="filenameBtn" value="수정" onclick="updateForm(this,'filenameInput')"></p>
			<div class="inputNone" id="filenameInput">
				<h4>변경할 파일명</h4>
				<!-- 전달받은 파일을 전송하기 위해 post방식의 multipart/form-data타입으로 form을 구성 -->
				<form action="${contextPath }/memFilenamePro.do" method="post" id="aaaa" enctype="multipart/form-data">
				<input type="hidden" id="mem_id" value="${member }">
				<input type="file" class="update" name="mem_filename" id="mem_filename" value="${vo.mem_filename }"><br>
				<input type="button" class="btn" value="수정취소" onclick="fileUpdateFormCancel()">
				<input type="submit" class="btnOk" value="수정완료">
				</form>
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">비밀번호 변경</div></th>
		<td>
			<div class="inputDiv">
			<p class="explanation">회원의 비밀번호를 변경할 수 있습니다.</p>
			<p><input type="button" class="btn" id="pwdBtn" value="수정" onclick="updateForm(this,'pwdInput')"></p>
			<div class="inputNone" id="pwdInput">
				<h4>현재 비밀번호</h4>
				<input type="password" class="update" name="mem_pwd" id="mem_pwd"><br>
				<input type="button" class="btn" value="수정취소" onclick="updateFormCancel(this,'pwdBtn')">
				<!-- 클릭하면(onclick) ajax를 통하여 아이디와 비밀번호가 일치하는지 확인하는 pwdUpdate() 함수 실행 -->
				<!-- 아이디와 비밀번호기 일치하면 변경하는 페이지로 이동 -->
				<!-- 아이디와 비밀번호기 불일치하면 alert() 실행 -->
				<input type="button" class="btnOk" value="수정하기" onclick="pwdUpdate()">
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">포인트</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData">${vo.mem_point }</p>
			<p class="explanation">이벤트 등 포인트가 필요한 경우  사용할 총포인트입니다.</p>
			</div>
		</td>
	</tr>
	
 	</table>
 	<!-- a링크를 클릭하면(onclock) del() 함수 실행 -->
 	<!-- confirm함수를 이용한 결과값(true, false)으로 회원탈퇴 여부를 결정 -->
	<span id="del">사이트를 더 이상 이용하지 않는다면 </span><a id="delLink" onclick="del()">회원탈퇴 바로가기</a>
</div>
<jsp:include page="/view/main/footer.html" />
</body>

<script type="text/javascript" src="${contextPath }/view/mem/js/memUpdate.js"></script>
	
</html>