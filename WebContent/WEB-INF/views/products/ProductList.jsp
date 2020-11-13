<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<%-- <link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" /> --%>
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
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/pagingProducts.json' />", true);
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
//從資料庫 [{id:1, name:"狗"}, {id:2, name:"貓"]
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


function getData(){
	var animalTypeId = document.getElementById("animalTypeId").value;
	var colorId = document.getElementById("colorId").value;
	var categoryId = document.getElementById("categoryId").value;
	var xhr = new XMLHttpRequest();
	var condiction = "?animalTypeId=" + animalTypeId + "&colorId=" + colorId + "&categoryId=" + categoryId;
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

function addToCart(productId){
	var cartNum = document.getElementById("qty").value;
	var xhr = new XMLHttpRequest();
	queryString="?productId=" + productId + "&cartNum=" + cartNum;
	xhr.open("Get", "<c:url value='/cart/addCart'/>" + queryString , true);
	xhr.send();
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var responseData = xhr.responseText;
		displayPageProducts(responseData);
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
		var condiction = "&animalTypeId=" + animalTypeId + "&colorId=" + colorId + "&categoryId=" + categoryId;
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
  var content = "<table border='1'><tr height='42' bgcolor='#fbdb98'>";
      content +=  "<th width='56'>編號</th><th width='360'>商品名稱</th><th width='80'>簡介</th>";
      content +=  "<th width='50'>定價</th><th width='50'>折扣</th><th  width='50'>實售</th>";
      content +=  "<th  width='100'>賣家</th><th width='60'>封面</th>";
	  content +=  "</tr>";
	var mapData = JSON.parse(responseData);
	pageNo = mapData.currPage;
	totalPage  = mapData.totalPage;
	recordCounts = mapData.recordCounts;
	
	var products = mapData.list;		// 傳回一個陣列
	var bgColor = "";   // 每一項商品的背影 
	var imageURL = "<c:url value='/product/getProductImage' />";
	var counterHtml = "<select id='qty' name='qty'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option></select>"
	document.getElementById("showRecordCounts").innerHTML = recordCounts;
	for(var i=0; i < products.length; i++){
		console.log(products[i]);
		bgColor = (i % 2 == 0 ? "#d4f5b2" : "#b2f5e5");
		content += "<tr height='80' bgcolor='" + bgColor + "'>" + 
		           "<td  align='right'>" + products[i].id + "&nbsp;</td>" + 
	               "<td>" + products[i].name + "</td>" +
	               "<td align='center'>" + products[i].description.substring(0, 100) + "</td>" +
	               "<td align='right'>" + products[i].price + "&nbsp;</td>" +
	               "<td align='center'>" + products[i].discount + "</td>" +
	               "<td align='right'>" + (products[i].price * products[i].discount) + "</td>" +
	               "<td align='center'>" + products[i].memberName + "</td>" +
	               "<td><img  width='60' height='80' " +   
	               " src='" + imageURL + "?productId=" + products[i].id + "'></td>" + 
	               "<td width='60' height='80'>"+counterHtml+"<button onclick='addToCart(" + products[i].id + ")'>add cart</button></td>" +
		           "</tr>";    
	}
	content += "</table>";
	
	document.getElementById("somedivS").innerHTML = content;
	
	
	var navContent = "<table border='1' ><tr height='36' bgcolor='#fbdb98'>" ;
	if (pageNo != 1){
		navContent += "<td width='80' align='center'><button id='first'>第一頁</button></td>";
		navContent += "<td width='80' align='center'><button id='prev'>前一頁</button></td>";
	}  else {
		navContent += "<td width='80' align='center'>&nbsp;</td>";
		navContent += "<td width='80' align='center'>&nbsp;</td>";
	}
	navContent += "<td width='200' align='center'>第" + pageNo + "頁 / 共" + totalPage + "頁</td>";
	if (pageNo != totalPage){
		navContent += "<td width='80' align='center'><button id='next'>下一頁</button></td>";
		navContent += "<td width='80' align='center'><button id='last'>最末頁</button></td>";
	}  else {
		navContent += "<td width='80' align='center'>&nbsp;</td>";
		navContent += "<td width='80' align='center'>&nbsp;</td>";
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

</script>
</head>
<body>
<jsp:include page="../public/top.jsp" />
<div align='center'>
	<h3>商品資訊</h3>
	<hr>
	<div id="selectBar"></div>
	<div>共蒐尋出<span style='color:red;' id='showRecordCounts'></span>項商品</div>
	<div id='somedivS'  style='height:260px;'></div>
	<div id='navigation' style='height:60px;'></div>
	<hr>
		<a href="<c:url value='/' />">回前頁</a>
	</div>
</body>
</html>