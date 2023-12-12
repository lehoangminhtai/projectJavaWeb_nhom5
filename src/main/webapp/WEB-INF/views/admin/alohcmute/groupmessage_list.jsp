<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<style>


.message-box {
	max-height: 400px;
	overflow-y: scroll;
	scrollbar-width: thin;
	scrollbar-color: #007bff #e0e0e0;
	background-color: #ffffff;
	border-radius: 10px;
	padding: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.message {
	display: flex;
	margin-bottom: 15px;
}

.sender-avatar {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	margin-right: 10px;
}

.message-content {
	background-color: #007bff;
	color: #fff;
	border-radius: 10px;
	padding: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.message-text {
	flex-grow: 1;
}

.sender-info {
	display: flex;
	align-items: center;
}

.sender-name {
	color: #007bff;
	font-weight: bold;
	margin-right: 10px;
}

.message-timestamp {
	font-size: 12px;
	color: #6c757d;
}

.message-form {
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	margin-top: 20px;
	padding: 15px;
}

.form-control {
	border-radius: 10px;
}

.btn-send {
	background-color: #007bff;
	border-color: #007bff;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	color: #fff;
	font-weight: bold;
}

.btn-send:hover {
	background-color: #0056b3;
}
</style>


<div class="container">
	<h2 class="mb-4">Tin Nhắn Nhóm</h2>
	<div class="message-box">
		<c:forEach var="msg" items="${messages}">
			<div class="message">
				<img class="sender-avatar" src="<c:url value='/admin/alohcmute/images/${msg.sender.avatar}'/>" alt="Avatar">
				<div class="message-text">
					<div class="sender-info">
						<span class="sender-name">${msg.sender.fullName}</span> <small
							class="message-timestamp">${msg.timestamp}</small>
					</div>
					<p class="message-content">${msg.content}</p>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="message-form">
		<h3>Gửi Tin Nhắn:</h3>
		<form action="/admin/alohcmute/groupmessage/send" method="post">
			<input type="hidden" name="groupChatId" value="${groupId}" />
			<div class="form-group">
				<textarea name="content" class="form-control"
					placeholder="Nhập tin nhắn..." rows="3"></textarea>
			</div>
			<button type="submit" class="btn btn-send btn-primary">Gửi</button>
		</form>
	</div>
</div>

<!-- Thêm Bootstrap JS và Popper.js -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

