<%@page import="board3.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id="article" class="board3.BoardDataBean">
	<jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%
	response.sendRedirect(request.getContextPath()+"/board3/list");
%>