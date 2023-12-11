<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="/common/taglib.jsp"%>

<html lang="en">
<head>
<meta charset="utf-8">

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

</head>
<body>
	
<%-- 	<div class="h5 m-0">${user}</div> --%>
	<%-- <div class="h5 m-0">${user.userName}</div> --%>
	
	<div class="container gedf-wrapper">
		<div class="row">
			<!--Content bên trái-->
			<div class="col-md-3">
				<div class="card">
					<div class="card-body">
						<a href="user_profile.html">
							<div class="h5">
								<img class="rounded-circle" width="45"
									src="https://p16-sign-sg.tiktokcdn.com/aweme/100x100/tos-alisg-avt-0068/69b4aeb1e3ac204e017ac6309e910231.jpeg?x-expires=1700020800&x-signature=z7KyQRNJqRkb6LxGuG%2Frqsyrv8U%3D"
									> @Lee Tai
							</div>
						</a>
						<div class="h7 text-muted">Fullname : Lê Hoàng Minh Tài</div>
						<div class="h7">Developer of web applications, JavaScript,
							PHP, Java, Python, Ruby, Java, Node.js, etc.</div>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<div class="h6 text-muted">Followers</div>
							<div class="h5">5.234</div>
						</li>
						<li class="list-group-item">
							<div class="h6 text-muted">Following</div>
							<div class="h5">67</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-6 gedf-main">
					<!-- SUA DAY -->
					<div class="card">
						<div class="card-body">
							<form>
								<div class="form-group">
									<a href="/posts/post/add/${userid}">
										<textarea class="form-control" rows="3"
											placeholder="What's on your mind?"></textarea>
									</a>
								</div>
								<a href="/posts/post/add/${userid}">
									<div class="btn btn-primary">
										Post
									</div>
								</a>
							</form>
						</div>
					</div>
				<!-- SUA DAY -->
				<!-- Main content hiển thị bài viết -->
				<c:forEach var="post" items="${post}">
						<div class="card gedf-card">
							<div class="card-header">
								<div class="d-flex justify-content-between align-items-center">
									<div class="d-flex justify-content-between align-items-center">
										<div class="mr-2">
											<!-- Avatar sửa ở đây -->
											<img class="rounded-circle" width="45"
												src="https://bootdey.com/img/Content/avatar/avatar1.png">
										</div>
										<div class="ml-2">
											<!-- Username sửa ở đây -->
										<c:forEach var="userEntry" items="${user}">
											<c:if test="${post.userid.userId == userEntry.key}">
												<c:set var="currentUser" value="${userEntry.key}" scope="request" />
  												<div class="h5 m-0">${userEntry.value}</div>
											</c:if>
										</c:forEach>
										<%-- <div class="h5 m-0">${user.value[0]}</div> --%>
											<div>
												<div class="h7 text-muted">${post.postDate}</div>
												<c:if test="${post.access_modifier == 0}">
													<div class="h7 text-muted"><i class="fa fa-globe"></i> Public</div>
												</c:if>
												<c:if test="${post.access_modifier == 1}">
													<div class="h7 text-muted"><i class="fa fa-users"></i> Friends</div>
												</c:if>
												<c:if test="${post.access_modifier == 2}">
													<div class="h7 text-muted"><i class="fa fa-user"></i> Just me</div>
												</c:if>
											</div>
											
										</div>
									</div>
									<div>
										<div class="dropdown">
											<button class="btn btn-link dropdown-toggle" type="button"
												id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false">
												<i class="fa fa-ellipsis-h"></i>
											</button>
											<div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="gedf-drop1">
												<div class="h6 dropdown-header">Configuration</div>
												<a class="dropdown-item" href="#">Save</a>
												<c:if test="${userid == currentUser}">
													<a class="dropdown-item" href="/posts/post/edit/${post.postid}/${userid}">Update</a>
													<a class="dropdown-item" href="/posts/post/delete/${post.postid}/${userid}">Delete</a>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-body">
								<!-- Nội dung post ở đây -->
								<p class="card-text">${post.content}</p><br>
								
								<!-- Hình ảnh post ở đây -->
								<c:if test="${post.media != null}">
									<img src="/posts/post/images/${post.media}" class="img-fluid">
								</c:if>
								
							</div>
							<div class="card-footer">
								<c:forEach var="likeCount" items="${likeCount}">
								<c:if test="${post.postid == likeCount.key}">
								
									<a  href="javascript:void(0)" onclick="checkAndLike(${post.postid}, ${userid});" class="card-link1"><i class="fa fa-gittip"></i>
									<label id="likeCountElement">${likeCount.value}</label> Like</a>
								</c:if>
							</c:forEach>
							
								<a href="/posts/post/comment/${post.postid}/${currentUser}/${userid}" class="card-link"><i class="fa fa-comment"></i> Comment</a>
								<a href="#" class="card-link"><i class="fa fa-mail-forward"></i> Share</a>
							</div>
						</div>
				</c:forEach>
			</div>

			<!--Content bên phải website-->
			<div class="col-md-3">
				<div class="card gedf-card">
					<div class="card-body">
						<div class="d-flex justify-content-between align-items-center">
							<h5 class="card-title">Gợi ý bạn bè</h5>
							<h6 class="card-subtitle mb-2 text-muted">Xem tất cả</h6>
						</div>
						<div class="d-flex justify-content-between align-items-center">
							<div class="mr-2">
								<img class="rounded-circle" width="45" src="https://picsum.photos/50/50">
								<a href="">Le Tai</a>

							</div>
							<div class="ml-2 ">
								<a href="">Theo dõi</a>
							</div>
						</div>
						<div
							class="d-flex justify-content-between align-items-center mt-2">
							<div class="mr-2">
								<img class="rounded-circle" width="45"
									src="https://p16-sign-sg.tiktokcdn.com/aweme/100x100/tos-alisg-avt-0068/69b4aeb1e3ac204e017ac6309e910231.jpeg?x-expires=1700020800&x-signature=z7KyQRNJqRkb6LxGuG%2Frqsyrv8U%3D"
									alt> <a href="">Le Tai</a>
							</div>
							<div class="ml-2">
								<a href="">Theo dõi</a>
							</div>
						</div>
					</div>
				</div>

				<div class="card gedf-card">
					<div class="card-body">
						<p class="card-text"></p>
						<a href="#" class="card-link">Giới thiệu</a><br>
						<a href="#" class="card-link">Trợ giúp</a><br>
						<a href="#" class="card-link">Báo chí</a><br>
						<a href="#" class="card-link">Quyền riêng tư</a><br>
						<a href="#" class="card-link">Điều khoản</a><br>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript"></script>
    
    <script>
	function checkAndLike(postId, userId) {
        $.ajax({
            type: 'GET',
            url: '/posts/post/check-like/' + postId + '/' + userId,
            success: function(response) {
                if (response) {
                    // Người dùng đã like, thực hiện delete
                    deleteLike(postId, userId);
                } else {
                    // Người dùng chưa like, thực hiện insert
                    likePost(postId, userId);
                }
            },
            error: function(error) {
                // Xử lý lỗi
            }
        });
    }
	
	function likePost(postId, userId) {
	    $.ajax({
	        type: 'POST',
	        url: '/posts/post/' + postId + '/' + userId + '/like',
	        success: function(response) {
	            // Xử lý thành công, cập nhật số lượt thích trên trang
	            var newLikeCount = parseInt(${likeCount.value}) + 1;
	            document.getElementById('likeCountElement').textContent = newLikeCount.toString();
	            
	            window.location.reload();
	        },
	        error: function(error) {
	            // Xử lý lỗi
	        }
	    });
	}
	
	function deleteLike(postId, userId) {
        $.ajax({
            type: 'DELETE',
            url: '/posts/post/' + postId + '/' + userId + '/like',
            success: function(response) {
                // Xử lý thành công, cập nhật số lượt thích trên trang
                var newLikeCount = parseInt(${likeCount.value}) - 1;
                document.getElementById('likeCountElement').textContent = newLikeCount.toString();
                location.reload();
            },
            error: function(error) {
                // Xử lý lỗi
            }
        });
    }
	</script>
</body>