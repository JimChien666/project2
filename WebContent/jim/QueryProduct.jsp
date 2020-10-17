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
<title>查詢產品資料</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
<!-- 設定變數funcName的值, top.jsp會使用此變數-->
<c:set var="funcName" value="ORD" scope="session"/>
<!-- 引入共同的頁首 -->
<jsp:include page="../nn/top.jsp" />

<a class="dropdown-item" href="<c:url value='/jim/InsertProduct.jsp' />">新增商品</a>
            <a class="dropdown-item" href="<c:url value='/jim/QueryProduct.jsp' />">查詢商品</a>
            <a class="dropdown-item" href="<c:url value='/jim/DeleteProduct.jsp' />">刪除商品</a>
<h2>
查詢產品資料${param.noResult}
</h2>
<form action="../QueryProduct" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">

	<tr>
	    <td>產品名稱:</td>
	    <td><input type="text" name="name" size="10" maxlength="100"></td>
	</tr>


</table>
<center>
<input type="submit" name="submit" value="送出">
</center>
</form>
</body>
</html>