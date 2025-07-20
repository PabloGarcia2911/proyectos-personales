package com.GreenSolar.cl.GreenSolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GreenSolar.cl.GreenSolar.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}