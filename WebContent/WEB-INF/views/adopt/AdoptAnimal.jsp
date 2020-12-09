<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>領養</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
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
				<h2>領養動物</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li class="active">全部動物</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="blog-area pt-50 pb-50 clearfix">
		<div class="container">
			<div class="row">
				<div class="page1">
					<c:forEach var="AnimalsList" items="${AnimalsList}">
						<div class="col-xl-3 col-lg-4 col-md-6 f-left">
							<div class="blog-wrapper mb-15 mt-15 gray-bg card2 pointerCursor" onclick="location.href='<c:url value='/AdoptAnimalDetails.controller?id=${AnimalsList.animalId}' />'">
								<div class="hover-effect square250px">
									<img class="cardImg marginAuto"
										alt=""
										src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${AnimalsList.animalId}">
								</div>
								<div class="blog-content form1 font22 H260">
									<div class="maxH70 cardContentW">
										<ul>
											<!-- <li><div class="div1">動物編號：&nbsp;</div> -->
											<%-- <div class="div1">${AnimalsList.animalId}</div> <br></li> --%>
											<!-- ===================================================================================== -->
											<li><div>領養地區：&nbsp;</div>
												<div>${AnimalsList.member.address}</div> <br></li>
											<!-- ===================================================================================== -->
<!-- 											<li><div class="div1">收容動物編號：&nbsp;</div> -->
<%-- 												<div class="div1 mb-10">${AnimalsList.acceptionId}</div> <br></li> --%>
											<!-- ===================================================================================== -->
											<li><div>品種：</div>
												<div>${AnimalsList.breeds.breed}</div> <br></li>
											<!-- ===================================================================================== -->
											<li><c:choose>
													<c:when test="${AnimalsList.gender == 1}">
														<div>性別：&nbsp;</div>公<br>
													</c:when>
													<c:otherwise>
														<div>性別：&nbsp;</div>母<br>
													</c:otherwise>
												</c:choose></li>
											<!-- ===================================================================================== -->
											<li><div>毛色：</div>
												<div>${AnimalsList.coatColor}</div> <br></li>
											<!-- ===================================================================================== -->
<%-- 											<li><c:choose> --%>
<%-- 													<c:when test="${AnimalsList.isAdoptionAvailable == 1}"> --%>
<!-- 														<div>是否開放領養：&nbsp;</div>是<br> -->
<%-- 													</c:when> --%>
<%-- 													<c:otherwise> --%>
<!-- 														<div>是否開放領養：&nbsp;</div>否<br> -->
<%-- 													</c:otherwise> --%>
<%-- 												</c:choose></li> --%>
											<!-- ===================================================================================== -->
<!-- 											<li><div>備註：&nbsp;</div> -->
<%-- 												<div class="note1 mb-10">${AnimalsList.note}</div> <br></li> --%>
										</ul>
									</div>
								</div>
<!-- 								<div class="about-us-btn divCenter"> -->
<%-- 									<a class="btn-style1 font22" href="<c:url value='/adoptNotice'/>?animalId=${AnimalsList.animalId}">我要認養</a> --%>
<!-- 								</div> -->
								<!-- ===================================================================================== -->
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="pagination-style text-center mt-20">
				<ul>
					<li><a href="#"><i class="icon-arrow-left"></i></a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a class="active" href="#"><i class="icon-arrow-right"></i></a>
					</li>
				</ul>
			</div>
		</div>
	</div>


	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>