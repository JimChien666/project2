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
	var count=document.getElementById("countnum"+ productId).value;
	count=parseInt(count)+1;
	document.getElementById("countnum"+productId).value=count;
	fixProductQuantity(productId, count);
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
                    '<input class="cart-plus-minus-box" id="countnum'+ cartList[i].productId +'" type="text" name="qtybutton" value="' + cartList[i].quantity + '">'+
                '<div class="inc qtybutton" onclick="adder(' + cartList[i].productId + ')">+</div>'+
                '</div>'+
            '</td>'+
            '<td class="product-subtotal" id="subtotal'+ cartList[i].productId + '">$'+cartList[i].discountPrice*cartList[i].quantity+'</td>'+
            '<td class="product-remove"><a href="#"  onclick="deleteCartItem('+cartList[i].productId+')"><i class="ti-trash"></i></a></td>'+
        '</tr>'
			total+=cartList[i].discountPrice*cartList[i].quantity;
		}
		content +="</tbody></table>"
		
        
		/* content +="<tr><td colspan='7'></td><td align='right'>共</td><td align='center'><span id='num' style='font-size: 20px; color:red;'>" + num + "</span>項商品</td></tr>"
		content +="<tr><td colspan='7'></td><td align='right'>總價：</td><td align='center'><span id='total' style='font-size: 20px; color:red;'>" + total + "</span>元</td></tr>"
		content += "</table>"; */
		
		document.getElementById("cartList").innerHTML = content;

		var totalCountContent = "<span>共:   <span id='num'>"+num+"</span>項商品</span>"+
					        "<h5>總價:   $<span id='total'>" + total + "</span></h5>"+
					        "<a href='#' onclick='comfirmOrder()'>結帳去</a>"
		document.getElementById("totalCount").innerHTML = totalCountContent;
	}
	
}
}



/* function addToCartList(){
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
} */

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
                <h3 class="page-title">購物清單</h3>
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
                        <div class="row">
                            
                            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                            
                                
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    
	
	
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
	<script src="<c:url value='/assets/js/showHeaderItems.js' />"></script>
</body>
</html>