<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<script>

function view_spec(code, obj){
	if(code !=''){
		eval(obj).location.replace("");
	}
	
}

function show_pic(val){
	var optview = document.getElementById("optview");
	optview.src="/final_project/fileSave/"+val;
}



var sum = 0;
var cpu_price = 0;
var mainboard_price = 0;
var graphic_price = 0;
var hdd_price = 0;
var power_price = 0;
var ram_price = 0;
var ssd_price = 0;

//빼기
function subtract_total_price(price){
	sum = parseInt(sum)-parseInt(price);
}

function subtract_cpu_price(){
	sum = parseInt(sum)-parseInt(cpu_price);
	document.getElementById('total_price').innerHTML = sum;
}

function subtract_mainboard_price(){
	sum = parseInt(sum)-parseInt(mainboard_price);
	document.getElementById('total_price').innerHTML = sum;
}

function subtract_graphic_price(){
	sum = parseInt(sum)-parseInt(graphic_price);
	document.getElementById('total_price').innerHTML = sum;
}

function subtract_ssd_price(){
	sum = parseInt(sum)-parseInt(ssd_price);
	document.getElementById('total_price').innerHTML = sum;
}

function subtract_hdd_price(){
	sum = parseInt(sum)-parseInt(hdd_price);
	document.getElementById('total_price').innerHTML = sum;
}

function subtract_power_price(){
	sum = parseInt(sum)-parseInt(power_price);
	document.getElementById('total_price').innerHTML = sum;
}

function subtract_ram_price(){
	sum = parseInt(sum)-parseInt(ram_price);
	document.getElementById('total_price').innerHTML = sum;
}

//더하기
function sum_total_price(price){
	sum = parseInt(sum)+parseInt(price);
	if(sum == 0){
		document.getElementById('total_price').innerHTML = 0;
	}else{
		document.getElementById('total_price').innerHTML = sum;
	}
	
}

//바꾸기
function change_opt_cpu(name, price){
	document.getElementById('cpu_name').innerHTML = name;
	document.getElementById('cpu_price').innerHTML = price;
	var price = document.getElementById('cpu_price').childNodes[0].nodeValue;
	subtract_total_price(cpu_price);
	cpu_price = price;
	sum_total_price(price);
	
}

function change_opt_mainboard(name, price){
	document.getElementById('mainboard_name').innerHTML = name;
	document.getElementById('mainboard_price').innerHTML = price;
	var price = document.getElementById('mainboard_price').childNodes[0].nodeValue;
	subtract_total_price(mainboard_price);
	mainboard_price = price;
	sum_total_price(price);
}

function change_opt_ram(name, price){
	document.getElementById('ram_name').innerHTML = name;
	document.getElementById('ram_price').innerHTML = price;
	var price = document.getElementById('ram_price').childNodes[0].nodeValue;
	subtract_total_price(ram_price);
	ram_price = price;
	sum_total_price(price);
	
}

function change_opt_graphic(name, price){
	document.getElementById('graphic_name').innerHTML = name;
	document.getElementById('graphic_price').innerHTML = price;
	var price = document.getElementById('graphic_price').childNodes[0].nodeValue;
	subtract_total_price(graphic_price);
	graphic_price = price;
	sum_total_price(price);
}

function change_opt_ssd(name, price){
	document.getElementById('ssd_name').innerHTML = name;
	document.getElementById('ssd_price').innerHTML = price;
	var price = document.getElementById('ssd_price').childNodes[0].nodeValue;
	subtract_total_price(ssd_price);
	ssd_price = price;
	sum_total_price(price);
}

function change_opt_hdd(name, price){
	document.getElementById('hdd_name').innerHTML = name;
	document.getElementById('hdd_price').innerHTML = price;
	var price = document.getElementById('hdd_price').childNodes[0].nodeValue;
	subtract_total_price(hdd_price);
	hdd_price = price;
	sum_total_price(price);
}

function change_opt_power(name, price){
	document.getElementById('power_name').innerHTML = name;
	document.getElementById('power_price').innerHTML = price;
	var price = document.getElementById('power_price').childNodes[0].nodeValue;
	subtract_total_price(power_price);
	power_price = price;
	sum_total_price(price);
}

function change_total_price(){
	
}

</script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container" style="margin-bottom:">
	<table class="table" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr valign="top">
			<td width="470" bgcolor="#FFFFFF" style="padding-top:0" align="left">
			<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed" bgcolor="#FFFFFF">
				<thread>
					<tr>
						<th width="20" style="font-size:11;line-height:13px;">호환</th>
						<th width="75" style="font-size:11;line-height:13px;">분류</th>
						<th width="263" style="font-size:11;line-height:13px;">이름&가격</th>
						<th width="95" style="font-size:11;line-height:13px;">사양변경</th>
					</tr>
				</thread>
				<tbody>
					<tr>
						<td><img src="<%=request.getContextPath()%>/fileSave/dot.gif">
						<td style="font-size:11;;line-height:13px;">CPU</td>
						<td onmouseover="this.style.backgroundColor='#F9F9F9'" bgcolor="#FFFFFF">
							<table width="100%">
								<tbody>
									<tr>
										<td><span id="cpu_name"></span></td>
										<td nowrap align="right">
											<font color="red" size="2"><span id="cpu_price">0</span></font>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="middle">
							<a href="/final_project/diy/cpu"   target="list">
								<img src="<%=request.getContextPath()%>/fileSave/change.gif" align="absmiddle">
							</a>
						</td>
					</tr>
					
					<tr>
						<td><img src="<%=request.getContextPath()%>/fileSave/dot.gif">
						<td style="font-size:11;;line-height:13px;">MAIN BOARD</td>
						<td onmouseover="this.style.backgroundColor='#F9F9F9'" bgcolor="#FFFFFF">
							<table width="100%">
								<tbody>
									<tr>
										<td><span id="mainboard_name"></span></td>
										<td nowrap align="right">
											<font color="red" size="2"><span id="mainboard_price">0</span></font>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="middle">
							<a href="/final_project/diy/mainboard" target="list">
								<img src="<%=request.getContextPath()%>/fileSave/change.gif" align="absmiddle">
							</a>
						</td>
					</tr>
					
					<tr>
						<td><img src="<%=request.getContextPath()%>/fileSave/dot.gif">
						<td style="font-size:11;;line-height:13px;">RAM</td>
						<td onmouseover="this.style.backgroundColor='#F9F9F9'" bgcolor="#FFFFFF">
							<table width="100%">
								<tbody>
									<tr>
										<td><span id="ram_name"></span></td>
										<td nowrap align="right">
											<font color="red" size="2"><span id="ram_price">0</span></font>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="middle">
							<a href="/final_project/diy/ram" target="list">
								<img src="<%=request.getContextPath()%>/fileSave/change.gif" align="absmiddle">
							</a>
						</td>
					</tr>
					
					<tr>
						<td><img src="<%=request.getContextPath()%>/fileSave/dot.gif">
						<td style="font-size:11;;line-height:13px;">GRAPHIC CARD</td>
						<td onmouseover="this.style.backgroundColor='#F9F9F9'" bgcolor="#FFFFFF">
							<table width="100%">
								<tbody>
									<tr>
										<td><span id="graphic_name"></span></td>
										<td nowrap align="right">
											<font color="red" size="2"><span id="graphic_price">0</span></font>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="middle">
							<a href="/final_project/diy/graphic" target="list">
								<img src="<%=request.getContextPath()%>/fileSave/change.gif" align="absmiddle">
							</a>
						</td>
					</tr>
					
					<tr>
						<td><img src="<%=request.getContextPath()%>/fileSave/dot.gif">
						<td style="font-size:11;;line-height:13px;">SSD</td>
						<td onmouseover="this.style.backgroundColor='#F9F9F9'" bgcolor="#FFFFFF">
							<table width="100%">
								<tbody>
									<tr>
										<td><span id="ssd_name"></span></td>
										<td nowrap align="right">
											<font color="red" size="2"><span id="ssd_price">0</span></font>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="middle">
							<a href="/final_project/diy/ssd" target="list">
								<img src="<%=request.getContextPath()%>/fileSave/change.gif" align="absmiddle">
							</a>
						</td>
					</tr>
					
					<tr>
						<td><img src="<%=request.getContextPath()%>/fileSave/dot.gif">
						<td style="font-size:11;;line-height:13px;">HDD</td>
						<td onmouseover="this.style.backgroundColor='#F9F9F9'" bgcolor="#FFFFFF">
							<table width="100%">
								<tbody>
									<tr>
										<td><span id="hdd_name"></span></td>
										<td nowrap align="right">
											<font color="red" size="2"><span id="hdd_price">0</span></font>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="middle">
							<a href="/final_project/diy/hdd" target="list">
								<img src="<%=request.getContextPath()%>/fileSave/change.gif" align="absmiddle">
							</a>
						</td>
					</tr>
					
					<tr>
						<td><img src="<%=request.getContextPath()%>/fileSave/dot.gif">
						<td style="font-size:11;;line-height:13px;">POWER</td>
						<td onmouseover="this.style.backgroundColor='#F9F9F9'" bgcolor="#FFFFFF">
							<table width="100%">
								<tbody>
									<tr>
										<td><span id="power_name"></span></td>
										<td nowrap align="right">
											<font color="red" size="2"><span id="power_price">0</span></font>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="middle">
							<a href="/final_project/diy/power" target="list">
								<img src="<%=request.getContextPath()%>/fileSave/change.gif" align="absmiddle">
							</a>
						</td>
					</tr>
					<tr>
						<td><img src="<%=request.getContextPath()%>/fileSave/dot.gif">
						<td style="font-size:11;;line-height:13px;">Total Price</td>
						<td onmouseover="this.style.backgroundColor='#F9F9F9'" bgcolor="#FFFFFF">
							<table width="100%">
								<tbody>
									<tr>
										<td><font color="red" size="2"><span id="total_price">0</span></font></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td valign="middle">
							<button	type="button" onclick="toPayment()">
							결제하기
							</button>
						</td>
					</tr>
					<tr>
					<iframe width="470" id="optview" height="300" src="" align="left" allowtransparency="true" frameborder="0" framespacing="0" scrolling="no"></iframe>
					</tr>
				</tbody>
			</table>
			</td>
			<td rowspan="2" style="padding-top:5">
			<iframe id="optlist" width="470" height="600" src="" align="right" allowtransparency="true" frameborder="0" framespacing="0" scrolling="auto"  name="list"></iframe>
			</td>
	</tbody>
	</table>		
	</div>

</body>
</html>
