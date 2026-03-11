package com.example.fs3t1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column
    private String nombre;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT 'USUARIO'")
    private String rol = "USUARIO"; // ADMIN, USUARIO
}
