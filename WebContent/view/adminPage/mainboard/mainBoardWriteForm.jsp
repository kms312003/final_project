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
	<form action="<%= request.getContextPath() %>/adminmainboard/writePro" enctype="multipart/form-data" method="post" name="createform" onsubmit="return checkMainBoard()">
		<div class="w3-container">
		<h2>MainBoard 등록</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>제품명</td>
					<td><input type="text" name="productName" size="50" maxlength="50"></td>
				</tr>
				<tr>
					<td>코드</td>
					<td><input type="text" name="code" size="50" maxlength="6"></td>
				</tr>
				<tr>
					<td>제조회사</td>
					<td>
						<input type=radio name="productCompany" value="ASROCK" checked>ASROCK
						<input type=radio name="productCompany" value="ASUS">ASUS
						<input type=radio name="productCompany" value="MSI">MSI
					</td>
				</tr>
				<tr>
					<td>CPU 소켓</td>
					<td><input type="text" name="cpuSocket" size="50" maxlength="30"></td>
				</tr>
				<tr>
					<td>세부 칩셋</td>
					<td><input type="text" name="chipSet" size="50" maxlength="30"></td>
				</tr>
				<tr>
					<td>폼팩터</td>
					<td><input type="text" name="formFactor" size="50" maxlength="20"></td>
				</tr>
				<tr>
					<td>메모리 종류</td>
					<td><input type="text" name="memoryType" size="50" maxlength="10"></td>
				</tr>
				<tr>
					<td>제품 분류</td>
					<td><input type="text" name="productSort" size="50" maxlength="20"></td>
				</tr>
				<tr>
					<td>메모리 슬롯 수</td>
					<td><input type="text" name="memorySlot" size="50" maxlength="10"> 개</td>
				</tr>
				<tr>
					<td>제품 등록일</td>
					<td><input type="text" name="productDate" size="50" maxlength="20" class="datepicker"></td>
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
					<input type="submit" class="btn btn-default" value="Create"> 
					<input type="reset" class="btn btn-default" value="Reset">
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/adminmainboard/list'">
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
</body>
</html>
