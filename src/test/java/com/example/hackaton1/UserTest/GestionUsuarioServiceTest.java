package com.example.hackaton1.UserTest;

import com.example.hackaton1.DTOs.UserDTO.NewUserDto;
import com.example.hackaton1.DTOs.UserDTO.UserResponseDto;
import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Empresa.Infraestructure.EmpresaRepository;
import com.example.hackaton1.User.Domain.GestionUsuarioService;
import com.example.hackaton1.User.Infraestructure.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GestionUsuarioServiceTest {

    @Autowired
    private GestionUsuarioService gestionUsuarioService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UserRepository userRepository;

    private Empresa empresa;

    @BeforeEach
    void setUp() {
        empresa = new Empresa();
        empresa.setNombre("TestEmpresa");
        empresa.setStatus(true);
        empresa = empresaRepository.save(empresa);
    }

    @Test
    void testCrearUsuario() {
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setNombre("Ana");
        newUserDto.setApellido("López");
        newUserDto.setEmail("ana.lopez@example.com");
        newUserDto.setPassword("password123");
        newUserDto.setEmpresaId(empresa.getId());

        UserResponseDto response = gestionUsuarioService.crearUsuario(newUserDto);

        assertEquals("Ana", response.getNombre());
        assertEquals("López", response.getApellido());
        assertEquals("ana.lopez@example.com", response.getEmail());
    }

    @Test
    void testListarUsuarios() {
        crearUsuarioDePrueba("Juan", "Pérez");
        crearUsuarioDePrueba("Lucía", "Ramírez");

        List<UserResponseDto> lista = gestionUsuarioService.listarUsuarios();

        assertEquals(2, lista.size());
    }

    @Test
    void testBuscarUsuarioPorId() {
        UserResponseDto creado = crearUsuarioDePrueba("Carlos", "Torres");

        UserResponseDto encontrado = gestionUsuarioService.buscarUsuarioPorId(creado.getId());

        assertEquals("Carlos", encontrado.getNombre());
        assertEquals("Torres", encontrado.getApellido());
    }

    @Test
    void testEliminarUsuario() {
        UserResponseDto creado = crearUsuarioDePrueba("Mateo", "Silva");
    }

    private UserResponseDto crearUsuarioDePrueba(String nombre, String apellido) {
        NewUserDto dto = new NewUserDto();
        dto.setNombre(nombre);
        dto.setApellido(apellido);
        dto.setEmail(nombre.toLowerCase() + "." + apellido.toLowerCase() + "@example.com");
        dto.setPassword("1234");
        dto.setEmpresaId(empresa.getId());

        return gestionUsuarioService.crearUsuario(dto);
    }
}

