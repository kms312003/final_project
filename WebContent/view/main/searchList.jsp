<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<body>
	<div class="container" style="margin-bottom:">
		
		<!-- Logo cpu -->
		<div class="col-lg-2 col-sm-3 col-3 order-1">
			<div class="logo_container">
				<div class="logo_strapline"><a href="<%= request.getContextPath() %>/main/main">Result of CPU</a></div>
			</div>
		</div>
		
		<!-- cpu list -->
		<div class="container">
	
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${countCpu == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">검색된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${countCpu != 0}">
				<c:forEach var="cpu" items="${cpuList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/${cpu.filename}" class="img-responsive" style="width: 100%" alt="Image"></a>
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
	
	<!-- cpu list 끝-->
	
	<br/>
	

		<!-- Logo graphic -->
		<div class="col-lg-2 col-sm-3 col-3 order-1">
			<div class="logo_container">
				<div class="logo_strapline"><a href="<%= request.getContextPath() %>/main/main">Result of GRAPHIC</a></div>
			</div>
		</div>
		
		<!-- graphic list -->
		<div class="container">
	
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${countGraphic == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">검색된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${countGraphic != 0}">
				<c:forEach var="graphic" items="${graphicList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/${graphic.filename}" class="img-responsive" style="width: 100%" alt="Image"></a>
							</div>
							<div class="" style="text-align: center;">${graphic.productName}</div>
							<br/>
							<div class="">${graphic.price} 원</div>
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
	
	<!-- graphic list 끝-->
	
	<br/>
	
	<!-- Logo hdd -->
		<div class="col-lg-2 col-sm-3 col-3 order-1">
			<div class="logo_container">
				<div class="logo_strapline"><a href="<%= request.getContextPath() %>/main/main">Result of HDD</a></div>
			</div>
		</div>
		
		<!-- hdd list -->
		<div class="container">
	
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${countHDD == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">검색된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${countHDD != 0}">
				<c:forEach var="hdd" items="${hddList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/${hdd.filename}" class="img-responsive" style="width: 100%" alt="Image"></a>
							</div>
							<div class="" style="text-align: center;">${hdd.productName}</div>
							<br/>
							<div class="">${hdd.price} 원</div>
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
	
	<!-- hdd list 끝-->
	
	<br/>
	
	<!-- Logo mainboard -->
		<div class="col-lg-2 col-sm-3 col-3 order-1">
			<div class="logo_container">
				<div class="logo_strapline"><a href="<%= request.getContextPath() %>/main/main">Result of MAINBOARD</a></div>
			</div>
		</div>
		
		<!-- mainboard list -->
		<div class="container">
	
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${countMainBoard == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">검색된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${countMainBoard != 0}">
				<c:forEach var="mainboard" items="${mainboardList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/${mainboard.filename}" class="img-responsive" style="width: 100%" alt="Image"></a>
							</div>
							<div class="" style="text-align: center;">${mainboard.productName}</div>
							<br/>
							<div class="">${mainboard.price} 원</div>
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
	
	<!-- mainboard list 끝-->
	
	<br/>
	
	<!-- Logo power -->
		<div class="col-lg-2 col-sm-3 col-3 order-1">
			<div class="logo_container">
				<div class="logo_strapline"><a href="<%= request.getContextPath() %>/main/main">Result of POWER</a></div>
			</div>
		</div>
		
		<!-- power list -->
		<div class="container">
	
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${countPower == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">검색된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${countPower != 0}">
				<c:forEach var="power" items="${powerList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/${power.filename}" class="img-responsive" style="width: 100%" alt="Image"></a>
							</div>
							<div class="" style="text-align: center;">${power.productName}</div>
							<br/>
							<div class="">${power.price} 원</div>
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
	
	<!-- power list 끝-->
	
	<br/>
	
	<!-- Logo ram -->
		<div class="col-lg-2 col-sm-3 col-3 order-1">
			<div class="logo_container">
				<div class="logo_strapline"><a href="<%= request.getContextPath() %>/main/main">Result of RAM</a></div>
			</div>
		</div>
		
		<!-- ram list -->
		<div class="container">

		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${countRam == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">검색된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${countRam != 0}">
				<c:forEach var="ram" items="${ramList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/${ram.filename}" class="img-responsive" style="width: 100%" alt="Image"></a>
							</div>
							<div class="" style="text-align: center;">${ram.productName}</div>
							<br/>
							<div class="">${ram.price} 원</div>
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
	
	<!-- ram list 끝-->
	
	<br/>
	
	<!-- Logo ssd -->
		<div class="col-lg-2 col-sm-3 col-3 order-1">
			<div class="logo_container">
				<div class="logo_strapline"><a href="<%= request.getContextPath() %>/main/main">Result of SSD</a></div>
			</div>
		</div>
		
		<!-- ssd list -->
		<div class="container">
	
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${countSSD == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">검색된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${countSSD != 0}">
				<c:forEach var="ssd" items="${ssdList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/${ssd.filename}" class="img-responsive" style="width: 100%" alt="Image"></a>
							</div>
							<div class="" style="text-align: center;">${ssd.productName}</div>
							<br/>
							<div class="">${ssd.price} 원</div>
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
	
	<!-- ssd list 끝-->
	
	<br/>
	
	<!-- Logo computer -->
		<div class="col-lg-2 col-sm-3 col-3 order-1">
			<div class="logo_container">
				<div class="logo_strapline"><a href="<%= request.getContextPath() %>/main/main">Result of COMPUTER</a></div>
			</div>
		</div>
		
		<!-- computer list -->
		<div class="container">
	
		<!-- 리스트 목록 보기 -->
		<div class="row">
			<c:if test="${countComputer == 0}">
				<div style="border: 1px solid #f5f5f5; text-align:center;">검색된 제품이 없습니다.</div>
			</c:if>

			<c:if test="${countComputer != 0}">
				<c:forEach var="computer" items="${computerList}">
					<div class="col-sm-3" style="padding:20px; border:solid 1px lightgray;">
						<div class="advert_content">
							<div class="panel-header">
								<a href=""><img src="<%=request.getContextPath()%>/fileSave/${computer.filename}" class="img-responsive" style="width: 100%" alt="Image"></a>
							</div>
							<div class="" style="text-align: center;">${computer.productName}</div>
							<br/>
							<div class="">${computer.price} 원</div>
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
	
	<!-- computer list 끝-->
	
	<br/>
	</div>

</body>
</html>
