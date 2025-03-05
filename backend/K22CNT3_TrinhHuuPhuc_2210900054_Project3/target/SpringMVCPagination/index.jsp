<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Nhân sự</title>
    
    <!-- Thêm Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #eef2f3;
            color: #333;
        }
        .header, .footer {
            background-color: #2c3e50;
            color: #ecf0f1;
            text-align: center;
            padding: 15px 0;
        }
        .content {
            padding: 20px;
            max-width: 800px;
            margin: 30px auto;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 8px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        a {
            display: inline-block;
            margin-top: 10px;
            color: #fff;
            background-color: #3498db;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Chào mừng đến Quản lý Nhân Sự</h1>
    </div>

    <div class="content">
        <c:set var="message" value="Xin chào, JSP!" />
        <p>${message}</p>
        <a href="/SpringMVCPagination/menu">Đến Menu Chức Năng</a>
    </div>

    <div class="footer">
        <p>&copy; 2025 Trang Web Quản Lý Nhân Sự. Được phát triển với tình yêu!</p>
    </div>
</body>
</html>
