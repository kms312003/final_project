<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
    <div class="col-sm-9 text-left">
    
    <h1 style="text-align:center;">Computer 관리</h1>     
      <hr/>
      <div class="w3-container">
      	<div style="text-align:right;">
			<span class="w3-left"> (전체글 : ${ count }) </span>
			<span class="w3-right"><input type="button" class="btn btn-default" value="Create" onclick="window.location='<%= request.getContextPath() %>/admincomputer/write'"></span>
		</div>

		<table class="table table-bordered table-hover" width="1660">
			<tr class="w3-grey">
				<td align="center" width="50">번호</td>
				<td align="center" width="100">제품코드</td>
				<td align="center" width="100">카테고리</td>
				<td align="center" width="150">제품명</td>
				<td align="center" width="120">CPU</td>
				<td align="center" width="120">메인보드</td>
				<td align="center" width="120">메모리</td>
				<td align="center" width="120">SSD</td>
				<td align="center" width="100">가격</td>
				<td align="center" width="150">제품 입력일</td>
				<td align="center" width="50">Actions</td>
			</tr>
			
			<c:if test="${computerList == null}">
				<tr >
					<td colspan="11" align="center">등록된 제품이 없습니다.</td>
				</tr>
			</c:if>
			
			<c:set var = "number" value="${number}"/>
			<c:if test="${computerList != null}">
				<c:forEach var="computer" items="${computerList}">
				<fmt:formatDate var="regDate" value="${computer.regDate}" pattern="yyyy.MM.dd"/>

				<tr>
					<td align="center" width="50">${number}</td>
					<c:set var="number" value="${number-1}"/>
					<td align="center" width="100">${ computer.productCode}</td>
					<td align="center" width="100">
						<c:if test="${computer.category == 'OFFICE'}">사무용</c:if>
						<c:if test="${computer.category == 'GAME'}">게임용</c:if>
						<c:if test="${computer.category == 'DESIGN'}">디자인용</c:if>
						<c:if test="${computer.category == 'BROADCASTING'}">방송용</c:if>
					</td>
					<td align="center" width="150">${ computer.productName }</td>
					<td align="center" width="120">
						<c:choose>
				        	<c:when test="${fn:length(computer.cpu) > 10}">
				            	<c:out value="${fn:substring(computer.cpu,0,10)}"/>....
				            </c:when>
			            	<c:otherwise>
				            	<c:out value="${computer.cpu}"/>
				            </c:otherwise> 
			        	</c:choose>
					</td>
					<td align="center" width="120">
						<c:choose>
				        	<c:when test="${fn:length(computer.mainBoard) > 10}">
				            	<c:out value="${fn:substring(computer.mainBoard,0,13)}"/>....
				            </c:when>
				            <c:otherwise>
				            	<c:out value="${computer.mainBoard}"/>
				            </c:otherwise> 
				        </c:choose>
					</td>
					<td align="center" width="120">
						<c:choose>
				        	<c:when test="${fn:length(computer.ram) > 10}">
				            	<c:out value="${fn:substring(computer.ram,0,13)}"/>....
				            </c:when>
				            <c:otherwise>
				            	<c:out value="${computer.ram}"/>
				            </c:otherwise> 
				        </c:choose>
					</td>
					<td align="center" width="120">
						<c:choose>
				        	<c:when test="${fn:length(computer.ssd) > 10}">
				            	<c:out value="${fn:substring(computer.ssd,0,13)}"/>....
				            </c:when>
				            <c:otherwise>
				            	<c:out value="${computer.ssd}"/>
				            </c:otherwise> 
			        	</c:choose>
					</td>
					
					<td align="center" width="100">${ computer.price }</td>
					<td align="center" width="150">${ regDate }</td>
					<td align="center" width="150">
			        	<a href="<%= request.getContextPath() %>/admincomputer/update?id=${ computer.id }" class="btn btn-sm btn-default" data-toggle="tooltip" data-placement="top" title="수정">
			            	<em class="fa fa-pencil"></em>
			        	</a>
			        	<a href="<%= request.getContextPath() %>/admincomputer/detail?id=${ computer.id }" class="btn btn-sm btn-default" data-toggle="tooltip" data-placement="top" title="상세보기 ">
			            	<em class="glyphicon glyphicon-list-alt"></em>
			        	</a>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>

		<div class="w3-center">
			<c:if test="${startPage > bottomLine }">
				<a href="<%= request.getContextPath() %>/admincomputer/list?pageNum=${  startPage - bottomLine }">[이전]</a>
			</c:if>
		
			<ul class="pagination">
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li>
					<a href="<%= request.getContextPath() %>/admincomputer/list?pageNum=${ i }">
						<c:if test="${i == currentPage }">
							<font color="blue">${i}</font>
						</c:if>
						<c:if test="${i != currentPage }">
					     	${i}
					     </c:if>
					</a>
				</li>
			</c:forEach>
			</ul>
		
			<c:if test="${endPage < pageCount }">
				<a href="<%= request.getContextPath() %>/admincomputer/list?pageNum=${ startPage + bottomLine }">[다음]</a>
			</c:if>
		</div>

		</div>
	</div>
    
  </div>
</div>
</body>
</html>
