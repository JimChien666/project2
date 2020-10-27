<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
<title>註冊頁面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<script language="javascript">
	function isValid(form) {
		if (form.name.value == "") {
			alert("使用者名稱不能為空");
			return false;
		}
		if (form.password.value != form.password2.value) {
			alert("兩次輸入的密碼不同！");
			return false;
		} else if (form.password.value == "") {
			alert("使用者密碼不能為空！");
			return false;
		} else
			return true;
	}
</script>
</head>
<body>
	<center>
		<body bgcolor="#e3e3e3">
			<h2>會員註冊</h2>
			<form method="post" action="<c:url value='/ActionInsert.do' />">

				<table>
					<label class="fontSize">身份：</label>
					<input type="radio" name="memberType" value="領養人" checked="true">
					領養人
					<input type="radio" name="memberType" value="收容所"> 收容所
					<br />
					<tr>
						<td>使用者帳號:</td>
						<td><input type="text" name="account" size="20" /></td>
					</tr>
					<tr>
						<td>使用者密碼:</td>
						<td><input type="text" name="password" size="20" /></td>
					</tr>
				<!--  <tr>
						<td>再次確認密碼:</td>
						<td><input type="text" name="password2" size="20" /></td>
					<tr>-->
					<tr>
						<td>使用者名稱:</td>
						<td><input type="text" name="name" size="20" /></td>
					</tr>

					<tr>
						<td>性別:</td>
						<td><input type="radio" name="sex" value="男" />男</td>
						<td><input type="radio" name="sex" value="女" />女</td>
					</tr>
					<tr>
						<td>電話:</td>
						<td><input type="text" name="tel" size="20" /></td>
					</tr>
					<tr>
						<td>信箱:</td>
						<td><input type="text" name="email" size="20" /></td>
					</tr>
					<tr>
						<td>收入:</td>
						<td><input type="text" name="income" size="20" /></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input type="text" name="address" size="20" /></td>
					</tr>
					<tr>
						<td>領養寵物等級:</td>
						<td><input type="text" name="adoptedlevelId" size="20" /></td>
					</tr>
					<tr>
			</form>
				</table>
						<td><input type="submit" value="註冊" />
						<td><input type="reset" value="重置" />
	</center>
	<br>
</body>
</html>