<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ALOHCMUTE</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="style.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<style type="text/css">
body {
	background-color: #eeeeee;
	margin: 0;
	font-family: 'Arial', sans-serif;
}

.h7 {
	font-size: 0.8rem;
}

header {
	background-color: #eeeeee;
	padding: 3px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

nav {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

nav div {
	display: flex;
	align-items: center;
}

input[type="text"] {
	width: 200px;
	margin-right: 10px;
	border-radius: 10px;
    border: 1px solid #ccc;
    padding: 5px;
}

.btn-nav {
	background-color: #eeeeee;
	border: none;
	cursor: pointer;
	margin-right: 5px;
}

.btn-nav i {
	font-size: 1.3rem;
}

.gedf-wrapper {
	margin-top: 0.97rem;
}

.container {
	width: 100%;
}

@media ( min-width : 992px) {
	.gedf-main {
		padding-left: 0rem;
		padding-right: 0rem;
	}
	.gedf-card {
		margin-bottom: 2.77rem;
	}
	.gedf-wrapper {
		padding-left: 0rem;
		padding-right: 0rem;
	}
}

/**Reset Bootstrap*/
.dropdown-toggle::after {
	content: none;
	display: none;
}
</style>
</head>
<body>

	<nav>
		<div class="">
			<a href="javascript:void(0)"><button class="btn-nav">
					<i class='bx bx-home-alt-2 bx-border-circle'></i>
				</button></a>
			<button class="btn-nav">
				<i class='bx bx-border-circle bx-group'></i>
			</button>
			<button class="btn-nav">
				<i class='bx bx-border-circle bx-tv'></i>
			</button>
			<button class="btn-nav">
				<i class='bx bx-border-circle bx-bookmark'></i>
			</button>
			<button class="btn-nav">
				<i class='bx bx-border-circle bx-dots-vertical-rounded'></i>
			</button>
		</div>
		<div>
			<h2>ALO HCMUTE</h2>
		</div>
		<div>
			<input class="" type="text" placeholder="Search">
			<button class="btn-nav">
				<i class='bx-border-circle bx bx-heart'></i>
			</button>
			<button class="btn-nav">
				<i class='bx-border-circle bx bx-user'></i>
			</button>
		</div>

	</nav>
	<div style="height: 0px;"></div>
	<link
		href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
		rel="stylesheet"
		integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
		crossorigin="anonymous">

</body>
</html>