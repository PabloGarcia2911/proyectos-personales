package com.GreenSolar.cl.GreenSolar.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GreenSolar.cl.GreenSolar.controller.ProductoControllerV2;
import com.GreenSolar.cl.GreenSolar.model.Producto;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    @NonNull
    public EntityModel<Producto> toModel(@NonNull Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControllerV2.class).getProducto(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).getAllProductos()).withRel("productos"),
                linkTo(methodOn(ProductoControllerV2.class).createProducto(null)).withRel("crear_producto"),
                linkTo(methodOn(ProductoControllerV2.class).updateProducto(producto.getId(), null)).withRel("actualizar_producto"),
                linkTo(methodOn(ProductoControllerV2.class).deleteProducto(producto.getId())).withRel("eliminar_producto")
        );
    }
}
