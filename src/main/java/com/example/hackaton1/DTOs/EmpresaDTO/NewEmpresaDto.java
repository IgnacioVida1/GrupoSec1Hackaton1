package com.example.hackaton1.DTOs.EmpresaDTO;

import com.example.hackaton1.User.Domain.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewEmpresaDto {

    private String nombre;

    private Long RUC;

    private User admin;

    private Boolean status;

    private LocalDateTime fecha_afiliacion;
}
