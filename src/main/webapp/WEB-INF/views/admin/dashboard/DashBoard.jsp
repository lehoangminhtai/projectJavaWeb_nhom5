<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DashBoard ADMIN </title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background: url(https://i.pinimg.com/564x/8b/b4/33/8bb433233a9176ea6cb01298f18a0035.jpg) center center/cover">
	
	<h1 class="text-center" style ="color:white; margin-bottom: 50px;"> <u> Dash Board ADMIN</u> <i class='bx bxs-bar-chart-alt-2' style='color:#ffffff' ></i></h1>
	
	<div class= "d-flex align-items-center justify-content-evenly ">
	<div class="col-md-4 mb-4">
		<div class="card" style="background-color: #77CDF2; height: 200px;" >
			<div class="card-body">
				<h4 class = "text-center mb-2" style="color: white">User</h4>
				<div class="d-flex align-items-center justify-content-between ">
					<img alt="" class="img-fluid mb-3 w-25 rounded-circle mr-3" src="https://static.vecteezy.com/system/resources/thumbnails/004/607/791/small/man-face-emotive-icon-smiling-male-character-in-blue-shirt-flat-illustration-isolated-on-white-happy-human-psychological-portrait-positive-emotions-user-avatar-for-app-web-design-vector.jpg">
					<h5> Total: <i> ${numUser}</i></h5>
				</div>
			</div>
		</div>
	</div>
	

	<div class="col-md-4 mb-4">
		<div class="card" style="background-color: #77CDF2; height: 200px;">
			<div class="card-body">
				<h4 class = "text-center mb-2" style="color: white">Post</h4>
				<div class="d-flex align-items-center justify-content-between ">
					<img alt="" class="img-fluid mb-3 w-50  mr-3" src="https://media.sproutsocial.com/uploads/2022/04/Best-times-to-post-2022_BTTP-Social-Media.jpg">
					<h5> Total: <i>${numPost}</i></h5>
				</div>
			</div>
		</div>
	</div>
	</div>
	<h3 class = "text-center" style="margin: 55px 0px">Trending  <i class='bx bx-trending-up'></i></h3>
	<h5 style="margin: 35px 0"> Top Like</h5>
	 <div class="row">
            <c:forEach var="i" items="${postLike}">
                <div class="col-md-4 mb-4">
                    <div class="card" style="background-color: #77CDF2">
                        <div class="card-body">
                        	<c:forEach var="user" items="${userTopLike}">
								<c:if test="${i.userid.userId == user.key}">
									<h4>User: @<i style="color: white">${user.value}</i></h4>
								</c:if>
							</c:forEach>
							<hr>
                            <h5 class="card-title" >Post ID:<i style="color: white"> ${i.postid}</i></h5>
                            <p class="card-text small" style="margin-top: -10px">Last update: ${i.postDate}</p>
                            <p class="card-text">${i.content}</p>
                            <img src="/posts/post/images/${i.media}" class="img-fluid mb-1 w-65" >
                            <div class="d-flex justify-content-between">
								<c:forEach var="likecount" items="${likecount}">
									<c:if test="${i == likecount.key}">
										<a class="card-link" ><i class="fa fa-gittip" style="color: white"></i>(${likecount.value})</a>
									</c:if>
								</c:forEach>

								 <c:forEach var="cmtcount" items="${cmtcount}">
									<c:if test="${i == cmtcount.key}">
										<a class="card-link" ><i class="fa fa-comment" style="color: white"></i>
											(${cmtcount.value})</a>
									</c:if>
								</c:forEach>
							</div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <h5 style="margin: 35px 0"> Top Comment</h5>
        <div class="row">
            <c:forEach var="i" items="${postCmt}">
                <div class="col-md-4 mb-4">
                    <div class="card" style="background-color: #77CDF2">
                        <div class="card-body">
							<c:forEach var="user" items="${userTopCmt}">
								<c:if test="${i.userid.userId == user.key}">
									<h4>User: @<i style="color: white">${user.value}</i></h4>
								</c:if>
							</c:forEach>
							<hr>
                            <h5 class="card-title" >Post ID:<i style="color: white"> ${i.postid}</i></h5>
                            <p class="card-text small" style="margin-top: -10px">Last update: ${i.postDate}</p>
                            <p class="card-text">${i.content}</p>
                            <img src="/posts/post/images/${i.media}" class="img-fluid mb-1 w-65" >
                            <div class="d-flex justify-content-between">
								<c:forEach var="likecount" items="${likecount}">
									<c:if test="${i == likecount.key}">
										<a class="card-link" ><i class="fa fa-gittip" style="color: white"></i>(${likecount.value})</a>
									</c:if>
								</c:forEach>

								<c:forEach var="cmtcount" items="${cmtcount}">
									<c:if test="${i == cmtcount.key}">
										<a class="card-link" ><i class="fa fa-comment" style="color: white"></i>
											(${cmtcount.value})</a>
									</c:if>
								</c:forEach> 
							</div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

</body>
</html>