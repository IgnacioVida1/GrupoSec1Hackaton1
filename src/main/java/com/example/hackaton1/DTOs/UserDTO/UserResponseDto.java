package com.example.hackaton1.DTOs.UserDTO;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Solicitud.Domain.Solicitud;
import com.example.hackaton1.User.Domain.Role;
import lombok.Data;

import java.util.List;


@Data
public class UserResponseDto {
    Role role;

    String nombre;

    String apellido;

    String email;

    List<Solicitud> lista_solicitudes;

    Empresa empresa;

    public UserResponseDto(Long id, String nombre, String apellido, String email, Long id1) {
    }

    public Long getId() {
        return null;
    }
}
