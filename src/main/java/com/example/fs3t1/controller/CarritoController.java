package com.example.fs3t1.controller;

import com.example.fs3t1.model.Producto;
import com.example.fs3t1.service.CarritoService;
import com.example.fs3t1.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;
    private final ProductoService productoService;

    public CarritoController(CarritoService carritoService, ProductoService productoService) {
        this.carritoService = carritoService;
        this.productoService = productoService;
    }

    @PostMapping("/agregar/{id}")
    public String agregar(@PathVariable Long id, HttpSession session) {
        Producto producto = productoService.listarProductos().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        carritoService.agregar(session, producto);
        return "redirect:/";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        carritoService.eliminar(session, id);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciar(HttpSession session) {
        carritoService.vaciar(session);
        return "redirect:/carrito";
    }

    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> count(HttpSession session) {
        return ResponseEntity.ok(Map.of("count", carritoService.contarItems(session)));
    }
}
