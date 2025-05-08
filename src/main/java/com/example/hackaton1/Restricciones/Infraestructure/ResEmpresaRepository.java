package com.example.hackaton1.Restricciones.Infraestructure;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Restricciones.Domain.RestriccionEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResEmpresaRepository extends JpaRepository<RestriccionEmpresa, Long> {

    List<RestriccionEmpresa> findByCompany(Empresa empresa);

    Optional<RestriccionEmpresa> findByIdAndCompany(Long id, Empresa empresa);
}
