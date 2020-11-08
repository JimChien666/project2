<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</style>
</head>
<body>
	<%-- <jsp:include page="../nn/top.jsp" /> --%>

	<main role="main" class="container">

		<div class="starter-template">
			<h1>文章發表</h1>
			<p class="lead">
				Share your story.<br> ...
			</p>
		</div>

	</main>
	<!-- /.container -->



<form action="saveToDB" enctype="multipart/form-data"
 method="post" class="was-validated" style="margin: 1% 10% 3% 3%;">
      <br>

      <!-- 標題-->
      <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">文章標題</span>
          </div>
          
          <input type="text" name="article.title" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="請輸入文章標題"  required>
        
        </div>
        <div class="invalid-feedback">
          <!-- Example invalid input group feedback -->
        </div>
      </div>

      <br>
      

      <!-- 選擇文章主題-->
      <div class="mb-3">
        <div class="input-group is-invalid">

          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">文章主題</span>
          </div>

          <select class="custom-select" name="article.articletypesid" id="Choose" required>
            <option value="">Choose...</option>
            <option value="1">狗</option>
            <option value="2">貓</option>

          </select>        </div>
        <div class="invalid-feedback">
          <!-- Example invalid input group feedback -->
        </div>
      </div> 
      

      <br>
      <!-- 輸入文章內文-->
    <div class="mb-3">
      <label for="validationTextarea">
        <div class="input-group-prepend">
          <span class="input-group-text" id="validatedInputGroupPrepend">文章內容</span>
        </div>
      </label>
      
      <textarea name="content" class="form-control is-invalid" id="validationTextarea" placeholder="請輸入文章內容"  style="height: 160px" required></textarea>

      <div style="float: right;">
        可以下拉調整大小
      </div>
    </div>



    <!-- 會員帳號
      <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">memIDID</span>
          </div>
          
          <input type="text" name="memberid" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="請輸入會員帳號"  required>
        
        </div>
        <div class="invalid-feedback">
        </div>
      </div>
    -->
    <!-- 會員帳號
      <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">memIDID</span>
          </div>
          
          <input type="text" name="article.memberid" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="請輸入會員帳號"  required>
        
        </div>
        <div class="invalid-feedback">
        </div>
      </div>
    -->
   
      
      
      
      
    
        <button type="submit" name="submit" id="submit" class="btn btn-primary">發文</button>
    </form>













<%-- <form:form action="saveToDB" method="POST" modelAttribute="forums"> --%>
<%-- title:<form:input path="article.title"/><br> --%>

<%-- articleType:<form:select path="articletype" items="${allArticleTypes}" itemLabel="articletype" itemValue="id"> --%>
<%-- </form:select><br> --%>

<%-- article:<form:textarea path="content"/> --%>
<%-- </form:form> --%>



</body>
</html>