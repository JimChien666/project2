<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



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
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/nn/controler/ShowIndexServlet' />">寵物旅遊</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/ServletReadAnimal' />">寵物領養</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/DemoHibernateServletAction1' />">討論區</a></li>
			<c:if test="${empty LoginOK}">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/_01_register/register.jsp' />" tabindex="-1"
					aria-disabled="true">註冊會員</a></li>
			</c:if>

		</ul>

		<c:if test="${!empty LoginOK}">
			<div class="navbar-brand">
				<img
					src="${pageContext.servletContext.contextPath}/nn/service/RetrieveImageServlet?id=${LoginOK.getFileId()}"
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
						<a class="dropdown-item" href="<c:url value='/FormMember.jsp' />">會員中心</a>
						<a class="dropdown-item"
							href="<c:url value='/_02_login/logout.jsp' />">登出</a>
					</div></li>
			</ul>
		</c:if>
		<c:if test="${empty LoginOK}">
		<a class="btn btn-success" href="<c:url value='/_02_login/login.jsp' />" role="button">登入</a>
		</c:if>

	</div>
</nav>