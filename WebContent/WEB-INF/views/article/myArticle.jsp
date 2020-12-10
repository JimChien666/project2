<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<c:url value='/assets/js/vendor/jquery-1.12.0.min.js' />"></script>
<script>
	$(function() {

		$.ajax({
			type : "GET",
			url : "<c:url value='/getArticleList' />?articleTypeId=1",
			success : function(mapData) {
				showArticleList(mapData)
			}
		});

		$("#forumsSelect1>a").on(
				"click",
				function() {
					selectResult = $(this).attr("id")
					console.log(selectResult)
					$.ajax({
						type : "GET",
						url : "<c:url value='/getArticleList' />?articleTypeId="
								+ selectResult,
						success : function(mapData) {
							showArticleList(mapData)
						}
					});
				})

		$("#forumsSelect2>a").on(
				"click",
				function() {
					selectResult = $(this).attr("id")
					console.log(selectResult)
					$.ajax({
						type : "GET",
						url : "<c:url value='/getArticleList' />?articleTypeId="
								+ selectResult,
						success : function(mapData) {
							showArticleList(mapData)
						}
					});
				})

		function showArticleList(mapData) {
// 			var $artilceList = $("#artilceList")
			var $artilceListTable = $("#artilceListTable")
			articleList = mapData.articleList;
			statusList = mapData.statusList;
			console.log(statusList);
			pageNo = mapData.currPage;
			totalPage = mapData.totalPage;
			recordCounts = mapData.recordCounts;
			// 		$artilceList.empty();
			$artilceListTable.empty();
			// 		$artilceList.append("<table border=1 style='width: 100%; font-size: 2em;' ><tbody>")

			$artilceListTable.append("<tr><th>文章標題</th><th>追蹤</th></tr>")
			
			
			
			
			$.each(articleList, function(i, article) {
				var urlStringent = "<c:url value='goArticlePage?articleId="
						+ article.id + "' />";
				var checkStatus = false;
				
				$.each(statusList, function(j, status){
				if(status.articleid==article.id && status.status==1){
					checkStatus = true;
					}
 					});
				
				$artilceListTable
						.append("<tr><td><a href="+urlStringent+"><div>"
								+ article.title + "</div></a></td><td class='product-wishlist-cart' id='forumsSelect1'><a onclick='follow(this)' style='color: white; cursor: pointer;' id='"+article.id+"'>"+(checkStatus? "取消追蹤":"追蹤")+"</a></td></tr>")
			})

// 			var navContent = "" ;
// 			if (pageNo != 1){
// 				navContent += "<li><a id='first'><i class='icon-arrow-left'></i></a></li>";
// 				navContent += "<li><a id='prev'>" + (parseInt(pageNo) - 1 ) + "</a></li>";
// 			}  else {
// 				navContent += "<li>&nbsp;</li>";
// 				navContent += "<li>&nbsp;</li>";
// 			}
// 			navContent += "<li><a class='active' href='#'>" + (parseInt(pageNo)) + "</a></li>";
// 			if (pageNo != totalPage){
// 				navContent += "<li><a id='next'>" + (parseInt(pageNo) + 1 ) + "</a></li>";
// 				navContent += "<li><a id='last'><i class='icon-arrow-right'></i></a></li>";
// 			}  else {
// 				navContent += "<td align='center'>&nbsp;</td>";
// 				navContent += "<td align='center'>&nbsp;</td>";
// 			}

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
			var prevBtn = document.getElementById("prev");
			var nextBtn = document.getElementById("next");
			var lastBtn = document.getElementById("last");
			if (firstBtn != null) {
				firstBtn.onclick = function() {
					asynRequest(this.id);
				}
			}

			if (prevBtn != null) {
				prevBtn.onclick = function() {
					asynRequest(this.id);
				}
			}

			if (nextBtn != null) {
				nextBtn.onclick = function() {
					asynRequest(this.id);
				}
			}

			if (lastBtn != null) {
				lastBtn.onclick = function() {
					asynRequest(this.id);
				}
			}

		}

		//當使用者按下『第一頁』、『前一頁』、『下一頁』、『最末頁』的連結時，由本方法發出非同步請求。
		function asynRequest(id) {
			var xhr = new XMLHttpRequest();
			var no = 0;
			var queryString = ""; // queryString紀錄查詢字串
			if (id == "first") { // 算出查詢字串中，要送出的pageNo為何?
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
			if (typeof selectResult == 'undefined') {
				xhr.open("GET", "<c:url value='/getArticleList' />?articleTypeId="
						+ "1" + queryString, true);
				// 		xhr.open("GET", "<c:url value='getArticleList' />?articleTypeId=1" + queryString , true);
				// 		console.log(no)
				// 		console.log(totalPage)

				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var mapData = xhr.responseText;
						console.log(pageNo);
						showArticleList(JSON.parse(mapData));
						// 			showArticleList(mapData);
					}
				}
			}
	
			xhr.open("GET", "<c:url value='/getArticleList' />?articleTypeId="
					+ selectResult + queryString, true);
			// 		xhr.open("GET", "<c:url value='getArticleList' />?articleTypeId=1" + queryString , true);
			// 		console.log(no)
			// 		console.log(totalPage)

			xhr.send();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var mapData = xhr.responseText;

					showArticleList(JSON.parse(mapData));
					// 			showArticleList(mapData);
				}
			}
		}		
	});
	function follow(Obj){

		
		console.log(Obj.id)
// 		console.log(${LoginOK.id})
		var memberId = "${LoginOK.id}"
		
		if (memberId!="") {
			swal({
				  title: "完成!",
				  icon: "success",
				  button: "確認",
				});

		var member = "${LoginOK.id}"
		var	articleid = Obj.id
		$.ajax({
			type : "GET",
			url : "<c:url value='statusChange' />?memberid="+member+"&articleid="+articleid,
			success :function(result){
				console.log("success get result")
			if(result==1){
				console.log("新增成功");
				console.log($("#"+articleid).text);
				
				$("#"+articleid).text("取消追蹤");
				
			} else {
				console.log("取消追蹤");
				console.log($("#"+articleid).text);
				console.log(result);
				$("#"+articleid).text("追蹤");
				}
			}
		});
			
		}else {
			swal({
				  title: "未登入!",
				  text: "請登入後再進行操作",
				  icon: "error",
				  button: "確認",
				});	
		}
	}
</script>
</head>
<jsp:include page="../fragments/links.jsp" />
<body>
	<jsp:include page="../fragments/headerArea.jsp" />
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>會員編輯</h2>
				<ul>
					<li><a href="<c:url value='/' />">首頁</a></li>
					<li><a href="<c:url value='/member/myAccount'/>">會員中心</a></li>
					<li class="active">會員編輯</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />


	<div class="container">
		<div class="row flex-row-reverse">
			<div class="col-lg-12 col-md-8"">
				<div class="row" style="margin-top: 10px;">
					<div class="col-lg-3 col-md-12">
						<jsp:include page="../members/fragments/myAccountLeftArea.jsp" />
					</div>
					<div class="col-lg-9 col-md-12">
						<!-- 放 -->
						<div class="container">
							<ul style="list-style: none; margin: 20px 0;">
								<li style="float: left; margin: 10px 10px 15px 10px;">
									<table>
										<tr>
											<td class="product-wishlist-cart" id="forumsSelect1"><a
												style='color: white; cursor: pointer;' id="1">狗狗討論版</a></td>
										</tr>
									</table>
								</li>
							</ul>
							<ul style="list-style: none; margin: 20px 0;">
								<li style="float: left; margin: 10px 10px 15px 10px;">

									<table>
										<tr>
											<td class="product-wishlist-cart" id="forumsSelect2"><a
												style='color: white; cursor: pointer;' id="2">貓咪討論版</a></td>
										</tr>
									</table>

								</li>
							</ul>


							<ul style="list-style: none; margin: 0px 0; clear: both;">
								<li style="float: left; margin: 0px 10px 10px 10px;"><a
									href="<c:url value='saveArticle' />">
										<button class="submit btn-style" type="submit"
											style="margin-top: 10px;">
											<span style="color: white; margin-top: 0px;">發文</span>
										</button>
								</a></li>
							</ul>


							<!-- 	<div id="forumsSelect1"> -->
							<!-- 		<button value="1" id="dog">狗</button> -->
							<!-- 	</div> -->
							<!-- 	<div id="forumsSelect2"> -->
							<!-- 		<button value="2" id="cat">貓</button>	 -->
							<!-- 	</div> -->

							<!-- 
	<ul style="list-style: none; margin: 10px 0; clear:both;">
		<c:forEach items="${allArticleTypes}" var="ArticleType" varStatus="id">
				<a href="<c:url value='articleList?articletypesId=${ArticleType.getId()}' />" >
			<li style=" margin: 10px 10px 10px 10px; border-radius:25%; padding: 2px 3px; width: 9.09%; box-sizing: border-box; float: left; text-align: center;"  class="forumC">
					
					<c:if test="${ArticleType.getId()=='1'}">
						<img src="https://image.flaticon.com/icons/png/512/194/194279.png" style="height: 20px; margin-bottom: 0px;">
					</c:if> 
					<c:if test="${ArticleType.getId()=='2'}">
						<img src="https://cdn4.iconfinder.com/data/icons/animal-3/100/animal-08-512.png" style="height: 20px; margin-bottom: 0px;">
					</c:if> 
					${ArticleType.getArticletype()}${Articletype.getId()}				
			</li>
				</a>
		</c:forEach>
	</ul>
 -->

							<div style="clear: both; width: 100%;">
								<!-- 下面要放文章 -->
								<!-- 		<div id="artilceList"> -->
								<table id="artilceListTable" border=1
									style='width: 100%; font-size: 2em;'>
								</table>
								<!-- 		</div> -->
							</div>
						</div>
						<div class="pagination-style text-center mt-20">
							<ul id='navigation'>
								<!-- 			<li><a href="#"><i class="icon-arrow-left"></i></a></li> -->
								<!-- 			<li><a href="#">1</a></li> -->
								<!-- 			<li><a href="#">2</a></li> -->
								<!-- 			<li><a class="active" href="#"><i class="icon-arrow-right"></i></a> -->
								<!-- 			</li> -->
							</ul>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>


	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />

</body>
</html>
