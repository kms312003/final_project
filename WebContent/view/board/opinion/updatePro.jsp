<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${x==1 }">
<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath() %>/opinion/list">
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