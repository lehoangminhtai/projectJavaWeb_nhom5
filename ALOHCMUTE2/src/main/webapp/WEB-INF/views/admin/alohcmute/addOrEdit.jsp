<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!-- JavaScript để xem trước ảnh -->
<script>
	function previewImage(event) {
		var reader = new FileReader();
		reader.onload = function() {
			var output = document.getElementById('imagePreview');
			output.src = reader.result;
			output.style.display = 'block'; // Hiển thị ảnh
		};
		reader.readAsDataURL(event.target.files[0]);
	}
</script>

<section class="row">
	<div class="col-6 offset-3 mt-4">
		<form action="<c:url value="/admin/alohcmute/saveOrUpdate" />"
			method="POST" enctype="multipart/form-data">
			<div class="card">
				<div class="card-header">
					<h2>${category.isEdit ? 'Edit Category' :'Add New Category'}</h2>
				</div>
				<div class="card-body">
					<div class="mb-3">
						<label for="categoryId" class="form-label">Category ID:</label> <input
							type="hidden" value="${category.isEdit}"> <input
							type="text" readonly="readonly" class="form-control"
							value="${category.categoryId}" id="categoryId" name="categoryId"
							aria-describedby="categoryIdid" placeholder="Category Id">
					</div>
					<div class="mb-3">
						<label for="categoryname" class="form-label">Category
							Name:</label> <input type="text" class="form-control"
							value="${category.categoryName}" id="categoryName"
							name="categoryName" aria-describedby="nameid"
							placeholder="Category Name">
					</div>
					<!-- Thêm input để chọn file ảnh -->
					<div class="mb-3">
						<label for="imageFile" class="form-label">Category Icon:</label> <input
							type="file" class="form-control" id="imageFile" name="imageFile"
							onchange="previewImage(event)">
						<!-- Thêm thẻ img để xem trước ảnh -->
						<img id="imagePreview" src="#" alt="Image preview"
							style="max-width: 100%; margin-top: 10px; display: none;" />
					</div>
				</div>
			</div>
			<div class="card-footer text-muted">
				<a href="<c:url value="/admin/alohcmute/add"/>"
					class="btn btn-secondary"><i class="fas fa-new"></i> New</a> <a
					href="<c:url value="/admin/alohcmute" />" class="btn btn-success"><i
					class="fas fa-bars"></i> List Categories</a>
				<button class="btn btn-primary" type="submit">
					<i class="fas fa-save"></i>
					<c:if test="${category.isEdit}">
						<span>Update</span>
					</c:if>
					<c:if test="${!category.isEdit}">
						<span>Save</span>
					</c:if>
				</button>
			</div>
		</form>
	</div>
</section>
