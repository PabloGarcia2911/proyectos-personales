package com.GreenSolar.cl.GreenSolar.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GreenSolar.cl.GreenSolar.assemblers.CategoriaModelAssembler;
import com.GreenSolar.cl.GreenSolar.model.Categoria;
import com.GreenSolar.cl.GreenSolar.service.CategoriaService;

@RestController
@RequestMapping("/categorias/v2")
public class CategoriaControllerV2 {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaModelAssembler categoriaModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Categoria>> getAll() {
        List<EntityModel<Categoria>> categorias = categoriaService.getAll().stream()
                .map(categoriaModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(categorias);
    }

    @GetMapping("/{id}")
    public EntityModel<Categoria> getById(@PathVariable Long id) {
        Categoria categoria = categoriaService.getById(id).orElseThrow();
        return categoriaModelAssembler.toModel(categoria);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Categoria>> createCategoria(@RequestBody Categoria categoria) {
        Categoria created = categoriaService.save(categoria);
        return ResponseEntity
                .created(URI.create("/categorias/v2/" + created.getId()))
                .body(categoriaModelAssembler.toModel(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> updateCategoria(@PathVariable Long id, @RequestBody Categoria datos) {
        return categoriaService.update(id, datos)
                .map(updated -> ResponseEntity.ok(categoriaModelAssembler.toModel(updated)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        return categoriaService.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}