<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>로그인페이지</title>
<style>
button {
	background-color: #4d90fe;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
	color: white;
}

.registerbtn {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.registerbtn:hover {
	opacity: 1;
}

.form-heading {
	color: #fff;
	font-size: 23px;
}

.panel h2 {
	color: #444444;
	font-size: 18px;
	margin: 0 0 8px 0;
}

.panel p {
	color: #777777;
	font-size: 14px;
	margin-bottom: 30px;
	line-height: 24px;
}

.login-form .form-control {
	background: #f7f7f7 none repeat scroll 0 0;
	border: 1px solid #d4d4d4;
	border-radius: 4px;
	font-size: 14px;
	height: 50px;
	line-height: 50px;
}

.main-div {
	background: #ffffff none repeat scroll 0 0;
	border-radius: 2px;
	margin: 10px auto 30px;
	max-width: 38%;
	padding: 50px 70px 70px 71px;
}

.login-form .form-group {
	margin-bottom: 10px;
}

.login-form {
	text-align: center;
}

.forgot a {
	color: #777777;
	font-size: 14px;
	text-decoration: underline;
}

.login-form  .btn.btn-primary {
	background: #f0ad4e none repeat scroll 0 0;
	border-color: #f0ad4e;
	color: #ffffff;
	font-size: 14px;
	width: 100%;
	height: 50px;
	line-height: 50px;
	padding: 0;
}

.forgot {
	text-align: left;
	margin-bottom: 30px;
}

.botto-text {
	color: #ffffff;
	font-size: 14px;
	margin: auto;
}

.login-form .btn.btn-primary.reset {
	background: #ff9900 none repeat scroll 0 0;
}

.back {
	text-align: left;
	margin-top: 10px;
}

.back a {
	color: #444444;
	font-size: 13px;
	text-decoration: none;
}
</style>
</head>
<script>
	function checkboard() {
		var form = document.loginForm;
		if (!form.email.value) {
			alert("email error");
			return false;
		}
		if (!form.psw.value) {
			alert("password error");
			return false;
		}
		return true;
	}
</script>
<body>
	<div class="container">
		<h1 class="form-heading">login Form</h1>
		<div class="login-form">
			<div class="main-div">
				<div class="panel">
					<h2>Login to Your Account</h2>
					<p>Please enter your UserID and password</p>
				</div>
				<form id="Login" name="loginForm" action="<%=request.getContextPath()%>/user/login" method="post" onsubmit="return checkboard()">
			
					<div class="form-group">
							<input type="email" class="form-control" id="inputemail" name="email" placeholder="User Email">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
					</div>
					
					<button type="submit">Login</button>
					<div>
						<button type="button" class="registerbtn"
							Onclick="window.location='<%=request.getContextPath()%>/user/register'">Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>
</body>
</html>