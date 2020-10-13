<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
<title>Post Article</title>
<style>
h1{
font-family: 'Noto Sans TC', sans-serif
}
</style>
</head>
<body>

<main role="main" class="container">
      
      <div class="starter-template">
        <h1>文章發表</h1>
        <p class="lead">Use this document as a way to quickly fuck any new project.<br> ...</p>
      </div>

    </main><!-- /.container -->   
    
    

<form action="<c:url value='/ArticleInsert' />" enctype="multipart/form-data"
 method="post" class="was-validated" style="margin: 1% 10% 3% 3%;">

<!-- ID
      <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">IDID</span>
          </div>
          
          <input type="text" name="id" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="enter Something bitxx"  required>
        
        </div>
        <div class="invalid-feedback">
           Example invalid input group feedback 
        </div>
      </div>
-->
      <br>


      <!-- 標題-->
      <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">文章標題</span>
          </div>
          
          <input type="text" name="title" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="enter Something bitxx"  required>
        
        </div>
        <div class="invalid-feedback">
          <!-- Example invalid input group feedback -->
        </div>
      </div>

      <br>
      
      
      <!-- actID
      <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">activitysId</span>
          </div>
          
          <input type="text" name="activitysId" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="enter Something bitxx"  required>
        
        </div>
        <div class="invalid-feedback">
          Example invalid input group feedback
        </div>
      </div>

      <br>
      -->
      

      <!-- 選擇文章主題-->
      <div class="mb-3">
        <div class="input-group is-invalid">

          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">文章主題</span>
          </div>

          <select class="custom-select" name="articleType" id="Choose" required>
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
      
      <textarea class="form-control is-invalid" id="validationTextarea" placeholder="Required example textarea"  style="height: 160px" required></textarea>

      <div style="float: right;">
        可以下拉調整大小
      </div>
    </div>
    
    <!-- showArticle
      <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">showArticle</span>
          </div>
          
          <input type="text" name="showArticle" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="enter Something bitxx"  required>
        
        </div>
        <div class="invalid-feedback">
           Example invalid input group feedback 
        </div>
      </div>

      <br>
    -->
    
    <!-- 會員帳號-->
      <div class="mb-3">
        <div class="input-group is-invalid">
          <div class="input-group-prepend">
            <span class="input-group-text" id="validatedInputGroupPrepend">memIDID</span>
          </div>
          
          <input type="text" name="memberId" class="form-control is-invalid" aria-describedby="validatedInputGroupPrepend" placeholder="enter Something bitxx"  required>
        
        </div>
        <div class="invalid-feedback">
          <!-- Example invalid input group feedback -->
        </div>
      </div>
    
        <button type="submit" name="submit" id="submit" class="btn btn-primary">發文</button>
    </form>

</body>
</html>