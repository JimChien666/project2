<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">


<title>討論區</title>
</head>
<body>
<div>
	<c:forEach items="${allArticleTypes}" var="ArticleType" varStatus="id">
		<div><a href="<c:url value='ArticleShow?articletypesId=${ArticleType.getId()}' />">${ArticleType.getArticleType()}</a></div>
	</c:forEach>
</div>

	<c:forEach items="${ArticleList}" var="Article" varStatus="id">
		${Article.getTitle()}
	</c:forEach>
	
<form action="<c:url value='/ArticleDelete' />" enctype="text/html"
 method="post" class="was-validated" style="margin: 1% 10% 3% 3%;">	
        
        <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">文章刪除</span>
          </div>
          
          <input type="text" name="articleId" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="enter Something ..."  required>
        
        </div>
        <div class="invalid-feedback">
          <!-- Example invalid input group feedback -->
        </div>
      </div>

      <br>
        
        <button type="submit" name="delete" id="delete" class="btn btn-primary">刪除</button>
    </form>        


</body>
</html>