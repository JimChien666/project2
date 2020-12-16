<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
em {
	color: #FF0000;
	font-style: normal;
	font-weight: bold;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
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
							<h4 class="contact-title">編輯個人資料</h4>
							<div class="contact-message">
								<form id="contact-form" action="assets/mail.php" method="post">
									<div class="row">

										<tr class="col-lg-9">
										<tr class="contact-form-style mb-20">
											<td width="120" align="right">姓名:<em>*</em></td>
											<input name="name" placeholder="Full Name" type="text"
												readonly value="${LoginOK.getName()}">
										</tr>
										</tr>
										<tr class="col-lg-9">
											<td width="120" align="right">帳號:<em>*</em></td>
										<tr class="contact-form-style mb-20">
											<input name="name" placeholder="Full Name" type="text"
												readonly value="${LoginOK.getAccount()}">
										</tr>
										</tr>
										<tr class="col-lg-9">
											<td width="120" align="right">電話:<em>*</em></td>
										<tr class="contact-form-style mb-20">
											<input name="name" placeholder="Full Name" type="text"
												readonly value="${LoginOK.getTel()}">
										</tr>
										</tr>
										<tr class="col-lg-9">
											<td width="120" align="right">地址:<em>*</em></td>
										<tr class="contact-form-style mb-20">

											<input name="name" placeholder="Full Name" type="text"
												readonly value="${LoginOK.getAddress()}">
										</tr>
										</tr>
										<tr class="col-lg-9">
											<td width="120" align="right">信箱:<em>*</em></td>
										<tr class="contact-form-style mb-20">
											<input name="name" placeholder="Full Name" type="text"
												readonly value="${LoginOK.getEmail()}">
										</tr>
										</tr>
										<tr class="col-lg-9">
											<div class="contact-form-style">

												<!--                                                 <button class="submit btn-style" type="submit">修改資料</button> -->
												<a class="btn-style1 btn-style-border"
													href="<c:url value='/member/myAccount.controller'/>">修改資料</a>
											</div>
									</div>
							</div>
							</form>
							<p class="form-messege"></p>
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
