<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>


<style>
body {
	margin: 0;
	padding: 0;
}

.main-content {
	display: flex;
	height: 100vh;
}

.left-section, .right-section {
	flex: 1;
	background-color: #f0f0f0; /* Màu nền cho hai phần còn lại */
	overflow-y: auto;
	/* Cho phép cuộn nếu nội dung dài hơn kích thước của phần tử */
	padding: 20px;
}

.center-section {
	flex: 3;
	overflow-y: auto;
	padding: 20px;
}

.btn-add-category {
	font-size: 12px; /* Điều chỉnh kích thước chữ của nút */
	padding: 6px 12px; /* Điều chỉnh kích thước nút */
}

.menu-item {
	display: flex;
	align-items: center;
	margin-bottom: 10px;
	cursor: pointer;
}

.menu-icon {
	font-size: 20px;
	margin-right: 10px;
}

.menu-text {
	font-size: 14px;
}

/* Thêm các kiểu CSS khác theo ý muốn của bạn */
.category-item-right {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
}

.category-image-right img {
	border: none;
	border-radius: 100%;
	max-width: 50px; /* Điều chỉnh chiều rộng của ảnh */
	max-height: 50px; /* Điều chỉnh chiều cao của ảnh */
	object-fit: cover;
	margin-right: 10px; /* Khoảng cách giữa ảnh và tên */
}

.category-details-right p {
	margin: 0;
	/* Loại bỏ margin của đoạn văn bản để giữ giao diện sạch sẽ */
}

<
link rel ="stylesheet " href ="https: //cdnjs.cloudflare.com /ajax /libs
	/font-awesome /5.15.3 /css /all.min.css " integrity ="sha384-ezjw8l5z3kgZsphgjrE9R1o2L3y3IrS2K1TOWvi5uuXlSv5irI2HJLZ5u8U0sDl
	 " crossorigin ="anonymous "> .modal {
	display: none; /* Ẩn modal */
	position: fixed; /* Giữ modal cố định */
	z-index: 1; /* Đảm bảo modal ở trên cùng */
	left: 0;
	top: 0;
	width: 100%; /* Chiều rộng modal */
	height: 100%; /* Chiều cao modal */
	overflow: auto; /* Cho phép cuộn nếu cần */
	background-color: rgb(0, 0, 0); /* Màu nền */
	background-color: rgba(0, 0, 0, 0.4); /* Màu nền có độ trong suốt */
	align-items: center;
	justify-content: center;
}

.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 50%; /* Chiều rộng của nội dung modal */
}

.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
}

.friend-suggestion-container {
	background-color: #f5f5f5;
	padding: 15px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.friend-suggestion-header {
	color: #333;
	font-size: 24px;
	margin-bottom: 15px;
}

.friend-suggestion-scroll {
	display: flex;
	overflow-x: auto;
	padding-bottom: 10px;
}

.friend-suggestion-item {
	flex: 0 0 33.333%;
	display: flex;
	align-items: center;
	justify-content: flex-start; /* Căn trái các phần tử con */
	background-color: #fff;
	margin-right: 10px;
	padding: 10px;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.friend-avatar {
	width: 60px;
	height: 60px;
	border-radius: 50%;
	margin-right: 15px; /* Khoảng cách giữa ảnh và tên */
}

.friend-details .friend-name {
	font-size: 16px;
	color: #333;
}

.btn-view-profile {
	padding: 6px 12px;
	font-size: 14px;
	color: #fff;
	background-color: #007bff;
	border: none;
	border-radius: 4px;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn-view-profile:hover {
	background-color: #0056b3;
	color: #fff;
}

.profile-avatar {
	width: 50px; /* Kích thước của avatar */
	height: 50px;
	overflow: hidden;
	border-radius: 50%; /* Bo góc thành hình tròn */
}

.profile-avatar img {
	width: 100%;
	height: 100%;
	object-fit: cover; /* Đảm bảo hình ảnh đầy đủ trong khung */
}
</style>
<div class="friend-suggestion-container">
	<h2 class="friend-suggestion-header">Danh sách bạn bè đề suất</h2>
	<div class="friend-suggestion-scroll">
		<c:forEach items="${usersuggestion}" var="friendsg">
			<div class="friend-suggestion-item">
				<!-- Nội dung mỗi item -->
				<img
					src="<c:choose><c:when test='${not empty friendsg.avatar}'><c:url value='/admin/alohcmute/images/${friendsg.avatar}'/></c:when><c:otherwise>path/to/default-avatar.png</c:otherwise></c:choose>"
					alt="Avatar" class="friend-avatar">
				<div class="friend-details">
					<p class="friend-name">${friendsg.fullName}</p>
					<!-- Các thông tin khác của friendsg -->
					<a href="/admin/alohcmute/profile/friends/${friendsg.username }"
						class="btn-view-profile">Trang cá nhân</a>

				</div>
			</div>
		</c:forEach>
	</div>
</div>
<div class="main-content">
	<div class="left-section">
		<!-- Nội dung của phần trái -->
		<!--  <div class="menu-item">
			<i class="fa fa-user menu-icon"></i> <span class="menu-text">Bạn
				bè</span>
		</div>
		<div class="menu-item">
			<i class="fa fa-calendar menu-icon"></i> <span class="menu-text">Kỷ
				niệm</span>
		</div>
		<div class="menu-item">
			<i class="fa fa-bookmark menu-icon"></i> <span class="menu-text">Đã
				lưu</span>
		</div> -->
		<div class="menu-item">
			<a href="/admin/alohcmute/groupchat/createGC"> <i
				class="fa fa-users menu-icon"></i> <span class="menu-text">Tạo
					nhóm chat</span>
			</a>
		</div>
		<div class="menu-item">
			<a href="/admin/alohcmute/groupchat/all"> <i
				class="fa fa-users menu-icon"></i> <span class="menu-text">Danh
					sách nhóm</span>
			</a>
		</div>

		<div class="menu-item">
			<a href="/user/dashboard/${userid }?countFriend=${countFriend}"> <i
				class="fa fa-list-alt menu-icon"></i> <span class="menu-text">Thống
					kế User</span>
			</a>
		</div>

		<div class="menu-item">
			<a href="/admin/alohcmute/listfriends/all"> <i
				class="fa fa-user menu-icon"></i> <span class="menu-text">Danh
					sách bạn bè</span>
			</a>
		</div>
		<!--<div class="menu-item">
			<i class="fa fa-video menu-icon"></i> <span class="menu-text">Video</span>
		</div>
		<div class="menu-item">
			<i class="fa fa-shopping-cart menu-icon"></i> <span class="menu-text">Marketplace</span>
		</div>
		<div class="menu-item">
			<i class="fa fa-list-alt menu-icon"></i> <span class="menu-text">Bảng
				feed</span>
		</div>
		<div class="menu-item">
			<i class="far fa-calendar menu-icon"></i> <span class="menu-text">Sự
				kiện</span>
		</div>

		<div class="menu-item">
			<i class="fa fa-heart menu-icon"></i> <span class="menu-text">Chiến
				dịch gây quỹ</span>
		</div>
		<div class="menu-item">
			<i class="fa fa-gamepad menu-icon"></i> <span class="menu-text">Chơi
				game</span>
		</div>
		<div class="menu-item">
			<i class="fa fa-shopping-basket menu-icon"></i> <span
				class="menu-text">Đơn đặt hàng</span>
		</div> -->
	</div>

	<div class="center-section">
		<!-- Post Creation Section -->
		<div class="card mb-3">
			<div class="card-body">
				<form action="/posts/post/add/${userid}" method="post">
					<div class="form-group">
						<a href="/posts/post/add/${userid}"> <textarea
								class="form-control" rows="3" placeholder="What's on your mind?"></textarea>
						</a>
					</div>
					<a href="/posts/post/add/${userid}">
						<div class="btn btn-primary">Post</div>
					</a>
				</form>
			</div>
		</div>

		<!-- Main Content to Display Posts -->
		<c:forEach var="post" items="${post}">
			<div class="card gedf-card mb-3">
				<div class="card-header">
					<div class="d-flex justify-content-between align-items-center">
						<div class="d-flex align-items-center">
							<div class="mr-2">
								<!-- Avatar -->
								<c:forEach var="userEntry" items="${user2}">
									<c:if test="${post.userid.userId == userEntry.key}">
										<img
											src="<c:url value='/admin/alohcmute/images/${userEntry.value}'/>"
											class="rounded-circle" alt="Avatar"
											style="width: 40px; height: 40px;">
									</c:if>
								</c:forEach>
							</div>
							<div class="ml-2">
								<!-- Username and Post Date -->
								<c:forEach var="userEntry" items="${user}">
									<c:if test="${post.userid.userId == userEntry.key}">
										<c:set var="currentUser" value="${userEntry.key}"
											scope="request" />
										<div class="h5 m-0">${userEntry.value}</div>
										<div class="h7 text-muted">${post.postDate}</div>
									</c:if>
								</c:forEach>
								<div>
									<!-- Access Modifier -->
									<c:if test="${post.access_modifier == 0}">
										<div class="text-muted small">
											<i class="fa fa-globe"></i> Public
										</div>
									</c:if>
									<c:if test="${post.access_modifier == 1}">
										<div class="text-muted small">
											<i class="fa fa-users"></i> Friends
										</div>
									</c:if>
									<c:if test="${post.access_modifier == 2}">
										<div class="text-muted small">
											<i class="fa fa-user"></i> Just me
										</div>
									</c:if>
								</div>
							</div>
						</div>
						<div>
							<!-- Dropdown for Post Configuration -->
							<div class="dropdown">
								<button class="btn btn-link" type="button" id="gedf-drop1"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<i class="fa fa-ellipsis-h"></i>
								</button>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="gedf-drop1">
									<div class="h6 dropdown-header">Configuration</div>
									<a class="dropdown-item" href="#">Save</a>
									<c:if test="${userid == currentUser}">
										<a class="dropdown-item"
											href="/posts/post/edit/${post.postid}/${userid}">Update</a>
										<a class="dropdown-item"
											href="/admin/alohcmute/delete/${post.postid}/${userid}">Delete</a>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<!-- Post Content -->
					<p class="card-text">${post.content}</p>
					<!-- Post Image -->
					<c:if test="${post.media != null}">
						<img src="/posts/post/images/${post.media}" class="img-fluid">
					</c:if>
				</div>
				<div class="card-footer">
					<!-- Like, Comment, Share Buttons -->
					<c:forEach var="likeCount" items="${likeCount}">
						<c:if test="${post.postid == likeCount.key}">
							<a href="javascript:void(0)"
								onclick="checkAndLike(${post.postid}, ${userid});"
								class="card-link"> <i class="fa fa-thumbs-up"></i> <span
								id="likeCountElement">${likeCount.value}</span> Like
							</a>
						</c:if>
					</c:forEach>
					<a
						href="/posts/post/comment/${post.postid}/${currentUser}/${userid}"
						class="card-link"><i class="fa fa-comment"></i> Comment</a> <a
						href="#" class="card-link"><i class="fa fa-share"></i> Share</a>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="right-section">
		<h2>Danh sách bạn bè</h2>
		<!-- Nội dung của phần phải -->
		<c:forEach items="${listfriends}" var="friend">
			<div class="category-item-right">
				<div class="category-image-right">
					<c:choose>
						<c:when test="${not empty friend.avatar}">
							<img
								src="<c:url value='/admin/alohcmute/images/${friend.avatar}'/>"
								class="img-thumbnail" alt="Category Icon">
						</c:when>
						<c:otherwise>
							<p>No icon available.</p>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="category-details-right">
					<a href="/admin/alohcmute/profile/message/${friend.userId }">${friend.fullName }</a>

				</div>
			</div>
		</c:forEach>
	</div>
</div>
<!-- Modal -->
<div id="categoryModal" class="modal">
	<div class="modal-content">
		<span class="close" onclick="closeModal()">&times;</span>
		<h2>Thông Tin Category</h2>
		<img id="categoryIcon" src="" alt="Category Icon"
			style="width: 100px; height: 100px;">
		<p id="categoryName"></p>
	</div>
</div>

<script>
	function openModal(iconSrc, categoryName) {
		document.getElementById('categoryIcon').src = iconSrc;
		document.getElementById('categoryName').innerText = categoryName;
		document.getElementById('categoryModal').style.display = 'flex';
	}

	function closeModal() {
		document.getElementById('categoryModal').style.display = 'none';
	}
</script>

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