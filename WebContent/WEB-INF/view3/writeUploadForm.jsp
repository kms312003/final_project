<%@page import="board3.BoardDataBean"%>
<%@page import="board3.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>회원가입 입력 폼</title>
</head>


<script>
function checkboard(){
	var form = document.writeform;
	if(!form.writer.value){
		alert("writer error")
		return false;
	}
	if(!form.subject.value){
		alert("subject error")
		return false;
	}
	if(!form.email.value){
		alert("email error")
		return false;
	}
	if(!form.content.value){
		alert("content error")
		return false;
	}
	if(!form.passwd.value){
		alert("password error")
		return false;
	}
	return true;
}

</script>
<body>
<form method="post" name="writeform" enctype="multipart/form-data" action="<%=request.getContextPath() %>/board3/write"
onsubmit="return checkboard()">
<input type="hidden" name="num" value='${num }'>
<input type="hidden" name="ref" value='${ref }'>
<input type="hidden" name="re_step" value='${re_step }'>
<input type="hidden" name="re_level" value='${re_level }'>
<center>
<div class="w3-container">
<h2> 게시판 </h2>
<table class = "w3-table-all" style="width:70%;">
<tr>
   <td>이름</td>
   <td colspan="3"><input type="text" name="writer" size="10"  ></td>
</tr>
<tr>
   <td>제목</td><td>
<c:if test="${num==0 }">
   	<input type="text" name="subject" size="40" maxlength="50" >
</c:if>
<c:if test="${num!=0 }">
    <input type="text" name="subject" size="40" maxlength="50" value="[답변]" >
</c:if>
   </td>
</tr>
<tr>
   <td>Email</td>
   <td><input type="text" name="email" size="40" maxlength="50"></td>
</tr>
<tr>
   <td>내용</td>
   <td><textarea name="content" rows = "13" cols="40" ></textarea></td>
</tr>
<tr>
	<td width="70" align="center">File</td>
	<td width="330">
		<input type="file" size="40" maxlength="30" name="uploadfile">
	</td>
<tr>
   <td>비밀번호</td>
   <td><input type="password" name="passwd" size="10"></td>
</tr>
<tr>
   <td colspan="4" align="center" style="padding-left:30%">
   <input type="submit" value="글쓰기">
   <input type="reset" value="다시작성">
   <input type="button" value="목록보기"
   OnClick = "window.location='<%=request.getContextPath() %>/board3/list'">
   </td>
   </tr>
</table>
</div>
</center>
</form>
</body>
</html>