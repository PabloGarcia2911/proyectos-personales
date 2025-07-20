package com.GreenSolar.cl.GreenSolar.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GreenSolar.cl.GreenSolar.controller.ProveedorControllerV2;
import com.GreenSolar.cl.GreenSolar.model.Proveedor;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>> {

    @Override
    @NonNull
    public EntityModel<Proveedor> toModel(@NonNull Proveedor proveedor) {
        return EntityModel.of(proveedor,
                linkTo(methodOn(ProveedorControllerV2.class).getById(proveedor.getId())).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class).getAll()).withRel("proveedores"),
                linkTo(methodOn(ProveedorControllerV2.class).createProveedor(null)).withRel("crear_proveedor"),
                linkTo(methodOn(ProveedorControllerV2.class).updateProveedor(proveedor.getId(), null)).withRel("actualizar_proveedor"),
                linkTo(methodOn(ProveedorControllerV2.class).deleteProveedor(proveedor.getId())).withRel("eliminar_proveedor")
        );
    }
}
