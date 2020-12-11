<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

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
<!-- <script src="https://cdn.ckeditor.com/ckeditor5/23.1.0/classic/ckeditor.js"></script> -->










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

.fixed0 {
	position: fixed;
	bottom: 15%;
	right: 2%;
}
</style>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
<jsp:include page="../fragments/links.jsp" />


</head>
<body>
<%-- <script src="<c:url value='/js/ckeditor/config.js' />"></script> --%>

<%-- <script src="<c:url value='/js/ckeditor/ckeditor.js' />"></script> --%>
<script src="<c:url value='/assets/javascripts/ckeditor/ckeditor.js' />"></script>

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
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li class="active">討論區</li>
				</ul>
			</div>
		</div>
	</div>


	<div class="container">
		<ul style="list-style: none; margin: 0px 0; ;">
			<li style="float: left; margin: 0px 10px 10px 10px;"><a
				href="<c:url value='/backArticle' />">
					<button class="submit btn-style" type="submit"
						style="margin-top: 10px;">
						<span style="color: white; margin-top: 0px;">回討論版</span>
					</button>
			</a></li>
		</ul>

		<div class="starter-template" style="clear:both">
			<h1>文章發表</h1>
			<p class="lead">
				Share your story...<br>
			</p>
		</div>



	<form:form action="saveToDB" method="POST" modelAttribute="forums">
<form:input path="article.title" placeholder="請輸入標題"/>
		<br>
		<br>
		
articleType:
<%-- <form:select path="articletypesid" items="${allArticleTypes}" itemLabel="articletype" itemValue="id"> --%>
		<div style="width:75px">
		<form:select path="article.articletypesid">
<%-- 									<form:option value="NONE" label="請選擇" /> --%>
			<form:option value="1" label="狗" />
			<form:option value="2" label="貓" />
		</form:select>
		</div>
		<br>
<%-- 		發表到狗狗討論版:<form:radiobutton value="1" path="article.articletypesid" /> --%>
<%-- 		發表到貓咪討論版:<form:radiobutton value="2" path="article.articletypesid" /> --%>
<%-- article:<form:textarea path="content" /> --%>

<br>
<br>
內文:<form:textarea name="editor" id="editor" path="content" />
				
		<br>
		<form:hidden value="0" path="article.activitysid" />
		<form:hidden value="1" path="article.showarticle" />
<%-- 		<form:hidden value="0" path="voteid" /> --%>

		投票主題:<form:textarea   path="votetopic" placeholder="請輸入投票主題" />
		插入圖片:<input name="contentImage" type="file" multiple/><span style="color: red;">${errors.multipartFile}</span><br/>
		
		加入投票選項:<button></button>




		<form:button value="submit" type="submit">送出</form:button>
	
	
	<script>

CKEDITOR.replace( "editor", {});

    </script>
	
	
	
	
	</form:form>


	</div>
	<!-- /.container -->

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />

</body>
</html>