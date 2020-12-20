<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊</title>
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
<jsp:include page="../fragments/headerArea.jsp" />
<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>註冊</h2>
				<ul>
					<li><a href="index.html">首頁</a></li>
					<li class="active">註冊</li>
				</ul>
			</div>
		</div>
	</div>


<div class="login-register-area pt-95 pb-100">
		<div class="container">
			<div class="row">
				<div class="col-lg-7 col-md-12 ml-auto mr-auto">
					<div class="login-register-wrapper">
						<div class="login-register-tab-list nav">
                               
                                <a data-toggle="tab" href="#lg2">
                                    <h4> 註冊 </h4>
                                </a>
                            </div>
						<div class="tab-content">
							
							<div id="lg2" class="tab-pane active">
								<div class="login-form-container">
									<div class="login-register-form">
										<form:form action="processRegister.controller" method="POST" modelAttribute="member" enctype="multipart/form-data">
											領養人: <form:radiobutton style="width:20px;height:20px;" path="memberType" value="領養人"/>
          									收容所: <form:radiobutton style="width:20px;height:20px;" path="memberType" value="收容所"/>${errors.memberType}
          									<form:input path="name" placeholder="使用者名稱"/>${errors.name}
          									男: <form:radiobutton style="width:20px;height:20px;" path="sex" value="男"/>
          									女: <form:radiobutton style="width:20px;height:20px;" path="sex" value="女"/>${errors.sex}
          									<form:input path="tel" placeholder="電話"/>${errors.tel}
          									<form:input path="account" placeholder="帳號"/>${errors.account}
											<form:input type="password" path="password" placeholder="密碼"/>${errors.password}
											<input type="password" name="checkPassword" placeholder="密碼確認"/>${errors.checkPassword}
											<form:input path="email" placeholder="Email"/>${errors.email}
											<form:input path="address" placeholder="住址"/>${errors.address}
											<input type="file" name="myFiles" placeholder="會員圖片"/>${errors.file}
											<div class="button-box">
												<form:button value="Send">Submit</form:button>
											</div>
										</form:form>
									</div>
								</div>
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