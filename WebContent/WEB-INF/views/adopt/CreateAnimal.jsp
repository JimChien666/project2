<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增動物</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<!-- <script src="js/jquery-3.5.1.js" charset="UTF-8"></script> -->
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<div>
		<jsp:include page="../public/top.jsp" />
	</div>
	<div class="wid450px">
		<a href="<c:url value='/ReadAnimal' />"
			class="btn btn-secondary margin10px">回維護首頁</a>
	</div>
	<div class="wid450px">
		<form:form action="CreateAnimal.controller" method="POST"
			modelAttribute="AnimalsList1" enctype="multipart/form-data">
			<form:label path="animalFiles" class="label1">照片：</form:label>
			<form:input path="animalFiles" type="file" class="wid200px"
				id="animalFilesCreate" />
			<br>
			<div class="divHidden square250px" id="animalFilesDiv">
				<img class="cardImg" id="preview_animalFiles" src="#" /><br>
			</div>
			<div class="divHidden">
				<form:label path="memberId" class="label1">會員編號：</form:label>
				<form:input path="memberId" type="text" name="memberId"
					placeholder="自動抓取會員編號" onblur="checkmemberId()" id="memberId"
					value="${LoginOK.id}" />
				<br>
			</div>
			<div id="memberIdDiv" class="divHidden">
				<br>
			</div>
			<form:label path="acceptionId" class="label1">收容動物編號：</form:label>
			<form:input path="acceptionId" type="text" name="acceptionId"
				onblur="checkacceptionId()" id="acceptionId" />收容所需填
			<br>
			<div id="acceptionIdDiv" class="divHidden">
				<br>
			</div>
			<form:label path="breedId" class="label1">品種：</form:label>
			<select id="family">
				<c:forEach var="Families" items="${Families}">
					<option value="">${Families}</option>
				</c:forEach>
			</select>
			<div id="dogBreedDiv" class="div1">
				<select id="dogBreed">
					<c:forEach var="dogBreed" items="${dogBreed}">
						<option value="${dogBreed.breedId}">${dogBreed.breed}</option>
					</c:forEach>
				</select>
			</div>
			<div id="breedDiv" class="div1"></div>
			<br>
			<!-- 			<div class="divHidden"> -->
			<form:input path="breedId" type="text" name="breedId"
				onblur="checkbreedId()" id="breedId" />
			<br>
			<!-- 			</div> -->
			<div id="breedIdDiv" class="divHidden">
				<br>
			</div>
			<form:label path="gender" class="label1">性別：</form:label>
			<form:select path="gender" id="gender">
				<form:option value="1">公</form:option>
				<form:option value="0">母</form:option>
			</form:select>
			<br>
			<form:label path="coatColor" class="label1">毛色：</form:label>
			<form:input path="coatColor" type="text" name="coatColor"
				onblur="checkcoatColor()" id="coatColor" />
			<br>
			<div id="coatColorDiv" class="divHidden">
				<br>
			</div>
			<form:label path="isAdoptionAvailable" class="label1">是否開放領養：</form:label>
			<form:select path="isAdoptionAvailable">
				<form:option value="1">開放</form:option>
				<form:option value="0">不開放</form:option>
			</form:select>
			<br>
			<form:label path="note" class="label1 posAbs">備註：</form:label>
			<form:textarea path="note" name="note" rows="5" cols="18"
				placeholder="可輸入寵物年齡" class="margleft125px" />
			<br>
			<a href="<c:url value='/preCreateAnimal.controller' />"
				class="btn btn-secondary">重填</a>
			<form:button value="Send" class="btn btn-primary">送出</form:button>
		</form:form>

	</div>
</body>
</html>