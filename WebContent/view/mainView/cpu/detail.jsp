<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function toBasket() {
   var amount = document.getElementById("amount").value;
   console.log(amount);
   alert(amount);
   
   location.href='<%=request.getContextPath()%>/basket/write?id=${id}&productCode=${productCode }&price=${price }&productName=${productName }&amount='+amount;  
}

function toPayment() {
   var amount = document.getElementById("amount").value;
   console.log(amount);
   alert(amount);
   
   location.href='<%=request.getContextPath()%>/order/write?id=${id}&productCode=${productCode }&price=${price }&productName=${productName }&amount='+amount;
}



</script>

<title>Insert title here</title>
</head>
<body>
      <!-- Detail 시작 -->
      <div align="center">
      <table width="980" border="0" align="center" cellpadding="0" cellspacing="0" style="border:1px solid #d5d5d5">
      <tbody>
         <!-- 사진 -->
         <tr>
         <td align="center" style="padding:20px">
         <table class="table table-bordered" width="380" border="0" align="center" cellpadding="0" cellspacing="2" valign="top">
         <tbody>
            <tr>
            <td with="360" height="281">
               <div align="center">
                  <a onfocus="blur();" href="<%=request.getContextPath()%>/fileSave/${filename}">
                  <img src="<%=request.getContextPath()%>/fileSave/${filename}" class="img-responsive" style="width: 360" alt="Image">
                  
                  </a>
               </div>
            </td>
            </tr>
         </tbody>
         </table>
         </td>
         
         
         
         <!-- 상세내용 -->
         <td style="padding:20px 35px 20px 20px">
         <table class="table table-bordered" width="100%" border="0" cellpadding="0" cellspacing="0">
         <tbody>
            <tr>
            <td>
               <font size="3" face="굴림">
                  <b>${productName}</b>
               </font>
            </td>
            </tr>
         </tbody>
         </table>
         
         <table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tbody>
            <tr>
            <td style="padding:7px 0 12px 0; border-bottom:1px solid #dddddd">
               "제품코드 : ${productCode } / 제조회사 : ${productCompany } / 브랜드분류 : ${brand } / 소켓구분 : ${socket } / 코어 : ${core } 코어 / 쓰레드 : ${thread } 개/ 동작속도 : ${clockSpeed } GHz/ 설계전력(TDP) : ${tdp } W"
            </td>
            </tr>
         </tbody>
         </table>
         <br>
         <table width="100%" cellspacing="0" cellpadding="0" align="center" border="0">
         <tbody>
            <tr>
            <td width="552" valign="top">
               <table cellspacing="0" cellpadding="0" width="99%" align="center" border="0">
               <tbody>
                  <tr>
                     <td width="110" height="25">상품코드번호</td><td width="15"> : </td><td>${productCode }</td>
                  </tr>
                  <tr>
                     <td height="25"><b>판매가격</b></td><td>:</td>
                     <td><img src="<%=request.getContextPath()%>/fileSave/w.gif" width="15" height="14" align="absmiddle" alt="Image"><font color="black"><b>${price }</b></font>원</td>
                  </tr>
                  <tr>
                     <td height="25">제조사</td><td>:</td><td>${productCompany }</td>
                  </tr>
                  <tr>
                     <td height="25">제품등록일</td><td>:</td><td><fmt:formatDate value="${regDate}" dateStyle="full" /></td>
                  </tr>
                  <tr>
                     <td height="25">배송 및 기타</td><td>:</td><td>평균배송일 1일</td>
                  </tr>
                  <tr>
                     <td height="25">주문수량</td><td>:</td><td><input type="text" name="amount" value="1" size="2" id="amount" required></td>
                  </tr>
                  <tr>
                     <td height="25">찜하기</td><td>:</td><td><a href=""><img src="<%=request.getContextPath()%>/fileSave/but_wish.gif" width="71" height="32"></a></td>
                  </tr>
               </tbody>
               </table>
            </td>
            </tr>
         </tbody>
         </table>
         <!-- detail2 코딩 -->
         <table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tbody>
            <tr>
            <td style="padding:7px 0 0 0; border-bottom:1px solid #dddddd">&nbsp;</td>
            </tr>
         </tbody>
         </table>
         <br>
         
         <table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tbody>
            <tr>
            <td align="center">
               <button type="button" class="btn btn-primary" onclick="toBasket()">
               장바구니
               </button>
               &nbsp
               <button type="button" class="btn btn-primary" onclick="toPayment()">
               결제하기
               </button>
            </td>
            </tr>
         </tbody>
         </table>

         </td>
         <br>
         </tr>
         
      </tbody>
      </table>
      <p/>
      ${detailPic1 }
      </div>
      
      &nbsp
      &nbsp
      &nbsp
   
</body>
</html>