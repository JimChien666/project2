<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../public/top.jsp" />
	<table>
		<form>
		<tr>
			<th colspan="2">會員資訊</th>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><img
				src="${pageContext.servletContext.contextPath}/filuploadAction.contoller?fileId=${LoginOK.getFileId()}"
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

</body>
</html>