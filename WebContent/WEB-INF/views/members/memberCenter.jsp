<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員中心</title>
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
                                    	
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                <input name="name" placeholder="Full Name" type="text" value="${LoginOK.getName()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                <input name="name" placeholder="Full Name" type="text" value="${LoginOK.getAccount()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                <input name="name" placeholder="Full Name" type="text" value="${LoginOK.getTel()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                <input name="name" placeholder="Full Name" type="text" value="${LoginOK.getAddress()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style mb-20">
                                                <input name="name" placeholder="Full Name" type="text" value="${LoginOK.getEmail()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="contact-form-style">
                                                
                                                <button class="submit btn-style" type="submit">修改資料</button>
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
