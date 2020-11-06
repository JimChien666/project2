<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../public/top.jsp" />
<form:form action="processRegister.controller" method="POST" modelAttribute="member" enctype="multipart/form-data">
	<table>
		<tr>
      		<td>身份:</td>
      		<td>領養人: <form:radiobutton path="memberType" value="領養人"/>
          		收容所: <form:radiobutton path="memberType" value="收容所"/> </td>
          	<td style="color:red;">${errors.memberType}</td>
  		</tr>
		<tr>
			<td><form:label path="name">名字:</form:label></td>
			<td><form:input path="name"/></td>
			<td style="color:red;">${errors.name}</td>
		</tr>
		<tr>
      		<td>性別:</td>
      		<td>男: <form:radiobutton path="sex" value="男"/>
          		女: <form:radiobutton path="sex" value="女"/> </td>
          	<td style="color:red;">${errors.sex}</td>
  		</tr>
		<tr>
			<td><form:label path="tel">電話:</form:label></td>
			<td><form:input path="tel"/></td>
			<td style="color:red;">${errors.tel}</td>
		</tr>
		<tr>
			<td><form:label path="account">帳號:</form:label></td>
			<td><form:input path="account"/></td>
			<td style="color:red;">${errors.account}</td>
		</tr>
		<tr>
			<td><form:label path="password">密碼:</form:label></td>
			<td><form:input type="password" path="password"/></td>
			<td style="color:red;">${errors.password}</td>
		</tr>
		<tr>
			<td><label>密碼確認:</label></td>
			<td><input type="password" name="checkPassword"/></td>
			<td style="color:red;">${errors.checkPassword}</td>
		</tr>
		<tr>
			<td><form:label path="email">Email:</form:label></td>
			<td><form:input path="email"/></td>
			<td style="color:red;">${errors.email}</td>
		</tr>
		<tr>
			<td><form:label path="address">地址:</form:label></td>
			<td><form:input path="address"/></td>
			<td style="color:red;">${errors.address}</td>
		</tr>
		<tr>
			<td>會員圖片:</td>
			<td>
				<input type="file" name="myFiles"/><br/>
				<td style="color:red;">${errors.file}</td>
			</td>
		</tr>
		<tr>
			<td colspan="2"><form:button value="Send">Submit</form:button></td>
		</tr>
		<tr>
			<td>${errors.errorAccountDup}</td>
		</tr>
		
	</table>

</form:form>
</body>
</html>