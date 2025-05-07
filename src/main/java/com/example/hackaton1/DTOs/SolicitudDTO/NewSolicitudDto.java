package com.example.hackaton1.DTOs.SolicitudDTO;

import com.example.hackaton1.User.Domain.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewSolicitudDto {

    String prompt;

    String respuesta;

    String modelo;

    LocalDate fecha;

    User usuario;
}
