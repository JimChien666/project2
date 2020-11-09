<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID:<c:out value="${article.getId()}" />/<c:out
		value="${article.getTitle()}" /></title>
</head>
<body>
	<table>
		<th>標題:<c:out value="${article.getTitle()}"></c:out></th>
				<th>發文時間</th>

		<c:forEach items="${forums}" var="Forums"  varStatus="id">
			<tr>
				<td>${Forums.getContent()}</td>
				<td>${Forums.getCreatedat()}</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>