<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
<script>
	function sortItem(orderby) {
		$.ajax({
			url:"/final_project/main/cpu/prolist?orderby="+orderby,
			type: "GET"
		}).done(function(data){
			console.log(data);	
			$("#productList").html(data);
		})
	} 
</script>

<body>
<script>
	sortItem("1");
</script>
	<!-- 리스트 목록 검색 -->
	<div class="container" style="margin-top:30px; margin-bottom:30px;">
		<form name="search_list" method="post" action="" target="product_list">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td width="20%">제조사</td>
						<td width="80%">
							<label class="checkbox-inline"><input type="checkbox" value="INTEL_7">인텔 7세대</label>
							<label class="checkbox-inline"><input type="checkbox" value="INTEL_8">인텔 8세대</label>
							<label class="checkbox-inline"><input type="checkbox" value="INTEL_9">인텔 9세대</label>
							<label class="checkbox-inline"><input type="checkbox" value="RYZEN_5">라이젠 5</label>
							<label class="checkbox-inline"><input type="checkbox" value="RYZEN_7">라이젠 7</label>
							<script>
								$('.checkbox-inline').css('margin-right', '50px');
							</script>
						</td>
					</tr>
					<tr>
						<td width="20%">브랜드 검색</td>
						<td width="80%"><input type="text" value="" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td width="20%">소켓 검색</td>
						<td width="80%"><input type="text" value="" size="100" maxlength="50"></td>
					</tr>
					<tr>
						<td width="20%">코어 검색</td>
						<td width="80%"><input type="text" value="" size="100" maxlength="50"></td>
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
	
	<div class="container">
		<!-- 리스트 목록 sorting -->
		<div class="row">
			<table class="table table-bordered" style="cursor:pointer;">
				<tbody>
					<tr>
						<td class="active" onclick="sortItem('1')">신제품순</td>
						<td class="active" onclick="sortItem('2')">낮은가격순</td>
						<td class="active" onclick="sortItem('3')">높은가격순</td>
						<td class="active" onclick="sortItem('4')">상품명순</td>
						<td class="active" onclick="sortItem('5')">제조사순</td>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- 리스트 ajax로 솔팅 -->
		<div id="productList"></div>
		
	</div>
</body>
</html>