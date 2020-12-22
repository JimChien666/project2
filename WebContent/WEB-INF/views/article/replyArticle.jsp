<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
<jsp:include page="../fragments/links.jsp" />
<head>
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" -->
<!-- 	crossorigin="anonymous"> -->
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 	crossorigin="anonymous"></script> -->
<!-- <script -->
<!-- 	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" -->
<!-- 	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" -->
<!-- 	crossorigin="anonymous"></script> -->
<!-- <script -->
<!-- 	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" -->
<!-- 	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" -->
<!-- 	crossorigin="anonymous"></script> -->
<!-- <script src="https://cdn.ckeditor.com/ckeditor5/23.1.0/classic/ckeditor.js"></script> -->

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet"> -->
<title>文章回覆</title>
<style>
h1 {
	font-family: 'Noto Sans TC', sans-serif
}

.btncls {
	background-color: #7E4C4F; /* Green */
	border: none;
	color: white;
	padding: 10px 15px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	border-radius: 10px;
	transition-duration: 0.3s;
	cursor: pointer;
}

button.btncls:hover {
	background-color: #000000;
}
</style>
<!-- 轉頁載入動畫1 -->
<link rel="stylesheet"
	href="<c:url value='/css/loadingAnimation.css' />">
</head>
<body>
	<div id="loader"></div>
	<div style="display: none;" id="myDiv" class="animate-bottom">
		<!-- 轉頁載入動畫1 -->
	<script
		src="<c:url value='/assets/javascripts/ckeditor/ckeditor.js' />"></script>

	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	
	

	
	
	
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>文章回覆</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li class="active">文章回覆</li>
				</ul>
			</div>
		</div>
	</div>

	<%-- 	<jsp:include page="../public/top.jsp" /> --%>
	<%-- <jsp:include page="../nn/top.jsp" /> --%>

	<div class="container">
		<ul style="list-style: none; margin: 0px 0;">
			<li style="float: left; margin: 0px 10px 10px 10px;"><a
				href="<c:url value='/backArticle' />">
					<button class="submit btn-style" type="submit"
						style="margin-top: 10px;">
						<span style="color: white; margin-top: 0px;">回討論版</span>
					</button>
			</a></li>
		</ul>

			<ul style="list-style: none; margin: 0px 0;">
				<li style="float: right; margin: 0px 10px 30px 10px;">
						<button class="submit btn-style" type="submit" id='createArticle'
							style="margin-top: 10px;">
							<span style="color: white; margin-top: 0px;">快速輸入資料</span>
						</button>
				</li>
			</ul>

		<div class="starter-template" style="clear: both">
			<h1>文章回覆</h1>
			<p class="lead">
				分享更多您的故事~<br>
			</p>
		</div>
		<hr>
		<form:form action="/team6/replyToDB" method="POST"
			modelAttribute="forums">
			<br>
			<form:label path="content">回覆內容</form:label>
			<form:textarea name="content" id="editor" path="content" />
			<br>
			<%-- 		<form:hidden value="1" path="voteid" /> --%>
			<%-- 		<form:hidden value="${article.getId()}" path="aricle.id" /> --%>
			<%-- 		<form:hidden value="${forum.getArticle().getId()}" path="forums.aricle.id" /> --%>
			<%--        <input type="hidden" name="id" value="${forums.getArticle().getId()}"> --%>
			<input type="hidden" name="id" value="${article.getId()}">
				
			<center>
				<form:button class="btncls" value="submit" type="submit">送出</form:button>
			</center>
					<hr>
			
			<script>
			var myeditor=CKEDITOR.replace("editor", {});
			$(document).on("click","#createArticle",function() {					
					console.log("產生!")
					myeditor.setData('<h2 style="font-style:italic"><span style="font-size:24px"><span style="font-family:微軟正黑體">我家的貓咪也很可愛</span></span></h2><p><img src="https://i.imgur.com/RXuuCv5.jpg" width="100%" /><span style="font-size:18px">在院子裡面的樣子</span></p><p>&nbsp;</p><p><img src="https://i.imgur.com/GbR2UDQ.jpg" width="100%" /><span style="font-size:18px">故意壓在草上</span></p><p><img src="https://i.imgur.com/Wgweiy0.jpg" width="100%" /><span style="font-size:18px">天氣冷，窩在紙箱裡</span></p><p>&nbsp;</p><p><img src="https://i.imgur.com/7ecmFyF.jpg" width="100%" /><span style="font-size:18px">傻呼呼的扭來扭去</span></p>');
				});	
			</script>
		</form:form>


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
</html>