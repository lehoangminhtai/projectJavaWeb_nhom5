<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Dashboard</title>
    <!-- Thêm đường dẫn đến Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body class="bg-light" style="background: url(https://i.pinimg.com/564x/8b/b4/33/8bb433233a9176ea6cb01298f18a0035.jpg) center center/cover">
	<a href="/posts/post/exit/${user.userid}"  class="btn btn-primary" aria-label="close position-absolute top-0 end-0 m-2" style="top:-50px">
							<span aria-hidden="true"><i class='bx bx-arrow-back' style='color:#ffffff'  ></i> Back Home</span>
						</a>
    <div class="container mt-5">
    <h1 class="text-center mt-4 mb-3"><i>Dashboard</i> <i class='bx bxs-bar-chart-alt-2' style='color:#ffffff' ></i></h1>
    	<div class="d-flex justify-content-center align-items-center">
            <img alt="" <%-- src="/posts/post/images/${user.avatar}" --%> src="https://i.pinimg.com/564x/7c/00/3f/7c003f765d1cec42ae00100084b94daf.jpg" 
            	class="img-fluid mb-3 w-25 rounded-circle mr-3">
            <div class= "card">
            	<div class= "card-body" style="background-color: #6DFCAB">
	                <h3 class="mb-1" >User:<i> ${user.fullname}</i></h3>
	                <h5 class="mb-1" style="color: #FC7777; font-family: serif;">Post Total:<i> ${numPost}</i></h5>
	                <h5 style="color: #778BFC;  font-family: serif;">Like Total:<i> ${numLike}</i></h5>
	                <h5 style="color: #A1A043;  font-family: serif;">Follower:<i> ${numLike}</i></h5>
                </div>
            </div>
        </div>
        <div class=""></div>
        <div class="row">
            <c:forEach var="i" items="${posts}">
                <div class="col-md-4 mb-4">
                    <div class="card" style="background-color: #77CDF2">
                        <div class="card-body">
                            <h5 class="card-title"style="color: white">Post ID: ${i.postid}</h5>
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
    </div>

    <!-- Thêm đường dẫn đến Bootstrap JS và Popper.js -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-Uo4uZf5YFq9skXFlz/7tfL3LeOMV+9TBEU3z6HXL9hshHoL/TEZ9nbDlVT9vGxp" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>
