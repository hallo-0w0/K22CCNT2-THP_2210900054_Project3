<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Nhân Viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        form {
            background: white;
            padding: 20px;
            width: 50%;
            margin: auto;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            padding: 10px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Thêm Nhân Viên</h2>
    <form action="save" method="post">
        Họ Tên: <input type="text" name="hoTen" required/><br/>
        Phòng Ban: <input type="text" name="phongBan" required/><br/>
        Vai Trò:
        <select name="vaiTro" required>
            <option value="NHAN_VIEN">Nhân Viên</option>
            <option value="NHAN_VIEN_KHO">Nhân Viên Kho</option>
            <option value="QUAN_LY">Quản Lý</option>
        </select><br/>
        <input type="submit" value="Lưu"/>
        <br/><br/>
        <a href="/SpringMVCPagination/nhanvien/view" style="background-color: #6c757d; padding: 10px; color: white; text-decoration: none; border-radius: 5px;">Quay lại Danh Sách Nhân Viên</a>       
    </form>
</body>
</html>
