<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>로그인페이지</title>
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
		var form = document.loginForm;
		if (!form.email.value) {
			alert("이메일을 입력해주세요.");
			return false;
		}
		if (!form.psw.value) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		return true;
	}
</script>
<body>

	<div class="container" style="margin-top: 10px; margin-bottom: 10px;">
		<center>
			<br> <br>
			<h2>Login to Your Account</h2>
			<p>Please enter your Email and password</p>
		</center>
		<form id="Login" name="loginForm"
			action="<%=request.getContextPath()%>/main/user/login" method="post"
			onsubmit="return checkboard()">
			<table class="table table-bordered">
				<center>
					<p>

						<input type="email" class="input_field" id="inputemail"
							name="email" placeholder="Email" size="50">
					</p>
				</center>

				<center>
					<p>
						<input type="password" class="input_field" id="inputPassword"
							name="password" placeholder="Password" size="50">
					</p>
				</center>

				<center>
					<p>
						<button style="margin: 20px; width: 200px;"
							class="btn btn-primary" type="submit">로그인</button>
					</p>
				</center>
			</table>
		</form>
	</div>
</body>
</html>