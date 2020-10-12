<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.div2{
	margin:10px 0;
}

.page1 {
	margin: 0 auto;
	width: 450px;
}

.span1 {
	display: inline-block;
	width: 120px;
	text-align: right;
}

.spanHidden{
	margin-left: 125px;
	display:none;
	color:red;
}
</style>
</head>
<body>
	<div class="page1">
		<a href="<%=application.getContextPath()%>/ServletReadAnimal" class="btn btn-secondary div2">回維護首頁</a>
		<form action="<%=application.getContextPath()%>/ServletCreateAnimal" method="post" enctype="multipart/form-data">
			<label for="" class="span1">照片：</label>
			<input type="file" name="animalFile" id="animalFile" multiple required><br>
			<label for="" class="span1">動物編號：</label>
			<input type="text" name="animalId" placeholder="自動產生" id="animalId" onblur="checkanimalId()" required><br>
			<span id="animalIdSpan" class="spanHidden"><br></span>
			<script type="text/javascript">
				function checkanimalId(){
					let animalId = document.getElementById("animalId").value;
					let animalIdLength = animalId.length;
					let animalIdSpan = document.getElementById("animalIdSpan");
					let flag1 = false;
					if (animalId == ""){
            			document.getElementById("animalIdSpan").style.display = "block";
						animalIdSpan.innerHTML = "不可空白";
					}else if (animalIdLength <= 10) {
                		for (let i = 0; i < animalIdLength; i++) {
                    		let ch = animalId.charAt(i);
                    		if ((ch >= "\u0030" && ch <= "\u0039")) {//判斷數字
                      			flag1 = true;
                    		}else{
                        		flag1 = false;
                        		break;
                    		}
                		}
                		if (flag1) {
                			animalIdSpan.innerHTML = "";
                		} else {
                			document.getElementById("animalIdSpan").style.display = "block";
                			animalIdSpan.innerHTML = "只能輸入數字";
                		}
            		} else {
            			document.getElementById("animalIdSpan").style.display = "block";
            			animalIdSpan.innerHTML = "只能輸入10碼";
            		}
				}
			</script>
			<label for="" class="span1">會員編號：</label>
			<input type="text" name="memberId" placeholder="自動抓取會員編號" onblur="checkmemberId()" id="memberId" required><br>
			<span id="memberIdSpan" class="spanHidden"><br></span>
			<script type="text/javascript">
				function checkmemberId(){
					let memberId = document.getElementById("memberId").value;
					let memberIdLength = memberId.length;
					let memberIdSpan = document.getElementById("memberIdSpan");
					let flag1 = false;
					if (memberId == ""){
            			document.getElementById("memberIdSpan").style.display = "block";
						memberIdSpan.innerHTML = "不可空白";
					}else if (memberIdLength <= 10) {
                		for (let i = 0; i < memberIdLength; i++) {
                    		let ch = memberId.charAt(i);
                    		if ((ch >= "\u0030" && ch <= "\u0039")) {//判斷數字
                      			flag1 = true;
                    		}else{
                        		flag1 = false;
                        		break;
                    		}
                		}
                		if (flag1) {
                			memberIdSpan.innerHTML = "";
                		} else {
                			document.getElementById("memberIdSpan").style.display = "block";
                			memberIdSpan.innerHTML = "只能輸入數字";
                		}
            		} else {
            			document.getElementById("memberIdSpan").style.display = "block";
            			memberIdSpan.innerHTML = "只能輸入10碼";
            		}
				}
			</script>
			<label for="" class="span1">收容動物編號：</label>
			<input type="text" name="acceptionId" onblur="checkacceptionId()" id="acceptionId">收容所需填<br>
			<span id="acceptionIdSpan" class="spanHidden"><br></span>
			<script type="text/javascript">
				function checkacceptionId(){
					let acceptionId = document.getElementById("acceptionId").value;
					let acceptionIdLength = acceptionId.length;
					let acceptionIdSpan = document.getElementById("acceptionIdSpan");
					let flag1 = false;
					if (acceptionId == ""){
						acceptionIdSpan.innerHTML = "";
					}else if (acceptionIdLength <= 10) {
                		for (let i = 0; i < acceptionIdLength; i++) {
                    		let ch = acceptionId.charAt(i);
                    		if ((ch >= "\u0030" && ch <= "\u0039") || (ch >= "\u0061" && ch <= "\u007a") || (ch >= "\u0041" && ch <= "\u005a")) {//判斷數字或英文大小寫
                      			flag1 = true;
                    		}else{
                        		flag1 = false;
                        		break;
                    		}
                		}
                		if (flag1) {
                			acceptionIdSpan.innerHTML = "";
                		} else {
                			document.getElementById("acceptionIdSpan").style.display = "block";
                			acceptionIdSpan.innerHTML = "只能輸入英文字母、數字";
                		}
            		} else {
            			document.getElementById("acceptionIdSpan").style.display = "block";
            			acceptionIdSpan.innerHTML = "只能輸入10碼";
            		}
				}
			</script>
			<label for="" class="span1">品種編號：</label>
			<input type="text" name="breedId" onblur="checkbreedId()" id="breedId" required>需查詢填入<br>
			<span id="breedIdSpan" class="spanHidden"><br></span>
			<script type="text/javascript">
				function checkbreedId(){
					let breedId = document.getElementById("breedId").value;
					let breedIdLength = breedId.length;
					let breedIdSpan = document.getElementById("breedIdSpan");
					let flag1 = false;
					if (breedId == ""){
            			document.getElementById("breedIdSpan").style.display = "block";
						breedIdSpan.innerHTML = "不可空白";
					}else if (breedIdLength <= 10) {
                		for (let i = 0; i < breedIdLength; i++) {
                    		let ch = breedId.charAt(i);
                    		if ((ch >= "\u0030" && ch <= "\u0039")) {//判斷數字
                      			flag1 = true;
                    		}else{
                        		flag1 = false;
                        		break;
                    		}
                		}
                		if (flag1) {
                			breedIdSpan.innerHTML = "";
                		} else {
                			document.getElementById("breedIdSpan").style.display = "block";
                			breedIdSpan.innerHTML = "只能輸入數字";
                		}
            		} else {
            			document.getElementById("breedIdSpan").style.display = "block";
            			breedIdSpan.innerHTML = "只能輸入10碼";
            		}
				}
			</script>
			<label for="gender" class="span1">性別：</label>
			<select name="gender" id="">
				<option value="1">公</option>
				<option value="0">母</option>
			</select><br>
			<label for="" class="span1">毛色：</label>
			<input type="text" name="coatColor" onblur="checkcoatColor()" id="coatColor"><br>
			<span id="coatColorSpan" class="spanHidden"><br></span>
			<script type="text/javascript">
				function checkcoatColor(){
					let coatColor = document.getElementById("coatColor").value;
					let coatColorLength = coatColor.length;
					let coatColorSpan = document.getElementById("coatColorSpan");
					let flag1 = false;
					if (coatColor == ""){
						coatColorSpan.innerHTML = "";
					}else {
                		for (let i = 0; i < coatColorLength; i++) {
                    		let ch = coatColor.charAt(i);
                    		if ((ch >= "\u4e00" && ch <= "\u9fa5") || (ch >= "\u0061" && ch <= "\u007a") || (ch >= "\u0041" && ch <= "\u005a")) {//判斷數字或英文大小寫
                      			flag1 = true;
                    		}else{
                        		flag1 = false;
                        		break;
                    		}
                		}
                		if (flag1) {
                			coatColorSpan.innerHTML = "";
                		} else {
                			document.getElementById("coatColorSpan").style.display = "block";
                			coatColorSpan.innerHTML = "只能輸入英文字母、中文";
                		}
            		}
				}
			</script>
			<label for="" class="span1">是否開放領養：</label>
			<select name="isAdoptionAvailable" id="">
				<option value="1">開放</option>
				<option value="0">不開放</option>
			</select><br>
			<label for="" class="span1" style="position: absolute">備註：</label>
			<textarea id="" name="note" rows="5" cols="18" placeholder="可輸入寵物年齡" style="margin-left: 125px"></textarea><br>
			<a href="<%=application.getContextPath()%>/wey/CreateAnimal.jsp" class="btn btn-secondary">重填</a>
			<c:choose>
			<c:when test="">
			</c:when>
			</c:choose>
			<button type="submit" name="submit" class="btn btn-primary">送出</button>
		</form>
	</div>
</body>
</html>