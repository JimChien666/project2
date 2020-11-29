<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					"<th>付款狀態</th>"+
					"<th>操作</th>"+
						"</tr>"
			content +="<tr>" +
					  "<td>"+orders[i].id+"</td>" +
					  "<td>"+orders[i].createdAtString+"</td>" +
					  "<td>"+orders[i].status+"</td>" +
					  "<td>"+orders[i].total+"</td>"
					  
				if(orders[i].isPaid==0){
				content +="<td>尚未付款</td>"
				}else if (orders[i].isPaid==1){
					content +="<td>付款完成</td>"
				}else{
					content +="<td>付款失敗</td>"
					}
			content +="<td><button class='btncls' onclick='showOrderInfo("+orders[i].id+")'>訂單詳細</button></td>"+
					  "</tr>"+
					  "</table>"
			content += "<table id='orderInfo"+orders[i].id+"' style='display: none;'>"+
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
							"<td>"+orders[i].orderItems[j].discountPrice+"</td>"+
							"<td>"+orders[i].orderItems[j].quantity+"</td>"+
							"<td>"+(orders[i].orderItems[j].discountPrice*orders[i].orderItems[j].quantity)+"</td>"+
							"<td>"+orders[i].orderItems[j].status+"</td>"+
						"</tr>"
				
			}
			content += "</table><hr>";
		}
		
		document.getElementById("orderShow").innerHTML = content;

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
	function getOrders(){
		var paidStatus = document.getElementById("paidStatus").value;
		var orderStatus = document.getElementById("orderStatus").value;
		console.log(paidStatus+orderStatus);
		var condiction = "?paidStatus=" + paidStatus + "&orderStatus=" + orderStatus;
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/order/pagingOrders.json' />"+condiction, true);
		xhr.send();
		 xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 ) {
				if (xhr.status == 200){
					var responseData = xhr.responseText;
					console.log(responseData);
					showPageOrders(responseData);   // 顯示讀取到的非文字性資料
				} else {
					alert(xhr.status);
				}
			}
		}
		if(paidStatus==""){
			document.getElementById("paidStatus").innerHTML='<option selected=selected disabled value="">付款狀態</option><option value="">全部</option><option value=0>尚未付款</option><option value=1>付款完成</option><option value=2>付款失敗</option>'
		}
		if(orderStatus==""){
			document.getElementById("orderStatus").innerHTML='<option selected=selected disabled value="">訂單狀態</option><option value="">全部</option><option value="訂單成立">訂單成立</option><option value="取消">取消</option>'
		}
	}
</script>
<jsp:include page="../fragments/links.jsp" />
<body>
<jsp:include page="../fragments/headerArea.jsp" />
<div class="breadcrumb-area pt-95 pb-95 bg-img" style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
            <div class="container">
                <div class="breadcrumb-content text-center">
                    <h2>購買紀錄</h2>
                    <ul>
                        <li><a href="<c:url value='/' />">首頁</a></li>
                        <li><a href="<c:url value='/member/myAccount'/>">會員中心</a></li>
                        <li class="active">購買紀錄</li>
                    </ul>
                </div>
            </div>
        </div>
<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />

                       
        <div class="cart-main-area pt-95 pb-100">
            <div class="container">
                <div class="shop-topbar-wrapper" style=" border: white;">
                       	<div class="product-sorting-wrapper" >
                              	<div class="product-show shorting-style" id="selectBar" style="display: inline-block;">
                                  	<h3>訂單列表</h3>
                                  	
                                    <!-- <div class="shop-search mt-25 mb-50" style="display: inline-block; width: 400px;">
                               		<form class="shop-search-form">
                                        <input type="text" placeholder="Find a product">
                                        <button type="submit">
                                            <i class="icon-magnifier"></i>
                                        </button>
                                    </form>
                                    
                                	</div> -->
                                	<select id="paidStatus" onchange="getOrders()">
                                        <option selected=selected disabled value="">付款狀態</option>
                                    	<option value="">全部</option>
                                        <option value=0>尚未付款</option>
                                        <option value=1>付款完成</option>
                                        <option value=2>付款失敗</option>
                                    </select>
                                    <select id="orderStatus" onchange="getOrders()">
                                        <option selected=selected disabled value="">訂單狀態</option>
                                        <option value="">全部</option>
                                        <option value="訂單成立">訂單成立</option>
                                        <option value="取消">取消</option>
                                    </select>
                                </div>
                        </div>
                        
					</div>
                <div class="row">
                   	 <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="table-content table-responsive" id='orderShow'>
                            </div>
                     </div>
                     
                </div>
            </div>
            <div class="blog-area pt-100 pb-100 clearfix" style="padding:0;">
            <div class="container">
                <div class="pagination-style text-center mt-10">
                	<ul id = 'navigation'>
			        </ul>
                </div>
            </div>
        </div>
        </div>
        
        



<jsp:include page="../fragments/footerArea.jsp" />
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>