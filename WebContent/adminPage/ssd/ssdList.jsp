<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
    
    <h1 style="text-align:center;">SSD 관리</h1>     
      <hr/>
      <div class="w3-container">
      	<div style="text-align:right;">
			<span class="w3-left"> (전체글 : ${ count }) </span>
			<span class="w3-right">
				<a href="<%= request.getContextPath() %>/admin/ssd/write">제품등록</a>
			</span>
		</div>

		<table class="table table-bordered table-hover" width="1200">
			<tr class="w3-grey">
				<td align="center" width="50">번호</td>
				<td align="center" width="100">제품코드</td>
				<td align="center" width="200">제품명</td>
				<td align="center" width="100">제조회사</td>
				<td align="center" width="150">디스크 타입</td>
				<td align="center" width="150">디스크 용량</td>
				<td align="center" width="100">인터페이스</td>
				<td align="center" width="150">가격</td>
				<td align="center" width="150">제품 입력일</td>
				<td align="center" width="50">Actions</td>
			</tr>
			
			<c:if test="${ssdList == null}">
				<tr >
					<td colspan="10" align="center">등록된 제품이 없습니다.</td>
				</tr>
			</c:if>
			
			<c:set var = "number" value="${number}"/>
			<c:if test="${ssdList != null}">
				<c:forEach var="ssd" items="${ssdList}">
				<fmt:formatDate var="regDate" value="${ssd.regDate}" pattern="yyyy.MM.dd"/>
				
				<tr>
					<td align="center" width="50">${number}</td>
					<c:set var="number" value="${number-1}"/>
					<td align="center" width="100">${ ssd.productCode}</td>
					<td align="center" width="200">${ ssd.productName}</td>
					<td align="center" width="100">${ ssd.productCompany }</td>
					<td align="center" width="150">${ ssd.diskType }</td>
					<td align="center" width="150">${ ssd.diskCapacity }</td>
					<td align="center" width="100">${ ssd.interFace }</td>
					<td align="center" width="150">${ ssd.price }</td>
					<td align="center" width="150">${ regDate }</td>
					<td align="center" width="150">
			        	<a href="<%= request.getContextPath() %>/admin/ssd/update?id=${ ssd.id }" class="btn btn-sm btn-default" data-toggle="tooltip" data-placement="top" title="수정">
			            	<em class="fa fa-pencil"></em>
			        	</a>
			        	<a href="<%= request.getContextPath() %>/admin/ssd/detail?id=${ ssd.id }" class="btn btn-sm btn-default" data-toggle="tooltip" data-placement="top" title="상세보기 ">
			            	<em class="glyphicon glyphicon-list-alt"></em>
			        	</a>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>

		<div class="w3-center">
			<c:if test="${startPage > bottomLine }">
				<a href="<%= request.getContextPath() %>/admin/ssd/list?pageNum=${  startPage - bottomLine }">[이전]</a>
			</c:if>
		
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="<%= request.getContextPath() %>/admin/ssd/list?pageNum=${ i }">
					<c:if test="${i == currentPage }">
						<font color="red">${i}</font>
					</c:if>
					<c:if test="${i != currentPage }">
				     	${i}
				     </c:if>
				</a>
			</c:forEach>
		
			<c:if test="${endPage < pageCount }">
				<a href="<%= request.getContextPath() %>/admin/ssd/list?pageNum=${ startPage + bottomLine }">[다음]</a>
			</c:if>
		</div>

		</div>
	</div>
    
  </div>
</div>

</body>
</html>
