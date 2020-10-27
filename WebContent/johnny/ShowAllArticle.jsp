<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<%
	response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.sactivelim.min.js"
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

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

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


<title>討論區</title>

</head>
<body>
	<jsp:include page="../nn/top.jsp" />
	<div class="btn btn-secondary float-right fixed0 box" id="idbox">

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

		<form action="<c:url value='/ArticleUpdate' />"
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

	<a href="<c:url value='/johnny\PostArticle.jsp' />" class="fixed">
		<button type="button" name="delete" class="btn btn-success">發文
		</button>
	</a>


	<div class="row justify-content-center align-items-center forum">
		<c:forEach items="${allArticleTypes}" var="ArticleType" varStatus="id">
			<div style="border: 3px solid black; width: 50%; text-align: center;">
				<a
					href="<c:url value='ShowAllArticle?articletypesId=${ArticleType.getId()}' />">
					<c:if test="${ArticleType.getId()=='1'}">
						<img src="https://image.flaticon.com/icons/png/512/194/194279.png"
							style="height: 80px; margin-bottom: 10px;">
					</c:if> 
					<c:if test="${ArticleType.getId()=='2'}">
						<img
							src="https://cdn4.iconfinder.com/data/icons/animal-3/100/animal-08-512.png"
							style="height: 80px; margin-bottom: 10px;">
					</c:if> ${ArticleType.getArticletype()}${Articletype.getId()}
				</a>
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