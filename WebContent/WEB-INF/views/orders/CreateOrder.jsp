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
	addToCartList();
}

//若productId為零,後端會直接回傳購物車列表
function addToCartList(){
	queryString="?productId=0&cartNum=0";
	var xhr = new XMLHttpRequest();
	xhr.open("Get", "<c:url value='/cart/AddCart'/>" + queryString , true);
	xhr.send();
	var imageURL = "<c:url value='/product/getProductImage' />";
	var productsInfo = "<c:url value='/product/productsInfo/productsPath' />";
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var responseData = xhr.responseText;
		var cartList = JSON.parse(responseData);
		var num = cartList.length;
		var total = 0;
		var content = "<table class='table'>"+
        				"<thead>"+
        				"<tr>"+
            			"<th class='width-1'>商品名稱</th>"+
			            "<th class='width-2'>單價</th>"+
			            "<th class='width-3'>數量</th>"+
			            "<th class='width-4'>小計</th>"+
			       		"</tr>"+
			   			"</thead>"+
			    	    "<tbody>";
			    	    
		for(var i=0; i < cartList.length; i++){
			content += '<tr>'+
			            '<td>'+
			            '<div class="o-pro-dec">'+
			                '<p>'+cartList[i].productName+'</p>'+
			            '</div>'+
			        '</td>'+
			        '<td>'+
			            '<div class="o-pro-price">'+
			                '<p>$'+cartList[i].discountPrice+'</p>'+
			            '</div>'+
			        '</td>'+
			        '<td>'+
			            '<div class="o-pro-qty">'+
			                '<p>'+cartList[i].quantity+'</p>'+
			            '</div>'+
			        '</td>'+
			        '<td>'+
			            '<div class="o-pro-subtotal">'+
			                '<p>$'+cartList[i].discountPrice*cartList[i].quantity+'</p>'+
			            '</div>'+
			        '</td>'+
			    '</tr>'
			
			total+=cartList[i].discountPrice*cartList[i].quantity;
		}
		content +='</tbody>'+
			            '<tfoot>'+
			        '<tr>'+
			            '<td colspan="3">總價</td>'+
			            '<td colspan="1">$' + total + '</td>'+
			       '</tr>'+
			    '</tfoot>'+
				'</table>'
		
        
		
		
		document.getElementById("cartList").innerHTML = content;

	}
	
}
}

function fillUpData(){
	var checkBox = document.getElementById("sameOrderData");
	var shippingName = document.getElementById("recipientName");
	var shippingTel = document.getElementById("recipientTel");
	var shippingAddress = document.getElementById("recipientAddress");
	var shippingEmail = document.getElementById("recipientEmail");
	if (checkBox.checked){
		shippingName.value=document.getElementById("buyerName").value;
		shippingTel.value=document.getElementById("buyerTel").value;
		shippingAddress.value=document.getElementById("buyerAddress").value;
		shippingEmail.value=document.getElementById("buyerEmail").value;
	}
	else{
		shippingName.value="";
		shippingTel.value="";
		shippingAddress.value="";
		shippingEmail.value="";
	}
}

</script>
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
	<jsp:include page="../fragments/headerArea.jsp" />
	
	<div class="breadcrumb-area pt-95 pb-95 bg-img" style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
            <div class="container">
                <div class="breadcrumb-content text-center">
                    <h2>訂單確認</h2>
                    <ul>
                        <li><a href="index.html">首頁</a></li>
                        <li class="active">訂單確認</li>
                    </ul>
                </div>
            </div>
        </div>
	
	<form:form action="CreateOrder.controller" method="POST" modelAttribute="order">
	<div class="checkout-area pt-95 pb-70">
            <div class="container">
                <h3 class="page-title">結帳</h3>
                <div class="row">
                    <div class="col-lg-9">
                        <div class="checkout-wrapper">
                            <div id="faq" class="panel-group">
                                
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title"><span>1</span> <a data-toggle="collapse" data-parent="#faq" href="#payment-1">購買人資訊</a></h5>
                                    </div>
                                    <div id="payment-1" class="panel-collapse collapse show">
                                        <div class="panel-body">
                                            <div class="billing-information-wrapper">
                                                <div class="row">
                                                
														
													
                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="billing-info">
                                                            <label>訂購人</label>
                                                            <form:input id="buyerName" path="buyerName" value="${LoginOK.name}"/>${errors.buyerName}
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="billing-info">
                                                            <label>訂購人電話</label>
                                                            <form:input id="buyerTel" path="buyerTel" value="${LoginOK.tel}"/>${errors.buyerTel}
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="billing-info">
                                                            <label>訂購人地址</label>
                                                            <form:input id="buyerAddress" path="buyerAddress" value="${LoginOK.address}"/>${errors.buyerAddress}
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="billing-info">
                                                            <label>訂購人Email</label>
                                                            <form:input id="buyerEmail" path="buyerEmail" value="${LoginOK.email}"/>${errors.buyerEmail}
                                                        </div>
                                                    </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title"><span>2</span> <a data-toggle="collapse" data-parent="#faq" href="#payment-2">收件人資訊</a></h5>
                                    </div>
                                    <div id="payment-2" class="panel-collapse collapse ">
                                        <div class="panel-body">
														
                                            <div class="shipping-information-wrapper">
                                                <div class="row">
                                                
														
													
                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="billing-info">
                                                            <label>收件人</label>
                                                            <form:input id="recipientName" path="recipientName" value=""/>${errors.recipientName}
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="billing-info">
                                                            <label>收件人電話</label>
                                                            <form:input id="recipientTel" path="recipientTel" value=""/>${errors.recipientTel}
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="billing-info">
                                                            <label>收件人地址</label>
                                                            <form:input id="recipientAddress" path="recipientAddress" value=""/>${errors.recipientAddress}
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6">
                                                        <div class="billing-info">
                                                            <label>收件人Email</label>
                                                            <form:input id="recipientEmail" path="recipientEmail" value=""/>${errors.recipientEmail}
                                                        </div>
                                                    </div>
                                            	</div>
                                                <div class="ship-wrapper">
                                                    <div class="single-ship">
                                                        <input id="sameOrderData" type="checkbox" value="false" onclick='fillUpData()'>
                                                        <label>同購買人資訊</label>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title"><span>3</span> <a data-toggle="collapse" data-parent="#faq" href="#payment-3">訂單商品</a></h5>
                                    </div>
                                    <div id="payment-3" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div class="order-review-wrapper">
                                                <div class="order-review">
                                                    <div class="table-responsive" id="cartList">
                                                        
                                                    </div>
                                                    <div class="billing-back-btn">
                                                        <span>
                                                            訂單有誤?
                                                            <a href="<c:url value='/cart/CartList' />"> 回購物車</a>

                                                        </span>
                                                        <div class="billing-btn">
                                                            <form:button value="Send">確定下單</form:button>
                                                            ${errors.createOrderError }
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</form:form>
	
	
	
	
<jsp:include page="../fragments/footerArea.jsp" />
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>