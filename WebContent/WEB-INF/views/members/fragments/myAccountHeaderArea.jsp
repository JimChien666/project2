<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<div class="header-top theme-bg">
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-12">
				<div class="welcome-area">
					<p><a href="<c:url value='/member/myAccount' />" style="color: white;">會員中心</a></p>
				</div>
			</div>
			<div class="col-lg-8 col-md-8 col-12">
				<div class="account-curr-lang-wrap f-right">
					<ul>
						<li class="top-hover"><a href="#">訂單管理 <i
								class="icon-arrow-down"></i></a>
							<ul>
								<li><a href="<c:url value='/order/OrderList' />">購買紀錄</a></li>
								<li><a href="<c:url value='/order/SellingOrderList' />">販售紀錄</a></li>
								<li><a href="<c:url value='/order/orderAnalysis' />">交易數據</a></li>
							</ul></li>
						<li><a href="#"> 商品管理 <i class="icon-arrow-down"></i></a>
							<ul>
								<li><a href="<c:url value='/product/goLikeProductPage' />">商品最愛</a></li>
							</ul></li>
						<li><a href="#"> 寵物管理 <i class="icon-arrow-down"></i></a>
							<ul>
								<li><a href="<c:url value='/MemberCenter/ReadAnimal' />">我的寵物</a></li>

							</ul></li>
						<li><a href="#"> 活動管理 <i class="icon-arrow-down"></i></a>
							<ul>
								<li><a href="<c:url value='/activitys/appliedActivity' />">參加的活動</a></li>
								<li><a href="<c:url value='/activitys/myActivity' />">舉辦過的活動</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
