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
	
	<div class="divFixed btn-style1" id="noticeInput">一鍵輸入</div>
	<!-- ===================================================================================== -->
	<div class="wid450px margin0Auto mt-50 mb-50">
		<div>
			<a href="<c:url value='/MemberCenter/ReadAnimal' />"
				class="btn-style-cancel btn-style-border mb-20">回我的寵物</a>
		</div>
		<!-- ===================================================================================== -->
		<div>
			<form:form action="/team6/MemberCenter/CreateAnimal.controller" method="POST"
				modelAttribute="AnimalsList1" enctype="multipart/form-data">
				<div class="mb-20">
					<form:label path="animalFiles" class="mb-0 font22">*照片：</form:label>
					<form:input path="animalFiles" type="file" id="animalFilesCreate"
						multiple="multiple" />
					<br>
				</div>
				<div class="mb-20">
					<div class="divHidden square250px ml-100" id="animalFilesDiv">
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
					<form:label path="breedId" class="mb-0 font22">*品種：</form:label>
					<select id="family" class="font22 borderBlack wid40p">
						<c:forEach var="Families" items="${Families}">
							<option value="">${Families}</option>
						</c:forEach>
					</select> <select id="breed" class="font22 borderBlack wid40p">
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
					<form:label path="gender" class="mb-0 font22">*性別：</form:label>
					<form:select path="gender" id="gender"
						class="font22 borderBlack wid40p">
						<form:option value="1">公</form:option>
						<form:option value="0">母</form:option>
					</form:select>
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="isAdoptionAvailable" class="mb-0 font22">*是否開放領養：</form:label>
					<form:select path="isAdoptionAvailable"
						class="font22 borderBlack wid40p">
						<form:option value="1">開放</form:option>
						<form:option value="0">不開放</form:option>
					</form:select>
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="acceptionId" class="mb-0 font22">收容動物編號：</form:label>
					<form:input path="acceptionId" type="text" name="acceptionId"
						onblur="checkacceptionId()" id="acceptionId" class="font22"
						placeholder="收容所需填" />
					<br>
				</div>
				<div id="acceptionIdDiv" class="divHidden">
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="coatColor" class="mb-0 font22">毛色：</form:label>
					<form:input path="coatColor" type="text" name="coatColor"
						onblur="checkcoatColor()" id="coatColor" class="font22" />
					<br>
				</div>
				<div id="coatColorDiv" class="divHidden">
					<br>
				</div>
				<!-- ===================================================================================== -->
				<div class="mb-20">
					<form:label path="note" class="mb-0 font22">備註：</form:label>
					<form:textarea path="note" name="note" rows="5" cols="18"
						placeholder="可輸入寵物年齡" class="font22" />
					<br>
				</div>
				<!-- ===================================================================================== -->
				<a href="<c:url value='/MemberCenter/preCreateAnimal.controller' />"
					class="btn-style-cancel btn-style-border">重填</a>
				<form:button value="Send" class="btn-style1 font22">送出</form:button>
			</form:form>
		</div>
		<!-- ===================================================================================== -->
	</div>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>
