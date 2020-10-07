<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<style>
		.span1{
			display: inline-block;
			width:120px;
			text-align:right;
		}
	</style>
</head>
<body>
<!-- https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_card_image&stacked=h -->
	<c:forEach var="AnimalsList" items="${AnimalsList}">
		<div class="card" style="width: 250px; float: left; margin:10px">
			<img class="card-img-top" src="" alt="Animal image">
			<div class="card-body">
				<span class="span1">動物編號：</span><span>${AnimalsList.animalId}</span><br>
				<span class="span1">會員編號：</span>${AnimalsList.memberId}<br>
				<span class="span1">收容動物編號：</span>${AnimalsList.acceptionId}<br>
				<span class="span1">品種編號：</span>${AnimalsList.breedId}<br>
				<span class="span1">性別：</span>${AnimalsList.gender}<br>
				<span class="span1">毛色：</span>${AnimalsList.coatColor}<br>
				<span class="span1">是否開放領養：</span>${AnimalsList.isAdoptionAvailable}<br>
			</div>
		</div>
	</c:forEach>
<!-- 				<a href="#" class="btn btn-primary">See Profile</a> -->
</body>
</html>