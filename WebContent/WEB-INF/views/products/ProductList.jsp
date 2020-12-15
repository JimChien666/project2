<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>購物商城首頁</title>
<style>
th, td{
width: 200px;
align: left;
}
.counter li{float:left; list-style-type:none; width:30px; height:30px; text-align:center; line-height:30px; border:#999 thin solid; background-color:#fff;}
.counter li input{font-size:20px; width:100%; height:100%; outline:none; -webkit-appearance:none; background:none; margin:0; padding:0; border: 1px solid transparent; border-radius: 0;}
#countnum{ border-left:hidden; border-right:hidden; color:#666}
ul,li{margin:0; padding:0; display:inline;}
.discountTag{
      background-color: red;
      color: white;
      position: absolute;
      left: -19px;
      top: 4px;
      padding: 2px 20px;
      transform: rotate(-45deg); 
}
.btncls{
	background-color: #7E4C4F; /* Green */
    border: none;
    color: white;
    padding: 10px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 10px;
    transition-duration: 0.3s;
    cursor: pointer;
}
button.btncls:hover{
	background-color: #000000;
}
</style>
<meta charset="UTF-8">
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>

<script>
var pageNo = 0;
var totalPage  = 0;
// 本網頁一開始時先向後端發出非同步請求：/product/pagingProducts.json，要求第一頁
//並將 各個分類下拉選單載入
window.onload = function() {
	getColors();
	getCategories();
	getAnimalTypes();
	getPage();
	getOrderBy();
	var xhr = new XMLHttpRequest();
	var recordsPerPage = document.getElementById("recordsPerPage").value;
	xhr.open("GET", "<c:url value='/product/pagingProducts.json' />" + "?recordsPerPage="+recordsPerPage, true);
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
	addToCart(0);
}
function adder(productId, quantity){
	var count=document.getElementById("countnum"+ productId).innerHTML;
	if(count>=quantity){
		document.getElementById("outOfRange"+ productId).innerHTML="<span style='color: red;'>現有庫存"+quantity+ "</span>";
	}else{
		count=parseInt(count)+1;
		document.getElementById("outOfRange"+ productId).innerHTML="";
	}
	document.getElementById("countnum"+productId).innerHTML=count;
}
function minuser(productId, quantity){
	var count=document.getElementById("countnum"+productId).innerHTML;
	if(count<=1){
		count=1;
	}else{
		count=parseInt(count)-1;
		document.getElementById("outOfRange"+ productId).innerHTML="";
	}	
	document.getElementById("countnum"+productId).innerHTML=count;
}
//從資料庫 [{id:1, name:"狗"}, {id:2, name:"貓"}]
function getCategories(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getCategories' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "<select id='categoryId' name='categoryId' onchange=getData()>";
			content += "<option value='' selected='selected'>全部</option>"
// 			把'/product/getCategories'回傳RespoenseBody的JSON抓出來
			var categories = JSON.parse(xhr.responseText);
			for(var i=0; i < categories.length; i++){
			    content += 	"<option value='" + categories[i].id + "'>" + categories[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("CategorySelectBar");
			divs.innerHTML += content;
			/* divs.innerHTML += "<br/>"; */
		}
	}
}
function getColors(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getColors' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "<select id='colorId' name='colorId' onchange=getData()>";
			content += "<option value='' selected='selected'>全部</option>"
			var colors = JSON.parse(xhr.responseText);
			for(var i=0; i < colors.length; i++){
			    content += 	"<option value='" + colors[i].id + "'>" + colors[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("ColorSelectBar");
			divs.innerHTML += content;
			/* divs.innerHTML += "<br/>"; */
		}
	}
}
function getAnimalTypes(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getAnimalTypes' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "<select id='animalTypeId' name='animalTypeId' onchange=getData()>";
			content += "<option value='' selected='selected'>全部</option>"
			var animalTypes = JSON.parse(xhr.responseText);
			for(var i=0; i < animalTypes.length; i++){
			    content += 	"<option value='" + animalTypes[i].id + "'>" + animalTypes[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("AnimalTypeSelectBar");
			divs.innerHTML += content;
			/* divs.innerHTML += "<br/>"; */
		}
	}
}
function getPage(){
			var content = "商品分頁：<select id='recordsPerPage' name='recordsPerPage' onchange=getData() >";
			for(var i=3; i <= 9; i+=3){
			    content += 	"<option value=" + i + ">" + i + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("selectBar");
			divs.innerHTML += content;
			/* divs.innerHTML += "<br/>"; */
}
function getOrderBy(){
	var content = "商品排序：<select id='orderBy' name='orderBy' onchange=getData() >";
	content += "<option value=0>" + "以顏色排序 " + "</option>";
	content += "<option value=1>" + "以名稱小至大排序 ↑" + "</option>";
	content += "<option value=2>" + "以價格小至大排序 ↑" + "</option>";
	content += "<option value=3>" + "以名稱大至小排序 ↓" + "</option>";
	content += "<option value=4>" + "以價格大至小排序 ↓" + "</option>";
	content += "<option value=5>" + "以上架時間排序 ↓" + "</option>";
	content += "</select>";
	var divs = document.getElementById("OrderBySelectBar");
	divs.innerHTML += content;
	/* divs.innerHTML += "<br/>"; */
}
function getData(){
	var animalTypeId = document.getElementById("animalTypeId").value;
	var colorId = document.getElementById("colorId").value;
	var categoryId = document.getElementById("categoryId").value;
	var recordsPerPage = document.getElementById("recordsPerPage").value;
	var keywordSearch = document.getElementById("keywordSearch").value;
	var orderBy = document.getElementById("orderBy").value;
	
	var xhr = new XMLHttpRequest();
	var condiction = "?animalTypeId=" + animalTypeId + "&colorId=" + colorId + "&categoryId=" + categoryId + "&recordsPerPage=" + recordsPerPage + "&keywordSearch=" + keywordSearch + "&orderBy=" + orderBy;
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/pagingProducts.json' />" + condiction, true);
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
//若productId為零,後端會直接回傳購物車列表
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
// 當使用者按下『第一頁』、『前一頁』、『下一頁』、『最末頁』的連結時，由本方法發出非同步請求。
function asynRequest(id) {
	var xhr = new XMLHttpRequest();
	var no = 0;
    var queryString = "";     		// queryString紀錄查詢字串
	    if (id == "first") {		// 算出查詢字串中，要送出的pageNo為何?
	    	no = 1;
	    } else if (id == "prev") {
	    	no = pageNo - 1;
	    } else if (id == "next") {
	    	no = pageNo + 1;
	    } else if (id == "last") {
	    	no = totalPage;	    	
	    }
	    console.log(no);
	    var animalTypeId = document.getElementById("animalTypeId").value;
		var colorId = document.getElementById("colorId").value;
		var categoryId = document.getElementById("categoryId").value;
		var recordsPerPage = document.getElementById("recordsPerPage").value;
		var keywordSearch = document.getElementById("keywordSearch").value;
		var orderBy = document.getElementById("orderBy").value;
		
		var condiction = "&animalTypeId=" + animalTypeId + "&colorId=" + colorId + "&categoryId=" + categoryId + "&recordsPerPage=" + recordsPerPage + "&keywordSearch=" + keywordSearch + "&orderBy=" + orderBy;
	    // 查詢字串包含1.即將要讀取的頁數(pageNo), 2.總共有幾頁(totalPage)
	    // 注意，查詢字串的前面有問號
	    queryString = "?pageNo=" + no + "&totalPage=" + totalPage + condiction;
		xhr.open("GET", "<c:url value='/product/pagingProducts.json'/>" + queryString , true);
		xhr.send();
		xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var responseData = xhr.responseText;
			displayPageProducts(responseData);
		}
	}
		
}
function displayPageProducts(responseData){
    var content = "";
	var mapData = JSON.parse(responseData);
	pageNo = mapData.currPage;
	totalPage  = mapData.totalPage;
	recordCounts = mapData.recordCounts;
	
	var products = mapData.list;		// 傳回一個陣列
	var likeList = mapData.likeList;
	console.log(likeList);
	var imageURL = "<c:url value='/product/getProductImage' />";
	var productsInfo = "<c:url value='/product/productsInfo/productsPath' />";
	var salesInfo = "<c:url value='/product/salesInfo/salesInfoPath' />";
	
	document.getElementById("showRecordCounts").innerHTML = recordCounts;
	for(var i=0; i < products.length; i++){
		console.log(products[i]);
		content += '<div class="product-width col-lg-6 col-xl-4 col-md-6 col-sm-6">';
		
		content += '<div class="product-wrapper mb-10">';
		content += '<div class="product-img">';
		var now = new Date();
		//一天86400*7天*1000毫秒
		if ((now-products[i].createdAt) < (86400*7*1000)){
		content += '<a href="'+productsInfo.replace("productsPath",products[i].id)+'"><div class="discountTag"><b>new</b></div>';
		}
		else{
			content += '<a href="'+productsInfo.replace("productsPath",products[i].id)+'">';
		}
		content += '<img src="' + imageURL + '?productId=' + products[i].id +'" alt="">';
		content += '</a>';
		content += '<div class="product-action">';
		content += '<a title="查看商品詳情" href="'+productsInfo.replace("productsPath",products[i].id)+'">';
		content += ' <i class="ti-plus"></i>';
		content += '</a>';
		content += ' <a title="加入購物車" onclick="addToCart(' + products[i].id + ')"  href="#">';
		content += '   <i class="ti-shopping-cart"></i>';
		content += ' </a>';
		content += '</div>';
		content += '<div class="product-action-wishlist">';
		content += '<a title="加到我的最愛" id="like'+products[i].id+'" href="#" onclick=like('+products[i].id+')>';
		var isLike = false;
		
		for (var j = 0;j<likeList.length;j++){
				if (likeList[j].productId == products[i].id){
					isLike=true;
				}
			}
		if (isLike){
			content += '🤎';
		}
		else{
			content += '🤍';
		}
			
				content += '</a>';
					content += '</div>';
		content += '</div>';
		content += '<div class="product-action-wishlist">';
		content += '<a title="加到我的最愛" href="#">';
		content += '</a>';
		content += '</div>';
		content += '</div>';
		content += '<div class="product-content">';
		content += '<h4><a href="'+productsInfo.replace("productsPath",products[i].id)+'">'+products[i].name+'</a></h4>';
		content += '<div class="product-price">';
		content += '<span class="new">$'+products[i].discountPrice+' </span>';
		if(products[i].discount < 1){
			content += '<span class="old">$'+products[i].price+'</span>';
		}
		content += '</div>';
		content += '</div>';
		content += '<div class="product-list-content">';
		content += '<h4><a href="'+productsInfo.replace("productsPath",products[i].id)+'">' + products[i].name + '</a></h4>';
		content += '<div class="product-price">';
		content += '<span class="new">$'+products[i].discountPrice+' </span>';
		content += '</div>';
		content += '<p>' + products[i].description.substring(0, 150) + '...</p>';
		content += '<div class="product-list-action">';
		content += '<div class="product-list-action-left">';
		content += '<a class="addtocart-btn" title="加入購物車" onclick="addToCart(' + products[i].id + ')" style="color: white; cursor: pointer;"><i class="ion-bag"></i>加入購物車</a>';
		content += '</div>';
		content += '<div class="product-list-action-right">';
		content += '<a title="查看商品詳情" href="'+productsInfo.replace("productsPath",products[i].id)+'"><i class="ti-plus"></i></a>';
		content += '</div>';
		content += '</div>';
		content += '</div>';
		content += '</div>';
	}
	
	document.getElementById("ProductListBox").innerHTML = content;
	
	
	var navContent = "" ;
	if (pageNo != 1){
		navContent += "<li><a id='first'><<</a></li>";
		navContent += "<li><a id='prev'>" + (parseInt(pageNo) - 1 ) + "</a></li>";
	}  else {
		navContent += "<li>&nbsp;</li>";
		navContent += "<li>&nbsp;</li>";
	}
	navContent += "<li><a class='active' href='#'>" + (parseInt(pageNo)) + "</a></li>";
	if (pageNo != totalPage){
		navContent += "<li><a id='next'>" + (parseInt(pageNo) + 1 ) + "</a></li>";
		navContent += "<li><a id='last'>>></a></li>";
	}  else {
		navContent += "<td align='center'>&nbsp;</td>";
		navContent += "<td align='center'>&nbsp;</td>";
	}
	document.getElementById("navigation").innerHTML = navContent;
	var firstBtn = document.getElementById("first");
	var prevBtn  = document.getElementById("prev");
	var nextBtn  = document.getElementById("next");
	var lastBtn  = document.getElementById("last");
	if (firstBtn != null) {
		firstBtn.onclick=function(){
			asynRequest(this.id);
		}
	}
	
	if (prevBtn != null) {
		prevBtn.onclick=function(){
			asynRequest(this.id);
		}
	}
	
	if (nextBtn != null) {
		nextBtn.onclick=function(){
			asynRequest(this.id);
		}
	}
	
	if (lastBtn != null) {
		lastBtn.onclick=function(){
			asynRequest(this.id);				
		}
	}	
}
function like(productId){
	if (${empty LoginOK}){
		alert("請登入");
		window.location.href = "<c:url value='/member/login' />";
	}
	console.log(productId);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/goLike' />?productId="+productId, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var responseData = xhr.responseText;
			var status = JSON.parse(responseData);
			if (status == 0){
				document.getElementById("like"+productId).innerHTML="🤍";
				
			}else{
				document.getElementById("like"+productId).innerHTML="🤎";
			}
			console.log(status);
		}
	}
}
function goToCartPage(){
	window.location.href = "<c:url value='/cart/CartList' />";
}





</script>
<jsp:include page="../fragments/links.jsp" />
<style>
.btncls{
	background-color: #7E4C4F; /* Green */
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 10px;
    transition-duration: 0.3s;
    cursor: pointer;
}
button.btncls:hover{
	background-color: #000000;
}
</style>
</head>
<body>
<jsp:include page="../fragments/headerArea.jsp" />
<script type="text/javascript">
	//webSocket
	var wsUri = getRootUri() + "/websocket-hello/hello";
	
	function getRootUri() {
	    return "ws://" + (document.location.hostname == "" ? "localhost" : document.location.hostname) + ":" +
	            (document.location.port == "" ? "8080" : document.location.port);
	}
	
	function init() {
	    output = document.getElementById("output");
	}
	
	function send_message() {
	
	    websocket = new WebSocket(wsUri);
	    websocket.onopen = function(evt) {
	        onOpen(evt)
	    };
	    websocket.onmessage = function(evt) {
	        onMessage(evt)
	    };
	    websocket.onerror = function(evt) {
	        onError(evt)
	    };
	
	}
	
	function onOpen(evt) {
	    writeToScreen("Connected to Endpoint!");
	    doSend(textID.value);
	
	}
	
	function onMessage(evt) {
	    writeToScreen("Message Received: " + evt.data);
	}
	
	function onError(evt) {
	    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}
	
	function doSend(message) {
	    writeToScreen("Message Sent: " + message);
	    websocket.send(message);
	}
	
	function writeToScreen(message) {
	    var pre = document.createElement("p");
	    pre.style.wordWrap = "break-word";
	    pre.innerHTML = message;
	      
	    output.appendChild(pre);
	}
	
	window.addEventListener("load", init, false);

</script>

<!-- TOP圖片麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img" style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
            <div class="container">
                <div class="breadcrumb-content text-center">
                    <h2>購物</h2>
                    <ul>
                        <li><a href="<c:url value='/' />">首頁</a></li>
                        <li class="active">購物</li>
                    </ul>
                </div>
            </div>
        </div>
        

	<div class="shop-area pt-100 pb-100 gray-bg">
           <div class="container">
            <div class="row flex-row-reverse">
                   <div class="col-lg-9">
                       <div class="shop-topbar-wrapper">
                       	<div class="product-sorting-wrapper">
                              	<div class="product-show shorting-style" id="selectBar">
                                  	<label>共有 <span id="showRecordCounts"></span> 項商品</label>                                   	
                                <div style="display:inline;" class="shop-list-style mt-20" id="OrderBySelectBar"></div>                        
                                </div>
                                
                        </div>
                        <div class="grid-list-options">
                                <ul class="view-mode">
                                    <li class="active"><a href="#product-grid" data-view="product-grid"><i class="ti-layout-grid4-alt"></i></a></li>
                                    <li><a href="#product-list" data-view="product-list"><i class="ti-align-justify"></i></a></li>
                                </ul>
                        </div>
                        
                        
					</div>
					<div class="grid-list-product-wrapper">
                            <div class="product-view product-grid">
                                <div class="row" id="ProductListBox">
                                </div>
                                <div class="pagination-style text-center mt-10">
                                    <ul id = 'navigation'>
                                    </ul>
                                </div>
                           	</div>
                        </div>
				</div>
				
				
<!-- 				左邊那條 -->
				<div class="col-lg-3">
                        <div class="shop-sidebar">
                            <div class="shop-widget">
                            
                            	<h4 class="shop-sidebar-title">搜尋商品</h4>
                                <div class="shop-search mt-25 mb-50" >                               

                                        <input  type="text" name="keywordSearch" id="keywordSearch" placeholder="輸入商品名稱" style="width: 200px;">
                                        <button  class="btncls" type="submit" onclick="getData()">

                                            <i class="icon-magnifier"></i>
                                        </button>

                                </div>
                            </div>
                            <div class="shop-widget mt-50">
                                 <h4 class="shop-sidebar-title">商品顏色 </h4>
                                 <div class="shop-list-style mt-20" id="ColorSelectBar"></div>
                                 <h4 class="shop-sidebar-title">寵物類別 </h4>
                                 <div class="shop-list-style mt-20" id="AnimalTypeSelectBar"></div>
                                 <h4 class="shop-sidebar-title">商品分類 </h4>
                                 <div class="shop-list-style mt-20" id="CategorySelectBar"></div>
                            </div>
                        </div>
                </div>
			</div>
          </div>
      </div>


        <div style="text-align: center;">
            <form action="">
                <input onclick="send_message()" value="Send" type="button">
                <input id="textID" name="message" value="Hello WebSocket!" type="text"><br>
            </form>
        </div>
        <div id="output"></div>

<jsp:include page="../fragments/footerArea.jsp" />
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>
