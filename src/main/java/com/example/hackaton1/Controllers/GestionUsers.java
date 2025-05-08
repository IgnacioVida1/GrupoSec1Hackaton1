package com.example.hackaton1.Controllers;



import com.example.hackaton1.DTOs.UserDTO.NewUserDto;
import com.example.hackaton1.DTOs.UserDTO.UserResponseDto;
import com.example.hackaton1.Restricciones.Domain.LimitesUsuario;
import com.example.hackaton1.User.Domain.GestionUsuarioService;
import com.example.hackaton1.User.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class GestionUsers {

    @Autowired
    private GestionUsuarioService gestionUsuarioService;

    @PostMapping
    public ResponseEntity<UserResponseDto> crearUsuario(@RequestBody NewUserDto newUserDto) {
        UserResponseDto userResponseDto = gestionUsuarioService.crearUsuario(newUserDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> listarUsuarios() {
        List<UserResponseDto> usuarios = gestionUsuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> buscarUsuarioPorId(@PathVariable Long id) {
        UserResponseDto usuario = gestionUsuarioService.buscarUsuarioPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> actualizarUsuario(@PathVariable Long id, @RequestBody NewUserDto newUserDto) {
        UserResponseDto updatedUser = gestionUsuarioService.actualizarUsuario(id, newUserDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/{id}/limits")
    public ResponseEntity<Void> asignarLimiteUsuario(@PathVariable Long id, @RequestBody LimitesUsuario LimitDto) {
        gestionUsuarioService.asignarLimiteUsuario(id, LimitDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<UserResponseDto> obtenerConsumoUsuario(@PathVariable Long id) {
        UserResponseDto consumo = gestionUsuarioService.obtenerConsumoUsuario(id);
        return new ResponseEntity<>(consumo, HttpStatus.OK);
    }
}
