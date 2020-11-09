<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="/CreateAnimal.controller" method="post" modelAttribute="animals">
<%-- <form:form action="<c:url value='/CreateAnimal.controller' />" method="post" modelAttribute="animals"> --%>
	<form:input path="animalId"/><br>
	<form:input path="memberId"/><br>
	<form:input path="acceptionId"/><br>
	<form:input path="breedId"/><br>
	<form:input path="gender"/><br>
	<form:input path="coatColor"/><br>
	<form:input path="isAdoptionAvailable"/><br>
	<form:input path="note"/><br>
	<form:input path="createdAt"/><br>
	<form:input path="updatedAt"/><br>
	<form:input path="deletedAt"/><br>
	<button type="submit">Submit</button>
</form:form>
</body>
</html>