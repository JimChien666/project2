<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員中心</title>
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
                                <form id="contact-form" action="assets/mail.php" method="post">
                                    <div class="row">
                                    	
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                姓名：<input name="name" placeholder="Full Name" type="text"  disabled="disabled" value="${LoginOK.getName()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                帳號：<input name="name" placeholder="Full Name" type="text"  disabled="disabled" value="${LoginOK.getAccount()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                電話：<input name="name" placeholder="Full Name" type="text" disabled="disabled" value="${LoginOK.getTel()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                地址：<input name="name" placeholder="Full Name" type="text" disabled="disabled" value="${LoginOK.getAddress()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                Email：<input name="name" placeholder="Full Name" type="text" disabled="disabled" value="${LoginOK.getEmail()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style">
                                                
                                                <a class="btncls"
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
