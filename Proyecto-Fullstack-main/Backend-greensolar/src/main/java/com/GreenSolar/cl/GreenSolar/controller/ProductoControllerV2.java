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

import com.GreenSolar.cl.GreenSolar.assemblers.ProductoModelAssembler;
import com.GreenSolar.cl.GreenSolar.model.Producto;
import com.GreenSolar.cl.GreenSolar.service.ProductoService;

@RestController
@RequestMapping("/productos/v2")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler productoModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Producto>> getAllProductos() {
        List<EntityModel<Producto>> productos = productoService.getAllProductos().stream()
                .map(productoModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(productos);
    }

    @GetMapping("/{id}")
    public EntityModel<Producto> getProducto(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id).orElseThrow();
        return productoModelAssembler.toModel(producto);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Producto>> createProducto(@RequestBody Producto producto) {
        Producto created = productoService.saveProducto(producto);
        return ResponseEntity
                .created(URI.create("/productos/v2/" + created.getId()))
                .body(productoModelAssembler.toModel(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.updateProducto(id, producto)
                .map(updated -> ResponseEntity.ok(productoModelAssembler.toModel(updated)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        return productoService.deleteProducto(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}