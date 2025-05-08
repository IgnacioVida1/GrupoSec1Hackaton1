package com.example.hackaton1.Empresa.Infraestructure;

import com.example.hackaton1.Empresa.Domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findById(Long id);
}
