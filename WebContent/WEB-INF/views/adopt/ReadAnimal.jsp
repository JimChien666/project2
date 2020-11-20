<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理者查詢動物</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
<!-- sweetalert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<div>
		<jsp:include page="../public/top.jsp" />
	</div>
	<div class="page1">
		<div class="margin10px">
			<a href="<c:url value='/preCreateAnimal.controller'/>"
				class="btn btn-primary">新增</a>
			<%-- <%=application.getContextPath()%> --%>
			<!-- 			搜尋框框 -->
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
				<div class="square250px">
					<img class="cardImg marginAuto"
						src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${AnimalsList.animalId}"
						alt="Animal image">
				</div>
				<div class="card-body form1">
					<ul>
						<li><div class="div1">動物編號：&nbsp;</div>
							<div class="div1">${AnimalsList.animalId}</div> <br></li>
						<li><div class="div1">會員編號：&nbsp;</div>
							<div class="div1">${AnimalsList.member.address}</div> <br></li>
						<li><div class="div1">收容動物編號：&nbsp;</div>
							<div class="div1">${AnimalsList.acceptionId}</div> <br></li>
						<li><div class="div1">品種：&nbsp;</div>
<%-- 							<div class="div1">${AnimalsList.breedId}</div>要改從breeds顯示 --%>
							 <br></li>
						<li><c:choose>
								<c:when test="${AnimalsList.gender == 1}">
									<div class="div1">性別：&nbsp;</div>公<br>
								</c:when>
								<c:otherwise>
									<div class="div1">性別：&nbsp;</div>母<br>
								</c:otherwise>
							</c:choose></li>
						<li><div class="div1">毛色：&nbsp;</div>
							<div class="div1">${AnimalsList.coatColor}</div> <br></li>
						<li><c:choose>
								<c:when test="${AnimalsList.isAdoptionAvailable == 1}">
									<div class="div1">是否開放領養：&nbsp;</div>是<br>
								</c:when>
								<c:otherwise>
									<div class="div1">是否開放領養：&nbsp;</div>否<br>
								</c:otherwise>
							</c:choose></li>
						<li><div class="div1">備註：&nbsp;</div>
							<div class="div1 note1">${AnimalsList.note}</div> <br></li>
					</ul>
				</div>
				<div>
					<a
						href="<c:url value='/preUpdateAnimal.controller'/>?animalId=${AnimalsList.animalId}"
						class="btn btn-secondary" style="width: 49%;">維護</a> <input
						type="button" value="刪除"
						onclick="deleteAnimal(${AnimalsList.animalId})"
						class="btn btn-danger" style="width: 49%;">
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>