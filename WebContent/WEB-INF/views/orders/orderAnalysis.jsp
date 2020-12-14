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

.navCustom li {
	font-size: 20px;
	padding: 10px;
	cursor: pointer;
}

.activeCustom {
	background-color: #7E4C4F; /* Green */
	border: none;
	color: #FFFFFF;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	border-radius: 10px 10px 0px 0px;
	transition-duration: 0.3s;
	cursor: pointer
}

li.activeCustom:hover {
	background-color: #000000;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../fragments/links.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.18/c3.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/5.16.0/d3.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.18/c3.js"></script>
<script>
function getActive(){
	$(".activeCustom").removeClass("activeCustom");
	this.event.path[0].classList.add("activeCustom");
	if(this.event.path[0].innerHTML=="販賣統計"){
		goSellingCountPage();
	}
	else if (this.event.path[0].innerHTML=="購買統計"){
		goBuyCountPage();
	}
	
}
function goSellingCountPage(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/order/sellingData.json' />",
			true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var responseData = xhr.responseText;
				data = JSON.parse(responseData);
				var content = "";
				content += '<ul class="nav nav-tabs navCustom">'+
					'<li id="buyCountli" onclick="getActive()">購買統計</li>'+
					'<li onclick="getActive()" class="activeCustom" >販賣統計</li>'+
					'<li onclick="getActive()">購買商品紀錄</li>'+
					'<li onclick="getActive()">販賣商品紀錄</li>'+
				'</ul>'+
				'<button onclick="goSellingCountPage()">整體</button><select style="width: 100px;" onchange="goSellingCountPageByMonth(this.options[this.options.selectedIndex].value)"><option>依月分</option>'
				for(i=0;i<data["dateChooseList"].length;i++){
					content += '<option value="'+data["dateChooseList"][i]+'">' + data["dateChooseList"][i] + '</option>'
				}
				content += '</select><div class="row">'+
				
					'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
						'<div class="single-count mb-30 text-center">'+
							'<span>總下單數</span>'+
							'<h2 class="count" id="totalOrder">'+ data["count"] + '</h2>'+
							'<span>張</span>'+
						'</div>'+
					'</div>'+
					'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
						'<div class="single-count mb-30 text-center">'+
							'<span>總營業額</span>'+
							'<h2 class="count" id="totalCost">'+ parseInt(data["sum"]) + '</h2>'+
							'<span>元</span>'+
						'</div>'+
					'</div>'+
					'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
						'<div class="single-count mb-30 text-center">'+
							'<span>平均客單價</span>'	
							if(data["count"]!=0){
								content+='<h2 class="count" id="avgCost">'+ parseInt(parseInt(data["sum"])/data["count"]) + '</h2>';
							}else{
								content+='<h2 class="count" id="avgCost">0</h2>';
							}
							
							content+='<span>元</span>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div id="c3_chart_1" style="width: 33%; display: inline-block;"></div>'+
				'<div id="c3_chart_2" style="width: 33%; display: inline-block;"></div>'+
				'<div id="c3_chart_3" style="width: 33%; display: inline-block;"></div>'+
				'<div id="c3_chart_4"></div>'

				document.getElementById("content").innerHTML=content;
				getSellingChart();
				
			} else {
				alert(xhr.status);
			}
		}
	}
}

function goSellingCountPageByMonth(date){
	console.log(date);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/order/sellingData.json' />?date="+date,
			true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var responseData = xhr.responseText;
				data = JSON.parse(responseData);
				var content = "";
				content += '<ul class="nav nav-tabs navCustom">'+
					'<li id="buyCountli" onclick="getActive()">購買統計</li>'+
					'<li onclick="getActive()" class="activeCustom" >販賣統計</li>'+
					'<li onclick="getActive()">購買商品紀錄</li>'+
					'<li onclick="getActive()">販賣商品紀錄</li>'+
				'</ul>'+
				'<button onclick="goSellingCountPage()">整體</button><select style="width: 100px;" onchange="goSellingCountPageByMonth(this.options[this.options.selectedIndex].value)"><option>依月分</option>'
				for(i=0;i<data["dateChooseList"].length;i++){
					if (data["dateChooseList"][i]==date){
						content += '<option value="'+data["dateChooseList"][i]+'" selected>' + data["dateChooseList"][i] + '</option>'
					}else{
						content += '<option value="'+data["dateChooseList"][i]+'">' + data["dateChooseList"][i] + '</option>'
					}
					
				}
				content += '</select><div class="row">'+
				
					'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
						'<div class="single-count mb-30 text-center">'+
							'<span>總下單數</span>'+
							'<h2 class="count" id="totalOrder">'+ data["count"] + '</h2>'+
							'<span>張</span>'+
						'</div>'+
					'</div>'+
					'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
						'<div class="single-count mb-30 text-center">'+
							'<span>總營業額</span>'+
							'<h2 class="count" id="totalCost">'+ parseInt(data["sum"]) + '</h2>'+
							'<span>元</span>'+
						'</div>'+
					'</div>'+
					'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
						'<div class="single-count mb-30 text-center">'+
							'<span>平均客單價</span>'	
							if(data["count"]!=0){
								content+='<h2 class="count" id="avgCost">'+ parseInt(parseInt(data["sum"])/data["count"]) + '</h2>';
							}else{
								content+='<h2 class="count" id="avgCost">0</h2>';
							}
							
							content+='<span>元</span>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div id="c3_chart_1" style="width: 33%; display: inline-block;"></div>'+
				'<div id="c3_chart_2" style="width: 33%; display: inline-block;"></div>'+
				'<div id="c3_chart_3" style="width: 33%; display: inline-block;"></div>'+
				'<div id="c3_chart_4"></div>'

				document.getElementById("content").innerHTML=content;
				getSellingChartByMonth(date);
				
			} else {
				alert(xhr.status);
			}
		}
	}
}

function goBuyCountPage(){
	var content = "";
	content += '<ul class="nav nav-tabs navCustom">'+
		'<li id="buyCountli" class="activeCustom" onclick="getActive()">購買統計</li>'+
		'<li onclick="getActive()">販賣統計</li>'+
		'<li onclick="getActive()">購買商品紀錄</li>'+
		'<li onclick="getActive()">販賣商品紀錄</li>'+
	'</ul>'+
	'<div class="row">'+
		'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
			'<div class="single-count mb-30 text-center">'+
				'<span>總下單數</span>'+
				'<h2 class="count" id="totalOrder">${dataPerMonth.count}</h2>'+
				'<span>張</span>'+
			'</div>'+
		'</div>'+
		'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
			'<div class="single-count mb-30 text-center">'+
				'<span>總下單金額</span>'+
				'<h2 class="count" id="totalCost">${dataPerMonth.sum}</h2>'+
				'<span>元</span>'+
			'</div>'+
		'</div>'+
		'<div class="col-lg-4 col-md-4 col-sm-6 col-12">'+
			'<div class="single-count mb-30 text-center">'+
				'<span>平均訂單金額</span>'	
				if(${dataPerMonth.count}!=0){
					content+='<h2 class="count" id="avgCost">${dataPerMonth.sum/dataPerMonth.count}</h2>';
				}else{
					content+='<h2 class="count" id="avgCost">0</h2>';
				}
				
				content+='<span>元</span>'+
			'</div>'+
		'</div>'+
	'</div>'+
	'<div id="c3_chart_1" style="width: 33%; display: inline-block;"></div>'+
	'<div id="c3_chart_2" style="width: 33%; display: inline-block;"></div>'+
	'<div id="c3_chart_3" style="width: 33%; display: inline-block;"></div>'+
	'<div id="c3_chart_4"></div>'
	document.getElementById("content").innerHTML=content;
	getBuyDount();
	
}
</script>

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
					<li class="active">交易數據</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />

	<div class="project-count-area pb-70 pt-100">
		<div class="container">
			<div class="row flex-row-reverse">
				<div class="col-lg-12 col-md-8"">
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-3 col-md-12">
							<jsp:include page="../members/fragments/myAccountLeftArea.jsp" />
						</div>
						<div class="col-lg-9 col-md-12" id="content">
							<ul class="nav nav-tabs navCustom">
								<li id="buyCountli" class="activeCustom" onclick="getActive()">購買統計</li>
								<li onclick="getActive()">販賣統計</li>
								<li onclick="getActive()">購買商品紀錄</li>
								<li onclick="getActive()">販賣商品紀錄</li>

							</ul>
							<div class="row" >
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>總下單數</span>
										<h2 class="count" id="totalOrder">${dataPerMonth.count}</h2>
										<span>張</span>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>總下單金額</span>
										<h2 class="count" id="totalCost">${dataPerMonth.sum}</h2>
										<span>元</span>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<div class="single-count mb-30 text-center">
										<span>平均訂單金額</span>
										<c:if test="${dataPerMonth.count!=0}">
											<h2 class="count" id="avgCost">${dataPerMonth.sum/dataPerMonth.count}</h2>
										</c:if>
										<c:if test="${dataPerMonth.count==0}">
											<h2 class="count" id="avgCost">0</h2>
										</c:if>
										<span>元</span>
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


		<jsp:include page="../fragments/footerArea.jsp" />
		<jsp:include page="../fragments/allJs.jsp" />





<script>
function getBuyDount(){
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
}

getBuyDount();
function getSellingChart(){
	var xhr = new XMLHttpRequest();
	xhr.open("Get", "<c:url value='/order/sellingCountByDate.json'/>", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 ) {
			if (xhr.status == 200){
				var responseData = xhr.responseText;
				
				result = JSON.parse(responseData);
				var chart = c3.generate({
					bindto: '#c3_chart_4',
					data: {
				        x: 'x',
				        columns: [
				            ['x'].concat(result['date']),
				            ['累積營收'].concat(result['sum']),
				            ['每日營收'].concat(result['sumPerDay'])
				        ]
				    },
				    axis: {
				        x: {
				            type: 'timeseries',
				            tick: {
				                format: '%Y-%m-%d'
				            }
				        }
				    }
				});
			}
		}
	}
	
}
function getSellingChartByMonth(date){
	var xhr = new XMLHttpRequest();
	xhr.open("Get", "<c:url value='/order/sellingCountByDate.json'/>?date="+date, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 ) {
			if (xhr.status == 200){
				var responseData = xhr.responseText;
				
				result = JSON.parse(responseData);
				var chart = c3.generate({
					bindto: '#c3_chart_4',
					data: {
				        x: 'x',
				        columns: [
				            ['x'].concat(result['date']),
				            ['累積營收'].concat(result['sum']),
				            ['每日營收'].concat(result['sumPerDay'])
				        ]
				    },
				    axis: {
				        x: {
				            type: 'timeseries',
				            tick: {
				                format: '%Y-%m-%d'
				            }
				        }
				    }
				});
			}
		}
	}
	
}
</script>
</body>
</html>