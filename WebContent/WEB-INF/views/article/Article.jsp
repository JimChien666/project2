<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
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

<script>

$(document).ready(function(){
	$.ajax({
			type:"GET",
			url:"<c:url value='article' />?articleId=461",
			success : function(responseData){
				showPageOrders(responseData)
	         }
		});

})



function showPageOrders(responseData){
	var $article = $("#articleShow")
// 	var mapData = JSON.parse(responseData);
	var mapData = responseData;
	article = mapData.article;
	pageNo = mapData.currPage;
	totalPage  = mapData.totalPage;
	recordCounts = mapData.recordCounts;
	var content="";
	$article.append("<h3>"+article.title+"</h3>")
	$article.append("<table><tr><th>討論串編號</th><th>討論串內容</th></tr>")

	$.each(article.forums, function(i, forum){
		$article.append("<tr><td>" + forum.id + "</td><td>"+forum.content+"</td></tr>")
		$article.append("</table>");
		$article.append("<hr>");
		$.each(forum.comments.sort(), function(j, c){
				$article.append("<tr><th>留言編號</th><th>留言內容</th></tr>")
				$article.append("<tr><td>" + c.id + "</td><td>"+c.comment+"</td></tr>")
			})
		
		})
		
	
}






</script>














<body>
	<jsp:include page="../public/top.jsp" />

<div align='center'>
	<h3>${article.getTitle()}</h3>
	
	<div id="articleShow"></div>
	<div id='navigation' style='height:60px;'></div>
	
</div>

	<a href="<c:url value='backArticle' />" class="fixed">
		<button type="button" class="btn btn-success">回討論版</button>
	</a>
	<a href="<c:url value='replyArticle?articleId=${article.getId()}' />"
		class="fixed0">
		<button type="button" class="btn btn-success">回覆文章</button>
	</a>


</body>
</html>