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
					<li class="active">會員中心</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />
	
	<div class="project-count-area pb-70 pt-100 gray-bg">
		<div class="container">
			<div class="section-title text-center mb-55">
                    <h2>消費報表</h2>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing</p>
                </div>
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
			<div id="legendPlaceholder"></div>
			<div id="flotcontainer"></div>
		</div>
	</div>
	

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="http://static.pureexample.com/js/flot/excanvas.min.js"></script>
	<script src="http://static.pureexample.com/js/flot/jquery.flot.min.js"></script>
	<script
		src="http://static.pureexample.com/js/flot/jquery.flot.pie.min.js"></script>
<script type="text/javascript">
$(function () { 
    var data = [
        {label: "食物", data: ${categoriesCost["食物"]}},
        {label: "玩具", data: ${categoriesCost["玩具"]}},
        {label: "客廳", data: ${categoriesCost["客廳"]}},
        {label: "廁所", data: ${categoriesCost["廁所"]}},
        {label: "護理", data: ${categoriesCost["護理"]}},
        {label: "餐廳", data: ${categoriesCost["餐廳"]}},
        {label: "戶外", data: ${categoriesCost["戶外"]}}
    ];
 
    var options = {
            series: {
                pie: {show: true}
                    },
            legend: {
                show: false
            }
         };
 
    $.plot($("#flotcontainer"), data, options);  
});
</script>
</body>
</html>