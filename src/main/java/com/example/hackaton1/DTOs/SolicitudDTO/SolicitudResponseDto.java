package com.example.hackaton1.DTOs.SolicitudDTO;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.User.Domain.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SolicitudResponseDto {

    String prompt;

    String respuesta;

    String modelo;

    int tokensConsumidos;

    LocalDate fechaHora;

    User usuario;

    Empresa empresa;
}
