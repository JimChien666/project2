<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>討論區</title>
</head>
<body>
<div>
	<c:forEach items="${allArticleTypes}" var="ArticleType" varStatus="id">
		<div><a href="<c:url value='ArticleShow?articletypesId=${ArticleType.getId()}' />">${ArticleType.getArticleType()}</a></div>
	</c:forEach>
</div>
<!--  
	<c:forEach items="${ArticleList}" var="Article" varStatus="id">
		${Article.getTitle()}
	</c:forEach>
-->
</body>
</html>