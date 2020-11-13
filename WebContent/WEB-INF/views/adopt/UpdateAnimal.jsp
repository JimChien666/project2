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
</head>
<body>
	<div>
		<jsp:include page="../public/top.jsp" />
	</div>
	<div class="wid450px">
		<a href="<c:url value='/readAnimal' />"
			class="btn btn-secondary margin10px">回維護首頁</a>
	</div>
	<div class="wid450px">
		<form:form action="UpdateAnimal.controller" method="POST"
			modelAttribute="animals" enctype="multipart/form-data">
			<form:label path="animalFiles" class="label1">照片：</form:label>
			<form:input path="animalFiles" type="file" class="wid200px"
				id="animalFilesUpdate" onchange="showImageUpdate()" />
			<!-- 		<input type="file" name="animalFile" id="animalFile1" multiple class="wid200px"> -->
			<div id="animalFilesDivOrigin" class="dispblok margleft125px">
				<img class="square250px"
					src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${animals.animalId}"
					alt="Animal image">
			</div>
			<div id="animalFilesDivAlter" class="divHidden">
				<img class="square250px" id="preview_animalFiles" src="#" /><br>
			</div>
			<form:label path="animalId" class="label1">動物編號：</form:label>
			<form:input path="animalId" type="text" name="animalId"
				value="${animals.animalId}" class="divHidden" />
			<form:label path="animalId">${animals.animalId}</form:label>
			<br>
			<!-- 		label不能用value，input readonly="readonly", disable="disabled"無作用-->
			<form:label path="memberId" class="label1">會員編號：</form:label>
			<form:input path="memberId" type="text" name="memberId"
				value="${animals.memberId}" onblur="checkmemberId()" id="memberId" />
			<br>
			<div id="memberIdDiv" class="divHidden">
				<br>
			</div>
			<form:label path="acceptionId" class="label1">收容動物編號：</form:label>
			<form:input path="acceptionId" type="text" name="acceptionId"
				value="${animals.acceptionId}" onblur="checkacceptionId()"
				id="acceptionId" />
			<br>
			<div id="acceptionIdDiv" class="divHidden">
				<br>
			</div>
			<form:label path="breedId" class="label1">品種編號：</form:label>
			<form:input path="breedId" type="text" name="breedId"
				value="${animals.breedId}" onblur="checkbreedId()" id="breedId" />
			<br>
			<div id="breedIdDiv" class="divHidden">
				<br>
			</div>
			<form:label path="gender" class="label1">性別：</form:label>
			<form:select path="gender">
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
			<form:label path="coatColor" class="label1">毛色：</form:label>
			<form:input path="coatColor" type="text" name="coatColor"
				value="${animals.coatColor}" onblur="checkcoatColor()"
				id="coatColor" />
			<br>
			<div id="coatColorDiv" class="divHidden">
				<br>
			</div>
			<form:label path="isAdoptionAvailable" class="label1">是否開放領養：</form:label>
			<form:select path="isAdoptionAvailable">
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
			<form:label path="note" class="label1 posAbs">備註：</form:label>
			<form:textarea path="note" name="note" rows="5" cols="18"
				class="margleft125px" value="${animals.note}" />
			<br>
			<a
				href="<c:url value='/preUpdateAnimal.controller' />?animalId=${animals.animalId}"
				class="btn btn-secondary">回復修改</a>
			<form:button value="Send" name="update" class="btn btn-primary">修改</form:button>
		</form:form>
	</div>
</body>
</html>