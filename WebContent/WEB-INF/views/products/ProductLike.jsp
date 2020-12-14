<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的最愛</title>
<jsp:include page="../fragments/links.jsp" />
<script>
window.onload = function() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getFollowProduct.controller' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var responseData = xhr.responseText;

				displayProducts(responseData); // 顯示讀取到的非文字性資料

			} else {
				alert(xhr.status);
			}
		}
	}
}
function displayProducts(responseData){
    var content = "";
	var products = JSON.parse(responseData);
	console.log(products);
	var imageURL = "<c:url value='/product/getProductImage' />";
	var productsInfo = "<c:url value='/product/productsInfo/productsPath' />";
	var salesInfo = "<c:url value='/product/salesInfo/salesInfoPath' />";

	
	/*  document.getElementById("showRecordCounts").innerHTML = recordCounts;*/
	content+=
		'<h3>商品最愛</h3>'+
		'<form action="#">'+
        '<div class="table-content table-responsive">'+
            '<table>'+
                '<thead>'+
                    '<tr>'+
                        '<th>圖片</th>'+
                        '<th>名稱</th>'+
                        '<th>價格</th>'+
                        '<th>操作</th>'+
                        '<th>刪除</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody>'
	for(var i=0; i < products.length; i++){
		content+=
                    '<tr id="likeProduct'+ products[i].id +'">'+
                        '<td class="product-thumbnail">'+
                            '<a href="#"><img src="' + imageURL + '?productId=' + products[i].id +'" alt="" width=150 height=150></a>'+
                        '</td>'+
                        '<td class="product-name"><a href="#">'+products[i].name+'</a></td>'+
                        '<td class="product-price-cart"><span class="amount">$'+products[i].discountPrice+'</span></td>'+
                        '<td class="product-wishlist-cart">'+
                            '<a href="#" onclick="addToCart(' + products[i].id + ')">加入購物車</a>'+
                        '</td>'+
                        '<td class="product-wishlist-cart">'+
                        '<a href="#" onclick="removeLike(' + products[i].id + ')">刪除</a>'+
                    '</td>'+
                    '</tr>'
                    
               
		}
	content+=
	'</tbody>'+
    '</table>'+
'</div>'+
'</form>';
	document.getElementById("content").innerHTML = content;
}
function removeLike(productId){
	if (${empty LoginOK}){
		swal("請登入");
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
			var parentObj = document.getElementById("likeProduct"+productId).parentNode;
			parentObj.removeChild(document.getElementById("likeProduct" + productId));
		}
	}
}
</script>
</head>
<body>
<jsp:include page="../fragments/headerArea.jsp" />
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">

			<div class="breadcrumb-content text-center">
				<h2>會員中心</h2>
				<ul>
					<li><a href="<c:url value='/' />">首頁</a></li>
					<li><a href="<c:url value='/member/myAccount' />">會員中心</a></li>
					<li class="active">商品最愛</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />
<div class="project-count-area pb-70 pt-100">
		<div class="container">
			<div class="row flex-row-reverse">
				<div class="col-lg-12 col-md-8"">
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-3 col-md-12">
							<jsp:include page="../members/fragments/myAccountLeftArea.jsp" />
						</div>
						<div class="col-lg-9 col-md-12" id="content">
							
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>


		<jsp:include page="../fragments/footerArea.jsp" />
		<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>