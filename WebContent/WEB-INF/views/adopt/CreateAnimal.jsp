<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加入動物</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<!-- <script src="js/jquery-3.5.1.js" charset="UTF-8"></script> -->
<script src="<c:url value='/js/animal.js' />" type="text/javascript"
	charset="UTF-8"></script>
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
				<h2>加入寵物</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li class="active">會員中心</li>
					<li><a href="<c:url value='/MemberCenter/ReadAnimal'/>">我的寵物</a></li>
					<li class="active">加入寵物</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />

	<div class="divFixed2 btn-style1 pointerCursor" id="createInput2">一鍵輸入(不開放)</div>
	<div class="divFixed btn-style1 pointerCursor" id="createInput">一鍵輸入(開放)</div>
	<!-- ===================================================================================== -->
	<div class="wid500 margin0Auto mt-50 mb-50">
		<div>
			<a href="<c:url value='/MemberCenter/ReadAnimal' />"
				class="btn-style-cancel btn-style-border mb-20">回我的寵物</a>
		</div>
		<!-- ===================================================================================== -->
		<div class="createAnimalW">
			<form:form action="/team6/MemberCenter/CreateAnimal.controller"
				method="POST" modelAttribute="AnimalsList1"
				enctype="multipart/form-data">
				<div class="mb-20">
					<form:label path="animalFiles" class="mb-0">*照片：</form:label>
					<form:input path="animalFiles" type="file" id="animalFilesCreate"
						multiple="multiple" />
					<br>
				</div>
				<div class="mb-20">
					<div class="divHidden square250px ml-173" id="animalFilesDiv">
						<img class="cardImg marginAuto" id="preview_animalFiles" src="#" /><br>
					</div>
				</div>
				<!-- ===================================================================================== -->
				<div class="divHidden">
					<form:label path="memberId" class="mb-0">會員編號：</form:label>
					<form:input path="memberId" type="text" name="memberId"
						onblur="checkmemberId()" id="memberId" value="${LoginOK.id}" />
					<br>
				</div>
				<div id="memberIdDiv" class="divHidden">
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="breedId" class="mb-0">*品種：</form:label>
					<script>
// 						$(function() {
// 							$("#family").autocomplete({
// 								source : "${Families}"
// 							});
// 						});
					</script>
<!-- 					<input id="family"> -->
					<select id="family" class="borderBlack">
						<c:forEach var="Families" items="${Families}">
							<option value="">${Families}</option>
						</c:forEach>
					</select> <select id="breed" class="borderBlack ml-173 mt-10">
						<c:forEach var="breed" items="${breed}">
							<option value="">${breed.breed}</option>
						</c:forEach>
					</select> <br>
				</div>
				<!-- 			input -->
				<div class="divHidden">
					<input type="text" name="breedText" id="breedText" /> <br>
				</div>
				<!-- 			品種檢查 -->
				<div id="breedIdDiv" class="divHidden">
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="gender" class="mb-0">*性別：</form:label>
					<form:select path="gender" id="gender"
						class="borderBlack">
						<form:option value="1">公</form:option>
						<form:option value="0">母</form:option>
					</form:select>
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="isAdoptionAvailable" class="mb-0">*是否開放領養：</form:label>
					<form:select path="isAdoptionAvailable" id="isAdoptionAvailable"
						class="font22 borderBlack">
						<form:option value="1">開放</form:option>
						<form:option value="0">不開放</form:option>
					</form:select>
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="acceptionId" class="mb-0">收容動物編號：</form:label>
					<form:input path="acceptionId" type="text" name="acceptionId"
						onblur="checkacceptionId()" id="acceptionId"
						placeholder="收容所需填" />
					<br>
				</div>
				<div id="acceptionIdDiv" class="divHidden">
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="coatColor" class="mb-0">毛色：</form:label>
					<form:input path="coatColor" type="text" name="coatColor"
						onblur="checkcoatColor()" id="coatColor" />
					<br>
				</div>
				<div id="coatColorDiv" class="divHidden">
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-50">
					<form:label path="note" class="posAbs">備註：</form:label>
					<form:textarea path="note" name="note" rows="5" cols="18" id="note"
						placeholder="可輸入寵物年齡" class="font22 wid320 ml-173"/>
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="divCenter">
				<a href="<c:url value='/MemberCenter/preCreateAnimal.controller' />"
					class="btn-style-cancel btn-style-border">重填</a>
				<form:button value="Send" class="btn-style1">送出</form:button>
				</div>
			</form:form>
		</div>
		<!-- ===================================================================================== -->
	</div>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
<script>
//一鍵輸入(不開放)
$('#createInput2').click(function(){
	$('#gender')[0].selectedIndex = 1;
	$('#isAdoptionAvailable')[0].selectedIndex = 1;
	$('#acceptionId').val("RAAAG1091130002");
	$('#coatColor').val("黃");
	$('#note').val("有點怕生");
});
//一鍵輸入(開放)
$('#createInput').click(function(){
	$('#breed')[0].selectedIndex = 18;
	$('#breedText').val("米格魯");
	$('#acceptionId').val("W081204-12");
	$('#coatColor').val("花");
	$('#note').val("很愛吃、有點邊緣狗個性");
});
</script>
</html>
