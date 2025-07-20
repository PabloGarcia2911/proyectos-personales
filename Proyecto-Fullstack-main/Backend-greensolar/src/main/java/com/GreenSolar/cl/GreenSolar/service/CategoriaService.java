package com.GreenSolar.cl.GreenSolar.service;

import com.GreenSolar.cl.GreenSolar.model.Categoria;
import com.GreenSolar.cl.GreenSolar.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> update(Long id, Categoria datos) {
        return categoriaRepository.findById(id).map(c -> {
            c.setNombre(datos.getNombre());
            return categoriaRepository.save(c);
        });
    }

    public boolean delete(Long id) {
        return categoriaRepository.findById(id).map(c -> {
            categoriaRepository.delete(c);
            return true;
        }).orElse(false);
    }
}