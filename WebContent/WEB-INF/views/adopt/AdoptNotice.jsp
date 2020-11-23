<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>Blog</h2>
				<ul>
					<li><a href="index.html">home</a></li>
					<li class="active">Blog</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- ============================================================================================= -->

	<h1>動物認養申請書</h1>
	<input type="text" class="input1 inputHW inputBorder">
	<h5 class="div1">已年滿20歲，並具有飼養之能力及場所，願向貴處認養動物乙隻，詳細資料如下:</h5>
	<div>收容編號：</div><div></div>
	<div>動物類別：</div><div></div>
	<div>動物品種：</div><div></div>
	<div>性別：</div><div></div>
	<div>毛色：</div><div></div>

	<!-- ============================================================================================= -->

	<div>
		<a href="<c:url value='/adoptApply'/>">儲存</a>
	</div>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>