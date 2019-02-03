<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
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
    <center>
	<%-- <form action="<%= request.getContextPath() %>/admin/cpu/update" enctype="multipart/form-data" method="post" name="updateform" onsubmit="return checkCpu()"> --%>
	<input type="hidden" name="id" value="${ no }">
	<input type="hidden" name="productCode" value="${ productCode }">
	<input type="hidden" name="oldfilename" value="${ filename }">
	<input type="hidden" name="oldfilesize" value="${ filesize }">
		<div class="w3-container">
		<h2>Cpu 상세보기</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>제품코드</td>
					<td align="center" width="330">${ productCode}</td>
				</tr>
				<tr>
					<td>제품명</td>
					<td align="center" width="330">${ productName}</td>
				</tr>
				<tr>
					<td>제조회사</td>
					<td align="center" width="330">${ productCompany}</td>
				</tr>
				<tr>
					<td>브랜드분류</td>
					<td align="center" width="330">${ brand}</td>
				</tr>
				<tr>
					<td>소켓구분</td>
					<td align="center" width="330">${ socket}</td>
				</tr>
				<tr>
					<td>코어</td>
					<td align="center" width="330">${ core} 코어</td>
				</tr>
				<tr>
					<td>쓰레드</td>
					<td align="center" width="330">${ thread} 개</td>
				</tr>
				<tr>
					<td>동작속도</td>
					<td align="center" width="330">${ clockSpeed} GHz</td>
				</tr>
				<tr>
					<td>설계전력(TDP)</td>
					<td align="center" width="330">${ tdp} W</td>
				</tr>
				<fmt:formatDate var="productDate" value="${productDate}" pattern="yyyy.MM.dd" />
				<fmt:formatDate var="regDate" value="${regDate}" pattern="yyyy.MM.dd" />
				<tr>
					<td>생산일</td>
					<td align="center" width="330">${ productDate}</td>
				</tr>
				<tr>
					<td>제품 입력일</td>
					<td align="center" width="330">${ regDate}</td>
				</tr>
				<tr>
					<td>가격</td>
					<td align="center" width="330">${ price} 원</td>
				</tr>
				<tr>
					<td>FILE</td>
					<td align="center" width="330">
						<c:if test="${filename == null}">파일없음</c:if>
						<c:if test="${filename != null}">${filename}</c:if>
					</td>
				</tr>
				<center>
				<tr>
					<td colspan="2">
					<input type="button" class="btn btn-default" value="Update" onclick="window.location='<%= request.getContextPath() %>/admin/cpu/update?id=${no}'"> 
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/admin/cpu/list'">
					</td>
				</tr>
				</center>
			</table>
		</div>	
	<!-- </form> -->
	
	</center>
	</div>
	
  </div>
</div>
</body>
</html>
