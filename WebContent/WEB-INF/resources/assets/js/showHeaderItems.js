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

addToCart(0);
