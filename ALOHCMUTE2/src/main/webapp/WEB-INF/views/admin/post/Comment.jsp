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
	<div class=" container col-md-10 gedf-main mx-auto "
		id="commentsSection">
		<div class="container mt-5 ">
			<div class="row card mb-3 ">
				<div class="col-md-15 card-body ">
					<div class="d-flex justify-content-between align-items-center">
						<h2 class="col text-center">Bài viết của @${ownername}</h2>
						<form id="exitForm" action="<c:url value="exit/${userid}"/>"
							method="POST">
							<button type="submit" class="close"
								aria-label="close position-absolute top-0 end-0 m-2"
								style="top: -50px">
								<span aria-hidden="true">&times;</span>
							</button>
						</form>
					</div>

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
					<form id="LoadCmt" action="<c:url value='/posts/post/cmtSave'/>"
						method="post">
						<input type="hidden" name="postid" value="${post.postid}">
						<input type="hidden" name="userid" value="${userid}">
						<div class="form-group d-flex align-items-center">
							<input type="text" class="form-control" id="idcontent"
								name="content" placeholder="Nhập bình luận" required
								value="${cmtModel.content}">
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
					<div id="comment_${comment.cmtid}" class="card mb-3 d-flex">
						<div class="card-body">

							<c:forEach var="userEntry" items="${user}">
								<c:if test="${comment.userid.userId == userEntry.key}">
									<c:set var="currentUser" value="${userEntry.key}"
										scope="request" />
									<img
										src="<c:url value='/admin/alohcmute/images/${comment.userid.avatar}'/>"
										class="rounded-circle" alt="Avatar"
										style="width: 40px; height: 40px;">
									<h5 class="card-title">${comment.userid.fullName}</h5>
								</c:if>
							</c:forEach>

							<p class="card-text">${comment.content}</p>
							<p class="card-text small">${comment.cmtDate}</p>
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
											<%-- 											href="javascript:void(0);" onclick="showEditComment(${comment.cmtid}, ${userid}, '${comment.content}')">Update</a> --%>
<%-- <%-- 											href="javascript:void(0);" onclick="editComment(${comment.cmtid}, ${userid});">Update</a> --%>
											href="/posts/post/editCmt/${comment.cmtid}/${userid}">Update</a>
										<a class="dropdown-item" href=""
											onclick="deleteComment(${comment.cmtid}, ${userid});">Delete</a>

									</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
    Additional scripts go here if needed
    
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- <script type="text/javascript">
	    $(document).ready(function () {    	 
	         $('#LoadCmt').submit(function (event) {
	            // Prevent the default form submission
	            event.preventDefault();
	
	            // Perform AJAX request
	            $.ajax({
	                type: 'POST',
	                url: $(this).attr('action'),
	                data: $(this).serialize(),
	                success: function (response) {
	                    location.reload();
	                	
	                },
	                error: function (error) {
	                    // Handle errors if needed
	                    alert("Error: " + error.responseText);
	                }
	            });
	        }); 
	    });
	</script> 
     -->
	<!--   <script type="text/javascript">
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
	</script> -->

	<!-- <script type="text/javascript">
	
	function showEditComment(cmtid, userid, currentContent) {
		var inputHtml = '<input type="text" class="edit-input" value="' + currentContent + '">';
        inputHtml += '<button class="save-edit" onclick="editComment(' + cmtid + ', ' + userid + ')">Lưu</button>';
        
        var commentLink = document.querySelector('a[href="/posts/post/editCmt/'+cmtid+'/'+userid+'"]');
        commentLink.parentNode.innerHTML = inputHtml;
	}
	
	
	 function editComment(cmtid, userid) {
		var editedContent = document.querySelector('.eidt-input').value;
	$.ajax({
	    type: "POST",
	    url: "/posts/post/cmtSave",
	    data: {cmtid: cmtid, userid: userid, editedContent: editedContent},
	    success: function(response) {
// 	    	location.reload();
			
			var commentDiv = document.querySelector('div[data-comment-id="' + cmtid + '"]');
			commentDiv.innerHTML = '<p>' + editedContent + '</p>';
            commentDiv.innerHTML += '<button class="edit-comment" onclick="showEditInput(' + cmtid + ', ' + userid + ', \'' + editedContent + '\')">Chỉnh sửa</button>';
	    },
	    error: function() {
	        // Xử lý lỗi nếu có
	        $("#message").text("Đã xảy ra lỗi trong quá trình chỉnh sửa bình luận.");
	    }
	});
	}
	</script>
	  -->
	<!-- <script type="text/javascript">
	function getComment(cmtid, userid) {
	$.ajax({
	    type: "GET",
	    url: "/posts/post/editCmt/" + cmtid +"/" + userid,
	    success: function(response) {
	        document.getElementById('idcontent').textContent = ${content}
	    	location.reload();
	    },
	    error: function() {
	        // Xử lý lỗi nếu có
	        $("#message").text("Đã xảy ra lỗi trong quá trình chỉnh sửa bình luận.");
	    }
	});
	}
	</script> -->
</body>


</html>