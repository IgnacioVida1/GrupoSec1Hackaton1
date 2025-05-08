package com.example.hackaton1.User.Domain;


import com.example.hackaton1.DTOs.UserDTO.NewUserDto;
import com.example.hackaton1.Empresa.Domain.EmpresaService;
import com.example.hackaton1.User.Infraestructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ModelMapper modelMapper;

    public NewUserDto crearUsuario(NewUserDto dto) {
        User user = new User();
        modelMapper.map(dto, user);
        user.setEmail(dto.getEmail());
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setPassword(dto.getPassword());
        user.setReporte_consumo(dto.getReporte_consumo());
        user.setEmpresa(dto.getEmpresa());
        userRepository.save(user);
        return dto;

    }

    public List<NewUserDto> listarUsuarios() {
        List<User> users = userRepository.findAll();
        List<NewUserDto> dtos = new ArrayList<>();
        for (User user : users) {
            NewUserDto dto = modelMapper.map(user, NewUserDto.class);
        }
        return dtos;
    }

    public NewUserDto obtenerUsuario(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return modelMapper.map(user, NewUserDto.class);
    }

    public NewUserDto actualizarUsuario(Long id, NewUserDto dto) {
        User user = userRepository.findById(id).orElse(null);
        modelMapper.map(dto, user);
        return modelMapper.map(user, NewUserDto.class);
    }


}