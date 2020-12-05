<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>維護產品資料</title>
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
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
			content += "<option value='' disabled selected='selected'>請選擇分類</option>"
			var categories = JSON.parse(xhr.responseText);
			for(var i=0; i < categories.length; i++){
			    content += 	"<option value='" + categories[i].id + "'>" + categories[i].name + "</option>";
			}
			content += "</select>"+"<span style='color: red;'>${errors.category}</span>" ;
			var divs = document.getElementById("somedivS");
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
			content += "<option value='' disabled selected='selected'>請選擇顏色</option>"
			var colors = JSON.parse(xhr.responseText);
			for(var i=0; i < colors.length; i++){
			    content += 	"<option value='" + colors[i].id + "'>" + colors[i].name + "</option>";
			}
			content += "</select>"+"<span style='color: red;'>${errors.color}</span>" ;
			var divs = document.getElementById("somedivS");
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
			content += "<option value='' disabled selected='selected'>請選擇寵物類別</option>"
			var animalTypes = JSON.parse(xhr.responseText);
			for(var i=0; i < animalTypes.length; i++){
			    content += 	"<option value='" + animalTypes[i].id + "'>" + animalTypes[i].name + "</option>";
			}
			content += "</select>"+"<span style='color: red;'>${errors.animalType}</span>";
			var divs = document.getElementById("somedivS");
			divs.innerHTML += content;
			divs.innerHTML += "<br/>";
		}
	}
}

</script>
<jsp:include page="../fragments/links.jsp" />
<style>
.btncls {
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

button.btncls:hover {
	background-color: #000000;
}
</style>
<!-- 引入JS -->
<script src="<c:url value='/js/product.js' />" type="text/javascript" charset="UTF-8"></script>
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
	<jsp:include page="../fragments/headerArea.jsp" />

	<form:form method="POST"
		action="${pageContext.servletContext.contextPath}/product/processUpdateProduct.controller"
		modelAttribute="products" enctype="multipart/form-data">
		<div class="container">
			*商品名稱:
			<form:input path="name" name="name" value="${products.name} " />
			<span style="color: red;">${errors.name}</span><br />
			 *商品價格:
			<form:input path="price" />
			<span style="color: red;">${errors.price}</span><br /> 
			*商品折扣:
			<form:input path="discount" />
			<span style="color: red;">${errors.discount}</span><br /> 
			

<!--商品封面圖===================================================================================== -->
			<div class="mb-20">	
				<form:label path="multipartFile" class="mb-0 font22">*商品圖片:</form:label>
				<form:input path="multipartFile" type="file" id="productFilesUpdate"/>
			</div>
			<span style="color: red;">${errors.multipartFile}</span><br />
						

			<!--原始圖片區域 ===================================================================================== -->			
			<div class="mb-20" >				
				<div id="productFilesDivOrigin">
					<img style='height: 25%; width: 25%;'
						src='${pageContext.request.contextPath}/product/getProductImage/?productId=${products.id}'>
				</div>
			<!--隱藏的預覽圖區域 ===================================================================================== -->
				<div style="display:none;"  id="productFilesDivAlter">
			<!--此處是由product.js使用JQuery $(#xx).show(),不能用於 visibility:hidden 的元素 ===================================================================================== -->				
					<img style="height: 25%; width: 25%;" class="cardImg marginAuto" id="preview_productFiles" src="#" /><br>
				</div>
			</div>	
<!--商品內容圖 ===================================================================================== -->
			<div class="mb-20">
				<label name="contentImage" class="mb-0 font22">*商品內容圖片(請選擇兩張圖):</label>
				<input name="contentImage" type="file" multiple id="productContentUpdate"/>
<!-- 				<input name="contentImage" type="file" multiple /> -->
				<span style="color: red;">${errors.multipartFile}</span><br />
			</div>
			<div id='productContentDivOrigin'>
				<c:forEach items="${products.productFilesId}" var="contentFileId"
					varStatus="s">
					<img  class='btnFollow${s.index}' style='height: 25%; width: 25%;'
						src='${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}'>
				</c:forEach>
			</div>
					<!--隱藏的內容預覽圖區域 ===================================================================================== -->
					<div style="display: none;" id="productContentDivAlter">
					<!--此處是由product.js使用JQuery $(#xx).show(),不能用於 visibility:hidden 的元素 ===================================================================================== -->
					<c:forEach items="${products.productFilesId}" var="contentFileId"
					varStatus="s">
						
						<img style="height: 25%; width: 25%;" class="cardImg marginAuto"
							id="preview_productContent${s.index}" src="#" /><br>
					</c:forEach>
					
					</div>				

			<!-- ===================================================================================== -->
			*商品描述:
			<form:textarea path="description" rows="5" cols="18" />
			<span style="color: red;">${errors.description}</span><br /> 
			*商品數量:
			<form:input path="quantity" />
			<span style="color: red;">${errors.quantity}</span><br /> 
			*商品狀態:上架中
			<form:radiobutton style="width:20px;height:20px;" path="status"
				value="上架中" />
			已下架
			<form:radiobutton style="width:20px;height:20px;" path="status"
				value="已下架" />
			<span style="color: red;">${errors.status}</span>
			<!-- 		用來動態顯示資料庫顏色、分類、動物種類 -->
			<div id='somedivS'>
				${errors.color}<br />${errors.category}<br />${errors.animalType}<br />
			</div>

		<form:button value="Send" class="btncls">送出</form:button>
		</div>
	</form:form>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</BODY>
</HTML>