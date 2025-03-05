<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Nhân Viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        a, input[type="submit"] {
            background-color: #007bff;
            color: white;
            text-decoration: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            border: none;
            display: inline-block;
        }
        a:hover, input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Danh Sách Nhân Viên</h2>
    <table>
        <tr>
            <th>Mã Nhân Viên</th>
            <th>Họ Tên</th>
            <th>Phòng Ban</th>
            <th>Vai Trò</th>
            <th>Hành Động</th>
        </tr>
        <c:forEach var="nv" items="${list}">
            <tr>
                <td>${nv.maNV}</td>
                <td>${nv.hoTen}</td>
                <td>${nv.phongBan}</td>
                <td>${nv.vaiTro}</td>
                <td>
                    <a href="edit/${nv.maNV}">Chỉnh Sửa</a>
                    <a href="delete/${nv.maNV}" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="form">Thêm Nhân Viên Mới</a>
    <a href="/SpringMVCPagination/menu" style="margin-left: 10px;">Quay lại Dashboard</a>
</body>
</html>
