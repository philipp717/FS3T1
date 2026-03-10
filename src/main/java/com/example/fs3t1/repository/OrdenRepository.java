package com.example.fs3t1.repository;

import com.example.fs3t1.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Orden findByPreferenceId(String preferenceId);
    List<Orden> findByIdUsuarioOrderByFechaDesc(Long idUsuario);
    @Query("SELECT o FROM Orden o ORDER BY o.fecha DESC")
    List<Orden> findAllOrdenadoPorFecha();
}
