<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>維護動物資料</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script src="<c:url value='/js/animal.js' />" type="text/javascript" charset="UTF-8"></script>
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
				<h2>維護動物資料</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
							<li class="active">會員中心</li>
							<li><a href="<c:url value='/MemberCenter/ReadAnimal'/>">我的寵物</a></li>
							<li><a href="<c:url value='/MemberCenter/ReadAnimalDetails.controller?id=${animals.animalId}'/>">動物資料</a></li>
							<li class="active">維護動物資料</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />
	<!-- ===================================================================================== -->
	<div class="wid450px margin0Auto mt-50 mb-50">
		<div>
			<a href="<c:url value='/MemberCenter/ReadAnimal' />"
				class="btn-style-cancel btn-style-border mb-20">回我的寵物</a>
		</div>
		<!-- ===================================================================================== -->
		<form:form action="/team6/MemberCenter/UpdateAnimal.controller" method="POST"
			modelAttribute="animals" enctype="multipart/form-data">
			<div class="mb-20">
				<form:label path="animalFiles" class="mb-0 font22">*照片：</form:label>
				<form:input path="animalFiles" type="file" id="animalFilesUpdate" />
				<br>
			</div>
			<div class="mb-20">
				<div class="dispblok square250px ml-100" id="animalFilesDivOrigin">
					<img class="cardImg marginAuto"
						src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${animals.animalId}"
						alt="Animal image">
				</div>
				<div class="divHidden square250px ml-100" id="animalFilesDivAlter">
					<img class="cardImg marginAuto" id="preview_animalFiles" src="#" /><br>
				</div>
			</div>
			<!-- ===================================================================================== -->
			<div class="mb-20">
				<form:label path="breedId" class="mb-0 font22">*品種：</form:label>
				<select id="family" class="font22 borderBlack wid40p">
					<c:forEach var="Families" items="${Families}">
						<c:choose>
							<c:when test="${Families == animals.breeds.family}">
								<option selected>${animals.breeds.family}</option>
							</c:when>
							<c:otherwise>
								<option>${Families}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select> <select id="breed" class="font22 borderBlack wid40p">
					<c:forEach var="breed" items="${breed}">
						<c:choose>
							<c:when test="${breed.breed == animals.breeds.breed}">
								<option selected>${animals.breeds.breed}</option>
							</c:when>
							<c:otherwise>
								<option>${breed.breed}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select> <br>
			</div>
			<div class="divHidden">
				<input type="text" name="breedText" value="${animals.breeds.breed}"
					id="breedText" /> <br>
			</div>
			<div id="breedIdDiv" class="divHidden">
				<br>
			</div>
			<!-- ===================================================================================== -->
			<div class="mb-20">
				<form:label path="gender" class="mb-0 font22">*性別：</form:label>
				<form:select path="gender" class="font22 borderBlack wid40p">
					<c:choose>
						<c:when test="${animals.gender == 1}">
							<form:option value="1" selected="selected">公</form:option>
							<form:option value="0">母</form:option>
						</c:when>
						<c:otherwise>
							<form:option value="1">公</form:option>
							<form:option value="0" selected="selected">母</form:option>
						</c:otherwise>
					</c:choose>
				</form:select>
				<br>
			</div>
			<!-- ===================================================================================== -->
			<div class="mb-20">
				<form:label path="isAdoptionAvailable" class="mb-0 font22">*是否開放領養：</form:label>
				<form:select path="isAdoptionAvailable"
					class="font22 borderBlack wid40p">
					<c:choose>
						<c:when test="${animals.isAdoptionAvailable == 1}">
							<form:option value="1" selected="selected">開放</form:option>
							<form:option value="0">不開放</form:option>
						</c:when>
						<c:otherwise>
							<form:option value="1">開放</form:option>
							<form:option value="0" selected="selected">不開放</form:option>
						</c:otherwise>
					</c:choose>
				</form:select>
				<br>
			</div>
			<!-- ===================================================================================== -->
			<div class="divHidden">
				<form:label path="animalId" class="mb-0 font22">動物編號：</form:label>
				<form:input path="animalId" type="text" name="animalId"
					value="${animals.animalId}" class="divHidden" id="animalId" />
				<form:label path="animalId">${animals.animalId}</form:label>
			</div>
			<!-- 		label不能用value，input readonly="readonly", disable="disabled"無作用-->
			<!-- ===================================================================================== -->
			<div class="divHidden">
				<form:label path="memberId" class="mb-0 font22">會員編號：</form:label>
				<form:input path="memberId" type="text" name="memberId"
					value="${animals.member.id}" onblur="checkmemberId()" id="memberId" />
				<br>
			</div>
			<div id="memberIdDiv" class="divHidden">
				<br>
			</div>
			<!-- ===================================================================================== -->
			<div class="mb-20">
				<form:label path="acceptionId" class="mb-0 font22">收容動物編號：</form:label>
				<form:input path="acceptionId" type="text" name="acceptionId"
					value="${animals.acceptionId}" onblur="checkacceptionId()"
					id="acceptionId" class="font22"/>
				<br>
			</div>
			<div id="acceptionIdDiv" class="divHidden">
				<br>
			</div>
			<!-- ===================================================================================== -->
			<div class="mb-20">
				<form:label path="coatColor" class="mb-0 font22">毛色：</form:label>
				<form:input path="coatColor" type="text" name="coatColor"
					value="${animals.coatColor}" onblur="checkcoatColor()"
					id="coatColor" class="font22" />
				<br>
			</div>
			<div id="coatColorDiv" class="divHidden">
				<br>
			</div>
			<!-- ===================================================================================== -->
			<div class="mb-20">
				<form:label path="note" class="mb-0 font22">備註：</form:label>
				<form:textarea path="note" name="note" rows="5" cols="18"
					class="font22" value="${animals.note}" />
				<br>
			</div>
			<!-- ===================================================================================== -->
			<a
				href="<c:url value='/MemberCenter/preUpdateAnimal.controller' />?animalId=${animals.animalId}"
				class="btn-style-cancel btn-style-border">回復修改</a>
			<form:button value="Send" name="update" class="btn-style1 font22">修改</form:button>
		</form:form>
	</div>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>