<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新動物</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<jsp:include page="../public/top.jsp" />
	<div class="wid400px">
		<a href="<c:url value='/readAnimal' />" class="btn btn-secondary margin10px">回維護首頁</a>

<!-- ====================================================================================================== -->
			
		<form:form action="UpdateAnimal.controller" method="POST" modelAttribute="animals">
<%-- 	<form action="<c:url value='/UpdateAnimals' />" method="post" enctype="multipart/form-data"> --%>
			
			<!-- ============================================================================== -->
			
		<label for="" class="label1">照片：</label> 
		<input type="file" name="animalFile" id="animalFile1" multiple class="wid200px">
		<span id="animalFileSpan2" class="dispblok height260px margleft125px">
		<img class="card-img-top square250px" src="${pageContext.servletContext.contextPath}/ServletRetrieveImage?id=${animals.animalId}&type=ANIMAL" alt="Animal image">
		</span> 
		<span id="animalFileSpan1" class="spanHidden height260px">
		<img id="preview_animalFile" src="#" class="square250px" /><br>
		</span>
			
			<!-- ============================================================================== -->
		
		<label for="" class="label1">動物編號：</label> 
		<form:input path="animalId" type="text" name="animalId" value="${animals.animalId}" class="spanHidden"/>
		<label>${animals.animalId}</label>
		<br>
<!-- 		label不能用value，input readonly="readonly", disable="disabled"無作用-->
<%-- 		<input type="text" name="animalId" value="${animals.animalId}" readonly><br> --%>
			
			<!-- ============================================================================== -->
		
		<label for="" class="label1">會員編號：</label> 
		<form:input path="memberId" type="text" name="memberId" value="${animals.memberId}" onblur="checkmemberId()" id="memberId"/>
		<br>
<%-- 		<input type="text" name="memberId" value="${animals.memberId}" onblur="checkmemberId()" id="memberId" required><br>  --%>
		<span id="memberIdSpan" class="spanHidden"><br></span>
			
			<!-- ============================================================================== -->
		
		<label for="" class="label1">收容動物編號：</label> 
		<form:input path="acceptionId" type="text" name="acceptionId" value="${animals.acceptionId}" onblur="checkacceptionId()" id="acceptionId"/>
		<br>
<%-- 		<input type="text" name="acceptionId" value="${animals.acceptionId}" onblur="checkacceptionId()" id="acceptionId"><br>  --%>
		<span id="acceptionIdSpan" class="spanHidden"><br></span>
			
			<!-- ============================================================================== -->
		
		<label for="" class="label1">品種編號：</label> 
		<form:input path="breedId" type="text" name="breedId" value="${animals.breedId}" onblur="checkbreedId()" id="breedId"/>
		<br>
<%-- 		<input type="text" name="breedId" value="${animals.breedId}" onblur="checkbreedId()" id="breedId" required><br>  --%>
		<span id="breedIdSpan" class="spanHidden"><br></span>
			
			<!-- ============================================================================== -->
		
		<label for="" class="label1">性別：</label> 
		<form:select path="gender" id="">
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
<%-- 			<c:choose> --%>
<%-- 				<c:when test="${animals.gender == 1}"> --%>
<%-- 					<option value="1" selected>公</option> --%>
<%-- 					<option value="0">母</option> --%>
<%-- 				</c:when> --%>
<%-- 				<c:otherwise> --%>
<%-- 					<option value="1">公</option> --%>
<%-- 					<option value="0" selected>母</option> --%>
<%-- 				</c:otherwise> --%>
<%-- 			</c:choose> --%>
		</form:select><br> 
			
			<!-- ============================================================================== -->
		
		<label for="" class="label1">毛色：</label> 
		<form:input path="coatColor" type="text" name="coatColor" value="${animals.coatColor}" onblur="checkcoatColor()" id="coatColor"/>
		<br>
<%-- 		<input type="text" name="coatColor" value="${animals.coatColor}" onblur="checkcoatColor()" id="coatColor"><br>  --%>
		<span id="coatColorSpan" class="spanHidden"><br></span>
			
			<!-- ============================================================================== -->
		
		<label for="" class="label1">是否開放領養：</label> 
		<form:select path="isAdoptionAvailable" id="">
			<c:choose>
				<c:when test="${animals.isAdoptionAvailable == 1}">
					<form:option value="1" selected="selected">開放</form:option>
					<form:option value="0">不開放</form:option>
<!-- 					<option value="1" selected>開放</option> -->
<!-- 					<option value="0">不開放</option> -->
				</c:when>
				<c:otherwise>
					<form:option value="1">開放</form:option>
					<form:option value="0" selected="selected">不開放</form:option>
<!-- 					<option value="1">開放</option> -->
<!-- 					<option value="0" selected>不開放</option> -->
				</c:otherwise>
			</c:choose>
		</form:select><br> 
			
			<!-- ============================================================================== -->
		
		<label for="" class="label1 posAbs">備註：</label>
		<form:textarea path="note" name="note" rows="5" cols="18" class="margleft125px" value="${animals.note}"/>
<%-- 		<textarea id="" name="note" rows="5" cols="18" class="margleft125px">${animals.note}</textarea> --%>
		<br> 
			
			<!-- ============================================================================== -->
		
		<a href="<c:url value='/preUpdateAnimal.controller' />?animalId=${animals.animalId}" class="btn btn-secondary">回復修改</a>
		<form:button value="Send" name="update" class="btn btn-primary">修改</form:button>
<!-- 		<button type="submit" name="update" class="btn btn-primary">修改</button> -->
<!-- 可加入新增修改刪除時間 -->
		</form:form>
<%-- 		</form> --%>
			
<!-- ====================================================================================================== -->
			
	</div>
</body>
</html>