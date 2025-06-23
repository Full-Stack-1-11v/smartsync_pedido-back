package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import io.micrometer.common.lang.NonNull;

import cl.ecomarket.pedido.controller.ProductoDtoController;
import cl.ecomarket.pedido.dto.ProductoDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler {

    /**
     * Convierte un ProductoDto en un EntityModel con enlaces HATEOAS a operaciones relacionadas.
     */
    @NonNull
    public EntityModel<ProductoDto> toModel(@NonNull ProductoDto productoDto) {
        return EntityModel.of(productoDto,
                linkTo(methodOn(ProductoDtoController.class).listarProductos()).withRel("listar")
                // Puedes agregar más enlaces aquí si tienes más endpoints (por ejemplo, buscar por ID, guardar, etc.)
        );
    }
}
