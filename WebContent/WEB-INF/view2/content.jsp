<%@page import="board2.BoardDataBean"%> 
<%@page import="board2.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
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

<div class="w3-container">
<span class="w3-center"><h2>�ڷ��</h2></span>
<table class="w3-table-all">
<tr>
	<td>�۹�ȣ</td>
	<td>${no }</td>
	<td>��ȸ��</td>
	<td>${article.readcount }</td>
</tr>
<tr>
	<td>�ۼ���</td>
	<td>${article.writer }</td>
	<td>�ۼ���</td>
	<td>${article.reg_date }</td>
</tr>
<tr>
	<td>������</td>
	<td colspan="3">${article.subject }</td>
</tr>
<tr>
	<td>�̹���</td>
	<td colspan="3"> <img src="<%=request.getContextPath() %>/fileSave/${article.filename }"/></td>
</tr>
<tr height="30">
	<td>�۳���</td>
	<td colspan="3">${article.content }</td>
</tr>

<tr>
	
	<td class="w3-center" colspan="4">
	<input type="button" value="�ۼ���"  OnClick = "location.href='<%=request.getContextPath() %>/board2/update?num=${article.num }&pageNum=${article.num }'">
	<input type="button" value="�ۻ���"  OnClick = "location.href='<%=request.getContextPath() %>/board2/delete?num=${article.num }&pageNum=${article.num }'">
	<input type="button" value="��۾���" OnClick = "location.href='<%=request.getContextPath() %>/board2/write?num=${article.num }&ref=${article.ref }&re_step=${article.re_step }&re_level=${article.re_level }'">
	<input type="button" value="�۸��" OnClick = "location.href='<%=request.getContextPath() %>/board2/list?num=${article.num }'">
	</td>
</tr>

</table>
</div>
</body>
</html>