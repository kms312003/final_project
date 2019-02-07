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
	<form action="<%= request.getContextPath() %>/admin/mainboard/update" enctype="multipart/form-data" method="post" name="updateform" onsubmit="return checkMainBoard()">
	<input type="hidden" name="id" value="${ no }">
	<input type="hidden" name="productCode" value="${ mainboard.productCode }">
	<input type="hidden" name="oldfilename" value="${ mainboard.filename }">
	<input type="hidden" name="oldfilesize" value="${ mainboard.filesize }">
		<div class="w3-container">
		<h2>MainBoard 수정</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>제품명</td>
					<td><input type="text" name="productName" size="50" maxlength="50" value="${mainboard.productName}"></td>
				</tr>
				<tr>
					<td>제조회사</td>
					<td>
						<input type=radio name="productCompany" value="ASROCK" <c:if test="${mainboard.productCompany == 'ASROCK'}">checked</c:if>>ASROCK
						<input type=radio name="productCompany" value="ASUS" <c:if test="${mainboard.productCompany == 'ASUS'}">checked</c:if>>ASUS
						<input type=radio name="productCompany" value="MSI" <c:if test="${mainboard.productCompany == 'MSI'}">checked</c:if>>MSI
					</td>
				</tr>
				<tr>
					<td>CPU 소켓</td>
					<td><input type="text" name="cpuSocket" size="50" maxlength="30" value="${mainboard.cpuSocket}"></td>
				</tr>
				<tr>
					<td>세부 칩셋</td>
					<td><input type="text" name="chipSet" size="50" maxlength="30" value="${mainboard.chipSet}"></td>
				</tr>
				<tr>
					<td>폼팩터</td>
					<td><input type="text" name="formFactor" size="50" maxlength="20" value="${mainboard.formFactor}"></td>
				</tr>
				<tr>
					<td>메모리 종류</td>
					<td><input type="text" name="memoryType" size="50" maxlength="10" value="${mainboard.memoryType}"></td>
				</tr>
				<tr>
					<td>제품 분류</td>
					<td><input type="text" name="productSort" size="50" maxlength="20" value="${mainboard.productSort}"></td>
				</tr>
				<tr>
					<td>메모리 슬롯 수</td>
					<td><input type="text" name="memorySlot" size="50" maxlength="10" value="${mainboard.memorySlot}"> 개</td>
				</tr>
				<tr>
					<td>제품 등록일</td>
					<td><input type="text" name="productDate" size="50" maxlength="20" class="datepicker" value="${productDate}"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><input type="text" name="price" size="50" maxlength="20" value="${mainboard.price}"> 원</td>
				</tr>
				<tr>
					<td width="70" align="center">file</td>
					<td width="330">
						<input type="file" size="50" maxlength="30" name="uploadfile" value="${mainboard.filename}">
					</td>
				</tr>
				<center>
				<tr>
					<td colspan="2">
					<input type="submit" class="btn btn-default" value="Update"> 
					<input type="reset" class="btn btn-default" value="Reset">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#mainBoardModal">
					  Delete
					</button>
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/admin/mainboard/list'">
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
<div class="modal fade" id="mainBoardModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
        <button type="button" class="btn btn-primary" onclick="window.location='<%= request.getContextPath() %>/admin/mainboard/delete?id=${ no }'">Delete</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
