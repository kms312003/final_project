<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>회원탈퇴 페이지</title>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

table {
	margin-left: auto;
	margin-right: auto;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 700px
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
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

	<div class="col-sm-9 text-left">
		<center>
			<form id="Delete" name="deleteForm" method="post" action="<%=request.getContextPath()%>/main/user/delete" onsubmit="return checkboard()">
				<div class="w3-container">
					<h2>User 탈퇴</h2>
					<table class="table table-bordered" style="width: 80%;">
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>

						<tr>
							<td colspan="2">
							<input type="button" value="취소" class="btn btn-default" Onclick="window.location='<%=request.getContextPath()%>/main/user/userInfo'">
							<input type="submit" value="탈퇴" class="btn btn-default" /></td>
						</tr>
					</table>
				</div>
			</form>
		</center>
	</div>
</body>
</html>