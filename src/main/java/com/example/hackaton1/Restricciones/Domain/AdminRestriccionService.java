package com.example.hackaton1.Restricciones.Domain;

import com.example.hackaton1.Empresa.Domain.Empresa;
import com.example.hackaton1.Restricciones.Infraestructure.ResEmpresaRepository;
import com.example.hackaton1.User.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRestriccionService {

    @Autowired
    ResEmpresaRepository resEmpresaRepository;

    public RestriccionEmpresa createRestriction(RestriccionEmpresa restriccion, User user)
    {
        Empresa empresa = user.getEmpresa();
        restriccion.setEmpresa(empresa);
        return resEmpresaRepository.save(restriccion);
    }

    public List<RestriccionEmpresa> getAllRestrictions(User user)
    {
        Empresa empresa = user.getEmpresa();
        return resEmpresaRepository.findByCompany(empresa);
    }

    public RestriccionEmpresa updateRestriction(Long id, RestriccionEmpresa updatedRestriction, User user) {
        Empresa empresa = user.getEmpresa();
        RestriccionEmpresa existingRestriction = resEmpresaRepository.findByIdAndCompany(id, empresa).orElseThrow();

        existingRestriction.setModelo(updatedRestriction.getModelo());
        existingRestriction.setMaxSolicitudesPorDia(updatedRestriction.getMaxSolicitudesPorDia());
        existingRestriction.setMaxTokensPorDia(updatedRestriction.getMaxTokensPorDia());

        return resEmpresaRepository.save(existingRestriction);
    }

    public void deleteRestriction(Long id, User user) {
        Empresa empresa = user.getEmpresa();
        RestriccionEmpresa restriction = resEmpresaRepository.findByIdAndCompany(id, empresa).orElseThrow();

        resEmpresaRepository.delete(restriction);
    }
}
