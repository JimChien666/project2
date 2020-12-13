<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>找不到商品</title>
<link rel="stylesheet" href="<c:url value='/css/Product.css' />">
<script type="text/javascript"	src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
<jsp:include page="../fragments/links.jsp" />
<script src="<c:url value='/js/product.js' />" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<jsp:include page="../fragments/headerArea.jsp" />
	<div align="center">
		<h2>找不到商品</h2>
	
		<a href="<c:url value='/' />">首頁</a>
	</div>
<jsp:include page="../fragments/footerArea.jsp" />
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>