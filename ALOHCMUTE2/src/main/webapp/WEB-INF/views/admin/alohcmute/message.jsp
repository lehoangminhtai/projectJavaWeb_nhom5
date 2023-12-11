<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<style>
.message-box {
	border: 1px solid #ddd;
	padding: 15px;
	height: 500px;
	overflow-y: auto;
}

.message {
	padding: 10px;
	margin-bottom: 10px;
	border-radius: 10px;
	background-color: #f8f8f8;
}

.sender {
	text-align: right;
}

.sender .message {
	background-color: #d1ecf1;
}

.receiver {
	text-align: left;
}

.receiver .message {
	background-color: #d1ecf1;
}
</style>

<div class="container mt-4">
	<div class="row">
		<div class="col-md-8 offset-md-2">
			<div class="card">
				<div class="card-header">Tin Nhắn</div>
				<div class="card-body message-box">
					<c:forEach var="msg" items="${messages}">
						<div
							class="message <c:if test="${msg.sender == senderId}">sender</c:if>">
							<strong><c:choose>
									<c:when test="${msg.sender == senderId}">
										<c:out value="${sender.fullName}" /> (You)
            </c:when>
									<c:when test="${msg.sender == receiverId}">
										<c:out value="${receiver.fullName}" />
									</c:when>
								</c:choose></strong>
							<p>
								<c:out value="${msg.content}" />
							</p>
							<small><c:out value="${msg.timestamp}" /></small>
						</div>
					</c:forEach>
				</div>
				<div class="card-footer">
					<form
						action="<c:url value='/admin/alohcmute/profile/message/send'/>"
						method="post">
						<input type="hidden" name="receiverId"
							value="<c:out value='${receiverId}'/>" />
						<div class="form-group">
							<textarea class="form-control" name="messageContent"
								placeholder="Nhập tin nhắn..."></textarea>
						</div>
						<button type="submit" class="btn btn-primary">Gửi</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
