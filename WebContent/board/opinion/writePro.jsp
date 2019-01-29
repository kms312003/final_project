
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>

<%
	request.setCharacterEncoding("euc-kr");
%>

<jsp:useBean id="article" class="board_opinion.BoardDataBean">
	<jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%
	response.sendRedirect(request.getContextPath()+"/board_opinion/list");
%>