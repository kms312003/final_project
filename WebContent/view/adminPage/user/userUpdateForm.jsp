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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
		if(!form.phoneNum.value){
			alert("전화번호를 입력해주세요.");
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
	
	//주소찾기 script
	 //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
   function sample4_execDaumPostcode() {
       new daum.Postcode({
           oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               var roadAddr = data.roadAddress; // 도로명 주소 변수
               var extraRoadAddr = ''; // 참고 항목 변수

               // 법정동명이 있을 경우 추가한다. (법정리는 제외)
               // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
               if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                   extraRoadAddr += data.bname;
               }
               // 건물명이 있고, 공동주택일 경우 추가한다.
               if(data.buildingName !== '' && data.apartment === 'Y'){
                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
               }
               // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
               if(extraRoadAddr !== ''){
                   extraRoadAddr = ' (' + extraRoadAddr + ')';
               }

               // 우편번호와 주소 정보를 해당 필드에 넣는다.
               document.getElementById('sample4_postcode').value = data.zonecode;
               document.getElementById("sample4_roadAddress").value = roadAddr;
               document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
               
               // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
               if(roadAddr !== ''){
                   document.getElementById("sample4_extraAddress").value = extraRoadAddr;
               } else {
                   document.getElementById("sample4_extraAddress").value = '';
               }

               var guideTextBox = document.getElementById("guide");
               // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
               if(data.autoRoadAddress) {
                   var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                   guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                   guideTextBox.style.display = 'block';

               } else if(data.autoJibunAddress) {
                   var expJibunAddr = data.autoJibunAddress;
                   guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                   guideTextBox.style.display = 'block';
               } else {
                   guideTextBox.innerHTML = '';
                   guideTextBox.style.display = 'none';
               }
           }
       }).open();
   }
</script>
<body>   
    <div class="col-sm-9 text-left"> 
    <center>
	<form action="<%= request.getContextPath() %>/adminuser/updatePro" enctype="multipart/form-data" method="post" name="updateform" onsubmit="return checkboard()">
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
					<td><input type="text" name="birth" size="50" maxlength="6" value="${user.birth}" placeholder="생년월일"> </td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phoneNum" size="50" maxlength="6" value="${user.phoneNum}" placeholder="전화번호"> </td>
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
				
				<!-- 주소 api 시작 -->
				<tr>
					<td>주소</td>
					<td>
						<input type="text" id="sample4_postcode" name="postcode" placeholder="우편번호">
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="sample4_roadAddress" name="roadAddress" placeholder="도로명주소">
					<input type="text" id="sample4_jibunAddress" name="jibunAddress" placeholder="지번주소">
					<span id="guide" style="color:#999;display:none"></span>
					<p>
					<input type="text" id="sample4_detailAddress" name="detailAddress" placeholder="상세주소">
					<input type="text" id="sample4_extraAddress" name="extraAddress" placeholder="참고항목">	
					</p>
					</td>
				</tr>
				<!-- 주소 api 끝 -->
				
				<center>
				<tr>
					<td colspan="2">
					<input type="submit" class="btn btn-default" value="Update"> 
					<input type="reset" class="btn btn-default" value="Reset">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#userModal">
					  Delete
					</button>
					<input type="button" class="btn btn-default" value="List" onclick="window.location='<%= request.getContextPath() %>/adminuser/list'">
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
        <button type="button" class="btn btn-primary" onclick="window.location='<%= request.getContextPath() %>/adminuser/delete?id=${ no }'">Delete</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
