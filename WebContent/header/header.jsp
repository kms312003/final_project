<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TeamPro</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/header/styles/bootstrap4/bootstrap.min.css">
<link href="<%=request.getContextPath() %>/header/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/header/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/header/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/header/plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/header/styles/blog_styles.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/header/styles/blog_responsive.css">

</head>
<body>

	<!-- Header -->
	
	<header class="header">

		<!-- Top Bar -->

		<div class="top_bar">
			<div class="container">
				<div class="row">
					<div class="col d-flex flex-row">
						<div class="top_bar_contact_item"><div class="top_bar_icon"><img src="<%=request.getContextPath() %>/header/images/phone.png" alt=""></div>+38 068 005 3570</div>
						<div class="top_bar_contact_item"><div class="top_bar_icon"><img src="<%=request.getContextPath() %>/header/images/mail.png" alt=""></div><a href="mailto:mailname@gmail.com">mailname@gmail.com</a></div>
						<div class="top_bar_content ml-auto">
							<div class="top_bar_menu">
								<ul class="standard_dropdown top_bar_dropdown">
									<li>
										<a href="#">English<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li><a href="#">Korean</a></li>
											<li><a href="#">Chinese</a></li>
											<li><a href="#">Japanese</a></li>
										</ul>
									</li>
									<li>
										<a href="#">$ US dollar<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li><a href="#">KRW Korean Won</a></li>
											<li><a href="#">CNY Chinese Yuan</a></li>
											<li><a href="#">JPY Japanese Yen</a></li>
										</ul>
									</li>
								</ul>
							</div>
							<div class="top_bar_user">
								<div class="user_icon"><img src="<%=request.getContextPath() %>/header/images/user.svg" alt=""></div>
								<div><a href="<%= request.getContextPath() %>/main/user/register">Register</a></div>
								<div><a href="<%= request.getContextPath() %>/main/user/login">Sign in</a></div>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>

		<!-- Header Main -->

		<div class="header_main">
			<div class="container">
				<div class="row">

					<!-- Logo -->
					<div class="col-lg-2 col-sm-3 col-3 order-1">
						<div class="logo_container">
							<div class="logo"><a href="#">TeamPro</a></div>
						</div>
					</div>

					<!-- Search -->
					<div class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
						<div class="header_search">
							<div class="header_search_content">
								<div class="header_search_form_container">
									<form action="#" class="header_search_form clearfix">
										<input type="search" required="required" class="header_search_input" placeholder="Search for products">
										
										<button type="submit" class="header_search_button trans_300" value="Submit"><img src="<%=request.getContextPath() %>/header/images/search.png" alt=""></button>
									</form>
								</div>
							</div>
						</div>
					</div>

					<!-- Wishlist -->
					<div class="col-lg-4 col-9 order-lg-3 order-2 text-lg-left text-right">
						<div class="wishlist_cart d-flex flex-row align-items-center justify-content-end">
							<div class="wishlist d-flex flex-row align-items-center justify-content-end">
								<div class="wishlist_icon"><img src="<%=request.getContextPath() %>/header/images/heart.png" alt=""></div>
								<div class="wishlist_content">
									<div class="wishlist_text"><a href="#">Wishlist</a></div>
									<div class="wishlist_count">115</div>
								</div>
							</div>

							<!-- Cart -->
							<div class="cart">
								<div class="cart_container d-flex flex-row align-items-center justify-content-end">
									<div class="cart_icon">
										<img src="<%=request.getContextPath() %>/header/images/cart.png" alt="">
										<div class="cart_count"><span>10</span></div>
									</div>
									<div class="cart_content">
										<div class="cart_text"><a href="#">Cart</a></div>
										<div class="cart_price">$85</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Main Navigation -->

		<nav class="main_nav">
			<div class="container">
				<div class="row">
					<div class="col">
						
						<div class="main_nav_content d-flex flex-row">

							<!-- DIY PC Menu -->

							<div class="cat_menu_container">
								<div class="cat_menu_title d-flex flex-row align-items-center justify-content-start">
									<div class="cat_menu_text">DIY PC</div>
								</div>
							</div>

							<!-- Main Nav Menu -->

							<div class="main_nav_menu ml-auto">
								<ul class="standard_dropdown main_nav_dropdown">
									<li><a href="<%= request.getContextPath() %>/main/main">Home<i class="fas fa-chevron-down"></i></a></li>
									<li class="hassubs">
										<a href="#">Computer parts<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li><a href="<%= request.getContextPath() %>/main/cpu/list">CPU<i class="fas fa-chevron-down"></i></a>
											<li><a href="<%= request.getContextPath() %>/main/mainboard/list">MainBoard<i class="fas fa-chevron-down"></i></a>
											<li><a href="<%= request.getContextPath() %>/main/ram/list">RAM<i class="fas fa-chevron-down"></i></a>
											<li><a href="<%= request.getContextPath() %>/main/graphic/list">Graphic Card<i class="fas fa-chevron-down"></i></a>
											<li><a href="<%= request.getContextPath() %>/main/power/list">Power Supply<i class="fas fa-chevron-down"></i></a>
											<li><a href="<%= request.getContextPath() %>/main/hdd/list">HDD<i class="fas fa-chevron-down"></i></a>
											<li><a href="<%= request.getContextPath() %>/main/ssd/list">SSD<i class="fas fa-chevron-down"></i></a>
											<li><a href="#">Accessories<i class="fas fa-chevron-down"></i></a>
										</ul>
									</li>
									<li class="hassubs">
										<a href="#">Recommendations<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li>
												<a href="<%= request.getContextPath() %>/main/computer/list?category=2">For Games<i class="fas fa-chevron-down"></i></a>
												<ul>
													<li><a href="#">Battle Ground<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="#">Overwatch<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="#">League of Legend<i class="fas fa-chevron-down"></i></a></li>
												</ul>
											</li>
											<li><a href="<%= request.getContextPath() %>/main/computer/list?category=1">For Office<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="<%= request.getContextPath() %>/main/computer/list?category=3">For Design<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="<%= request.getContextPath() %>/main/computer/list?category=4">For Broadcast<i class="fas fa-chevron-down"></i></a></li>
										</ul>
									</li>
									<li class="hassubs">
										<a href="#">Community<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li><a href="<%= request.getContextPath() %>/board_notice/list">NOTICE<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="<%= request.getContextPath() %>/board_information/list">INFORMATION<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="<%= request.getContextPath() %>/board_opinion/list">OPINION<i class="fas fa-chevron-down"></i></a></li>
											<!-- <li><a href="blog_single.html">Announcement<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="regular.html">Cafe<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="cart.html">Cart<i class="fas fa-chevron-down"></i></a></li> -->
										</ul>
									</li>
									<li><a href="<%= request.getContextPath() %>/board_qa/list">Q&A<i class="fas fa-chevron-down"></i></a></li>
									<li><a href="contact.html">Contact<i class="fas fa-chevron-down"></i></a></li>
								</ul>
							</div>

							<!-- Menu Trigger -->

							<div class="menu_trigger_container ml-auto">
								<div class="menu_trigger d-flex flex-row align-items-center justify-content-end">
									<div class="menu_burger">
										<div class="menu_trigger_text">menu</div>
										<div class="cat_burger menu_burger_inner"><span></span><span></span><span></span></div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</nav>

	</header>

</body>
</html>