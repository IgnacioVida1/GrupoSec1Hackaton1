package com.example.hackaton1.Restricciones.Domain;

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

    private ModelType modelo;

    private int maxTokens;

    private int maxSolicitudes;

    @ManyToOne
    private User usuario;
}
