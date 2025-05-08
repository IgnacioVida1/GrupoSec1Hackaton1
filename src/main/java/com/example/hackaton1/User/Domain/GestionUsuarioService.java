package com.example.hackaton1.User.Domain;

import com.example.hackaton1.DTOs.UserDTO.NewUserDto;
import com.example.hackaton1.DTOs.UserDTO.UserResponseDto;
import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Empresa.Infraestructure.EmpresaRepository;
import com.example.hackaton1.User.Infraestructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionUsuarioService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public UserResponseDto crearUsuario(NewUserDto newUserDto) {
        Long empresaId = newUserDto.getEmpresaId();

        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        User usuario = new User();
        usuario.setNombre(newUserDto.getNombre());
        usuario.setApellido(newUserDto.getApellido());
        usuario.setEmail(newUserDto.getEmail());
        usuario.setPassword(newUserDto.getPassword());
        usuario.setEmpresa(empresa);

        User guardado = usuarioRepository.save(usuario);

        return convertirADto(guardado);
    }

    public List<UserResponseDto> listarUsuarios() {
        List<User> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::convertirADto).toList();
    }

    public UserResponseDto buscarUsuarioPorId(Long id) {
        User usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertirADto(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UserResponseDto convertirADto(User usuario) {
        return new UserResponseDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getEmpresa().getId()
        );
    }

}
