package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cl.ecomarket.pedido.controller.ProductoDtoController;
import cl.ecomarket.pedido.dto.ProductoDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Ensamblador para el modelo de {@link ProductoDto}, encargado de convertir
 * objetos ProductoDto en {@link EntityModel} con enlaces HATEOAS relacionados.
 * <p>
 * Permite agregar enlaces útiles para la navegación y manipulación de recursos
 * de productos en la API REST.
 * </p>
 */
@Component
public class ProductoDTOModelAssembler {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public ProductoDTOModelAssembler() {
        // Constructor por defecto
    }

    /**
     * Convierte un {@link ProductoDto} en un {@link EntityModel} que incluye enlaces HATEOAS
     * a operaciones relacionadas, como listar productos.
     *
     * @param productoDto El DTO de producto a convertir.
     * @return Un EntityModel que envuelve el ProductoDto y contiene enlaces HATEOAS.
     */
    public EntityModel<ProductoDto> toModel(ProductoDto productoDto) {
        org.springframework.hateoas.Link listarLink = linkTo(methodOn(ProductoDtoController.class).listarProductos())
            .withRel("productos")
            .withTitle("Listado de productos");

        return EntityModel.of(productoDto, listarLink);
    }
}