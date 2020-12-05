<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
window.onload = function() {
	
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/memberRecommandProducts.json' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 ) {
			if (xhr.status == 200){
				var responseData = xhr.responseText;
				displayPageProducts(responseData);   // 顯示讀取到的非文字性資料
			} else {
				alert(xhr.status);
			}
		}
	}
}
function displayPageProducts(responseData){
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
	document.getElementById("productList").innerHTML = content;
}
function addToCart(productId){
	
	/* if(productId!=0){
		var cartNum = document.getElementById("countnum"+productId).innerHTML;
	}else{
		var cartNum = 0;
	} */
	var cartNum = 1;
	
	queryString="?productId=" + productId + "&cartNum=" + cartNum;
	var xhr = new XMLHttpRequest();
	xhr.open("Get", "<c:url value='/cart/AddCart'/>" + queryString , true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var responseData = xhr.responseText;
		var cartList = JSON.parse(responseData);
		var num = cartList.length;
		/* var total = 0;
		
		for(var i=0; i < cartList.length; i++){
			total += cartList[i].price * cartList[i].discount * cartList[i].quantity;
			} */
		document.getElementById("shopCart").innerHTML=num;
		}
	
	}
}
function getRandomInt(max) {
	  return Math.floor(Math.random() * Math.floor(max));
	}
</script>