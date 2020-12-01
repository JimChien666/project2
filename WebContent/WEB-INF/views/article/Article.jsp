<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>

<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css"> -->

<!-- <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script> -->



<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
<jsp:include page="../fragments/links.jsp" />




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

/*  table, th, td {  */
/*  	border: 1px solid black;  */
/*  }  */
/* td { */
/* 	border: 1px solid black; */
/* } */
/* table{ */
/* 	table-dark */
/* 	table */
/* } */
</style>
<meta charset="UTF-8">
<title>ID:<c:out value="${article.getId()}" />/<c:out
		value="${article.getTitle()}" /></title>
</head>

<script>
	// $(document).ready(function(){
	// 	console.log("hi")
	// 	$("#articleShow").DataTable({
	// 	"ajax": {
	//         "type" : "GET",
	//         "url" : "<c:url value='article' />?articleId=${articleId}",
	//         "dataSrc": function (json){
	//         	console.log(json.article.forums)
	//         	return json.article.forums 	
	//             }
	// 		},
	// 	"columns" :[
	// 		{"data": "id"},
	// 		{"data": "content"}
	// 		]
	// 		});
	// })

// 	var article = $.ajax({
	
	$(function() {	
	$.ajax({
		type : "GET",
		url : "<c:url value='article' />?articleId=${articleId}",
		success : function(mapData) {
			showPage(mapData)
		}
	});

	function showPage(mapData) {
		var $article = $("#articleShow")
		// 	var mapData = JSON.parse(responseData);
		// 	var 	 = responseData;
		article = mapData.article;
// 		console.log(article["title"]);
		pageNo = mapData.currPage;
		totalPage = mapData.totalPage;
		recordCounts = mapData.recordCounts;
		forumList = mapData.forumList;
// 		var content = "";
		$article.empty();
// 		console.log(article)
// 		$article.append("<h3>" + article.title + "</h3>")
		$article.append("<h2 style='clear:both; box-shadow:1px 3px 5px 2px #cccccc;'>"+"標題: " + article.title + "</h2>")
		$article.append("<table style='width: 100%;'><tr><th>"+""+"</th><th>"+""+"</th></tr>")
// 		$article.append("<table style='width: 100%;' class='table table-striped'><tr><th>討論串編號</th><th>討論串內容</th></tr>")
		console.log(forumList)
		$.each(forumList, function(i, forum) {
			var imgTag = `<img src="<c:url value='/member/processFileReadAction.contoller?fileId=` + forum.forumOwnerFileId + `' />" width="50" height="50" class="d-inline-block align-top" alt="" style="border-radius: 50%; border: 2px white solid;">`
// 			$article.append("<tr><td><div style='width:60px; background-color: coral; box-shadow:1px 3px 5px 2px #cccccc;'>"+ imgTag + forum.memberid + "</div></td><td id="+forum.id+"><div style='width:1100px; margin:0px 10px 10px 10px; padding:30px; box-shadow:1px 3px 5px 2px #cccccc;'>" + forum.content
			$article.append("<tr><td><div style='width:60px;'>"+ imgTag +"</div></td><td id="+forum.id+"><div style='width:1100px; margin:0px 10px 10px 10px; padding:30px; box-shadow:1px 3px 5px 2px #cccccc;'>" + forum.content
					+ "</div></td></tr>")
			$article.append("</table>");
//--------------------------------------
			var forumId  = forum.id
			var $forums = $("#"+forum.id)
// 	 		console.log("forumId: "+forumId)
	 		
	 		
									$.ajax({
										type:'GET',
										url:'showComments?forumsId='+forumId,
// 										url:"<c:url value='showComments?forumsId=${Forums.getId()}' />",
										success: function(comments){
// 											console.log('successInsert',comments)
										$.each(comments, function(j, order){
											if(order.forumid===forumId){
// 												console.log("order.forumid:"+order.forumid);
// 												console.log("forumId:"+forumId);								
												$forums.append('<div style="width:1050px; margin: 0px 20px 10px 10px; background-color:#F5F5F5; box-shadow:1px 3px 5px 2px #cccccc;">'+order.memberid+':'+order.comment+'</div>')
// 										 		console.log("$forums: "+ $forums)												
												}
											});


										if(${empty LoginOK}){
											$forums.append("<div style='width:1100px; margin: 0px 10px 10px 10px; background-color:	#FFDAB9;'>回覆本討論串:<input type='text' disabled placeholder='請登入後留言' name='comments' id=reply"+forum.id+"></div>")
										}else{											
											$forums.append("<div style='width:1100px; margin: 0px 10px 10px 10px; background-color:#FFDAB9;'>回覆本討論串:<input type='text' name='comments' placeholder='有甚麼想法呢?' id=reply"+forum.id+"></div>")
											}


											$("#reply"+forum.id).keypress(function (e) {
												// $("input").keypress(function (e) {
													console.log("hi hi");
													console.log(forumId);
// 													var id = this.forumId;
													var comment = this.value;
// 													console.log(id);
													console.log(comment);
												if (e.keyCode == 13&&comment!="") {
												$.ajax({
												  url: "saveComments",
												  data: {
													  id:forumId,
													  comment:comment
													  },
												  success:function(){
//														  reset();
														showPage(mapData);

//														  var $comments = $('.${Forums.id}');
//															console.log($comments);

												  $("input").prop("value","");
												}
												//  dataType: dataType
												});
//												  	var $comments = $('.${Forums.id}');					      
												};//if end
												});
											}					
										});


//-----------------------
// 			$.each(forum.comments, function(j, c) {
// 				$article.append("<tr><th>留言編號</th><th>留言內容</th></tr>").one
// 				$article.append("<tr><td>" + c.id + "</td><td>" + c.comment
// 						+ "</td></tr>")
// 			})
			$article.append("<br>");

		})

//////////////

		var navContent = "" ;
		if (pageNo != 1){
			navContent += "<li><a id='first'><<</a></li>";
			navContent += "<li><a id='prev'><</a></li>";
		}  else {
			navContent += "<li>&nbsp;</li>";
			navContent += "<li>&nbsp;</li>";
		}
		navContent += "<td width='200' align='center'> " + pageNo + " / " + totalPage + "</td>";
		if (pageNo != totalPage){
			navContent += "<li><a id='next'>></a></li>";
			navContent += "<li><a id='last'>>></a></li>";
		}  else {
			navContent += "<td align='center'>&nbsp;</td>";
			navContent += "<td align='center'>&nbsp;</td>";
		}
		
		document.getElementById("navigation").innerHTML = navContent;
		var firstBtn = document.getElementById("first");
		var prevBtn  = document.getElementById("prev");
		var nextBtn  = document.getElementById("next");
		var lastBtn  = document.getElementById("last");
		if (firstBtn != null) {
			firstBtn.onclick=function(){
				asynRequest(this.id);
			}
		}
		
		if (prevBtn != null) {
			prevBtn.onclick=function(){
				asynRequest(this.id);
			}
		}
		
		if (nextBtn != null) {
			nextBtn.onclick=function(){
				asynRequest(this.id);
			}
		}
		
		if (lastBtn != null) {
			lastBtn.onclick=function(){
				asynRequest(this.id);				
			}
		}


	}
/////////////  showPage func end




// 當使用者按下『第一頁』、『前一頁』、『下一頁』、『最末頁』的連結時，由本方法發出非同步請求。
function asynRequest(id) {
	var xhr = new XMLHttpRequest();
	var no = 0;
    var queryString = "";     		// queryString紀錄查詢字串
	    if (id == "first") {		// 算出查詢字串中，要送出的pageNo為何?
	    	no = 1;
	    } else if (id == "prev") {
	    	no = pageNo - 1;
	    } else if (id == "next") {
	    	no = pageNo + 1;
	    } else if (id == "last") {
	    	no = totalPage;	    	
	    }
	    // 查詢字串包含1.即將要讀取的頁數(pageNo), 2.總共有幾頁(totalPage)
	    // 注意，查詢字串的前面有問號
	    queryString = "&pageNo=" + no + "&totalPage=" + totalPage;
					   //<c:url value='article' />?articleId=${articleId}
// 		xhr.open("GET", "<c:url value='getArticle' />?articleId=${articleId}" + queryString , true);
		xhr.open("GET", "<c:url value='article' />?articleId=${articleId}" + queryString , true);
// 		console.log(no)
// 		console.log(totalPage)
		
		
		xhr.send();
		xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var mapData = xhr.responseText;
			
			showPage(JSON.parse(mapData));
		}
	}
		
}













	});	
</script>



<body>

	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>Blog</h2>
				<ul>
					<li><a href="index.html">home</a></li>
					<li class="active">Blog</li>
				</ul>
			</div>
		</div>
	</div>









<%-- 	<jsp:include page="../public/top.jsp" /> --%>

<%-- 		<h3>${article.getTitle()}</h3> --%>
	<div class="container" style='box-shadow:1px 3px 5px 2px #cccccc;'>
		<ul style="list-style: none; margin: 0px 0;">
			<li style="float: left; margin: 0px 10px 10px 10px;"><a
				href="<c:url value='backArticle' />">
					<button class="submit btn-style" type="submit"
						style="margin-top: 10px;">
						<span style="color: white; margin-top: 0px;">回討論版</span>
					</button>
			</a></li>
		</ul>
		
		<ul style="list-style: none; margin: 0px 0;">
			<li style="float: right; margin: 0px 10px 10px 10px;"><a
				href="<c:url value='replyArticle?articleId=${articleId}' />">
					<button class="submit btn-style" type="submit"
						style="margin-top: 10px;">
						<span style="color: white; margin-top: 0px;">回覆文章</span>
					</button>
			</a></li>
		</ul>		
		
		
			<div id="articleShow" style="padding:70px 0px 0px 0px;">
			</div>
		</div>

		<!-- <table id='articleShow'> -->
		<!-- <thead> -->
		<!-- 	<th>id</th> -->
		<!-- 	<th>content</th> -->
		<!-- </thead> -->
		<!-- <tfoot> -->
		<!-- 	<th>id</th> -->
		<!-- 	<th>content</th> -->
		<!-- </tfoot> -->
		<!-- </table> -->


<!-- 		<div id='navigation' style='height: 60px;'></div> -->


	<div class="pagination-style text-center mt-20">
		<ul id = 'navigation'>
<!-- 			<li><a href="#"><i class="icon-arrow-left"></i></a></li> -->
<!-- 			<li><a href="#">1</a></li> -->
<!-- 			<li><a href="#">2</a></li> -->
<!-- 			<li><a class="active" href="#"><i class="icon-arrow-right"></i></a> -->
<!-- 			</li> -->
		</ul>
	</div>

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>