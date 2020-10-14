<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Pet me, 陪你</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a class="nav-link" href="#">Home</a>
        </li>
        <%-- <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="<c:url value='/ProductsPage' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            購物商城
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="<c:url value='/jim/InsertProduct.jsp' />">新增商品</a>
            <a class="dropdown-item" href="<c:url value='/jim/QueryProduct.jsp' />">查詢商品</a>
            <a class="dropdown-item" href="<c:url value='/jim/DeleteProduct.jsp' />">刪除商品</a>
            <a class="dropdown-item" href="<c:url value='/ProductsPage' />">購物</a>
          </div>
        </li> --%>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/ProductsPage' />">購物</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/nn/controler/ShowIndexServlet' />">寵物旅遊</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/ServletReadAnimal' />">寵物領養</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/ArticleShow' />">討論區</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/FormMember.jsp' />" tabindex="-1" aria-disabled="true">會員中心</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://172.16.39.61:8080/project2" tabindex="-1" aria-disabled="true">活動專區</a>
        </li>
        
        
      </ul>
    </div>
  </nav>