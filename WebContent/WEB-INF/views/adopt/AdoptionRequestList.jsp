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
<script src="<c:url value='/js/animal.js' />" type="text/javascript"
	charset="UTF-8"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
		<%-- 		<jsp:include page="../members/fragments/myAccountLeftArea.jsp" /> --%>

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
						<td><c:choose>
								<c:when
									test="${AdoptionRequestList.reviewStatus == 1 && source == 'AdoptionRequest'}">
									<c:forEach var="reviewStatusMap" items="${reviewStatusMap}">
										<c:if
											test="${reviewStatusMap.key == AdoptionRequestList.animal.animalId}">
											<c:choose>
												<c:when test="${reviewStatusMap.value == 1}">
													<div class="btn-style1 btn-style-border"
														data-toggle="modal"
														data-target="#applyData${AdoptionRequestList.adoptionId}">
														查看申請資料</div>
												</c:when>
												<c:otherwise>
													<div class="btn-style-cancel btn-style-border"
														data-toggle="modal"
														data-target="#applyData${AdoptionRequestList.adoptionId}">
														查看申請資料</div>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="btn-style-cancel btn-style-border"
										data-toggle="modal"
										data-target="#applyData${AdoptionRequestList.adoptionId}">
										查看申請資料</div>
								</c:otherwise>
							</c:choose>
							<div class="modal fade"
								id="applyData${AdoptionRequestList.adoptionId}" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalScrollableTitle"
								aria-hidden="true">
								<div class="modal-dialog modal-dialog-scrollable"
									role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title" id="exampleModalScrollableTitle">領養申請資料</h4>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="textLeft">
											申請日期：
											<fmt:formatDate value="${AdoptionRequestList.applyTime}"
												pattern="yyyy/MM/dd HH:mm:ss" />
											<br> <br> ===動物資料===<br>
											<%-- 收容編號：${AdoptionRequestList.animal.acceptionId}<br> --%>
											動物類別：${AdoptionRequestList.animal.breeds.family}<br>
											動物品種：${AdoptionRequestList.animal.breeds.breed}<br>
											<c:choose>
												<c:when test="${AdoptionRequestList.animal.gender == 1}">
													<div class="displayInlineBlock">性別：</div>公<br>
												</c:when>
												<c:otherwise>
													<div class="displayInlineBlock">性別：</div>母<br>
												</c:otherwise>
											</c:choose>
											毛色：${AdoptionRequestList.animal.coatColor}<br>
											<div class="hover-effect square250px">
												<img class="cardImg marginAuto" alt=""
													src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${AdoptionRequestList.animal.animalId}">
											</div>
											<br> ===申請者資料===<br> 1.申請者皆同意與了解認養須知<br>
											2.申請者已閱讀並同意定型化契約<br> 3.申請人已年滿20歲<br>
											4.飼養地點型態：${AdoptionRequestList.feedAddressType}<br>
											5.現有動物隻數：${AdoptionRequestList.currentAnimalsNum}<br> <br>
											===申請人聯絡方式===<br> 1.市內電話：${AdoptionRequestList.tel}<br>
											2.行動電話：${AdoptionRequestList.mobile}<br>
											3.電子郵件：${AdoptionRequestList.email}<br> <br>
											===送養者聯絡方式===<br>
											1.電話：${AdoptionRequestList.ownerMember.tel}<br>
											2.電子郵件：${AdoptionRequestList.ownerMember.email}<br>
										</div>
										<div class="modal-footer">
											<button type="button"
												class="btn-style-cancel btn-style-border"
												data-dismiss="modal">返回</button>
										</div>
									</div>
								</div>
							</div></td>
						<td><c:choose>
								<c:when test="${AdoptionRequestList.reviewStatus == 0}">
									已於<fmt:formatDate
										value="${AdoptionRequestList.applyRejectedAt}"
										pattern="yyyy/MM/dd HH:mm:ss" />
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
								退回申請
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
								被退回申請
										</c:when>
									</c:choose>
									<div class="mt-10 btn-style-cancel btn-style-border"
										data-toggle="modal"
										data-target="#rejectedReason${AdoptionRequestList.adoptionId}">查看退回原因
									</div>
									<div class="modal fade"
										id="rejectedReason${AdoptionRequestList.adoptionId}"
										tabindex="-1" role="dialog"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="exampleModalLabel">退回原因</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<form>
													<div class="modal-body">
														<div class="form-group">
															<label for="message-text" class="col-form-label font22">
																<c:choose>
																	<c:when test="${source == 'AdoptionRequest'}">
															回覆給申請者的話：
										</c:when>
																	<c:when test="${source == 'MyAdoptionProgress'}">
															回覆給您的話：
										</c:when>
																</c:choose>
															</label> <label class="font22">${AdoptionRequestList.rejectedReason}</label>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button"
															class="btn-style-cancel btn-style-border"
															data-dismiss="modal">返回</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</c:when>
								<c:when test="${AdoptionRequestList.reviewStatus == 1}">
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
											<c:forEach var="reviewStatusMap" items="${reviewStatusMap}">
												<c:if
													test="${reviewStatusMap.key == AdoptionRequestList.animal.animalId}">
													<c:choose>
														<c:when test="${reviewStatusMap.value == 2}">
															尚在等待其他申請者<br>確認是否領養寵物
														</c:when>
														<c:when test="${reviewStatusMap.value == 3}">
															該寵物已被領養
															<div class="mt-10 btn-style-cancel btn-style-border"
																data-toggle="modal"
																data-target="#reviewStatus0${AdoptionRequestList.adoptionId}">退回申請
															</div>
														</c:when>
														<c:when test="${reviewStatusMap.value == 1}">
															待核准申請
															<div class="mt-10 btn-style1 btn-style-border"
																data-toggle="modal"
																data-target="#reviewStatus2${AdoptionRequestList.adoptionId}">核准申請
															</div>
															<div class="mt-10 btn-style-cancel btn-style-border"
																data-toggle="modal"
																data-target="#reviewStatus0${AdoptionRequestList.adoptionId}">退回申請
															</div>
														</c:when>
													</c:choose>
												</c:if>
											</c:forEach>
											<!-- 核准申請 -->
											<div class="modal fade"
												id="reviewStatus2${AdoptionRequestList.adoptionId}"
												tabindex="-1" role="dialog"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">核准申請</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<form
															action="<c:url value='/MemberCenter/adoptionRequestList.controller.2?animalId=${AdoptionRequestList.animal.animalId}&memberId=${AdoptionRequestList.member.id}'/>"
															method="post">
															<div class="modal-body">
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">回覆給申請者的話：</label>
																	<textarea class="form-control" id="message-text"
																		name="approvedReason" rows="5" cols="80">感謝您的領養申請，請於OOOO年OO月OO日OO時間至OO地點領取寵物。</textarea>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button"
																	class="btn-style-cancel btn-style-border"
																	data-dismiss="modal">取消</button>
																<button type="submit" value="submit"
																	class="btn-style1 btn-style-border">核准</button>
															</div>
														</form>
													</div>
												</div>
											</div>
											<!-- 退回申請 -->
											<div class="modal fade"
												id="reviewStatus0${AdoptionRequestList.adoptionId}"
												tabindex="-1" role="dialog"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">退回申請</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<form
															action="<c:url value='/MemberCenter/adoptionRequestList.controller.0?animalId=${AdoptionRequestList.animal.animalId}&memberId=${AdoptionRequestList.member.id}'/>"
															method="post">
															<div class="modal-body">
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">回覆給申請者的話：</label>
																	<textarea class="form-control" id="message-text"
																		name="rejectedReason" rows="5" cols="80">很抱歉，您的申請不符合送養者的需求。</textarea>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button"
																	class="btn-style-cancel btn-style-border"
																	data-dismiss="modal">取消</button>
																<button type="submit" value="submit"
																	class="btn-style1 btn-style-border">退回</button>
															</div>
														</form>
													</div>
												</div>
											</div>
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
											待核准申請
											<button class="mt-10 btn-style-cancel btn-style-border" onclick="confirm('是否要放棄領養?', 'warning', '放棄', ${AdoptionRequestList.animal.animalId}, ${AdoptionRequestList.member.id})">放棄領養</button>
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
											<div class="mt-10 btn-style1 btn-style-border"
												data-toggle="modal"
												data-target="#reviewStatus5${AdoptionRequestList.adoptionId}">確認領養
											</div>
											<button class="mt-10 btn-style-cancel btn-style-border" onclick="confirm('是否要放棄領養?', 'warning', '放棄', ${AdoptionRequestList.animal.animalId}, ${AdoptionRequestList.member.id})">放棄領養</button>
											<!-- 確認領養 -->
											<div class="modal fade"
												id="reviewStatus5${AdoptionRequestList.adoptionId}"
												tabindex="-1" role="dialog"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">確認領養</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<form
															action="<c:url value='/MemberCenter/adoptionRequestList.controller.5?animalId=${AdoptionRequestList.animal.animalId}&memberId=${AdoptionRequestList.member.id}'/>"
															method="post">
															<div class="modal-body">
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">回覆給送養者的話：</label>
																	<textarea class="form-control" id="message-text"
																		name="confirmedAdoptionMessage" rows="5" cols="80">謝謝您的核准，我將於OOOO年OO月OO日OO時間至OO地點向您領取寵物。</textarea>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button"
																	class="btn-style-cancel btn-style-border"
																	data-dismiss="modal">取消</button>
																<button type="submit" value="submit"
																	class="btn-style1 btn-style-border">確認</button>
															</div>
														</form>
													</div>
												</div>
											</div>
										</c:when>
									</c:choose>
									<div class="mt-10 btn-style-cancel btn-style-border"
										data-toggle="modal"
										data-target="#approvedReason${AdoptionRequestList.adoptionId}">查看核准訊息
									</div>
									<div class="modal fade"
										id="approvedReason${AdoptionRequestList.adoptionId}"
										tabindex="-1" role="dialog"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="exampleModalLabel">核准訊息</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<form>
													<div class="modal-body">
														<div class="form-group">
															<label for="message-text" class="col-form-label font22">
																<c:choose>
																	<c:when test="${source == 'AdoptionRequest'}">
																	回覆給申請者的話：
																	</c:when>
																	<c:when test="${source == 'MyAdoptionProgress'}">
																	回覆給您的話：
																	</c:when>
																</c:choose>
															</label> <label class="font22">${AdoptionRequestList.approvedReason}</label>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button"
															class="btn-style-cancel btn-style-border"
															data-dismiss="modal">返回</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</c:when>
								<c:when test="${AdoptionRequestList.reviewStatus == 4}">
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
										申請者於<br>
											<fmt:formatDate
												value="${AdoptionRequestList.abandonedAdoptionAt}"
												pattern="yyyy/MM/dd HH:mm:ss" />
											<br>放棄領養
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
										您已於<br>
											<fmt:formatDate
												value="${AdoptionRequestList.abandonedAdoptionAt}"
												pattern="yyyy/MM/dd HH:mm:ss" />
											<br>放棄領養
										</c:when>
									</c:choose>
								</c:when>
								<c:when test="${AdoptionRequestList.reviewStatus == 5}">
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
										申請者於<br>
											<fmt:formatDate
												value="${AdoptionRequestList.confirmedAdoptionAt}"
												pattern="yyyy/MM/dd HH:mm:ss" />
											<br>確認領養
											<div class="mt-10 btn-style-cancel btn-style-border"
												data-toggle="modal"
												data-target="#confirmedAdoptionMessage${AdoptionRequestList.adoptionId}">
												<c:choose>
													<c:when test="${source == 'AdoptionRequest'}">
														領養者給您的訊息
													</c:when>
													<c:when test="${source == 'MyAdoptionProgress'}">
														您給送養者的訊息
													</c:when>
												</c:choose>
											</div>
											<div class="modal fade"
												id="confirmedAdoptionMessage${AdoptionRequestList.adoptionId}"
												tabindex="-1" role="dialog"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">領養者確認領養訊息</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<form>
															<div class="modal-body">
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">
																		<c:choose>
																			<c:when test="${source == 'AdoptionRequest'}">
																				回覆給您的話：
																			</c:when>
																			<c:when test="${source == 'MyAdoptionProgress'}">
																				回覆給送養者的話：
																			</c:when>
																		</c:choose>
																	</label> <label class="font22">${AdoptionRequestList.confirmedAdoptionMessage}</label>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button"
																	class="btn-style-cancel btn-style-border"
																	data-dismiss="modal">返回</button>
															</div>
														</form>
													</div>
												</div>
											</div>
											<div class="mt-10 btn-style-cancel btn-style-border"
												data-toggle="modal"
												data-target="#approvedReason${AdoptionRequestList.adoptionId}">
												<c:choose>
													<c:when test="${source == 'AdoptionRequest'}">
														您給領養者的訊息
													</c:when>
													<c:when test="${source == 'MyAdoptionProgress'}">
														送養者給您的訊息
													</c:when>
												</c:choose>
											</div>
											<div class="modal fade"
												id="approvedReason${AdoptionRequestList.adoptionId}"
												tabindex="-1" role="dialog"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">核准訊息</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<form>
															<div class="modal-body">
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">
																		<c:choose>
																			<c:when test="${source == 'AdoptionRequest'}">
																		回覆給申請者的話：
																	</c:when>
																			<c:when test="${source == 'MyAdoptionProgress'}">
																		回覆給您的話：
																	</c:when>
																		</c:choose>
																	</label> <label class="font22">${AdoptionRequestList.approvedReason}</label>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button"
																	class="btn-style-cancel btn-style-border"
																	data-dismiss="modal">返回</button>
															</div>
														</form>
													</div>
												</div>
											</div>
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
										當您領取完寵物後<br>按下按鈕以完成領養
											<div class="mt-10 btn-style1 btn-style-border"
												data-toggle="modal"
												data-target="#reviewStatus3${AdoptionRequestList.adoptionId}">完成領養
											</div>
											<div class="modal fade"
												id="reviewStatus3${AdoptionRequestList.adoptionId}"
												tabindex="-1" role="dialog"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="exampleModalLabel">完成領養</h4>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<form
															action="<c:url value='/MemberCenter/adoptionRequestList.controller.3?animalId=${AdoptionRequestList.animal.animalId}&memberId=${AdoptionRequestList.member.id}'/>"
															method="post">
															<div class="modal-body">
																<div class="form-group">
																	<label for="message-text" class="col-form-label font22">回覆給送養者的話：</label>
																	<textarea class="form-control" id="message-text"
																		name="adopterMessage" rows="5" cols="80">謝謝您的送養，我會好好照顧她/他的。</textarea>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button"
																	class="btn-style-cancel btn-style-border"
																	data-dismiss="modal">取消</button>
																<button type="submit" value="submit"
																	class="btn-style1 btn-style-border">完成領養</button>
															</div>
														</form>
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
									<div class="mt-10 btn-style-cancel btn-style-border"
										data-toggle="modal"
										data-target="#adopterMessage${AdoptionRequestList.adoptionId}">
										<c:choose>
											<c:when test="${source == 'AdoptionRequest'}">
												領養者給您的訊息
											</c:when>
											<c:when test="${source == 'MyAdoptionProgress'}">
												您給送養者的訊息
											</c:when>
										</c:choose>
									</div>
									<div class="modal fade"
										id="adopterMessage${AdoptionRequestList.adoptionId}"
										tabindex="-1" role="dialog"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="exampleModalLabel">領養者訊息</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<form>
													<div class="modal-body">
														<div class="form-group">
															<label for="message-text" class="col-form-label font22">
																<c:choose>
																	<c:when test="${source == 'AdoptionRequest'}">
																		回覆給您的話：
																	</c:when>
																	<c:when test="${source == 'MyAdoptionProgress'}">
																		回覆給送養者的話：
																	</c:when>
																</c:choose>
															</label> <label class="font22">${AdoptionRequestList.confirmedAdoptionMessage}</label><br>
															確認領養時間：
															<fmt:formatDate
																value="${AdoptionRequestList.confirmedAdoptionAt}"
																pattern="yyyy/MM/dd HH:mm:ss" />
															<br> <br> <label class="font22">${AdoptionRequestList.adopterMessage}</label><br>
															完成領養時間：
															<fmt:formatDate
																value="${AdoptionRequestList.adoptionDate}"
																pattern="yyyy/MM/dd HH:mm:ss" />
														</div>
													</div>
													<div class="modal-footer">
														<button type="button"
															class="btn-style-cancel btn-style-border"
															data-dismiss="modal">返回</button>
													</div>
												</form>
											</div>
										</div>
									</div>
									<div class="mt-10 btn-style-cancel btn-style-border"
										data-toggle="modal"
										data-target="#approvedReason${AdoptionRequestList.adoptionId}">
										<c:choose>
											<c:when test="${source == 'AdoptionRequest'}">
												您給領養者的訊息
											</c:when>
											<c:when test="${source == 'MyAdoptionProgress'}">
												送養者給您的訊息
											</c:when>
										</c:choose>
									</div>
									<div class="modal fade"
										id="approvedReason${AdoptionRequestList.adoptionId}"
										tabindex="-1" role="dialog"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="exampleModalLabel">核准訊息</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<form>
													<div class="modal-body">
														<div class="form-group">
															<label for="message-text" class="col-form-label font22">
																<c:choose>
																	<c:when test="${source == 'AdoptionRequest'}">
																		回覆給申請者的話：
																	</c:when>
																	<c:when test="${source == 'MyAdoptionProgress'}">
																		回覆給您的話：
																	</c:when>
																</c:choose>
															</label> <label class="font22">${AdoptionRequestList.approvedReason}</label><br>完成領養時間：
															<fmt:formatDate
																value="${AdoptionRequestList.applyApprovedAt}"
																pattern="yyyy/MM/dd HH:mm:ss" />
														</div>
													</div>
													<div class="modal-footer">
														<button type="button"
															class="btn-style-cancel btn-style-border"
															data-dismiss="modal">返回</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</c:when>
								<c:when test="${AdoptionRequestList.reviewStatus == 6}">
									<c:choose>
										<c:when test="${source == 'AdoptionRequest'}">
										申請者於<br>
											<fmt:formatDate
												value="${confirmedTimeOutAt}"
												pattern="yyyy/MM/dd HH:mm:ss" />
											<br>放棄領養
										</c:when>
										<c:when test="${source == 'MyAdoptionProgress'}">
										您因未確認領養，而於<br>
											<fmt:formatDate
												value="${confirmedTimeOutAt}"
												pattern="yyyy/MM/dd HH:mm:ss" />
											<br>被取消領養
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