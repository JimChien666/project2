<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理者查詢動物</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>
<style>
.card2 {
	width: 250px;
	float: left;
	margin: 10px;
	padding: 5px;
}

.card2 img {
	height: 250px;
}

.div1 {
	margin: 10px 0 0 10px;
}

.div2 {
	height: 300px;
	overflow: auto;
	margin: 5px 0 5px 0;
}

.note1 {
	height: auto;
	word-wrap: break-word;
}

.page1 {
	margin: 0 auto;
	width: 87%;
}

.span1 {
	display: inline-block;
}
</style>
</head>
<body>
	<jsp:include page="/nn/top.jsp" />
	<div class="page1">
		<div class="div1">
			<a href="<%=application.getContextPath()%>/wey/animal/CreateAnimal.jsp" class="btn btn-primary">新增</a>
<!-- 			<div class="input-group mb-3 search1"> -->
<!-- 			  <input type="text" class="form-control" placeholder="Search"> -->
<!-- 			  <div class="input-group-append"> -->
<!-- 			    <button class="btn btn-success" type="submit">Go</button> -->
<!-- 			  </div> -->
<!-- 			</div> -->
		</div>
		<!-- card來源https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_card_image&stacked=h -->
		<c:forEach var="AnimalsList" items="${AnimalsList}">
			<div class="card card2">
				<img class="card-img-top" src="${pageContext.servletContext.contextPath}/ServletRetrieveImage?id=${AnimalsList.animalId}&type=ANIMAL" alt="Animal image">
				<form action="">
					<div class="card-body div2">
						<ul>
							<li><span class="span1">動物編號：&nbsp;</span><span class="span1">${AnimalsList.animalId}</span><br></li>
							<li><span class="span1">會員編號：&nbsp;</span><span class="span1">${AnimalsList.memberId}</span><br></li>
							<li><span class="span1">收容動物編號：&nbsp;</span><span class="span1">${AnimalsList.acceptionId}</span><br></li>
							<li><span class="span1">品種編號：&nbsp;</span><span class="span1">${AnimalsList.breedId}</span><br></li>
							<li><c:choose>
									<c:when test="${AnimalsList.gender == 1}">
										<span class="span1">性別：&nbsp;</span>公<br>
									</c:when>
									<c:otherwise>
										<span class="span1">性別：&nbsp;</span>母<br>
									</c:otherwise>
								</c:choose></li>
							<li><span class="span1">毛色：&nbsp;</span><span class="span1">${AnimalsList.coatColor}</span><br></li>
							<li><c:choose>
									<c:when test="${AnimalsList.isAdoptionAvailable == 1}">
										<span class="span1">是否開放領養：&nbsp;</span>是<br>
									</c:when>
									<c:otherwise>
										<span class="span1">是否開放領養：&nbsp;</span>否<br>
									</c:otherwise>
								</c:choose></li>
							<li><span class="span1">備註：&nbsp;</span><span class="span1 note1">${AnimalsList.note}</span><br></li>
						</ul>
					</div>
					<a href="ServletPreUpdateAnimal?animalId=${AnimalsList.animalId}" class="btn btn-secondary" style="width: 49%;">維護</a>
					<script type="text/javascript">
				function confirmDelete(animalId) {
					if(confirm("確定刪除此筆動物資料(動物編號:"+animalId+")?")){
						document.forms[0].action="ServletDeleteAnimal?animalId="+animalId;
						document.forms[0].method="POST";
						document.forms[0].submit();//submit is not a function可能是因為有按鈕的name也叫submit
					}
				}

				function deleteAnimal(animalId){
					swal({
					    title: "確定刪除動物編號"+animalId+"這筆資料?",
					    icon: "warning",
					    buttons: {Btn: false,
					        cancel: {text: "取消",visible: true},
					        danger: {text: "刪除",visible: true}
					    },
					    dangerMode: true
					}).then((value) => {
						switch(value){
							case "danger":
								document.forms[0].action="ServletDeleteAnimal?animalId="+animalId;
								document.forms[0].method="POST";
								document.forms[0].submit();//submit is not a function可能是因為有按鈕的name也叫submit
								break;
							  }});
					}
				</script>
					<input type="button" value="刪除"
						onclick="deleteAnimal(${AnimalsList.animalId})"
						class="btn btn-danger" style="width: 49%;">
				</form>
			</div>
		</c:forEach>
	</div>
</body>
</html>