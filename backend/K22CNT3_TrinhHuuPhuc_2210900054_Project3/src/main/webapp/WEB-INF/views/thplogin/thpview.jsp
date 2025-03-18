<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .login-container {
            width: 400px;
            margin: 100px auto;
            background: white;
            padding: 30px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
        }

        label {
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .btn-login {
            width: 100%;
            background-color: #007bff;
            color: white;
            padding: 12px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
        }

        .btn-login:hover {
            background-color: #0056b3;
        }

        .register-link {
            text-align: center;
            display: block;
            margin-top: 15px;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h2>Đăng Nhập</h2>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/thplogin/thpauth" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="thpEmail" required>

            <label for="matKhau">Mật Khẩu:</label>
            <input type="password" id="matKhau" name="thpMatKhau" required>

            <button type="submit" class="btn-login">Đăng Nhập</button>
        </form>

        <a href="/SpringMVCPagination/thpregister/thpview" class="register-link">Chưa có tài khoản? Đăng ký ngay</a>
        
	    <!-- Quay lại trang chủ -->
	    <a href="http://localhost:8080/SpringMVCPagination/" class="back-home">
	        <i class="fas fa-arrow-left"></i> Quay Lại Trang Chủ
	    </a>
    </div>

</body>
</html>
