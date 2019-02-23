<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<!-- 주소 -->
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
		if(!form.phoneNum.value){
			alert("전화번호를 입력해주세요.");
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
               /* document.getElementById("sample4_jibunAddress").value = data.jibunAddress; */
               
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
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
	var IMP = window.IMP;
	IMP.init('imp20438571');
</script>
<body>
	<div class="container" style="margin-top:30px; margin-bottom:30px;">
		<table width="920" border="0" align="center" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td width="230" valign="top">
						<img src="<%= request.getContextPath() %>/fileSave/order_product_information.png" width="920" height="40">
					</td>
			</tbody>
		</table>
		
		<table class="table">
			<tbody>
				<tr>
					<td align="center" width="100"><strong>상품번호</strong></td>
					<td align="left" width="300"><strong>상품명</strong></td>
					<td align="center" width="50"><strong>수량</strong></td>
					<td align="center" width="200"><strong>가격</strong></td>
					<td align="center" width="200"><strong>합계</strong></td>
				</tr>
				<tr>
					<td id="productCode" align="center" width="100">${orderWaitProduct.productCode}</td>
					<td id="productName" align="left" width="300">${orderWaitProduct.productName}</td>
					<td align="center" width="50">1</td>
					<td align="center" width="200">${orderWaitProduct.price}</td>
					<td id="total" align="center" width="200">${orderWaitProduct.price}</td>
				</tr>
				<tr>
					<td colspan="5" align="right">
						<button	type="button" class="btn btn-info" onclick="location.href='javascript:history.back()'">
							이전페이지
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<hr/>
	
	<div class="container" style="margin-top:30px; margin-bottom:30px;">
			<!-- 주문고객 정보 -->
			<div class="col-sm-5">
				<table width="320" border="0" align="center" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td width="230" valign="top">
								<img src="<%= request.getContextPath() %>/fileSave/order_user_information.png" width="320" height="65">
							</td>
					</tbody>
				</table>
				
				<table class="table">
					<tbody>
						<tr>
							<td align="center" width="40%">주문자 이름</td>
							<td align="left" width="60%">
								<input type="text" name="userName" value="${user.name}" size="30">
							</td>
						</tr>
						<tr>
							<td align="center" width="40%">이메일</td>
							<td align="left" width="60%">
								<input type="text" name="email" value="${user.email}" size="30">
							</td>
						</tr>
						<tr>
							<td align="center" width="40%">휴대전화</td>
							<td align="left" width="60%">
								<input type="text" name="phoneNum" value="${user.phoneNum}" size="30">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<!-- 배송지 정보 -->
			<div class="col-sm-7">
				<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td width="230" valign="top">
								<img src="<%= request.getContextPath() %>/fileSave/delivery_information.png" width="600" height="65">
							</td>
					</tbody>
				</table>

				<table class="table">
					<tbody>
						<tr>
							<td align="center" width="30%">받으실분 이름</td>
							<td align="left" width="70%">
								<input type="text" name="userName" value="${user.name}" size="30">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								주문자와 동일 <input type="checkbox" name="orderUserEqual">
							</td>
						</tr>
						<tr>
							<td align="center" width="30%">휴대전화</td>
							<td align="left" width="70%">
								<input type="text" name="email" value="${user.phoneNum}" size="30">
							</td>
						</tr>
						<tr>
							<td align="center" width="30%">주소</td>
							<td align="left" width="70%">
								<table>
									<tbody>
										<tr>
											<td>
												<input type="text" id="sample4_postcode" name="postcode" placeholder="우편번호">
												<button type="button" class="btn btn-default" onclick="sample4_execDaumPostcode()" >우편번호검색</button>
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" id="sample4_roadAddress" name="roadAddress" size="30" placeholder="도로명주소">도로명 주소
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" id="sample4_extraAddress" name="extraAddress" size="30" placeholder="참고항목">	참고항목	
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" id="sample4_detailAddress" name="detailAddress" size="30" placeholder="상세주소">상세주소
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<hr/>
			<div style="width:200px; float:right; margin-top:50px;">
				<table class="table" style="padding:20px;">
					<tbody>
						<tr>
							<td><strong>총 결제 금액</strong></td>
						</tr>
						<tr>
							<td>${total} 원</td>
						</tr>
						<tr>
							<td>
								<button type="button" class="btn btn-primary" onclick="requestPay()">결제하기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	</div>
	
	<hr/>
	<div id="result" style="width:600px; margin:200px auto; float:center; align:center;">
		<img src="<%= request.getContextPath() %>/fileSave/order-success.png" width="600" height="400">
		<div style="width:600px; margin:20px auto; align:center;">
			<button style="float:left; margin-left:180px; width:20%;" type="button" class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/main/main">메인으로 가기</button>
			<button style="float:right; margin-right: 180px; width:15%;" type="button" class="btn btn-info" onclick="location.href='<%=request.getContextPath()%>/order/list">주문목록</button>
		</div>
	</div>
	
		


<script>
	// 상품
	var amount = 100;
	var name = 'kms';
	var total = ${total};
	console.log("total:::",total);
	var productName = '${orderWaitProduct.productName}';
	console.log("productName:::",productName);
	var productCode = ${orderWaitProduct.productCode};
	console.log("productCode:::",productCode);
	var category = '${productCategory}';
	console.log("category:::",category);
	
	var amount = ${total};
	var email = '${user.email}';
	var username = '${user.name}';
	var tel = ${user.phoneNum};
	var addr = '${user.roadAddress}';
	var postcode = ${user.postcode};
			
	function requestPay() {
		IMP.request_pay({
			pg : 'nice',
			pay_method : 'card', //card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(휴대폰소액결제)
			merchant_uid : 'merchant_' + new Date().getTime(), //상점에서 관리하시는 고유 주문번호를 전달
			name : '제품주문',
			amount : amount,
			buyer_email : email,
			buyer_name : username,
			buyer_tel : tel,
			buyer_addr : addr,
			buyer_postcode : postcode
		},
				function(rsp) {
					var rsp2 = {
							imp_uid : rsp.imp_uid,
							merchant_uid : rsp.merchant_uid,
							pay_method : rsp.pay_method,
							name : rsp.name,
							status : rsp.status,
							email : rsp.buyer_email,
						}
					console.log(JSON.stringify(rsp2));
					if (rsp.success) {
						//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
						console.log("rsp: ", rsp);
						var json = "?json=" + encodeURI(JSON.stringify(rsp2));
						
						
						jQuery.ajax({
							url : "/final_project/order/payment"+json+"&total="+total+"&productName=" +productName+"&productCode="+productCode+"&category="+category,
							type : 'GET',
							dataType : 'text'		
 						}).done(function(data) {
							<%-- document.getElementById("result").innerHTML='<img src="<%= request.getContextPath() %>/fileSave/order_product_information.png" width="920" height="40">'; --%>
						});
					} else {
						var msg = '결제에 실패하였습니다.';
						msg += '에러내용 : ' + rsp.error_msg;

						alert(msg);
					}
				});
	}
	
	
</script>
</body>
</html>