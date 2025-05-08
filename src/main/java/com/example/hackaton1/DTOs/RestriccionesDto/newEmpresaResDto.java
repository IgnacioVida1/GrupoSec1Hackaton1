package com.example.hackaton1.DTOs.RestriccionesDto;


import com.example.hackaton1.Restricciones.Domain.ModelType;
import lombok.Data;

@Data
public class newEmpresaResDto {

    private ModelType modelo;

    private Integer maxSolicitudesPorDia;

    private Integer maxTokensPorDia;
}
