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
	<form action="<%= request.getContextPath() %>/admin/cpu/write" enctype="multipart/form-data" method="post" name="createform" onsubmit="return checkCpu()">
		<div class="w3-container">
		<h2>Cpu 등록</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>제품명</td>
					<td><input type="text" name="productName" size="50" maxlength="50"></td>
				</tr>
				<tr>
					<td>제조회사</td>
					<td>
						<input type=radio name="productCompany" value="INTEL_7" checked>인텔 7세대
						<input type=radio name="productCompany" value="INTEL_8">인텔 8세대
						<input type=radio name="productCompany" value="INTEL_9">인텔 9세대
						<input type=radio name="productCompany" value="RYZEN_5">라이젠 5
						<input type=radio name="productCompany" value="RYZEN_7">라이젠 7
					</td>
				</tr>
				<tr>
					<td>브랜드분류</td>
					<td><input type="text" name="brand" size="50" maxlength="30"></td>
				</tr>
				<tr>
					<td>소켓구분</td>
					<td><input type="text" name="socket" size="50" maxlength="30"></td>
				</tr>
				<tr>
					<td>코어</td>
					<td><input type="text" name="core" size="50" maxlength="10"> 코어</td>
				</tr>
				<tr>
					<td>쓰레드</td>
					<td><input type="text" name="thread" size="50" maxlength="20"> 개</td>
				</tr>
				<tr>
					<td>동작속도</td>
					<td><input type="text" name="clockSpeed" size="50" maxlength="20"> GHz</td>
				</tr>
				<tr>
					<td>설계전력(TDP)</td>
					<td><input type="text" name="tdp" size="50" maxlength="20"> W</td>
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
</body>
</html>
