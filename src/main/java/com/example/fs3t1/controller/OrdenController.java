package com.example.fs3t1.controller;

import com.example.fs3t1.model.Orden;
import com.example.fs3t1.repository.OrdenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenRepository ordenRepository;

    public OrdenController(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    // GET /ordenes - listar todas las órdenes (ordenadas por fecha desc)
    @GetMapping
    public ResponseEntity<List<Orden>> listarOrdenes() {
        return ResponseEntity.ok(ordenRepository.findAllOrdenadoPorFecha());
    }

    // GET /ordenes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerOrden(@PathVariable Long id) {
        return ordenRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /ordenes/usuario/{idUsuario} - órdenes de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Orden>> ordenesPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(ordenRepository.findByIdUsuarioOrderByFechaDesc(idUsuario));
    }

    // POST /ordenes - crear orden manualmente
    @PostMapping
    public ResponseEntity<Orden> crearOrden(@RequestBody Orden orden) {
        Orden nueva = ordenRepository.save(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // PUT /ordenes/{id} - actualizar estado de una orden
    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizarOrden(@PathVariable Long id, @RequestBody Orden datos) {
        return ordenRepository.findById(id).map(orden -> {
            if (datos.getEstado() != null) orden.setEstado(datos.getEstado());
            if (datos.getTotal() != null) orden.setTotal(datos.getTotal());
            if (datos.getMetodoPago() != null) orden.setMetodoPago(datos.getMetodoPago());
            if (datos.getIdUsuario() != null) orden.setIdUsuario(datos.getIdUsuario());
            return ResponseEntity.ok(ordenRepository.save(orden));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /ordenes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        if (!ordenRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ordenRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
