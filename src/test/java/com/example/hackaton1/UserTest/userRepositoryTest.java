package com.example.hackaton1.User.Infraestructure;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Empresa.Infraestructure.EmpresaRepository;
import com.example.hackaton1.User.Domain.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class userRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    void testGuardarYBuscarUsuario() {
        // Crear una empresa primero
        Empresa empresa = new Empresa();
        empresa.setNombre("Empresa Test");
        empresa.setStatus(true);
        empresa = empresaRepository.save(empresa);

        // Crear un usuario asociado a la empresa
        User user = new User();
        user.setNombre("Juan");
        user.setApellido("Pérez");
        user.setEmail("juan.perez@example.com");
        user.setPassword("1234");
        user.setEmpresa(empresa);

        User savedUser = userRepository.save(user);

        // Verificar que fue guardado correctamente
        Optional<User> userOptional = userRepository.findById(savedUser.getId());
        assertTrue(userOptional.isPresent());

        User fetchedUser = userOptional.get();
        assertEquals("Juan", fetchedUser.getNombre());
        assertEquals("Pérez", fetchedUser.getApellido());
        assertEquals("juan.perez@example.com", fetchedUser.getEmail());
        assertEquals("Empresa Test", fetchedUser.getEmpresa().getNombre());
    }
}
