<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>Blog</h2>
				<ul>
					<li><a href="index.html">home</a></li>
					<li class="active">Blog</li>
				</ul>
			</div>
		</div>
	</div>
<<<<<<< HEAD
<h1 style="text-align:center;">還沒做拉</h1>
=======
	
	
	
<h1 style="text-align:center;">還沒做拉</h1>
<iframe
    allow="microphone;"
    width="350"
    height="430"
    src="https://console.dialogflow.com/api-client/demo/embedded/46757a64-945c-4f4e-ae7b-db249796cfda">
</iframe>



>>>>>>> refs/heads/wey
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>