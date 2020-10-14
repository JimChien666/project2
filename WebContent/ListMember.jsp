<%@page import="Members.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.sactivelim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員列表</title>
</head>
<body>
<jsp:include page="/nn/top.jsp" />
<table border="1" width="1600px" align"center">
	<tr>
		<th align="center" bgcolor="lightgreen"><h2>編號</h2></td>
		<th align="center" bgcolor="lightgreen"><h2>姓名</h2></td>
		<th align="center" bgcolor="lightgreen"><h2>性別</h2></td>
		<th align="center" bgcolor="lightgreen"><h2>電話</h2></td>
		<th align="center" bgcolor="lightgreen"><h2>信箱</h2></td>
		<th align="center" bgcolor="lightgreen"><h2>地址</h2></td>
		<th align="center" bgcolor="lightgreen"><h2>收入</h2></td>
		<th align="center" bgcolor="lightgreen"><h2>帳號</h2></td>
	    <th align="center" bgcolor="lightgreen"><h2>身分</h2></td>
	</tr> 
	
	

<c:forEach var="Members" items="${alist}">
	<tr>
		<td aligh="center" width="200px" height="20px">${Members.id}</td>
		<td aligh="center" width="200px" height="20px">${Members.name}</td>
		<td aligh="center" width="200px" height="20px">${Members.sex}</td>
	    <td aligh="center" width="200px" height="20px">${Members.tel}</td>
	    <td aligh="center" width="200px" height="20px">${Members.email}</td>
	    <td aligh="center" width="200px" height="20px">${Members.address}</td>
		<td aligh="center" width="200px" height="20px">${Members.income}</td>
		<td aligh="center" width="200px" height="20px">${Members.account}</td>
		<td aligh="center" width="200px" height="20px">${Members.memberType}</td>
	</tr>

</c:forEach>

	

</table>


</body>
</html>