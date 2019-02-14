<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>


<jsp:useBean id="article" class="board_information.BoardDataBean">
	<jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%
	response.sendRedirect(request.getContextPath()+"/information/list");
%>