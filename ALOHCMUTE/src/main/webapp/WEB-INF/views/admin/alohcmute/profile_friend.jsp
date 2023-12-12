<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<style>
.profile-cover {
	width: 100%;
	height: 300px; /* Kích thước ảnh bìa */
	overflow: hidden;
}

.profile-cover img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.profile-avatar {
	width: 270px; /* Kích thước của avatar */
	height: 270px;
	overflow: hidden;
	border-radius: 50%; /* Bo góc thành hình tròn */
}

.profile-avatar img {
	width: 100%;
	height: 100%;
	object-fit: cover; /* Đảm bảo hình ảnh đầy đủ trong khung */
}
</style>

<div class="main-content">
	<div class="card">
		<div class="card-header profile-cover">
			<c:choose>
				<c:when test="${not empty user.avatar}">
					<img src="<c:url value='/admin/alohcmute/images/${user.avatar}'/>"
						class="img-fluid" alt="Cover">
				</c:when>
				<c:otherwise>
					<p>No icon available.</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="card-header">Thông Tin Cá Nhân</div>
		<div class="card-body">
			<!-- Hiển thị thông báo -->
			<c:if test="${message != null}">
				<div class="alert alert-primary" role="alert">
					<i>${message}</i>
				</div>
			</c:if>
			<!-- Hết thông báo -->

			<!-- Hiển thị thông tin cá nhân -->
			<div class="row">
				<div class="col-md-3">
					<div class="profile-avatar">
						<c:choose>
							<c:when test="${not empty user.avatar}">
								<img
									src="<c:url value='/admin/alohcmute/images/${user.avatar}'/>"
									class="img-fluid rounded-circle" alt="Avatar">
							</c:when>
							<c:otherwise>
								<p>No icon available.</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="col-md-9">
					<h2>${user.fullName}</h2>
					<p>Email: ${user.email}</p>
					<p>Ngày Sinh: ${user.dateOfBirth}</p>
					<p>Giới Tính: ${user.gender}</p>
					<div class="form-group">
						<label for="bio">Bio:</label>
						<div class="bio-container">
							<div class="bio-content">
								<p>${user.bio}</p>
							</div>
						</div>
					</div>
					<p>Số Điện Thoại: ${user.phoneNumber}</p>
					<p>Địa Chỉ: ${user.address}</p>
					<p>Tình Trạng Mối Quan Hệ: ${user.relationshipStatus}</p>
					<p>Nghề Nghiệp: ${user.job}</p>
					<p>Giáo Dục: ${user.education}</p>
					<a class="btn btn-primary btn-add-friend"
					href="/admin/alohcmute/profile/addfriends/${user.userId }">Add
					Friend</a>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div id="categoryModal" class="modal">
	<div class="modal-content">
		<span class="close" onclick="closeModal()">&times;</span>
		<h2>Thông Tin Category</h2>
		<img id="categoryIcon" src="" alt="Category Icon"
			style="width: 100px; height: 100px;">
		<p id="categoryName"></p>
	</div>
</div>

<script>
	function openModal(iconSrc, categoryName) {
		document.getElementById('categoryIcon').src = iconSrc;
		document.getElementById('categoryName').innerText = categoryName;
		document.getElementById('categoryModal').style.display = 'flex';
	}

	function closeModal() {
		document.getElementById('categoryModal').style.display = 'none';
	}
</script>
