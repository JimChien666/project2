<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
th, td{
width: 200px;
align: right;
}
#cartList{
	/* position: absolute; */
	width: auto;
	height: auto;
	top:30%;
	left: 50%;
	/* transform: translate(-50%, -50%); */

}

.counter li{float:left; list-style-type:none; width:30px; height:30px; text-align:center; line-height:30px; border:#999 thin solid; background-color:#fff;}
.counter li input{font-size:20px; width:100%; height:100%; outline:none; -webkit-appearance:none; background:none; margin:0; padding:0; border: 1px solid transparent; border-radius: 0;}
#countnum{ border-left:hidden; border-right:hidden; color:#666}
ul,li{margin:0; padding:0; display:inline;}
</style>
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
<script>
window.onload = function() {
	addToCartList();
}

function adder(productId){
	var count=document.getElementById("countnum"+ productId).innerHTML;
	count=parseInt(count)+1;
	document.getElementById("countnum"+productId).innerHTML=count;
	fixProductQuantity(productId, count);
}
function minuser(productId){
	var count=document.getElementById("countnum"+productId).innerHTML;
	if(count<=1){
		count=1;
	}else{
		count=parseInt(count)-1;
	}	
	document.getElementById("countnum"+productId).innerHTML=count;
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
			}
		}
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
			document.getElementById("subtotal" + cartList[i].productId).innerHTML = cartList[i].discount * cartList[i].price * cartList[i].quantity;
			total+=cartList[i].discount * cartList[i].price * cartList[i].quantity;
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
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var responseData = xhr.responseText;
		var cartList = JSON.parse(responseData);
		var num = cartList.length;
		var total = 0;
		var content = "<table><tr style='border: 1px solid black;'>";
		content +=  "<th>編號</th><th>封面</th><th colspan='4'>商品名稱</th>";
	    content +=  "<th>實售單價</th>";
	    content +=  "<th>數量</th>";
	    content +=  "<th>總價</th>";
	   	content +=  "<th>操作</th>";
		content +=  "</tr>";
		for(var i=0; i < cartList.length; i++){
			content += "<tr id='tr" + cartList[i].productId + "' height='80'>" + 
	        "<td>" + cartList[i].productId + "&nbsp;</td>" + 
	        "<td><img  width='60' height='80' " +   
	        " src='" + imageURL + "?productId=" + cartList[i].productId + "'></td>" + 
            "<td colspan='4' align='center'>" + cartList[i].productName + "</td>" +
            "<td>" + (cartList[i].discount * cartList[i].price) + "</td>" +
            "<td><ul class='counter'>" + 
            "<li id='minus" + cartList[i].productId + "'><input type='button' onclick='minuser(" + cartList[i].productId + ")' value='-'/></li>"+
            "<li id='countnum" + cartList[i].productId + "'>" + cartList[i].quantity + "</li>"+
            "<li id='plus" + cartList[i].productId + "'><input type='button' onclick='adder(" + cartList[i].productId + ")' value='+'/></li>"+
           	"</ul></td>" +
           	"<td id='subtotal" + cartList[i].productId + "'>" + (cartList[i].discount * cartList[i].price * cartList[i].quantity) + "</td>"+
           	"<td><button onclick='deleteCartItem("+cartList[i].productId+")'>刪除</button></td>"+
           	"</tr>";
			total+=cartList[i].discount * cartList[i].price * cartList[i].quantity;
		}
		content +="<tr><td colspan='7'></td><td align='right'>共</td><td align='center'><span id='num' style='font-size: 20px; color:red;'>" + num + "</span>項商品</td></tr>"
		content +="<tr><td colspan='7'></td><td align='right'>總價：</td><td align='center'><span id='total' style='font-size: 20px; color:red;'>" + total + "</span>元</td></tr>"
		content += "</table>";
		
		document.getElementById("cartList").innerHTML = content;
	}
	
}
}

function comfirmOrder(){
	var check = confirm("前往結帳");
	if (check){
		window.location.href = "<c:url value='/order/CreateOrder' />";
	}
}
</script>
</head>
<body>
	<jsp:include page="../public/top.jsp" />
	<div align='center'>
		<h3>購物車</h3>
		<hr>
		<div id="cartList"></div>
		<hr>
		<div id='navigation' style='height:60px;'>
			<button><a href="<c:url value='/product/ProductList' />" style="text-decoration:none;color:black;">返回購物</a></button>
			<button onclick='comfirmOrder()'>立即結帳</button>
		</div>
	</div>
</body>
</html>