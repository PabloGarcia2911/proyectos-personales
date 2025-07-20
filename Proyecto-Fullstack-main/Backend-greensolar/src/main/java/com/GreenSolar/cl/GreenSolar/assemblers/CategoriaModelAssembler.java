package com.GreenSolar.cl.GreenSolar.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.GreenSolar.cl.GreenSolar.controller.CategoriaControllerV2;
import com.GreenSolar.cl.GreenSolar.model.Categoria;

@Component
public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {

    @Override
    @NonNull
    public EntityModel<Categoria> toModel(@NonNull Categoria categoria) {
        return EntityModel.of(categoria,
                linkTo(methodOn(CategoriaControllerV2.class).getById(categoria.getId())).withSelfRel(),
                linkTo(methodOn(CategoriaControllerV2.class).getAll()).withRel("categorias"),
                linkTo(methodOn(CategoriaControllerV2.class).createCategoria(null)).withRel("crear_categoria"),
                linkTo(methodOn(CategoriaControllerV2.class).updateCategoria(categoria.getId(), null)).withRel("actualizar_categoria"),
                linkTo(methodOn(CategoriaControllerV2.class).deleteCategoria(categoria.getId())).withRel("eliminar_categoria")
        );
    }
}
