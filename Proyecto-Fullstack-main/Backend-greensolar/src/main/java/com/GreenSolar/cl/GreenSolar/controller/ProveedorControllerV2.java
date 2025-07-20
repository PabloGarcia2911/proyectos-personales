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

import com.GreenSolar.cl.GreenSolar.assemblers.ProveedorModelAssembler;
import com.GreenSolar.cl.GreenSolar.model.Proveedor;
import com.GreenSolar.cl.GreenSolar.service.ProveedorService;

@RestController
@RequestMapping("/proveedores/v2")
public class ProveedorControllerV2 {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorModelAssembler proveedorModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Proveedor>> getAll() {
        List<EntityModel<Proveedor>> proveedores = proveedorService.getAll().stream()
                .map(proveedorModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(proveedores);
    }

    @GetMapping("/{id}")
    public EntityModel<Proveedor> getById(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.getById(id).orElseThrow();
        return proveedorModelAssembler.toModel(proveedor);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Proveedor>> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor created = proveedorService.save(proveedor);
        return ResponseEntity
                .created(URI.create("/proveedores/v2/" + created.getId()))
                .body(proveedorModelAssembler.toModel(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Proveedor>> updateProveedor(@PathVariable Long id, @RequestBody Proveedor datos) {
        return proveedorService.update(id, datos)
                .map(updated -> ResponseEntity.ok(proveedorModelAssembler.toModel(updated)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        return proveedorService.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
