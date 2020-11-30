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


<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
<jsp:include page="../fragments/links.jsp" />

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>


<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<style type="text/css">
.forumC:hover{
	border:10px solid black;
	
}
.forumC{
	border:10px solid #7e4c4f;
}
.fixed {
	position: fixed;
	bottom: 10%;
	right: 3%;
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
</style>


<title>討論區</title>

</head>
<body>



<script>
$(document).ready( function () {
    $('#articleTable').DataTable();
} );
</script>






	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>討論區</h2>
				<ul>
					<li><a href="index.html">首頁</a></li>
					<li class="active">討論區</li>
				</ul>
			</div>
		</div>
	</div>



<div class="container">
	<ul style="list-style: none; margin: 10px 0;">
		<li style="float: left; margin: 0px 10px 10px 10px;">
	<a href="<c:url value='saveArticle' />" >
<!-- 		<button type="button" class="btn btn-success">發文</button> -->
		<button class="submit btn-style" type="submit" style="margin-top: 10px;"><span style="color:white; margin-top: 0px;">發文</span></button>
	</a>
		</li>
		
<!-- 		<li style="float: left; margin: 10px 10px 10px 10px;"> -->
<!-- 		<input > -->
<!-- 		</li> -->
	
	</ul>
	<ul style="list-style: none; margin: 10px 0; clear:both;">
		<c:forEach items="${allArticleTypes}" var="ArticleType" varStatus="id">
				<a href="<c:url value='articleList?articletypesId=${ArticleType.getId()}' />" >
			<li style=" margin: 10px 10px 10px 10px; border-radius:25%; padding: 2px 3px; width: 9.09%; box-sizing: border-box; float: left; text-align: center;"  class="forumC">
<!-- 			<li style=" margin: 10px 10px 10px 10px; border: 10px solid #7e4c4f; border-radius:25%; padding: 2px 3px; width: 9.09%; box-sizing: border-box; float: left; text-align: center;"> -->
					
					<c:if test="${ArticleType.getId()=='1'}">
						<img src="https://image.flaticon.com/icons/png/512/194/194279.png" style="height: 20px; margin-bottom: 0px;">
					</c:if> 
					<c:if test="${ArticleType.getId()=='2'}">
						<img src="https://cdn4.iconfinder.com/data/icons/animal-3/100/animal-08-512.png" style="height: 20px; margin-bottom: 0px;">
					</c:if> 
					${ArticleType.getArticletype()}${Articletype.getId()}
				
			</li>
				</a>
		</c:forEach>
	</ul>

	<div style="clear:both; width: 100%;">
		<table border=1 style="width: 100%;" id="articleTable">
<!-- 			<th>文章ID</th> -->
			<th>文章標題</th>
			<th>選擇修改</th>
			<th>選擇刪除</th>
			<c:forEach items="${Articles}" var="Article" varStatus="id">
				<tr>
<%-- 					<td>${Article.getId()}</td> --%>
					<td><a href="<c:url value='goArticlePage?articleId=${Article.getId()}' />">
					${Article.getTitle()}</a></td>
					<td><a
						href="<c:url value='updateArticle?articleId=${Article.getId()}' />">
							<button type="button" class="btn btn-info">修改</button>
					</a></td>
					<td><a
						href="<c:url value='deleteArticle?articleId=${Article.getId()}' />">
							<button type="button" class="btn btn-danger">刪除</button>
					</a></td>
				<tr>
			</c:forEach>
		</table>
	</div>
</div>


	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>