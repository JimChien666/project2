<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->





<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>


<html>
<head>


<style type="text/css">
.fixed {
	position: fixed;
	bottom: 3%;
	right: 2%;
}

.fixed0 {
	position: fixed;
	bottom: 15%;
	right: 2%;
}

table, th, td {
	border: 1px solid black;
}
</style>
<meta charset="UTF-8">
<title>ID:<c:out value="${article.getId()}" />/<c:out
		value="${article.getTitle()}" /></title>
</head>
<body>
	<jsp:include page="../public/top.jsp" />
	<table>
		<th colspan="3">標題:<c:out value="${article.getTitle()}"></c:out></th>

		<tr>
			<td>發文時間</td>
			<td colspan="3">作者:<c:out
					value="${article.getMember().getName()}"></c:out></td>
		</tr>


		<c:forEach items="${forums}" var="Forums" varStatus="id">
			<c:if test="${id.isLast()}">
				<tr>
					<td>${Forums.getMember().getName()}</td>
					<td>${Forums.getContent()}</td>
					<td>${Forums.getCreatedat()}</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<br>

	<table>
		<th>標題:<c:out value="${article.getTitle()}"></c:out></th>
		<th>發文時間</th>
		<th>作者:<c:out value="${article.getMember().getName()}"></c:out></th>

		<c:forEach items="${forums}" var="Forums" varStatus="id">
			<c:if test="${!id.isLast()}">
				<tr>
					<td>${Forums.getContent()}</td>
					<td>${Forums.getCreatedat()}</td>
					<td>${Forums.getMember().getName()}</td>
				</tr>
				<c:forEach items="${comments}" var="Comments" varStatus="id">
					<tr>
						<td> </td>
					</tr>
				</c:forEach>
					<tr>
						<c:if test="${empty LoginOK}">
							<td>回覆本討論串:<input disabled placeholder="登入後留言" type="text" name="comments" id="${Forums.getId()}" onmouseover="gotToLogin()"></td>
						</c:if>
						<c:if test="${!empty LoginOK}">
							<td>回覆本討論串:<input type="text" name="comments" id="${Forums.getId()}"></td>
						</c:if>
					</tr>
					<tr>
						<td><div id='result0'></div></td>
					</tr>

			</c:if>
		</c:forEach>
	</table>
	<a href="<c:url value='backArticle' />" class="fixed">
		<button type="button" class="btn btn-success">回討論版</button>
	</a>
	<a href="<c:url value='replyArticle?articleId=${article.getId()}' />"
		class="fixed0">
		<button type="button" class="btn btn-success">回覆文章</button>
	</a>
	
	
	
<script>
// function gotToLogin(){
// 	alert("佛祖");
// 	window.location.href = "<c:url value='/member/processLogin.controller'/>"
// }


$("input").keypress(function (e) {
		console.log(this);
	   if (e.keyCode == 13) {
			var id = this.id;
// 			var name = obj.name;
			var comment = this.value;
			
			console.log(id);
			console.log(name);
			console.log(comment);
// 	      alert('Enter key pressed!');

		$.ajax({
		  url: "saveComments",
		  data: {
			  id:id,
			  comment:comment
			  },
// 		  success: success,
// 		  dataType: dataType
		});

	    }
	});	

</script>
</body>
</html>