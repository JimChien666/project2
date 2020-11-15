<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
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
<style type="text/css">
.fixed {
	position: fixed;
	bottom: 3%;
	right: 2%;
}

.fixed0 {
	position: fixed;
	bottom: 15%;
	right: 2%;
}

table, th, td {
	border: 1px solid black;
}
</style>
<meta charset="UTF-8">
<title>ID:<c:out value="${article.getId()}" />/<c:out
		value="${article.getTitle()}" /></title>
</head>
<body>
	<jsp:include page="../public/top.jsp" />

	<table>
		<th colspan="3">標題:<c:out value="${article.getTitle()}"></c:out></th>

		<tr>
			<td>發文時間</td>
			<td colspan="3">作者:<c:out
					value="${article.getMember().getName()}"></c:out></td>
		</tr>


		<c:forEach items="${forums}" var="Forums" varStatus="id">
			<c:if test="${id.isLast()}">
				<tr>
					<td>${Forums.getMember().getName()}</td>
					<td>${Forums.getContent()}</td>
					<td>${Forums.getCreatedat()}</td>
				</tr>
			</c:if>

		</c:forEach>
	</table>
	<br>

	<table>
		<th>標題:<c:out value="${article.getTitle()}"></c:out></th>
		<th>發文時間</th>
		<th>作者:<c:out value="${article.getMember().getName()}"></c:out></th>

		<c:forEach items="${forums}" var="Forums" varStatus="id">
			<c:if test="${!id.isLast()}">
				<tr>
					<td>${Forums.getContent()}</td>
					<td>${Forums.getCreatedat()}</td>
					<td>${Forums.getMember().getName()}</td>
				</tr>
				<c:forEach items="${comments}" var="Comments" varStatus="id">
					<tr>
						<td></td>
					</tr>
				</c:forEach>

			</c:if>
		</c:forEach>
	</table>
	<a href="<c:url value='backArticle' />" class="fixed">
		<button type="button" class="btn btn-success">回討論版</button>
	</a>
	<a href="<c:url value='replyArticle?articleId=${article.getId()}' />"
		class="fixed0">
		<button type="button" class="btn btn-success">回覆文章</button>
	</a>
<script type="text/javascript">

</script>
</body>
</html>