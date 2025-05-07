package com.example.hackaton1.Restricciones;

import com.example.hackaton1.Empresa.Domain.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestriccionEmpresa {

    @Id @GeneratedValue
    private Long id;
    private String modelo;
    private int maxTokensPorDia;
    private int maxSolicitudesPorSemana;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @ManyToOne
    private Empresa empresa;

    @PreUpdate
    private void preUpdate() {
        this.fechaFin = LocalDate.now();
    }
}