<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>刪除產品頁面</title>
<link rel="stylesheet" href="<c:url value='/css/Product.css' />">
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
<script>
// window.onload = function() {
// 	getColors();
// 	getCategories();
// 	getAnimalTypes();
// }

// function getCategories(){
// 	var xhr = new XMLHttpRequest();
// 	xhr.open("GET", "<c:url value='/product/UpdateCategories' />", true);
// 	xhr.send();
// 	xhr.onreadystatechange = function() {
// 		if (xhr.readyState == 4 && xhr.status == 200) {
// 			var content = "選擇分類：<div><select name='categoryId'>";
// 			content += "<option value='' disabled selected='selected'>請選擇分類</option>"
// 			var categories = JSON.parse(xhr.responseText);
// 			for(var i=0; i < categories.length; i++){
// 			    content += 	"<option value='" + categories[i].id + "'>" + categories[i].name + "</option>";
// 			}
// 			content += "</select>"+"<span style='color: red;'>${errors.category}</span>" ;
// 			var divs = document.getElementById("somedivS");
// 			divs.innerHTML += content;
// 			divs.innerHTML += "<br/></div>";
// 		}
// 	}
// }

// function getColors(){
// 	var xhr = new XMLHttpRequest();
// 	xhr.open("GET", "<c:url value='/product/UpdateColors' />", true);
// 	xhr.send();
// 	xhr.onreadystatechange = function() {
// 		if (xhr.readyState == 4 && xhr.status == 200) {
// 			var content = "商品顏色：<div><select name='colorId'>";
// 			content += "<option value='' disabled selected='selected'>請選擇顏色</option>"
// 			var colors = JSON.parse(xhr.responseText);
// 			for(var i=0; i < colors.length; i++){
// 			    content += 	"<option value='" + colors[i].id + "'>" + colors[i].name + "</option>";
// 			}
// 			content += "</select>"+"<span style='color: red;'>${errors.color}</span>" ;
// 			var divs = document.getElementById("somedivS");
// 			divs.innerHTML += content;
// 			divs.innerHTML += "</div><br/>";
// 		}
// 	}
// }


// function getAnimalTypes(){
// 	var xhr = new XMLHttpRequest();
// 	xhr.open("GET", "<c:url value='/product/UpdateAnimalTypes' />", true);
// 	xhr.send();
// 	xhr.onreadystatechange = function() {
// 		if (xhr.readyState == 4 && xhr.status == 200) {
// 			var content = "寵物類別：<div><select name='animalTypeId'>";
// 			content += "<option value='' disabled selected='selected'>請選擇寵物類別</option>"
// 			var animalTypes = JSON.parse(xhr.responseText);
// 			for(var i=0; i < animalTypes.length; i++){
// 			    content += 	"<option value='" + animalTypes[i].id + "'>" + animalTypes[i].name + "</option>";
// 			}
// 			content += "</select>"+"<span style='color: red;'>${errors.animalType}</span>";
// 			var divs = document.getElementById("somedivS");
// 			divs.innerHTML += content;
// 			divs.innerHTML += "</div><br/>";
// 		}
// 	}
// }
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
		action="${pageContext.servletContext.contextPath}/product/processDeteleProduct.controller"
		modelAttribute="products" enctype="multipart/form-data" >
		<div class="container" >

			<form:input path="id" class="divHidden" /> 
			*商品名稱:
			<form:input path="name"  disabled="true"/>
			<span style="color: red;">${errors.name}</span><br />
			 *商品價格:
			<form:input path="price" disabled="true"/>
			<span style="color: red;">${errors.price}</span><br /> 
			*商品折扣:
			<form:input path="discount" disabled="true"/>
			<span style="color: red;">${errors.discount}</span><br /> 
			

<!--商品封面圖===================================================================================== -->
			<div class="mb-20">	
				<form:label path="multipartFile" >*商品圖片:</form:label>
				<form:input path="multipartFile" type="file" class="divHidden" id="productFilesUpdate"/>
<%-- 				<form:input path="multipartFile" type="file" id="productFilesUpdate"/> --%>
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
					<img style="height: 25%; width: 25%;"  id="preview_productFiles" src="#" /><br>
				</div>
			</div>	
<!--商品內容圖 ===================================================================================== -->
			<div class="mb-20">
				*商品內容圖片
				<input name="contentImage" type="text" class="divHidden" multiple id="productContentUpdate"/>
<!-- 				<input name="contentImage" type="file" multiple /> -->
				<span style="color: red;">${errors.multipartFile}</span><br />
			</div>
			<div id='productContentDivOrigin'>
				<c:forEach items="${products.productFilesId}" var="contentFileId" varStatus="s">
					<img  class='btnFollow${s.index}' style='height: 25%; width: 25%;'
						src='${pageContext.request.contextPath}/product/getProductFilesImage/?productFilesId=${contentFileId}'>
				</c:forEach>
			</div>
			<!--隱藏的內容預覽圖區域 ===================================================================================== -->
			<div style="display: none;" id="productContentDivAlter">
					<!--此處是由product.js使用JQuery $(#xx).show(),不能用於 visibility:hidden 的元素 ===================================================================================== -->
				<c:forEach items="${products.productFilesId}" var="contentFileId"
				varStatus="s">
						
				<img style='height: 25%; width: 25%;' 
					id="preview_productContent${s.index}" src="#" />
				</c:forEach>
					
			</div>				

			<!-- ===================================================================================== -->
			*商品描述:
			<form:textarea path="description" rows="5" cols="18" disabled="true"/>
			<span style="color: red;">${errors.description}</span><br /> 
			*商品數量:
			<form:input path="quantity" disabled="true"/>
			<span style="color: red;">${errors.quantity}</span><br /> 
			<!--讀商品狀態的屬性===================================================================================== -->
			<div>
				*商品狀態:<br/>			
				<c:choose >
					<c:when test="${products.status == '已下架'}">
						<form:radiobutton disable="true" checked="checked" style="width:20px;height:20px;"	path="status" value="已下架" />
						已下架
					</c:when>
					<c:otherwise>
						<form:radiobutton disable="true" checked="checked" style="width:20px;height:20px;" path="status"	value="上架中"/>
						上架中
					</c:otherwise>
				</c:choose>
				<span style="color: red;">${errors.status}</span>
			</div>
			<!--商品顏色 ===================================================================================== -->
			<div class="mb-20">
				<form:label path="colorId" class="" >*商品顏色： </form:label>				 	
				<span style="color: red;">${errors.color}</span><br /> 
					<form:select path="colorId" disabled="true" style="background-color:RGB(236,239,248);height:50px;">								
						<c:forEach items="${Colors}" var="Colors" >
							<c:choose>
								<c:when test="${Colors.name == products.color.name}">
									<option selected value="${products.color.id}">${products.color.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${Colors.id}">${Colors.name}</option>
								</c:otherwise>
							</c:choose>			
						</c:forEach>
					</form:select> 	
			</div>
			<!--商品分類 ===================================================================================== -->
			<div class="mb-20">
				<form:label path="categoryId" class="">*商品分類： </form:label>				 	
				<span style="color: red;">${errors.category}</span><br /> 
					<form:select path="categoryId" disabled="true" style="background-color:RGB(236,239,248);height:50px;">								
						<c:forEach items="${Categories}" var="Categories" >
							<c:choose>
								<c:when test="${Categories.name == products.category.name}">
									<option selected value="${products.category.id}">${products.category.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${Categories.id}">${Categories.name}</option>
								</c:otherwise>
							</c:choose>			
						</c:forEach>
					</form:select> 	
			</div>
			
			<!--寵物類別 ===================================================================================== -->
			<div class="mb-20">
				<form:label path="animalTypeId" >*寵物類別：</form:label>				 	
				<span style="color: red;">${errors.animalType}</span><br /> 
					<form:select path="animalTypeId" disabled="true" style="background-color:RGB(236,239,248);height:50px;">								
						<c:forEach items="${AnimalTypes}" var="AnimalTypes" >
							<c:choose>
								<c:when test="${AnimalTypes.name == products.animalType.name}">
									<option selected value="${products.animalType.id}">${products.animalType.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${AnimalTypes.id}">${AnimalTypes.name}</option>
								</c:otherwise>
							</c:choose>			
						</c:forEach>
					</form:select> 	
			</div>	
			<!-- 		用來動態顯示資料庫顏色、分類、動物種類 -->
<!-- 			<div id='somedivS'> -->
<%-- 				${errors.color}<br />${errors.category}<br />${errors.animalType}<br /> --%>
<!-- 			</div> -->
			<span >	
				<a href="<c:url value='/product/preDeleteProduct' />/${products.id}"
				 class="btn-style-cancel btn-style-border" style="font-size:13px;height:45px;width:85px; ">回復</a>
			</span>
		<form:button onclick="myFunction()" value="Send" class="btncls btn-style-border" >送出</form:button>
		</div>
	</form:form>
	<script>
		function myFunction() {
			swal("刪除完成");
		}
	</script>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</BODY>
</HTML>