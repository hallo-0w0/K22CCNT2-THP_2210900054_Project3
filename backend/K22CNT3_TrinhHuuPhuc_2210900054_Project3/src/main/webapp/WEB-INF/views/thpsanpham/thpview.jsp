<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Sản Phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

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

        table {
            width: 100%;
            margin: 0 auto;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        td a {
            background-color: #007bff;
            color: white;
            text-decoration: none;
            padding: 6px 10px;
            border-radius: 5px;
            margin-right: 5px;
            display: inline-block;
        }

        td a:hover {
            background-color: #0056b3;
        }

        .actions {
            margin-top: 20px;
            text-align: center;
        }

        .actions a {
            background-color: #007bff;
            color: white;
            text-decoration: none;
            padding: 10px 16px;
            border-radius: 5px;
            margin: 5px;
            display: inline-block;
        }

        .actions a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="sidebar">
	    <h2>QUẢN LÝ Cửa Hàng Văn Phòng Phẩm</h2>
	    <a href="/SpringMVCPagination/thpmenu"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
	    <a href="/SpringMVCPagination/thpsanpham/thpview"><i class="fas fa-box"></i> Quản lý Sản Phẩm</a>
	    <a href="/SpringMVCPagination/thpnhacungcap/thpview"><i class="fas fa-truck"></i> Nhà Cung Cấp</a>
	    <a href="/SpringMVCPagination/thpnhanvien/thpview"><i class="fas fa-users"></i> Nhân Viên</a>
	    <a href="/SpringMVCPagination/thpphieunhap/thpview"><i class="fas fa-file-import"></i> Phiếu Nhập</a>
	    <a href="/SpringMVCPagination/thpchitietphieunhap/thpview"><i class="fas fa-file-export"></i> Chi Tiết Phiếu Nhập</a>
	    <a href="/SpringMVCPagination/thptaikhoan/thpview"><i class="fas fa-user"></i> Quản lý Tài Khoản</a>
	
	    <!-- Quay lại trang chủ -->
	    <a href="http://localhost:8080/SpringMVCPagination/" class="back-home">
	        <i class="fas fa-arrow-left"></i> Quay Lại Trang Chủ
	    </a>
	</div>

    <div class="content">
        <h2>Danh Sách Sản Phẩm</h2>

        <table>
            <tr>
                <th>Mã Sản Phẩm</th>
                <th>Tên Sản Phẩm</th>
                <th>Loại Sản Phẩm</th>
                <th>Đơn Vị Tính</th>
                <th>Giá Nhập</th>
                <th>Số Lượng Tồn</th>
                <th>Hành Động</th>
            </tr>
            <c:forEach var="sp" items="${list}">
                <tr>
                    <td>${sp.thpMaSP}</td>
                    <td>${sp.thpTenSP}</td>
                    <td>${sp.thpTenLoai}</td>
                    <td>${sp.thpDonViTinh}</td>
                    <td>${sp.thpGiaNhap}</td>
                    <td>${sp.thpSoLuongTon}</td>
                    <td>
					    <a href="${pageContext.request.contextPath}/thpsanpham/thpedit/${sp.thpMaSP}">
					        <i class="fas fa-edit"></i> Chỉnh Sửa
					    </a>
					    <a href="${pageContext.request.contextPath}/thpsanpham/thpdelete/${sp.thpMaSP}" 
					       onclick="return confirm('Bạn có chắc muốn xóa?')">
					        <i class="fas fa-trash-alt"></i> Xóa
					    </a>
					</td>

                </tr>
            </c:forEach>
        </table>

        <div class="actions">
            <a href="thpform"><i class="fas fa-plus"></i> Thêm Sản Phẩm Mới</a>
            <a href="/SpringMVCPagination/thpmenu"><i class="fas fa-arrow-left"></i> Quay lại Dashboard</a>
        </div>
    </div>
</body>
</html>
