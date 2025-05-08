package com.example.hackaton1.Restricciones.Domain;

import com.example.hackaton1.Empresa.Domain.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestriccionEmpresa {

    @Id @GeneratedValue
    private Long id;

    private ModelType modelo;

    private Integer maxTokensPorDia;

    private Integer maxSolicitudesPorDia;

    @ManyToOne
    private Empresa empresa;

}