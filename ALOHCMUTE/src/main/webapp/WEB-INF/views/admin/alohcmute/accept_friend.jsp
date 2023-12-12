<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<style>
    .friend-card {
        margin-bottom: 20px;
        border: 1px solid #ddd;
        border-radius: 10px;
        background-color: #fff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .friend-avatar {
        width: 80px;
        height: 80px;
        overflow: hidden;
        border-radius: 50%;
        margin-right: 15px;
    }

    .friend-avatar img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .friend-info {
        display: flex;
        align-items: center;
        padding: 15px;
    }

    .friend-name {
        font-size: 18px;
        font-weight: bold;
        margin: 0;
    }

    .friend-actions {
        padding: 15px;
        border-top: 1px solid #ddd;
        display: flex;
        justify-content: flex-end;
    }

    .btn-add-friend {
        background-color: #007bff;
        color: #fff;
        border: none;
        padding: 8px 16px;
        border-radius: 5px;
        cursor: pointer;
        margin-left: 10px;
    }

    .btn-add-friend:hover {
        background-color: #0056b3;
    }
</style>

<div class="container mt-4">
    <h2 class="mb-4">List Friends Request</h2>

    <c:forEach var="friends" items="${friends}">
        <div class="friend-card">
            <div class="friend-info">
                <div>
                    <p class="friend-name">Mã người dùng: ${friends.user1}</p>
                    <p class="friend-name">Trạng thái: ${friends.status}</p>
                    <!-- Thêm các thông tin khác tùy thuộc vào yêu cầu -->
                </div>
            </div>

            <div class="friend-actions">
                <a class="btn btn-primary btn-add-friend" href="/admin/alohcmute/find_friendsById/${friends.user1 }">Chi tiết</a>
                <!-- Thêm các nút hoặc hành động khác tùy thuộc vào yêu cầu -->
            </div>
        </div>
    </c:forEach>
</div>
