<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="board.BoardDBBean" %>
<%@ page import="board4.BoardDataBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>공지사항</title>
</head>
<body>
<%
%>
   
   <div class="w3-container">
   <span class="w3-center w3-large"> </span>
   <span class="w3-right w3-padding-right-large"> (전체글 : ${count })
   <a href="<%=request.getContextPath() %>/board4/write">글쓰기</a></span>
   
   <c:if test="${count==0 }">
   <table class="table-bordered" width="700">
      <tr class="w3-grey">
         <td align="center">게시판에 저장된 글이 없습니다.</td></tr>
         </table>
   </c:if>
   
   <c:if test="${count!=0 }">
   
   <table class="w3-table-all" width="700">
      <tr class="w3-grey">
         <td align="center" width="50">번호</td>
         <td align="center" width="250">제목</td>
         <td align="center" width="100">작성자</td>
         <td align="center" width="150">작성일</td>
         <td align="center" width="50">조회</td>
         <td align="center" width="100">IP</td>
         </tr>
         <c:set var="number" value="${number}"/> 
         <c:forEach var="article" items="${articleList}">
         <tr>
         	<td align="center" width="50">${number} </td>
         	<c:set var="number" value="${number-1}"/> 
         <td>
    <c:if test="${article.re_level > 0 }"> 
         <img src="<%=request.getContextPath()%>/images/level.gif" width="${article.re_level*5 }" height="16">
    
         <img src="<%=request.getContextPath()%>/images/re.gif">
    </c:if>         
         <a href="<%=request.getContextPath() %>/board4/content?num=${article.num }&no=${number}">${article.subject }</a></td>         
         
         <td align="center" width="100">${article.writer }</td>
         <td align="center" width="150">${article.reg_date }</td>
         <td align="center" width="50">${article.readcount }</td>
         <td align="center" width="100">${article.ip }</td>
         </tr>
         </c:forEach>
         </table>
       
            <div class="w3-center">
            <c:if test="${startPage > bottomLine }">
                  <a href="<%=request.getContextPath() %>/board4/list?pageNum=${startPage - bottomLine}">[이전]</a>
            </c:if>
            
            <c:forEach var="i" begin="${startPage }" end="${endPage }">
               <a href="<%=request.getContextPath() %>/board4/list?pageNum=${i}">
               <c:if test="${i==currentPage }">
               <font color="red">${i }</font>
               </c:if>
               <c:if test="${i!=currentPage }">
               ${i}
            </c:if>
            </a>
            </c:forEach>
            
            <c:if test="${endPage < pageCount }">
                  <a href="<%=request.getContextPath() %>/board4/list?pageNum=${endPage+1}">[다음]</a>
            </c:if>
            </div>
   </c:if>
   </div>
</body>
</html>