<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-9 text-left">
    
    <h1 style="text-align:center;">Graphic 관리</h1>     
      <hr/>
      <div class="w3-container">
      	<div style="text-align:right;">
			<span class="w3-left"> (전체글 : ${ count }) </span>
			<span class="w3-right"><input type="button" class="btn btn-default" value="제품등록" onclick="window.location='<%= request.getContextPath() %>/admin/graphic/write'"></span>
		</div>

		<table class="table table-bordered table-hover" width="1200">
			<tr class="w3-grey">
				<td align="center" width="50">번호</td>
				<td align="center" width="100">제품코드</td>
				<td align="center" width="200">제품명</td>
				<td align="center" width="100">제조회사</td>
				<td align="center" width="150">NVIDIA 칩셋</td>
				<td align="center" width="150">칩셋 그룹</td>
				<td align="center" width="100">메모리 용량</td>
				<td align="center" width="150">가격</td>
				<td align="center" width="150">제품 입력일</td>
				<td align="center" width="50">Actions</td>
			</tr>
			
			<c:if test="${graphicList == null}">
				<tr >
					<td colspan="10" align="center">등록된 제품이 없습니다.</td>
				</tr>
			</c:if>
			
			<c:set var = "number" value="${number}"/>
			<c:if test="${graphicList != null}">
				<c:forEach var="graphic" items="${graphicList}">
				<fmt:formatDate var="regDate" value="${graphic.regDate}" pattern="yyyy.MM.dd"/>
				
				<tr>
					<td align="center" width="50">${number}</td>
					<c:set var="number" value="${number-1}"/>
					<td align="center" width="100">${ graphic.productCode}</td>
					<td align="center" width="200">${ graphic.productName}</td>
					<td align="center" width="100">${ graphic.productCompany }</td>
					<td align="center" width="150">${ graphic.nvidiaChipSet }</td>
					<td align="center" width="150">${ graphic.chipSetGroup }</td>
					<td align="center" width="100">${ graphic.memoryCapacity }</td>
					<td align="center" width="150">${ graphic.price }</td>
					<td align="center" width="150">${ regDate }</td>
					<td align="center" width="150">
			        	<a href="<%= request.getContextPath() %>/admin/graphic/update?id=${ graphic.id }" class="btn btn-sm btn-default" data-toggle="tooltip" data-placement="top" title="수정">
			            	<em class="fa fa-pencil"></em>
			        	</a>
			        	<a href="<%= request.getContextPath() %>/admin/graphic/detail?id=${ graphic.id }" class="btn btn-sm btn-default" data-toggle="tooltip" data-placement="top" title="상세보기 ">
			            	<em class="glyphicon glyphicon-list-alt"></em>
			        	</a>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>

		<div class="w3-center">
			<c:if test="${startPage > bottomLine }">
				<a href="<%= request.getContextPath() %>/admin/graphic/list?pageNum=${  startPage - bottomLine }">[이전]</a>
			</c:if>
		
			<ul class="pagination">
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li>
					<a href="<%= request.getContextPath() %>/admin/graphic/list?pageNum=${ i }">
						<c:if test="${i == currentPage }">
							<font color="blue">${i}</font>
						</c:if>
						<c:if test="${i != currentPage }">
					     	${i}
					     </c:if>
					</a>
				</li>
			</c:forEach>
			</ul>
			
			<c:if test="${endPage < pageCount }">
				<a href="<%= request.getContextPath() %>/admin/graphic/list?pageNum=${ startPage + bottomLine }">[다음]</a>
			</c:if>
		</div>

		</div>
	</div>
</body>
</html>