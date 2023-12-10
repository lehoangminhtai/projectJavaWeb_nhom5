<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/taglib.jsp"%>

<section class="row">
	<div class="col mt-4">
		<div class="card">
			<div class="card-header">List Post</div>
			<div class="card-body">
				<!-- Hiển thông báo -->
				<c:if test="${message != null}">
					<div class="alert alert-primary" role="alert">
						<i>${message}</i>
					</div>
				</c:if>
				<!-- Hêt thông báo -->
				<a href="/posts/post/add">Add Post</a>
				<table class="table table-striped table-responsive">
					<thead class="thead-inverse">
						<tr>
							<th>postid</th>
							<th>userid</th>
							<th>content</th>
							<th>media</th>
							<th>access_modifier</th>
							<th>postDate</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${post}" var="post">
							<tr>
								<td scope="row">${post.postid}</td>
								<td>${post.userid}</td>
								<td>${post.content}</td>
								<td>
								<c:if test="${post.media != null}">
								<img src="/posts/post/images/${post.media}" width="70" class="img-fluid" alt="">
								</c:if>
								
								<c:if test="${post.media == null}">
								<img src="/templates/images/noimage.png" width="70" class="img-fluid" alt="">
								</c:if>
								</td>
								<td>${post.access_modifier}</td>
								<td>${post.postDate}</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</section>