package com.example.fs3t1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden_detalle")
@Data
@NoArgsConstructor
public class OrdenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private Orden orden;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer precio;

    public OrdenItem(Producto producto, Orden orden, Integer cantidad) {
        this.producto = producto;
        this.orden = orden;
        this.cantidad = cantidad;
        this.precio = producto.getPrecio() * cantidad;
    }
}
