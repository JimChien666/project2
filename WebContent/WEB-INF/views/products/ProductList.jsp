<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProductList</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />" ></script>
<script>
window.onload = function() {
	getColors();
}


function getProducts(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/product/getProducts' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "商品顏色：<select name='colorId'>";
			content += "<option value='' disabled selected='selected'>請選擇顏色</option>"
			var colors = JSON.parse(xhr.responseText);
			for(var i=0; i < colors.length; i++){
			    content += 	"<option value='" + colors[i].id + "'>" + colors[i].name + "</option>";
			}
			content += "</select>";
			var divs = document.getElementById("somedivS");
			divs.innerHTML += content;
			divs.innerHTML += "<br/>";
		}
	}
}


</script>
</head>
<body>

</body>
</html>