<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Cửa hàng Văn phòng phẩm</title>
    
    <!-- Thêm Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f4f6f9;
            color: #333;
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* Đảm bảo chiều cao tối thiểu là 100% màn hình */
        }

        .header {
            background: linear-gradient(to right, #007bff, #6610f2);
            color: #fff;
            text-align: center;
            padding: 20px 0;
            font-size: 24px;
            font-weight: bold;
        }

        .content {
            flex: 1; /* Đẩy footer xuống dưới nếu nội dung không đủ dài */
            padding: 30px;
            max-width: 800px;
            margin: 40px auto;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
            text-align: center;
        }

        .content h2 {
            font-size: 26px;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 15px;
        }

        .content p {
            font-size: 18px;
            color: #555;
            margin-bottom: 20px;
        }

        .btn-group {
            display: flex;
            justify-content: center;
            gap: 15px; /* Khoảng cách giữa các nút */
            margin-top: 20px;
        }

        .btn-custom {
            color: #fff;
            background: linear-gradient(to right, #28a745, #20c997);
            padding: 12px 30px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            font-size: 18px;
            transition: background 0.3s, transform 0.2s;
            display: inline-block;
            text-align: center;
            min-width: 150px; /* Giữ kích thước nút đồng đều */
        }

        .btn-custom:hover {
            background: linear-gradient(to right, #218838, #17a2b8);
            transform: scale(1.05);
        }

        .footer {
            background: linear-gradient(to right, #343a40, #6c757d);
            color: #fff;
            text-align: center;
            padding: 15px 0;
            margin-top: auto; /* Đẩy footer xuống dưới cùng */
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Quản lý Cửa hàng Văn phòng phẩm</h1>
    </div>

    <div class="content">
        <h2>Chào mừng!</h2>
        <c:set var="message" value="Chào mừng bạn đến với hệ thống quản lý cửa hàng!" />
        <p>${message}</p>

        <!-- Nhóm các nút vào một div để căn giữa và tạo khoảng cách hợp lý -->
        <div class="btn-group">
            <a href="/SpringMVCPagination/thpmenu" class="btn-custom">Vào Hệ Thống</a>
            <a href="http://127.0.0.1:5500/frontend/html/index.html" class="btn-custom">Quay lại</a>
        </div>
    </div>

    <div class="footer">
        <p>&copy; 2025 Quản lý Cửa hàng Văn phòng phẩm. All rights reserved.</p>
    </div>
</body>
</html>
