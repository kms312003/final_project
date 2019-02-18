<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>회원정보</title>
<style>
body{
	margin: 0;
	padding: 0;
}
.box{
	width:450px;
	background:rgba(33,150,243,100);
	padding:40px;
	text-align:center;
	margin:auto;
	margin-top: 5%;
	color:white;
	font-family: 'Century Gothic', sans-serif;
}
.box-img{
	border-radius:50%;
	width:200px;
	height: 200px;
}
.box h1{
	font-size:40px;
	letter-spacing: 4px;
	font-weight: 250;
}
.box h5{
	font-size: 20px;
	letter-spacing: 3px;
	font-weight: 100;
	text-align: justify;
}
.box h6{
	font-size: 15px;
	letter-spacing: 1px;
	font-weight: 100;
	text-align: justify;
}
ul{
margin:0;
padding:0;
}
.box li{
	display:inline-block;
	margin:6px;
	list-style:none;
}
.box li a{
	color:white;
	text-decoration:none;
	font-size:60px;
	transition: all ease-in-out 250ms;
}
.box li a:hover{
	color:#b9b9b9;
}

</style>
</head>
<body>

<!-- Sidebar -->
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:15%">
  <h3 class="w3-bar-item">My page Menu</h3>
  <a href="<%=request.getContextPath()%>/main/user/update" class="w3-bar-item w3-button">회원정보 수정</a>
  <a href="<%=request.getContextPath()%>/main/user/delete" class="w3-bar-item w3-button">회원 탈퇴</a>
  <a href="<%=request.getContextPath()%>/main/user/cart" class="w3-bar-item w3-button">내 장바구니</a>
  <a href="<%=request.getContextPath()%>/main/user/like" class="w3-bar-item w3-button">관심 상품</a>
</div>
<div class="box">
		<img src="" alt="" class="box-img">
	
		<h1>My Page</h1>
		<h5>Email</h5>
		<h6>${user.email }</h6>
		<h5>Name</h5>
		<h6>${user.name }</h6>
		<h5>Gender</h5>
		<c:if test="${user.gender=='MALE' }">
		<h6>Male</h6>
		</c:if>
		<c:if test="${user.gender=='FEMALE' }">
		<h6>Female</h6>
		</c:if>
		
		<h5>Birth</h5>
		<h6>${user.birth}</h6>
		
		<h5>Job</h5>
		<c:if test="${user.job=='STUDENT' }">
		<h6>학생</h6>
		</c:if>
		<c:if test="${user.job=='EMPLOYED' }">
		<h6>직장인</h6>
		</c:if>
		<c:if test="${user.job=='UNEMPLOYED' }">
		<h6>무직</h6>
		</c:if>
		<c:if test="${user.job=='ETC' }">
		<h6>기타</h6>
		</c:if>
		<h5>Address</h5>
		<h6>${user.roadAddress } ${user.detailAddress} ${user.extraAddress }</h6>
		
	</div>


</body>
</html>