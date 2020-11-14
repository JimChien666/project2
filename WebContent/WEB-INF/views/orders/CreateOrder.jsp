<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CreateOrder</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
<script>
function fillUpData(){
	var shippingName = document.getElementById("shippingName");
	var shippingTel = document.getElementById("shippingTel");
	var shippingAddress = document.getElementById("shippingAddress");
	shippingName.value=document.getElementById("orderName").value;
	shippingTel.value=document.getElementById("orderTel").value;
	shippingAddress.value=document.getElementById("orderAddress").value;
}
</script>
</head>
<body>
	<jsp:include page="../public/top.jsp" />
	<div align='center'>
		<h3>立即結帳</h3>
		<hr>
		<div id="buyerInfo">
			<form action="order/CreateOrder.controller">
				<h4>購買人資訊</h4>
				訂購人:<input type="text" id="orderName" name="orderName" value="${LoginOK.name}"/><br/>
				訂購人電話:<input type="text" id="orderTel" name="orderTel" value="${LoginOK.tel}"/><br/>
				購買人地址:<input type="text" id="orderAddress" name="orderAddress" value="${LoginOK.address}"/><br/>
				<h4>收件人資訊</h4>
				<input type="checkbox" name="" value="" onclick='fillUpData()'>同購買人資訊<br/>
				收件人:<input type="text" id="shippingName" name="shippingName" value=""/><br/>
				收件人電話:<input type="text" id="shippingTel" name="shippinTel" value=""/><br/>
				收件人地址:<input type="text" id="shippingAddress" name="shippinAddress" value=""/><br/>
				<button type="submit" name="send" value="send">確定下單</button>
			</form>
		</div>
		<div id="shippingInfo"></div>
	</div>
</body>
</html>