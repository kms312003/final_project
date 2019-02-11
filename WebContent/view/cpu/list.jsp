<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->

<body>

	<!-- 리스트 목록 검색 -->
	<div class="container" style="margin-top:30px; margin-bottom:30px;">
		<form name="search_list" method="post" action="" target="product_list">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td width="20%">제조사</td>
						<td width="80%">
							<%-- <c:forEach var="productCompanys" items="${productCompanys}">
								<label class="checkbox-inline"><input type="checkbox" value="${productCompanys}">${productCompanys}</label>
							</c:forEach> --%>
							<label class="checkbox-inline"><input type="checkbox" value="INTEL_7">인텔 7세대</label>
							<label class="checkbox-inline"><input type="checkbox" value="INTEL_8">인텔 8세대</label>
							<label class="checkbox-inline"><input type="checkbox" value="INTEL_9">인텔 9세대</label>
							<label class="checkbox-inline"><input type="checkbox" value="RYZEN_5">인텔 5</label>
							<label class="checkbox-inline"><input type="checkbox" value="RYZEN_7">인텔 7</label>
						</td>
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
						<td width="20%">키워드 검색</td>
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
			<table id="sorting" class="table table-bordered" style="cursor:pointer;" onclick="location.href=''">
				<tbody>
					<tr>
						<td id="date" class="active">신제품순</td>
						<td id="lowprice" class="active">낮은가격순</td>
						<td id="highprice"class="active">높은가격순</td>
						<td id="productName"class="active">상품명순</td>
						<td id="productComany" class="active">제조사순</td>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${count == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">등록된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${count != 0}">
				<c:forEach var="cpu" items="${cpuList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/AMD 라이젠 5 2600(피나클 릿지).jpg" class="img-responsive" style="width: 100%" alt="Image"></a>
							</div>
							<div class="" style="text-align: center;">${cpu.productName}</div>
							<br/>
							<div class="">${cpu.price} 원</div>
							<br/>
							<div class="center">
								<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/'">
									장바구니
								</button>
								<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/'">
									관심상품
								</button>
								<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/'">
									결제하기
								</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		
	</div>
</body>
</html>