<%-- <%@page import="board.BoardDataBean"%>
<%@page import="board.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
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
    .row.content {height: 700px}
    
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
      .row.content {height:auto;} 
    }
  </style>
</head>

	<%
    	String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardDataBean article = dbPro.getArticle(num);
	%>
	
<body>

<div class="container-fluid text-center">
	<div class="row content">
	
	<div class="col-sm-2 sidenav">
		<p><a href="#">Q&A</a></p>
		<p><a href="#">공지사항</a></p>
	</div>
	
	<div class="col-sm-8 text-left"> 
		<div class="w3-container">
		<p class="w3-center"><h2>글내용</h2></p>
		<table class="w3-table-all">
			<tr>
				<td>글번호</td>
				<td><%= article.getNum() %></td>
				<td>조회수</td>
				<td><%= article.getReadcount() %></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%= article.getWriter() %></td>
				<td>작성일</td>
				<td><%= article.getReg_date() %></td>
			</tr>
			<tr>
				<td>글제목</td>
				<td colspan="3"><%= article.getSubject() %></td>
			</tr>
			<tr>
				<td>글내용</td>
				<td colspan="4"><%= article.getContent() %></td>
			</tr>
			<tr>
				<td class="w3-center" colspan="4">
					<input type="button" value="글수정" onclick="window.location='updateForm.jsp?num=<%= article.getNum() %>'">
					<input type="button" value="글삭제" onclick="window.location='deleteForm.jsp?num=<%= article.getNum() %>'">
					<input type="button" value="답글쓰기" onclick="document.location.href='writeForm.jsp?num=<%= article.getNum() %>&ref=<%= article.getRef() %>&re_step=<%= article.getRe_step() %>&re_level=<%= article.getRe_level() %>'">
					<input type="button" value="글목록" onclick="window.location='<%= request.getContextPath() %>/board/list.jsp?boardid=<%= boardid %>'">
				</td>
			</tr>
		</table>
		</div>
	</div>
    
    <!-- <div class="col-sm-2 sidenav">
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div> -->
    
  </div>
</div>

</body>
<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>
</html>
 --%>