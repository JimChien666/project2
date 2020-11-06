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
	<Form action="processLogin.controller" method="POST" name="loginForm">
		<table>
		  <TR>
             <TD>帳號：　</TD>
             <TD><input  type="text" name="account" value="${requestScope.user}${param.account}"/></TD>
             <TD style="color: red;">${errors.AccountEmptyError}</TD>
          </TR>
         <TR>
             <TD>密碼：　</TD>
             <TD><input  type="password" name="password" value="${requestScope.password}${param.password}"/></TD>
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