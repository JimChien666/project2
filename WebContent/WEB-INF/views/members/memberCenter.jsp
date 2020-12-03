<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
						<div class="blog-wrapper mb-30 gray-bg">
							
							<div class="blog-img hover-effect">
								<div style="margin-left: 20%;width: 60%; text-align: center; border-bottom: 1px solid gray">
									<img alt=""
										src="<c:url value='/member/processFileReadAction.contoller?fileId=${LoginOK.getFileId()}' />" style="height: 150px;border-radius: 50%;">
									<h4 style="padding: 20px;">${LoginOK.name}你好</h4>
								</div>
								
							</div>
							<div class="blog-content">
							<div class="shop-widget mt-50">
                                <h4 class="shop-sidebar-title">會員管理</h4>
                                 <div class="shop-list-style mt-20">
                                    <ul>
                                        <li><a href="#">編輯個人資料</a></li>
                                        <li><a href="#">修改密碼</a></li>
                                    </ul>
                                </div>
                            </div>
                            </div>
							
						</div>
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
