<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../fragments/links.jsp" />
<body>
<jsp:include page="../fragments/headerArea.jsp" />
<div class="breadcrumb-area pt-95 pb-95 bg-img" style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
            <div class="container">
                <div class="breadcrumb-content text-center">
                    <h2>會員編輯</h2>
                    <ul>
                        <li><a href="<c:url value='/' />">首頁</a></li>
                        <li><a href="<c:url value='/member/myAccount'/>">會員中心</a></li>
                        <li class="active">會員編輯</li>
                    </ul>
                </div>
            </div>
        </div>
<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />
	<table>
		<form>
		<tr>
			<th colspan="2">會員資訊</th>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><img
				src="<c:url value='/member/processFileReadAction.contoller?fileId=${LoginOK.getFileId()}' />"
				width="100" height="100" class="d-inline-block align-top" alt=""
				style="border: 2px white solid;"></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td>${LoginOK.getName()}</td>
		</tr>
		<tr>
			<td>身份</td>
			<td>${LoginOK.getMemberType()}</td>
		</tr>
		<tr>
			<td>性別</td>
			<td>${LoginOK.getSex()}</td>
		</tr>
		<tr>
			<td>電話</td>
			<td>${LoginOK.getTel()}</td>
		</tr>
		<tr>
			<td>地址</td>
			<td>${LoginOK.getAddress()}</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${LoginOK.getEmail()}</td>
		</tr>
		<tr>
			<td>帳號</td>
			<td>${LoginOK.getAccount()}</td>
		</tr>
		</form>
	</table>

<jsp:include page="../fragments/footerArea.jsp" />
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>