<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Using jsp:setProperty</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<link REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
</head>

<BODY>
<h2>
產品購物商城
</h2>
<!-- 設定變數funcName的值, top.jsp會使用此變數-->
<c:set var="funcName" value="SHO" scope="session"/>
<!-- 引入共同的頁首 -->
<jsp:include page="../nn/top.jsp" />
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#" >Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar" ">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/jim/InsertProduct.jsp' />">新增商品</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/jim/QueryProduct.jsp' />">查詢商品</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/jim/DeleteProduct.jsp' />">刪除商品</a>
      </li>    
    </ul>
  </div>  
</nav>
<div class="page1">
	<a href="<c:url value='/jim/ProductsPage.jsp' />" class="btn btn-secondary div2">回產品首頁</a>
	<form action="<%=application.getContextPath() %>/ProductUpdate" method="post">
		<label for="" class="span1">產品編號：</label>
		<input type="text" name="productId" value="${product.id}" readonly><br> 
		<label for="" class="span1">會員編號：</label>
		<input type="text" name="memberId" value="${valueObjectAnimal.memberId}" onblur="checkmemberId()" id="memberId" required><br>
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
		<input type="button" name="delete" value="刪除" onclick="confirmDelete()" class="btn btn-danger" />
	</form>
</div>



<div align='center'>
<TABLE  BORDER=1>
	<TR> <TH>產品編號</TH><TH>商品名稱</TH><TH>圖片</TH><TH>產品價格</TH><TH>產品描述</TH></TR>
<c:forEach var="ProductList" items="${ProductList}" >
<TR>

			<TD>${ProductList.id}</TD>
			<TD>${ProductList.name}</TD>
			<TD><img height='200px' width='100px'
			src='${pageContext.request.contextPath}/_00_init/getImage?id=${ProductList.id}&type=PRODUCTS'>
			</TD>
			<TD>${ProductList.price}</TD>
			<TD>${ProductList.descript}</TD>
</TR>	
</c:forEach>
</TABLE>
</div>

<!-- <div id="bpaging">以下為控制第一頁、前一頁、下一頁、最末頁 等超連結 -->
<!-- <table border="1"> -->
<!-- 	<tr align="center"> -->
<%-- 		<td width='80' height='20'><c:if test="${pageNo > 1}"> --%>
<!-- 			<div id="blfirst"><a -->
<%-- 				href="<c:url value='DisplayPageProducts?pageNo=1' />"> <img --%>
<!-- 				border='0' alt='第一頁' height='30' width='30' -->
<!-- 				src='/images/first-icon.png' /> </a></div> -->
<%-- 		</c:if></td> --%>
<%-- 		<td width='80'><c:if test="${pageNo > 1}"> --%>
<!-- 			<div id="blprev"><a -->
<%-- 				href="<c:url value='DisplayPageProducts?pageNo=${pageNo-1}' />"> --%>
<!-- 			<img border='0' alt='前一頁' height='30' width='30' -->
<!-- 				src='../images/prev-icon.png' /></a></div> -->
<%-- 		</c:if></td> --%>
<%-- 		<td width='76'>${pageNo} / ${totalPages}</td> --%>
<%-- 		<td width='80'><c:if test="${pageNo != totalPages}"> --%>
<!-- 			<div id="blnext"><a -->
<%-- 				href="<c:url value='DisplayPageProducts?pageNo=${pageNo+1}' />"> --%>
<!-- 			<img border='0' alt='下一頁' height='30' width='30' -->
<!-- 				src='/jim/images/next-icon.png' /> </a></div> -->
<%-- 		</c:if></td> --%>
<%-- 		<td width='80'><c:if test="${pageNo != totalPages}"> --%>
<!-- 			<div id="bllast"><a -->
<%-- 				href="<c:url value='DisplayPageProducts?pageNo=${totalPages}' />"> --%>
<!-- 			<img border='0' alt='最末頁' height='30' width='30' -->
<!-- 				src='../jim/last-icon.png' /> </a></div> -->
<%-- 		</c:if></td> --%>
<!-- 	</tr> -->
<!-- </table> -->
<!-- </div> -->

             
</BODY>
</HTML>