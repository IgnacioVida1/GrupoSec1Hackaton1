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

    @Autowired
    private ModelMapper modelMapper;

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

        return modelMapper.map(guardado, UserResponseDto.class);
    }

    public List<UserResponseDto> listarUsuarios() {
        List<User> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> modelMapper.map(usuario, UserResponseDto.class)).toList();
    }

    public UserResponseDto buscarUsuarioPorId(Long id) {
        User usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return modelMapper.map(usuario, UserResponseDto.class);
    }

    public UserResponseDto actualizarUsuario(Long id, NewUserDto newUserDto) {
        User usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(newUserDto.getNombre());
        usuario.setApellido(newUserDto.getApellido());
        usuario.setEmail(newUserDto.getEmail());
        usuario.setPassword(newUserDto.getPassword());

        User updatedUser = usuarioRepository.save(usuario);
        return modelMapper.map(updatedUser, UserResponseDto.class);
    }

    public <UserLimitDto> void asignarLimiteUsuario(Long id, UserLimitDto userLimitDto) {
        User usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setPassword(String.valueOf(userLimitDto.getClass()));
        usuario.setLimites(userLimitDto.getClass());

        usuarioRepository.save(usuario);
    }

    public <UserConsumptionDto> UserConsumptionDto obtenerConsumoUsuario(Long id) {
        User usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserConsumptionDto consumoDto = new UserConsumptionDto();
        consumoDto.getClass();
        consumoDto.getClass();

        return consumoDto;
    }
}
