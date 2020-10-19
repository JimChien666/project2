<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新動物</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<style>
.div2{
	margin:10px 0;
}

.img1{
	height:250px;
	width:250px;
	margin-left: 125px;
}

.page1 {
	margin: 0 auto;
	width: 400px;
}

.span1 {
 	display: inline-block;
	width: 120px;
	text-align: right;
}

.span2{
	display:block;
}

.spanHidden{
	margin-left: 125px;
	display:none;
	color:red;
}
</style>
</head>
<body>
<jsp:include page="/nn/top.jsp" />
<div class="page1">
	<a href="ServletReadAnimal" class="btn btn-secondary div2">回維護首頁</a>
	<form action="<%=application.getContextPath() %>/ServletUpdateAnimal" method="post" enctype="multipart/form-data">
	<label for="" class="span1">照片：</label>
	<input type="file" name="animalFile" id="animalFile" multiple style="width:200px">
	<span id="animalFileSpan2" class="span2" style="height: 260px"><img class="card-img-top img1" src="${pageContext.servletContext.contextPath}/ServletRetrieveImage?id=${valueObjectAnimal.animalId}&type=ANIMAL" alt="Animal image"></span>
	<span id="animalFileSpan1" class="spanHidden" style="height: 260px"><img id="preview_animalFile" src="#" style="height:250px;"/><br></span>
		<label for="" class="span1">動物編號：</label>
		<input type="text" name="animalId" value="${valueObjectAnimal.animalId}" readonly><br> 
		<label for="" class="span1">會員編號：</label>
		<input type="text" name="memberId" value="${valueObjectAnimal.memberId}" onblur="checkmemberId()" id="memberId" required><br>
			<span id="memberIdSpan" class="spanHidden"><br></span>
			<script type="text/javascript">
			$("#animalFile").change(function(){
				document.getElementById("animalFileSpan2").style.display = "none";
				document.getElementById("animalFileSpan1").style.display = "block";
				  readURL(this);
				});
				function readURL(input){
				  if(input.files && input.files[0]){
				    var reader = new FileReader();
				    reader.onload = function (e) {
				    	$("#preview_animalFile").attr('src', e.target.result);
				    }
				    reader.readAsDataURL(input.files[0]);
				  }
				}

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
		<input type="text" name="acceptionId" value="${valueObjectAnimal.acceptionId}" onblur="checkacceptionId()" id="acceptionId"><br>
			<span id="acceptionIdSpan" class="spanHidden"><br></span>
			<script type="text/javascript">
				function checkacceptionId(){
					let acceptionId = document.getElementById("acceptionId").value;
					let acceptionIdLength = acceptionId.length;
					let acceptionIdSpan = document.getElementById("acceptionIdSpan");
					let flag1 = false;
					if (acceptionId == ""){
						acceptionIdSpan.innerHTML = "";
					}else if (acceptionIdLength > 0) {
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
            			acceptionIdSpan.innerHTML = "";
            		}
				}
			</script>
		<label for="" class="span1">品種編號：</label> 
		<input type="text" name="breedId" value="${valueObjectAnimal.breedId}" onblur="checkbreedId()" id="breedId" required><br>
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
		<label for="" class="span1">性別：</label>
		<select name="gender" id="">
		<c:choose>
		<c:when test="${valueObjectAnimal.gender == 1}">
			<option value="1" selected>公</option>
			<option value="0">母</option>
		</c:when>
		<c:otherwise>
			<option value="1">公</option>
			<option value="0" selected>母</option>
		</c:otherwise>
		</c:choose>
		</select><br> 
		<label for="" class="span1">毛色：</label> 
		<input type="text" name="coatColor" value="${valueObjectAnimal.coatColor}" onblur="checkcoatColor()" id="coatColor"><br>
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
		<c:choose>
		<c:when test="${valueObjectAnimal.isAdoptionAvailable == 1}">
			<option value="1" selected>開放</option>
			<option value="0">不開放</option>
		</c:when>
		<c:otherwise>
			<option value="1">開放</option>
			<option value="0" selected>不開放</option>
		</c:otherwise>
		</c:choose>
		</select><br> 
		<label for="" class="span1" style="position:absolute">備註：</label>
		<textarea id="" name="note" rows="5" cols="18" style="margin-left:125px">${valueObjectAnimal.note}</textarea><br>
		<a href="ServletPreUpdateAnimal?animalId=${valueObjectAnimal.animalId}" class="btn btn-secondary">回復修改</a>
		<button type="submit" name="update" class="btn btn-primary">修改</button>
	</form>
</div>
</body>
</html>