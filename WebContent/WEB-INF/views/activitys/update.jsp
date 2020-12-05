<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring5 MVC Hibernate Demo</title>
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
	<h1>Input Form</h1>
	<form:form action="${pageContext.request.contextPath}/activitys/updateActivitys" method="post" modelAttribute="activitysVo">
		<table>
			<tr>
				<td>ID</td>
				<td><form:input path="id" readonly="true" /> <br />
				<form:errors path="id" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><form:input path="name" /><br />
				<form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Topic</td>
				<td><form:input path="topic" /><br />
				<form:errors path="topic" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><form:input path="content" /><br />
				<form:errors path="content" cssClass="error" /></td>
			</tr>
			<tr>
				<td>ActivityDate</td>
				<td><form:input path="activityDate" /><br /> <form:errors
						path="activityDate" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><form:input path="amount" /><br />
				<form:errors path="amount" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Location</td>
				<td><form:input path="location" /><br />
				<form:errors path="location" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Limit Number</td>
				<td><form:input path="limitNum" /><br />
				<form:errors path="limitNum" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Limit Type</td>
				<td><form:input path="limitType" /><br />
				<form:errors path="limitType" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Limit People</td>
				<td><form:input path="limitPeople" /><br />
				<form:errors path="limitPeople" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Activitys Type</td>
				<td><form:input path="activitysType" /><br />
				<form:errors path="activitysType" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">Submit</button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>