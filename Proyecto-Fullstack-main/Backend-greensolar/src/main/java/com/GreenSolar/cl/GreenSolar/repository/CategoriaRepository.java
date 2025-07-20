package com.GreenSolar.cl.GreenSolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GreenSolar.cl.GreenSolar.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}