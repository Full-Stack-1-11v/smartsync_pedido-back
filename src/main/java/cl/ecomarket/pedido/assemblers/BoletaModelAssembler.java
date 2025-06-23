package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import io.micrometer.common.lang.NonNull;

import cl.ecomarket.pedido.controller.BoletaController;
import cl.ecomarket.pedido.dto.BoletaDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BoletaModelAssembler {

    /**
     * Convierte un BoletaDto en un EntityModel con enlaces HATEOAS a operaciones relacionadas.
     */
    @NonNull
    public EntityModel<BoletaDto> toModel(@NonNull BoletaDto boletaDto) {
        return EntityModel.of(boletaDto,
                linkTo(methodOn(BoletaController.class).listarBoletas()).withRel("listar"),
                linkTo(methodOn(BoletaController.class).crearBoleta(boletaDto)).withRel("guardar")
        );
    }
}
