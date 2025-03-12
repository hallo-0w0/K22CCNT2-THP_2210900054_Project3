// Khởi tạo giỏ hàng từ LocalStorage nếu có
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Cập nhật giỏ hàng khi trang tải xong
document.addEventListener('DOMContentLoaded', function () {
    updateCartUI();
    displayCart(); // Hiển thị giỏ hàng ngay khi tải trang
});

// Thêm sản phẩm vào giỏ hàng
document.querySelectorAll('.thp-add-cart').forEach(button => {
    button.addEventListener('click', function () {
        let productElement = this.closest('.thp-product');
        let productName = productElement.getAttribute('data-name');
        let productPrice = parseInt(productElement.getAttribute('data-price'));

        let existingItem = cart.find(item => item.name === productName);
        if (existingItem) {
            existingItem.quantity++;
        } else {
            cart.push({ name: productName, price: productPrice, quantity: 1 });
        }

        saveCart();
        updateCartUI();
        alert('Sản phẩm đã được thêm vào giỏ hàng!');
    });
});

// Hiển thị giỏ hàng khi bấm nút
document.getElementById('thp-cart-btn').addEventListener('click', function () {
    document.getElementById('thp-cart-modal').style.display = 'flex';
    displayCart();
});

// Đóng giỏ hàng
document.getElementById('thp-close-cart').addEventListener('click', function () {
    document.getElementById('thp-cart-modal').style.display = 'none';
});

// Hiển thị giỏ hàng
function displayCart() {
    let cartList = document.getElementById('thp-cart-list');
    let cartTotal = document.getElementById('thp-cart-total');
    if (!cartList || !cartTotal) return; // Kiểm tra nếu thiếu modal giỏ hàng

    cartList.innerHTML = '';
    let total = 0;

    cart.forEach((item, index) => {
        total += item.price * item.quantity;
        let li = document.createElement('li');
        li.innerHTML = `${item.name} x${item.quantity} - ${item.price * item.quantity} VNĐ
            <button onclick="removeFromCart(${index})">Xóa</button>`;
        cartList.appendChild(li);
    });

    cartTotal.innerText = total;
    updateCartUI();
}

// Xóa sản phẩm khỏi giỏ hàng
function removeFromCart(index) {
    cart.splice(index, 1);
    saveCart();
    displayCart();
}

// Cập nhật số lượng giỏ hàng
function updateCartUI() {
    let cartCount = document.getElementById('thp-cart-count');
    if (cartCount) {
        cartCount.innerText = cart.reduce((sum, item) => sum + item.quantity, 0);
    }
}

// Lưu giỏ hàng vào LocalStorage
function saveCart() {
    localStorage.setItem('cart', JSON.stringify(cart));
}
