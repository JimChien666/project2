<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
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
<title>產品更新</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<link REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
</head>

<BODY>
<h2>
產品購物商城
</h2>
<!-- 設定變數funcName的值, top.jsp會使用此變數-->
<c:set var="funcName" value="SHO" scope="session"/>
<!-- 引入共同的頁首 -->
<%-- <jsp:include page="../nn/top.jsp" /> --%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#" >PetMe!陪你</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar" >
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/products' />">商品首頁</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/insert/products' />">新增商品</a>
      </li>  
    </ul>
  </div>  
</nav>
<div class="container">
	<form:form  method="POST" action="${pageContext.servletContext.contextPath}/product/processCreateProduct.controller" modelAttribute="products" enctype="multipart/form-data"  >
		<b>
		商品名稱:<form:input path="name"/><span style="color: red;">${errors.name}</span><br/>
		商品價格:<form:input path="price"/><span style="color: red;">${errors.price}</span><br/>
		商品折扣:<form:input path="discount"/><span style="color: red;">${errors.discount}</span><br/>
		商品圖片:<form:input path="multipartFile" type="file" /><span style="color: red;">${errors.multipartFile}</span><br/>
		商品內容圖片:<input name="contentImage" type="file" multiple/><span style="color: red;">${errors.multipartFile}</span><br/>
		商品描述:<form:input path="description"/><span style="color: red;">${errors.description}</span><br/>
		商品數量:<form:input path="quantity"/><span style="color: red;">${errors.quantity}</span><br/>
		商品狀態:上架中<form:radiobutton style="width:20px;height:20px;" path="status" value="上架中"/>
          	   已下架<form:radiobutton style="width:20px;height:20px;" path="status" value="已下架"/><span style="color: red;">${errors.status}</span><br/>
        <div id='somedivS'></div>
         ${errors.color}<br/>${errors.category}<br/>${errors.animalType}<br/> 
		</b>
        <form:button value="Send" class="btncls">送出</form:button>
	</form:form>
</div>	
     
</BODY>
</HTML>