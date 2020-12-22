<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>寵物領養分析</title>

<jsp:include page="../fragments/links.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.18/c3.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/5.16.0/d3.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.18/c3.js"></script>
<!-- 轉頁載入動畫1 -->
<link rel="stylesheet"
	href="<c:url value='/css/loadingAnimation.css' />">
</head>
<body>
	<!-- 	<div id="loader"></div> -->
	<!-- 	<div style="display: none;" id="myDiv" class="animate-bottom"> -->
	<!-- 轉頁載入動畫1 -->

	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>寵物領養分析</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li class="active">會員中心</li>
					<li class="active">寵物領養分析</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />
	
	<div class="project-count-area pb-70 pt-100">
		<div class="container">
			<div class="row flex-row-reverse">
				<div class="col-lg-12 col-md-8">
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-3 col-md-12">
							<jsp:include page="../members/fragments/myAccountLeftArea.jsp" />
						</div>
						<div class="col-lg-9 col-md-12" id="content">
							<!--<ul class="nav nav-tabs navCustom"> -->
							<!--	<li id="buyCountli" class="activeCustom" onclick="getActive()">購買統計</li> -->
							<!--	<li onclick="getActive()">販賣統計</li> -->
							<!--</ul> -->
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>動物總數</span>
										<h2 class="count" id="myAnimalsNum">${myAnimalsNum}</h2>
										<span>隻</span>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>領養申請總數</span>
										<h2 class="count" id="adoptionApplyNum">${adoptionApplyNum}</h2>
										<span>筆</span>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>領養成功總數</span>
										<h2 class="count" id="adoptionSussessedNum">${adoptionSussessedNum}</h2>
										<span>筆</span>
									</div>
								</div>
							</div>

							<div id="c3_chart_1" style="width: 33%; display: inline-block;"></div>
							<div id="c3_chart_2" style="width: 33%; display: inline-block;"></div>
							<div id="c3_chart_3" style="width: 33%; display: inline-block;"></div>
							<div id="c3_chart_4"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />

	<!-- 轉頁載入動畫2 -->
	<!-- 	</div> -->
</body>
<script>
// 	setTimeout(function() {
// 		$(document).ready(function() {
// 			document.getElementById("loader").style.display = "none";
// 			document.getElementById("myDiv").style.display = "block";
// 		});
// 	}, 500);

	function getDount() {

		var chart1 = c3.generate({
			bindto : '#c3_chart_1',
			data : {
				columns : [
					<c:forEach var="readVarietyAnimalsNums" items="${readVarietyAnimalsNums}">
						["${readVarietyAnimalsNums.key}", ${readVarietyAnimalsNums.value}],
					</c:forEach>
				],
				type : 'donut'
			},
			donut : {
				title : "依品種"
			}
		});

				var chart2 = c3.generate({
					bindto: '#c3_chart_2',
				    data: {
				        columns: [
							<c:forEach var="readVarietyAdoptionAppliesNums" items="${readVarietyAdoptionAppliesNums}">
								["${readVarietyAdoptionAppliesNums.key}", ${readVarietyAdoptionAppliesNums.value}],
							</c:forEach>
				        ],
				        type : 'donut'
				    },
				    donut: {
				        title: "依品種"
				    }
				});

				var chart3 = c3.generate({

					bindto: '#c3_chart_3',
				    data: {
				        columns: [
							<c:forEach var="readVarietyAdoptionSuccessedAppliesNums" items="${readVarietyAdoptionSuccessedAppliesNums}">
								["${readVarietyAdoptionSuccessedAppliesNums.key}", ${readVarietyAdoptionSuccessedAppliesNums.value}],
								</c:forEach>
				        ],

				        type : 'donut'
				    },
				    donut: {
				        title: "依品種"
				    }
				});
	}

	getDount();
</script>
<!-- 轉頁載入動畫2 -->
</html>