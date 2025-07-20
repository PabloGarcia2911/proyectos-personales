package com.GreenSolar.cl.GreenSolar.service;

import com.GreenSolar.cl.GreenSolar.model.Proveedor;
import com.GreenSolar.cl.GreenSolar.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getAll() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> getById(Long id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Optional<Proveedor> update(Long id, Proveedor datos) {
        return proveedorRepository.findById(id).map(p -> {
            p.setNombre(datos.getNombre());
            p.setRut(datos.getRut());
            return proveedorRepository.save(p);
        });
    }

    public boolean delete(Long id) {
        return proveedorRepository.findById(id).map(p -> {
            proveedorRepository.delete(p);
            return true;
        }).orElse(false);
    }
}