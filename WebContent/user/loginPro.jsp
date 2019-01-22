
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1 }">
<script>
	alert("로그인에 성공했습니다.");
</script>
<meta http-equiv="Refresh" content="0;url=<%= request.getContextPath() %>/view/main.jsp">
</c:if>

<c:if test="${check==0 }">
<script>
	alert("비밀번호가 틀렸습니다.");
	history.go(-1);
</script>
</c:if>

<c:if test="${check==-1 }">
<script>
	alert("아이디가 없습니다.");
	history.go(-1);
</script>
</c:if>