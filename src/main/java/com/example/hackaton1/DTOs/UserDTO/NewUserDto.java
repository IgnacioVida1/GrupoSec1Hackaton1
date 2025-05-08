package com.example.hackaton1.DTOs.UserDTO;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Solicitud.Domain.Solicitud;
import com.example.hackaton1.User.Domain.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class NewUserDto {
    Role role;

    String nombre;

    String apellido;

    String email;

    String password;

    Empresa empresa;

    String Reporte_consumo;
    Long empresaId;
}
