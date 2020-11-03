<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="addMembers" method="POST" modelAttribute="members" enctype="multipart/form-data">
	<table>
		<tr>
			<td><form:label path="memberName">MemberName:</form:label></td>
			<td><form:input path="memberName"/></td>
		</tr>
		<tr>
			<td><form:label path="gender">MemberGender:</form:label></td>
			<td><form:input path="gender"/></td>
		</tr>
		<tr>
			<td><form:label path="age">MemberAge:</form:label></td>
			<td><form:input path="age"/></td>
		</tr>
		<tr><td><input type="file" name="myFiles"/><br/></td></tr>
		<tr>
			<td colspan="2"><form:button value="Send">Submit</form:button></td>
		</tr>
		
	</table>

</form:form>
</body>
</html>