 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>產品新增資料</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>


<!-- 設定變數funcName的值, top.jsp會使用此變數-->
<c:set var="funcName" value="CHE" scope="session"/>
<!-- 引入共同的頁首 -->
<jsp:include page="../nn/top.jsp" />

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#" >Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar" ">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/jim/InsertProduct.jsp' />">新增商品</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/jim/QueryProduct.jsp' />">查詢商品</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/jim/DeleteProduct.jsp' />">刪除商品</a>
      </li>    
    </ul>
  </div>  
</nav>



<h2>
產品新增資料
</h2>
<!-- 	產品價格判斷 -->
	<script type="text/javascript">
		function checkProductPrice() {
			let productPrice = document.getElementById("price").value;
			let productPriceLength = productPrice.length;
			let productPriceSpan = document.getElementById("productPriceSpan");
			let flag1 = false;
			if (price == "") {
				document.getElementById("productPriceSpan").style.display = "block";
				memberIdSpan.innerHTML = "不可空白";
			}
		}
		function checkProductQuantity() {
			let productQuantity = document.getElementById("quantity").value;
			let productQuantityLength = productQuantity.length;
			let productPriceSpan = document.getElementById("productQuantitySpan");
			let flag1 = false;
			if (price == "") {
				document.getElementById("productQuantitySpan").style.display = "block";
				memberIdSpan.innerHTML = "不可空白";
			}
		}
		
	</script>
<form enctype='multipart/form-data' action="../InsertProduct" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td>產品名稱:</td>
    <td><input type="text" name="name" size="10" maxlength="100"></td>
</tr>
<tr>
    <td>產品價格:</td>
    <td><input type="text" name="price" size="10" maxlength="10" onblur="checkProductPrice()" id="price" onkeyup="this.value=this.value.replace(/[^\d]/,'')" required>
    <span id="productPriceSpan" class="spanHidden"><br></span>
    </td>
</tr>
<tr>
    <td>產品圖片:</td>
    <td><input enctype="multipart/form-data" type="file" name="img" size="10" maxlength="10"></td>
</tr>
<tr>
    <td>產品描述:</td>
    <td><input type="text" name="descript" size="10" maxlength="500"></td>
</tr>
<tr>
    <td>產品數量:</td>
    <td><input type="text" name="quantity" size="10" maxlength="500" onblur="checkProductquantity()" id="quantity" onkeyup="this.value=this.value.replace(/[^\d]/,'')" required>
        <span id="productQuantitySpan" class="spanHidden"><br></span>
    </td>
</tr>
</table>
	<center>
				<a href="<%=application.getContextPath()%>/jim/InsertProduct.jsp" class="btn btn-secondary">重填</a>
				<c:choose>
				<c:when test="">
				</c:when>
				</c:choose>
				<button type="submit" name="submit" class="btn btn-primary">送出</button>
	
	</center>
</form>
</body>
</html>