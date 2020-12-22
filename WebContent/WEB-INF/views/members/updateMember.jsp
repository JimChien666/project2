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
.btncls{
	background-color: #7E4C4F; /* Green */
    border: none;
    color: white;
    padding: 10px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 10px;
    transition-duration: 0.3s;
    cursor: pointer;
}
button.btncls:hover{
	background-color: #000000;
}
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
<jsp:include page="../fragments/links.jsp" />
<body>
	<jsp:include page="../fragments/headerArea.jsp" />
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>會員編輯</h2>
				<ul>
					<li><a href="<c:url value='/' />">首頁</a></li>
					<li><a href="<c:url value='/member/myAccount'/>">會員中心</a></li>
					<li class="active">會員編輯</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />


	<div class="container">
		<div class="row flex-row-reverse">
			<div class="col-lg-12 col-md-8">
				<div class="row" style="margin-top: 10px;">
					<div class="col-lg-3 col-md-12">
						<jsp:include page="./fragments/myAccountLeftArea.jsp" />
					</div>
					<div class="col-lg-9 col-md-12">
						<div class="contact-message-wrapper">
							<h4 class="contact-title">會員資料</h4>
							<div class="contact-message">
								<form action="/team6/member/UpdateMember.controller"
									method="POST" enctype="multipart/form-data">
									<div class="row">
										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
												<input name="id" type="hidden" placeholder="請輸入姓名"
													maxlength='100' value="${LoginOK.getId()}" size="20">
											</div>
										</div>
										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
											姓名：
												<input name="name" type="text" placeholder="請輸入姓名"
													maxlength='100' value="${LoginOK.getName()}" size="20">
											</div>
										</div>
										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
											帳號：
												<input name="account" type="text" placeholder="請輸入帳號"
													maxlength='100' value="${LoginOK.getAccount()}" size="20">
											</div>
										</div>

										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
											性別：
												<input name="sex" type="text" placeholder="請輸入性別"
													maxlength='100' value="${LoginOK.getSex()}" size="20">
											</div>
										</div>
										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
											
												<input name="password" type="hidden" placeholder="請輸入密碼"
													maxlength='100' value="${LoginOK.getPassword()}" size="20">
											</div>
										</div>
										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
											電話：
												<input name="tel" type="text" placeholder="請輸入電話"
													maxlength='100' value="${LoginOK.getTel()}" size="20">
											</div>
										</div>
										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
											信箱：
												<input name="email" type="text" placeholder="請輸入信箱"
													maxlength='100' value="${LoginOK.getEmail()}" size="20">
											</div>
										</div>
										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
											地址：
												<input name="address" type="text" placeholder="請輸入地址"
													maxlength='100' value="${LoginOK.getAddress()}" size="20">
											</div>
										</div>
										<div class="col-lg-9">
											<div class="contact-form-style mb-20">
											會員種類：
												<input name="memberType" type="text" placeholder="請輸入會員種類"
													maxlength='100' value="${LoginOK.getMemberType()}"
													size="20">

											</div>
										</div>
										<div class="col-lg-9">
											<div class="contact-form-style">
												<button class="btncls" type="submit" formmethod="post">
													提交
												</button>

												<a class="btncls" href="<c:url value='/member/myAccount.controller'/>">重填</a>
												<a class="btncls" href="<c:url value='/member/myAccount'/>">回前頁</a>
											</div>
										</div>
										</div>
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
			</div>
			</div>


			<jsp:include page="../fragments/footerArea.jsp" />
			<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>
