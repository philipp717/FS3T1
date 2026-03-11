package com.example.fs3t1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long id;

    @Column
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column
    private Integer precio;

    /** Duración en minutos */
    @Column
    private Integer duracion;
}
