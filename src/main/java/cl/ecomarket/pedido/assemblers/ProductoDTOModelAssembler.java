package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cl.ecomarket.pedido.controller.ProductoDtoController;
import cl.ecomarket.pedido.dto.ProductoDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoDTOModelAssembler {

    /**
     * Convierte un ProductoDto en un EntityModel con enlaces HATEOAS a operaciones relacionadas.
     */
    
    public EntityModel<ProductoDto> toModel(ProductoDto productoDto) {
        org.springframework.hateoas.Link listarLink = linkTo(methodOn(ProductoDtoController.class).listarProductos())
            .withRel("productos")
            .withTitle("Listado de productos");

        return EntityModel.of(productoDto, listarLink);
    }
}