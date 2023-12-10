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

</style>
</head>

<body class="bg-light">
	<div class=" container col-md-10 gedf-main mx-auto ">
		<div class="container mt-5 ">
			<div class="row card mb-3 ">
				<div class="col-md-15 card-body ">

					
				<h5>EditComments</h5>

				</div>
			</div>

			<div class="row">
				<div class="">
					<form action="<c:url value='/posts/post/cmtSave'/>" method="post">
						<input type="hidden" name="isEdit" value="${cmtModel.isEdit}">
						<input type="hidden" name="userid" value="${user}">
						<input type="hidden" name="postid" value="${post.postid}">
<%-- 						<input type="hidden" name="cmtDate" value="${cmtModel.cmtDate}"> --%>
						<div class="form-group d-flex align-items-center">
							<input type="text" value = "${cmtModel.content}" class="form-control" id="content"
							 name="content" placeholder="Nhập bình luận" required>
							<button type="submit" class="btn btn-primary ml-2">
								<i class='bx bx-send' style='color: #ffffff'></i>
							</button>
						</div>
					</form>
				</div>
			</div>
			
		</div>
	</div>

	
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Additional scripts go here if needed -->
</body>


</html>