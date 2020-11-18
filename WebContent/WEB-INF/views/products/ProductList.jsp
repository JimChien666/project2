<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style>
th, td{
width: 200px;
align: left;
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
//從資料庫 [{id:1, name:"狗"}, {id:2, name:"貓"}]
function getCategories(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getCategories' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "選擇分類：<select id='categoryId' name='categoryId' onchange=getData()>";
			content += "<option value='' selected='selected'>全部</option>"
			var categories = JSON.parse(xhr.responseText);
			for(var i=0; i < categories.length; i++){
			    content += 	"<option value='" + categories[i].id + "'>" + categories[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("selectBar");
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
			var content = "商品顏色：<select id='colorId' name='colorId' onchange=getData()>";
			content += "<option value='' selected='selected'>全部</option>"
			var colors = JSON.parse(xhr.responseText);
			for(var i=0; i < colors.length; i++){
			    content += 	"<option value='" + colors[i].id + "'>" + colors[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("selectBar");
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
			var content = "寵物類別：<select id='animalTypeId' name='animalTypeId' onchange=getData()>";
			content += "<option value='' selected='selected'>全部</option>"
			var animalTypes = JSON.parse(xhr.responseText);
			for(var i=0; i < animalTypes.length; i++){
			    content += 	"<option value='" + animalTypes[i].id + "'>" + animalTypes[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("selectBar");
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


function getData(){
	var animalTypeId = document.getElementById("animalTypeId").value;
	var colorId = document.getElementById("colorId").value;
	var categoryId = document.getElementById("categoryId").value;
	var recordsPerPage = document.getElementById("recordsPerPage").value;
	var xhr = new XMLHttpRequest();
	var condiction = "?animalTypeId=" + animalTypeId + "&colorId=" + colorId + "&categoryId=" + categoryId + "&recordsPerPage="+recordsPerPage;
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
	
	if(productId!=0){
		var cartNum = document.getElementById("qty"+productId).value;
	}else{
		var cartNum = 0;
	}
	queryString="?productId=" + productId + "&cartNum=" + cartNum;
	var xhr = new XMLHttpRequest();
	xhr.open("Get", "<c:url value='/cart/AddCart'/>" + queryString , true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var responseData = xhr.responseText;
		var cartList = JSON.parse(responseData);
		var num = cartList.length;
		var total = 0;
		for(var i=0; i < cartList.length; i++){
			total += cartList[i].price * cartList[i].discount * cartList[i].quantity;
			}
		document.getElementById("shopCart").innerHTML="<img src='https://img.icons8.com/pastel-glyph/64/000000/shopping-cart--v2.png'/><span>共"+num+"項商品,金額為" + total + "</span>"
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
	    var animalTypeId = document.getElementById("animalTypeId").value;
		var colorId = document.getElementById("colorId").value;
		var categoryId = document.getElementById("categoryId").value;
		var recordsPerPage = document.getElementById("recordsPerPage").value;
		var condiction = "&animalTypeId=" + animalTypeId + "&colorId=" + colorId + "&categoryId=" + categoryId + "&recordsPerPage=" + recordsPerPage;
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
    var content = "<table><tr style='border: 1px solid black;'>";
      content +=  "<th>編號</th><th>商品名稱</th>";
      content +=  "<th>定價</th><th>折扣</th><th>實售</th>";
      content +=  "<th>賣家</th><th>封面</th><th>購物車</th>";
	  content +=  "</tr>";
	var mapData = JSON.parse(responseData);
	pageNo = mapData.currPage;
	totalPage  = mapData.totalPage;
	recordCounts = mapData.recordCounts;
	
	var products = mapData.list;		// 傳回一個陣列
	var imageURL = "<c:url value='/product/getProductImage' />";
	var productsInfo = "<c:url value='/product/productsInfo/productsPath' />";
	var salesInfo = "<c:url value='/product/salesInfo/salesInfoPath' />";
	
	document.getElementById("showRecordCounts").innerHTML = recordCounts;
	for(var i=0; i < products.length; i++){

		
		content += "<tr>" + 
		           "<td>" + products[i].id + "&nbsp;</td>" + 
		           "<td>" +  
	               "<a href='"+productsInfo.replace("productsPath",products[i].id)+"'>"+products[i].name+"</a>"
	               + "</td>" +
	               "<td>" + products[i].price + "&nbsp;</td>" +
	               "<td>" + products[i].discount + "</td>" +
	               "<td>" + (products[i].price * products[i].discount) + "</td>" +
	               "<td>" + 
	               "<a href='"+salesInfo.replace("salesInfoPath",products[i].memberId)+"'>"+products[i].memberName+"</a>"
	               + "</td>" +
	               "<td><img  width='60' height='80' " +   
	               " src='" + imageURL + "?productId=" + products[i].id + "'></td>" + 
	               "<td><select id='qty"+ products[i].id +"' name='qty'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option></select><button onclick='addToCart(" + products[i].id + ")'>add cart</button></td>" +
		           "</tr>";    
	}
	content += "</table>";
	
	document.getElementById("somedivS").innerHTML = content;
	
	
	var navContent = "<table border='1' ><tr height='36' bgcolor='#fbdb98'>" ;
	if (pageNo != 1){
		navContent += "<td align='center'><button id='first'>第一頁</button></td>";
		navContent += "<td align='center'><button id='prev'>前一頁</button></td>";
	}  else {
		navContent += "<td align='center'>&nbsp;</td>";
		navContent += "<td align='center'>&nbsp;</td>";
	}
	navContent += "<td width='200' align='center'>第" + pageNo + "頁 / 共" + totalPage + "頁</td>";
	if (pageNo != totalPage){
		navContent += "<td align='center'><button id='next'>下一頁</button></td>";
		navContent += "<td align='center'><button id='last'>最末頁</button></td>";
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

function goToCartPage(){
	window.location.href = "<c:url value='/cart/CartList' />";
}

</script>
</head>
<body>
<jsp:include page="../public/top.jsp" />
<div align='center'>
	<h3>商品資訊</h3>
	<hr>
	<div id="selectBar" style="display:inline;"></div><div id="shopCart" onclick="goToCartPage()" style='cursor: pointer;display:inline;margin-left:20%;' dir='rtl'><img src="https://img.icons8.com/pastel-glyph/64/000000/shopping-cart--v2.png"/></div>
	<div>共蒐尋出<span style='color:red;' id='showRecordCounts'></span>項商品</div>
	<hr>
	<div id='somedivS'></div>
	<div id='navigation'></div>
	<hr>

	<a href="<c:url value='/' />">回前頁</a>
</div>
</body>
</html>