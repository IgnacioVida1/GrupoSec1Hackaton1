package com.example.hackaton1.Empresa.Domain;

import com.example.hackaton1.Empresa.Infraestructure.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;

@Transactional
@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;


    public void crearEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa buscarEmpresaPorid(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    public Empresa cambiarEstadoEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);

        if (empresa != null) {
            empresa.setStatus(!empresa.getStatus());
        }
        else {
            throw new RuntimeException("No existe una empresa");
        }

        return empresaRepository.save(empresa);
    }
}
