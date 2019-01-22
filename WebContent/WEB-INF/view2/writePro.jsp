<%@page import="board2.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id="article" class="board2.BoardDataBean">
	<jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%
	response.sendRedirect(request.getContextPath()+"/board2/list");
%>