package com.example.fs3t1.controller;

import com.example.fs3t1.model.CarritoItem;
import com.example.fs3t1.model.Orden;
import com.example.fs3t1.model.OrdenItem;
import com.example.fs3t1.model.Producto;
import com.example.fs3t1.repository.OrdenRepository;
import com.example.fs3t1.repository.ProductoRepository;
import com.example.fs3t1.service.CarritoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pago")
public class PagoController {

    private final CarritoService carritoService;
    private final OrdenRepository ordenRepository;
    private final ProductoRepository productoRepository;

    public PagoController(CarritoService carritoService,
                          OrdenRepository ordenRepository,
                          ProductoRepository productoRepository) {
        this.carritoService = carritoService;
        this.ordenRepository = ordenRepository;
        this.productoRepository = productoRepository;
    }

    @PostMapping("/mercadopago")
    public String iniciarMercadoPago(HttpSession session, RedirectAttributes attrs) {
        List<CarritoItem> items = carritoService.obtenerCarrito(session);
        if (items.isEmpty()) return "redirect:/carrito";

        try {
            Orden orden = crearOrden(items, "MERCADOPAGO", (Long) session.getAttribute("usuarioId"));
            carritoService.vaciar(session);
            return "redirect:/pago-exitoso?orden=" + orden.getId() + "&metodo=mercadopago";
        } catch (Exception e) {
            attrs.addFlashAttribute("error", "Error al procesar el pago: " + e.getMessage());
            return "redirect:/checkout";
        }
    }

    @PostMapping("/flow")
    public String iniciarFlow(HttpSession session, RedirectAttributes attrs) {
        List<CarritoItem> items = carritoService.obtenerCarrito(session);
        if (items.isEmpty()) return "redirect:/carrito";

        // Flow requiere credenciales de producción/sandbox.
        // En un entorno real se debe integrar con la API REST de Flow (https://www.flow.cl/docs)
        // usando apiKey y secretKey provistos por Flow.
        try {
            Orden orden = crearOrden(items, "FLOW", (Long) session.getAttribute("usuarioId"));
            carritoService.vaciar(session);
            // Redirigir a pago exitoso simulado (reemplazar con integración real de Flow)
            return "redirect:/pago-exitoso?orden=" + orden.getId() + "&metodo=flow";
        } catch (Exception e) {
            attrs.addFlashAttribute("error", "Error al conectar con Flow: " + e.getMessage());
            return "redirect:/checkout";
        }
    }

    private Orden crearOrden(List<CarritoItem> items, String metodoPago, Long idUsuario) {
        Orden orden = new Orden();
        orden.setMetodoPago(metodoPago);
        orden.setEstado("PENDIENTE");
        orden.setIdUsuario(idUsuario);
        int total = 0;
        for (CarritoItem ci : items) {
            total += ci.getSubtotal();
        }
        orden.setTotal(total);
        ordenRepository.save(orden);

        for (CarritoItem ci : items) {
            Producto p = productoRepository.findById(ci.getProductoId()).orElseThrow();
            OrdenItem oi = new OrdenItem(p, orden, ci.getCantidad());
            orden.getItems().add(oi);
        }
        return ordenRepository.save(orden);
    }
}
