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
					<td align="center" width="50"><strong>선택</strong></td>
					<td align="center" width="100"><strong>상품번호</strong></td>
					<td align="left" width="300"><strong>상품명</strong></td>
					<td align="center" width="50"><strong>수량</strong></td>
					<td align="center" width="200"><strong>가격</strong></td>
					<td align="center" width="220"><strong>합계</strong></td>
				</tr>
				<tr>
					<td align="center" width="50">
						<input type="checkbox" name="basket" value="">
					</td>
					</td>
					<td align="center" width="100">130365</td>
					<td align="left" width="300">cpu</td>
					<td align="center" width="50">3</td>
					<td align="center" width="200">267,000원</td>
					<td align="center" width="220">total price</td>
				</tr>
				<tr>
					<td colspan="6" align="right">
						<button	id="" type="button" class="btn btn-info" onclick="location.href='javascript:history.back()'">
							이전페이지
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
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
								<input type="text" name="phoneNumber" value="${user.phoneNumber}" size="30">
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
								<input type="text" name="userName" value="" size="30">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								주문자와 동일 <input type="checkbox" name="orderUserEqual">
							</td>
						</tr>
						<tr>
							<td align="center" width="30%">휴대전화</td>
							<td align="left" width="70%">
								<input type="text" name="email" value="" size="30">
							</td>
						</tr>
						<tr>
							<td align="center" width="30%">주소</td>
							<td align="left" width="70%">
								<table>
									<tbody>
										<tr>
											<td>
												<input type="text" name="phoneNumber" value="" size="10">
												<button>우편번호검색</button>
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" name="phoneNumber" value="" size="30"> 기본주소
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" name="phoneNumber" value="" size="30"> 나머지 주소
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div style="width:200px; float:right; margin-top:50px;">
				<table class="table" style="padding:20px;">
					<tbody>
						<tr>
							<td><strong>총 결제 금액</strong></td>
						</tr>
						<tr>
							<td>300000 원</td>
						</tr>
						<tr>
							<td>
								<button onclick="requestPay()">결제하기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	</div>


<script>
	// 상품
	var amount = 100;
	var name = "";
	/* buyer_email : ${user.email},
	buyer_name : ${user.name},
	buyer_tel : ${user.phoneNumber},
	buyer_addr : ${user.roadAddress},
	buyer_postcode : ${user.postcode} */
			
	function requestPay() {
		IMP.request_pay({
			pg : 'nice',
			pay_method : 'card', //card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(휴대폰소액결제)
			merchant_uid : 'merchant_' + new Date().getTime(), //상점에서 관리하시는 고유 주문번호를 전달
			name : '주문명',
			amount : amount,
			buyer_email : 'kms@naver.com',
			buyer_name : 'kms',
			buyer_tel : '010',
			buyer_addr : 'abc',
			buyer_postcode : '123123'
		}),
				function(rsp) {
					var rsp2 = {
							imp_uid : rsp.imp_uid,
							merchant_uid : rsp.merchant_uid,
							pay_method : rsp.pay_method,
							card_quota : rsp.card_quota,
							name : rsp.name,
							amount : rsp.amount,
							status : rsp.status,
							buyer_email : rsp.buyer_email
						}
					console.log(JSON.stringify(rsp2));
					if (rsp.success) {
						//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
						console.log("rsp: ", rsp);
						var json = "?json=" + encodeURI(JSON.stringify(rsp2));
						/* var */
						jQuery.ajax({
							url : "/final_project/payment/"+json+, //cross-domain error가 발생하지 않도록 주의해주세요
							type : 'GET',
							dataType : 'text'
					
							//기타 필요한 데이터가 있으면 추가 전달
						}).done(function(data) {
							console.log("aaa");
							alert("data=======", data);
							
							document.getElementById("test").innerHTML=data;
							//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
							/* if (everythings_fine) {
								var msg = '결제가 완료되었습니다.';
								msg += '\n고유ID : ' + rsp.imp_uid;
								msg += '\n상점 거래ID : ' + rsp.merchant_uid;
								msg += '\결제 금액 : ' + rsp.paid_amount;
								msg += '카드 승인번호 : ' + rsp.apply_num;

								alert(msg);
							} else {
								//[3] 아직 제대로 결제가 되지 않았습니다.
								//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
							} */
						});
					} else {
						var msg = '결제에 실패하였습니다.';
						msg += '에러내용 : ' + rsp.error_msg;

						alert(msg);
					}
				};
	}
</script>
</body>
</html>