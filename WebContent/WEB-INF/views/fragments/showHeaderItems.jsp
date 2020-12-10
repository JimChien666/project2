<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<script>
function addToCart(productId){
	
	/* if(productId!=0){
		var cartNum = document.getElementById("countnum"+productId).innerHTML;
	}else{
		var cartNum = 0;
	} */
	var cartNum = 1;
	
	queryString="?productId=" + productId + "&cartNum=" + cartNum;
	var xhr = new XMLHttpRequest();
	
	xhr.open("Get", "<c:url value='/cart/AddCart' />" + queryString , true);
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
		if (document.getElementById("recommendProductShow")!=null){
			addToCartList();
		}
		
		}
	
	}
}

function goToCartPage(){
	
	window.location.href = "<c:url value='/cart/CartList' />";
}


function getShoppingOptions(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getColors' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content='';
			var url = "<c:url value='/product/ProductList' />";
			content += '<li><ul><li class="mega-menu-title">商品顏色</li>'
			var colors = JSON.parse(xhr.responseText);
			for(var i=0; i < colors.length; i++){
			    content += 	'<li><a href="' + url +'">'+colors[i].name+'</a></li>';
			}
			content += "</ul></li>";
			
			/* divs.innerHTML += "<br/>"; */
			
			var divs = document.getElementById("ShoppingOptionsArea");
			divs.innerHTML += content;
		}
	}
	var xhr2 = new XMLHttpRequest();
	xhr2.open("GET", "<c:url value='/product/getAnimalTypes' />", true);
	xhr2.send();
	xhr2.onreadystatechange = function() {
		if (xhr2.readyState == 4 && xhr2.status == 200) {
			var content='';
			var url="<c:url value='/product/ProductList' />";
			content += '<li><ul><li class="mega-menu-title">寵物類別</li>'
			var animalTypes = JSON.parse(xhr2.responseText);
			for(var i=0; i < animalTypes.length; i++){
			    content += 	'<li><a href="' + url + '">'+animalTypes[i].name+'</a></li>';
			}
			content += "</ul></li>";
			var divs = document.getElementById("ShoppingOptionsArea");
			divs.innerHTML += content;
			/* divs.innerHTML += "<br/>"; */
		}
	}
	var xhr3 = new XMLHttpRequest();
	xhr3.open("GET", "<c:url value='/product/getCategories' />", true);
	xhr3.send();
	xhr3.onreadystatechange = function() {
		if (xhr3.readyState == 4 && xhr3.status == 200) {
			var content='';
			var url="<c:url value='/product/ProductList' />";
			content += '<li><ul><li class="mega-menu-title">商品分類</li>'
			var categories = JSON.parse(xhr3.responseText);
			for(var i=0; i < categories.length; i++){
			    content += 	'<li><a href="' + url + '">'+categories[i].name+'</a></li>';
			}
			content += "</ul></li>";
			
			var divs = document.getElementById("ShoppingOptionsArea");
			divs.innerHTML += content;
			/* divs.innerHTML += "<br/>"; */
		}
	}
	
}

addToCart(0);
getShoppingOptions();
</script>
