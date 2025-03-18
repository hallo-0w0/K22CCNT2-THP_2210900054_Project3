<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Quản Lý Kho</title>
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
		    color: #ffc107; /* Đồng bộ màu tiêu đề */
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

        .dashboard-title {
            text-align: center;
            font-size: 26px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        /* Box thống kê */
        .stat-box {
            background: white;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        .stat-box i {
            font-size: 40px;
            color: #007bff;
        }

        .stat-box h3 {
            margin-top: 10px;
            font-size: 22px;
        }

        .stat-box p {
            font-size: 18px;
            font-weight: bold;
            color: #333;
        }

        /* Link dạng block */
        .stat-box a {
            text-decoration: none;
            color: inherit;
            display: block;
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


    <!-- Nội dung chính -->
    <div class="content">
        <h2 class="dashboard-title">Dashboard Quản Lý Kho</h2>

        <!-- Box thống kê -->
        <div class="row">
            <div class="col-md-4 mb-3">
                <a href="/SpringMVCPagination/thpsanpham/thpview">
                    <div class="stat-box">
                        <i class="fas fa-box"></i>
                        <h3 class="text-dark">Tổng Sản Phẩm</h3>
                        <p class="text-muted">${totalSanPham}</p>
                    </div>
                </a>
            </div>

            <div class="col-md-4 mb-3">
                <a href="/SpringMVCPagination/thpnhacungcap/thpview">
                    <div class="stat-box">
                        <i class="fas fa-truck"></i>
                        <h3 class="text-dark">Nhà Cung Cấp</h3>
                        <p class="text-muted">${totalNhaCungCap}</p>
                    </div>
                </a>
            </div>

            <div class="col-md-4 mb-3">
                <a href="/SpringMVCPagination/thpnhanvien/thpview">
                    <div class="stat-box">
                        <i class="fas fa-user"></i>
                        <h3 class="text-dark">Nhân Viên</h3>
                        <p class="text-muted">${totalNhanVien}</p>
                    </div>
                </a>
            </div>

            <div class="col-md-4 mb-3">
                <a href="/SpringMVCPagination/thpphieunhap/thpview">
                    <div class="stat-box">
                        <i class="fas fa-file-import"></i>
                        <h3 class="text-dark">Phiếu Nhập</h3>
                        <p class="text-muted">${totalPhieuNhap}</p>
                    </div>
                </a>
            </div>

            <div class="col-md-4 mb-3">
                <a href="/SpringMVCPagination/thpchitietphieunhap/thpview">
                    <div class="stat-box">
                        <i class="fas fa-file-import"></i>
                        <h3 class="text-dark">Chi Tiết Phiếu Nhập</h3>
                        <p class="text-muted">${totalChiTietPhieuNhap}</p>
                    </div>
                </a>
            </div>
            
            <div class="col-md-4 mb-3">
                <a href="/SpringMVCPagination/thptaikhoan/thpview">
                    <div class="stat-box">
                        <i class="fas fa-user"></i>
                        <h3 class="text-dark">Tài Khoản</h3>
                        <p class="text-muted">${totalTaiKhoan}</p>
                    </div>
                </a>
            </div>
        </div>
    </div>

</body>
</html>