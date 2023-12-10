<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tạo Nhóm Chat</title>
    <!-- Thêm các link Bootstrap CSS -->
    <link rel="stylesheet" href="path-to-bootstrap-css">
</head>
<body>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-header">Tạo Nhóm Chat Mới</div>
                <div class="card-body">
                    <form action="/admin/alohcmute/groupchat/create" method="post">
                        <div class="form-group">
                            <label for="groupName">Tên Nhóm:</label>
                            <input type="text" class="form-control" id="groupName" name="groupName" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Tạo Nhóm</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Thêm các script Bootstrap JS -->
<script src="path-to-bootstrap-js"></script>

</body>
</html>
