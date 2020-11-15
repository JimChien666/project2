<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CreateOrder</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
<script>
window.onload = function() {
	ShowCartInfo(0);
}

function ShowCartInfo(productId){
	
	if(productId!=0){
		var cartNum = document.getElementById("qty"+productId).value;
	}else{
		var cartNum = 0;
	}
	queryString="?productId=" + productId + "&cartNum=" + cartNum;
	var xhr = new XMLHttpRequest();
	xhr.open("Get", "<c:url value='/cart/AddCart'/>" + queryString , true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var responseData = xhr.responseText;
		var cartList = JSON.parse(responseData);
		var num = cartList.length;
		var total = 0;
		for(var i=0; i < cartList.length; i++){
			total += cartList[i].price * cartList[i].discount * cartList[i].quantity;
			}
		document.getElementById("shippingInfo").innerHTML="共<span style='color:red;'>"+num+"</span>項商品,金額為<span style='color:red;'>" + total + "</span>元"
		}
	
	}
}

function fillUpData(){
	var checkBox = document.getElementById("sameOrderData");
	var shippingName = document.getElementById("recipientName");
	var shippingTel = document.getElementById("recipientTel");
	var shippingAddress = document.getElementById("recipientAddress");
	if (checkBox.checked){
		shippingName.value=document.getElementById("buyerName").value;
		shippingTel.value=document.getElementById("buyerTel").value;
		shippingAddress.value=document.getElementById("buyerAddress").value;
	}
	else{
		shippingName.value="";
		shippingTel.value="";
		shippingAddress.value="";
	}
}
</script>
</head>
<body>
	<jsp:include page="../public/top.jsp" />
	<div align='center'>
		<h3>立即結帳</h3>
		<hr>
		<div id="buyerInfo">
			<form:form action="CreateOrder.controller" method="POST" modelAttribute="order">
				<h4>購買人資訊</h4>
				訂購人:<form:input id="buyerName" path="buyerName" value="${LoginOK.name}"/>${errors.buyerName}<br/>
				訂購人電話:<form:input id="buyerTel" path="buyerTel" value="${LoginOK.tel}"/>${errors.buyerTel}<br/>
				購買人地址:<form:input id="buyerAddress" path="buyerAddress" value="${LoginOK.address}"/>${errors.buyerAddress}<br/>
				<h4>收件人資訊</h4>
				<input id="sameOrderData" type="checkbox" name="" value="false" onclick='fillUpData()'>同購買人資訊<br/>
				收件人:<form:input id="recipientName" path="recipientName" value=""/>${errors.recipientName}<br/>
				收件人電話:<form:input id="recipientTel" path="recipientTel" value=""/>${errors.recipientTel}<br/>
				收件人地址:<form:input id="recipientAddress" path="recipientAddress" value=""/>${errors.recipientAddress}<br/>
				<form:button value="Send">確定下單</form:button>
				${errors.createOrderError}
			</form:form>
		</div>
		<div id="shippingInfo"></div>
	</div>
</body>
</html>