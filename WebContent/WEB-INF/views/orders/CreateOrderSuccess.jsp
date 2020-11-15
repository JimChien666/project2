<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
th, td{
width: 200px;
align: right;
}
</style>
<script>
function goToShop(){
	window.location.href = "<c:url value='/product/ProductList' />";
}
function goToOrderPage(){
	window.location.href = "<c:url value='/product/ProductList' />";
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../public/top.jsp" />
	<div align='center'>
		<h3>下單成功！</h3>
		<hr>
		<div id="orderDetail">
		<table>
			<tr>
				<th>訂單編號</th><th>訂單日期</th><th>訂單狀態</th><th>總價</th>
			</tr>
			<tr>
				<td>${order.id}</td><td>${order.createdAt}</td><td>${order.status}</td><td>${order.total}</td>
			</tr>
		</table>
		</div>
		<div id="orderItemDetail">
			<table>
				<tr>
					<th>商品圖片</th><th>商品編號</th><th>商品名稱</th><th>商品數量</th><th>商品總價</th>
				</tr>
				<c:forEach items="${orderItems}" var="orderItem" varStatus="id">
				<tr>
					<td><img width='60' height='80' src="<c:url value='/product/getProductImage' />?productId=${orderItem.productId}"></td><td>${orderItem.productId}</td><td>${orderItem.productName}</td><td>${orderItem.quantity}</td><td>${orderItem.price*orderItem.quantity*orderItem.discount}</td>
				</tr>
				</c:forEach>
			</table>
			<div><button onclick='goToShop()'>繼續購物</button><button onclick='goToOrderPage()'>查看我的訂單</button></div>
		</div>
	</div>
</body>
</html>