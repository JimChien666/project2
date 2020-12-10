<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="blog-wrapper mb-30 gray-bg">

	<div class="blog-img hover-effect"
		style="border: 1px solid #BEBEBE; border-bottom: white;">
		<div style="margin-top: 30px; margin-left: 30%; width: 40%;">
			<img alt=""
				src="<c:url value='/member/processFileReadAction.contoller?fileId=${LoginOK.getFileId()}' />"
				style="height: 100px; border-radius: 50%;">

		</div>
		<h4 style="padding: 20px; text-align: center;">${LoginOK.name}</h4>
	</div>
	<div class="blog-content"
		style="padding-top: 0px; padding-left: 60px; border: 1px solid #BEBEBE; background-color: white">
		<div class="shop-widget mt-50">
			<h4 class="shop-sidebar-title">會員管理</h4>
			<div class="shop-list-style mt-20">
				<ul>
					<li><a href="<c:url value='/member/myAccount' />">編輯個人資料</a></li>
					<li><a href="#">修改密碼</a></li>
				</ul>
			</div>
		</div>
		<div class="shop-widget mt-50">
			<h4 class="shop-sidebar-title">訂單管理</h4>
			<div class="shop-list-style mt-20">
				<ul>
					<li><a href="<c:url value='/order/OrderList' />">購買紀錄</a></li>
					<li><a href="<c:url value='/order/SellingOrderList' />">販賣紀錄</a></li>
					<li><a href="<c:url value='/order/orderAnalysis' />">交易數據</a></li>
				</ul>
			</div>
		</div>
		<div class="shop-widget mt-50">
			
			<h4 class="shop-sidebar-title">商品管理</h4>
			<div class="shop-list-style mt-20">
				<ul>
					<li><a href="<c:url value='/product/goLikeProductPage' />">商品最愛</a></li>
					<li><a href="#">瀏覽紀錄</a></li>
				</ul>
			</div>
		</div>
		<div class="shop-widget mt-50">
			<h4 class="shop-sidebar-title">寵物管理</h4>
			<div class="shop-list-style mt-20">
				<ul>
					<li><a href="<c:url value='/MemberCenter/ReadAnimal' />">我的寵物</a></li>
				</ul>
			</div>
		</div>
		<div class="shop-widget mt-50">
			<h4 class="shop-sidebar-title">活動管理</h4>
			<div class="shop-list-style mt-20">
				<ul>
					<li><a href="<c:url value='/activitys/appliedActivity' />">參加的活動</a></li>
					<li><a href="<c:url value='/activitys/myActivity' />">舉辦過的活動</a></li>
				</ul>
			</div>
		</div>
		
	</div>

</div>