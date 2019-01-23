
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <% request.setCharacterEncoding("euc-kr"); %>
 
<%
	String pageNum = request.getParameter("pageNum");
	String passwd = request.getParameter("passwd");
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDBBean dbPro = BoardDBBean.getInstance();
	int x = dbPro.deleteArticle(num,passwd);
%> --%>

<c:if test="${x==1 }">
<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath() %>/board_qa/list">
</c:if>

<c:if test="${x==0 }">
<script>
alert("비밀번호가 맞지 않습니다.");
history.go(-1);
</script>
</c:if>

<c:if test="${x==-1 }">
<script>
alert("번호가 맞지 않습니다.");
history.go(-1);
</script>
</c:if>