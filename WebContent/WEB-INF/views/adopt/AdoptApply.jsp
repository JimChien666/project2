<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>動物認養申請書</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
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
						href="<c:url value='/AdoptAnimalDetails.controller?id=${animal.animalId}'/>">動物資料</a></li>
					<li><a
						href="<c:url value='/adoptNotice'/>?animalId=${animal.animalId}">認養須知</a></li>
					<li class="active">認養申請</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- ============================================================================================= -->

<!-- 	<div class="mt-50 divCenter"> -->
<!-- 		<h1>動物認養申請書</h1> -->
<!-- 	</div> -->
	<div class="wid1000px">
		<!-- 閱讀 -->
		<input type="text" class="mt-50 inputH30W200 inputBorder font22"
			placeholder="請輸入申請人姓名">
		<h4 class="div1 mb-30">已年滿20歲，並具有飼養之能力及場所，願向貴處認養動物乙隻，詳細資料如下:</h4>
		<div class="font22">
			<div class="wid700px">
				<div class="f-left mr-10 ptb-20">
					<h3 class="lineH10">
						申請日期：${Today}<br> 收容編號：${animal.acceptionId}<br>
						動物類別：${animal.breeds.family}<br> 動物品種：${animal.breeds.breed}<br>
						<c:choose>
							<c:when test="${animal.gender == 1}">
								<div class="div1">性別：</div>公<br>
							</c:when>
							<c:otherwise>
								<div class="div1">性別：</div>母<br>
							</c:otherwise>
						</c:choose>
						毛色：${animal.coatColor}<br>
					</h3>
				</div>
				<div class="ptb-50">
					<div class="hover-effect square250px">
						<img class="cardImg marginAuto" alt=""
							src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${animal.animalId}">
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
			<input type="checkbox" class="square20px">
			<h3 class="div1">我已詳盡閱讀以上內容</h3>
		</div>
		<!-- 申請 -->
		<div class="mt-50">
			<h1>申請資料</h1>
		</div>
		<div class="mt-30 font22 ">
			<div class="mb-20">
				1.飼養地點：<input placeholder="請輸入地址">
			</div>
			<div class="mb-20">
				2.飼養地點型態：<input placeholder="請輸入飼養地點型態(大樓、公寓、平房等)">
			</div>
			<div class="mb-20">
				3.現有動物隻數：<input placeholder="請輸入隻數">
			</div>
			<div class="mb-20">
				<div class="mb-10">
					4.個人資料：<br>
				</div>
				<div class="mb-10">
					認養人姓名：<input type="text"><br>
				</div>
				<div class="mb-10">
					*身分證字號：<input type="text"><br>
				</div>
				<div class="mb-10">
					*出生日期：<input type="text" class="datepicker"><br>
				</div>
				<div class="mb-10">
					室內電話：<input type="text" class="wid305px"> 行動電話：<input
						type="text" class="wid305px"><br>
				</div>
				<div class="mb-10">
					電子郵件：<input type="text"><br>
				</div>
				<div class="mb-10">
					*戶籍地址<input type="text"><br>
				</div>
				<div class="mb-50">
					通訊地址<input type="text"><br>
				</div>
			</div>
		</div>
	</div>

	<!-- ============================================================================================= -->


	<div class="divCenter mb-50">
		<a href="<c:url value='/adopt'/>"
			class="btn-style-cancel btn-style-border">取消</a> <a
			href="<c:url value='/adopt'/>"
			class="btn-style1 btn-style-border" id="save">送出申請</a>
	</div>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>