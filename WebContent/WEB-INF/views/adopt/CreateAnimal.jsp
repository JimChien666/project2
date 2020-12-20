<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:choose>
	<c:when test="${source == 'create'}">
		<title>加入動物</title>
	</c:when>
	<c:when test="${source == 'update'}">
		<title>維護動物資料</title>
	</c:when>
</c:choose>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<!-- <script src="js/jquery-3.5.1.js" charset="UTF-8"></script> -->
<script src="<c:url value='/js/animal.js' />" type="text/javascript"
	charset="UTF-8"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
	
	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<c:choose>
					<c:when test="${source == 'create'}">
						<h2>加入寵物</h2>
					</c:when>
					<c:when test="${source == 'update'}">
						<h2>維護動物資料</h2>
					</c:when>
				</c:choose>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li class="active">會員中心</li>
					<li><a href="<c:url value='/MemberCenter/ReadAnimal'/>">我的寵物</a></li>
					<c:choose>
						<c:when test="${source == 'create'}">
							<li class="active">加入寵物</li>
						</c:when>
						<c:when test="${source == 'update'}">
							<li><a
								href="<c:url value='/MemberCenter/ReadAnimalDetails.controller?id=${animals.animalId}'/>">動物資料</a></li>
							<li class="active">維護動物資料</li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />
	<c:choose>
		<c:when test="${source == 'create'}">
			<div class="divFixed2 btn-style1" id="createInput2">一鍵輸入(不開放)</div>
			<div class="divFixed btn-style1" id="createInput">一鍵輸入(開放)</div>
		</c:when>
		<c:when test="${source == 'update'}">
			<div class="divFixed btn-style1" id="updateInput">一鍵輸入</div>
		</c:when>
	</c:choose>

	<!-- ===================================================================================== -->
	<div class="wid500 margin0Auto mt-50 mb-50">
		<div>
			<a href="<c:url value='/MemberCenter/ReadAnimal' />"
				class="btn-style-cancel btn-style-border mb-20">回我的寵物</a>
		</div>
		<!-- ===================================================================================== -->
		<div class="createAnimalW">
			<c:choose>
				<c:when test="${source == 'create'}">
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
							<select id="family" >
								<c:forEach var="Families" items="${Families}">
									<option value="">${Families}</option>
								</c:forEach>
							</select> <select id="breed" class="ml-173 mt-10">
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
							<form:select path="gender" id="gender" >
								<form:option value="1">公</form:option>
								<form:option value="0">母</form:option>
							</form:select>
							<br>
						</div>
						<!-- ===================================================================================== -->
						<div class="mb-20">
							<form:label path="isAdoptionAvailable" class="mb-0">*是否開放領養：</form:label>
							<form:select path="isAdoptionAvailable" id="isAdoptionAvailable"
								>
								<form:option value="1">開放</form:option>
								<form:option value="0">不開放</form:option>
							</form:select>
							<br>
						</div>
						<!-- ===================================================================================== -->
						<div class="mb-20">
							<form:label path="acceptionId" class="mb-0">收容動物編號：</form:label>
							<form:input path="acceptionId" type="text" name="acceptionId"
								onblur="checkacceptionId()" id="acceptionId" placeholder="收容所需填" />
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
							<form:textarea path="note" name="note" rows="5" cols="18"
								id="note" placeholder="可輸入寵物年齡" class="wid320 ml-173 font22" />
							<br>
						</div>
						<!-- ===================================================================================== -->
						<div class="divCenter">
							<a
								href="<c:url value='/MemberCenter/preCreateAnimal.controller' />"
								class="btn-style-cancel btn-style-border">重填</a>
							<form:button value="Send" class="btn-style1" onclick="success('新增','')">新增</form:button>
						</div>
					</form:form>
				</c:when>
				<c:when test="${source == 'update'}">
					<form:form action="/team6/MemberCenter/UpdateAnimal.controller"
						method="POST" modelAttribute="animals"
						enctype="multipart/form-data">
						<div class="mb-20">
							<form:label path="animalFiles" class="mb-0">*照片：</form:label>
							<form:input path="animalFiles" type="file" id="animalFilesUpdate" />
							<br>
						</div>
						<div class="mb-20">
							<div class="dispblok square250px ml-173"
								id="animalFilesDivOrigin">
								<img class="cardImg marginAuto"
									src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${animals.animalId}"
									alt="Animal image">
							</div>
							<div class="divHidden square250px ml-173"
								id="animalFilesDivAlter">
								<img class="cardImg marginAuto" id="preview_animalFiles" src="#" /><br>
							</div>
						</div>
						<!-- ===================================================================================== -->
						<div class="mb-20">
							<form:label path="breedId" class="mb-0">*品種：</form:label>
							<select id="family" >
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
							</select> <select id="breed" class="ml-173 mt-10">
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
							<input type="text" name="breedText"
								value="${animals.breeds.breed}" id="breedText" /> <br>
						</div>
						<div id="breedIdDiv" class="divHidden">
							<br>
						</div>
						<!-- ===================================================================================== -->
						<div class="mb-20">
							<form:label path="gender" class="mb-0">*性別：</form:label>
							<form:select path="gender" >
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
							<form:label path="isAdoptionAvailable" class="mb-0">*是否開放領養：</form:label>
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
						</div>
						<!-- ===================================================================================== -->
						<div class="divHidden">
							<form:label path="animalId" class="mb-0">動物編號：</form:label>
							<form:input path="animalId" type="text" name="animalId"
								value="${animals.animalId}" class="divHidden" id="animalId" />
							<form:label path="animalId">${animals.animalId}</form:label>
						</div>
						<!-- 		label不能用value，input readonly="readonly", disable="disabled"無作用-->
						<!-- ===================================================================================== -->
						<div class="divHidden">
							<form:label path="memberId" class="mb-0">會員編號：</form:label>
							<form:input path="memberId" type="text" name="memberId"
								value="${animals.member.id}" onblur="checkmemberId()"
								id="memberId" />
							<br>
						</div>
						<div id="memberIdDiv" class="divHidden">
							<br>
						</div>
						<!-- ===================================================================================== -->
						<div class="mb-20">
							<form:label path="acceptionId" class="mb-0">收容動物編號：</form:label>
							<form:input path="acceptionId" type="text" name="acceptionId"
								value="${animals.acceptionId}" onblur="checkacceptionId()"
								id="acceptionId" />
							<br>
						</div>
						<div id="acceptionIdDiv" class="divHidden">
							<br>
						</div>
						<!-- ===================================================================================== -->
						<div class="mb-20">
							<form:label path="coatColor" class="mb-0">毛色：</form:label>
							<form:input path="coatColor" type="text" name="coatColor"
								value="${animals.coatColor}" onblur="checkcoatColor()"
								id="coatColor" />
							<br>
						</div>
						<div id="coatColorDiv" class="divHidden">
							<br>
						</div>
						<!-- ===================================================================================== -->
						<div class="mb-20">
							<form:label path="note" class="posAbs">備註：</form:label>
							<form:textarea path="note" name="note" rows="5" cols="18"
								class="wid320 ml-173 font22" value="${animals.note}" />
							<br>
						</div>
						<!-- ===================================================================================== -->
						<div class="divCenter">
							<a
								href="<c:url value='/MemberCenter/preUpdateAnimal.controller' />?animalId=${animals.animalId}"
								class="btn-style-cancel btn-style-border">回復修改</a>
							<form:button value="Send" name="update" class="btn-style1" onclick="success('修改','')">修改</form:button>
						</div>
					</form:form>
				</c:when>
			</c:choose>
		</div>
		<!-- ===================================================================================== -->
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

<script>
	//一鍵輸入(不開放)
	$('#createInput2').click(function() {
		$('#gender')[0].selectedIndex = 1;
		$('#isAdoptionAvailable')[0].selectedIndex = 1;
		$('#acceptionId').val("RAAAG1091130002");
		$('#coatColor').val("黃");
		$('#note').val("有點怕生");
	});
	//一鍵輸入(開放)
	$('#createInput').click(function() {
		$('#breed')[0].selectedIndex = 18;
		$('#breedText').val("米格魯");
		$('#acceptionId').val("W081204-12");
		$('#coatColor').val("花");
		$('#note').val("很愛吃、有點邊緣狗個性");
	});
	$('#updateInput').click(function() {
		$('#isAdoptionAvailable')[0].selectedIndex = 0;
		$('#coatColor').val("橘");
		$('#note').val("跟主人熟了會撒嬌");
	});
	//品種選擇
	//都不選的預設
	var breedId = document.getElementById("breed").options[breed.selectedIndex].text;
	document.getElementById("breedText").value = breedId;
	//只選breed
	breed.onchange = function() {
		var breedId = document.getElementById("breed").options[breed.selectedIndex].text;
		document.getElementById("breedText").value = breedId;
	}
	var family = document.getElementById("family");
	family.onchange = function() {
		$("#breed").html("");
		$("#breed").empty();//或用下一行
		//	$("#breed").find("option").remove();
		var xhr = new XMLHttpRequest();
		var familyValue = family.options[family.selectedIndex].text;
		var url = "/team6/getBreed.controller?family=" + familyValue;
		xhr.open("GET", url, true);
		xhr.send();
		xhr.onreadystatechange = function() {
			// 向伺服器提出的請求已經收到回應
			if (xhr.readyState === 4) {
				// 伺服器回應成功
				if (xhr.status === 200) {
					var breed = JSON.parse(xhr.responseText);
					//可再確認用array.join或用一般字串相加，哪個效率好
					var content = "";
					for (var i = 0; i < breed.length; i++) {
						content += "<option value='" + breed[i].breedId + "'>"
							+ breed[i].breed + "</option>";
					}
					document.getElementById("breed").innerHTML = content;

					//改變family和breed時的預設值
					var breed = document.getElementById("breed");
					breed.onchange = function() {
						var breedId = document.getElementById("breed").options[breed.selectedIndex].text;
						document.getElementById("breedText").value = breedId;
					}

					//只改變family時的預設值
					var breedId = document.getElementById("breed").options[breed.selectedIndex].text;
					document.getElementById("breedText").value = breedId;
				}
			}
		}
	}
</script>
</html>