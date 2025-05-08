package com.example.hackaton1.Restricciones.Infraestructure;

import com.example.hackaton1.Restricciones.Domain.LimitesUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimUsuarioRepository extends JpaRepository<LimitesUsuario, Long> {
}
