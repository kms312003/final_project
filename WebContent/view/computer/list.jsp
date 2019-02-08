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
	<%-- <div class="adverts">
		<div class="container">
			<div class="row">

				<div class="col-lg-3 advert_col">
					
					<!-- Advert Item -->

					<div class="advert d-flex flex-row align-items-center justify-content-start">
						<div class="advert_content">
							<div class="advert_title"><a href="#">Trends 2018</a></div>
							<div class="advert_text">Lorem ipsum dolor sit amet, consectetur.</div>
						</div>
						<div class="ml-auto"><div class="advert_image"><img src="<%=request.getContextPath()%>/fileSave/AMD 라이젠 5 2600(피나클 릿지).jpg" alt=""></div></div>
					</div>
				</div>
				
			</div>
		</div>
	</div> --%>
	<div class="adverts">
	<div class="container">
		<div class="row">
			<c:if test="${count == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">등록된 숙소가 없습니다.</div>
			</c:if>

			<c:if test="${count != 0}">
				<c:forEach var="computer" items="${computerList}">
					<div class="col-sm-3" style="padding:10px;">
						<div advert_content>
							<div class="panel-header">
								<img src="<%=request.getContextPath()%>/fileSave/${computer.filename}" class="img-responsive" style="width: 100%" alt="Image">
							</div>
							<div class="advert_title" style="text-align: center;">${computer.productName}</div>
							<div class="advert_text">${computer.price} 원</div>
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
	</div>
</body>
</html>