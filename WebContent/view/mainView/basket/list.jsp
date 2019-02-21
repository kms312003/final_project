<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script>
	$(document).ready(function(){

		// 전체 체크/해제
		$('#checkAll').click(function(){
			$('.checkbox').prop('checked', this.checked);
		});
	});
	
		var basketIds = new Array();
		var line = "";
		var productId = 0;
		var productName = ""; 
		var price = 0;
		var count = 0;
		var productCode = "";
		
		function checkOrder() {
			$("input[name=basket]:checked").each(function(){
				var checked = $(this).is(":checked");
				var checkCnt = $(this).length;
				
				if(checked) {
					console.log("value:::", this.value);
					console.log("checkCnt:::", checkCnt);
					line += this.value+", ";
				}
				
				console.log("line:::", line);
				var id = $(this).val();
				console.log("id:::", id);
				
				for(var i = 0; i < checkCnt; i++) {
					basketIds[i] = id;
				}
				
				if(checked && checkCnt == 1) {
					productId = ${id};
					console.log("productId::::::::::", productId);
					productName = document.getElementById('productName').innerHTML;
					price = document.getElementById('price').innerHTML;
					count = document.getElementById('count').innerHTML;
					productCode = document.getElementById('productCode').innerHTML;
				} 
				console.log("basketIds:::", basketIds);
			});
			
			location.href='<%=request.getContextPath()%>/order/order?productId=' 
					+ productId + '&productName=' + productName +'&price=' + price + '&count=' 
					+ count +'&productCode=' + productCode +'&basketIds=' + basketIds;
		}
	 
	// 선택삭제
	function deletePro(id, index) {
		 var chk = $("input[name=basket]")[index].checked;
		
		 if(chk == true) {
			 location.href='<%=request.getContextPath()%>/basket/delete?id='+id;
			 alert('삭제가 완료되었습니다.');
		 } else {
			 alert('삭제할 장바구니를 선택해주세요.');
		 }
	}
</script>
<body>
	<div class="container" style="margin-top:30px; margin-bottom:30px;">
	<form name="order_basket" method="post" action="">
		<input type="hidden" name="" value="">
		<table width="1140" border="0" align="center" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td width="230" valign="top">
						<img src="<%= request.getContextPath() %>/fileSave/order_title_cart.gif" width="1140" height="90">
					</td>
			</tbody>
		</table>
		
		<table class="table" width="920">
			<tbody>
				<tr>
					<td align="center" width="100"><strong>전체선택</strong>
					<br><br><input id="checkAll" type="checkbox" name="allCheck"></td>
					<td align="center" width="100"><strong>카테고리</strong></td>
					<td align="center" width="100"><strong>상품번호</strong></td>
					<td align="left" width="270"><strong>상품명</strong></td>
					<td align="center" width="50"><strong>수량</strong></td>
					<td align="center" width="200"><strong>가격</strong></td>
					<td align="center" width="200"><strong>합계</strong></td>
					<td align="center" width="50"><strong>삭제</strong></td>
					
				</tr>
				<c:forEach var="basket" items="${basketList}"  varStatus="stat">
					<tr>
						<td align="center" width="100">
							<input class="checkbox" type="checkbox" name="basket" value="${basket.id}"   id="${stat.index }">
						</td>
						<td id="productCategory" align="center" width="100">${basket.productCategory}</td>
						<td id="productCode" align="center" width="100">${basket.productCode}</td>
						<td id="productName" align="left" width="270">${basket.productName}</td>
						<td id="count" align="center" width="50">${basket.count}</td>
						<td id="price" align="center" width="200">${basket.price}</td>
						<td align="center" width="200">${basket.total}</td>
						<td align="center" width="50">
							<button id="delete" type="button" class="btn btn-danger"
									onclick="deletePro('${basket.id}', '${stat.index }')">삭제
							</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8" align="right">
						<!-- <button type="button" class="btn btn-primary" onclick="allOrder()">
							전체상품구매하기
						</button> -->
						<button type="button" class="btn btn-primary" onclick="checkOrder()">
							선택상품구매하기
						</button>
						<button	type="button" class="btn btn-info" onclick="location.href='javascript:history.back()'">
							쇼핑계속하기
						</button>
						<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#basketModal">
					  		전체삭제
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	

<!-- Modal -->
<div class="modal fade" id="basketModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
        <button type="button" class="btn btn-primary" onclick="window.location='<%=request.getContextPath()%>/basket/deleteAll?email=${email}'">Delete</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>