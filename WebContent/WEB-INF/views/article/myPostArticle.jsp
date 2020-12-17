<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<html>
<head>
<meta charset="UTF-8">
<style>

tr:hover {
	background-color:#FCE6C9;
}
table th{
	background-color:#FDEEDB;
}


</style>
<title>我的文章</title>
<script src="<c:url value='/assets/js/vendor/jquery-1.12.0.min.js' />"></script>
<script>
	$(function() {

		$.ajax({
			type : "GET",
			url : "<c:url value='/getPersonalPostList' />?articleTypeId=1",
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
						url : "<c:url value='/getPersonalPostList' />?articleTypeId="
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
						url : "<c:url value='/getPersonalPostList' />?articleTypeId="
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

			recordCounts = mapData.recordCounts;
			// 		$artilceList.empty();
			$artilceListTable.empty();
			// 		$artilceList.append("<table border=1 style='width: 100%; font-size: 2em;' ><tbody>")

			$artilceListTable.append("<tr><th>文章標題</th></tr>")
			
			$.each(articleList, function(i, article) {
				var urlStringent = "<c:url value='goArticlePage?articleId="	+ article.id + "' />";
				
				$artilceListTable
// 						.append("<tr><td align='center'  width='100px'>"+popularity+"</td><td><a href="+urlStringent+"><div>"  
// 						+ (article.title.length>14 ? article.title.slice(0,13)+'..' : article.title) +"</div></a></td><td width='110px' class='product-wishlist-cart' id='forumsSelect1'><a onclick='follow(this)' style='color: white; cursor: pointer;' id='follow"+article.id+"'>"+(checkStatus? "取消追蹤":"追蹤")+"</a></td></tr>")
						.append("<tr id='tr"+article.id+"'><td><a href="+urlStringent+"><div>"
								+ article.title + "</div></a></td></tr>")
			});
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
			url : "<c:url value='/statusChange' />?memberid="+member+"&articleid="+articleid,
			success :function(result){
				console.log("success get result")

				console.log("新增成功");
				console.log($("#"+articleid).text);
				console.log(result);
				$("#tr"+articleid).remove();
				
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



							<div style="clear: both; width: 100%;">
								<!-- 下面要放文章 -->
								<!-- 		<div id="artilceList"> -->
								<table id="artilceListTable" 
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
