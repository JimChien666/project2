<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
th, td{
width: 200px;
align: left;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>

	function showOrderInfo(orderId){
		var order = document.getElementById("orderInfo"+orderId);
		console.log(order.style.display);
		if(order.style.display=="none"){
			order.style.display = "";
		}else{
			order.style.display = "none";
		}
		
	}
</script>
<body>
<jsp:include page="../public/top.jsp" />
<div align='center'>
	<h2>Hello orderList</h2>
	<c:forEach items="${orderList}" var="order" varStatus="id">
		<table>
			<tr>
				<th>訂單編號</th>
				<th>訂單日期</th>
				<th>訂單狀態</th>
				<th>訂單總額</th>
			</tr>
			<tr>
				<td>${order.id}</td>
				<td>${order.createdAt}</td>
				<td>${order.status}</td>
				<td>${order.total}</td>
				<td><button onclick="showOrderInfo(${order.id})">訂單詳細</button></td>
			</tr>
			</table>
			<table id="orderInfo${order.id}" style="display: none;">
					<tr>
						<th>商品編號</th>
						<th>商品圖片</th>
						<th>商品名稱</th>
						<th>商品單價</th>
						<th>購買數量</th>
						<th>價格</th>
						<th>狀態</th>
					</tr>
					<c:forEach items="${order.orderItems}" var="orderItem" varStatus="id">
					<tr>
						<td>${orderItem.productId}</td>
						<td><img width='60' height='80' src="<c:url value='/product/getProductImage' />?productId=${orderItem.productId}"></td>
						<td>${orderItem.productName}</td>
						<td>${orderItem.price*orderItem.discount}</td>
						<td>${orderItem.quantity}</td>
						<td>${orderItem.price*orderItem.discount*orderItem.quantity}</td>
						<td>${orderItem.status}</td>
					</tr>
				</c:forEach>
			</table>
	<hr>
	</c:forEach>
	<div id='navigation' style='height:60px;'></div>
	</div>
</body>
</html>