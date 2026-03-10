package com.example.fs3t1.controller;

import com.example.fs3t1.repository.OrdenRepository;
import com.example.fs3t1.service.CarritoService;
import com.example.fs3t1.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final ProductoService productoService;
    private final CarritoService carritoService;
    private final OrdenRepository ordenRepository;

    public WebController(ProductoService productoService, CarritoService carritoService, OrdenRepository ordenRepository) {
        this.productoService = productoService;
        this.carritoService = carritoService;
        this.ordenRepository = ordenRepository;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("carritoCount", carritoService.contarItems(session));
        return "index";
    }

    @GetMapping("/carrito")
    public String carrito(Model model, HttpSession session) {
        model.addAttribute("items", carritoService.obtenerCarrito(session));
        model.addAttribute("total", carritoService.calcularTotal(session));
        model.addAttribute("carritoCount", carritoService.contarItems(session));
        return "carrito";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        if (carritoService.contarItems(session) == 0) {
            return "redirect:/carrito";
        }
        model.addAttribute("items", carritoService.obtenerCarrito(session));
        model.addAttribute("total", carritoService.calcularTotal(session));
        model.addAttribute("carritoCount", carritoService.contarItems(session));
        return "checkout";
    }

    @GetMapping("/pago-exitoso")
    public String pagoExitoso(Model model, HttpSession session) {
        model.addAttribute("carritoCount", 0);
        return "pago-exitoso";
    }

    @GetMapping("/pago-error")
    public String pagoError(Model model, HttpSession session) {
        model.addAttribute("carritoCount", carritoService.contarItems(session));
        return "pago-error";
    }

    @GetMapping("/mi-cuenta")
    public String miCuenta(Model model, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        model.addAttribute("ordenes", ordenRepository.findByIdUsuarioOrderByFechaDesc(usuarioId));
        model.addAttribute("carritoCount", carritoService.contarItems(session));
        return "mi-cuenta";
    }
}
