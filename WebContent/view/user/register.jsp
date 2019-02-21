<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${check==1 }">
	<script>
		alert("회원가입에 성공했습니다.")
	</script>
	<meta http-equiv="Refresh"
		content="0;url=<%=request.getContextPath()%>/user/login">
</c:if>

<c:if test="${check==0 }">
	<script>
		alert("중복되는 이메일이 존재합니다.");
		history.go(-1);
	</script>
</c:if>
