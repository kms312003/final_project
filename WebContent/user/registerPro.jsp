<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${check==1 }">
	<script>
		alert("ȸ�����Կ� �����߽��ϴ�.")
	</script>
	<meta http-equiv="Refresh"
		content="0;url=<%=request.getContextPath()%>/user/login">
</c:if>

<c:if test="${check==0 }">
	<script>
		alert("�ߺ��Ǵ� �̸����� �����մϴ�.");
		history.go(-1);
	</script>
</c:if>
