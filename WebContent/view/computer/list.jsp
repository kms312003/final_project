<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<body>
	<div class="container">
		<div class="row">
			<c:if test="${count == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">등록된 숙소가 없습니다.</div>
			</c:if>

			<c:if test="${count != 0}">
				<c:forEach var="computer" items="${computerList}">
					<div class="col-sm-3" style="padding:10px;">
						<div>
							<div class="panel-header">
								<img src="<%=request.getContextPath()%>/fileSave/${computer.filename}" class="img-responsive" style="width: 100%" alt="Image">
							</div>
							<div class="panel-body" style="text-align: center;">${computer.productName}</div>
							<div class="left-box">${computer.price} 원</div>
							<div class="right-box">
								<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/'">
									장바구니
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