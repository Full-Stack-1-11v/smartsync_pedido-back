package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cl.ecomarket.pedido.controller.BoletaController;
import cl.ecomarket.pedido.dto.BoletaDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BoletaModelAssembler {

    /**
     * Convierte un BoletaDto en un EntityModel con enlaces HATEOAS a operaciones relacionadas.
     */
    public EntityModel<BoletaDto> toModel(BoletaDto boletaDto) {
        org.springframework.hateoas.Link listarLink = linkTo(methodOn(BoletaController.class).listarBoletas())
            .withRel("listar")
            .withTitle("Listado de boletas");

        org.springframework.hateoas.Link guardarLink = linkTo(methodOn(BoletaController.class).crearBoleta(boletaDto))
            .withRel("guardar")
            .withTitle("Guardar boleta");

        return EntityModel.of(boletaDto, listarLink, guardarLink);
    }
}
