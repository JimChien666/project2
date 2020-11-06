<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入</title>
</head>
<body>
<jsp:include page="../public/top.jsp" />
	<Form action="processLogin.controller" method="POST" name="loginForm">
		<table>
		  <TR>
             <TD>帳號：　</TD>
             <c:if test="${param.account != null}">
             	<TD><input  type="text" name="account" value="${param.account}"/></TD>
             </c:if>
             <c:if test="${param.account == null}">
             	<TD><input  type="text" name="account" value="${requestScope.user}"/></TD>
             </c:if>
             <TD style="color: red;">${errors.AccountEmptyError}</TD>
          </TR>
         <TR>
             <TD>密碼：　</TD>
             <c:if test="${param.password != null}">
             	<TD><input  type="password" name="password" value="${param.password}"/></TD>
             </c:if>
             <c:if test="${param.password == null}">
             	<TD><input  type="password" name="password" value="${requestScope.password}"/></TD>
             </c:if>
             <TD style="color: red;">${errors.PasswordEmptyError}</TD>
         </TR> 
         <tr>
	         <TD width="180" align="right" >
	             <input type="checkbox" name="rememberMe" value="true">
	         </TD><TD>記住密碼</TD>
         </tr>
         <tr>
	         <TD width="180" align="right" >
	             <button type="submit" name="send" value="send">送出</button>
	         </TD>
         </tr>
         <tr>
	         <TD style="color: red;">
	             ${errors.LoginError}
	         </TD>
         </tr>
		</table>
	</Form>
</body>
</html>