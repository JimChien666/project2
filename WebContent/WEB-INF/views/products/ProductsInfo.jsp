<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>

<title>商品詳情</title>

<script>
	$(document).ready(function(){
		$("#button1").mouseover(function(){
		   	$("#button1").attr("style","background-color:#E9E9E4; width:200px; color:#ab7661; border:1px #ab7661 solid;");
		}); 
		$("#button1").mouseout(function(){
		   	$("#button1").attr("style","background-color:#ab7661; width:200px;");
		}); 
	  $(".btnFollow0").click(function(){
				<c:forEach  items="${ProductsInfo.productFilesId}" var="contentFileId" step="2">
	          	$("#chgicon").attr("src","${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}");
// 	          	$("#chgicon").attr("style","border:1px black solid; height:40%; width:450px;");
				</c:forEach>
	          	$(".btnFollow0").attr("style","border:2px #ab7661 solid; height:120px; width:120px;");
	           	$(".btnFollow1").attr("style","border:0px #ab7661 solid; height:120px; width:120px;");	
	           	$(".btnFollow2").attr("style","border:0px #ab7661 solid; height:120px; width:120px;");
		});
      $(".btnFollow1").click(function(){
			<c:forEach  items="${ProductsInfo.productFilesId}" var="contentFileId">
            	$("#chgicon").attr("src","${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}");
//             	$("#chgicon").attr("style","border:1px black solid; height:40%; width:450px;");
 
			</c:forEach>
           	$(".btnFollow1").attr("style","border:2px #ab7661 solid; height:120px; width:120px;");	
           	$(".btnFollow2").attr("style","border:0px #ab7661 solid; height:120px; width:120px;");	
           	$(".btnFollow0").attr("style","border:0px #ab7661 solid; height:120px; width:120px;");	
		});

      $(".btnFollow2").click(function(){
            	$("#chgicon").attr("src","${pageContext.request.contextPath}/product/getProductImage/?productId=${ProductsInfo.id}");
            	$(".btnFollow2").attr("style","border:2px #ab7661 solid; height:120px; width:120px;");	 
               	$(".btnFollow1").attr("style","border:0px #ab7661 solid; height:120px; width:120px;");	
               	$(".btnFollow0").attr("style","border:0px #ab7661 solid; height:120px; width:120px;");	
		});
	});
	
	
	//若productId為零,後端會直接回傳購物車列表
	function addToCart(productId){
		
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

			document.getElementById("shopCart").innerHTML=num;
			}
		
		}
	}

</script>
<jsp:include page="../fragments/links.jsp" />
<jsp:include page="../fragments/getIndexData.jsp" />
</head>

<body>
<jsp:include page="../fragments/headerArea.jsp" />
<%-- <jsp:include page="../public/top.jsp" /> --%>
<!-- TOP圖片麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img" style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
            <div class="container">
                <div class="breadcrumb-content text-center">
                    <h2>購物</h2>
                    <ul>
                        <li><a href="<c:url value='/' />">首頁</a></li>
                        <li class="active"><a href="<c:url value='/product/ProductList' />">購物</a></li>
                    </ul>
                </div>
            </div>
        </div>
        

<div align='center'>
	<h3>商品資訊</h3>
	
<div style='position:absolute; top:425px;  right:100px; font-family:Microsoft JhengHei;'>	
	<p style='font-family:cwtexhei; border-left:18px #ab7661 solid;'> ${ProductsInfo.name}</p>
	<p style='color:#ab7661; font-size:22px;'><b> NT$${ProductsInfo.price}</b></p>
	<button id='button1' class='btn btn-danger' onclick='addToCart(${ProductsInfo.id})'  style='background-color:#ab7661; width:200px;'>加入購物車</button>
	<br/>
<!-- 	<a style='color:#ab7661;' href='http://tw.yahoo.com/' >♡加入追蹤清單</a> -->
</div>
<div style='position:absolute; top:425px;  left:100px; '>

	<c:forEach  items="${ProductsInfo.productFilesId}" var="contentFileId" varStatus="s">
		<img  class='btnFollow${s.index}' style='height:120px; width:120px;' src='${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}'>
		<br/>
		<br/>
	</c:forEach>
	<img class='btnFollow2' style='height:120px; width:120px; border:2px #ab7661 solid;' src='${pageContext.request.contextPath}/product/getProductImage/?productId=${ProductsInfo.id}'>

</div>	

	<!-- 	抓ProductListController/getProductImage的封面圖 -->
	<img id='chgicon' style='border:1px black solid; height:450px; width:450px;' src='${pageContext.request.contextPath}/product/getProductImage/?productId=${ProductsInfo.id}'>
	<div style="font-size:22px">
		<br/>
		<hr/>
		${ProductsInfo.description}
		<br/>
	</div>
<c:forEach  items="${ProductsInfo.productFilesId}" var="contentFileId">
		<img src='${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}'>
		<br/>
		<br/>
</c:forEach>
	<br>
	<div class="container">
	
	<div><h5>猜你喜歡</h5></div>
	<div class="row" id="productList"style="border-top: 1px gray solid; padding-top: 10px;"></div>
	</div>
	<hr/>
	
	<a href="<c:url value='/' />">回前頁</a>
</div>
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>