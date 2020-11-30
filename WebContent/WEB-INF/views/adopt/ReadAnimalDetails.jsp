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

	<div class="mt-50 divCenter">
		<h1>動物資料</h1>
	</div>
	<div class="mt-50 mb-50 wid1000px">
		<div class="H700W540 pl-20 pr-20 pt-20 bgcGray div1">
			<div class="square500px">
				<img class="cardImg marginAuto" alt=""
					src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${animal.animalId}">
			</div>
			<div class="inputBorder mt-20"></div>
			<div class="pr-20 pt-20">
				<div class="square100px borderOriginGray" id="imgChange">
					<img class="cardImg marginAuto" alt=""
						src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${animal.animalId}">
				</div>
			</div>
		</div>
		<div class="wid400px div1 posAbs p-10 font22 lineH10">
			是否開放認領養：
			<c:if test="${animal.isAdoptionAvailable==1}">
			是<br>
			</c:if>
			<c:if test="${animal.isAdoptionAvailable==0}">
			否<br>
			</c:if>
			收容編號：${animal.acceptionId}<br> 動物別：${animal.breeds.family}<br>
			動物品種：${animal.breeds.breed}<br> 毛色：${animal.coatColor}<br>
			來源行政區：${animal.member.address}<br> 動物性別：
			<c:if test="${animal.gender==1}">
			公<br>
			</c:if>
			<c:if test="${animal.gender==0}">
			母<br>
			</c:if>
		</div>
	</div>

	<c:choose>
		<c:when test="${source=='ReadAnimal'}">
			<div class="about-us-btn divCenter mb-50 font22">
				<a class="btn-style1 font22"
					href="<c:url value='/preUpdateAnimal.controller'/>?animalId=${animal.animalId}">維護</a>
			</div>
		</c:when>
		<c:when test="${source=='AdoptAnimal'}">
			<div class="about-us-btn divCenter mb-50 font22">
				<a class="btn-style1 font22"
					href="<c:url value='/adoptNotice'/>?animalId=${animal.animalId}">我要認養</a>
			</div>
		</c:when>
	</c:choose>

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>