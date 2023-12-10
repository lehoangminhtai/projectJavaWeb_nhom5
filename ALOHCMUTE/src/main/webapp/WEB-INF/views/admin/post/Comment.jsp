<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Comment System</title>
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
	background-color: #f8f9fa; /* Light gray background */
	margin: 0;
	display: flex;
	min-height: 100vh;
	align-items: center;
	justify-content: center;
	margin: 0;
}

.container {
	background-color: #ffffff; /* White container background */
	border: 1px solid #ced4da;
	border-radius: 8px; /* Rounded corners */
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Soft shadow */
	padding: 20px;
}

h2 {
	color: #007bff; /* Bootstrap primary color */
}

p {
	color: #495057; /* Bootstrap secondary color */
}

img#media {
	border-radius: 8px; /* Rounded corners for images */
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Soft shadow for images */
}

.form-group {
	margin-bottom: 0; /* Remove default margin-bottom for form-group */
}

input.form-control {
	border-radius: 4px; /* Rounded corners for form input */
}

button.btn-primary {
	background-color: #007bff; /* Bootstrap primary color for buttons */
	border: 1px solid #007bff;
	border-radius: 4px; /* Rounded corners for buttons */
}
</style>
</head>

<body class="bg-light">
	<div class=" container col-md-10 gedf-main mx-auto ">
		<div class="container mt-5 ">
			<div class="row card mb-3 ">
				<div class="col-md-15 card-body ">

					<h2 class="col text-center">Bài viết của @${ownername}</h2>
					<hr>
					<p>${post.content}</p>
					<c:if test="${post.media != null}">
						<img src="/posts/post/images/${post.media}" width="150px"
							class="img-fluid w-50 mx-auto d-block">
					</c:if>

					<!-- Uncomment this section when you have comments to display -->

				</div>
			</div>

			<div class="row">
				<div class="">
					<form action="<c:url value='/posts/post/cmtSave'/>" method="post"
						class="">
						<input type="hidden" name="postid" value="${post.postid}">
						<input type="hidden" name="userid" value="${userid}">
						
						<div class="form-group d-flex align-items-center">
							<input type="text" class="form-control" id="content"
								name="content" placeholder="Nhập bình luận" required>
							<button type="submit" class="btn btn-primary ml-2">
								<i class='bx bx-send' style='color: #ffffff'></i>
							</button>
						</div>
					</form>
				</div>
			</div>
			<div class="mt-4">
				<h3>Comments</h3>
				<c:forEach var="comment" items="${comment}">
					<div class="card mb-3 d-flex">
						<div class="card-body">

							<c:forEach var="userEntry" items="${user}">
								<c:if test="${comment.userid.userid == userEntry.key}">
									<c:set var="currentUser" value="${userEntry.key}"
										scope="request" />
										
									<b class="card-title">${userEntry.value}</b>
									<span class="h7 text-muted">${comment.cmtDate}</span>
								</c:if>
							</c:forEach>

							<p class="card-text small">${comment.content}</p>
						</div>
						<div class="ml-auto" style="position: relative; top: -90px;">
							<div class="dropdown">
								<button class="btn btn-link dropdown-toggle" type="button"
									id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<i class="fa fa-ellipsis-h"></i>
								</button>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="gedf-drop1">
									<div class="h6 dropdown-header">Configuration</div>
									<a class="dropdown-item" href="#">Report</a>
									<c:if test="${userid == currentUser}">
										<a class="dropdown-item"
											href="/posts/post/editCmt/${comment.cmtid}/${userid}">Update</a>
										<a class="dropdown-item"
<%-- 											href="/posts/post/cmtdelete/${comment.cmtid}/${userid}">Delete</a> --%>
											href="" onclick="deleteComment(${comment.cmtid}, ${userid});">Delete</a>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function deleteComment(cmtid, userid) {
	// Gửi yêu cầu xóa bình luận bằng AJAX
	$.ajax({
	    type: "DELETE",
	    url: "/posts/post/cmtdelete/" + cmtid + "/" + userid,
	    success: function(response) {
	        location.reload();
	    },
	    error: function() {
	        // Xử lý lỗi nếu có
	        $("#message").text("Đã xảy ra lỗi trong quá trình xóa bình luận.");
	    }
	});
	}

	
	
	</script>




	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Additional scripts go here if needed -->
</body>


</html>