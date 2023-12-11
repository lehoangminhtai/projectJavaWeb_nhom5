<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi Tiết Nhóm Chat</title>
    <!-- Thêm Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2 class="mb-4">Thông Tin Nhóm: ${group.groupName}</h2>
    
    <div class="mb-3">
        <h3>Thành viên:</h3>
        <ul class="list-group">
            <c:forEach items="${group.members}" var="member">
                <li class="list-group-item">${member.fullName}</li> <!-- Giả sử bạn có trường fullName -->
            </c:forEach>
        </ul>
    </div>

    <div class="mb-3">
        <h3>Thêm Thành Viên:</h3>
        <form action="/admin/alohcmute/groupchat/addMember" method="post" class="form-inline">
            <input type="hidden" name="groupId" value="${group.groupId}" />
            <input type="text" name="userId" placeholder="ID Người Dùng" class="form-control mr-2" />
            <button type="submit" class="btn btn-primary">Thêm</button>
        </form>
    </div>

    <div class="mb-3">
        <h3>Loại Bỏ Thành Viên:</h3>
        <form action="/admin/alohcmute/groupchat/removeMember" method="post" class="form-inline">
            <input type="hidden" name="groupId" value="${group.groupId}" />
            <input type="text" name="userId" placeholder="ID Người Dùng" class="form-control mr-2" />
            <button type="submit" class="btn btn-danger">Loại Bỏ</button>
        </form>
    </div>

    <a href="/admin/alohcmute/groupmessage/view/${group.groupId}" class="btn btn-info">Xem Tin Nhắn</a>
</div>

<!-- Thêm Bootstrap JS và Popper.js -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
