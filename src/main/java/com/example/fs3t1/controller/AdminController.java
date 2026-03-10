package com.example.fs3t1.controller;

import com.example.fs3t1.model.Orden;
import com.example.fs3t1.model.Producto;
import com.example.fs3t1.model.Usuario;
import com.example.fs3t1.repository.OrdenRepository;
import com.example.fs3t1.repository.UsuarioRepository;
import com.example.fs3t1.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrdenRepository ordenRepository;
    private final ProductoService productoService;
    private final UsuarioRepository usuarioRepository;

    public AdminController(OrdenRepository ordenRepository, ProductoService productoService, UsuarioRepository usuarioRepository) {
        this.ordenRepository = ordenRepository;
        this.productoService = productoService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping({"", "/"})
    public String dashboard(Model model) {
        List<Orden> ordenes = ordenRepository.findAllOrdenadoPorFecha();
        Map<Long, String> usuarios = usuarioRepository.findAll().stream()
                .collect(Collectors.toMap(Usuario::getId, Usuario::getNombre));
        int totalFacturado = ordenes.stream().mapToInt(Orden::getTotal).sum();
        long ordensPagadas = ordenes.stream().filter(o -> "PAGADA".equals(o.getEstado())).count();

        model.addAttribute("ordenes", ordenes);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalFacturado", totalFacturado);
        model.addAttribute("totalOrdenes", ordenes.size());
        model.addAttribute("ordenesPagadas", ordensPagadas);
        return "admin/index";
    }

    @GetMapping("/servicios")
    public String servicios(Model model, RedirectAttributes attrs) {
        model.addAttribute("servicios", productoService.listarProductos());
        return "admin/servicios";
    }

    @PostMapping("/servicios/nuevo")
    public String crearServicio(@RequestParam String nombre,
                                @RequestParam(required = false) String descripcion,
                                @RequestParam Integer precio,
                                @RequestParam Integer duracion,
                                RedirectAttributes attrs) {
        Producto p = new Producto(null, nombre, descripcion, precio, duracion);
        productoService.agregarProducto(p);
        attrs.addFlashAttribute("exito", "Servicio \"" + nombre + "\" creado correctamente");
        return "redirect:/admin/servicios";
    }

    @PostMapping("/servicios/eliminar/{id}")
    public String eliminarServicio(@PathVariable Long id, RedirectAttributes attrs) {
        productoService.obtenerPorId(id).ifPresent(p -> {
            productoService.eliminar(id);
            attrs.addFlashAttribute("exito", "Servicio \"" + p.getNombre() + "\" eliminado");
        });
        return "redirect:/admin/servicios";
    }
}
