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
	<form action="<%= request.getContextPath() %>/admin/graphic/update" enctype="multipart/form-data" method="post" name="updateform" onsubmit="return checkGraphic()">
	<input type="hidden" name="id" value="${ no }">
	<input type="hidden" name="productCode" value="${ graphic.productCode }">
	<input type="hidden" name="oldfilename" value="${ graphic.filename }">
	<input type="hidden" name="oldfilesize" value="${ graphic.filesize }">
		<div class="w3-container">
		<h2>Graphic 수정</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>제품명</td>
					<td><input type="text" name="productName" size="50" maxlength="50" value="${graphic.productName}"></td>
				</tr>
				<tr>
					<td>코드</td>
					<td><input type="text" name="code" size="50" maxlength="6" value="${graphic.code}"></td>
				</tr>
				<tr>
					<td>제조회사</td>
					<td>
						<input type=radio name="productCompany" value="MSI" <c:if test="${graphic.productCompany == 'MSI'}">checked</c:if>>MSI
						<input type=radio name="productCompany" value="GIGABYTE" <c:if test="${graphic.productCompany == 'GIGABYTE'}">checked</c:if>>기가바이트
						<input type=radio name="productCompany" value="ZOTAC" <c:if test="${graphic.productCompany == 'ZOTAC'}">checked</c:if>>조탁
					</td>
				</tr>
				<tr>
					<td>칩셋 그룹</td>
					<td><input type="text" name="chipSetGroup" size="50" maxlength="30" value="${graphic.chipSetGroup}"></td>
				</tr>
				<tr>
					<td>인터페이스</td>
					<td><input type="text" name="interFace" size="50" maxlength="30" value="${graphic.interFace}"></td>
				</tr>
				<tr>
					<td>전원포트</td>
					<td><input type="text" name="powerPort" size="50" maxlength="10" value="${graphic.powerPort}"> 개</td>
				</tr>
				<tr>
					<td>메모리 용량</td>
					<td><input type="text" name="memoryCapacity" size="50" maxlength="20" value="${graphic.memoryCapacity}"> GB</td>
				</tr>
				<tr>
					<td>NVIDIA 칩셋</td>
					<td><input type="text" name="nvidiaChipSet" size="50" maxlength="20" value="${graphic.nvidiaChipSet}"></td>
				</tr>
				<tr>
					<td>최대 사용 전력</td>
					<td><input type="text" name="maxPower" size="50" maxlength="20" value="${graphic.maxPower}"> W</td>
				</tr>
				<tr>
					<td>최대 지원 모니터 수</td>
					<td><input type="text" name="maxMonitor" size="50" maxlength="20" value="${graphic.maxMonitor}"> 개</td>
				</tr>
				<tr>
					<td>길이</td>
					<td><input type="text" name="length" size="50" maxlength="20" value="${graphic.length}"> mm</td>
				</tr>
				<tr>
					<td>제품 등록일</td>
					<td><input type="text" name="productDate" size="50" maxlength="20" class="datepicker" value="${productDate}"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><input type="text" name="price" size="50" maxlength="20" value="${graphic.price}"> 원</td>
				</tr>
				<tr>
					<td width="70" align="center">file</td>
					<td width="330">
						<input type="file" size="50" maxlength="30" name="uploadfile" value="${graphic.filename}">
					</td>
				</tr>
				<center>
				<tr>
					<td colspan="2">
					<input type="submit" class="btn btn-default" value="Update"> 
					<input type="reset" class="btn btn-default" value="Reset">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#graphicModal">
					  Delete
					</button>
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/admin/graphic/list'">
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
<div class="modal fade" id="graphicModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
        <button type="button" class="btn btn-primary" onclick="window.location='<%= request.getContextPath() %>/admin/graphic/delete?id=${ no }'">Delete</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
