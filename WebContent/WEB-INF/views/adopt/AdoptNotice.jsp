<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>愛心犬貓認養須知</title>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
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
				<h2>愛心犬貓認養須知</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li><a href="<c:url value='/adopt'/>">全部動物</a></li>
					<li><a
						href="<c:url value='/AdoptAnimalDetails.controller?id=${animal.animalId}'/>">動物資料</a></li>
					<li class="active">認養須知</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- ============================================================================================= -->

<!-- 	<div class="mt-50 divCenter"> -->
<!-- 		<h1>愛心犬貓認養須知</h1> -->
<!-- 	</div> -->
	<form:form action="/team6/adopt/adoptApply" method="POST"
		modelAttribute="adoptionRecord">
		<div class="wid700px mt-30">
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
					<div class="divHidden">
					會員編號
					<form:input path="memberId" value="${member.id}" />
					動物編號
					<form:input path="animalId" value="${animal.animalId}" />
					</div>
			</div>
			<div class="ptb-50">
				<div class="hover-effect square250px">
					<img class="cardImg marginAuto" alt=""
						src="${pageContext.servletContext.contextPath}/filuploadAction.contoller/${animal.animalId}">
				</div>
			</div>
		</div>
		<div class="mt-20 mb-50">
			<table class="wid1000px font22">
				<tr>
					<td class="tdVertical wid20">(一)</td>
					<td>您是否看過犬貓之行為健康評估表?<br>您是否與管理人員或志工討論過該犬貓之狀況。
					</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice1" value="1">有</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice1" value="0" checked>無</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(二)</td>
					<td>您是否知道犬貓的健康有許多不確定的風險?<br>您是否了解當牠生病時立即就醫治療是飼主的責任?
					</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice2" value="1">了解</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice2" value="0" checked>不了解</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(三)</td>
					<td>您是否了解犬貓因想要什麼或不想要什麼的時候藉由吠叫來表達，也會因為個性的不同及對事物的好奇會有難以控制的情況發生，只要透過適當的教導就可以獲得改善。您是否了解:
						給犬貓良好的教育，是飼主應該努力學習的責任?</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice3" value="1">了解</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice3" value="0" checked>不了解</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(四)</td>
					<td>您是否了解犬貓剛到新環境時，許多潛在的疾病會因<br>1.運輸過程吹風淋雨 2.洗澡 3.過度玩耍
						4.更換食物等等讓降低抵抗力而爆發疾病<br>您是否了解飼主在犬貓適應期間扮演著極度重要的角色?<br>(註:運輸使用運輸籠，14天後再洗澡，以乾飼料為主食可幫助犬貓度過適應期)
					</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice4" value="1">了解</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice4" value="0" checked>不了解</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(五)</td>
					<td>您是否了解就外觀判斷犬貓健康是不夠的，必須到動物醫院為牠做基本的檢查</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice5" value="1">了解</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice5" value="0" checked>不了解</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(六)</td>
					<td>犬貓將近有15年的壽命，您是否了解無論人事物的演變<br>(例如:結婚生子 搬家出國
						當兵都不足以構成棄養的原因!)
					</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice6" value="1">了解</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice6" value="0" checked>不了解</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(七)</td>
					<td>您是否了解飼養動物需要適當的居家條件?<br>(請考量套房 公寓 大樓 的飼養空間)
					</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice7" value="1">了解</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice7" value="0" checked>不了解</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(八)</td>
					<td>您是否同意在未取得共識之前絕不貿然認養，以免造成日後極大的心理負擔<br>認養犬貓的決定是否已經獲得房東/家人/室友的同意?
					</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice8" value="1">同意</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice8" value="0" checked>不同意</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(九)</td>
					<td>依照動物保護法，犬隻出入公共場所都要有飼主陪同，任意縱放犬貓在外遊蕩將會受罰<br>您是否了解若縱放犬貓在外，任何人都可以加以協送保護送交收容所。
					</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice9" value="1">了解</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice9" value="0" checked>不了解</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tdVertical wid20">(十)</td>
					<td>如因任何原因無法續養，本人願為牠找到新的認養家庭，或送至動物保護團體所屬收容所，或再送至貴所辦理不擬續養手續並依收容所規定繳交規費。
					</td>
					<td class="wid20"></td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice10" value="1">同意</td>
					<td class="tdVertical wid100"><input type="radio"
						class="square20px" name="notice10" value="0" checked>不同意</td>
				</tr>
			</table>
		</div>

		<!-- ============================================================================================= -->

		<div class="divCenter mb-50">
			<a href="<c:url value='/adopt'/>"
				class="btn-style-cancel btn-style-border">取消</a>
			<form:button value="Send" class="btn-style1 btn-style-border">儲存</form:button>
		</div>
	</form:form>
	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>