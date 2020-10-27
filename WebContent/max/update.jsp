<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<center>
	<body bgcolor="#e3e3e3">
		<h2>修改會員</h2>

		<form method="post" action="<c:url value='/ActionUpdate.do' />">
			<c:forEach var="searchMB" varStatus="status" items="${list}">
<table>
				<div>
                <input type="hidden" value='${searchMB.id}'  name="id" >
              </div>
				<tr>
					<td>使用者密碼:</td>
					<td><input type="text" value='${searchMB.password}' name="password" size="20" /></td>
				</tr>
				<tr>
					<td>使用者姓名:</td>
					<td><input type="text" value='${searchMB.name}' name="name" size="20" /></td>
				</tr>
				<tr>
					<td>電話:</td>
					<td><input type="text" value='${searchMB.tel}' name="tel" size="20" /></td>
				</tr>
				<tr>
					<td>信箱:</td>
					<td><input type="text" value='${searchMB.email}' name="email" size="20" /></td>
				</tr>
				<tr>
					<td>收入:</td>
					<td><input type="text" value='${searchMB.income}' name="income" size="20" /></td>
				</tr>
				<tr>
					<td>地址:</td>
					<td><input type="text" value='${searchMB.address}' name="address" size="20" /></td>
				</tr>
				<tr>
					<td>領養寵物等級:</td>
					<td><input type="text" value='${searchMB.adoptedLevelId}' name="adoptedLevelId" size="20" /></td>
				</tr>
				<tr>
				</table>
			</c:forEach>
		</form>
		<input type="submit" value="確認">
	</body>
</html>