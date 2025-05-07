package com.example.hackaton1.User.Domain;


import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Restricciones.LimitesUsuario;
import com.example.hackaton1.Solicitud.Domain.Solicitud;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    Role role;

    @Column(nullable = false)
    String nombre;

    @Column(nullable = false)
    String apellido;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Column(nullable = false)
    private List<Solicitud> historial_solicitudes;

    @ManyToOne
    private Empresa empresa;

    @OneToMany(mappedBy = "usuario")
    private List<LimitesUsuario> limites;
}
