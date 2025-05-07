package com.example.hackaton1.DTOs.EmpresaDTO;

import com.example.hackaton1.Solicitud.Domain.Solicitud;
import com.example.hackaton1.User.Domain.User;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaResponseDto {

    private String nombre;

    private Long RUC;

    private Boolean status;

    private User admin;

    private List<User> users;

    private List<Solicitud> lista_solicitudes;
}
