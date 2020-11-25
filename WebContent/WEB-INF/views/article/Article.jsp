<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>


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

/* table, th, td { */
/* 	border: 1px solid black; */
/* } */
td {
border: 1px solid black;
}
</style>
<meta charset="UTF-8">
<title>ID:<c:out value="${article.getId()}" />/<c:out
		value="${article.getTitle()}" /></title>
</head>

<script>

$(document).ready(function(){
	console.log("hi")
	$("#articleShow").DataTable({
	"ajax": {
        "type" : "GET",
        "url" : "<c:url value='article' />?articleId=${articleId}",
        "dataSrc": function (json){
        	console.log(json.article.forums)
        	return json.article.forums 	
            }
		},
	"columns" :[
		{"data": "id"},
		{"data": "content"}
		]
		});
})

</script>



<body>
<%-- 	<jsp:include page="../public/top.jsp" /> --%>

<div align='center'>
	<h3>${article.getTitle()}</h3>
	
<!-- 	<div id="articleShow"></div> -->
	
<table id='articleShow'>
<thead>
	<th>id</th>
	<th>content</th>
</thead>
<tfoot>
	<th>id</th>
	<th>content</th>
</tfoot>
</table>
	
	
	<div id='navigation' style='height:60px;'></div>
	
</div>

	<a href="<c:url value='backArticle' />" class="fixed">
		<button type="button" class="btn btn-success">回討論版</button>
	</a>
	<a href="<c:url value='replyArticle?articleId=${articleId}' />"
		class="fixed0">
		<button type="button" class="btn btn-success">回覆文章</button>
	</a>


</body>
</html>