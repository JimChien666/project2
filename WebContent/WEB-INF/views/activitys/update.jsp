<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Activitys</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<style type="text/css">
.error {
	color: red;
}
table {
	width: 50%;
	border-collapse: collapse;
	border-spacing: 0px;
}
table td {
	border: 1px solid #565454;
	padding: 20px;
}
</style>
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
<jsp:include page="../fragments/headerArea.jsp" />
<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>活動</h2>
				<ul>
					<li><a href="<c:url value='/' />">首頁</a></li>
					<li class="active">活動</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="contact-area pt-100 pb-100">
            <div class="container">
                
                <div class="row">
                    <div class="col-12">
                        <div class="contact-message-wrapper">
                            <h4 class="contact-title">活動報名</h4>
                            <div class="contact-message">
                                <form:form action="${pageContext.request.contextPath}/activitys/updateActivitys" method="post" modelAttribute="activitysVo" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-lg-7">
                                        <form:input type="hidden" path="id" readonly="true" />
                                        活動名稱：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="name" /><br /><form:errors path="name" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        活動主題：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="topic" /><br /><form:errors path="topic" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        報名截止日期（yyyy/mm/dd hh:mm:ss）：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="activityDate" /><br /> <form:errors path="activityDate" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        費用：
                                            <div class="contact-form-style mb-20">
                                                <input name="amount" type="text" value="${activitysVo.amount}"/><br /><form:errors path="amount" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        活動地點：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="location" /><br /><form:errors path="location" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        活動圖片：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="pic" type="file" /><img style="width: 420px; height: 300px;" alt="img" src="data:image/jpeg;base64,${activitysVo.base64Pic}"/><br /><form:errors path="pic" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        活動內容：
                                            <div class="contact-form-style">
                                                <form:input path="content" /><br /><form:errors path="content" cssClass="error" />
                                                <button class="submit btn-style" type="submit">送出</button>
                                                <button class="submit btn-style" type="submit"><a href="<c:url value='/activitys/list' />" style="color: white;">返回活動頁面</a></button>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
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