<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
.row.content {
	height: 700px
}

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
	/* margin-top:50px; */
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>
</head>
<body>
		<div class="col-sm-9 text-left">

			<h1 style="text-align: center;">Cpu 관리</h1>
			<hr />
			<div class="w3-container">
				<div style="text-align: right;">
					<span class="w3-left"> (전체글 : ${ count }) </span>
					<span class="w3-right"><input type="button" class="btn btn-default" value="Create" onclick="window.location='<%= request.getContextPath() %>/admincpu/write'"></span> 
				</div>

				<table class="table table-bordered table-hover" width="1200">
					<tr class="w3-grey">
						<td align="center" width="50">번호</td>
						<td align="center" width="100">제품코드</td>
						<td align="center" width="200">제품명</td>
						<td align="center" width="100">제조회사</td>
						<td align="center" width="150">브랜드분류</td>
						<td align="center" width="150">소켓구분</td>
						<td align="center" width="100">코어</td>
						<td align="center" width="150">가격</td>
						<td align="center" width="150">제품 입력일</td>
						<td align="center" width="50">Actions</td>
					</tr>

					<c:if test="${cpuList == null}">
						<tr>
							<td colspan="10" align="center">등록된 제품이 없습니다.</td>
						</tr>
					</c:if>

					<c:set var="number" value="${number}" />
					<%-- <c:if test="${cpuList != null}"> --%>
						<c:forEach var="cpu" items="${cpuList}">
							<fmt:formatDate var="regDate" value="${cpu.regDate}" pattern="yyyy.MM.dd" />

							<tr>
								<td align="center" width="50">${number}</td>
								<c:set var="number" value="${number-1}" />
								<td align="center" width="100">${ cpu.productCode}</td>
								<td align="center" width="200">${ cpu.productName}</td>
								<td align="center" width="100">${ cpu.productCompany }</td>
								<td align="center" width="150">${ cpu.brand }</td>
								<td align="center" width="150">${ cpu.socket }</td>
								<td align="center" width="100">${ cpu.core }</td>
								<td align="center" width="150">${ cpu.price }</td>
								<td align="center" width="150">${ regDate }</td>
								<td align="center" width="150"><a
									href="<%= request.getContextPath() %>/admincpu/update?id=${ cpu.id }"
									class="btn btn-sm btn-default" data-toggle="tooltip"
									data-placement="top" title="수정"> <em class="fa fa-pencil"></em>
								</a> <a
									href="<%= request.getContextPath() %>/admincpu/detail?id=${ cpu.id }"
									class="btn btn-sm btn-default" data-toggle="tooltip"
									data-placement="top" title="상세보기 "> <em
										class="glyphicon glyphicon-list-alt"></em>
								</a></td>
							</tr>
						</c:forEach>
					<%-- </c:if> --%>
				</table>

				<div class="w3-center">
					<ul class="pager">
					<c:if test="${startPage > bottomLine }">
						<li class="previous"><a href="<%= request.getContextPath() %>/admincpu/list?pageNum=${  startPage - bottomLine }">previous</a></li>
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<li>
							<a href="<%= request.getContextPath() %>/admincpu/list?pageNum=${ i }">
								<c:if test="${i == currentPage }">
									<font color="blue">${i}</font>
								</c:if> 
								<c:if test="${i != currentPage }">
				     				${i}
				     			</c:if>
							</a>
						</li>		
					</c:forEach>
					
					<c:if test="${endPage < pageCount }">
						<li class="next"><a href="<%= request.getContextPath() %>/admincpu/list?pageNum=${ startPage + bottomLine }">next</a></li>
					</c:if>
					</ul>
				</div>

			</div>
		</div>

	</div>
</div>

</body>
<!-- jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
	var IMP = window.IMP;
	IMP.init('imp20438571');
</script>
<button onclick="requestPay()">결제하기</button>
<script>
	function requestPay() {
		IMP.request_pay({
			pg : 'nice',
			pay_method : 'card', //card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(휴대폰소액결제)
			merchant_uid : 'merchant_' + new Date().getTime(), //상점에서 관리하시는 고유 주문번호를 전달
			name : '주문명:결제테스트',
			amount : 100,
			buyer_email : 'iamport@siot.do',
			buyer_name : '구매자이름',
			buyer_tel : '010-1234-5678',
			buyer_addr : '서울특별시 강남구 삼성동',
			buyer_postcode : '123-456'
		},
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

						jQuery.ajax({
							url : "/final_project/payment/"+json, //cross-domain error가 발생하지 않도록 주의해주세요
							type : 'GET',
							dataType : 'text'
							/* data : {
								imp_uid : rsp.imp_uid,
								merchant_uid : rsp.merchant_uid,
								pay_method : rsp.pay_method,
								card_quota : rsp.card_quota,
								name : rsp.name,
								amount : rsp.amount,
								status : rsp.status,
								buyer_email : rsp.buyer_email

							//기타 필요한 데이터가 있으면 추가 전달
							}
 */						}).done(function(data) {
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
				});
	}
</script>
<div id="test"></div>
</html>