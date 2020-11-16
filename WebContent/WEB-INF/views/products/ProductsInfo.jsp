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
      $(".btnFollow").click(function(){
            $("#chgicon").attr("src","${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}");
	      });
	});
</script>

</head>
<body>
<jsp:include page="../public/top.jsp" />
<div align='center'>
	<h3>商品資訊</h3>
	
	
	${ProductsInfo.price}
	
<!-- 	抓ProductListController/getProductImage的封面圖 -->
	<a class="btnFollow" title="JQuery 換圖功能">JQuery 換圖功能</a>
	<img style='border:2px black solid;'  id='chgicon' height='400' weight='400' src='${pageContext.request.contextPath}/product/getProductImage/?productId=${ProductsInfo.id}'>

	
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
</body>
</html>