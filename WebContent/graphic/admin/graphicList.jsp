<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    table {
    	margin-left: auto;
    	margin-right: auto;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 700px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
      /* margin-top:50px; */
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
</head>
<body>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="<%= request.getContextPath() %>/admin/cpu/list">Cpu</a></p>
      <p><a href="<%= request.getContextPath() %>/admin/mainboard/list">메인보드</a></p>
      <p><a href="<%= request.getContextPath() %>/admin/ram/list">메모리(Ram)</a></p>
      <p><a href="<%= request.getContextPath() %>/admin/graphic/list">그래픽카드</a></p>
      <p><a href="<%= request.getContextPath() %>/admin/hdd/list">하드디스크(HDD)</a></p>
      <p><a href="<%= request.getContextPath() %>/admin/ssd/list">SSD</a></p>
      <p><a href="<%= request.getContextPath() %>/admin/power/list">파워</a></p>
    </div>
    
    <div class="col-sm-9 text-left">
    
    <h1 style="text-align:center;">Graphic 관리</h1>     
      <hr/>
      <div class="w3-container">
      	<div style="text-align:right;">
			<span class="w3-left"> (전체글 : ${ count }) </span>
			<span class="w3-right">
				<a href="<%= request.getContextPath() %>/admin/graphic/write">제품등록</a>
			</span>
		</div>

		<table class="table table-bordered table-hover" width="1200">
			<tr class="w3-grey">
				<td align="center" width="50">번호</td>
				<td align="center" width="100">제품코드</td>
				<td align="center" width="200">제품명</td>
				<td align="center" width="100">제조회사</td>
				<td align="center" width="150">NVIDIA 칩셋</td>
				<td align="center" width="150">칩셋 그룹</td>
				<td align="center" width="100">메모리 용량</td>
				<td align="center" width="150">가격</td>
				<td align="center" width="150">제품 입력일</td>
				<td align="center" width="50">Actions</td>
			</tr>
			
			<c:if test="${graphicList == null}">
				<tr >
					<td colspan="10" align="center">등록된 제품이 없습니다.</td>
				</tr>
			</c:if>
			
			<c:set var = "number" value="${number}"/>
			<c:if test="${graphicList != null}">
				<c:forEach var="graphic" items="${graphicList}">
				<fmt:formatDate var="regDate" value="${graphic.regDate}" pattern="yyyy.MM.dd"/>
				
				<tr>
					<td align="center" width="50">${number}</td>
					<c:set var="number" value="${number-1}"/>
					<td align="center" width="100">${ graphic.productCode}</td>
					<td align="center" width="200">${ graphic.productName}</td>
					<td align="center" width="100">${ graphic.productCompany }</td>
					<td align="center" width="150">${ graphic.nvidiaChipSet }</td>
					<td align="center" width="150">${ graphic.chipSetGroup }</td>
					<td align="center" width="100">${ graphic.memoryCapacity }</td>
					<td align="center" width="150">${ graphic.price }</td>
					<td align="center" width="150">${ regDate }</td>
					<td align="center" width="50">
			        	<a href="<%= request.getContextPath() %>/admin/graphic/update?id=${ graphic.id }" class="btn btn-sm btn-default" data-toggle="tooltip" data-placement="top" title="상세보기 및 수정">
			            	<em class="fa fa-pencil"></em>
			        	</a>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>

		<div class="w3-center">
			<c:if test="${startPage > bottomLine }">
				<a href="<%= request.getContextPath() %>/admin/graphic/list?pageNum=${  startPage - bottomLine }">[이전]</a>
			</c:if>
		
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="<%= request.getContextPath() %>/admin/graphic/list?pageNum=${ i }">
					<c:if test="${i == currentPage }">
						<font color="red">${i}</font>
					</c:if>
					<c:if test="${i != currentPage }">
				     	${i}
				     </c:if>
				</a>
			</c:forEach>
		
			<c:if test="${endPage < pageCount }">
				<a href="<%= request.getContextPath() %>/admin/graphic/list?pageNum=${ startPage + bottomLine }">[다음]</a>
			</c:if>
		</div>

		</div>
	</div>
    
  </div>
</div>

</body>
<!-- <footer class="container-fluid text-center">
  <p>Just travel</p>
</footer> -->
</html>
