<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->
  <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
<script>	

	<%-- function toBasket() {
	   location.href='<%=request.getContextPath()%>/basket/write?id=${id}&productCode=${productCode }&price=${price }&productName=${productName }&amount=1';  
	}

	function toPayment() {
	   location.href='<%=request.getContextPath()%>/order/write?id=${id}&productCode=${productCode }&price=${price }&productName=${productName }&amount=1';
	} --%>
	
	function sortItem(orderby, sql) {
		
		console.log(sql);
		
		$.ajax({
			url:"/final_project/ssd/prolist?orderby="+orderby+"&sql="+encodeURI(sql),
			type: "GET"
		}).done(function(data){
			$("#productList").html(data);
		})
	}
</script>

<body>
<script>
	 sortItem("1", ' ');
</script>
	<h2 style="margin:50px; width:100%; text-align:center;">SSD 목록</h2>
	<!-- 리스트 목록 검색 -->
	<div class="container" style="margin-top:30px; margin-bottom:30px;">
		<form name="search_list" method="post" action="<%= request.getContextPath() %>/cpu/listPost">
			<input type="hidden" name="sql" value="${sql}">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td width="20%">제조사</td>
						<td width="80%">
							<label class="checkbox-inline"><input type="checkbox" name="productCompany" value="SAMSUNG" <c:if test="${fn:contains(line, 'SAMSUNG')}">checked</c:if>>SAMSUNG</label>
							<label class="checkbox-inline"><input type="checkbox" name="productCompany" value="MICRON" <c:if test="${fn:contains(line, 'MICRON')}">checked</c:if>>MICRON</label>
							<label class="checkbox-inline"><input type="checkbox" name="productCompany" value="ADATA" <c:if test="${fn:contains(line, 'ADATA')}">checked</c:if>>ADATA</label>
							<script>
								$('.checkbox-inline').css('margin-right', '50px');
							</script>
						</td>
					</tr>
					<tr>
						<td width="20%">제품명 검색</td>
						<td width="80%"><input type="text" name="productName" value="${productName}" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td width="20%">디스크 타입 검색</td>
						<td width="80%"><input type="text" name="diskType" value="${diskType}" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td width="20%">인터페이스 검색</td>
						<td width="80%"><input type="text" name="interFace" value="${interFace}" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<button style="margin:20px; width:200px;" class="btn btn-primary" type="reset" value="초기화">초기화</button>
							<button style="margin:20px; width:200px;" class="btn btn-primary" type="submit" name="검색">검색</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
	<div class="container" style="margin-bottom:200px;">
		<!-- 리스트 목록 sorting -->
		<div class="row">
			<table class="table table-bordered" style="cursor:pointer;">
				<tbody>
					<tr>
						<td class="active" onclick="sortItem('1', document.search_list.sql.value)">신제품순</td>
						<td class="active" onclick="sortItem('2', document.search_list.sql.value)">낮은가격순</td>
						<td class="active" onclick="sortItem('3', document.search_list.sql.value)">높은가격순</td>
						<td class="active" onclick="sortItem('4', document.search_list.sql.value)">상품명순</td>
						<td class="active" onclick="sortItem('5', document.search_list.sql.value)">제조사순</td>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- 리스트 목록 보기 -->
		<div id="productList"></div>
	</div>
</body>
</html>