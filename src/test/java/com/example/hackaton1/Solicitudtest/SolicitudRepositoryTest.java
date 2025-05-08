package com.example.hackaton1.Solicitudtest;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Empresa.Infraestructure.EmpresaRepository;
import com.example.hackaton1.Solicitud.Domain.Solicitud;
import com.example.hackaton1.Solicitud.Infraestructure.SolicitudRepository;
import com.example.hackaton1.User.Domain.User;
import com.example.hackaton1.User.Infraestructure.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SolicitudRepositoryTest {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    void testGuardarYBuscarSolicitud() {
        // Crear empresa y usuario primero, porque solicitud depende de ellos
        Empresa empresa = new Empresa();
        empresa.setNombre("EmpresaTest");
        empresa.setStatus(true);
        empresa = empresaRepository.save(empresa);

        User user = new User();
        user.setNombre("Juan");
        user.setApellido("Pérez");
        user.setEmail("juan@test.com");
        user.setPassword("1234");
        user.setEmpresa(empresa);
        user = userRepository.save(user);

        // Crear solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setModelo("GPT-4");
        solicitud.setConsulta("¿Cuál es la capital de Francia?");
        solicitud.setRespuesta("París");
        solicitud.setTokensConsumidos(12);
        solicitud.setFechaHora(LocalDate.now());
        solicitud.setUser(user);
        solicitud.setEmpresa(empresa);

        Solicitud guardada = solicitudRepository.save(solicitud);
        assertNotNull(guardada.getId());

        Optional<Solicitud> encontrada = solicitudRepository.findById(guardada.getId());
        assertTrue(encontrada.isPresent());
        assertEquals("GPT-4", encontrada.get().getModelo());
        assertEquals("París", encontrada.get().getRespuesta());
    }
}

