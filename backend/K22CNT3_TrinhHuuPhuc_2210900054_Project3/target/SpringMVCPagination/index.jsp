<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ - Cửa Hàng Văn Phòng Phẩm</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
        }

        /* Header */
        .thp-header {
            background: linear-gradient(90deg, #3498db, #2c3e50);
            padding: 15px 0;
            color: white;
            text-align: center;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
        }

        .thp-header .nav {
            display: flex;
            justify-content: center;
            gap: 30px;
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .thp-header .nav li {
            display: inline;
        }

        .thp-header .nav a {
            color: white;
            text-decoration: none;
            font-size: 18px;
            font-weight: bold;
            transition: 0.3s;
            padding: 10px;
        }

        .thp-header .nav a:hover {
            color: #f1c40f;
        }

        /* Nội dung chính */
        .thp-content {
            max-width: 1000px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .thp-content h2 {
            color: #3498db;
            font-weight: bold;
        }

        .thp-content p {
            font-size: 18px;
            margin-bottom: 20px;
            text-align: justify;
        }

        .thp-content .thp-btn {
            display: inline-block;
            background: #3498db;
            color: white;
            padding: 12px 25px;
            font-size: 18px;
            border-radius: 25px;
            text-decoration: none;
            transition: 0.3s;
        }

        .thp-content .thp-btn:hover {
            background: #2980b9;
        }

        /* Footer */
        .thp-footer {
            background: #2c3e50;
            color: white;
            padding: 15px;
            margin-top: 30px;
            font-size: 14px;
            text-align: center;
        }
    </style>
</head>
<body>

    <!-- Header -->
    <header class="thp-header">
        <h1 class="thp-title">Chào mừng đến với cửa hàng văn phòng phẩm</h1>
        <nav>
            <ul class="nav">
                <li><a href="#">Trang Chủ</a></li>
                <li><a href="#">Sản Phẩm</a></li>
                <li><a href="#">Liên Hệ</a></li>
                <li><a href="/SpringMVCPagination/thpmenu"><i class="fas fa-sign-in-alt"></i> Đăng Nhập</a></li>
            </ul>
        </nav>
    </header>

    <!-- Nội dung chính -->
    <section class="thp-content">
        <h2>Về Chúng Tôi</h2>
        <p>
            Cửa hàng văn phòng phẩm của chúng tôi chuyên cung cấp các sản phẩm chất lượng cao dành cho văn phòng, trường học và cá nhân. 
            Với hơn 10 năm kinh nghiệm trong lĩnh vực này, chúng tôi cam kết mang đến cho khách hàng những sản phẩm chất lượng tốt nhất với giá cả hợp lý.
        </p>
        <p>
            Tại đây, bạn có thể tìm thấy nhiều loại sản phẩm như: bút viết, sổ tay, giấy in, dụng cụ văn phòng và nhiều mặt hàng khác. Chúng tôi không chỉ cung cấp sản phẩm 
            mà còn hỗ trợ khách hàng lựa chọn các mặt hàng phù hợp với nhu cầu sử dụng. Với hệ thống quản lý kho hàng hiện đại, chúng tôi đảm bảo hàng hóa luôn có sẵn 
            và giao hàng nhanh chóng.
        </p>
        <p>
            Nếu bạn cần thêm thông tin, hãy truy cập mục **Sản phẩm** để xem các mặt hàng đang có, hoặc liên hệ với chúng tôi qua mục **Liên hệ**. Chúng tôi luôn sẵn sàng hỗ trợ!
        </p>
    </section>

    <!-- Footer -->
    <footer class="thp-footer">
        <p>&copy; 2025 Cửa Hàng Văn Phòng Phẩm. Thiết kế bởi Trịnh Hữu Phúc.</p>
    </footer>

</body>
</html>
