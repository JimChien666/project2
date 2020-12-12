<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
.btncls {
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
	margin-right: 5px;
}

button.btncls:hover {
	background-color: #000000;
}
</style>
<meta charset="UTF-8">
<title>我的活動</title>
<script>
	window.onload = function() {
		var xhr = new XMLHttpRequest();
		xhr
				.open("GET", "<c:url value='/activitys/getAppliedActivity' />",
						true);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					var list = JSON.parse(xhr.responseText);
					console.log(list);
					var imgUrl = "<c:url value='/assets/img/banner/act1.jpg' />";
					var updateUrl = "<c:url value='/activitys/update' />";
					var content = "";
					for (var i = 0; i < list.length; i++) {
						content += '<div class="col-lg-6 col-md-6" id="show'+list[i].id+'">'
								+ '<div class="blog-wrapper mb-30 gray-bg">'
								+ '<div class="blog-img hover-effect">'
								+ '<a href="#"><img alt="img" src="data:image/jpeg;base64,'+list[i].base64Pic+'"/></a>'
								+ '</div>'
								+ '<div class="blog-content">'
								+ '<div class="blog-meta">'
								+ '<h4>'
								+ '<a href="blog-details.html">'
								+ list[i].name
								+ '</a>'
								+ '</h4>'
								+ '<ul>'
								+ '<li>名稱：'
								+ list[i].name
								+ '</li>'
								+ '<br />'
								+ '<li>報名截止：'
								+ list[i].activityDate
								+ '</li>'
								+ '<br />'
								+ '<li>活動費用：'
								+ list[i].amount
								+ '</li>'
								+ '<br />'
								+ '<li>活動地點：'
								+ list[i].location
								+ '</li>'
								+ '<br />'
								+ '<li>活動內容：'
								+ list[i].content
								+ '</li>'

								+ '</ul>'
								+ '</div>'
								+ '<button class="btncls" onclick="deleteActivityApply('
								+ list[i].id
								+ ')">取消報名</button>'
								+ '</div>'
								+ '</div>' + '</div>' + '</div>'
					}
					if (list.length == 0) {
						content += "<div style='width: 100%; text-align:center;'><I>無參加活動</I></div>";
					}
					document.getElementById("content").innerHTML = content;
				} else {
					alert(xhr.status);
				}
			}

		}
	}
	function deleteActivityApply(activityId) {

		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/activityApply/delete' />" + "/"
				+ activityId, true);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					var status = JSON.parse(xhr.responseText);
					if (status) {
						swal("取消成功");
						document.getElementById("show" + activityId).remove();
					} else {
						swal("取消失敗");
					}
				}
			}
		}

	}
	function AddActivity() {
		window.location.href = "<c:url value='/activitys/add' />";
	}
</script>
<jsp:include page="../fragments/links.jsp" />
</head>
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
			<div class="col-lg-12 col-md-8"">
				<div class="row" style="margin-top: 10px;">
					<div class="col-lg-3 col-md-12">
						<jsp:include page="../members/fragments/myAccountLeftArea.jsp" />
					</div>
					<div class="col-lg-9 col-md-12">
						<h2>活動管理</h2>
						<hr>
						<div class="row" id="content"></div>
					</div>

				</div>
			</div>
		</div>
	</div>


	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>