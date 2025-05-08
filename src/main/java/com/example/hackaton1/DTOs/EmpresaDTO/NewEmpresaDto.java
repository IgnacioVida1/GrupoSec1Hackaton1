package com.example.hackaton1.DTOs.EmpresaDTO;

import com.example.hackaton1.User.Domain.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class NewEmpresaDto {

    private String nombre;

    private String RUC;

    private User admin;

    private Boolean status;

    private LocalDate fecha_afiliacion;
}
