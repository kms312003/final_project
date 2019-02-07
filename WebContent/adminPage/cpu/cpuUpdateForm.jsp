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
	<form action="<%= request.getContextPath() %>/admin/cpu/update" enctype="multipart/form-data" method="post" name="updateform" onsubmit="return checkCpu()">
	<input type="hidden" name="id" value="${ no }">
	<input type="hidden" name="productCode" value="${ cpu.productCode }">
	<input type="hidden" name="oldfilename" value="${ cpu.filename }">
	<input type="hidden" name="oldfilesize" value="${ cpu.filesize }">
		<div class="w3-container">
		<h2>Cpu 수정</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>제품명</td>
					<td><input type="text" name="productName" size="50" maxlength="50" value="${cpu.productName}"></td>
				</tr>
				<tr>
					<td>제조회사</td>
					<td>
						<input type=radio name="productCompany" value="INTEL_7" <c:if test="${cpu.productCompany == 'INTEL_7'}">checked</c:if>>인텔 7세대
						<input type=radio name="productCompany" value="INTEL_8" <c:if test="${cpu.productCompany == 'INTEL_8'}">checked</c:if>>인텔 8세대
						<input type=radio name="productCompany" value="INTEL_9" <c:if test="${cpu.productCompany == 'INTEL_9'}">checked</c:if>>인텔 9세대
						<input type=radio name="productCompany" value="RYZEN_5" <c:if test="${cpu.productCompany == 'RYZEN_5'}">checked</c:if>>라이젠 5
						<input type=radio name="productCompany" value="RYZEN_7" <c:if test="${cpu.productCompany == 'RYZEN_7'}">checked</c:if>>라이젠 7
					</td>
				</tr>
				<tr>
					<td>브랜드분류</td>
					<td><input type="text" name="brand" size="50" maxlength="30" value="${cpu.brand}"></td>
				</tr>
				<tr>
					<td>소켓구분</td>
					<td><input type="text" name="socket" size="50" maxlength="30" value="${cpu.socket}"></td>
				</tr>
				<tr>
					<td>코어</td>
					<td><input type="text" name="core" size="50" maxlength="10" value="${cpu.core}"> 코어</td>
				</tr>
				<tr>
					<td>쓰레드</td>
					<td><input type="text" name="thread" size="50" maxlength="20" value="${cpu.thread}"> 개</td>
				</tr>
				<tr>
					<td>동작속도</td>
					<td><input type="text" name="clockSpeed" size="50" maxlength="20" value="${cpu.clockSpeed}"> GHz</td>
				</tr>
				<tr>
					<td>설계전력(TDP)</td>
					<td><input type="text" name="tdp" size="50" maxlength="20" value="${cpu.tdp}"> W</td>
				</tr>
				<tr>
					<td>제품 등록일</td>
					<td><input type="text" name="productDate" size="50" maxlength="20" class="datepicker" value="${productDate}"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><input type="text" name="price" size="50" maxlength="20" value="${cpu.price}"> 원</td>
				</tr>
				<tr>
					<td width="70" align="center">file</td>
					<td width="330">
						<input type="file" size="50" maxlength="30" name="uploadfile" value="${cpu.filename}">
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
        <button type="button" class="btn btn-primary" onclick="window.location='<%= request.getContextPath() %>/admin/cpu/delete?id=${ no }'">Delete</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
