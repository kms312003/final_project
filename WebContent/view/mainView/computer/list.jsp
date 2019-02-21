<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
<script>	

	<%-- function toBasket() {
	   location.href='<%=request.getContextPath()%>/basket/write?id=${id}&productCode=${productCode }&price=${price }&productName=${productName }&amount=1';  
	}

	function toPayment() {
	   location.href='<%=request.getContextPath()%>/order/write?id=${id}&productCode=${productCode }&price=${price }&productName=${productName }&amount=1';
	} --%>
	
	function sortItem(orderby, sql) {
		
		console.log("sqlsql:::::",sql);
		
		$.ajax({
			url:"/final_project/computer/prolist?orderby="+orderby+"&sql="+encodeURI(sql),
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
	<!-- 리스트 목록 검색 -->
	<div class="container" style="margin-top:30px; margin-bottom:30px;">
		<form name="search_list" method="post" action="<%= request.getContextPath() %>/computer/listPost?category=${category}">
			<input type="hidden" name="sql" value="${sql}">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td width="20%">Cpu 검색</td>
						<td width="80%"><input type="text" name="cpu" value="${cpu}" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td width="20%">메인보드 검색</td>
						<td width="80%"><input type="text" name="mainBoard" value="${mainBoard}" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td width="20%">메모리 검색</td>
						<td width="80%"><input type="text" name="ram" value="${ram}" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td width="20%">그래픽카드 검색</td>
						<td width="80%"><input type="text" name="vga" value="${vga}" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td width="20%">하드디스크 검색</td>
						<td width="80%"><input type="text" name="hdd" value="${hdd}" size="100" maxlength="50"></td>
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
			<table class="table table-bordered" style="cursor:pointer;" onclick="location.href=''">
				<tbody>
					<tr>
						<td class="active" onclick="sortItem('1', document.search_list.sql.value)">신제품순</td>
						<td class="active" onclick="sortItem('2', document.search_list.sql.value)">낮은가격순</td>
						<td class="active" onclick="sortItem('3', document.search_list.sql.value)">높은가격순</td>
						<td class="active" onclick="sortItem('4', document.search_list.sql.value)">상품명순</td>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- 리스트 목록 보기 -->
		<div id="productList"></div>
	</div>
</body>
</html>