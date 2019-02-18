<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴 페이지</title>
<style>
input[type=password] {
	width: 250px;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	background-color: white;
	background-position: 10px 10px;
	background-repeat: no-repeat;
	padding: 4px 4px 4px 4px;
}
</style>
</head>
<script>
	function checkboard() {
		var form = document.deleteForm;
		if (!form.password.value) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		return true;
	}
</script>
<body>
<div class="container" style="margin-top: 10px; margin-bottom: 10px;">
	<center>
		<br><br>
		<h2>회원 탈퇴</h2>
	
	<form id="Delete" name="deleteForm" method="post" action="<%=request.getContextPath()%>/main/user/delete" onsubmit="return checkboard()">
	<table class="table table-bordered">
		<p><input type="password" id="inputPassword" name="password"
							placeholder="비밀번호를 입력해주세요." onkeyup='check();'></p>
		
		<p>
		<input type="submit" value="탈퇴" class="btn btn-primary" />
		<input type="button" value="취소" class="btn btn-default" Onclick="window.location='<%=request.getContextPath()%>/main/user/userInfo'">
							
		</p>
	</table>
	</form>
	</center>
</div>

</body>
</html>