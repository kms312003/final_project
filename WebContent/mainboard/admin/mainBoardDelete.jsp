<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<script>
	alert("제품이 삭제 되었습니다.");
</script>
<meta http-equiv="Refresh" content="0;url=<%= request.getContextPath() %>/admin/mainBoard/list">