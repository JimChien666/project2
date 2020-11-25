<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
<jsp:include page="../fragments/headerArea.jsp" />
<div class="breadcrumb-area pt-95 pb-95 bg-img" style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
            <div class="container">
                <div class="breadcrumb-content text-center">
                    <h2>會員中心</h2>
                    <ul>
                        <li><a href="<c:url value='/' />">首頁</a></li>
                        <li class="active">會員中心</li>
                    </ul>
                </div>
            </div>
        </div>
<jsp:include page="./fragments/myAccountHeaderArea.jsp" />


<jsp:include page="../fragments/footerArea.jsp" />
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>