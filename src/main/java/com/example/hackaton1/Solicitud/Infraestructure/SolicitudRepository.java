package com.example.hackaton1.Solicitud.Infraestructure;

import com.example.hackaton1.Solicitud.Domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}
