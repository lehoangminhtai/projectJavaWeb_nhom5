<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

<style type="text/css">
body {
	background-color: #eeeeee;
	margin: 0;
	display: flex;
    min-height: 100vh;
    align-items: center;
    justify-content: center;
    margin: 0;
}

.h7 {
	font-size: 0.8rem;
}

.gedf-wrapper {
	margin-top: 0.97rem;
}

.container {
	width: 80%;
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
	<!-- SUA DAY -->
	<div class=" container col-md-6 gedf-main mx-auto">
	<!-- SUA DAY -->
		<form action="<c:url value="/posts/post/saveOrUpdate"/>"
			method="POST" enctype="multipart/form-data">
			<div class="card gedf-card">
				<div class="card-header">
				<div class="d-flex justify-content-between align-items-center">
						<h2>${post.isEdit ? 'Edit Post' :'Add New Post'}</h2>
						<%-- <form id="exit" action="<c:url value="posts/post/exit/${userid}"/>"
							method="GET"> --%>
						<a href="/posts/post/exit/${userid}"  class="close" aria-label="close position-absolute top-0 end-0 m-2" style="top:-50px">
							<span aria-hidden="true">&times;</span>
						</a>
						<!-- </form> -->
					</div>
				
					<ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							id="posts-tab" data-toggle="tab" href="#posts" role="tab"
							aria-controls="posts" aria-selected="true">Make a publication</a></li>
					</ul>
				</div>
				<div class="card-body">
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
							
							<div class="form-group">
							
							<%-- <input type="hidden" value="${post.isEdit}">
							<input type="text" value="${post.postid}"> --%>
							
							<!-- <label for="postid" class="form-label">Post ID:</label>  -->
							<input type="hidden" value="${post.isEdit}"> 
							<input type="hidden" readonly="readonly" class="form-control"
							value="${post.postid}" id="postid" name="postid"
							aria-describedby="postid" placeholder="Post Id">
							
							<input type="hidden" value="${post.datePost}">
							
								<label class="sr-only" for="message">post</label>
								<textarea class="form-control" id="message" rows="3" 
								placeholder="What are you thinking?" name ="content">${post.content}</textarea>
							</div>
							
							<script type="text/javascript">
							function chooseFile(fileInput) {
								if(fileInput.files && fileInput.files[0]){
									var reader = new FileReader();
									reader.onload = function (e){
										$('#media').attr('src',e.target.result);
									}
									reader.readAsDataURL(fileInput.files[0]);
								}
							}
							</script>

							<c:if test="${post.media != null}">
								<img id="media" src="/posts/post/images/${post.media}" class="img-fluid">
							</c:if>

							<c:if test="${post.media == null}">
								<img id="media" class="img-fluid">
							</c:if>

							<div class="form-group">
								<div class="custom-file">
									<label class="custom-file-label" for="imageFile">Upload image</label>
									<input type="file" class="custom-file-input" id="imageFile"
									name="imageFile" placeholder="Post Image" 
									onchange="chooseFile(this)" accept=".jpg, .png, .mp4">
								</div>
							</div>
							
							<div class="form-group">
						
								 <input type="hidden" placeholder="${userid}" name="userid" value="${userid}" readonly>
							</div>

							<div class="form-group" style="display: none">
								<input type="text" name="access_modifier" id="access_modifier_input" value="${post.access_modifier}" readonly>
							</div>

							<div class="py-4"></div>
						</div>
						
					</div>
					<div class="btn-toolbar justify-content-between">
						<div class="btn-group">
							<button type="submit" class="btn btn-primary">
								<c:if test="${post.isEdit}">
									<span>Update</span>
								</c:if>
								<c:if test="${!post.isEdit}">
									<span>Post</span>
								</c:if>
							</button>
						</div>
						
						<div class="btn-group">
							<button id="btnGroupDrop1" type="button"
								class="btn btn-link dropdown-toggle" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-globe"></i>
							</button>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="btnGroupDrop1">
								<a class="dropdown-item" id="public" onclick="setAccessModifier(0)"><i class="fa fa-globe"></i> Public </a>
								<a class="dropdown-item" id="friend" onclick="setAccessModifier(1)"><i class="fa fa-users"></i> Friends</a>
								<a class="dropdown-item" id="justme" onclick="setAccessModifier(2)"><i class="fa fa-user"> </i> Just me</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript"></script>
	<script>
		var access_modifier = -1;
		function setAccessModifier(value) {
			access_modifier = value;
			document.getElementById('access_modifier_input').value = access_modifier;
		}
	</script>
	
	<script>
    function setAccessModifier(accessModifier) {
      var iconElement = document.querySelector('#btnGroupDrop1 i');
      switch (accessModifier) {
        case 0:
          iconElement.className = 'fa fa-globe';
          break;
        case 1:
          iconElement.className = 'fa fa-users';
          break;
        case 2:
          iconElement.className = 'fa fa-user';
          break;
        default:
          iconElement.className = 'fa fa-globe';
      }
    }
  </script>
</body>