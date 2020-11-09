<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增動物</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div>
	<jsp:include page="../public/top.jsp" />
</div>
	<div class="wid400px">
		<a href="<c:url value='/readAnimal' />" class="btn btn-secondary margin10px">回維護首頁</a>
	</div>
		
	<div class="wid400px">
<!-- ====================================================================================================== -->

		<form:form action="CreateAnimal.controller" method="POST" modelAttribute="AnimalsList1" enctype="multipart/form-data">
			
			<!-- ============================================================================== -->
			
			<label for="" class="label1">照片：</label>
			<form:input path="animalFiles" type="file" class="wid200px"/>
<!-- 			<input type="file" name="animalFile" id="animalFile" multiple class="wid200px"><br> -->
			<br>
			<span id="animalFileSpan" class="spanHidden height260px">
				<img id="preview_animalFile" src="#" class="square250px" /><br>
			</span>
			
			<!-- ============================================================================== -->

			<label for="" class="label1">會員編號：</label>
			<form:input path="memberId" type="text" name="memberId" placeholder="自動抓取會員編號" onblur="checkmemberId()" id="memberId" /><br>
		<!--<input type="text" name="memberId" placeholder="自動抓取會員編號" onblur="checkmemberId()" id="memberId" required><br> -->
			<span id="memberIdSpan" class="spanHidden"><br></span>
			
			<!-- ============================================================================== -->

			<label for="" class="label1">收容動物編號：</label>
			<form:input path="acceptionId" type="text" name="acceptionId" onblur="checkacceptionId()" id="acceptionId" />收容所需填
			<br>
		<!--<input type="text" name="acceptionId" onblur="checkacceptionId()" id="acceptionId">收容所需填<br> -->
			<span id="acceptionIdSpan" class="spanHidden"><br></span>
			
			<!-- ============================================================================== -->

			<label for="" class="label1">品種編號：</label>
			<form:input path="breedId" type="text" name="breedId" onblur="checkbreedId()" id="breedId" />需查詢填入
			<br>
		<!--<input type="text" name="breedId" onblur="checkbreedId()" id="breedId" required>需查詢填入<br> -->
			<span id="breedIdSpan" class="spanHidden"><br></span>
			
			<!-- ============================================================================== -->

			<label for="gender" class="label1">性別：</label>
			<form:select path="gender">
				<form:option value="1">公</form:option>
				<form:option value="0">母</form:option>
			</form:select>
			<br>
		<!--<select name="gender" id=""> -->
		<!-- 	<option value="1">公</option> -->
		<!-- 	<option value="0">母</option> -->
		<!--</select><br> -->
			
			<!-- ============================================================================== -->

			<label for="" class="label1">毛色：</label>
			<form:input path="coatColor" type="text" name="coatColor" onblur="checkcoatColor()" id="coatColor" />
			<br>
		<!--<input type="text" name="coatColor" onblur="checkcoatColor()" id="coatColor"><br> -->
			<span id="coatColorSpan" class="spanHidden"><br></span>
			
			<!-- ============================================================================== -->

			<label for="" class="label1">是否開放領養：</label>
			<form:select path="isAdoptionAvailable">
				<form:option value="1">開放</form:option>
				<form:option value="0">不開放</form:option>
			</form:select>
			<br>
		<!--<select name="isAdoptionAvailable" id=""> -->
		<!-- 	<option value="1">開放</option> -->
		<!-- 	<option value="0">不開放</option> -->
		<!--</select><br> -->
			
			<!-- ============================================================================== -->

			<label for="" class="label1 posAbs">備註：</label>
			<form:textarea path="note" name="note" rows="5" cols="18" placeholder="可輸入寵物年齡" class="margleft125px" />
			<br>
		<!--<textarea id="" name="note" rows="5" cols="18" placeholder="可輸入寵物年齡" class="margleft125px"></textarea><br> -->
			
			<!-- ============================================================================== -->

			<a href="<c:url value='/preCreateAnimal.controller' />" class="btn btn-secondary">重填</a>
			<form:button value="Send" class="btn btn-primary">送出</form:button>
		<!--<button type="submit" name="submit" class="btn btn-primary">送出</button> -->
		<%--</form> --%>
		</form:form>
			
<!-- ====================================================================================================== -->
			
	</div>
</body>
</html>