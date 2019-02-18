<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<body>
	<div class="container" style="margin-top:30px; margin-bottom:30px;">
	<form name="order_basket" method="post" action="">
		<input type="hidden" name="" value="">
		<table width="920" border="0" align="center" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td width="230" valign="top">
						<img src="<%= request.getContextPath() %>/fileSave/order_title_cart.gif" width="920" height="85">
					</td>
			</tbody>
		</table>
		
		<table class="table" width="920">
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
						<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/main/paymentAll'">
							전체상품구매하기
						</button>
						<button	type="button" class="btn btn-success" onclick="location.href='<%=request.getContextPath()%>/main/payment'">
							선택상품구매하기
						</button>
						<button	id="" type="button" class="btn btn-info" onclick="location.href='javascript:history.back()'">
							쇼핑계속하기
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
</body>
</html>