<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${source == 'MyAnimals'}">
		<title>我的寵物</title>
	</c:when>
	<c:when test="${source == 'AdoptAnimal'}">
		<title>領養</title>
	</c:when>
</c:choose>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
<!-- sweetalert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="<c:url value='/js/animal.js' />" type="text/javascript"
	charset="UTF-8"></script>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<jsp:include page="../fragments/links.jsp" />

<!-- 轉頁載入動畫1 -->
<link rel="stylesheet"
	href="<c:url value='/css/loadingAnimation.css' />">
</head>
<body>
	<div id="loader"></div>
	<div style="display: none;" id="myDiv" class="animate-bottom">
		<!-- 轉頁載入動畫1 -->

		<jsp:include page="../fragments/headerArea.jsp" />

		<!-- 	麵包屑 -->
		<div class="breadcrumb-area pt-95 pb-95 bg-img"
			style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
			<div class="container">
				<div class="breadcrumb-content text-center">
					<c:choose>
						<c:when test="${source == 'MyAnimals'}">
							<h2>我的寵物</h2>
						</c:when>
						<c:when test="${source == 'AdoptAnimal'}">
							<h2>領養動物</h2>
						</c:when>
					</c:choose>
					<ul>
						<li><a href="<c:url value='/'/>">首頁</a></li>
						<c:choose>
							<c:when test="${source == 'MyAnimals'}">
								<li><a href="<c:url value='/member/myAccount' />">會員中心</a></li>
								<li class="active">我的寵物</li>
							</c:when>
							<c:when test="${source == 'AdoptAnimal'}">
								<li class="active">全部動物</li>
							</c:when>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${source == 'MyAnimals'}">
				<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />
			</c:when>
		</c:choose>
		<!-- ===================================================================================== -->

		<%-- 	<a href="<c:url value='/mail'/>" --%>
		<!-- 				class="btn-style-cancel btn-style-border">寄信</a> -->

		<div class="blog-area pt-50 pb-50 clearfix">
			<div class="container">
				<div class="row">
					<!-- 				樣版 -->
					<div class="page1">
						<c:choose>
							<c:when test="${source == 'MyAnimals'}">
								<div class="about-us-btn mb-20 textRight">
									<a class="btn-style1"
										href="<c:url value='/MemberCenter/preCreateAnimal.controller'/>">新增寵物</a>
								</div>
							</c:when>
						</c:choose>
						<%-- <%=application.getContextPath()%> --%>
						<nav class="navbar navbar-light bg-light justify-content-between">
							<a class="navbar-brand"></a>
<%-- 							<form class="" action="<c:url value='/MemberCenter/readKeyword.controller'/>"> --%>
								<div class="form-inline">
								<input class="form-control mr-sm-2" type="search"
									placeholder="搜尋動物類別/品種/收容編號" aria-label="Search" name="factor1">
								<button class="btn btn-success my-2 my-sm-0" id="keywordSearch"
									type="submit" style="height:38px;border-radius:5px;"><font class="font22">搜尋</font></button>
								</div>
<!-- 							</form> -->
						</nav>
						<script type="text/javascript">
						$("#keywordSearch").click(function(){
							var xhr = new XMLHttpRequest();
							var keyword = $("input[name='factor1']").val();
							alert(keyword)
							//ing



							
							});
						</script>
						<!-- ===================================================================================== -->
						<!-- card來源https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_card_image&stacked=h -->
						<!-- 					自行上架的 -->
						<c:forEach var="AnimalsList" items="${AnimalsList}">
							<div class="col-xl-3 col-lg-4 col-md-6 f-left">
								<div
									class="blog-wrapper mb-15 mt-15 gray-bg card2 pointerCursor"
									<c:choose>
										<c:when test="${source == 'MyAnimals'}">
												onclick="location.href='<c:url value='/MemberCenter/ReadAnimalDetails.controller?id=${AnimalsList.animalId}' />'"
										</c:when>
										<c:when test="${source == 'AdoptAnimal'}">
												onclick="location.href='<c:url value='/AdoptAnimalDetails.controller?id=${AnimalsList.animalId}' />'"
										</c:when>
									</c:choose>>
									<div class="hover-effect square250px bgcWhite">
										<img class="cardImg marginAuto" alt=""
											src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${AnimalsList.animalId}">
									</div>
									<div class="blog-content form1 font22 H260 cardContentW">
										<ul>
											<!--<li><div class="div1">動物編號：&nbsp;</div> -->
											<%--	<div class="div1">${AnimalsList.animalId}</div> <br></li> --%>
											<!--===================================================================================== -->
											<li><div>品種：</div>
												<div>${AnimalsList.breeds.breed}</div> <br></li>
											<li><div>類別：</div>
												<div>${AnimalsList.breeds.family}</div> <br></li>
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
											<c:choose>
												<c:when test="${source == 'AdoptAnimal'}">
													<li><div>領養地區：&nbsp;</div>
														<div>${AnimalsList.member.address}</div> <br></li>
												</c:when>
											</c:choose>
											<!-- ===================================================================================== -->
											<c:choose>
												<c:when test="${source == 'MyAnimals'}">
													<li><c:choose>
															<c:when test="${AnimalsList.isAdoptionAvailable == 1}">
																<div>是否開放領養：&nbsp;</div>是<br>
															</c:when>
															<c:otherwise>
																<div>是否開放領養：&nbsp;</div>否<br>
															</c:otherwise>
														</c:choose></li>
												</c:when>
											</c:choose>
											<!-- ===================================================================================== -->
											<!--<li><div class="div1">備註：&nbsp;</div> -->
											<%--	<div class="div1 note1 mb-10">${AnimalsList.note}</div> <br></li> --%>
										</ul>
										<!--<h4><a href="blog-details.html">Lorem ipsum dolor amet cons adipisicing elit</a></h4> -->
									</div>
									<!--<div> -->
									<!--	<a -->
									<%--		href="<c:url value='/preUpdateAnimal.controller'/>?animalId=${AnimalsList.animalId}" --%>
									<!--		class="btn btn-secondary" style="width: 49%;">維護</a> <input -->
									<!--		type="button" value="刪除" -->
									<%--		onclick="deleteAnimal(${AnimalsList.animalId})" --%>
									<!--		class="btn btn-danger" style="width: 49%;"> -->
									<!--</div> -->
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
						<li><a class="active" href="#"><i
								class="icon-arrow-right"></i></a></li>
					</ul>
				</div>
			</div>
		</div>

		<jsp:include page="../fragments/footerArea.jsp" />
		<jsp:include page="../fragments/allJs.jsp" />

		<!-- 轉頁載入動畫2 -->
	</div>
</body>
<script>
	setTimeout(function() {
		$(document).ready(function() {
			document.getElementById("loader").style.display = "none";
			document.getElementById("myDiv").style.display = "block";
		});
	}, 500);
</script>
<!-- 轉頁載入動畫2 -->

</html>