<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>動物資料</title>
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
				<h2>動物資料</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<c:choose>
						<c:when test="${source=='ReadAnimal'}">
							<li class="active">會員中心</li>
							<li><a href="<c:url value='/ReadAnimal'/>">我的寵物</a></li>
							<li class="active">動物資料</li>
						</c:when>
						<c:when test="${source=='AdoptAnimal'}">
							<li><a href="<c:url value='/adopt'/>">全部動物</a></li>
							<li class="active">動物資料</li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>

	<!-- ============================================================================================= -->

	<!-- 	<div class="mt-50 divCenter"> -->
	<!-- 		<h1>動物資料</h1> -->
	<!-- 	</div> -->
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
			收容編號：${animal.acceptionId}<br> 領養地區：${animal.member.address}<br>
			動物類別：${animal.breeds.family}<br> 動物品種：${animal.breeds.breed}<br>
			性別：
			<c:if test="${animal.gender==1}">
			公<br>
			</c:if>
			<c:if test="${animal.gender==0}">
			母<br>
			</c:if>
			毛色：${animal.coatColor}<br> 是否開放認領養：
			<c:if test="${animal.isAdoptionAvailable==1}">
			是<br>
			</c:if>
			<c:if test="${animal.isAdoptionAvailable==0}">
			否<br>
			</c:if>
			備註：${animal.note}<br>
		</div>
	</div>

	<div class="divCenter">
		<div class="div1"></div>
		<div class="div1">
			<div class="about-us-btn mb-50">
				<c:choose>
					<c:when test="${source=='ReadAnimal'}">
						<a href="<c:url value='/ReadAnimal' />"
							class="btn-style-cancel btn-style-border">返回</a>
						<a class="btn-style1 btn-style-border"
							href="<c:url value='/preUpdateAnimal.controller'/>?animalId=${animal.animalId}">維護</a>
					</c:when>
					<c:when test="${source=='AdoptAnimal'}">
						<a href="<c:url value='/adopt' />"
							class="btn-style-cancel btn-style-border">返回</a>
						<a class="btn-style1 btn-style-border"
							href="<c:url value='/adoptNotice'/>?animalId=${animal.animalId}">我要認養</a>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>