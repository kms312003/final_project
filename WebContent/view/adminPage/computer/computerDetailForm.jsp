<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
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
	<input type="hidden" name="id" value="${ no }">
	<input type="hidden" name="productCode" value="${ productCode }">
	<input type="hidden" name="oldfilename" value="${ filename }">
	<input type="hidden" name="oldfilesize" value="${ filesize }">
		<div class="w3-container">
		<h2>Computer 상세보기</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>제품코드</td>
					<td align="center" width="330">${ productCode}</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td align="center" width="330">
						<c:if test="${category == 'OFFICE'}">사무용 PC</c:if>
						<c:if test="${category == 'GAME'}">게임용 PC</c:if>
						<c:if test="${category == 'DESIGN'}">디자인용 PC</c:if>
						<c:if test="${category == 'BROADCASTING'}">방송용 PC</c:if>
					</td>
				</tr>
				<tr>
					<td>제품명</td>
					<td align="center" width="330">${ productName}</td>
				</tr>
				<tr>
					<td>CPU</td>
					<td align="center" width="330">${ cpu} </td>
				</tr>
				<tr>
					<td>메인보드</td>
					<td align="center" width="330">${ mainBoard} </td>
				</tr>
				<tr>
					<td>메모리</td>
					<td align="center" width="330">${ ram} </td>
				</tr>
				<tr>
					<td>그래픽카드</td>
					<td align="center" width="330">${ vga} </td>
				</tr>
				<tr>
					<td>하드디스크</td>
					<td align="center" width="330">${ hdd} </td>
				</tr>
				<tr>
					<td>SSD</td>
					<td align="center" width="330">${ ssd} </td>
				</tr>
				<tr>
					<td>케이스</td>
					<td align="center" width="330">${ tower} </td>
				</tr>
				<tr>
					<td>파워</td>
					<td align="center" width="330">${ power} </td>
				</tr>
				<fmt:formatDate var="productDate" value="${productDate}" pattern="yyyy.MM.dd" />
				<fmt:formatDate var="regDate" value="${regDate}" pattern="yyyy.MM.dd" />
				<tr>
					<td>제품 등록일</td>
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
					<input type="button" class="btn btn-default" value="Update" onclick="window.location='<%= request.getContextPath() %>/admincomputer/update?id=${no}'"> 
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/admincomputer/list'">
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
