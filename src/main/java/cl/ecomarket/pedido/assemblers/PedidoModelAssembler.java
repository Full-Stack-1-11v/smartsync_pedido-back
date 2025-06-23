package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import io.micrometer.common.lang.NonNull;

import cl.ecomarket.pedido.controller.PedidoController;
import cl.ecomarket.pedido.model.Pedido;
import cl.ecomarket.pedido.dto.PedidoDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler {

    /**
     * Convierte un Pedido en un EntityModel con enlaces HATEOAS a operaciones relacionadas.
     */
    @NonNull
    public EntityModel<Pedido> toModel(@NonNull Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoController.class).buscar(pedido.getPedidoId())).withRel("buscarPorId"),
                linkTo(methodOn(PedidoController.class).guardar(pedido)).withRel("guardar"),
                linkTo(methodOn(PedidoController.class).listarTodos()).withRel("listar"),
                linkTo(methodOn(PedidoController.class).actualizar(pedido.getPedidoId(), pedido)).withRel("actualizar"),
                linkTo(methodOn(PedidoController.class).eliminar(pedido.getPedidoId())).withRel("eliminar")
        );
    }

    /**
     * Convierte un PedidoDto en un EntityModel con enlaces HATEOAS a operaciones relacionadas.
     */
    @NonNull
    public EntityModel<PedidoDto> toModelDto(@NonNull PedidoDto pedidoDto) {
        return EntityModel.of(pedidoDto,
                linkTo(methodOn(PedidoController.class).buscar(pedidoDto.getPedidoId())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).listarTodos()).withRel("listar")
        );
    }
}