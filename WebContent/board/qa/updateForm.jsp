<%@page import="board_qa.BoardDataBean"%>

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

<form method="post" name="writeform" action="<%=request.getContextPath() %>/board_qa/update"
onsubmit="return checkboard()">
<input type="hidden" name="num" value="${article.num}">
<%-- <input type="hidden" name="pageNum" value="<%=pageNum%>"> --%>


<div class="w3-center w3-container">
<h2> �Խ��� </h2>
<table class = "w3-table-all" style="width:70%;">
<tr>
	<td>����</td>
	<td colspan="3">
	<select name="price" id="price">
	<option value="20~30������">20~30������</option>
	<option value="40~50������">40~50������</option>
	<option value="60~70������">60~70������</option>
	<option value="80~90������">80~90������</option>
	<option value="100���� �̻�">100���� �̻�</option>
	</select>
</tr>

<tr>
	<td>�뵵</td>
	<td colspan="3">
	<select name="purpose" id="purpose">
	<option value="���ͳ�/�繫��PC">���ͳ�/�繫��PC</option>
	<option value="�¶��ΰ��ӿ�">�¶��ΰ��ӿ�</option>
	<option value="�׷����۾��� ">�׷����۾��� </option>
	<option value="����/��ȭ�����">����/��ȭ�����</option>
	<option value="��Ÿ">��Ÿ</option>
	
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
   OnClick = "window.location='<%=request.getContextPath() %>/board_qa/list'">
   </td>
   </tr>
</table>
</div>

</form>
</body>
</html>