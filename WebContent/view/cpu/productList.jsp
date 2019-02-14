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

			<%-- <c:if test="${count != 0}"> --%>
				<c:forEach var="cpu" items="${cpuList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
							
							=======${cpuList}=======
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
			<%-- </c:if> --%>
		</div>
</body>
</html>