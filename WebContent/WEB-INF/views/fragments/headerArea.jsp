<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!--     <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script> -->
<!--     <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script> -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.js"></script>
<header class="header-area">
<style>
.orderClass{
margin: 20px;width:200px;height: 60px; right:5%; position: fixed; z-index: 999;background-color: white;line-height:60px;text-align: center;cursor: pointer;
}
</style>
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />" ></script>
<div id="search-content">
</div>
	<div class="header-bottom transparent-bar">
		<div class="container">
			<div class="row">
				<div class="col-xl-2 col-lg-3 col-md-4 col-sm-4 col-5">
					<div class="logo pt-39">
						<a href="<c:url value='/' />"><img alt=""
							src="<c:url value='/assets/img/logo/logo.png' />"></a>
					</div>
				</div>
				<div class="col-xl-8 col-lg-7 d-none d-lg-block">
					<div class="main-menu text-center">
						<nav>
							<ul>
								<li class="mega-menu-position" id="homeLi"><a href="<c:url value='/' />" style="font-size: 22px;"><img
										src="<c:url value='/images/home.svg' />" id="homeIcon"
										style="height: 35px; width: 35px;">首頁</a>
									<!-- <ul class="submenu">
                                                <li>
                                                    <a href="index.html">home version 1</a>
                                                </li>
                                                <li>
                                                    <a href="index-2.html">home version 2</a>
                                                </li>
                                            </ul> --></li>
								<li class="mega-menu-position" id="adoptLi"><a
									href="<c:url value='/adopt' />" style="font-size: 22px;"><img
										src="<c:url value='/images/cat.png' />" id="adoptIcon"
										style="height: 35px; width: 35px;">領養</a></li>


								<li class="mega-menu-position" id="shopLi"><a
									href="<c:url value='/product/ProductList' />" style="font-size: 22px;">
									<img src="<c:url value='/images/shop.png' />" id="shopIcon" style="height:35px;width:35px;"> 購物</a>
									<ul class="mega-menu" id='ShoppingOptionsArea'>  
									</ul>
								</li>
								<li class="mega-menu-position" id="articleLi"><a href="<c:url value='/backArticle' />" style="font-size: 22px;"><img
										src="<c:url value='/images/notepad.svg' />" id="articleIcon"
										style="height: 35px; width: 35px;">討論區</a>
									</li>
								<li class="mega-menu-position" id="activityLi"><a href="<c:url value='/activitys/list' />" style="font-size: 22px;"><img
										src="<c:url value='/images/activism.svg' />" id="activityIcon"
										style="height: 35px; width: 35px;">活動</a>
									</li>

							</ul>
						</nav>
					</div>
				</div>
				<div class="col-xl-2 col-lg-2 col-md-8 col-sm-8 col-7">
					<div class="search-login-cart-wrapper">
						<div class="header-search same-style">
							<button class="search-toggle">
								<i class="icon-magnifier s-open"></i> <i
									class="ti-close s-close"></i>
							</button>
<!-- 							<div id="search-content" class="search-content open"style="width: 100px;border: 1px black solid;"> -->
<!-- 								<div>您有一筆新訂單</div> -->
<!-- 							</div> -->
						</div>
						<%-- <div class="header-login same-style">
                                    <a href="<c:url value='/member/login' />"><i class="icon-user icons"></i></a>
                                </div> --%>
						<div class="header-login same-style">
							<button class="icon-cart" id="fbname" data-toggle="tooltip"
								data-placement="bottom" title="會員">
								<i class="icon-user icons"></i>
								<c:if test="${!empty LoginOK}">
									<span class="count-style">${LoginOK.name.substring(0, 1)}</span>
								</c:if>
							</button>
							<div class="shopping-cart-content"
								style="width: 200px; padding-top: 30px;">
								<ul id="fbstatus">
									<c:if test="${!empty LoginOK}">
										<li><a href="<c:url value='/member/myAccount' />">會員中心</a>
										</li>


										<li><a href="<c:url value='/member/logout' />">登出</a></li>
									</c:if>
									<c:if test="${empty LoginOK}">
										<li><a href="<c:url value='/member/login' />">登入</a></li>
										<li><a href="<c:url value='/member/register' />">註冊</a></li>
									</c:if>
								</ul>

							</div>


						</div>
						<div class="header-cart same-style">
							<button class="icon-cart" onclick="goToCartPage()"
								data-toggle="tooltip" data-placement="bottom" title="購物車">
								<i class="icon-handbag"></i> <span class="count-style"
									id="shopCart"></span>
							</button>

						</div>
					</div>
				</div>
				<div
					class="mobile-menu-area electro-menu d-md-block col-md-12 col-lg-12 col-12 d-lg-none d-xl-none">
					<div class="mobile-menu">
						<nav id="mobile-menu-active">
							<ul class="menu-overflow">
								<li><a href="<c:url value='/'/>">首頁</a> <!-- <ul>
                                                <li><a href="index.html">home version 1</a></li>
                                                <li><a href="index-2.html">home version 2</a></li>
                                            </ul> --></li>
								<li><a href="<c:url value='/adopt'/>">領養動物</a> <!--<ul> -->
									<!--	<li><a href="about-us.html">about us</a></li> --> <!--	<li><a href="shop-page.html">shop page</a></li> -->
									<!--	<li><a href="shop-list.html">shop list</a></li> --> <!--	<li><a href="product-details.html">product details</a></li> -->
									<!--	<li><a href="cart.html">cart page</a></li> --> <!--	<li><a href="checkout.html">checkout</a></li> -->
									<!--	<li><a href="wishlist.html">wishlist</a></li> --> <!--	<li><a href="contact.html">contact us</a></li> -->
									<!--	<li><a href="my-account.html">my account</a></li> --> <!--	<li><a href="login-register.html">login / register</a></li> -->
									<!--</ul> --></li>
								<li><a href="#">Food</a>
									<ul>
										<li><a href="#">Dogs Food</a>
											<ul>
												<li><a href="shop-page.html">Grapes and Raisins</a></li>
												<li><a href="shop-page.html">Carrots</a></li>
												<li><a href="shop-page.html">Peanut Butter</a></li>
												<li><a href="shop-page.html">Salmon fishs</a></li>
												<li><a href="shop-page.html">Eggs</a></li>
											</ul></li>
										<li><a href="#">Cats Food</a>
											<ul>
												<li><a href="shop-page.html">Meat</a></li>
												<li><a href="shop-page.html">Fish</a></li>
												<li><a href="shop-page.html">Eggs</a></li>
												<li><a href="shop-page.html">Veggies</a></li>
												<li><a href="shop-page.html">Cheese</a></li>
											</ul></li>
										<li><a href="#">Fishs Food</a>
											<ul>
												<li><a href="shop-page.html">Rice</a></li>
												<li><a href="shop-page.html">Veggies</a></li>
												<li><a href="shop-page.html">Cheese</a></li>
												<li><a href="shop-page.html">wheat bran</a></li>
												<li><a href="shop-page.html">Cultivation</a></li>
											</ul></li>
									</ul></li>
								<li><a href="<c:url value='/backArticle'/>">討論區</a>
									<ul>
										<li><a href="blog.html">blog page</a></li>
										<li><a href="blog-leftsidebar.html">blog left sidebar</a>
										</li>
										<li><a href="blog-details.html">blog details</a></li>
									</ul></li>
								<li><a href="contact.html"> Contact us </a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
</header>
<jsp:include page="showHeaderItems.jsp" />

<script>
	function goindex() {
		window.location.href = "<c:url value='/' />";
	}

	$("#adoptLi").mouseover(function() {
		$("#adoptIcon").attr("src", "<c:url value='/images/cat_brown.png' />");
	})

	$("#adoptLi").mouseout(function() {
		$("#adoptIcon").attr("src", "<c:url value='/images/cat.png' />");
	})

	
	$("#shopLi").mouseover(function(){
		$("#shopIcon").attr("src", "<c:url value='/images/shop_brown.png' />");
		})
	
	$("#shopLi").mouseout(function(){
		$("#shopIcon").attr("src", "<c:url value='/images/shop.png' />");
		})
		
	$("#articleLi").mouseover(function(){
		$("#articleIcon").attr("src", "<c:url value='/images/notepadCHG.svg' />");
		})
	
	$("#articleLi").mouseout(function(){
		$("#articleIcon").attr("src", "<c:url value='/images/notepad.svg' />");
		})
		
	$("#activityLi").mouseover(function(){
		$("#activityIcon").attr("src", "<c:url value='/images/activismCHG.svg' />");
		})
	
	$("#activityLi").mouseout(function(){
		$("#activityIcon").attr("src", "<c:url value='/images/activism.svg' />");
		})
	$("#homeLi").mouseover(function(){
		$("#homeIcon").attr("src", "<c:url value='/images/homeCHG.svg' />");
		})
	
	$("#homeLi").mouseout(function(){
		$("#homeIcon").attr("src", "<c:url value='/images/home.svg' />");
		})

</script>

<script type="text/javascript" src="<c:url value='/js/websocket.js' />" ></script>