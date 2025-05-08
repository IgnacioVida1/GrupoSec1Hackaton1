package com.example.hackaton1.Controllers;

import com.example.hackaton1.DTOs.EmpresaDTO.EmpresaResponseDto;
import com.example.hackaton1.DTOs.EmpresaDTO.NewEmpresaDto;
import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Empresa.Domain.EmpresaService;
import com.example.hackaton1.Solicitud.Domain.Solicitud;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class SuperAdminController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> crearUnaEmpresa(@RequestBody NewEmpresaDto empresa) {
        Empresa newEmpresa = new Empresa();

        modelMapper.map(empresa, newEmpresa);

        empresaService.crearEmpresa(newEmpresa);
        return ResponseEntity.ok(newEmpresa);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDto>> listarEmpresas() {
        List<Empresa> lista = empresaService.listarEmpresas();

        List<EmpresaResponseDto> empresaResponseList = new ArrayList<>();

        for (Empresa empresa : lista) {
            EmpresaResponseDto empresaResponseDto = new EmpresaResponseDto();
            modelMapper.map(empresa, empresaResponseDto);
        }

        return ResponseEntity.ok(empresaResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDto> buscarEmpresaPorId(@PathVariable Long id) {
        Empresa empresa = empresaService.buscarEmpresaPorid(id);

        EmpresaResponseDto empresaDTO = new EmpresaResponseDto();

        modelMapper.map(empresa, empresaDTO);

        return ResponseEntity.ok(empresaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDto> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        Empresa empresaActual = empresaService.buscarEmpresaPorid(id);

        EmpresaResponseDto empresaDTO = new EmpresaResponseDto();

        empresaService.crearEmpresa(empresaActual);

        modelMapper.map(empresa, empresaDTO);

        return ResponseEntity.ok(empresaDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> cambiarEstadoEmpresa(@PathVariable Long id) {
        Empresa empresa = empresaService.cambiarEstadoEmpresa(id);
        return ResponseEntity.ok("Estado actualizado a: " + (empresa.getStatus() ? "Activa" : "Inactiva"));
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<List<Solicitud>> obtenerConsumption(@PathVariable Long id) {
        Empresa empresaActual = empresaService.buscarEmpresaPorid(id);
        return ResponseEntity.ok(empresaActual.getLista_solicitudes());
    }
}
