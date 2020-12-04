<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
#flotcontainer {
	width: 600px;
	height: 400px;
	text-align: left;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../fragments/links.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.18/c3.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/5.16.0/d3.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.18/c3.js"></script>
</head>
<body>
	<jsp:include page="../fragments/headerArea.jsp" />
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">

			<div class="breadcrumb-content text-center">
				<h2>會員中心</h2>
				<ul>
					<li><a href="<c:url value='/' />">首頁</a></li>
					<li><a href="<c:url value='/member/myAccount' />">會員中心</a></li>
					<li class="active">訂單數據</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />

	<div class="project-count-area pb-70 pt-100 gray-bg">
		<div class="container">
			<div class="row flex-row-reverse">
				<div class="col-lg-12 col-md-8"">
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-3 col-md-12">
							<jsp:include page="../members/fragments/myAccountLeftArea.jsp" />
						</div>
						<div class="col-lg-9 col-md-12">
							
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>總下單數</span>
										<h2 class="count">${dataPerMonth.count}</h2>
										<span>張</span>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>總下單金額</span>
										<h2 class="count">${dataPerMonth.sum}</h2>
										<span>元</span>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>平均訂單金額</span>
										<c:if test="${dataPerMonth.count!=0}">
											<h2 class="count">${dataPerMonth.sum/dataPerMonth.count}</h2>
										</c:if>
										<c:if test="${dataPerMonth.count==0}">
											<h2 class="count">0</h2>
										</c:if>
										<span>元</span>
									</div>
								</div>
							</div>

							<div id="c3_chart_1" style="width: 33%; display: inline-block;"></div>
							<div id="c3_chart_2" style="width: 33%; display: inline-block;"></div>
							<div id="c3_chart_3" style="width: 33%; display: inline-block;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<jsp:include page="../fragments/footerArea.jsp" />
		<jsp:include page="../fragments/allJs.jsp" />





		<script>
var chart1 = c3.generate({
	bindto: '#c3_chart_1',
    data: {
        columns: [
        	["食物", ${categoriesCost["食物"]}],
            ["玩具", ${categoriesCost["玩具"]}],
            ["客廳", ${categoriesCost["客廳"]}],
            ["廁所", ${categoriesCost["廁所"]}],
            ["護理", ${categoriesCost["護理"]}],
            ["餐廳", ${categoriesCost["餐廳"]}],
            ["戶外", ${categoriesCost["戶外"]}],
        ],
        type : 'donut'
    },
    donut: {
        title: "依分類"
    }
});

var chart2 = c3.generate({
	
	bindto: '#c3_chart_2',
    data: {
        columns: [
        	["貓", ${animalTypesCost["貓"]}],
            ["狗", ${animalTypesCost["狗"]}],
            
        ],
        type : 'donut'
    },
    donut: {
        title: "依寵物類別"
    }
});

var chart3 = c3.generate({
	
	bindto: '#c3_chart_3',
    data: {
        columns: [
        	["純白", ${colorsCost["純白"]}],
            ["灰黑", ${colorsCost["灰黑"]}],
            ["紅粉", ${colorsCost["紅粉"]}],
            ["棕黃", ${colorsCost["棕黃"]}],
            ["綠色", ${colorsCost["綠色"]}],
            ["藍紫", ${colorsCost["藍紫"]}],
            
        ],
        
        type : 'donut'
    },
    donut: {
        title: "依顏色"
    }
});


</script>
</body>
</html>