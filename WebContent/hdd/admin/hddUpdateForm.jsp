<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> -->
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <!-- <script>
	function checkHDD() {
		var form = document.updateform;
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
	<form action="<%= request.getContextPath() %>/admin/hdd/update" enctype="multipart/form-data" method="post" name="updateform" onsubmit="return checkHDD()">
	<input type="hidden" name="id" value="${ no }">
	<input type="hidden" name="oldfilename" value="${ hdd.filename }">
	<input type="hidden" name="oldfilesize" value="${ hdd.filesize }">
		<div class="w3-container">
		<h2>HDD 수정</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>제품명</td>
					<td><input type="text" name="productName" size="50" maxlength="50" value="${hdd.productName}"></td>
				</tr>
				<tr>
					<td>제조회사</td>
					<td><input type="text" name="productCompany" size="50" maxlength="20" value="${hdd.productName}"></td>
				</tr>
				<tr>
					<td>인터페이스</td>
					<td><input type="text" name="interFace" size="50" maxlength="30" value="${hdd.interFace}"></td>
				</tr>
				<tr>
					<td>디스크 크기</td>
					<td><input type="text" name="diskSize" size="50" maxlength="30" value="${hdd.diskSize}"> cm</td>
				</tr>
				<tr>
					<td>디스크 용량</td>
					<td><input type="text" name="diskCapacity" size="50" maxlength="10" value="${hdd.diskCapacity}"> TB</td>
				</tr>
				<tr>
					<td>버퍼 용량</td>
					<td><input type="text" name="bufferCapacity" size="50" maxlength="20" value="${hdd.bufferCapacity}"> MB</td>
				</tr>
				<tr>
					<td>회전수</td>
					<td><input type="text" name="rotation" size="50" maxlength="20" value="${hdd.rotation}"> RPM</td>
				</tr>
				<tr>
					<td>제품 등록일</td>
					<td><input type="text" name="productDate" size="50" maxlength="20" value="${productDate}"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><input type="text" name="price" size="50" maxlength="20" value="${hdd.price}"> 원</td>
				</tr>
				<tr>
					<td width="70" align="center">file</td>
					<td width="330">
						<input type="file" size="50" maxlength="30" name="uploadfile" value="${hdd.filename}">
					</td>
				</tr>
				<center>
				<tr>
					<td colspan="2">
					<input type="submit" class="btn btn-default" value="Update"> 
					<input type="reset" class="btn btn-default" value="Reset">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#hddModal">
					  Delete
					</button>
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/admin/hdd/list'">
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
<div class="modal fade" id="hddModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
        <button type="button" class="btn btn-primary" onclick="window.location='<%= request.getContextPath() %>/admin/hdd/delete?id=${ no }'">Delete</button>
      </div>
    </div>
  </div>
</div>

</body>
<!-- <footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer> -->
</html>
