<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Sản Phẩm</title>
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
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin: 10px 0 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .btn-action {
            width: 32%;
            margin: 5px;
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
	
	    <!-- Kiểm tra trạng thái đăng nhập -->
        <%
            if (session.getAttribute("loggedUser") != null) {
        %>
            <a href="${pageContext.request.contextPath}/thplogin/thplogout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
        <%
            } else {
        %>
            <a href="${pageContext.request.contextPath}/thplogin/thpview"><i class="fas fa-sign-in-alt"></i> Đăng nhập</a>
        <%
            }
        %>

	</div>

    <div class="content">
        <h2>Thêm Sản Phẩm</h2>
        <form action="${pageContext.request.contextPath}/thpsanpham/save" method="post">
            <label>Tên Sản Phẩm:</label>
            <input type="text" name="thpTenSP" required />

            <label>Loại Sản Phẩm:</label>
			<select id="thpMaLoai" name="thpMaLoai">
			    <c:forEach var="loaiSP" items="${listLoaiSP}">
			        <option value="${loaiSP.thpMaLoai}">${loaiSP.thpTenLoai}</option>
			    </c:forEach>
			</select>
			
            <!-- Quản lý loại sản phẩm -->
			<input type="text" id="thpTenLoaiMoi" placeholder="Nhập loại sản phẩm mới..." />
			<button type="button" class="btn btn-primary" onclick="themLoaiSanPham()">Thêm loại</button>

            <label>Đơn Vị Tính:</label>
            <input type="text" name="thpDonViTinh" required />

            <label>Giá Nhập:</label>
            <input type="number" step="0.01" name="thpGiaNhap" required />

            <label>Số Lượng Tồn:</label>
            <input type="number" name="thpSoLuongTon" required />

            <input type="submit" value="Lưu" class="btn btn-success" />
        </form>

        <a href="/SpringMVCPagination/thpsanpham/thpview" class="btn btn-secondary mt-3">
            <i class="fas fa-arrow-left"></i> Quay lại
        </a>

        <!-- Script AJAX -->
        <script>
        function themLoaiSanPham() {
            var tenLoaiMoi = document.getElementById("thpTenLoaiMoi").value.trim();
            if (tenLoaiMoi === "") {
                alert("Vui lòng nhập loại sản phẩm!");
                return;
            }

            fetch("${pageContext.request.contextPath}/thpsanpham/addLoai", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded", "Accept": "application/json" },
                body: "thpTenLoaiMoi=" + encodeURIComponent(tenLoaiMoi)
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("Thêm loại sản phẩm thành công!");
                    location.reload(); 
                } else {
                    alert("Thêm loại thất bại!");
                }
            })
            .catch(error => console.error("Lỗi:", error));
        }

        </script>
    </div>
</body>
</html>
