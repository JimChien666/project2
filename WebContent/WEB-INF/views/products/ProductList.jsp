<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<%-- <link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" /> --%>
<meta charset="UTF-8">
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
<script>
window.onload = function() {
	getColors();
	getCategories();
	getAnimalTypes();
}

function getCategories(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getCategories' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "選擇分類：<select name='categoryId'>";
			content += "<option value='' disabled selected='selected'>全部</option>"
			var categories = JSON.parse(xhr.responseText);
			for(var i=0; i < categories.length; i++){
			    content += 	"<option value='" + categories[i].id + "'>" + categories[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("selectBar");
			divs.innerHTML += content;
			divs.innerHTML += "<br/>";
		}
	}
}

function getColors(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getColors' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "商品顏色：<select name='colorId'>";
			content += "<option value='' disabled selected='selected'>全部</option>"
			var colors = JSON.parse(xhr.responseText);
			for(var i=0; i < colors.length; i++){
			    content += 	"<option value='" + colors[i].id + "'>" + colors[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("selectBar");
			divs.innerHTML += content;
			divs.innerHTML += "<br/>";
		}
	}
}


function getAnimalTypes(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getAnimalTypes' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "寵物類別：<select name='animalTypeId'>";
			content += "<option value='' disabled selected='selected'>全部</option>"
			var animalTypes = JSON.parse(xhr.responseText);
			for(var i=0; i < animalTypes.length; i++){
			    content += 	"<option value='" + animalTypes[i].id + "'>" + animalTypes[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("selectBar");
			divs.innerHTML += content;
			divs.innerHTML += "<br/>";
		}
	}
}


var pageNo = 0;
var totalPage  = 0;
// 本網頁一開始時先向後端發出非同步請求：/ch04/_07/pagingBookData.json，要求第一頁
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
	    // 查詢字串包含1.即將要讀取的頁數(pageNo), 2.總共有幾頁(totalPage)
	    // 注意，查詢字串的前面有問號
	    queryString = "?pageNo=" + no + "&totalPage=" + 	totalPage;

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
      content +=  "<th width='56'>編號</th><th width='360'>書名</th><th width='80'>作者</th>";
      content +=  "<th width='50'>定價</th><th width='50'>折扣</th><th  width='50'>實售</th>";
      content +=  "<th  width='100'>出版社</th><th width='60'>封面</th>";
	  content +=  "</tr>";
// 	  alert(responseData);
	var mapData = JSON.parse(responseData);
	pageNo = mapData.currPage;
	totalPage  = mapData.totalPage;
	
	var products = mapData.list;		// 傳回一個陣列
	var bgColor = "";   // 每一項商品的背影 
	var imageURL = "<c:url value='/product/getProductImage' />";
	for(var i=0; i < products.length; i++){
		console.log(products[i]);
		bgColor = (i % 2 == 0 ? "#d4f5b2" : "#b2f5e5");
		content += "<tr height='80' bgcolor='" + bgColor + "'>" + 
		           "<td  align='right'>" + products[i].id + "&nbsp;</td>" + 
	               "<td>" + products[i].name + "</td>" +
	               "<td align='center'>" + products[i].description + "</td>" +
	               "<td align='right'>" + products[i].price + "&nbsp;</td>" +
	               "<td align='center'>" + products[i].discount + "</td>" +
	               "<td align='right'>" + (products[i].price * products[i].discount) + "</td>" +
	               "<td align='center'>" + products[i].memberName + "</td>" +
	               "<td><img  width='60' height='80' " +   
	               " src='" + imageURL + "?productId=" + products[i].id + "'></td>" + 
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
	<div id="selectBar"><button onclick="asynRequest('first')">所有商品</button><br/></div>
	<div id='somedivS'  style='height:260px;'> </div>
	<div id='navigation' style='height:60px;'></div>
	<hr>
		<a href="<c:url value='/' />">回前頁</a>
	</div>
</body>
</html>