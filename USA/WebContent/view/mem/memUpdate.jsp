<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<link rel="stylesheet" href="${contextPath }/view/mem/css/memUpdate.css">
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	function updateForm(me, id) {
		$("#" + me.id).hide();
		$("#" + id).show();
	}

	function updateForm2(me, btn) {
		$("#" + me.parentNode.id).hide();
		$("#" + btn).show();
	}
	
	function update(id) {
		var mem_pwd = prompt("회원수정을 위하여 비밀번호를 입력하세요");
		var mem_id = $("#mem_id").val();
		var mem_value = $("#" + id).val();
		
		$.ajax({
			type : "get",
			dataType : "text",
			url : "../../memUpdatePro.do?mem_id=" + mem_id + "&mem_pwd=" + mem_pwd + "&colc=" + id + "&mem_value=" + mem_value,
			success : function(data) {
				var test = $(data).find("result").each(function(){
				alert(test);
					
				});
			},
			err : function(err) {
				alert("회원수정 중에 오류가 발생했습니다");
			}
		});
		
	}
</script>

<div id="memUpdate">

<%-- <form action="${contextPath }/memUpdatePro.do" method="post" enctype="multipart/form-data"> --%>
<%-- <input type="hidden" id="mem_id" value="${member }"> --%>
<input type="hidden" id="mem_id" value="sejong">
<table>
	<tr>
		<th><div class="inputName">사용자 이름</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData">김세종</p>
			<p class="explanation">실명 정보(이름, 생년월일, 성별, 개인 고유 식별 정보)가 변경된 경우 본인 확인을 통해 정보를 수정하실 수 있습니다.</p>
			<p><input type="button" class="btn" id="nameBtn" value="수정" onclick="updateForm(this,'nameInput')"></p>
			<div class="inputNone" id="nameInput">
				<h4>변경할 이름</h4>
				<input type="text" class="update" name="mem_name" id="mem_name" value="${vo.mem_name }"><br>
				<input type="button" class="btn" value="수정취소" onclick="updateForm2(this,'nameBtn')"><input type="button" class="btnOk" value="수정완료" onclick="update('mem_name')">
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">전화번호</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData">010-1111-1111</p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			<p><input type="button" class="btn" value="수정" onclick="updateForm('phInput')"></p>
			<p class="inputNone" id="phInput"><input type="text" name="mem_ph" value="${vo.mem_ph }"></p>
			</div>
		</td>
	</tr>
	
	<tr>
		<th><div class="inputName">주소</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData">010-1111-1111</p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			<p><input type="button" class="btn" value="수정" onclick="updateForm('addrInput')"></p>
			<p class="inputNone" id="addrInput"><input type="text" name="mem_addr" value="${vo.mem_addr }"></p>
			</div>
		</td>
	</tr>
	<tr>
		<th><div class="inputName">포인트</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData">${vo.mem_point }121212</p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			</div>
		</td>
	</tr>
	<%-- 
	<tr>
		<th><div class="inputName">파일명</div></th>
		<td>
			<div class="inputDiv">
			<p class="inputData"><img alt="프로필 사진" src="${contextPath }/${vo.mem_filename }"></p>
			<p class="explanation">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 사이트로부터 알림을 받을 때 사용할 휴대전화입니다.</p>
			</div>
		</td>
	</tr>

 --%>
 	</table>
	<input type="submit" value="수정">
	<input type="button" value="회원탈퇴" onclick="window.location.href='${contextPath}/memDelPro.do'">
<!-- </form> -->

</div>

</body>
</html>