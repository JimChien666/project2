<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>動物認養申請書</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script src="<c:url value='/js/animal.js' />" type="text/javascript"
	charset="UTF-8"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
				<h2>動物認養申請書</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li><a href="<c:url value='/adopt'/>">全部動物</a></li>
					<li><a
						href="<c:url value='/AdoptAnimalDetails.controller?id=${adoptionRecord.animal.animalId}'/>">動物資料</a></li>
					<li><a
						href="<c:url value='/adopt/adoptNotice'/>/${adoptionRecord.animal.animalId}">認養須知</a></li>
					<li class="active">認養申請</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="divFixed btn-style1" id="applyInput">輸入資料</div>

	<!-- ============================================================================================= -->

	<div class="mt-50 divCenter">
		<h2 style="color: red;">請詳實填寫申請書資料，再送出申請。</h2>
	</div>
	<form:form action="/team6/adopt/apply" method="POST"
		modelAttribute="adoptionRecord">
		<div class="wid1000">
			<form:input path="applicantName" type="text"
				class="mt-50 inputH30W200 inputBorder font22" placeholder="請輸入申請人姓名"
				value="${adoptionRecord.member.name}" />
			<h4 class="displayInlineBlock mb-30">已年滿20歲，並具有飼養之能力及場所，願向貴處認養動物乙隻，詳細資料如下:</h4>
			<div class="font22">
				<div class="wid700">
					<div class="f-left mr-10 ptb-20">
						<h3 class="lineH10">
							申請日期：${Today}<br> 收容編號：${adoptionRecord.animal.acceptionId}<br>
							動物類別：${adoptionRecord.animal.breeds.family}<br>
							動物品種：${adoptionRecord.animal.breeds.breed}<br>
							<c:choose>
								<c:when test="${adoptionRecord.animal.gender == 1}">
									<div class="displayInlineBlock">性別：</div>公<br>
								</c:when>
								<c:otherwise>
									<div class="displayInlineBlock">性別：</div>母<br>
								</c:otherwise>
							</c:choose>
							毛色：${adoptionRecord.animal.coatColor}<br>
						</h3>
						<div class="divHidden">
							<!-- 領養編號要列出，更新才能存 -->
							領養編號
							<form:input path="adoptionId"
								value="${adoptionRecord.adoptionId}" />
							會員編號
							<form:input path="memberId" value="${adoptionRecord.member.id}" />
							動物編號
							<form:input path="animalId"
								value="${adoptionRecord.animal.animalId}" />
						</div>
					</div>
					<div class="ptb-50">
						<div class="hover-effect square250px">
							<img class="cardImg marginAuto" alt=""
								src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${adoptionRecord.animal.animalId}">
						</div>
					</div>
				</div>
				<div class="mt-50 mb-30">本人願遵守以下約定:</div>
				<div class="mb-30">1. 依法辦理寵物登記、晶片植入等事項。並同意為認養隻動物絕育，避免不必要之繁殖。</div>
				<div class="mb-30">
					2.無論何時都以人道方式對待認養動物，提供牠適當之食物、飲水及空間，並絕不任意棄養認養的動物。</div>
				<div class="mb-30">
					3.若認養動物年齡未滿8週或不宜施打疫苗者，必須完成狂犬病預防注射，並將注射證明影本郵寄或傳真回本所備查。</div>
				<div class="mb-30">4. 定期幫牠進行狂犬病預防注射、驅蟲及健康檢查，受傷或罹病時，必請獸醫師給予醫療。</div>
				<div class="mb-30">5. 妥善照顧牠，防止其無故侵害他人之生命，身體、自由、財產或安寧。</div>
				<div class="mb-30">6.
					不再隨便放縱牠於戶外，牠出入公共場所或公眾出入之場所時，必由7歲以上之人伴同，並採取適當之防衛措施，如繫犬鍊、帶口罩等，始得攜出戶外。</div>
				<div class="mb-30">7.
					當牠轉讓、死亡或住所異動時，於1個月內依規定辦理變更登記；遺失時，於5天內依規定申報。</div>
				<div class="mb-30">8. 本人願接受貴所之追蹤訪視及飼養輔導。</div>
				<div class="mb-30">9. 如有違反上述認養規定，貴所有權收回該動物，並終止認養人對該認養動物之權利。</div>
				<div class="mb-30">10.
					如因任何原因無法續養，本人願為牠找到新的認養家庭，或送至動物保護團體所屬收容所，或再送至貴所辦理不擬續養手續並依收容所規定繳交規費。</div>
				<div class="mb-30">11.
					依據「動物保護法」第33-1條之規定:完成不擬續養手續後將不得飼養寵物及認養收容動物。</div>
				<div class="mb-30">12.
					本認養申請資料送出後，不代表已完成所選動物之認養，亦不代表您已具認養本動物的第一優先權，認養以收容所現場完成程序為準。</div>
			</div>
			<div class="mb-30">
				<form:checkbox path="agreement" value="1" class="square20px"
					id="agreement" />
				<h3 class="displayInlineBlock">我已詳盡閱讀並同意以上內容</h3>
			</div>
			<!-- 申請 -->
			<div class="wid700 margin0Auto">
				<div class="mt-50">
					<h1>申請資料</h1>
				</div>
				<div class="mt-30 font22 applyInputW">
					<div class="mb-20">
						<div><font color="red">*</font>1.飼養地點</div>
						<form:input path="feedAddress" placeholder="請輸入地址"
							value="${adoptionRecord.member.address}" />
					</div>
					<div class="mb-20">
						<div><font color="red">*</font>2.飼養地點型態</div>
						<form:input path="feedAddressType"
							placeholder="請輸入飼養地點型態(大樓、公寓、平房等)" id="feedAddressType" />
					</div>
					<div class="mb-20">
						<div>3.現有動物隻數</div>
						<form:label path="currentAnimalsNum" class="font22">${currentAnimalsNum}隻(您在本平台擁有的寵物數量)</form:label>
						<form:input path="currentAnimalsNum" placeholder="請輸入隻數"
							id="currentAnimalsNum" value="${currentAnimalsNum}" class="divHidden"/>
					</div>
				</div>
				<div class="font22 applyInputW2 mb-20">
					<div class="mb-10">
						4.個人資料<br>
					</div>
					<div class="mb-10">
						<div>認養人姓名：</div>
						<form:input path="adopterName"
							value="${adoptionRecord.member.name}" />
						<br>
					</div>
					<div class="mb-10">
						<div><font color="red">*</font>身分證字號：</div>
						<form:input path="personalId" type="text" id="personalId" />
						<br>
					</div>
					<div class="mb-10">
						<div><font color="red">*</font>出生日期：</div>
						<form:input path="birthdayString" type="date" id="date" />
						<br>
					</div>
					<div class="mb-10">
						<div><font color="red">*</font>市內電話：</div>
						<form:input path="tel" type="text" id="tel" />
						<br>
					</div>
					<div class="mb-10">
						<div><font color="red">*</font>行動電話：</div>
						<form:input path="mobile" type="text" id="mobile" />
						<br>
					</div>
					<div class="mb-10">
						<div><font color="red">*</font>電子郵件：</div>
						<form:input path="email" value="${adoptionRecord.member.email}" />
						<br>
					</div>
					<div class="mb-10">
						<div><font color="red">*</font>戶籍地址：</div>
						<form:input path="residentAddress" type="text" />
						<br>
					</div>
					<div class="mb-50">
						<div><font color="red">*</font>通訊地址：</div>
						<form:input path="mailingAddress"
							value="${adoptionRecord.member.address}" />
						<br>
					</div>
				</div>
			</div>
		</div>

		<!-- ============================================================================================= -->

		<div class="divCenter mb-50">
			<a href="<c:url value='/adopt'/>"
				class="btn-style-cancel btn-style-border">取消</a>
			<form:button value="Send"
				class="btn-style1 btn-style-border" onclick="success('申請', ' 寄送審核資料給送養者中')">送出申請</form:button>
		</div>
	</form:form>
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

<script>
	//一鍵輸入
	$('#applyInput').click(function() {
		$('input:checkbox:first').attr("checked", 'checked');
		$('#feedAddressType').val("大樓");
		$('#personalId').val("A123456789");
		$('#date').val("1991-05-05");
		$('#tel').val("02-2239-5214");
		$('#mobile').val("0985-459-725");
		$('#residentAddress').val($('#mailingAddress').val());
	});
</script>
</html>