function addToCart(productId){
	
	/* if(productId!=0){
		var cartNum = document.getElementById("countnum"+productId).innerHTML;
	}else{
		var cartNum = 0;
	} */
	var cartNum = 1;
	
	queryString="?productId=" + productId + "&cartNum=" + cartNum;
	var xhr = new XMLHttpRequest();
	xhr.open("Get", "/team6/cart/AddCart" + queryString , true);
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

function goToCartPage(){
	window.location.href = "/team6/cart/CartList";
}


function getShoppingOptions(){
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/team6/product/getColors", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content='';
			content += '<li><ul><li class="mega-menu-title">商品顏色</li>'
			var colors = JSON.parse(xhr.responseText);
			for(var i=0; i < colors.length; i++){
			    content += 	'<li><a href="/team6/product/ProductList">'+colors[i].name+'</a></li>';
			}
			content += "</ul></li>";
			
			/* divs.innerHTML += "<br/>"; */
			
			var divs = document.getElementById("ShoppingOptionsArea");
			divs.innerHTML += content;
		}
	}
	var xhr2 = new XMLHttpRequest();
	xhr2.open("GET", "/team6/product/getAnimalTypes", true);
	xhr2.send();
	xhr2.onreadystatechange = function() {
		if (xhr2.readyState == 4 && xhr2.status == 200) {
			var content='';
			content += '<li><ul><li class="mega-menu-title">寵物類別</li>'
			var animalTypes = JSON.parse(xhr2.responseText);
			for(var i=0; i < animalTypes.length; i++){
			    content += 	'<li><a href="/team6/product/ProductList">'+animalTypes[i].name+'</a></li>';
			}
			content += "</ul></li>";
			var divs = document.getElementById("ShoppingOptionsArea");
			divs.innerHTML += content;
			/* divs.innerHTML += "<br/>"; */
		}
	}
	var xhr3 = new XMLHttpRequest();
	xhr3.open("GET", "/team6/product/getCategories", true);
	xhr3.send();
	xhr3.onreadystatechange = function() {
		if (xhr3.readyState == 4 && xhr3.status == 200) {
			var content='';
			content += '<li><ul><li class="mega-menu-title">商品分類</li>'
			var categories = JSON.parse(xhr3.responseText);
			for(var i=0; i < categories.length; i++){
			    content += 	'<li><a href="/team6/product/ProductList">'+categories[i].name+'</a></li>';
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
