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
	<form name="registerForm" action="<%= request.getContextPath() %>/admin/user/write" enctype="multipart/form-data" method="post" onsubmit="return checkboard()">
		<div class="w3-container">
		<h2>User 등록</h2>	
			<table class="table table-bordered" style="width:80%;">
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" size="50" maxlength="50" placeholder="email"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="name" name="name" size="50" maxlength="20" placeholder="이름"></td>
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
						<input type=radio id="inputGender" name="gender" value="MALE" checked>남자
						<input type=radio id="inputGender" name="gender" value="FEMALE">여자
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="birth" size="50" maxlength="6" placeholder="생년월일"></td>
				</tr>
				<tr>
					<td>직업</td>
					<td>
						<input type=radio id="inputJob" name="job" value="STUDENT" checked>학생
						<input type=radio id="inputJob" name="job" value="EMPLOYED">직장인
						<input type=radio id="inputJob" name="job" value="UNEMPLOYED">무직
						<input type=radio id="inputJob" name="job" value="ETC">기타
					</td>		
				</tr>
				<center>
				<tr>
					<td colspan="2">
					<input type="submit" class="btn btn-default" value="Create"> 
					<input type="reset" class="btn btn-default" value="Reset">
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
</body>
</html>
