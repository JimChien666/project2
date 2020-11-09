<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">Pet me, 陪你</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="#">Home</a></li>
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
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/ProductsPage' />">購物</a></li>
			<%-- <li class="nav-item"><a class="nav-link"
				href="<c:url value='/ShowIndexServlet' />">寵物旅遊</a></li> --%>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/ServletReadAnimal' />">寵物領養</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/ArticleShow' />">討論區</a></li>
			<c:if test="${empty LoginOK}">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/member/register' />" tabindex="-1"
					aria-disabled="true">註冊會員</a></li>
			</c:if>

		</ul>

		<c:if test="${!empty LoginOK}">
			<div class="navbar-brand">
				<img
					src="<c:url value='/member/processFileReadAction.contoller?fileId=${LoginOK.getFileId()}' />"
					width="30" height="30" class="d-inline-block align-top" alt=""
					style="border-radius: 50%; border: 2px white solid;">

			</div>
			<ul class="navbar-nav">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
						${LoginOK.getName()} </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="<c:url value='/member/memberCenter' />">會員中心</a>
						<a class="dropdown-item"
							href="<c:url value='/member/logout' />">登出</a>
							
					</div></li>
			</ul>
		</c:if>
		<c:if test="${empty LoginOK}">
		<a class="btn btn-success" href="<c:url value='/member/login' />" role="button">登入</a>
		</c:if>

	</div>
</nav>