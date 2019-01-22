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
<span class="w3-center"><h2>자료실</h2></span>
<table class="w3-table-all">
<tr>
	<td>글번호</td>
	<td>${no }</td>
	<td>조회수</td>
	<td>${article.readcount }</td>
</tr>
<tr>
	<td>작성자</td>
	<td>${article.writer }</td>
	<td>작성일</td>
	<td>${article.reg_date }</td>
</tr>
<tr>
	<td>글제목</td>
	<td colspan="3">${article.subject }</td>
</tr>
<tr>
	<td>이미지</td>
	<td colspan="3"> <img src="<%=request.getContextPath() %>/fileSave/${article.filename }"/></td>
</tr>
<tr height="30">
	<td>글내용</td>
	<td colspan="3">${article.content }</td>
</tr>

<tr>
	
	<td class="w3-center" colspan="4">
	<input type="button" value="글수정"  OnClick = "location.href='<%=request.getContextPath() %>/board2/update?num=${article.num }&pageNum=${article.num }'">
	<input type="button" value="글삭제"  OnClick = "location.href='<%=request.getContextPath() %>/board2/delete?num=${article.num }&pageNum=${article.num }'">
	<input type="button" value="답글쓰기" OnClick = "location.href='<%=request.getContextPath() %>/board2/write?num=${article.num }&ref=${article.ref }&re_step=${article.re_step }&re_level=${article.re_level }'">
	<input type="button" value="글목록" OnClick = "location.href='<%=request.getContextPath() %>/board2/list?num=${article.num }'">
	</td>
</tr>

</table>
</div>
</body>
</html>