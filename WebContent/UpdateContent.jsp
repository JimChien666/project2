<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.sactivelim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>請輸入修改會員內容</title>
</head>
<body>
<jsp:useBean id="updcon" class="Members.MemberBean" scope="session"/>
<jsp:include page="/nn/top.jsp" />

<h2>
修改會員
</h2>
<form action=".\MembersServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">

<tr bgcolor="#FFFFE1">
    <td>會員姓名:</td>
       <td><input type="text" name="name" size="38" maxlength="38"></td>
    <td><jsp:getProperty name="updcon" property="name" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>會員性別:</td>
        <td><input type="text" name="sex" size="80" maxlength="500"></td>
    <td><jsp:getProperty name="updcon" property="sex" /></td>
</tr>


<tr bgcolor="#FFFFE1">
    <td>會員連絡電話:</td>
        <td><input type="text" name="tel" size="80" maxlength="80"></td>
    <td><jsp:getProperty name="updcon" property="tel" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>會員信箱:</td>
        <td><input type="text" name="email" size="80" maxlength="80"></td>
    <td><jsp:getProperty name="updcon" property="email" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>會員地址:</td>
        <td><input type="text" name="address" size="80" maxlength="80"></td>
    <td><jsp:getProperty name="updcon" property="address" /></td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>會員收入</td>
        <td><input type="text" name="income" size="80" maxlength="80"></td>
    <td><jsp:getProperty name="updcon" property="income" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>會員帳號:</td>
        <td><input type="text" name="account" size="80" maxlength="80"></td>
    <td><jsp:getProperty name="updcon" property="account" /></td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>會員密碼</td>
        <td><input type="text" name="password" size="80" maxlength="80"></td>
    <td><jsp:getProperty name="updcon" property="password" /></td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>會員類別</td>
        <td><input type="text" name="memberType" size="80" maxlength="80"></td>
    <td><jsp:getProperty name="updcon" property="memberType" /></td>
</tr>
<input type="hidden" value=${idStr} name="id" size="80" maxlength="80">
</table>
<input type="submit" name="submit" value="submitUpdate">
</form>
</body>
</html>