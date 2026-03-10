// Actualizar badge del carrito sin recargar página
document.querySelectorAll('.btn-add').forEach(btn => {
    btn.addEventListener('click', function () {
        const badge = document.querySelector('.cart-badge');
        if (badge) {
            const current = parseInt(badge.textContent) || 0;
            badge.textContent = current + 1;
            badge.style.transform = 'scale(1.3)';
            setTimeout(() => badge.style.transform = 'scale(1)', 200);
        }
    });
});
