<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Marten - Pet Food eCommerce Bootstrap4 Template</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <jsp:include page="fragments/links.jsp" />
        <jsp:include page="fragments/getIndexData.jsp" />
    </head>
    <body>
    	<!-- headers -->
        <jsp:include page="fragments/headerArea.jsp" />
        
        <!-- 輪播 -->
        <div class="slider-area">
            <div class="slider-active owl-dot-style owl-carousel">
                <div class="single-slider pt-100 pb-100 yellow-bg">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-12 col-sm-7">
                                <div class="slider-content slider-animated-1 pt-114">
                                    <h3 class="animated">We keep pets for pleasure.</h3>
                                    <h1 class="animated">Food & Vitamins <br>For all Pets</h1>
                                    <div class="slider-btn">
                                        <a class="animated" href="product-details.html">SHOP NOW</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-12 col-sm-5">
                                <div class="slider-single-img slider-animated-1">
                                    <img class="animated" src="assets/img/slider/slider-single-img.png" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="single-slider pt-100 pb-100 yellow-bg">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-7 col-12">
                                <div class="slider-content slider-animated-1 pt-114">
                                    <h3 class="animated">We keep pets for pleasure.</h3>
                                    <h1 class="animated">Food & Vitamins <br>For all Pets</h1>
                                    <div class="slider-btn">
                                        <a class="animated" href="product-details.html">SHOP NOW</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-5 col-12">
                                <div class="slider-single-img slider-animated-1">
                                    <img class="animated" src="assets/img/slider/slider-single-img-2.png" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- part1 -->
        <div class="food-category food-category-col pt-100 pb-60">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-4">
                        <div class="single-food-category cate-padding-1 text-center mb-30">
                            <div class="single-food-hover-2">
                                <img src="assets/img/product/product-1.jpg" alt="">
                            </div>
                            <div class="single-food-content">
                                <h3>Dogs Food</h3>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="single-food-category cate-padding-2 text-center mb-30">
                            <div class="single-food-hover-2">
                                <img src="assets/img/product/product-2.jpg" alt="">
                            </div>
                            <div class="single-food-content">
                                <h3>Cats Food</h3>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="single-food-category cate-padding-3 text-center mb-30">
                            <div class="single-food-hover-2">
                                <img src="assets/img/product/product-3.jpg" alt="">
                            </div>
                            <div class="single-food-content">
                                <h3>Fishs Food</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
		<!-- part2 -->        
        <div class="product-area pt-95 pb-70 gray-bg">
            <div class="container">
                <div class="section-title text-center mb-55">
                    <h5>精選商品</h5>
                    <h2>為您推薦</h2>
                </div>
                <div class="row" id="productList">

                    
                </div>
            </div>
        </div>
        
        
        <!-- part3 -->
        <div class="deal-area bg-img pt-95 pb-100">
            <div class="container">
                <div class="section-title text-center mb-50">
                    <h4>Best Product</h4>
                    <h2>Deal of the Week</h2>
                </div>
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="deal-img wow fadeInLeft">
                            <a href="#"><img src="assets/img/banner/banner-2.png" alt=""></a>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="deal-content">
                            <h3><a href="#">Name Your Product</a></h3>
                            <div class="deal-pro-price">
                                <span class="deal-old-price">$16.00 </span>
                                <span> $10.00</span>
                            </div>
                            <p>Lorem ipsum dolor sit amet, co adipisicing elit, sed do eiusmod tempor incididunt labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo </p>
                            <div class="timer timer-style">
                                <div data-countdown="2017/10/01"></div>
                            </div>
                            <div class="discount-btn mt-35">
                                <a class="btn-style" href="#">SHOP NOW</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- part4 -->
        
		<div class="testimonial-area pt-90 pb-70 bg-img" style="background-image:url(assets/img/banner/banner-1.jpg);">
		    <div class="container">
                <div class="row">
                    <div class="col-lg-10 ml-auto mr-auto">
                        <div class="testimonial-wrap">
                            <div class="testimonial-text-slider text-center">
                                <div class="sin-testiText">
                                    <p>Lorem ipsum dolor sit amet, co adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo</p>
                                </div>
                                <div class="sin-testiText">
                                    <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or amro porano ja cai tomi tai go amro porano  amro porano ja cai tomi tai go  .... </p>
                                </div>
                                <div class="sin-testiText">
                                    <p>Lorem ipsum dolor sit amet, co adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo</p>
                                </div>
                                <div class="sin-testiText">
                                    <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or amro porano ja cai tomi tai go amro porano  amro porano ja cai tomi tai go  .... </p>
                                </div>
                            </div>
                            <div class="testimonial-image-slider text-center">
                                <div class="sin-testiImage">
                                    <img src="assets/img/testi/3.jpg" alt="">
                                    <h3>Robiul Islam</h3>
                                    <h5>Customer</h5>
                                </div>
                                <div class="sin-testiImage">
                                    <img src="assets/img/testi/4.jpg" alt="">
                                    <h3>Robiul Islam</h3>
                                    <h5>Customer</h5>
                                </div>
                                <div class="sin-testiImage">
                                    <img src="assets/img/testi/3.jpg" alt="">
                                    <h3>F. H. Shuvo</h3>
                                    <h5>Developer</h5>
                                </div>
                                <div class="sin-testiImage">
                                    <img src="assets/img/testi/5.jpg" alt="">
                                    <h3>T. T. Rayed</h3>
                                    <h5>CEO</h5>
                                </div>
                            </div>
                            <div class="testimonial-shap">
                                <img src="assets/img/icon-img/testi-shap.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		</div>
		
		<!-- part5 -->
		
		<div class="service-area bg-img pt-100 pb-65">
		    <div class="container">
		        <div class="row">
		            <div class="col-lg-4 col-md-4">
		                <div class="service-content text-center mb-30 service-color-1">
		                    <img src="assets/img/icon-img/shipping.png" alt="">
		                    <h4>FREE SHIPPING</h4>
		                    <p>Free shipping on all order </p>
		                </div>
		            </div>
		            <div class="col-lg-4 col-md-4">
		                <div class="service-content text-center mb-30 service-color-2">
		                    <img src="assets/img/icon-img/support.png" alt="">
		                    <h4>ONLINE SUPPORT</h4>
		                    <p>Online support 24 hours a day</p>
		                </div>
		            </div>
		            <div class="col-lg-4 col-md-4">
		                <div class="service-content text-center mb-30 service-color-3">
		                    <img src="assets/img/icon-img/money.png" alt="">
		                    <h4>MONEY RETURN</h4>
		                    <p>Back guarantee under 5 days</p>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		<!-- part6 -->
		
		<div class="blog-area pb-70">
		    <div class="container">
		        <div class="section-title text-center mb-60">
                    <h4>Latest News</h4>
                    <h2>From Our Blog</h2>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <div class="blog-wrapper mb-30 gray-bg">
                            <div class="blog-img hover-effect">
                                <a href="blog-details.html"><img alt="" src="assets/img/blog/blog-1.jpg"></a>
                            </div>
                            <div class="blog-content">
                                <div class="blog-meta">
                                    <ul>
                                        <li>By: <span>Admin</span></li>
                                        <li>Sep 14, 2018</li>
                                    </ul>
                                </div>
                                <h4><a href="blog-details.html">Lorem ipsum dolor amet cons adipisicing elit</a></h4>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="blog-wrapper mb-30 gray-bg">
                            <div class="blog-img hover-effect">
                                <a href="blog-details.html"><img alt="" src="assets/img/blog/blog-2.jpg"></a>
                            </div>
                            <div class="blog-content">
                                <div class="blog-meta">
                                    <ul>
                                        <li>By: <span>Admin</span></li>
                                        <li>Sep 14, 2018</li>
                                    </ul>
                                </div>
                                <h4><a href="blog-details.html">Lorem ipsum dolor amet cons adipisicing elit</a></h4>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="blog-wrapper mb-30 gray-bg">
                            <div class="blog-img hover-effect">
                                <a href="blog-details.html"><img alt="" src="assets/img/blog/blog-3.jpg"></a>
                            </div>
                            <div class="blog-content">
                                <div class="blog-meta">
                                    <ul>
                                        <li>By: <span>Admin</span></li>
                                        <li>Sep 14, 2018</li>
                                    </ul>
                                </div>
                                <h4><a href="blog-details.html">Lorem ipsum dolor amet cons adipisicing elit</a></h4>
                            </div>
                        </div>
                    </div>
                </div>
		    </div>
		</div>
		<jsp:include page="fragments/footerArea.jsp" />
		<!-- modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span class="ti-close" aria-hidden="true"></span>
            </button>
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="qwick-view-left">
                            <div class="quick-view-learg-img">
                                <div class="quick-view-tab-content tab-content">
                                    <div class="tab-pane active show fade" id="modal1" role="tabpanel">
                                        <img src="assets/img/quick-view/l1.jpg" alt="">
                                    </div>
                                    <div class="tab-pane fade" id="modal2" role="tabpanel">
                                        <img src="assets/img/quick-view/l2.jpg" alt="">
                                    </div>
                                    <div class="tab-pane fade" id="modal3" role="tabpanel">
                                        <img src="assets/img/quick-view/l3.jpg" alt="">
                                    </div>
                                </div>
                            </div>
                            <div class="quick-view-list nav" role="tablist">
                                <a class="active" href="#modal1" data-toggle="tab">
                                    <img src="assets/img/quick-view/s1.jpg" alt="">
                                </a>
                                <a href="#modal2" data-toggle="tab" role="tab">
                                    <img src="assets/img/quick-view/s2.jpg" alt="">
                                </a>
                                <a href="#modal3" data-toggle="tab" role="tab">
                                    <img src="assets/img/quick-view/s3.jpg" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="qwick-view-right">
                            <div class="qwick-view-content">
                                <h3>Dog Calcium Food</h3>
                                <div class="product-price">
                                    <span class="new">$20.00 </span>
                                    <span class="old">$50.00</span>
                                </div>
                                <div class="product-rating">
                                    <i class="icon-star theme-color"></i>
                                    <i class="icon-star theme-color"></i>
                                    <i class="icon-star theme-color"></i>
                                    <i class="icon-star"></i>
                                    <i class="icon-star"></i>
                                </div>
                                <p>Lorem ipsum dolor sit amet, consectetur adip elit, sed do amt tempor incididun ut labore et dolore magna aliqua. Ut enim ad mi , quis nostrud veniam exercitation .</p>
                                <div class="quick-view-select">
                                    <div class="select-option-part">
                                        <label>Size*</label>
                                        <select class="select">
                                            <option value="">- Please Select -</option>
                                            <option value="">XS</option>
                                            <option value="">S</option>
                                            <option value="">M</option>
                                            <option value=""> L</option>
                                            <option value="">XL</option>
                                            <option value="">XXL</option>
                                        </select>
                                    </div>
                                    <div class="select-option-part">
                                        <label>Color*</label>
                                        <select class="select">
                                            <option value="">- Please Select -</option>
                                            <option value="">orange</option>
                                            <option value="">pink</option>
                                            <option value="">yellow</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="quickview-plus-minus">
                                    <div class="cart-plus-minus">
                                        <input type="text" value="2" name="qtybutton" class="cart-plus-minus-box">
                                    </div>
                                    <div class="quickview-btn-cart">
                                        <a class="btn-style" href="#">add to cart</a>
                                    </div>
                                    <div class="quickview-btn-wishlist">
                                        <a class="btn-hover" href="#"><i class="ti-heart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		
		
		
		<!-- all js here -->
		
        <jsp:include page="fragments/allJs.jsp" />
        
        
    </body>
</html>
