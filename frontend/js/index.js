let cart = [];
document.querySelectorAll('.thp-add-cart').forEach(button => {
    button.addEventListener('click', function() {
        let productElement = this.closest('.thp-product');
        let productName = productElement.getAttribute('data-name');
        let productPrice = parseInt(productElement.getAttribute('data-price'));

        let existingItem = cart.find(item => item.name === productName);
        if (existingItem) {
            existingItem.quantity++;
        } else {
            cart.push({ name: productName, price: productPrice, quantity: 1 });
        }

        updateCartUI();
        alert('Sản phẩm đã được thêm vào giỏ hàng!');
    });
});

document.getElementById('thp-cart-btn').addEventListener('click', function() {
    document.getElementById('thp-cart-modal').style.display = 'flex';
    displayCart();
});

document.getElementById('thp-close-cart').addEventListener('click', function() {
    document.getElementById('thp-cart-modal').style.display = 'none';
});

function displayCart() {
    let cartList = document.getElementById('thp-cart-list');
    let cartTotal = document.getElementById('thp-cart-total');
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
    document.getElementById('thp-cart-count').innerText = cart.length;
}

function removeFromCart(index) {
    cart.splice(index, 1);
    displayCart();
    updateCartUI();
}

function updateCartUI() {
    document.getElementById('thp-cart-count').innerText = cart.length;
}
