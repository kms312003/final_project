<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <!-- datepicker 필요 --> 
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />    
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
  <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
  <script src="/final_project/js/datepicker.js"></script>
  
  <!-- <script>
	function checkGraphic() {
		var form = document.createform;
		if(!form.name.value) {
			alert("name error");
			return false;
		}
		if(!form.number.value) {
			alert("number error");
			return false;
		}
		if(!form.size.value) {
			alert("size error");
			return false;
		}
		if(!form.price.value) {
			alert("price error");
			return false;
		}
		if(!form.content.value) {
			alert("content  error");
			return false;
		}
		return true;
	}
  </script> -->

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
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="<%= request.getContextPath() %>/houseModel2/list">숙소관리</a></p>
      <p><a href="<%= request.getContextPath() %>/likeModel2/list">찜관리</a></p>
      <p><a href="<%= request.getContextPath() %>/reservationModel2/list">예약관리</a></p>
    </div>
    
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
					<td>제조회사</td>
					<td><input type="text" name="productCompany" size="50" maxlength="20"></td>
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
<!-- <footer class="container-fluid text-center">
  <p>Just travel</p>
</footer> -->
</html>