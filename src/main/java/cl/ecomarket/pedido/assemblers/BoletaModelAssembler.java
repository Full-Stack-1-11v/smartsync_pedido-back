package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cl.ecomarket.pedido.controller.BoletaController;
import cl.ecomarket.pedido.dto.BoletaDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Ensamblador para el modelo de {@link BoletaDto}, encargado de convertir
 * objetos BoletaDto en {@link EntityModel} con enlaces HATEOAS relacionados.
 * <p>
 * Permite agregar enlaces útiles para la navegación y manipulación de recursos
 * de boletas en la API REST.
 * </p>
 */
@Component
public class BoletaModelAssembler {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public BoletaModelAssembler() {
        // Constructor por defecto
    }

    /**
     * Convierte un {@link BoletaDto} en un {@link EntityModel} que incluye enlaces HATEOAS
     * a operaciones relacionadas, como listar y guardar boletas.
     *
     * @param boletaDto El DTO de boleta a convertir.
     * @return Un EntityModel que envuelve el BoletaDto y contiene enlaces HATEOAS.
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
