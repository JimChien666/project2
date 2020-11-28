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
</script>
<jsp:include page="../fragments/links.jsp" />
</head>

<body>
<jsp:include page="../fragments/headerArea.jsp" />
<%-- <jsp:include page="../public/top.jsp" /> --%>
<div align='center'>
	<h3>商品資訊</h3>
	
<div style='position:absolute; top:134px;  right:100px; font-family:Microsoft JhengHei;'>	
	<p style='font-family:cwtexhei; border-left:18px #ab7661 solid;'> ${ProductsInfo.name}</p>
	<p style='color:#ab7661; font-size:22px;'><b> NT$${ProductsInfo.price}</b></p>
	<button id='button1' class='btn btn-danger' onclick='addToCart(" + products[i].id + ")'  style='background-color:#ab7661; width:200px;'>加入購物車</button>
	<br/>
	<a style='color:#ab7661;' href='http://tw.yahoo.com/' >♡加入追蹤清單</a>
</div>
<div style='position:absolute; top:134px;  left:100px; '>

	<c:forEach  items="${ProductsInfo.productFilesId}" var="contentFileId" varStatus="s">
		<img  class='btnFollow${s.index}' style='height:120px; width:120px;' src='${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}'>
		<br/>
		<br/>
	</c:forEach>
	<img class='btnFollow2' style='height:120px; width:120px; border:2px #ab7661 solid;' src='${pageContext.request.contextPath}/product/getProductImage/?productId=${ProductsInfo.id}'>

</div>	

	<!-- 	抓ProductListController/getProductImage的封面圖 -->
	<img id='chgicon' style='border:1px black solid; height:450px; width:450px;' src='${pageContext.request.contextPath}/product/getProductImage/?productId=${ProductsInfo.id}'>

	
	${ProductsInfo.description}
<c:forEach  items="${ProductsInfo.productFilesId}" var="contentFileId">
		<img src='${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}'>
		<br/>
		<br/>
</c:forEach>
	
	<div id="selectBar"></div>
	<div id='somedivS'  style='height:260px;'></div>
	<div id='navigation' style='height:60px;'></div>
	<hr>
	
	<a href="<c:url value='/' />">回前頁</a>
</div>
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>