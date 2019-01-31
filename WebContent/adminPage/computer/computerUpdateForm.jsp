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
	<form action="<%= request.getContextPath() %>/admin/computer/update" enctype="multipart/form-data" method="post" name="updateform" onsubmit="return checkComputer()">
	<input type="hidden" name="id" value="${ no }">
	<input type="hidden" name="productCode" value="${ computer.productCode }">
	<input type="hidden" name="oldfilename" value="${ computer.filename }">
	<input type="hidden" name="oldfilesize" value="${ computer.filesize }">
		<div class="w3-container">
		<h2>Computer 수정</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>카테고리</td>
					<td>
						<input type=radio id="inputCategory" name="category" value="OFFICE" <c:if test="${computer.category == 'OFFICE'}">checked</c:if>>사무용
						<input type=radio id="inputCategory" name="category" value="GAME" <c:if test="${computer.category == 'GAME'}">checked</c:if>>게임용
						<input type=radio id="inputCategory" name="category" value="DESIGN" <c:if test="${computer.category == 'DESIGN'}">checked</c:if>>디자인용
						<input type=radio id="inputCategory" name="category" value="BROADCASTING" <c:if test="${computer.category == 'BROADCASTING'}">checked</c:if>>방송용
					</td>
				</tr>
				<tr>
					<td>제조회사</td>
					<td><input type="text" name="productCompany" size="50" maxlength="20" value="${computer.productCompany}"></td>
				</tr>
				<tr>
					<td>CPU</td>
					<td><input type="text" name="cpu" size="50" maxlength="50" value="${computer.cpu}"></td>
				</tr>
				<tr>
					<td>메인보드</td>
					<td><input type="text" name="mainBoard" size="50" maxlength="50" value="${computer.mainBoard}"></td>
				</tr>
				<tr>
					<td>메모리(RAM)</td>
					<td><input type="text" name="ram" size="50" maxlength="50" value="${computer.ram}"></td>
				</tr>
				<tr>
					<td>그래픽카드</td>
					<td><input type="text" name="vga" size="50" maxlength="50" value="${computer.vga}"></td>
				</tr>
				<tr>
					<td>하드디스크(HDD)</td>
					<td><input type="text" name="hdd" size="50" maxlength="50" value="${computer.hdd}"></td>
				</tr>
				<tr>
					<td>SSD</td>
					<td><input type="text" name="ssd" size="50" maxlength="50" value="${computer.ssd}"></td>
				</tr>
				<tr>
					<td>케이스</td>
					<td><input type="text" name="tower" size="50" maxlength="50" value="${computer.tower}"></td>
				</tr>
				<tr>
					<td>파워</td>
					<td><input type="text" name="power" size="50" maxlength="50" value="${computer.power}"></td>
				</tr>
				<tr>
					<td>제품 등록일</td>
					<td><input type="text" name="productDate" size="50" maxlength="20" class="datepicker" value="${productDate}"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><input type="text" name="price" size="50" maxlength="20"> 원</td>
				</tr>
				<tr>
					<td width="70" align="center">file</td>
					<td width="330">
						<input type="file" size="50" maxlength="30" name="uploadfile">
					</td>
				</tr>
				<center>
				<tr>
					<td colspan="2">
					<input type="submit" class="btn btn-default" value="Update"> 
					<input type="reset" class="btn btn-default" value="Reset">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#cpuModal">
					  Delete
					</button>
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/admin/cpu/list'">
					</td>
				</tr>
				</center>
			</table>
		</div>	
	</form>
	
	</center>
	</div>
	
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="cpuModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete Confirm</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="window.location='<%= request.getContextPath() %>/admin/computer/delete?id=${ no }'">Delete</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
