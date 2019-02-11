<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>회원정보</title>
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
<body>
	<div class="col-sm-9 text-left">
		<center>
			<div class="w3-container">
				<h2>User 정보</h2>
				<table class="table table-bordered" style="width: 80%;">
					<tr>
						<td>이메일</td>
						<td>${user.email }</td>
					</tr>

					<tr>
						<td>이름</td>
						<td>${user.name }</td>
					</tr>

					<tr>
						<td>성별</td>
						<c:if test="${user.gender=='MALE' }">
							<td>남자</td>
						</c:if>

						<c:if test="${user.gender=='FEMALE' }">
							<td>여자</td>
						</c:if>

					</tr>

					<tr>
						<td>생년월일</td>
						<td>${user.birth }</td>
					</tr>

					<tr>
						<td>직업</td>
						<c:if test="${user.job=='STUDENT' }">
							<td>학생</td>
						</c:if>

						<c:if test="${user.job=='EMPLOYED' }">
							<td>직장인</td>
						</c:if>

						<c:if test="${user.job=='UNEMPLOYED' }">
							<td>무직</td>
						</c:if>

						<c:if test="${user.job=='ETC' }">
							<td>기타</td>
						</c:if>
					</tr>
						<tr>
							<td colspan="2"><input type="button" value="회원정보 변경" class="btn btn-default"
								Onclick="window.location='<%=request.getContextPath()%>/main/user/update'">
								<input type="button" value="회원탈퇴" class="btn btn-default"
								Onclick="window.location='<%=request.getContextPath()%>/main/user/delete'">
							</td>
						</tr>
				</table>
			</div>
		</center>
	</div>
</body>
</html>