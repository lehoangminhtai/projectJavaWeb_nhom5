<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<div class="main-content">
	<form action="<c:url value="/admin/alohcmute/profile/UpdateUser" />"
			method="POST" enctype="multipart/form-data">
		<div class="form-group">
			<label for="userID">Mã người dùng:</label> <input type="text"
				readonly="readonly" class="form-control" id="userId" name="userId"
				value="${user.userId}" placeholder="User Id">
		</div>

		<div class="form-group">
			<label for="username">Username:</label> <input type="text"
				readonly="readonly" class="form-control" id="username"
				name="username" value="${user.username}" placeholder="User Name">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				readonly="readonly" class="form-control" id="password"
				name="password" value="${user.password}" placeholder="User Password">
		</div>

		<div class="form-group">
			<label for="email">Email:</label> <input type="text"
				class="form-control" id="email"
				name="email" value="${user.email}" placeholder="User Email">
		</div>

		<div class="form-group">
			<label for="fullName">Họ và Tên:</label> <input type="text"
				class="form-control" id="fullName" name="fullName"
				value="${user.fullName}" placeholder="User Full Name">
		</div>

		<div class="form-group">
			<label for="dateOfBirth">Ngày Sinh:</label> <input type="date"
				class="form-control" id="dateOfBirth" name="dateOfBirth"
				value="${user.dateOfBirth}">
		</div>

		<div class="form-group">
			<label for="gender">Giới Tính:</label> <select class="form-control"
				id="gender" name="gender">
				<option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Nam</option>
				<option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Nữ</option>
				<option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Khác</option>
			</select>
		</div>

		<div class="form-group">
			<label for="bio">Bio:</label>
			<textarea class="form-control" id="bio" name="bio">${user.bio}</textarea>
		</div>

		<div class="form-group">
			<label for="phoneNumber">Số Điện Thoại:</label> <input type="text"
				class="form-control" id="phoneNumber" name="phoneNumber"
				value="${user.phoneNumber}" placeholder="User Number">
		</div>

		<div class="form-group">
			<label for="address">Địa Chỉ:</label> <input type="text"
				class="form-control" id="address" name="address"
				value="${user.address}" placeholder="User Address">
		</div>

		<div class="form-group">
			<label for="relationshipStatus">Tình Trạng Mối Quan Hệ:</label> <input
				type="text" class="form-control" id="relationshipStatus"
				name="relationshipStatus" value="${user.relationshipStatus}"
				placeholder="User Relationship Status">
		</div>

		<div class="form-group">
			<label for="job">Nghề Nghiệp:</label> <input type="text"
				class="form-control" id="job" name="job" value="${user.job}">
		</div>

		<div class="form-group">
			<label for="education">Giáo Dục:</label> <input type="text"
				class="form-control" id="education" name="education"
				value="${user.education}" placeholder="User Job">
		</div>

		<div class="form-group">
			<label for="avatarFile">Ảnh Đại Diện:</label> <input type="file"
				class="form-control" id="avatarFile" name="avatarFile"
				onchange="previewImage(event)"> <img id="imagePreview"
				src="#" alt="Image preview"
				style="max-width: 100%; margin-top: 10px;">
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" class="btn btn-primary">Cập Nhật</button>
	</form>
</div>

<script>
	function previewImage(event) {
		var input = event.target;
		var preview = document.getElementById('imagePreview');

		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				preview.src = e.target.result;
			};
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
