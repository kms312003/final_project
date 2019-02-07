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
<script>
	function checkboard() {
		var form = document.registerForm;
		if (!form.email.value) {
			alert("이메일을 적어주세요.");
			return false;
		}
		if (!form.name.value) {
			alert("name error");
			return false;
		}
		if (!form.password.value) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		
		if (!form.birth.value){
			alert("생년월일을 입력해주세요.");
			return false;
		}
		return true;
	}
	
	var check = function(){
		if(document.getElementById('inputPassword').value==document.getElementById('confirmPassword').value){
			document.getElementById('message').style.color = 'green';
			document.getElementById('message').innerHTML ='matching';
		}else{
			document.getElementById('message').style.color = 'red';
			document.getElementById('message').innerHTML ='not matching';
		}
	}
</script>
<body>   
    <div class="col-sm-9 text-left"> 
    <center>
	<form action="<%= request.getContextPath() %>/admin/user/update" enctype="multipart/form-data" method="post" name="updateform" onsubmit="return checkboard()">
	<input type="hidden" name="id" value="${ no }">
		<div class="w3-container">
		<h2>User 수정</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" size="50" maxlength="50" value="${user.email}" placeholder="email"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="name" name="name" size="50" maxlength="20" value="${user.name}" placeholder="이름"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" size="50" maxlength="30" placeholder="비밀번호" onkeyup='check();'></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="confirm_password" size="50" maxlength="30" placeholder="비밀번호 확인" onkeyup='check();'></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<input type=radio id="inputGender" name="gender" value="MALE" <c:if test="${user.gender == 'MALE'}">checked</c:if>>남자
						<input type=radio id="inputGender" name="gender" value="FEMALE" <c:if test="${user.gender == 'FEMALE'}">checked</c:if>>여자
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="birth" size="50" maxlength="6" value="${user.birth}" placeholder="생년월일"> 개</td>
				</tr>
				<tr>
					<td>직업</td>
					<td>
						<input type=radio id="inputJob" name="job" value="STUDENT" <c:if test="${user.job == 'STUDENT'}">checked</c:if>>학생
						<input type=radio id="inputJob" name="job" value="EMPLOYED" <c:if test="${user.job == 'EMPLOYED'}">checked</c:if>>직장인
						<input type=radio id="inputJob" name="job" value="UNEMPLOYED" <c:if test="${user.job == 'UNEMPLOYED'}">checked</c:if>>무직
						<input type=radio id="inputJob" name="job" value="ETC" <c:if test="${user.job == 'ETC'}">checked</c:if>>기타
					</td>		
				</tr>
				<center>
				<tr>
					<td colspan="2">
					<input type="submit" class="btn btn-default" value="Update"> 
					<input type="reset" class="btn btn-default" value="Reset">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#userModal">
					  Delete
					</button>
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/admin/user/list'">
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
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
        <button type="button" class="btn btn-primary" onclick="window.location='<%= request.getContextPath() %>/admin/user/delete?id=${ no }'">Delete</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>