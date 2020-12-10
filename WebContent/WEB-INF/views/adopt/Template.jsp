<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
<!-- 跳轉網頁動畫1 -->
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<jsp:include page="../fragments/links.jsp" />
</head>
<body style="margin:0;">
	<div id="loader"></div>
	<div style="display:none;" id="myDiv" class="animate-bottom">
<!-- 跳轉網頁動畫1 -->
	
	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>愛心犬貓認養須知</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li><a href="<c:url value='/adopt'/>">全部動物</a></li>
					<li><a
						href="<c:url value='/AdoptAnimalDetails.controller?id=${adoptionRecord.animal.animalId}'/>">動物資料</a></li>
					<li class="active">認養須知</li>
				</ul>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
	
<!-- 跳轉網頁動畫2 -->
	</div>
</body>
<script>
    setTimeout(function () {
        $(document).ready(function () {
            document.getElementById("loader").style.display = "none";
            document.getElementById("myDiv").style.display = "block";
        });
     }, 500);
</script>
<!-- 跳轉網頁動畫2 -->

</html>