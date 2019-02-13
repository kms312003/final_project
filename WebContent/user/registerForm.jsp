<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>회원가입 페이지</title>

<style>
input[type=email] {
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

input[type=name] {
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

input[type=text] {
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
		var form = document.registerForm;
		if (!form.email.value) {
			alert("이메일을 입력해주세요.");
			return false;
		}
		if (!form.name.value) {
			alert("이름을 입력해주세요.");
			return false;
		}
		if (!form.password.value) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}

		if (!form.birth.value) {
			alert("생년월일을 입력해주세요.");
			return false;
		}
		return true;
	}

	var check = function() {
		if (document.getElementById('inputPassword').value == document
				.getElementById('confirmPassword').value) {
			document.getElementById('message').style.color = 'green';
			document.getElementById('message').innerHTML = 'matching';
		} else {
			document.getElementById('message').style.color = 'red';
			document.getElementById('message').innerHTML = 'not matching';
		}
	}
</script>
<body>

	<div class="container" style="margin-top: 10px; margin-bottom: 10px;">
		<center>
			<br> <br>
			<h1>회원 가입</h1>
		</center>
		<form id="Register" name="registerForm" method="post"
			action="<%=request.getContextPath()%>/main/user/register"
			onsubmit="return checkboard()">
			<table class="table table-bordered">

				<center>
					<p>
						<input type="email" id="inputEmail" name="email" placeholder="이메일">
					</p>
				</center>

				<center>
					<p>
						<input type="name" id="inputname" name="name" placeholder="이름">
					</p>
				</center>

				<center>
					<p>
						<input type="password" id="inputPassword" name="password"
							placeholder="비밀번호" onkeyup='check();'>
					</p>
				</center>


				<center>
					<p>
						<input type="password" id="confirmPassword"
							name="confirm_password" placeholder="비밀번호 확인 " onkeyup='check();'>
					<p id='message'></p>
					</p>
				</center>

				<center>
					<p>
						<input type=radio id="inputGender" name="gender" value="MALE">남자
						<input type=radio id="inputGender" name="gender" value="FEMALE">여자
					</p>
				</center>

				<center>
					<p>
						<input type="text" id="inputBirth" name="birth"
							placeholder="생년월일(ex:YYMMDD)">
					</p>
				</center>

				<center>
					<p>
						<input type=radio id="inputJob" name="job" value="STUDENT">학생
						<input type=radio id="inputJob" name="job" value="EMPLOYED">직장인
						<input type=radio id="inputJob" name="job" value="UNEMPLOYED">무직
						<input type=radio id="inputJob" name="job" value="ETC">기타
					</p>
				</center>

				<center>
					<p>
						<button style="margin: 20px; width: 200px;"
							class="btn btn-primary" type="submit">회원 가입</button>
					</p>
				</center>
			</table>
		</form>
	</div>


</body>
</html>