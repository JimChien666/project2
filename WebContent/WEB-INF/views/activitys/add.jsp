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
                            <h4 class="contact-title">活動報名</h4><button class="btn-style" onclick="addInput()">一鍵輸入</button>
                            <div class="contact-message">
                                <form:form action="saveActivitys" method="post" modelAttribute="activitysVo" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-lg-7">
                                        活動名稱：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="name" id="name"/><br /><form:errors path="name" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        活動主題：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="topic" id="topic" /><br /><form:errors path="topic" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        報名截止日期（yyyy/mm/dd hh:mm:ss）：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="activityDate" id="activityDate" /><br /> <form:errors path="activityDate" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        費用：
                                            <div class="contact-form-style mb-20">
                                                <input name="amount" type="text" id="amount" value="${activitysVo.amount}"/><br /><form:errors path="amount" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        活動地點：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="location" id="location" /><br /><form:errors path="location" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        活動圖片：
                                            <div class="contact-form-style mb-20">
                                                <form:input path="pic" type="file" id="file"/><img id="demo"  /><br /><form:errors path="pic" cssClass="error" />
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-7">
                                        活動內容：
                                            <div class="contact-form-style">
                                                <form:input path="content" id="content" /><br /><form:errors path="content" cssClass="error" />
                                                <button class="submit btn-style" type="submit">送出</button>
                                                <button class="submit btn-style"><a href="<c:url value='/activitys/list' />" style="color: white;">返回活動頁面</a></button>
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
	<script>

	$('#file').change(function() {
		  var file = $('#file')[0].files[0];
		  var reader = new FileReader;
		  reader.onload = function(e) {
		    $('#demo').attr('src', e.target.result);
		    $('#demo').attr('style', "width: 420px; height: 300px;");		    
		  };
		  reader.readAsDataURL(file);
		});

	function addInput(){
		document.getElementById("name").value="假日建國花市流浪動物認領養活動";
		document.getElementById("topic").value="養牠 愛牠 別遺棄牠";
		document.getElementById("activityDate").value="2021/12/26 14:20:46";
		document.getElementById("amount").value="0";
		document.getElementById("location").value="台北市大安區建國南路一段";
		document.getElementById("content").value="免費流浪動物認領養‧文宣品發放‧志工招募等等";
	}
</script>
</body>
</html>