 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>產品新增資料</title>
</head>
<body>


<!-- 設定變數funcName的值, top.jsp會使用此變數-->
<c:set var="funcName" value="CHE" scope="session"/>
<!-- 引入共同的頁首 -->
<jsp:include page="/jim/fragment/top.jsp" />

<h2>
產品新增資料
</h2>
<form enctype='multipart/form-data' action="../InsertProduct" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td>產品名稱:</td>
    <td><input type="text" name="name" size="10" maxlength="100"></td>
</tr>
<tr>
    <td>產品價格:</td>
    <td><input type="text" name="price" size="10" maxlength="10"></td>
</tr>
<tr>
    <td>產品圖片:</td>
    <td><input enctype="multipart/form-data" type="file" name="img" size="10" maxlength="10"></td>
</tr>
<tr>
    <td>產品描述:</td>
    <td><input type="text" name="descript" size="10" maxlength="500"></td>
</tr>
<tr>
    <td>產品數量:</td>
    <td><input type="text" name="quantity" size="10" maxlength="500"></td>
</tr>
</table>
<center>
<input type="submit" name="submit" value="送出">
</center>
</form>
</body>
</html>