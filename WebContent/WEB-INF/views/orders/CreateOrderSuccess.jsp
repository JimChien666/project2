<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<script>
function goToShop(){
	window.location.href = "<c:url value='/product/ProductList' />";
}
function goToOrderPage(){
	window.location.href = "<c:url value='/order/OrderList' />";
}
</script>
<jsp:include page="../fragments/links.jsp" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<jsp:include page="../fragments/headerArea.jsp" />
	
    
    <!-- shopping-cart-area start -->
        <div class="cart-main-area pt-95 pb-100">
            <div class="container">
                <h3 class="page-title">下單成功</h3>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                        <form action="#">
                            <div class="table-content table-responsive">
                                <table>
									<thead>
										<tr>
											<th>訂單編號</th><th>訂單日期</th><th>訂單狀態</th><th>總價</th>
										</tr>
									<tr>
										<td align="center">${order.id}</td><td align="center">${order.createdAtString}</td><td align="center">${order.status}</td><td align="center">${order.total}</td>
									</tr>
								</table>
								<table>
									<tr>
										<th>商品圖片</th><th>商品編號</th><th>商品名稱</th><th>商品數量</th><th>商品總價</th>
									</tr>
									<c:forEach items="${orderItems}" var="orderItem" varStatus="id">
									<tr>
										<td align="center"><img width='60' height='80' src="<c:url value='/product/getProductImage' />?productId=${orderItem.productId}"></td><td align="center">${orderItem.productId}</td><td align="center">${orderItem.productName}</td><td align="center">${orderItem.quantity}</td><td align="center">${orderItem.discountPrice*orderItem.quantity}</td>
									</tr>
									</c:forEach>
								</table>
                            </div>
                            
                        </form>
                       
                        </div>
                    </div>
                    <div class="row">
                                <div class="col-lg-12">
                                    <div class="cart-shiping-update-wrapper">
                                        <div class="cart-shiping-update">
                                            <a href="<c:url value='/product/ProductList' />">繼續購物</a>
                                            <a href="#" onclick='goToOrderPage()'>查看我的訂單</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                </div>
            </div>

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>