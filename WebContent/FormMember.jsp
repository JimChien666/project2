<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員主畫面</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.sactivelim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/nn/top.jsp" />
<h2>
會員
</h2>

<h5>新增會員</h5>
<form action="./MembersServlet" method="post">

<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td>會員姓名:</td>
    <td><input type="text" name="name" size="38" maxlength="38"></td>
</tr>

<tr>
    <td>會員性別:</td>
    <td><input type="text" name="sex" size="38"></td>
</tr>

<tr>
    <td>會員連絡電話:</td>
    <td><input type="text" name="tel" maxlength="38"></td>
</tr>

<tr>
    <td>會員信箱:</td>
    <td><input type="text" name="email" maxlength="38"></td>
</tr>

<tr>
    <td>會員地址:</td>
    <td><input type="text" name="address" maxlength="38"></td>
</tr>

<tr>
    <td>會員收入:</td>
    <td><input type="text" name="income" maxlength="38"></td>
</tr>

<tr>
    <td>會員帳號:</td>
    <td><input type="text" name="account" maxlength="38"></td>
</tr>

<tr>
    <td>會員密碼:</td>
    <td><input type="text" name="password" maxlength="38"></td>
</tr>

<tr>
    <td>會員身分:</td>
    <td><input type="text" name="memberType" maxlength="38"></td>
</tr>


</table>

<input type="submit" name="submit" value="submit">

</form>

<h5>刪除會員</h5>

<form action="./MembersServlet" method="post">

<tr>
    <td>會員編號</td>
    <td><input type="text" name="Id" size="38" maxlength="38"></td>
</tr>

<input type="submit" name="submit" value="delete">
</form>


<h5>會員列表&會員修改</h5>


<form action="./MembersServlet" method="post">

<tr>
    <td>會員編號:</td>
    <td><input type="text" name="title2" size="38" maxlength="38"></td>
</tr>
<input type="submit" name="submit" value="selectId">

<input type="submit" name="submit" value="red_list">
</form>


</body>

</html>