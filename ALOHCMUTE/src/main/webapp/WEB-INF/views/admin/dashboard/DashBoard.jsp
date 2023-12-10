<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DashBoard ADMIN</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<h1 class="text-center">Dash Board ADMIN</h1>
	<h5> Total user: ${numUser}</h5>
	<h5> Total post: ${numPost}</h5>
	
	<h2></h2>

	 <div class="row">
            <c:forEach var="i" items="${postLike}">
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
        
        <div class="row">
            <c:forEach var="i" items="${postCmt}">
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

</body>
</html>