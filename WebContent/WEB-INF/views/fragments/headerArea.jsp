<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<header class="header-area">
            <div class="header-bottom transparent-bar">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-2 col-lg-3 col-md-4 col-sm-4 col-5">
                            <div class="logo pt-39">
                                <a href="<c:url value='/' />"><img alt="" src="<c:url value='/assets/img/logo/logo.png' />"></a>
                            </div>
                        </div>
                        <div class="col-xl-8 col-lg-7 d-none d-lg-block">
                            <div class="main-menu text-center">
                                <nav>
                                    <ul>
                                        <li><a href="<c:url value='/' />">HOME</a>
                                            <!-- <ul class="submenu">
                                                <li>
                                                    <a href="index.html">home version 1</a>
                                                </li>
                                                <li>
                                                    <a href="index-2.html">home version 2</a>
                                                </li>
                                            </ul> -->
                                        </li>
                                        <li class="mega-menu-position"><a href="<c:url value='/product/ProductList' />">購物</a>
                                            <ul class="mega-menu" id='ShoppingOptionsArea'>
                                                
                                            </ul>
                                        </li>
                                        <li><a href="<c:url value='/adopt' />">領養</a>
<!--                                             <ul class="submenu"> -->
<!--                                                 <li> -->
<%--                                                     <a href="<c:url value='' />">會員/我的寵物</a> --%>
<!--                                                 </li> -->
<!--                                             </ul> -->
                                        </li>
                                        <li><a href="blog-leftsidebar.html">Blog</a>
                                            <ul class="submenu">
                                                <li>
                                                    <a href="blog.html">blog page</a>
                                                </li>
                                                <li>
                                                    <a href="blog-leftsidebar.html">blog left sidebar</a>
                                                </li>
                                                <li>
                                                    <a href="blog-details.html">blog details</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="about-us.html">ABOUT</a></li>
                                        <li><a href="contact.html">contact us</a></li>
                                        <li><a href="<c:url value='/none' />">會員中心</a>
                                            <ul class="submenu">
                                                <li>
                                                    <a href="<c:url value='/MemberCenter/ReadAnimal' />">我的寵物</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <div class="col-xl-2 col-lg-2 col-md-8 col-sm-8 col-7">
                            <div class="search-login-cart-wrapper">
                                <div class="header-search same-style">
                                    <button class="search-toggle">
                                        <i class="icon-magnifier s-open"></i>
                                        <i class="ti-close s-close"></i>
                                    </button>
                                    <div class="search-content">
                                        <form action="#">
                                            <input type="text" placeholder="Search">
                                            <button>
                                                <i class="icon-magnifier"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                <div class="header-login same-style">
                                    <a href="<c:url value='/member/login' />"><i class="icon-user icons"></i></a>
                                </div>
                                <div class="header-cart same-style">
                                    <button class="icon-cart" onclick="goToCartPage()">
                                        <i class="icon-handbag"></i>
                                        <span class="count-style" id="shopCart"></span>
                                    </button>
                                    
                                </div>
                            </div>
                        </div>
                        <div class="mobile-menu-area electro-menu d-md-block col-md-12 col-lg-12 col-12 d-lg-none d-xl-none">
                            <div class="mobile-menu">
                                <nav id="mobile-menu-active">
                                    <ul class="menu-overflow">
                                        <li><a href="#">HOME</a>
                                            <!-- <ul>
                                                <li><a href="index.html">home version 1</a></li>
                                                <li><a href="index-2.html">home version 2</a></li>
                                            </ul> -->
                                        </li>
                                        <li><a href="#">pages</a>
                                            <ul>
                                                <li>
                                                    <a href="about-us.html">about us</a>
                                                </li>
                                                <li>
                                                    <a href="shop-page.html">shop page</a>
                                                </li>
                                                <li>
                                                    <a href="shop-list.html">shop list</a>
                                                </li>
                                                <li>
                                                    <a href="product-details.html">product details</a>
                                                </li>
                                                <li>
                                                    <a href="cart.html">cart page</a>
                                                </li>
                                                <li>
                                                    <a href="checkout.html">checkout</a>
                                                </li>
                                                <li>
                                                    <a href="wishlist.html">wishlist</a>
                                                </li>
                                                <li>
                                                    <a href="contact.html">contact us</a>
                                                </li>
                                                <li>
                                                    <a href="my-account.html">my account</a>
                                                </li>
                                                <li>
                                                    <a href="login-register.html">login / register</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="#">Food</a>
                                            <ul>
                                                <li><a href="#">Dogs Food</a>
                                                    <ul>
                                                        <li><a href="shop-page.html">Grapes and Raisins</a></li>
                                                        <li><a href="shop-page.html">Carrots</a></li>
                                                        <li><a href="shop-page.html">Peanut Butter</a></li>
                                                        <li><a href="shop-page.html">Salmon fishs</a></li>
                                                        <li><a href="shop-page.html">Eggs</a></li>
                                                    </ul>
                                                </li>
                                                <li><a href="#">Cats Food</a>
                                                    <ul>
                                                        <li><a href="shop-page.html">Meat</a></li>
                                                        <li><a href="shop-page.html">Fish</a></li>
                                                        <li><a href="shop-page.html">Eggs</a></li>
                                                        <li><a href="shop-page.html">Veggies</a></li>
                                                        <li><a href="shop-page.html">Cheese</a></li>
                                                    </ul>
                                                </li>
                                                <li><a href="#">Fishs Food</a>
                                                    <ul>
                                                        <li><a href="shop-page.html">Rice</a></li>
                                                        <li><a href="shop-page.html">Veggies</a></li>
                                                        <li><a href="shop-page.html">Cheese</a></li>
                                                        <li><a href="shop-page.html">wheat bran</a></li>
                                                        <li><a href="shop-page.html">Cultivation</a></li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="#">blog</a>
                                            <ul>
                                                <li>
                                                    <a href="blog.html">blog page</a>
                                                </li>
                                                <li>
                                                    <a href="blog-leftsidebar.html">blog left sidebar</a>
                                                </li>
                                                <li>
                                                    <a href="blog-details.html">blog details</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="contact.html"> Contact us </a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <script src="<c:url value='/js/showHeaderItems.js' />"></script>