package com.example.fs3t1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItem implements Serializable {
    private Long productoId;
    private String nombre;
    private Integer precio;
    private Integer duracion;  // minutos
    private Integer cantidad;

    public Integer getSubtotal() {
        return precio * cantidad;
    }
}
