<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/eDM.css" type="text/css" /> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品首頁</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>   

</head>

<BODY>
<%-- <jsp:include page="../nn/top.jsp" /> --%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#" >PetMe!陪你</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar" ">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/products' />">商品首頁</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/insert/products' />">新增商品</a>
      </li>
<!--       <li class="nav-item"> -->
<%--         <a class="nav-link" href="<c:url value='/select/allproducts' />">查詢商品</a> --%>
<!--       </li> -->
    </ul>
  </div>  
</nav>

<h2>
產品購物商城
</h2>
<form action="/team6/select/productsByName" method="post">
<div>
	    產品名稱:
	 <input type="text" name="keyword" size="10" maxlength="100">
	 <input class="btn btn-primary" type="submit" name="submit" value="查詢">
</div>


</form>
<!-- 設定變數funcName的值, top.jsp會使用此變數-->
<c:set var="funcName" value="SHO" scope="session"/>
<!-- 引入共同的頁首 -->

<div align='center'>

<TABLE  BORDER=1>
	<TR> <TH>產品編號</TH><TH>商品名稱</TH><TH>圖片</TH><TH>產品價格</TH><TH>產品描述</TH><TH colspan="2">產品維護</TH></TR>
<c:forEach var="ProductList" items="${ProductList}" >
<TR>
			<TD>${ProductList.id}</TD>
			<TD>${ProductList.name}</TD>
			<TD><img height='200px' width='100px'
			src='${pageContext.request.contextPath}/image/${ProductList.id}'>
			</TD>												
			<TD>${ProductList.price}</TD>
			<TD>${ProductList.descript}</TD>
			<TD><input class="btn btn-primary" type ="button" onclick="javascript:location.href='/team6/postSelectById/products/${ProductList.id}'" value="修改"></input></TD>
			<TD><input class="btn btn-danger" type ="button" onclick="javascript:location.href='/team6/getSelectById/products/${ProductList.id}'" value="刪除"></input></TD>
</TR>	
</c:forEach>
</TABLE>
</div>

<!-- <div id="bpaging">以下為控制第一頁、前一頁、下一頁、最末頁 等超連結 -->
<!-- <table border="1"> -->
<!-- 	<tr align="center"> -->
<%-- 		<td width='80' height='20'><c:if test="${pageNo > 1}"> --%>
<!-- 			<div id="blfirst"><a -->
<%-- 				href="<c:url value='ProductsPage?pageNo=1' />"> <img --%>
<!-- 				border='0' alt='第一頁' height='30' width='30' -->
<!-- 				src='../jim/images/first-icon.png' /> </a></div> -->
<%-- 		</c:if></td> --%>
<%-- 		<td width='80'><c:if test="${pageNo > 1}"> --%>
<!-- 			<div id="blprev"><a -->
<%-- 				href="<c:url value='ProductsPage?pageNo=${pageNo-1}' />"> --%>
<!-- 			<img border='0' alt='前一頁' height='30' width='30' -->
<!-- 				src='../jim/images/prev-icon.png' /></a></div> -->
<%-- 		</c:if></td> --%>
<%-- 		<td width='76'>${pageNo} / ${totalPages}</td> --%>
<%-- 		<td width='80'><c:if test="${pageNo != totalPages}"> --%>
		
<!-- 			<div id="blnext"><a -->
<%-- 				href="<c:url value='ProductsPage?pageNo=${pageNo+1}' />"> --%>
<!-- 			<img border='0' alt='下一頁' height='30' width='30' -->
<!-- 				src='/jim/images/next-icon.png' /> </a></div> -->
<%-- 		</c:if></td> --%>
<%-- 		<td width='80'><c:if test="${pageNo != totalPages}"> --%>
<!-- 			<div id="bllast"><a -->
<%-- 				href="<c:url value='ProductsPage?pageNo=${totalPages}' />"> --%>
<!-- 			<img border='0' alt='最末頁' height='30' width='30' -->
<!-- 				src='../jim/images/last-icon.png' /> </a></div> -->
<%-- 		</c:if></td> --%>
<!-- 	</tr> -->
<!-- </table> -->
<!-- </div> -->

             
</BODY>
</HTML>