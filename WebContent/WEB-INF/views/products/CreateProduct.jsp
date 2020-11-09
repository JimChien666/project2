<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Product</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />" ></script>
<script>
window.onload = function() {
	/* getColors(); */
	getCategories();
	
}

function getCategories(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getCategories' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "商品分類：<select name='category'>";
			content += "<option value='' disabled selected='selected'>請選擇商品分類</option>"
			var categories = JSON.parse(xhr.responseText);
			for(var i=0; i < categories.length; i++){
				categories += 	"<option value='" + categories[i].id + "'>" + categories[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("somedivS");
			divs.innerHTML += content;
		}
	}
}

function getColors(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getColors' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "商品顏色：<select name='color'>";
			content += "<option value='' disabled selected='selected'>請選擇顏色</option>"
			var colors = JSON.parse(xhr.responseText);
			for(var i=0; i < colors.length; i++){
			    content += 	"<option value='" + colors[i].id + "'>" + colors[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("somedivS");
			divs.innerHTML += content;
		}
	}
}


</script>
</head>
<body>
<jsp:include page="../public/top.jsp" />
	<form action="POST"id='somedivS' class="center">
	</form>
</body>
</html>