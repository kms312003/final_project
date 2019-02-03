<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<title>Main Head</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"></head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
 <!-- datepicker 필요 --> 
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />    
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
  <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
  <script src="/final_project/js/datepicker.js"></script>
  
<style>
	.navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
</style>
</head>
<body>
<script>
	window.onload = function() {
		var atag = document.getElementsByTagName("a");
		var litag = document.getElementsByTagName("li");
		console.log(atag);
		var url = document.location.href.split("/");
		console.log(url);
		console.log(url[url.length-2]);
		console.log(document.location.href);
		if(url[url.length-2] == "main") {
			litag[0].className += " active";
		} else if(url[url.length-2] == "user") {
			litag[1].className += " active";
		} else if(url[url.length-2] == "room") {
			litag[2].className += " active";
		} else if(url[url.length-2] == "board"){
			litag[3].className += " active";
		}
	}
</script>
</head>

<!--  최상단 홈페이지 이동   -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Admin Page</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <%-- <ul class="nav navbar-nav">
      	<li><a href="<%= request.getContextPath() %>/admin/computer/list">Home</a></li>
        <li>
	        <c:if test="${type eq 'HOST'}">
	        	<a href="<%= request.getContextPath() %>/houseModel2/list">My page</a>
	        </c:if> 
	        <c:if test="${type eq 'USER'}">
	        	<a href="<%= request.getContextPath() %>/userModel2/guestList">My page</a>
	        </c:if>
        </li>
        
        <li><a href="<%= request.getContextPath() %>/houseModel2/room">Room</a></li>
        <li><a href="<%= request.getContextPath() %>/boardModel2/list?boardid=${ boardid }">Questions</a></li>
      </ul> --%>
      <ul class="nav navbar-nav navbar-right">
      	<li style="color:white; padding:15px;">${ userId }(${ type })님 환영합니다.</li>
      	<li><a href="<%= request.getContextPath() %>/userModel2/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul> 
    </div>
  </div>
</nav>

<body>
<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
			<%-- <div class="btn-group-vertical">
			    <button style="width:200px;" type="button" class="btn btn-default" onclick="window.location='<%= request.getContextPath() %>/admin/cpu/write'">Apple</button>
			    <button type="button" class="btn btn-primary">Samsung</button>
			    <button type="button" class="btn btn-primary">Sony</button>
			</div> --%>
				<p><a href="<%=request.getContextPath()%>/admin/user/list">User</a></p>
				<p><a href="<%=request.getContextPath()%>/admin/computer/list">Computer</a></p>
				<p><a href="<%=request.getContextPath()%>/admin/cpu/list">Cpu</a></p>
				<p><a href="<%=request.getContextPath()%>/admin/mainboard/list">메인보드</a></p>
				<p><a href="<%=request.getContextPath()%>/admin/ram/list">메모리(Ram)</a></p>
				<p><a href="<%=request.getContextPath()%>/admin/graphic/list">그래픽카드</a></p>
				<p><a href="<%=request.getContextPath()%>/admin/hdd/list">하드디스크(HDD)</a></p>
				<p><a href="<%=request.getContextPath()%>/admin/ssd/list">SSD</a></p>
				<p><a href="<%=request.getContextPath()%>/admin/power/list">파워</a></p>
				<p><a href="<%=request.getContextPath()%>/board_notice/list">공지사항/이벤트</a></p>
				<p><a href="<%=request.getContextPath()%>/board_information/list">정보자료실</a></p>
				<p><a href="<%=request.getContextPath()%>/board_opinion/list">구매후기</a>	</p>
				<p><a href="<%=request.getContextPath()%>/board_qa/list">견적문의</a></p>
			</div>
</body>