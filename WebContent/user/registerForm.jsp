<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>ȸ������ ������</title>
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

.register-form .form-control {
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

.register-form .form-group {
	margin-bottom: 10px;
}

.register-form {
	text-align: center;
}

.forgot a {
	color: #777777;
	font-size: 14px;
	text-decoration: underline;
}

.register-form  .btn.btn-primary {
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

.register-form .btn.btn-primary.reset {
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
		var form = document.registerForm;
		if (!form.email.value) {
			alert("�̸����� �����ּ���.");
			return false;
		}
		if (!form.name.value) {
			alert("name error");
			return false;
		}
		if (!form.password.value) {
			alert("��й�ȣ�� �Է����ּ���.");
			return false;
		}
		
		if (!form.birth.value){
			alert("��������� �Է����ּ���.");
			return false;
		}
		return true;
	}
	
	var check = function(){
		if(document.getElementById('inputPassword').value==document.getElementById('confirmPassword').value){
			document.getElementById('message').style.color = 'green';
			document.getElementById('message').innerHTML ='matching';
		}else{
			document.getElementById('message').style.color = 'red';
			document.getElementById('message').innerHTML ='not matching';
		}
	}
</script>
<body>
	<div class="container">
		<h1 class="form-heading">register Form</h1>
		<div class="register-form">
			<div class="main-div">
				<div class="panel">
					<h2>Register</h2>
					
				</div>
				<form id="Register" name="registerForm" method="post" action="<%=request.getContextPath()%>/user/register" onsubmit="return checkboard()">
		
					<div class="form-group">


					<div class="form-group">


						<input type="email" class="form-control" id="inputEmail" name="email" placeholder="�̸���">

					</div>
					
					<div class="form-group">

						<input type="name" class="form-control" id="inputname" name="name" placeholder="�̸�">

					</div>
					
					<div class="form-group">

						<input type="password" class="form-control" id="inputPassword" name="password" placeholder="��й�ȣ" onkeyup='check();'>
	
					</div>
					
					<!-- confirm password -->
					<div class="form-group">

						<input type="password" class="form-control" id="confirmPassword" name="confirm_password" placeholder="��й�ȣ Ȯ��" onkeyup='check();'>
						<span id='message'></span>
					</div>
					
					<div class="form-group">

						<input type=radio id="inputGender" name="gender" value="MALE">����
						<input type=radio id="inputGender" name="gender" value="FEMALE">����

					</div>
					
					<div class="form-group">

						<input type="text" class="form-control" id="inputBirth" name="birth" placeholder="�������">

					</div>
					<div class="form-group">

						<input type=radio id="inputJob" name="job" value="STUDENT">�л�
						<input type=radio id="inputJob" name="job" value="EMPLOYED">������
						<input type=radio id="inputJob" name="job" value="UNEMPLOYED">����
						<input type=radio id="inputJob" name="job" value="ETC">��Ÿ

					</div>
					
					<div>
						<button type="submit" class="registerbtn">Register</button>
						<c:if test=""></c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>
</body>
</html>