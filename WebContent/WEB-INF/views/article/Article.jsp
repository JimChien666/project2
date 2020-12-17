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

<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- Load c3.css -->


<html>
<head>
<style>

.btncls{
	background-color: #7E4C4F; /* Green */
    border: none;
    color: white;
    padding: 10px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 10px;
    transition-duration: 0.3s;
    cursor: pointer;
}
button.btncls:hover{
	background-color: #000000;
}
.greybtn{
	background-color: #8A9199; /* Grey */
    border: none;
    color: white;
    padding: 10px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 10px;
    transition-duration: 0.3s;
    cursor: pointer;
}
button.greybtn:hover{
	background-color: #000000;
}
.autoModal.modal .modal-body{
    max-height: 100%;
}
p img{
/* width: 50%; */
width:50vw;
/* height:50vmin; */
/*     width: auto; */
    height: auto;
}
p{
col-11
}

</style>
<meta charset="UTF-8">
<title><c:out value="${thisArticle.title}" /></title>
<%-- <title>ID:<c:out value="${articleId}" />/<c:out	value="${thisArticle.title}" /></title> --%>
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
		var imageURL = "<c:url value='/getOptionImg' />";
		
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
// 		console.log(forumList)
		$.each(forumList, function(i, forum) {
			console.log(forum);
			if(forum.votetopic != null){
// 				var x = ""
// 				for (i = 0; i < forum.options.length; i++) {
// 						  x += forum.options[i].content	+"/";
// 						}
// 					console.log(String(x))
// 					console.log(forum.id)
// 					forum.id
				var voteBlock = `<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog modal-xl" aria-labelledby="exampleModalLabel" aria-hidden="true"><div class="modal-dialog" role="document"><div class="modal-content"><div class="modal-header"><h5 class="modal-title" id="exampleModalLabel">`+forum.votetopic+`</h5><button type="button" class="close" data-dismiss="modal"aria-label="Close"><span aria-hidden="true">&times;</span></button></div><div class="modal-body"></div><div class="modal-footer"><button type="button" class="greybtn"data-dismiss="modal">取消</button><button type="button" id="voteComfirmBTN" value="`+forum.id+`" data-dismiss="modal" class="btncls" onclick="voteComfirm()">送出</button></div></div></div></div>`
					$("#voteSpace").append(voteBlock);
// 				var voteFoot = ``
				
// 					$("#voteSpace").append(voteFoot);
						$(".modal-body").empty();
					$.each(forum.options, function(k, options){
						$(".modal-body").append("<p style='height:20px;'><input style='width:20px;height:20px;' type='radio'id=radio"+options.id+" name='vote' value="+options.id+"><label for=radio"+options.id+">"+options.content+"</label><img style='width:50px; height:auto;'  src='"+imageURL+"?optionId="+options.id+"' alt=''></p><br><br>")
						})
					$(".modal-body").append("<div style='height:20%; width: 20%' id='chart'></div>")					
// 					$(".modal-body").append("<center><div id='chart'></div></center>")
// 					$(".modal-body").append("<center><div style='height:20%; width: 20%' id='chart'></div></center>")
// 					$(".modal-body").append("<p style='background-color:#FFBB73;' id='chart'></p>")
				}

// .......................................................... 			
					var forumId = ${forumID.id};

	$("#exampleModal").on("shown.bs.modal",function(e){
		  console.log('轉場特效結束，已完全呈現時呼叫');
		  $.ajax({
			  url: "getVoteResult",
			  size: {
			        height: 200,
			        width: 200
			    },
			  data: {
				  forumId:forumId
				  },
			  success:function(result){
				  console.log("result:"+result);
				  console.log(result);
					var chart = c3.generate({
					    bindto: '#chart',		
					    data: {
					        type : 'donut',
					        json: result
					    },
					    donut: {
					        title: "投票結果"
					    }
					});
					
					}
				});
			
		});
					

			
				
			var imgTag = `<img src="<c:url value='/member/processFileReadAction.contoller?fileId=` + forum.forumOwnerFileId + `' />" class="d-inline-block align-top" alt="" style="width:50px; height:50px; border-radius: 50%; border: 2px white solid;">`
// 			$article.append("<tr><td><div style='width:60px; background-color: coral; box-shadow:1px 3px 5px 2px #cccccc;'>"+ imgTag + forum.memberid + "</div></td><td id="+forum.id+"><div style='width:1100px; margin:0px 10px 10px 10px; padding:30px; box-shadow:1px 3px 5px 2px #cccccc;'>" + forum.content
			$article.append("<tr><td><div style='margin:0px 10px 10px 10px;valign=top'>"+ imgTag +"</div></td><td id="+forum.id+"><div style=' width:100%;margin:10px 10px 10px 10px; padding:10px 20px 10px 30px; box-shadow:1px 3px 5px 2px #cccccc;'>" + forum.content + "</div></td></tr>")
// 			$article.append("<tr><td><div style='width:60px;'>"+ imgTag +"</div></td><td id="+forum.id+"><div style='width:1100px; margin:0px 10px 10px 10px; padding:30px; box-shadow:1px 3px 5px 2px #cccccc;'>" + forum.content + "</div></td></tr>")
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
<!--fuck-->
												
												var imgTag2 = `<img src="<c:url value='/member/processFileReadAction.contoller?fileId=` + order.forumOwnerFileId + `' />" class="d-inline-block align-top" alt="" style="width:50px; height:50px; border-radius: 50%;">`
												$forums.append('<div style="margin: 0px 20px 10px 10px; background-color:#fcedda; box-shadow:1px 3px 5px 2px #cccccc; line-height: 50px;">'+imgTag2+'<p>'+order.memberName+":"+order.comment+'</p></div>')
// 												$forums.append('<div style="width:1050px; margin: 0px 20px 10px 10px; background-color:#fcedda; box-shadow:1px 3px 5px 2px #cccccc;">'+order.memberid+':'+order.comment+'</div>')
// 										 		console.log("$forums: "+ $forums)												
												}
											});

										const login = `<a href="<c:url value='/member/login' />">`
										if(${empty LoginOK}){
											$forums.append("<div style='margin: 0px 10px 10px 10px; background-color:#F5F5F5;'>回覆本討論串:"+login+"<input type='text' disabled placeholder='請登入後留言' name='comments' id=reply"+forum.id+"></a></div>")
// 											$forums.append("<div style='margin: 0px 10px 10px 10px; background-color:	#F5F5F5;'>回覆本討論串:"+login+"<input type='text' disabled placeholder='請登入後留言' name='comments' id=reply"+forum.id+"></a></div>")
// 											$forums.append("<div style='width:1100px; margin: 0px 10px 10px 10px; background-color:	#F5F5F5;'>回覆本討論串:"+login+"<input type='text' disabled placeholder='請登入後留言' name='comments' id=reply"+forum.id+"></a></div>")
										}else{											
											$forums.append("<div style='margin: 0px 10px 10px 10px; background-color:#F5F5F5;;'>回覆本討論串:<br/><textarea style='overflow:hidden;'  type='text' name='comments' placeholder='有甚麼想法呢?' id=reply"+forum.id+"></textarea></div>")
// 											$forums.append("<div style='width:1100px; margin: 0px 10px 10px 10px; background-color:#F5F5F5;;'>回覆本討論串:<input type='text' name='comments' placeholder='有甚麼想法呢?' id=reply"+forum.id+"></div>")
											}

// 											$('.auto').autoboxBind();
											$('textarea').autogrow();
											$("#reply"+forum.id).keydown(function (e) {
												// $("input").keypress(function (e) {
// 													console.log("hi hi");
// 													console.log(forumId);
// 													var id = this.forumId;
// 													var comment = this.value;
													var comment = $(this).val().replace(/\n/g, "<br />")
													var commentNoBr = comment.replace(/<br \/>/g,"")													
// 															.val().replace(/\n/g, "<br />")
// 													console.log(id);
// 													console.log(comment);
												if (e.shiftKey && e.keyCode == 13) {
													var event = $(this).val();
// 											        $(this).val(event + '/n' );
// 											        $(this).val(event + '<br>');
												}												
												else if (e.keyCode == 13 && commentNoBr!="") {
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

												  $("textarea").prop("value","");
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
			console.log(pageNo);
			
			showPage(JSON.parse(mapData));
		}
	}
		
}

	});	


	
</script>
<link rel="stylesheet" href="<c:url value='/css/loadingAnimation.css' />">
</head>
<body>
<div id="loader"></div>
<div style="display: none;" id="myDiv" class="animate-bottom">
	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>
					<c:out value="${thisArticle.title}" />
				</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li class="active"><c:out value="${thisArticle.title}" /></li>
				</ul>
			</div>
		</div>
	</div>









	<%-- 	<jsp:include page="../public/top.jsp" /> --%>

	<%-- 		<h3>${article.getTitle()}</h3> --%>
	<div class="container" style='box-shadow: 1px 3px 5px 2px #cccccc;'>
		<ul style="list-style: none; margin: 0px 0;">
			<li style="float: left; margin: 0px 10px 30px 10px;"><a
				href="<c:url value='/backArticle' />">
					<button class="submit btn-style" type="submit"
						style="margin-top: 10px;">
						<span style="color: white; margin-top: 0px;">回討論版</span>
					</button>
			</a></li>
		</ul>


		<ul style="list-style: none; margin: 0px 0;">
			<li style="float: right; margin: 0px 10px 30px 10px;"><a
				href="<c:url value='/replyArticle/${articleId}' />">
					<button class="submit btn-style" type="submit"
						style="margin-top: 10px;">
						<span style="color: white; margin-top: 0px;">回覆文章</span>
					</button>
			</a></li>
		</ul>
		
		
		
		<c:if test="${LoginOK.id!=null&&forumID.votetopic!=null}">
			<ul style="list-style: none; margin: 0px 0;">
				<li style="float: right; margin: 0px 10px 30px 10px;">
					<button class="submit btn-style" style="margin-top: 10px;" id="toVoteBtn"
						data-toggle='modal' data-target="#exampleModal">
						<span style="color: white; margin-top: 0px;">進行投票</span>
					</button>
				</li>
			</ul>
		</c:if>


		<c:if test="${LoginOK.id==thisArticle.memberid}">
			<ul style="list-style: none; margin: 0px 0;">
				<li style="float: right; margin: 0px 10px 30px 10px;"><a
					href="<c:url value='/updateArticle?articleId=${articleId}' />">
						<button class="submit btn-style" type="submit"
							style="margin-top: 10px;">
							<span style="color: white; margin-top: 0px;">修改</span>
						</button>
				</a></li>
			</ul>
			<ul style="list-style: none; margin: 0px 0;">
				<li style="float: right; margin: 0px 10px 30px 10px;">
						<button class="submit btn-style" type="submit" id='deleteArticle'
							style="margin-top: 10px;">
							<span style="color: white; margin-top: 0px;">刪除</span>
						</button>
				</a></li>
			</ul>

		</c:if>
<script>



$("#deleteArticle").on("click",	function() {

	swal({
		title: "確認刪除文章?",
		icon: "warning",
		buttons: {
			Btn: false,
			cancel: { text: "取消", visible: true },
			danger: { text: "刪除", visible: true }
		},
		dangerMode: true
	}).then((value) => {
		switch (value) {
			case "danger":
				swal("提示", "刪除", "success");
				setTimeout(
					function() {
						window.location = "deleteArticle?articleId=" + ${articleId};  
					}
					, 2000
				);

				//				document.forms[0].action="DeleteAnimal.controller?animalId="+animalId;
				//				document.forms[0].method="POST";
				//				document.forms[0].submit();//submit is not a function可能是因為有按鈕的name也叫submit
				break;
		}
	});
				})




































					
</script>

		<div id="articleShow" style="padding: 70px 0px 0px 0px;"></div>
	</div>



	<div class="pagination-style text-center mt-20">
		<ul id='navigation'>

		</ul>
	</div>

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />

	<div id="voteSpace"></div>
	
	<script>
	function voteComfirm() {
		console.log("hihi")
		var optionid = $('input[name="vote"]:checked').val();
		var forumId = $("#voteComfirmBTN").attr("value");
		console.log(forumId)
		console.log(optionid)
		if(typeof(optionid)!= "undefined"){

		$.ajax({
			  url: "voteConfirm",
			  data: {
				  optionid:optionid,
				  forumId:forumId
				  },
			  success:function(result){
				  console.log(result);

					if (result) {						
						swal({
							  title: "完成!",
							  icon: "success",
							  button: "確認",
							});
					}else {						
					swal({
						  title: "投過了!",
						  text: "你投過了唷!",
						  icon: "error",
						  button: "確認",
						});	
					}
				  
//					  reset();
//		 			showPage(mapData);
//					  var $comments = $('.${Forums.id}');
//						console.log($comments);
// 				console.log(optionid)
// 				$('#toVoteBtn').attr('disabled', true);
					}
					//  dataType: dataType
			});
		}
		console.log("byebye")		
	}








	

	</script>

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
</html>