<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="processRegister.controller" method="POST" modelAttribute="member" enctype="multipart/form-data">
	<table>
		<tr>
      		<td>身份:</td>
      		<td>領養人: <form:radiobutton path="memberType" value="領養人"/>
          		收容所: <form:radiobutton path="memberType" value="收容所"/> </td>
  		</tr>
		<tr>
			<td><form:label path="name">名字:</form:label></td>
			<td><form:input path="name"/></td>
		</tr>
		<tr>
      		<td>性別:</td>
      		<td>男: <form:radiobutton path="sex" value="男"/>
          		女: <form:radiobutton path="sex" value="女"/> </td>
  		</tr>
		<tr>
			<td><form:label path="tel">電話:</form:label></td>
			<td><form:input path="tel"/></td>
		</tr>
		<tr>
			<td><form:label path="income">收入:</form:label></td>
			<td><form:input path="income"/></td>
		</tr>
		<tr>
			<td><form:label path="account">帳號:</form:label></td>
			<td><form:input path="account"/></td>
		</tr>
		<tr>
			<td><form:label path="password">密碼:</form:label></td>
			<td><form:input type="password" path="password"/></td>
		</tr>
		<tr>
			<td><label>密碼確認:</label></td>
			<td><input type="password" name="checkPassword"/></td>
		</tr>
		<tr>
			<td><form:label path="email">Email:</form:label></td>
			<td><form:input path="email"/></td>
		</tr>
		<tr>
			<td><form:label path="address">地址:</form:label></td>
			<td><form:input path="address"/></td>
		</tr>
		<tr><td><input type="file" name="myFiles"/><br/></td></tr>
		<tr>
			<td colspan="2"><form:button value="Send">Submit</form:button></td>
		</tr>
		
	</table>

</form:form>
</body>
</html>