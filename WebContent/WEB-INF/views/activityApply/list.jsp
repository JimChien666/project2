<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Activitys</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
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
	<div class="blog-area pt-100 pb-100 clearfix">
		<div class="container">
			<div class="row">
				<c:forEach items="${activitysVoList}" var="activitys">
					<div class="col-lg-6 col-md-6">
						<div class="blog-wrapper mb-30 gray-bg">
							<div class="blog-img hover-effect">
								<a href="blog-details.html"><img alt=""
									src="<c:url value='/assets/img/banner/act1.jpg' />"></a>
							</div>
							<div class="blog-content">
								<div class="blog-meta">
									<h4>
										<a href="blog-details.html">${activitys.name}</a>
									</h4>
									<ul>
										<li>名稱：${activitys.name}</li>
										<br />
										<li>報名截止：${activitys.activityDate}</li>
										<br />
										<li>活動費用：${activitys.amount}</li>
										<br />
										<li>活動地點：${activitys.location}</li>
										<br />
										<li>活動內容：${activitys.content}</li>

									</ul>
								</div>
								<button class="btncls">我要報名</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</div>
	
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>