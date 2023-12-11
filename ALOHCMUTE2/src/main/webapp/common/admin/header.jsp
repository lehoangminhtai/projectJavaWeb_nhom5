<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ALOHCMUTE</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	rel="stylesheet">
<!-- Your custom styles -->
<style>
body {
	padding-top: 56px;
	background-color: #f8f9fa; /* Màu nền */
}

.navbar {
	background-color: #4267B2; /* Màu nền của Facebook */
	border-bottom: 1px solid #29487D;
}

.navbar-brand {
	color: #fff;
	font-size: 24px;
	font-weight: bold;
}

.navbar-nav .nav-link {
	color: #fff;
}

.navbar-toggler-icon {
	background-color: #fff;
}

.jumbotron {
	background-color: #4267B2;
	color: #fff;
}

.jumbotron h1 {
	font-size: 48px;
}

.jumbotron p {
	font-size: 18px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-md navbar-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/admin/alohcmute">ALOHCMUTE</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarsExampleDefault"
				aria-controls="navbarsExampleDefault" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-end"
				id="navbarsExampleDefault">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link"
						href="https://www.facebook.com/"><i class="fab fa-facebook"></i>
							Facebook</a></li>
					<li class="nav-item"><a class="nav-link"
						href="https://twitter.com/home"><i class="fab fa-twitter"></i>
							Twitter</a></li>
					<li class="nav-item"><a class="nav-link"
						href="https://www.instagram.com/"><i class="fab fa-instagram"></i>
							Instagram</a></li>
					<li class="nav-item"><a class="nav-link"
						href="https://www.linkedin.com/"><i class="fab fa-linkedin"></i>
							LinkedIn</a></li>
					<li class="nav-item"><a class="nav-link"
						href="https://www.youtube.com/"><i class="fab fa-youtube"></i>
							YouTube</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="notificationDropdown" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="fas fa-bell"></i> Thông Báo
					</a>
						<div class="dropdown-menu" aria-labelledby="notificationDropdown">
							<!-- Thêm các thông báo khác tại đây -->
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="/admin/alohcmute/profile/FriendRequestNotification">Xem tất cả thông báo</a>
						</div></li>
					<!-- Thông tin người dùng -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="userDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-user"></i>
							${sessionScope.user.username}
					</a>
						<div class="dropdown-menu" aria-labelledby="userDropdown">
							<!-- Hiển thị thông tin người dùng -->
							<a class="dropdown-item" href="#">User:
								${sessionScope.user.username}</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="/admin/alohcmute/profile">Trang
								cá nhân</a>
							<c:if test="${not empty sessionScope.user}">
								<a class="dropdown-item" href="/logout">Đăng xuất</a>
							</c:if>
						</div></li>

				</ul>

				<form class="form-inline my-2 my-lg-0" id="searchForm"
					action="/admin/alohcmute/find_friends" method="get">
					<div class="input-group input-group-sm">
						<input type="text" class="form-control" placeholder="Search..."
							id="searchInput" name="fullName">
						<div class="input-group-append">
							<button type="submit" class="btn btn-light btn-number">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</nav>

	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading">ALOHCMUTE</h1>
			<p class="lead text-muted mb-0">Connect with friends, share
				updates, and explore the world.</p>
		</div>
	</section>

	<!-- Bootstrap JS, Popper.js, jQuery -->
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
