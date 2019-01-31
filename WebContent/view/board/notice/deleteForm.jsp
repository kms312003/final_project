<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    
    
<%
	String pageNum = request.getParameter("pageNum");
	int num = Integer.parseInt(request.getParameter("num"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container">
	<b>글삭제</b><br>
	<form method="post" name="delForm"
	action="<%=request.getContextPath() %>/board_notice/delete">
	<input type="hidden" name="num" value="${article.num}">
	<input type="hidden" name="pageNum" value="<%=pageNum %>">
	<table class="table-bordered" width="360">
		<tr height="30">
			<td align=center><b>비밀번호를 입력해주세요.</b></td>
		</tr>
		<tr height="30">
			<td align=center>비밀번호:
			<input type="password" name="passwd" size="8" maxlength="12">
			</td>
		</tr>
		<tr height="30">
			<td align=center>
				<input type="submit" value="글삭제">
				<input type="button" value="글목록">
			</td>
		</tr>
	</table>
	</form>

</div>


</body>
</html>