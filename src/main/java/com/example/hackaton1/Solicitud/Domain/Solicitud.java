package com.example.hackaton1.Solicitud.Domain;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.User.Domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
public class Solicitud {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String consulta;
    private String respuesta;
    private int tokensConsumidos;
    private LocalDate fechaHora;

    @ManyToOne
    private User user;

    @ManyToOne
    private Empresa empresa;
}
