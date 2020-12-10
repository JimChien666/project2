<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>領養申請列表</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<style>
td {
	border: 1px black solid;
	text-align: center;
}
</style>
<!-- 跳轉網頁動畫1 -->
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<jsp:include page="../fragments/links.jsp" />
</head>
<body style="margin: 0;">
	<div id="loader"></div>
	<div style="display: none;" id="myDiv" class="animate-bottom">
		<!-- 跳轉網頁動畫1 -->

		<div>
			<jsp:include page="../fragments/headerArea.jsp" />
		</div>
		<!-- 	麵包屑 -->
		<div class="breadcrumb-area pt-95 pb-95 bg-img"
			style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
			<div class="container">
				<div class="breadcrumb-content text-center">
					<h2>領養申請列表</h2>
					<ul>
						<li><a href="<c:url value='/'/>">首頁</a></li>
						<li><a href="<c:url value='/member/myAccount' />">會員中心</a></li>
						<li class="active">領養申請列表</li>
					</ul>
				</div>
			</div>
		</div>

		<!-- 	==================================================================================== -->

		<table class="mt-50 mb-50 font22 wid1000">
			<thead>
				<tr>
					<td>寵物圖片</td>
					<td>申請時間</td>
					<td>申請資料</td>
					<td>審核狀態</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="AdoptionRequestList" items="${AdoptionRequestList}">
					<tr>
						<td>${AdoptionRequestList.animal.animalId}</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<jsp:include page="../fragments/footerArea.jsp" />
		<jsp:include page="../fragments/allJs.jsp" />

		<!-- 跳轉網頁動畫2 -->
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
<!-- 跳轉網頁動畫2 -->

</html>