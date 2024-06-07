<%@page import="model.ShoppingCart"%>
<%@page import="database.ShoppingCartDAO"%>
<%@page import="database.ProductDAO"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Management product</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Icon logo -->
<link rel="icon" href="img/icon.png">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<div class="header__top__left">
							<ul>
								<li><i class="fa fa-envelope"></i> welcometoOgani@gmail.com</li>
								<li>Giao hàng miễn phí cho tất cả đơn hàng trị giá $99</li>
							</ul>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="header__top__right">
							<div class="header__top__right__social">
								<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
									class="fa fa-twitter"></i></a> <a href="#"><i
									class="fa fa-linkedin"></i></a> <a href="#"><i
									class="fa fa-pinterest-p"></i></a>
							</div>
							<div class="header__top__right__language">
								<img  src="img/vietnam-language.webp" alt="" style="width:27px;height:17px">
								<div>Việt Nam</div>
								<span class="arrow_carrot-down"></span>
								<ul>
									<li><a href="#">Spanis</a></li>
									<li><a href="#">Việt Nam</a></li>
								</ul>
							</div>
							<%
							String username = (String) session.getAttribute("username");
							String idCustomer = (String) session.getAttribute("idCustomer");
							ArrayList<Product> products = ProductDAO.getInstance().getAllProduct();
							if (username != null) {
							%>
							<div class="header__top__right__auth">
								<a href="personal-info.jsp"><i class="fa fa-user"></i>
									${username}</a>
							</div>
							<%
							} else {
							%>
							<div class="header__top__right__auth">
								<a href="./login.jsp"><i class="fa fa-user"></i> Login</a>
							</div>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="./index.jsp"><img src="img/logo.png" alt=""></a>
					</div>
				</div>
				<div class="col-lg-7">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="./product-management.jsp">Sản phẩm</a></li>
							<li><a href="#">Danh mục</a></li>
							<li><a href="#">Khách hàng</a></li>
							<li><a href="#">Thống kê</a>
								<ul class="header__menu__dropdown">
                                    <li><a href="./statistic-revenue.jsp">Doanh thu</a></li>
                                    <li><a href="./statistic-best-selling-products.jsp">Sản phẩm bán chạy</a></li>
                                    <li><a href="./statistic-report-product.jsp">Sản phẩm</a></li>
                                </ul>
							</li>
						</ul>
					</nav>
				</div>
				<div class="col-lg-2">
					
				</div>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<!-- Hero Section End -->

	<!-- Blog Details Hero Begin -->
	<section class="blog-details-hero set-bg"
		data-setbg="img/blog/details/details-hero.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="blog__details__hero__text">
						<h2>Quản lý sản phẩm</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Details Hero End -->

	<!-- product-insert Section Begin -->
	<div class="container">
		
		<!-- <h1>Quản lý sản phẩm</h1> -->
		<form action="insertProduct" method="post"
			enctype="multipart/form-data">
			<h2 style="display: flex; justify-content: center; padding: 15px 0px 0px 0px;">Tạo mới sản phẩm</h2>
			<div class="row">
				<div class="c-8">
					<div class="row margin-top-64">
						<div class="c-12">
							<div class="row">
								<div class="c-4">
									<label for="name">Tên sản phẩm:</label>
								</div>
								<div class="c-8">
									<input type="text" name="nameProduct" id="name"
										class="input-promanager" required>
								</div>
							</div>
						</div>
						<div class="c-12">
							<div class="row">
								<div class="c-4">
									<label for="quantity">Số lượng:</label>
								</div>
								<div class="c-8">
									<input type="number" name="quantity" id="quantity"
										class="input-promanager" required>
								</div>
							</div>
						</div>
						<div class="c-12">
							<div class="row">
								<div class="c-4">
									<label for="old-price">Giá cũ:</label>
								</div>
								<div class="c-8">
									<input type="number" name="oldPrice" id="old-price"
										class="input-promanager" required>
								</div>
							</div>
						</div>
						<div class="c-12">
							<div class="row">
								<div class="c-4">
									<label for="new-price">Giá mới:</label>
								</div>
								<div class="c-8">
									<input type="number" name="newPrice" id="new-price"
										class="input-promanager" required>
								</div>
							</div>
						</div>
						<div class="c-12">
							<div class="row">
								<div class="c-4">
									<label for="image">Ảnh sản phẩm:</label>
								</div>
								<div class="c-8">
									<input type="file" name="img" id="image"
										class="input-promanager" required>
								</div>
							</div>
						</div>
						<div class="c-12">
							<div class="row">
								<div class="c-4">
									<label for="category">Danh mục:</label>
								</div>
								<div class="c-8">
									<select name="idCategory" id="category" required>
										<%@page import="database.CategoryDAO"%>
										<%@page import="model.Category"%>
										<%@page import="java.util.ArrayList"%>
										<%
										ArrayList<Category> listCategory = CategoryDAO.getInstance().getAllCategories();
										for (Category category : listCategory) {
											out.print("<option value='" + category.getIdCategory() + "'>" + category.getNameCategory() + "</option>");
										}
										%>
									</select>
								</div>
							</div>
						</div>
						<div class="c-12">
							<div class="row">
								<div class="c-4">
									<label for="description">Mô tả:</label>
								</div>
								<div class="c-8">
									<textarea name="describe" id="description" cols="30" rows="10"
										required></textarea>
								</div>
							</div>
						</div>
						<div class="c-o-4 c-8">
							<input type="submit" class="input-promanager"
								value="Thêm sản phẩm">
						</div>
					</div>
				</div>

				<div class="c-4"></div>

			</div>
		</form>
	</div>
	<!-- product-managerment Section End -->

	<!-- Related Blog Section Begin -->
	<section class="related-blog spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title related-blog-title">
						<h2>Bài đăng bạn có thể thích</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="img/blog/blog-1.jpg" alt="">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 4,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5>
								<a href="#">Cooking tips make cooking simple</a>
							</h5>
							<p>Sed quia non numquam modi tempora indunt ut labore et
								dolore magnam aliquam quaerat</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="img/blog/blog-2.jpg" alt="">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 4,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5>
								<a href="#">6 ways to prepare breakfast for 30</a>
							</h5>
							<p>Sed quia non numquam modi tempora indunt ut labore et
								dolore magnam aliquam quaerat</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="img/blog/blog-3.jpg" alt="">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 4,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5>
								<a href="#">Visit the clean farm in the US</a>
							</h5>
							<p>Sed quia non numquam modi tempora indunt ut labore et
								dolore magnam aliquam quaerat</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Related Blog Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="./index.jsp"><img src="img/logo.png" alt=""></a>
						</div>
						<ul>
							<li>Address: 48 Cao Thắng</li>
							<li>Phone: +84 34.870.7913</li>
							<li>Email: welcometoOgani@gmail.com</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
					<div class="footer__widget">
						<h6>Useful Links</h6>
						<ul>
							<li><a href="#">About Us</a></li>
							<li><a href="#">About Our Shop</a></li>
							<li><a href="#">Secure Shopping</a></li>
							<li><a href="#">Delivery infomation</a></li>
							<li><a href="#">Privacy Policy</a></li>
							<li><a href="#">Our Sitemap</a></li>
						</ul>
						<ul>
							<li><a href="#">Who We Are</a></li>
							<li><a href="#">Our Services</a></li>
							<li><a href="#">Projects</a></li>
							<li><a href="#">Contact</a></li>
							<li><a href="#">Innovation</a></li>
							<li><a href="#">Testimonials</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="footer__widget">
						<h6>Join Our Newsletter Now</h6>
						<p>Get E-mail updates about our latest shop and special
							offers.</p>
						<form action="#">
							<input type="text" placeholder="Enter your mail">
							<button type="submit" class="site-btn">Subscribe</button>
						</form>
						<div class="footer__widget__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-instagram"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-pinterest"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="footer__copyright">
						<div class="footer__copyright__text">
							<p>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;
								<script>
									document.write(new Date().getFullYear());
								</script>
								All rights reserved | This template is made with <i
									class="fa fa-heart" aria-hidden="true"></i> by OGANI
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
						<div class="footer__copyright__payment">
							<img src="img/payment-item.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
</body>

</html>