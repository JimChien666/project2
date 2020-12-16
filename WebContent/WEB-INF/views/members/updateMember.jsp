<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updatemember</title>
<head>
<style>
#main {
	width: 95%;
	text-align: left;
	margin: 0 auto;
}

#main0 {
	width: 95%;
	text-align: center;
	text-align: center;
	margin: 0 30%;
}

#toolbar {
	font-size: 14pt;
	border-width: 1px 0px 1px 0px;
	border-style: dotted;
	margin: 5px 0px 5px 0px;
	padding: 5px 0px 5px 0px;
	width: 95%;
	margin: 0 auto;
	text-align: left;
}

#post-message {
	color: #333333;
	background-color: #FFFFDD;
	padding: 10px;
	word-wrap: break-word;
	overflow: auto;
}

/
body {
	background-color: #FFFFFF;
	font-size: 16px;
}

a {
	color: #FF6600;
}

textarea, input[type="text"], input[type="title"], input[type="password"]
	{
	width: 100%;
	border: 1px solid #d2d2d2;
	padding: 10px 5px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

select {
	width: 88%;
	padding: 10px;
	-webkit-appearance: listbox;
	min-height: 40px;
	border-radius: 0;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

input[type="checkbox"], input[type="radio"] {
	width: 20px;
	height: 20px;
}

input, textarea {
	border: 1px solid #B2B2B2;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size-adjust: none;
	font-stretch: normal;
	font-style: normal;
	font-variant: normal;
	font-weight: normal;
	line-height: normal;
}

input.button {
	background: transparent url(public/images/fade-butt.png) repeat scroll
		0% 50%;
	border-color: #CCCCCC rgb(153, 153, 153) rgb(153, 153, 153)
		rgb(204, 204, 204);
	border-style: double;
	border-width: 3px;
	color: #333333;
	padding: 0.15em;
}

input.radio {
	border: 0px;
}

input.button:active {
	background: #f4f4f4;
	border: 3px double #ccc;
	border-left-color: #999;
	border-top-color: #999;
}

img#captcha {
	border: 1px solid #888;
	padding: 1px;
}

em {
	color: #FF0000;
	font-style: normal;
	font-weight: bold;
}

#msgbox {
	text-align: center;
	border-style: solid;
	border-width: 1px 2px 2px 1px;
	border-color: #999999;
	width: 40%;
	margin: 0 auto;
}

#msgbox h1 {
	font-size: 14pt;
	text-align: left;
	background-color: #D4FEB9;
	margin: 0px;
	padding: 2px;
}
</style>

</head>
<body>
	<form action="/team6/member/UpdateMember.controller"
						method="POST" 
						enctype="multipart/form-data">
	<div id="toolbar">
		<a href="<c:url value='/member/myAccount' />"class="btn-style-cancel btn-style-border mb-20">回到會員中心</a> 
	</div>
	<div id='main'>
		<table width="100%" align="center" cellpadding="5" cellspacing="5"
			border="0" id="post-sign">
			<tr height="10">
			</tr>
			
			<tr>
				<td width="120" align="right">id:<em>*</em></td>
				<td valign="top"><input name="id" type="text"
					placeholder="請輸入姓名" maxlength='100' value="${LoginOK.getId()}"
					size="20"></td>
			</tr>
			<tr>
				<td width="120" align="right">姓名:<em>*</em></td>
				<td valign="top"><input name="name" type="text"
					placeholder="請輸入姓名" maxlength='100' value="${LoginOK.getName()}"
					size="20"></td>
			</tr>
			<tr>
				<td width="120" align="right">帳號:<em>*</em></td>
				<td valign="top"><input name="account" type="text"
					placeholder="請輸入帳號" maxlength='100' value="${LoginOK.getAccount()}"
					size="20"></td>
			</tr>
			<tr>
				<td width="120" align="right">性別:<em>*</em></td>
				<td valign="top"><input name="sex"  type="text"
					placeholder="請輸入性別" maxlength='100' value="${LoginOK.getSex()}"
					size="20"></td>
			</tr>


			<tr>

				<td width="120" align="right">密碼:<em>*</em></td>
				<td valign="top"><input name="password" type="text"
					placeholder="請輸入密碼" maxlength='100'
					value="${LoginOK.getPassword()}" size="20"></td>
			</tr>


			<tr>

				<td width="120" align="right">電話:<em>*</em></td>
				<td valign="top"><input name="tel" type="text"
					placeholder="請輸入電話" maxlength='100' value="${LoginOK.getTel()}"
					size="20"></td>
			</tr>

			<tr>
				<td width="120" align="right">信箱:<em>*</em></td>
				<td valign="top"><input name="email" type="text"
					placeholder="請輸入信箱" maxlength='100' value="${LoginOK.getEmail()}"
					size="20"></td>
			</tr>
			<tr>
				<td width="120" align="right">地址:<em>*</em></td>
				<td valign="top"><input name="address" type="text"
					placeholder="請輸入地址" maxlength='100' value="${LoginOK.getAddress()}"
					size="20"></td>
			</tr>
			<tr>
				<td width="120" align="right">會員種類:<em>*</em></td>
				<td valign="top"><input name="memberType" type="text"
					placeholder="請輸入會員種類" maxlength='100' value="${LoginOK.getMemberType()}"
					size="20"></td>
			</tr>
			
<!-- 			<tr> -->
<!-- 				<td colspan="2" align="right" height="30"><br> <input -->
<!-- 					type="submit" style="width: 45%" class='button' value="送出" -->
<!-- 					id='submitbtn'>  -->
<!-- 								<input type="reset" style="width: 45%"class='button' value="清除"> -->
					
                    <tr>
                    
                    <td  colspan="2" align="center" height="30"  >
                    <button  type="submit"  formmethod="post">提交</a></button>
<!--                     <button  type="submit"  formmethod="post">重填</a></button> -->
<%--                     <a href="<c:url value='/member/UpdateMember.controller'/>">修改完成</a> --%>
					<a href="<c:url value='/member/myAccount.controller'/>">重填</a>
					</form></td>
			        </tr>
		</table>
		</form>
</body>
</html>
