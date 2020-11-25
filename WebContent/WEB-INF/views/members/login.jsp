<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入</title>
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
	<jsp:include page="../fragments/headerArea.jsp" />
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>登入</h2>
				<ul>
					<li><a href="index.html">首頁</a></li>
					<li class="active">登入</li>
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
							<a class="active" data-toggle="tab" href="#lg1">
								<h4>login</h4>
							</a>
						</div>
						<div class="tab-content">
							<div id="lg1" class="tab-pane active">
								<div class="login-form-container">
									<div class="login-register-form">
										<Form
											action="<c:url value='/member/processLogin.controller' />"
											method="POST" name="loginForm">
											<c:if test="${param.account != null}">
												<input type="text" name="account" value="${param.account}" />
											</c:if>
											<c:if test="${param.account == null}">
												<input type="text" name="account"
													value="${requestScope.user}" />
											</c:if>
											<c:if test="${param.password != null}">
												<input type="password" name="password"
													value="${param.password}" />
											</c:if>
											<c:if test="${param.password == null}">
												<input type="password" name="password"
													value="${requestScope.password}" />
											</c:if>
											<div class="button-box">
												<div class="login-toggle-btn">
													<input type="checkbox" name="rememberMe" value="true" checked="checked"> <label>記住我</label>
													<a href="#">Forgot Password?</a>
												</div>
												<button type="submit" name="send" value="send">
													<span>送出</span>
												</button>
											</div>
										</form>
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