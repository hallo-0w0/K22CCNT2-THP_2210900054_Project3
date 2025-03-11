<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Chi Tiết Phiếu Nhập</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        /* Sidebar */
        .sidebar {
            width: 250px;
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            background: #343a40;
            color: white;
            padding-top: 20px;
        }

        .sidebar h2 {
            text-align: center;
            font-size: 22px;
            margin-bottom: 30px;
            color: #ffc107;
            font-weight: bold;
            letter-spacing: 1px;
        }

        .sidebar a {
            display: flex;
            align-items: center;
            padding: 12px 20px;
            font-size: 18px;
            text-decoration: none;
            color: white;
            transition: background 0.3s;
        }

        .sidebar a:hover {
            background: #575d63;
        }

        .sidebar a i {
            margin-right: 10px;
        }

        /* Nội dung chính */
        .content {
            margin-left: 260px;
            padding: 20px;
        }

        h2 {
            color: #333;
            font-size: 26px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            background: white;
            padding: 30px;
            width: 60%;
            margin: auto;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
            text-align: left;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin: 10px 0 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 16px;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .back-link {
            display: block;
            margin: 20px auto;
            width: fit-content;
            background-color: #6c757d;
            padding: 10px 20px;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .back-link:hover {
            background-color: #5a6268;
        }

        .error-message {
            color: red;
            text-align: center;
            font-size: 16px;
            margin-bottom: 10px;
        }

    </style>
</head>
<body>

    <!-- Sidebar -->
    <div class="sidebar">
        <h2>QUẢN LÝ KHO</h2>
        <a href="/SpringMVCPagination/thpmenu"><i class="fas fa-home"></i> Dashboard</a>
        <a href="/SpringMVCPagination/thpsanpham/thpview"><i class="fas fa-box"></i> Quản lý Sản Phẩm</a>
        <a href="/SpringMVCPagination/thpnhacungcap/thpview"><i class="fas fa-truck"></i> Nhà Cung Cấp</a>
        <a href="/SpringMVCPagination/thpnhanvien/thpview"><i class="fas fa-users"></i> Nhân Viên</a>
        <a href="/SpringMVCPagination/thpphieunhap/thpview"><i class="fas fa-file-import"></i> Phiếu Nhập</a>
        <a href="/SpringMVCPagination/thpchitietphieunhap/thpview"><i class="fas fa-file-import"></i> Chi Tiết Phiếu Nhập</a>
    </div>

     <!-- Nội dung chính -->
    <div class="content">
        <h2>Thêm Chi Tiết Phiếu Nhập</h2>

        <form action="${pageContext.request.contextPath}/thpchitietphieunhap/save" method="post">

            <!-- Giữ nguyên mã phiếu nhập -->
            <input type="hidden" name="thpMaPN" value="${thpMaPN}"/>

            <!-- Mã Sản Phẩm (readonly, cập nhật theo Tên Sản Phẩm) -->
            <label for="maSP">Mã Sản Phẩm:</label>
            <input type="text" id="maSP" name="thpMaSP" readonly required/>

            <!-- Chọn Tên Sản Phẩm -->
            <label for="tenSP">Tên Sản Phẩm:</label>
            <select id="tenSP" required>
                <option value="">-- Chọn sản phẩm --</option>
                <c:forEach var="sp" items="${listSanPham}">
                    <option value="${sp.thpMaSP}" data-masp="${sp.thpMaSP}">${sp.thpTenSP}</option>
                </c:forEach>
            </select>

            <label for="soLuongNhap">Số Lượng Nhập:</label>
            <input type="number" id="soLuongNhap" name="thpSoLuongNhap" min="1" required/>

            <input type="submit" value="Lưu"/>
        </form>

        <a href="/SpringMVCPagination/thpchitietphieunhap/thpview/${thpMaPN}" class="back-link">
            <i class="fas fa-arrow-left"></i> Quay lại Chi Tiết Phiếu Nhập
        </a>
    </div>

    <!-- JavaScript để tự động cập nhật Mã Sản Phẩm -->
    <script>
        document.getElementById("tenSP").addEventListener("change", function () {
            let selectedOption = this.options[this.selectedIndex];
            document.getElementById("maSP").value = selectedOption.getAttribute("data-masp");
        });
    </script>

</body>
</html>
