<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.span1 {
 	display: inline-block;
	width: 120px;
	text-align: right;
/*  	background-color:yellow;  */
}
</style>
</head>
<body>
	<form action="" method="post">
		<label for="" class="span1">動物編號：</label> 
		<input type="text" name="animalId"><br> 
		<label for="" class="span1">會員編號：</label>
		<input type="text" name="memberId"><br> 
		<label for="" class="span1">收容動物編號：</label> 
		<input type="text" name="acceptionId"><br>
		<label for="" class="span1">品種編號：</label> 
		<input type="text" name="breedId"><br> 
		<label for="" class="span1">性別：</label>
		<select name="gender" id="">
			<option value="">公</option>
			<option value="">母</option>
		</select><br> 
		<label for="" class="span1">毛色：</label> 
		<input type="text" name="coatColor"><br> 
		<label for="" class="span1">是否開放領養：</label>
		<select name="isAdoptionAvailable" id="">
			<option value="">開放</option>
			<option value="">不開放</option>
		</select><br> 
		<label for="" class="span1" style="position:absolute">備註：</label>
<!-- 		 style="height:100px" -->
		<textarea id="" name="note" rows="10" cols="18" placeholder="可輸入寵物年齡" style="margin-left:125px"></textarea><br>
		<button type="reset">重填</button>
		<button type="submit">送出</button>
	</form>
</body>
</html>