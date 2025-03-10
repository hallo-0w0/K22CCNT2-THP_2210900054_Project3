<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Sản Phẩm & Loại Sản Phẩm</title>
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
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            padding: 12px 20px;
            font-size: 16px;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
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

        .ql-loai-san-pham {
            background: #fff;
            padding: 25px;
            margin-top: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .ql-loai-san-pham h2 {
            color: #007bff;
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: 600;
        }

        .ql-loai-san-pham select,
        .ql-loai-san-pham input {
            width: 80%;
            padding: 12px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .ql-loai-san-pham input {
            text-align: center;
        }

        .ql-loai-san-pham .btn {
            width: 48%;
            margin: 5px;
            padding: 12px;
            font-size: 16px;
            border-radius: 5px;
        }

        .ql-loai-san-pham .btn-warning {
            background-color: #ffc107;
        }

        .ql-loai-san-pham .btn-warning:hover {
            background-color: #e0a800;
        }

        .ql-loai-san-pham .btn-danger {
            background-color: #dc3545;
        }

        .ql-loai-san-pham .btn-danger:hover {
            background-color: #c82333;
        }

        @media (max-width: 768px) {
            .content {
                margin-left: 0;
                padding: 10px;
            }

            form,
            .ql-loai-san-pham {
                width: 100%;
                padding: 20px;
            }
        }

    </style>
</head>
<body>

    <div class="sidebar">
        <h2>QUẢN LÝ KHO</h2>
        <a href="/SpringMVCPagination/thpmenu"><i class="fas fa-home"></i> Dashboard</a>
        <a href="/SpringMVCPagination/thpsanpham/thpview"><i class="fas fa-box"></i> Quản lý Sản Phẩm</a>
        <a href="/SpringMVCPagination/thpnhacungcap/thpview"><i class="fas fa-truck"></i> Nhà Cung Cấp</a>
        <a href="/SpringMVCPagination/thpnhanvien/thpview"><i class="fas fa-users"></i> Nhân Viên</a>
        <a href="/SpringMVCPagination/thpphieunhap/thpview"><i class="fas fa-file-import"></i> Phiếu Nhập</a>
        <a href="/SpringMVCPagination/thpphieuxuat/thpview"><i class="fas fa-file-export"></i> Phiếu Xuất</a>
        <a href="#"><i class="fas fa-chart-bar"></i> Báo Cáo - Thống Kê</a>
    </div>

    <div class="content">
        <h2>Chỉnh Sửa Sản Phẩm</h2>
        <form action="${pageContext.request.contextPath}/thpsanpham/thpeditsave" method="post">
            <input type="hidden" name="thpMaSP" value="${command.thpMaSP}" />
            <label>Tên Sản Phẩm:</label>
            <input type="text" name="thpTenSP" value="${command.thpTenSP}" required />

            <label>Loại Sản Phẩm:</label>
            <select name="thpMaLoai">
                <c:forEach var="loaiSP" items="${listLoaiSP}">
                    <option value="${loaiSP.thpMaLoai}" ${loaiSP.thpMaLoai == command.thpMaLoai ? 'selected' : ''}>
                        ${loaiSP.thpTenLoai}
                    </option>
                </c:forEach>
            </select>

            <label>Đơn Vị Tính:</label>
            <input type="text" name="thpDonViTinh" value="${command.thpDonViTinh}" required />

            <label>Giá Nhập:</label>
            <input type="number" step="0.01" name="thpGiaNhap" value="${command.thpGiaNhap}" required />

            <label>Số Lượng Tồn:</label>
            <input type="number" name="thpSoLuongTon" value="${command.thpSoLuongTon}" required />

            <input type="submit" value="Lưu" class="btn btn-success" />
        </form>

        <div class="ql-loai-san-pham">
            <h2>Quản Lý Loại Sản Phẩm</h2>
            <select id="thpMaLoaiEdit">
                <c:forEach var="loaiSP" items="${listLoaiSP}">
                    <option value="${loaiSP.thpMaLoai}">${loaiSP.thpTenLoai}</option>
                </c:forEach>
            </select>

            
            <button type="button" class="btn btn-danger" onclick="xoaLoaiSanPham()">Xóa</button>

            <a href="${pageContext.request.contextPath}/thpsanpham/thpview" class="btn btn-secondary mt-3">
                <i class="fas fa-arrow-left"></i> Quay lại
            </a>
        </div>

        <script>
	        
            function xoaLoaiSanPham() {
                var select = document.getElementById("thpMaLoaiEdit");
                var thpMaLoai = select.value;

                if (!confirm("Bạn có chắc chắn muốn xóa loại sản phẩm này?")) {
                    return;
                }

                fetch("${pageContext.request.contextPath}/thpsanpham/deleteLoai?thpMaLoai=" + thpMaLoai, {
                    method: "DELETE"
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert("Xóa thành công!");
                        location.reload(); 
                    } else {
                        alert("Không xóa được. Loại sản phẩm đang được sử dụng!");
                    }
                })
                .catch(error => console.error("Lỗi:", error));
            }
        </script>
    </div>
</body>
</html>
