package com.example.hackaton1.Controllers;

import com.example.hackaton1.DTOs.RestriccionesDto.EmpresaResResponseDto;
import com.example.hackaton1.DTOs.RestriccionesDto.newEmpresaResDto;
import com.example.hackaton1.Restricciones.Domain.AdminRestriccionService;
import com.example.hackaton1.Restricciones.Domain.RestriccionEmpresa;
import com.example.hackaton1.User.Domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company/restrictions")
public class AdminRestriccionController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminRestriccionService adminRestriccionService;

    @PostMapping
    public ResponseEntity<RestriccionEmpresa> createRestriccion(@RequestBody newEmpresaResDto dto, @AuthenticationPrincipal User user) {
        RestriccionEmpresa restriccion = modelMapper.map(dto, RestriccionEmpresa.class);

        RestriccionEmpresa restriccionCreated = adminRestriccionService.createRestriction(restriccion, user);

        return new ResponseEntity<>(restriccionCreated,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResResponseDto>> getRestricciones(@AuthenticationPrincipal User user) {
        List<RestriccionEmpresa> restricciones = adminRestriccionService.getAllRestrictions(user);

        List<EmpresaResResponseDto> restriccionesDto = restricciones.stream().map(restriccionEmpresa -> modelMapper.map(restriccionEmpresa, EmpresaResResponseDto.class)).toList();

        return new ResponseEntity<>(restriccionesDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResResponseDto> updateRestriction(
            @PathVariable Long id,
            @RequestBody newEmpresaResDto dto,
            @AuthenticationPrincipal User user) {

        RestriccionEmpresa updatedRestriction = modelMapper.map(dto, RestriccionEmpresa.class);

        RestriccionEmpresa restriction = adminRestriccionService.updateRestriction(id, updatedRestriction, user);

        EmpresaResResponseDto restrictionDto = modelMapper.map(restriction, EmpresaResResponseDto.class);

        return new ResponseEntity<>(restrictionDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestriction(@PathVariable Long id, @AuthenticationPrincipal User user) {
        // Eliminar la restricción por ID
        adminRestriccionService.deleteRestriction(id, user);

        // Retornamos una respuesta vacía con estado 204 (No Content)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
