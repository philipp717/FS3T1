package com.example.fs3t1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orden")
@Data
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false)
    private String estado; // PENDIENTE, PAGADA, FALLIDA

    @Column(nullable = false)
    private Integer total;

    @Column(name = "metodo_pago")
    private String metodoPago; // MERCADOPAGO, FLOW

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "preference_id")
    private String preferenceId;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrdenItem> items = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
        if (this.estado == null) this.estado = "PENDIENTE";
    }
}
