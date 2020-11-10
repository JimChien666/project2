<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet"> -->
<title>文章發表</title>
<style>
h1 {
	font-family: 'Noto Sans TC', sans-serif
}
</style>
</head>
<body>
	<%-- <jsp:include page="../nn/top.jsp" /> --%>

	<main role="main" class="container">

		<div class="starter-template">
			<h1>文章發表</h1>
			<p class="lead">
				Share your story.<br> ...
			</p>
		</div>

	</main>
	<!-- /.container -->




	<form:form action="saveToDB" method="POST" modelAttribute="forums">
title:<form:input path="article.title" />
		<br>

articleType:


<%-- <form:select path="articletypesid" items="${allArticleTypes}" itemLabel="articletype" itemValue="id"> --%>
		<form:select path="article.articletypesid">
<%-- 						<form:option value="NONE" label="請選擇" /> --%>
						<form:option value="1" label="狗" />
						<form:option value="2" label="貓" />
		
		
<%-- 			<c:forEach items="${allArticleTypes}" var="ArticleType" --%>
<%-- 				varStatus="id"> --%>
<%-- 				<form:options >  --%>
<%-- 				<form:option value="${ArticleType.getId()}">${ArticleType.getArticletype()}</form:option> --%>
<%-- 				</form:options>  --%>
<%-- 			</c:forEach> --%>
		</form:select>
		<br>
article:<form:textarea path="content" />
		<br>
<form:hidden value="1" path="article.activitysid"/>		
<form:hidden value="1" path="article.showarticle"/>		
<form:hidden value="1" path="article.memberid"/>
<form:hidden value="1" path="memberid"/>		
<form:hidden value="1" path="voteid"/>		
		<form:button value="submit" type="submit">送出</form:button>
		
	</form:form>



</body>
</html>