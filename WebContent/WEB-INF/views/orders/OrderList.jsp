<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
th, td{
width: 200px;
align: left;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
window.onload = function() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/order/pagingOrders.json' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 ) {
			if (xhr.status == 200){
				var responseData = xhr.responseText;
				
				showPageOrders(responseData);   // 顯示讀取到的非文字性資料
				
			} else {
				alert(xhr.status);
			}
		}
	}
}
	function showOrderInfo(orderId){
		var order = document.getElementById("orderInfo"+orderId);
		console.log(order.style.display);
		if(order.style.display=="none"){
			order.style.display = "";
		}else{
			order.style.display = "none";
		}
		
	}

	function showPageOrders(responseData){
		var mapData = JSON.parse(responseData);
		orders = mapData.list;
		pageNo = mapData.currPage;
		totalPage  = mapData.totalPage;
		recordCounts = mapData.recordCounts;
		var content="";
		var imageUrl="<c:url value='/product/getProductImage' />";
		for(var i=0; i < orders.length; i++){
			content+="<table>"+
						"<tr>"+
					"<th>訂單編號</th>"+
					"<th>訂單日期</th>"+
					"<th>訂單狀態</th>"+
					"<th>訂單總額</th>"+
						"</tr>"
			content +="<tr>" +
					  "<td>"+orders[i].id+"</td>" +
					  "<td>"+orders[i].createdAtString+"</td>" +
					  "<td>"+orders[i].status+"</td>" +
					  "<td>"+orders[i].total+"</td>" +
					  "<td><button onclick='showOrderInfo("+orders[i].id+")'>訂單詳細</button></td>" +
			   	      "</tr>"+
					  "</table>"
			content += "<table border='1' id='orderInfo"+orders[i].id+"' style='display: none;'>"+
						"<tr>"+
						"<th>商品編號</th>"+
						"<th>商品圖片</th>"+
						"<th>商品名稱</th>"+
						"<th>商品單價</th>"+
						"<th>購買數量</th>"+
						"<th>價格</th>"+
						"<th>狀態</th>"+
					"</tr>"
			for(var j=0; j < orders[i].orderItems.length; j++){
				console.log(orders[i].orderItems[j]);
				
				content += 
							"<tr>"+
							"<td>"+orders[i].orderItems[j].productId+"</td>"+
							"<td><img width='60' height='80' src='"+imageUrl+"?productId="+orders[i].orderItems[j].productId+"'></td>"+
							"<td>"+orders[i].orderItems[j].productName+"</td>"+
							"<td>"+orders[i].orderItems[j].price*orders[i].orderItems[j].discount+"</td>"+
							"<td>"+orders[i].orderItems[j].quantity+"</td>"+
							"<td>"+(orders[i].orderItems[j].price*orders[i].orderItems[j].discount*orders[i].orderItems[j].quantity)+"</td>"+
							"<td>"+orders[i].orderItems[j].status+"</td>"+
						"</tr>"
				
			}
			content += "</table><hr>";
		}
		
		document.getElementById("orderShow").innerHTML = content;

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
		    queryString = "?pageNo=" + no + "&totalPage=" + totalPage;

			xhr.open("GET", "<c:url value='/order/pagingOrders.json'/>" + queryString , true);
			xhr.send();
			xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var responseData = xhr.responseText;
				showPageOrders(responseData);
			}
		}
			
	}
</script>
<body>
<jsp:include page="../public/top.jsp" />
<div align='center'>
	<h2>訂單列表</h2>
	<div id="orderShow"></div>
	<div id='navigation' style='height:60px;'></div>
	</div>
</body>
</html>