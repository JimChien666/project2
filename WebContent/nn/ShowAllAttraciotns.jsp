<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="top.jsp" />
  	<div class="container">
  	<span style="margin: 10px;">${totalNum}筆資料</span><div class="btn-group">
	  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    ${param.name}
 	 </button>
	  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		  <c:forEach items="${attractionTypeList}" var="attractionType" varStatus="id">
		    <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/ShowAllAttractionsServlet?attrId=${attractionType.getId()}&page=1&showNum=9&name=${attractionType.getName()}">${attractionType.getName()}</a>
		   </c:forEach>
	  </div>
	 <div class="btn-group">
	  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    ${param.cityName}<c:if test="${param.cityName == null}">請選擇城市</c:if>
	  </button>
	  <form class="form-inline my-2 my-lg-0" style="margin-left: 80px;" method="get" action="${pageContext.servletContext.contextPath}/ShowAllAttractionsServlet">
        <input class="form-control mr-sm-2" type="search" placeholder="輸入景點名稱" aria-label="Search" name="search" value=${param.search}>
        <c:if test="${param.cityName != null}">
        	<input name="cityName" value=${param.cityName}  type="hidden">
        </c:if>
        <c:if test="${param.cityId != null}">
        	<input name="cityId" value=${param.cityId}  type="hidden">
        </c:if>
        <input name="attrId" value=${param.attrId}  type="hidden">
        <input name="page" value=${param.page}  type="hidden">
        <input name="showNum" value="9"  type="hidden">
        <input name="name" value=${param.name}  type="hidden">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
	  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		  <c:forEach items="${cityList}" var="city" varStatus="id">
		    <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/ShowAllAttractionsServlet?attrId=${param.attrId}&page=${param.page}&showNum=9&name=${param.name}&cityName=${city.getName()}&cityId=${city.getId()}">${city.getName()}</a>
		   </c:forEach>
	  </div>
	  </div>

	</div>    
