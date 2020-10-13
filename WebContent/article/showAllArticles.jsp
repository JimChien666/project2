<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap"
	rel="stylesheet">
<style type="text/css">
.fixed {
	position: fixed;
	bottom: 3%;
	right: 2%;
}

.fixed0 {
	position: fixed;
	top: 12%;
	right: 1%;
}

.fixed1 {
	position: fixed;
	top: 40%;
	right: 1%;
}

.forum {
	font-size: 4.5em;
}

table {
	font-size: 2em;
}

.box {
	width: 40px;
	background: lightskyblue;
	transition: .6s;
}

.active {
	width: 250px;
	background: pink;
	border-radius: 5%;
	transition: .5s;
}
</style>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap"
	rel="stylesheet">

<title>討論區</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<div class="btn btn-secondary float-right fixed0" id="idbox"
		class="box">

		<form action="<c:url value='/ArticleDelete' />" enctype="text/html"
			method="post" class="was-validated">

			<div class="mb-3">
				<div class="input-group is-invalid">
					<div class="input-group-prepend">
						<span class="input-group-text" id="validatedInputGroupPrepend">文章刪除</span>
					</div>

					<input type="text" name="articleId" class="form-control is-invalid"
						aria-describedby="validatedInputGroupPrepend"
						placeholder="enter id" required>

				</div>
				<div class="invalid-feedback">
					<!-- Example invalid input group feedback -->
				</div>
			</div>

			<button type="submit" name="delete" id="delete"
				class="btn btn-primary">刪除</button>
		</form>
	</div>

	<div class="btn btn-secondary float-right fixed1 box" id="idbox1">

		<form action="<c:url value='ArticleUpdate' />"
			enctype="multipart/form-data" method="post" class="was-validated">

			<div class="mb-3">
				<div class="input-group is-invalid">
					<div class="input-group-prepend">
						<span class="input-group-text" id="validatedInputGroupPrepend">根據文章id修改</span>
					</div>

					<input type="text" name="id" class="form-control is-invalid"
						aria-describedby="validatedInputGroupPrepend"
						placeholder="enter id" required>

				</div>
				<div class="invalid-feedback">
					<!-- Example invalid input group feedback -->
				</div>
			</div>

			<div class="mb-3">
				<div class="input-group is-invalid">
					<div class="input-group-prepend">
						<span class="input-group-text" id="validatedInputGroupPrepend">文章標題</span>
					</div>

					<input type="text" name="title" class="form-control is-invalid"
						aria-describedby="validatedInputGroupPrepend"
						placeholder="enter title" required>

				</div>
				<div class="invalid-feedback">
					<!-- Example invalid input group feedback -->
				</div>
			</div>
			<button type="submit" name="update" id="update"
				class="btn btn-primary">修改</button>
		</form>
	</div>

	<a href="<c:url value='/article/PostArticle.jsp' />" class="fixed">
		<button type="button" name="delete" class="btn btn-success">發文
		</button>
	</a>


	<div class="row justify-content-center align-items-center forum">
		<c:forEach items="${allArticleTypes}" var="ArticleType" varStatus="id">
			<div>
				<a
					href="<c:url value='ArticleShow?articletypesId=${ArticleType.getId()}' />">${ArticleType.getArticleType()}</a>
			</div>
		</c:forEach>
	</div>

	<div align="center">
		<table border=1>
			<th>文章ID</th>
			<th>文章標題</th>
			<c:forEach items="${ArticleList}" var="Article" varStatus="id">
				<tr>
					<td>${Article.getId()}</td>
					<td>${Article.getTitle()}</td>
				<tr>
			</c:forEach>
		</table>
	</div>
	<script>
		$(function() {

			$("#idbox").hover(over, out);

			function over() {
				$('#idbox').removeClass("box").addClass("active");
			}
			function out() {
				$('#idbox').removeClass("active").addClass("box");
			}
		})
	</script>
	<script>
		$(function() {

			$("#idbox1").hover(over, out);

			function over() {
				$('#idbox1').removeClass("box").addClass("active");
			}
			function out() {
				$('#idbox1').removeClass("active").addClass("box");
			}
		})
	</script>
</body>
</html>