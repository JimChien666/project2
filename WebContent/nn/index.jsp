<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>寵物旅遊</title>
  <style>

    .circle{
      width:100px;
      height: 100px;
      line-height:100px;
      /* border:1px solid black; */
      border-radius: 50%;
      text-align: center;
      color:bisque;
      font-size: 24px;
      margin-top: 30px;
      background-color: #FF88C2;
      position: absolute;
      left:50%;
      transform: translate(-50%, 0%);
      cursor: pointer;
	    }
	    .circle:hover{
	    	box-shadow: 2px 2px 2px black;
	    	cursor: pointer;
	    }

  </style>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
  <jsp:include page="top.jsp" />



  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active" style="overflow: hidden; height: 600px;">
        <img src="<c:url value='/nn/images/狗狗1.jpg' />" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item" style="overflow: hidden; height: 600px;">
        <img src="<c:url value='/nn/images/狗狗2.jpg' />" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item" style="overflow: hidden; height: 600px;">
        <img src="<c:url value='/nn/images/狗狗3.jpg' />" class="d-block w-100" alt="...">
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  <div class="container">
    <div class="row">
    <c:forEach items="${attractionTypeList}" var="attractionType" varStatus="id">
    	<div class="col-sm">
        	<div class="circle"><a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${attractionType.getId()}&page=1&showNum=9&name=${attractionType.getName()}" style="text-decoration:none; color: white;">${attractionType.getName()}</a></div>
        </div>
    </c:forEach>
    </div>
  </div>

  <div class="container" style="margin-top: 200px;">
  	<c:forEach items="${attractionTypeList}" var="attractionType" varStatus="id">
  		<h2 style="margin-bottom: 50px;margin-top: 50px;">熱門${attractionType.getName()}:</h2>
  			<div class="row"  style=" padding: 30px; border-radius: 10px;">
  					<jsp:include page="/nn/controler/ShowOneTypeAttractionServlet?attrId=${attractionType.getId()}" />
  			</div>
  	</c:forEach>
    
  </div>
  <div class="circle" style="font-size: 30px; width: 50px; height: 50px;line-height:50px; position: fixed; top: 80%; left: 80%;"><a href="<c:url value='/nn/controler/CheckInsertAttractionServlet' />" style="text-decoration:none; color: white;">+</a></div>
</body>
</html>