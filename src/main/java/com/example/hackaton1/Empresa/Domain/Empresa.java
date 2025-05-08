package com.example.hackaton1.Empresa.Domain;

import com.example.hackaton1.Restricciones.RestriccionEmpresa;
import com.example.hackaton1.Solicitud.Domain.Solicitud;
import com.example.hackaton1.User.Domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Empresa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String RUC;

    @Column(nullable = false)
    private LocalDate fecha_afiliacion;

    @Column(nullable = false)
    private Boolean status;

    @OneToOne(cascade = CascadeType.ALL)
    private User admin;

    @OneToMany(mappedBy = "empresa")
    private List<User> lista_usuarios;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Solicitud> lista_solicitudes;

    @OneToMany(mappedBy = "empresa")
    private List<RestriccionEmpresa> restricciones;
}
