package com.example.hackaton1.User.Domain;


import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Restricciones.Domain.LimitesUsuario;
import com.example.hackaton1.Solicitud.Domain.Solicitud;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Solicitud> historial_solicitudes;

    @Column
    String reporte_consumo;

    private Boolean expired = false;
    private Boolean locked = false;
    private Boolean credentialsExpired = false;
    private Boolean enable = true;

    @ManyToOne
    private Empresa empresa;


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }


}

    @OneToMany(mappedBy = "usuario")
    private List<LimitesUsuario> limites;

    public Object getTotalTokensConsumidos() {
    }

    public Object getTotalSolicitudesRealizadas() {
    }
}
