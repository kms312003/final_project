<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body>
aa
	<div class="container" style="margin-bottom:">
	aa
		<div class="row">
			<c:if test="${count == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">등록된 숙소가 없습니다.</div>
			</c:if>

			<c:if test="${count != 0}">
				<c:forEach var="house" items="${houseList}">
					<div class="col-sm-4" style="padding:10px;">
						<div>
							<div class="panel-header">
								<img src="<%=request.getContextPath()%>/fileSave/${house.image}" class="img-responsive" style="width: 100%"
									alt="Image">
							</div>
							<div class="panel-body" style="text-align: center;">${house.name}</div>
							<div class="left-box">${house.price} 원</div>
							<div class="right-box">
								<c:if test="${check_in == null || check_out == null}">
									<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/houseModel2/roomDetail?houseId=${house.houseId}'">
										숙소보기
									</button>
								</c:if>
								<c:if test="${check_in != null && check_out != null}">
									<button	type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/houseModel2/roomDetail?houseId=${house.houseId}&check_in=${ check_in }&check_out=${ check_out }'">
										숙소보기
									</button>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>

</body>
</html>
