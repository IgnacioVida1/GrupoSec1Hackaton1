package com.example.hackaton1.Restricciones;

import com.example.hackaton1.User.Domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LimitesUsuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private int maxTokens;
    private int maxSolicitudes;
    private String tipoVentana;

    @ManyToOne
    private User usuario;
}
