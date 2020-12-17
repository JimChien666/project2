<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車清單</title>

<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
<script>
window.onload = function() {
	addToCartList();
	addRecommendProduct();
}

function adder(productId){
	var count=document.getElementById("countnum"+ productId).value;
	var xhr = new XMLHttpRequest();
	var queryString = "?productId=" + productId;
	xhr.open("Get", "<c:url value='/cart/getProductQuantity'/>" + queryString , true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 ) {
			if (xhr.status == 200){
				var responseData = xhr.responseText;
				var quantity = JSON.parse(responseData);
				console.log(quantity);
				if (parseInt(count)+1<=quantity){
				count=parseInt(count)+1;
				document.getElementById("countnum"+productId).value=count;
				fixProductQuantity(productId, count);
				}else{
					document.getElementById("countnum"+productId).value=quantity;
					alert("庫存最多"+quantity+"個");
					
				}
			}
		}
	}
	
}
function minuser(productId){
	var count=document.getElementById("countnum"+productId).value;
	if(count<=1){
		count=1;
	}else{
		count=parseInt(count)-1;
	}	
	document.getElementById("countnum"+productId).value=count;
	fixProductQuantity(productId, count);
}

function fixProductQuantity(productId, count){
	var xhr = new XMLHttpRequest();
	var queryString = "?productId=" + productId + "&count=" + count;
	xhr.open("Get", "<c:url value='/cart/FixProductQuantity'/>" + queryString , true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 ) {
			if (xhr.status == 200){
	updateQuantityAndPrice();
			}
		}
	}
}

function deleteCartItem(productId){
	var xhr = new XMLHttpRequest();
	var queryString = "?productId=" + productId;
	xhr.open("Get", "<c:url value='/cart/DeleteCartItem'/>" + queryString, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 ) {
			if (xhr.status == 200){
	var parentObj = document.getElementById("tr" + productId).parentNode;
	parentObj.removeChild(document.getElementById("tr" + productId));
	updateQuantityAndPrice();
	addToCart(0);
			}
		}
	}
}

function clearCartItems(){
	var xhr = new XMLHttpRequest();

	xhr.open("Get", "<c:url value='/cart/ClaerCartItems'/>", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		addToCartList();
		updateQuantityAndPrice();
		addToCart(0);
	}
	
}

function updateQuantityAndPrice(){
	queryString="?productId=0&cartNum=0";
	var xhr = new XMLHttpRequest();
	xhr.open("Get", "<c:url value='/cart/AddCart'/>" + queryString , true);
	xhr.send();
	var imageURL = "<c:url value='/product/getProductImage' />";
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var responseData = xhr.responseText;
		var cartList = JSON.parse(responseData);
		var num = cartList.length;
		var total = 0;
		
		for(var i=0; i < cartList.length; i++){
			document.getElementById("subtotal" + cartList[i].productId).innerHTML = '$' + cartList[i].discountPrice * cartList[i].quantity;
			total+=cartList[i].discountPrice*cartList[i].quantity;
		}
		document.getElementById("total").innerHTML = total;
		document.getElementById("num").innerHTML = num;
	}
	
}
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
		var content = "<table>"+
        				"<thead>"+
        				"<tr>"+
            			"<th>圖片</th>"+
			            "<th>商品名稱</th>"+
			            "<th>單價</th>"+
			            "<th>數量</th>"+
			            "<th>小計</th>"+
			            "<th>操作</th>"+
			       		"</tr>"+
			   			"</thead>"+
			    	    "<tbody>";
		for(var i=0; i < cartList.length; i++){
			content +=
			'<tr id="tr' + cartList[i].productId + '">'+
            '<td class="product-thumbnail">'+
                '<a href="' + productsInfo.replace('productsPath', cartList[i].productId) + '"><img width="160" height="160" src="' + imageURL + '?productId=' + cartList[i].productId + '" alt=""></a>'+
            '</td>'+
            '<td class="product-name"><a href="' + productsInfo.replace('productsPath', cartList[i].productId) + '">'+cartList[i].productName+'</a></td>'+
            '<td class="product-price-cart"><span class="amount">$'+cartList[i].discountPrice+'</span></td>'+
            '<td class="product-quantity">'+
                '<div class="cart-plus-minus">'+
            	'<div class="dec qtybutton" onclick="minuser(' + cartList[i].productId + ')">-</div>'+
                    '<input class="cart-plus-minus-box" id="countnum'+ cartList[i].productId +'" type="text" name="qtybutton" value="' + cartList[i].quantity + '" onblur="adder(' + cartList[i].productId + ')">'+
                '<div class="inc qtybutton" onclick="adder(' + cartList[i].productId + ')">+</div>'+
                '</div>'+
            '</td>'+
            '<td class="product-subtotal" id="subtotal'+ cartList[i].productId + '">$'+cartList[i].discountPrice*cartList[i].quantity+'</td>'+
            '<td class="product-remove"><a href="#"  onclick="deleteCartItem('+cartList[i].productId+')"><i class="ti-trash"></i></a></td>'+
        '</tr>'
			total+=cartList[i].discountPrice*cartList[i].quantity;
		}
		content +="</tbody></table>"
		
        
		
		document.getElementById("cartList").innerHTML = content;

		var totalCountContent = "<span>共:   <span id='num'>"+num+"</span>項商品</span>"+
					        "<h5>總價:   $<span id='total'>" + total + "</span></h5>"+
					        "<a href='#' onclick='comfirmOrder()'>結帳去</a>"
		document.getElementById("totalCount").innerHTML = totalCountContent;
	}
	
}
}


function addRecommendProduct(){

	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/memberRecommandProducts.json' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 ) {
			if (xhr.status == 200){
				var responseData = xhr.responseText;
				displayRecommendProducts(responseData);   // 顯示讀取到的非文字性資料
			} else {
				alert(xhr.status);
			}
		}
	}
}

function displayRecommendProducts(responseData){
	var content = "";
	var products = JSON.parse(responseData);
	
	var imageURL = "<c:url value='/product/getProductImage' />";
	var productsInfo = "<c:url value='/product/productsInfo/productsPath' />";
	var salesInfo = "<c:url value='/product/salesInfo/salesInfoPath' />";
	content="";
	for(var i=0; i < products.length; i++){
		content +='<div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">'+
            '<div class="product-wrapper mb-10">'+
        '<div class="product-img">'+
            '<a href="'+productsInfo.replace("productsPath",products[i].id)+'">'+
                '<img src="' + imageURL + '?productId=' + products[i].id +'" alt="">'+
            '</a>'+
            '<div class="product-action">'+
                '<a title="Quick View" data-toggle="modal" data-target="#exampleModal" href="<a href="'+productsInfo.replace("productsPath",products[i].id)+'">'+
                    '<i class="ti-plus"></i>'+
                '</a>'+
                '<a title="Add To Cart" onclick="addToCart(' + products[i].id + ')"  href="#">'+
                    '<i class="ti-shopping-cart"></i>'+
                '</a>'+
            '</div>'+

        '</div>'+
        '<div class="product-content">'+
            '<h4><a href="<a href="'+productsInfo.replace("productsPath",products[i].id)+'">'+products[i].name+'</a></h4>'+
            '<div class="product-price">';
            content += '<span class="new">$'+products[i].discountPrice+' </span>';
			if(products[i].discount < 1){
				content += '<span class="old">$'+products[i].price+'</span>';
			}
		content += '</div>'+
        '</div>'+
    '</div>'+
'</div>'
	}
	document.getElementById("recommendProductShow").innerHTML = content;
}

function comfirmOrder(){
	var check = confirm("前往結帳");
	if (check){
		window.location.href = "<c:url value='/order/CreateOrder' />";
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
                <h2>購物車</h2>
                <ul>
                    <li><a href="index.html">首頁</a></li>
                    <li class="active">購物車</li>
                </ul>
            </div>
        </div>
    </div>
    
    <!-- shopping-cart-area start -->
        <div class="cart-main-area pt-95 pb-100">
        
            <div class="container">
                <h5>購物清單</h5>
                <span style="color: red;">${error}</span>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                        <form action="#">
                            <div class="table-content table-responsive" id="cartList">
                                
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="cart-shiping-update-wrapper">
                                        <div class="cart-shiping-update">
                                            <a href="<c:url value='/product/ProductList' />">繼續購物</a>
                                            <a href="#" onclick='clearCartItems()'>清空購物車</a>
                                        </div>
                                        <div class="cart-clear" id="totalCount">
                                        	
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        
                        </div>
                    </div>
                    
                    
                </div>
            </div>
            <div class="product-area pt-95 pb-70" style="padding-top: 0;">
            <div class="container">
                <div><h5>猜你喜歡</h5></div>
                <div class="row" id="recommendProductShow" style="border-top: 1px gray solid; padding-top: 10px;">

                    
                </div>
            </div>
        </div>

    
    
	
	
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
	
</body>
</html>