package com.example.fs3t1.service;

import com.example.fs3t1.model.CarritoItem;
import com.example.fs3t1.model.Producto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private static final String SESSION_KEY = "carrito";

    @SuppressWarnings("unchecked")
    public List<CarritoItem> obtenerCarrito(HttpSession session) {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute(SESSION_KEY);
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute(SESSION_KEY, carrito);
        }
        return carrito;
    }

    public void agregar(HttpSession session, Producto producto) {
        List<CarritoItem> carrito = obtenerCarrito(session);
        Optional<CarritoItem> existente = carrito.stream()
                .filter(i -> i.getProductoId().equals(producto.getId()))
                .findFirst();
        if (existente.isPresent()) {
            existente.get().setCantidad(existente.get().getCantidad() + 1);
        } else {
            carrito.add(new CarritoItem(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getDuracion(),
                    1
            ));
        }
    }

    public void eliminar(HttpSession session, Long productoId) {
        List<CarritoItem> carrito = obtenerCarrito(session);
        carrito.removeIf(i -> i.getProductoId().equals(productoId));
    }

    public void vaciar(HttpSession session) {
        session.removeAttribute(SESSION_KEY);
    }

    public Integer calcularTotal(HttpSession session) {
        return obtenerCarrito(session).stream()
                .mapToInt(CarritoItem::getSubtotal)
                .sum();
    }

    public int contarItems(HttpSession session) {
        return obtenerCarrito(session).stream()
                .mapToInt(CarritoItem::getCantidad)
                .sum();
    }
}
