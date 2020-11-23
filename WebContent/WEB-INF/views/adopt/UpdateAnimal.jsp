<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新動物</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
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
				<h2>Blog</h2>
				<ul>
					<li><a href="index.html">home</a></li>
					<li class="active">Blog</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- ===================================================================================== -->
	<div class="wid450px">
		<a href="<c:url value='/ReadAnimal' />"
			class="btn btn-secondary margin10px">回維護首頁</a>
	</div>
	<!-- ===================================================================================== -->
	<div class="wid450px">
		<form:form action="UpdateAnimal.controller" method="POST"
			modelAttribute="animals" enctype="multipart/form-data">
			<form:label path="animalFiles" class="label1">照片：</form:label>
			<form:input path="animalFiles" type="file" class="input1 inputHW"
				id="animalFilesUpdate" />
			<br>
			<div class="dispblok margleft125px square250px"
				id="animalFilesDivOrigin">
				<img class="cardImg"
					src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${animals.animalId}"
					alt="Animal image">
			</div>
			<div class="divHidden square250px" id="animalFilesDivAlter">
				<img class="cardImg" id="preview_animalFiles" src="#" /><br>
			</div>
			<!-- ===================================================================================== -->
			<form:label path="animalId" class="label1">動物編號：</form:label>
			<form:input path="animalId" type="text" name="animalId"
				value="${animals.animalId}" class="divHidden" id="animalId" />
			<form:label path="animalId">${animals.animalId}</form:label>
			<br>
			<!-- 		label不能用value，input readonly="readonly", disable="disabled"無作用-->
			<!-- ===================================================================================== -->
			<div class="divHidden">
				<form:label path="memberId" class="label1">會員編號：</form:label>
				<form:input path="memberId" type="text" name="memberId"
					value="${animals.member.id}" onblur="checkmemberId()" id="memberId" />
				<br>
			</div>
			<div id="memberIdDiv" class="divHidden">
				<br>
			</div>
			<!-- ===================================================================================== -->
			<form:label path="acceptionId" class="label1">收容動物編號：</form:label>
			<form:input path="acceptionId" type="text" name="acceptionId"
				value="${animals.acceptionId}" onblur="checkacceptionId()"
				id="acceptionId" class="wid200px input1 inputBorder inputHW"/>
			<br>
			<div id="acceptionIdDiv" class="divHidden">
				<br>
			</div>
			<!-- ===================================================================================== -->
			<form:label path="breedId" class="label1">品種編號：</form:label>
			<select id="family" class="input1 inputBorder inputHW">
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
			</select>
			<select id="breed" class="input1 inputBorder inputHW">
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
			</select>
			<br>
			<div class="divHidden">
				<input type="text" name="breedText" value="${animals.breeds.breed}"
					id="breedText" /> <br>
			</div>
			<div id="breedIdDiv" class="divHidden">
				<br>
			</div>
			<!-- ===================================================================================== -->
			<form:label path="gender" class="label1">性別：</form:label>
			<form:select path="gender" class="input1 inputBorder inputHW">
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
			<!-- ===================================================================================== -->
			<form:label path="coatColor" class="label1">毛色：</form:label>
			<form:input path="coatColor" type="text" name="coatColor"
				value="${animals.coatColor}" onblur="checkcoatColor()"
				id="coatColor" class="input1 inputBorder inputHW"/>
			<br>
			<div id="coatColorDiv" class="divHidden">
				<br>
			</div>
			<!-- ===================================================================================== -->
			<form:label path="isAdoptionAvailable" class="label1">是否開放領養：</form:label>
			<form:select path="isAdoptionAvailable" class="input1 inputBorder inputHW">
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
			<!-- ===================================================================================== -->
			<form:label path="note" class="label1 posAbs">備註：</form:label>
			<form:textarea path="note" name="note" rows="5" cols="18"
				 class="margleft125px input1 inputBorder wid305px" value="${animals.note}" />
			<br>
			<!-- ===================================================================================== -->
			<a
				href="<c:url value='/preUpdateAnimal.controller' />?animalId=${animals.animalId}"
				class="btn btn-secondary">回復修改</a>
			<form:button value="Send" name="update" class="btn btn-primary">修改</form:button>
		</form:form>
	</div>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>