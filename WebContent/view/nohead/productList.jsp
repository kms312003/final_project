<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function show_pic(val){
	parent.show_pic(val);
}

//reset option
function reset_opt_cpu(name, price){
	parent.change_opt_cpu(name, price);
	parent.subtract_cpu_price();
}

function reset_opt_mainboard(name, price){
	parent.change_opt_mainboard(name, price);
	parent.subtract_mainboard_price();
}

function reset_opt_graphic(name, price){
	parent.change_opt_graphic(name, price);
	parent.subtract_graphic_price();
}

function reset_opt_ssd(name, price){
	parent.change_opt_ssd(name, price);
	parent.subtract_ssd_price();
}

function reset_opt_hdd(name, price){
	parent.change_opt_hdd(name, price);
	parent.subtract_hdd_price();
}

function reset_opt_power(name, price){
	parent.change_opt_power(name, price);
	parent.subtract_power_price();
}

function reset_opt_ram(name, price){
	parent.change_opt_ram(name, price);
	parent.subtract_ram_price();
}




//change option
function change_opt_cpu(name, price){
	parent.change_opt_cpu(name, price);
}

function change_opt_ram(name, price){
	parent.change_opt_ram(name, price);
}

function change_opt_mainboard(name, price){
	parent.change_opt_mainboard(name, price);
}

function change_opt_ram(name, price){
	parent.change_opt_ram(name, price);
}

function change_opt_graphic(name, price){
	parent.change_opt_graphic(name, price);
}

function change_opt_ssd(name, price){
	parent.change_opt_ssd(name, price);
}

function change_opt_hdd(name, price){
	parent.change_opt_hdd(name, price);
}

function change_opt_power(name, price){
	parent.change_opt_power(name, price);
}
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<title></title>
<body topmargin="0" leftmargin="0">
<div class="container">
	<table class="table" cellspacing="0" align="right">
	<thead>
		<tr>
			<th>부품명</th>
			<th>변경가격</th>
			<th>구분</th>
	</thead>
	
	<tbody>
		
		<c:if test="${countCpu != 0}">
			<tr height="18">
			<td>
				<b><span onmouseover="parent.view_spec('','optview')">
					<b>not included CPU</b>[click for changing option] -></span></b>
			</td>
			<td>0</td>
			<td><a href="javascript:reset_opt_cpu('','0')">None</a>
			</tr>
			
			<c:forEach var="cpu" items="${cpuList }">
			<tr>
				<td>${countCpu}<span onmouseover="show_pic('${cpu.filename}')">${cpu.productName }</span></td>
				<td>${cpu.price }</td>
				<td><button type="button" onclick="change_opt_cpu('${cpu.productName}','${cpu.price }')">change</button></td>
			</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${countMainBoard != 0}">
			<tr height="18">
			<td>
				<b><span onmouseover="parent.view_spec('','optview')">
					<b>not included MAINBOARD</b>[click for changing option] -></span></b>
			</td>
			<td>0</td>
			<td><a href="javascript:reset_opt_mainboard('','0')">None</a>
			</tr>
		
			<c:forEach var="mainboard" items="${mainboardList }">
			<tr>
				<td><span onmouseover="show_pic('${mainboard.filename}')">${mainboard.productName }</span></td>
				<td>${mainboard.price }</td>
				<td><button type="button" onclick="change_opt_mainboard('${mainboard.productName}','${mainboard.price }')">change</button></td>
			</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${countGraphic != 0}">
			<tr height="18">
			<td>
				<b><span onmouseover="parent.view_spec('','optview')">
					<b>not included GRAPHIC</b>[click for changing option] -></span></b>
			</td>
			<td>0</td>
			<td><a href="javascript:reset_opt_graphic('','0')">None</a>
			</tr>
			
			<c:forEach var="graphic" items="${graphicList }">
			<tr>
				<td><span onmouseover="show_pic('${graphic.filename}')">${graphic.productName }</span></td>
				<td>${graphic.price }</td>
				<td><button type="button" onclick="change_opt_graphic('${graphic.productName}','${graphic.price }')">change</button></td>
			</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${countHDD != 0}">
			<tr height="18">
			<td>
				<b><span onmouseover="parent.view_spec('','optview')">
					<b>not included HARD DISC</b>[click for changing option] -></span></b>
			</td>
			<td>0</td>
			<td><a href="javascript:reset_opt_hdd('','0')">None</a>
			</tr>
		
			<c:forEach var="hdd" items="${hddList }">
			<tr>
				<td><span onmouseover="show_pic('${hdd.filename}')">${hdd.productName }</span></td>
				<td>${hdd.price }</td>
				<td><button type="button" onclick="change_opt_hdd('${hdd.productName}','${hdd.price }')">change</button></td>
			</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${countPower != 0}">
			<tr height="18">
			<td>
				<b><span onmouseover="parent.view_spec('','optview')">
					<b>not included POWER</b>[click for changing option] -></span></b>
			</td>
			<td>0</td>
			<td><a href="javascript:reset_opt_power('','0')">None</a>
			</tr>
		
			<c:forEach var="power" items="${powerList }">
			<tr>
				<td><span onmouseover="show_pic('${power.filename}')">${power.productName }</span></td>
				<td>${power.price }</td>
				<td><button type="button" onclick="change_opt_power('${power.productName}','${power.price }')">change</button></td>
			</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${countRam != 0}">
			<tr height="18">
			<td>
				<b><span onmouseover="parent.view_spec('','optview')">
					<b>not included RAM</b>[click for changing option] -></span></b>
			</td>
			<td>0</td>
			<td><a href="javascript:reset_opt_ram('','0')">None</a>
			</tr>
			
			<c:forEach var="ram" items="${ramList }">
			<tr>
				<td><span onmouseover="show_pic('${ram.filename}')">${ram.productName }</span></td>
				<td>${ram.price }</td>
				<td><button type="button" onclick="change_opt_ram('${ram.productName}','${ram.price }')">change</button></td>
			</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${countSSD != 0}">
			<tr height="18">
			<td>
				<b><span onmouseover="parent.view_spec('','optview')">
					<b>not included SSD</b>[click for changing option] -></span></b>
			</td>
			<td>0</td>
			<td><a href="javascript:reset_opt_ssd('','0')">None</a>
			</tr>
		
			<c:forEach var="ssd" items="${ssdList }">
			<tr>
				<td><span onmouseover="show_pic('${ssd.filename}')">${ssd.productName }</span></td>
				<td>${ssd.price }</td>
				<td><button type="button" onclick="change_opt_ssd('${ssd.productName}','${ssd.price }')">change</button></td>
			</tr>
			</c:forEach>
		</c:if>
		
	</tbody>
	</table>
	</div>
</body>
</html>