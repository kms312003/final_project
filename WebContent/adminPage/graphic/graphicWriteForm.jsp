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
	<form action="<%= request.getContextPath() %>/admin/graphic/write" enctype="multipart/form-data" method="post" name="createform" onsubmit="return checkGraphic()">
		<div class="w3-container">
		<h2>Graphic 등록</h2>	
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
						<input type=radio name="productCompany" value="MSI" checked>MSI
						<input type=radio name="productCompany" value="GIGABYTE">기가바이트
						<input type=radio name="productCompany" value="ZOTAC">조탁
					</td>
				</tr>
				<tr>
					<td>칩셋 그룹</td>
					<td><input type="text" name="chipSetGroup" size="50" maxlength="30"></td>
				</tr>
				<tr>
					<td>인터페이스</td>
					<td><input type="text" name="interFace" size="50" maxlength="30"></td>
				</tr>
				<tr>
					<td>전원포트</td>
					<td><input type="text" name="powerPort" size="50" maxlength="10"> 개</td>
				</tr>
				<tr>
					<td>메모리 용량</td>
					<td><input type="text" name="memoryCapacity" size="50" maxlength="20"> GB</td>
				</tr>
				<tr>
					<td>NVIDIA 칩셋</td>
					<td><input type="text" name="nvidiaChipSet" size="50" maxlength="20"></td>
				</tr>
				<tr>
					<td>최대 사용 전력</td>
					<td><input type="text" name="maxPower" size="50" maxlength="20"> W</td>
				</tr>
				<tr>
					<td>최대 지원 모니터 수</td>
					<td><input type="text" name="maxMonitor" size="50" maxlength="20"> 개</td>
				</tr>
				<tr>
					<td>길이</td>
					<td><input type="text" name="length" size="50" maxlength="20"> mm</td>
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
</body>
</html>
