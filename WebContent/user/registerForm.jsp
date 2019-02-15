<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>회원가입 페이지</title>

<style>
input[type=email] {
	width: 250px;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	background-color: white;
	background-position: 10px 10px;
	background-repeat: no-repeat;
	padding: 4px 4px 4px 4px;
}

input[type=name] {
	width: 250px;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	background-color: white;
	background-position: 10px 10px;
	background-repeat: no-repeat;
	padding: 4px 4px 4px 4px;
}

input[type=password] {
	width: 250px;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	background-color: white;
	background-position: 10px 10px;
	background-repeat: no-repeat;
	padding: 4px 4px 4px 4px;
}

input[type=text] {
	width: 250px;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	background-color: white;
	background-position: 10px 10px;
	background-repeat: no-repeat;
	padding: 4px 4px 4px 4px;
}
</style>
</head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function checkboard() {
		var form = document.registerForm;
		if (!form.email.value) {
			alert("이메일을 입력해주세요.");
			return false;
		}
		if (!form.name.value) {
			alert("이름을 입력해주세요.");
			return false;
		}
		if (!form.password.value) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}

		if (!form.birth.value) {
			alert("생년월일을 입력해주세요.");
			return false;
		}
		return true;
	}

	var check = function() {
		if (document.getElementById('inputPassword').value == document
				.getElementById('confirmPassword').value) {
			document.getElementById('message').style.color = 'green';
			document.getElementById('message').innerHTML = 'matching';
		} else {
			document.getElementById('message').style.color = 'red';
			document.getElementById('message').innerHTML = 'not matching';
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

	<div class="container" style="margin-top: 10px; margin-bottom: 10px;">
		<center>
			<br> <br>
			<h2>회원 가입</h2>
		</center>
		<form id="Register" name="registerForm" method="post"
			action="<%=request.getContextPath()%>/main/user/register"
			onsubmit="return checkboard()">
			<table class="table table-bordered">

				<center>
					<p>
						<input type="email" id="inputEmail" name="email" placeholder="이메일">
					</p>
				</center>

				<center>
					<p>
						<input type="name" id="inputname" name="name" placeholder="이름">
					</p>
				</center>

				<center>
					<p>
						<input type="password" id="inputPassword" name="password"
							placeholder="비밀번호" onkeyup='check();'>
					</p>
				</center>


				<center>
					<p>
						<input type="password" id="confirmPassword"
							name="confirm_password" placeholder="비밀번호 확인 " onkeyup='check();'>
					<p id='message'></p>
					</p>
				</center>



				<center>
					<p>
						<input type="text" id="inputBirth" name="birth"
							placeholder="생년월일(ex:YYMMDD)">
					</p>
				</center>
				<center>
				<p>
				<input type="text" id="sample4_postcode" name="postcode" placeholder="우편번호">
				<input type="button" style="margin: 20px; width: 200px;"
							class="btn btn-primary" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample4_roadAddress" name="roadAddress" placeholder="도로명주소">
				<input type="text" id="sample4_jibunAddress" name="jibunAddress" placeholder="지번주소">
				<span id="guide" style="color:#999;display:none"></span>
				<p>
				<input type="text" id="sample4_detailAddress" name="detailAddress" placeholder="상세주소">
				<input type="text" id="sample4_extraAddress" name="extraAddress" placeholder="참고항목">
				</p>
				</center>
				<center>
					<p>성별  : 
						<input type=radio id="inputGender" name="gender" value="MALE">남자
						<input type=radio id="inputGender" name="gender" value="FEMALE">여자
					</p>
				</center>
				<center>
					<p>직업 :
						<input type=radio id="inputJob" name="job" value="STUDENT">학생
						<input type=radio id="inputJob" name="job" value="EMPLOYED">직장인
						<input type=radio id="inputJob" name="job" value="UNEMPLOYED">무직
						<input type=radio id="inputJob" name="job" value="ETC">기타
					</p>
				</center>

				<center>
					<p>
						<button style="margin: 20px; width: 200px;"
							class="btn btn-primary" type="submit">회원 가입</button>
					</p>
				</center>
			</table>
		</form>
	</div>


</body>
</html>