<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
request.setAttribute("path", basePath);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員列表</title>
</head>
<body>
	<jsp:include page="/nn/top.jsp" />
	<table border="1" width="1800px"align"center">
		<tr>
			<th align="center" bgcolor="lightgreen"><h2>編號</h2>
			</td>
			<th align="center" bgcolor="lightgreen"><h2>姓名</h2>
			</td>
			<th align="center" bgcolor="lightgreen"><h2>性別</h2>
			</td>
			<th align="center" bgcolor="lightgreen"><h2>電話</h2>
			</td>
			<th align="center" bgcolor="lightgreen"><h2>信箱</h2>
			</td>
			<th align="center" bgcolor="lightgreen"><h2>地址</h2>
			</td>
			<th align="center" bgcolor="lightgreen"><h2>收入</h2>
			</td>
			<th align="center" bgcolor="lightgreen"><h2>帳號</h2>
			</td>
			<th align="center" bgcolor="lightgreen"><h2>身分</h2>
			</td>
		</tr>



		<c:forEach var="searchMB" varStatus="status" items="${beanList}">
			<tr>
				<td aligh="center" width="200px" height="20px">${searchMB.id}</td>
				<td aligh="center" width="200px" height="20px">${searchMB.name}</td>
				<td aligh="center" width="200px" height="20px">${searchMB.sex}</td>
				<td aligh="center" width="200px" height="20px">${searchMB.tel}</td>
				<td aligh="center" width="200px" height="20px">${searchMB.email}</td>
				<td aligh="center" width="200px" height="20px">${searchMB.address}</td>
				<td aligh="center" width="200px" height="20px">${searchMB.income}</td>
				<td aligh="center" width="200px" height="20px">${searchMB.account}</td>
				<td aligh="center" width="200px" height="20px">${searchMB.memberType}</td>
			</tr>
			<h5>會員列表&會員修改</h5>


			<form action="./MembersServlet" method="post">

				<tr>
					<td>會員編號:</td>
					<td><input type="text" name="title2" size="38" maxlength="38"></td>
				</tr>
				<input type="submit" name="submit" value="selectId"> <input
					type="submit" name="submit" value="red_list">
			</form>
			<td style="justify-content: space-between" align="center"><a
				href="<c:url value='/ActionUpdate.do?id=${searchMB.id}' />"
				class="btn btn-info"><i class="fas fa-edit"></i></a> <a
				href="<c:url value='/ActionDelete.do?id=${searchMB.id}' />"
				class="btn btn-danger"><i class="fas fa-trash"></i></a></td>
		</c:forEach>



	</table>


</body>
</html>


