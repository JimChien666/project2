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
.forum {
	font-size: 4.5em;
}
</style>

<title>討論區</title>
</head>
<body>

	<div class="row justify-content-center align-items-center">

		<form action="<c:url value='/ArticleDelete' />" enctype="text/html"
			method="post" class="was-validated">

			<div class="mb-3">
				<div class="input-group is-invalid">
					<div class="input-group-prepend">
						<span class="input-group-text" id="validatedInputGroupPrepend">文章刪除</span>
					</div>

					<input type="text" name="articleId" class="form-control is-invalid"
						aria-describedby="validatedInputGroupPrepend"
						placeholder="enter Something ..." required>

				</div>
				<div class="invalid-feedback">
					<!-- Example invalid input group feedback -->
				</div>
			</div>

			<button type="submit" name="delete" id="delete"
				class="btn btn-primary">刪除</button>
		</form>













		<form action="<c:url value='ArticleUpdate' />"
			enctype="multipart/form-data" method="post" class="was-validated">

			<div class="mb-3">
				<div class="input-group is-invalid">
					<div class="input-group-prepend">
						<span class="input-group-text" id="validatedInputGroupPrepend">根據文章id修改</span>
					</div>

					<input type="text" name="id" class="form-control is-invalid"
						aria-describedby="validatedInputGroupPrepend"
						placeholder="enter Something ..." required>

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
						placeholder="enter Something bitxx" required>

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
		<c:forEach items="${ArticleList}" var="Article" varStatus="id">
		${Article.getId()}
		${Article.getTitle()}
		<br>
		</c:forEach>
	</div>



</body>
</html>