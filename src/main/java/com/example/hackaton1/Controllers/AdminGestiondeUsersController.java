package com.example.hackaton1.Controllers;


import com.example.hackaton1.DTOs.RestriccionesDto.EmpresaResResponseDto;
import com.example.hackaton1.DTOs.RestriccionesDto.newEmpresaResDto;
import com.example.hackaton1.DTOs.UserDTO.NewUserDto;
import com.example.hackaton1.DTOs.UserDTO.UserResponseDto;
import com.example.hackaton1.Restricciones.Domain.RestriccionEmpresa;
import com.example.hackaton1.User.Domain.GestionUsuarioService;
import com.example.hackaton1.User.Domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class AdminGestiondeUsersController {

    @Autowired
    private GestionUsuarioService usuarioService;

    @PostMapping
    public UserResponseDto crearUsuario(@RequestBody NewUserDto newUserDto) {
        return usuarioService.crearUsuario(newUserDto);
    }

    @GetMapping
    public List<UserResponseDto> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UserResponseDto buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
