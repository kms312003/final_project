
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1 }">
<script>
	alert("�α��ο� �����߽��ϴ�.");
</script>
<meta http-equiv="Refresh" content="0;url=<%= request.getContextPath() %>/view/main.jsp">
</c:if>

<c:if test="${check==0 }">
<script>
	alert("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
	history.go(-1);
</script>
</c:if>

<c:if test="${check==-1 }">
<script>
	alert("���̵� �����ϴ�.");
	history.go(-1);
</script>
</c:if>