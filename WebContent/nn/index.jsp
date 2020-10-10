<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
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
	    @charset "UTF-8";
	* {
	  font-family: 微軟正黑體;
	  position: relative;
	}
	
	
	.phone {
	  width: 300px;
	  border: solid 1px #888; 
	  border-radius: 10px;
	}
	
	.top {
	  width: 100%;
	  height: 150px;
	  background-size: cover;
	  background-position: center center;
	}
	
	.bottom {
	  width: 100%;
	  height: 200px;
	  background: white;
	}
	
	.headpic {
	  width: 80px;
	  height: 80px;
	  position: absolute;
	  top: -50px;
	  left: 20px;
	  background-size: cover;
	  border: solid 2px white;
	  border-radius: 50%;
	}
	
	.name {
	  right: -110px;
	  top: 10px;
	  color: white;
	  letter-spacing: 2px;
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
        <img src="<c:url value='/nn/images/狗狗1.jpeg' />" class="d-block w-100" alt="...">
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
        	<div class="circle">${attractionType.getName()}</div>
        </div>
    </c:forEach>
    </div>
  </div>

  <div class="container" style="margin-top: 200px;">
  	<c:forEach items="${attractionTypeList}" var="attractionType" varStatus="id">
  		<h2>熱門${attractionType.getName()}:</h2>
  			<jsp:include page="/nn/controler/ShowOneTypeAttractionServlet?attrId=${attractionType.getId()}" />
  		<%-- <div class="row"  style=" padding: 30px; border-radius: 10px;">
  		  <c:forEach items="${attractionIntroductionList}" var="attractionIntroduction" varStatus="id">
	      <div class="col-lg-4">
	        <div class="phone">
	        <style>
	        		</style>
			  		<div class="top" style="background-image: '<c:url value='/nn/images/狗狗1.jpeg' />'"></div>
			 			 <div class="bottom">
			    			<div class="headpic">
			     			 <div class="name">${attractionIntroduction.getName()}</div>
			    			</div>
			  	</div>
			</div>
	      </div>
	      </c:forEach>
    	</div> --%>
    	
  	</c:forEach>
    
  </div>
</body>
</html>