package com.example.hackaton1.RestriccionesTest;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Empresa.Infraestructure.EmpresaRepository;
import com.example.hackaton1.Restricciones.Domain.ModelType;
import com.example.hackaton1.Restricciones.Domain.RestriccionEmpresa;
import com.example.hackaton1.Restricciones.Infraestructure.ResEmpresaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ResEmpresaRepositoryTest {

    @Autowired
    private ResEmpresaRepository resEmpresaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    void testGuardarYBuscarRestriccionesPorEmpresa() {
        // Crear empresa
        Empresa empresa = new Empresa();
        empresa.setNombre("OpenAI SAC");
        empresa.setStatus(true);
        empresa = empresaRepository.save(empresa);

        // Crear restricción
        RestriccionEmpresa restriccion = new RestriccionEmpresa();
        restriccion.setModelo(ModelType.GPT_4);
        restriccion.setMaxTokensPorDia(10000);
        restriccion.setMaxSolicitudesPorDia(200);
        restriccion.setEmpresa(empresa);

        RestriccionEmpresa guardada = resEmpresaRepository.save(restriccion);

        // Buscar por empresa
        List<RestriccionEmpresa> lista = resEmpresaRepository.findByCompany(empresa);
        assertEquals(1, lista.size());
        assertEquals(ModelType.GPT_4, lista.get(0).getModelo());

        // Buscar por id y empresa
        Optional<RestriccionEmpresa> encontrada = resEmpresaRepository.findByIdAndCompany(guardada.getId(), empresa);
        assertTrue(encontrada.isPresent());
        assertEquals(200, encontrada.get().getMaxSolicitudesPorDia());
    }
}


