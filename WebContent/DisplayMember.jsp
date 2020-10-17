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
<title>新增會員內容確認</title>
</head>
<body>
<jsp:useBean id="rel_Member" class="Members.MemberBean" scope="session"/>
<jsp:include page="/nn/top.jsp" />
<h2>
新增會員內容如下請確認
</h2>
<form action=".\MembersServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">

<tr bgcolor="#FFFFE1">
    <td>會員姓名:</td>
   
    <td><jsp:getProperty name="rel_Member" property="name" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>會員性別:</td>
    <td><jsp:getProperty name="rel_Member" property="sex" /></td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>會員連絡電話:</td>
    <td><jsp:getProperty name="rel_Member" property="tel" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>會員信箱</td>
    <td><jsp:getProperty name="rel_Member" property="email" /></td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>會員地址:</td>
    <td><jsp:getProperty name="rel_Member" property="address" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>會員收入:</td>
    <td><jsp:getProperty name="rel_Member" property="income" /></td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>會員帳號:</td>
    <td><jsp:getProperty name="rel_Member" property="account" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>會員密碼:</td>
    <td><jsp:getProperty name="rel_Member" property="password" /></td>
</tr>


<tr bgcolor="#F2F4FB">
    <td>會員類別:</td>
    <td><jsp:getProperty name="rel_Member" property="memberType" /></td>
</tr>
</table>
<input type="submit" name="submit" value="confirm">
</form>
</body>
</html>