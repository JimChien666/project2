<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${source == 'AdoptionRequest'}">
		<title>領養申請列表</title>
	</c:when>
	<c:when test="${source == 'MyAdoptionProgress'}">
		<title>我的領養進度</title>
	</c:when>
</c:choose>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<style>
td {
	border: 1px black solid;
	text-align: center;
	width: 250px;
}
</style>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<jsp:include page="../fragments/links.jsp" />

<!-- 轉頁載入動畫1 -->
<link rel="stylesheet"
	href="<c:url value='/css/loadingAnimation.css' />">
</head>
<body>
	<div id="loader"></div>
	<div style="display: none;" id="myDiv" class="animate-bottom">
		<!-- 轉頁載入動畫1 -->

		<div>
			<jsp:include page="../fragments/headerArea.jsp" />
		</div>
		<!-- 	麵包屑 -->
		<div class="breadcrumb-area pt-95 pb-95 bg-img"
			style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
			<div class="container">
				<div class="breadcrumb-content text-center">
					<c:choose>
						<c:when test="${source == 'AdoptionRequest'}">
							<h2>領養申請列表</h2>
						</c:when>
						<c:when test="${source == 'MyAdoptionProgress'}">
							<h2>我的領養進度</h2>
						</c:when>
					</c:choose>
					<ul>
						<li><a href="<c:url value='/'/>">首頁</a></li>
						<li><a href="<c:url value='/member/myAccount' />">會員中心</a></li>
						<c:choose>
							<c:when test="${source == 'AdoptionRequest'}">
								<li class="active">領養申請列表</li>
							</c:when>
							<c:when test="${source == 'MyAdoptionProgress'}">
								<li class="active">我的領養進度</li>
							</c:when>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>


		<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />

		<!-- 	==================================================================================== -->

		<table class="mt-50 mb-50 font22 wid1000">
			<thead>
				<tr>
					<td>寵物圖片</td>
					<td>申請時間</td>
					<td>申請資料</td>
					<td>審核狀態</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="AdoptionRequestList" items="${AdoptionRequestList}">
					<tr>
						<td><div class="square250px">
								<img class="cardImg marginAuto" alt=""
									src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${AdoptionRequestList.animal.animalId}">
							</div></td>
						<td><fmt:formatDate value="${AdoptionRequestList.applyTime}"
								pattern="yyyy/MM/dd HH:mm:ss" /></td>
						<td><div>
								<a class="btn-style1" href="<c:url value='/'/>">查看申請資料</a>
							</div></td>
						<td><c:choose>
								<c:when test="${AdoptionRequestList.reviewStatus == 0}">
									已於<fmt:formatDate
										value="${AdoptionRequestList.applyRejectedAt}"
										pattern="yyyy/MM/dd HH:mm:ss" />
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
								退回申請
								<!-- 退回原因 -->
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
								被退回申請
								<!-- 退回原因 -->
										</c:when>
									</c:choose>
								</c:when>
								<c:when test="${AdoptionRequestList.reviewStatus == 1}">
								待核准申請
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
											<div class="mt-20 btn-style1 btn-style-border"
												data-toggle="modal" data-target="#reviewStatus2">核准申請
											</div>
											<div class="mt-10 btn-style-cancel btn-style-border"
												data-toggle="modal" data-target="#reviewStatus0">退回申請
											</div>
											<!-- 核准申請 -->
											<div class="modal fade" id="reviewStatus2" tabindex="-1"
												role="dialog" aria-labelledby="exampleModalLabel"
												aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">核准申請</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
															<form>
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">回覆給申請者的話：</label>
																	<textarea class="form-control" id="message-text"
																		rows="5" cols="80"></textarea>
																	<!-- 																	textarea可加預設對話 -->
																</div>
															</form>
														</div>
														<div class="modal-footer">
															<button type="button"
																class="btn-style-cancel btn-style-border"
																data-dismiss="modal">取消</button>
															<a class="btn-style1 btn-style-border"
																href="<c:url value='/MemberCenter/adoptionRequestList.controller.2?animalId=${AdoptionRequestList.animal.animalId}&memberId=${AdoptionRequestList.member.id}'/>">核准</a>
														</div>
													</div>
												</div>
											</div>
											<!-- 退回申請 -->
											<div class="modal fade" id="reviewStatus0" tabindex="-1"
												role="dialog" aria-labelledby="exampleModalLabel"
												aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">退回申請</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
															<form>
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">回覆給申請者的話：</label>
																	<textarea class="form-control" id="message-text"
																		rows="5" cols="80"></textarea>
																	<!-- 																	textarea可加預設對話 -->
																</div>
															</form>
														</div>
														<div class="modal-footer">
															<button type="button"
																class="btn-style-cancel btn-style-border"
																data-dismiss="modal">取消</button>
															<a class="btn-style1 btn-style-border"
																href="<c:url value='/MemberCenter/adoptionRequestList.controller.0?animalId=${AdoptionRequestList.animal.animalId}&memberId=${AdoptionRequestList.member.id}'/>">退回</a>
														</div>
													</div>
												</div>
											</div>
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
										</c:when>
									</c:choose>
								</c:when>
								<c:when test="${AdoptionRequestList.reviewStatus == 2}">
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
											<fmt:formatDate
												value="${AdoptionRequestList.applyApprovedAt}"
												pattern="yyyy/MM/dd HH:mm:ss" />
											<br>已通知領養者領養
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
											按下按鈕以完成領養
											<div class="mt-20 btn-style1 btn-style-border"
												data-toggle="modal" data-target="#reviewStatus3">完成領養
											</div>
											<div class="modal fade" id="reviewStatus3" tabindex="-1"
												role="dialog" aria-labelledby="exampleModalLabel"
												aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">完成領養</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
															<form>
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">回覆給送養者的話：</label>
																	<textarea class="form-control" id="message-text"
																		rows="5" cols="80"></textarea>
																	<!--textarea可加預設對話 -->
																</div>
															</form>
														</div>
														<div class="modal-footer">
															<button type="button"
																class="btn-style-cancel btn-style-border"
																data-dismiss="modal">取消</button>
															<a class="btn-style1 btn-style-border"
																href="<c:url value='/MemberCenter/adoptionRequestList.controller.3?animalId=${AdoptionRequestList.animal.animalId}&memberId=${AdoptionRequestList.member.id}'/>">完成領養</a>
														</div>
													</div>
												</div>
											</div>
										</c:when>
									</c:choose>
								</c:when>
								<c:when test="${AdoptionRequestList.reviewStatus == 3}">
									<fmt:formatDate value="${AdoptionRequestList.adoptionDate}"
										pattern="yyyy/MM/dd HH:mm:ss" />
									<br>
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
										已送養
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
										已完成領養
										</c:when>
									</c:choose>
								</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<jsp:include page="../fragments/footerArea.jsp" />
		<jsp:include page="../fragments/allJs.jsp" />

		<!-- 轉頁載入動畫2 -->
	</div>
</body>
<script>
	setTimeout(function() {
		$(document).ready(function() {
			document.getElementById("loader").style.display = "none";
			document.getElementById("myDiv").style.display = "block";
		});
	}, 500);
</script>
<!-- 轉頁載入動畫2 -->

</html>