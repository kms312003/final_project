<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${x==1 }">
<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath() %>/board_opinion/list">
</c:if>

<c:if test="${x==0 }">
<script>
alert("��й�ȣ�� ���� �ʽ��ϴ�.");
history.go(-1);
</script>
</c:if>

<c:if test="${x==-1 }">
<script>
alert("��ȣ�� ���� �ʽ��ϴ�.");
history.go(-1);
</script>
</c:if>