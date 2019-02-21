<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${count == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">등록된 제품이 없습니다.</div>
			</c:if>

			<c:forEach var="ssd" items="${ssdList}">
				<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
					<div class="advert_content">
						<div class="panel-header">
							<a href="<%=request.getContextPath()%>/ssd/detail?id=${ssd.id}"><img src="<%=request.getContextPath()%>/fileSave/AMD 라이젠 5 2600(피나클 릿지).jpg" class="img-responsive" style="width: 100%" alt="Image"></a>
						</div>
						<div class="" style="text-align: center;">${ssd.productName}</div>
						<br/>
						<div class="">${ssd.price} 원</div>
						<br/>
						<div class="center">
							<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/basket/write?id=${ssd.id}&productCode=${ssd.productCode }&price=${ssd.price }&productName=${ssd.productName }&amount=1'">
								장바구니
							</button>
							<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/'">
								관심상품
							</button>
							<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/order/write?id=${ssd.id}&productCode=${ssd.productCode }&price=${ssd.price }&productName=${ssd.productName }&amount=1'">
								결제하기
							</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
</body>
</html>