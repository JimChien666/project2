<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Activitys</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<style type="text/css">
.error {
	color: red;
}

table {
	width: 50%;
	border-collapse: collapse;
	border-spacing: 0px;
}

table td {
	border: 1px solid #565454;
	padding: 20px;
}
</style>
</head>
<body>
<jsp:include page="../public/top.jsp" />
	<h2>Activitys List</h2>
	<table>
		<tr>
			<td><strong>id</strong></td>
			<td><strong>Name</strong></td>
			<td><strong>Topic</strong></td>
			<td><strong>Content</strong></td>
			<td><strong>Activity Date</strong></td>
			<td><strong>Amount</strong></td>
			<td><strong>Location</strong></td>
			<td><strong>Limit Number</strong></td>
			<td><strong>Limit Type</strong></td>
			<td><strong>Limit People</strong></td>
			<td><strong>Activitys Type</strong></td>
			<td><strong>Create Date</strong></td>
			<td><strong>File</strong></td>
		</tr>
		<c:forEach items="${activitysVoList}" var="activitys">
			<tr>
				<td>${activitys.id}</td>
				<td>${activitys.name}</td>
				<td>${activitys.topic}</td>
				<td>${activitys.content}</td>
				<td>${activitys.activityDate}</td>
				<td>${activitys.amount}</td>
				<td>${activitys.location}</td>
				<td>${activitys.limitNum}</td>
				<td>${activitys.limitType}</td>
				<td>${activitys.limitPeople}</td>
				<td>${activitys.activitysType}</td>
				<td>${activitys.createDate}</td>
				<td><img alt="img" src="data:image/jpeg;base64,${activitys.base64Pic}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>