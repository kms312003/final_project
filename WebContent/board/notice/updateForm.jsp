<%@page import="board_notice.BoardDataBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<%
	String pageNum = request.getParameter("pageNum");
	int num = Integer.parseInt(request.getParameter("num"));
%>
<%-- <%
BoardDBBean dbPro = BoardDBBean.getInstance();
BoardDataBean article = dbPro.getUpdate(num);
%> --%>

<form method="post" name="writeform" action="<%=request.getContextPath() %>/board_notice/update"
onsubmit="return checkboard()">
<input type="hidden" name="num" value="${article.num}">
<%-- <input type="hidden" name="pageNum" value="<%=pageNum%>"> --%>


<div class="w3-center w3-container">
<h2> �Խ��� </h2>
<table class = "w3-table-all" style="width:70%;">
<tr>
	<td>�з�</td>
	<td colspan="3">
	<select name="inform" id="inform">
	<option value="[��������]">��������</option>
	<option value="[�̺�Ʈ]">�̺�Ʈ</option>
	</select>
</tr>

<tr>
   <td>�̸�</td>
   <td colspan="3"><input type="text" name="writer" size="10" value="${article.writer}"></td>
</tr>
<tr>
   <td>����</td>
   <td><input type="text" name="subject" size="40" maxlength="50" value="${article.subject}"></td>
</tr>
<tr>
   <td>Email</td>
   <td><input type="text" name="email" size="40" maxlength="50" value="${article.email}"></td>
</tr>
<tr>
   <td>����</td>
   <td><textarea name="content" rows = "13" cols="40" >${article.content}</textarea></td>
</tr>
<tr>
   <td>��й�ȣ</td>
   <td><input type="password" name="passwd" size="10" ></td>
</tr>
<tr>
   <td colspan="4" align="center" style="padding-left:30%">
   <input type="submit" value="�۾���">
   <input type="reset" value="�ٽ��ۼ�">
   <input type="button" value="��Ϻ���"
   OnClick = "window.location='<%=request.getContextPath() %>/board_notice/list'">
   </td>
   </tr>
</table>
</div>

</form>
</body>
</html>