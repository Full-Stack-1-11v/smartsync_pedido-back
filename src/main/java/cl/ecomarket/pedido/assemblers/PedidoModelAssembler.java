package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cl.ecomarket.pedido.controller.PedidoController;
import cl.ecomarket.pedido.model.Pedido;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Ensamblador para el modelo de {@link Pedido}, encargado de convertir
 * objetos Pedido en {@link EntityModel} con enlaces HATEOAS relacionados.
 * <p>
 * Permite agregar enlaces útiles para la navegación y manipulación de recursos
 * de pedidos en la API REST.
 * </p>
 */
@Component
public class PedidoModelAssembler {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public PedidoModelAssembler() {
        // Constructor por defecto
    }

    /**
     * Convierte un {@link Pedido} en un {@link EntityModel} que incluye enlaces HATEOAS
     * a operaciones relacionadas, como listar, buscar, guardar, actualizar y eliminar pedidos.
     *
     * @param pedido El objeto Pedido a convertir.
     * @return Un EntityModel que envuelve el Pedido y contiene enlaces HATEOAS.
     */
    public EntityModel<Pedido> toModel(Pedido pedido) {
        org.springframework.hateoas.Link listarLink = linkTo(methodOn(PedidoController.class).listarTodos())
            .withRel("listar")
            .withTitle("Listado de pedidos");

        org.springframework.hateoas.Link buscarLink = linkTo(methodOn(PedidoController.class).buscar(pedido.getPedidoId()))
            .withRel("buscar")
            .withTitle("Buscar pedido por ID");

        org.springframework.hateoas.Link guardarLink = linkTo(methodOn(PedidoController.class).guardar(pedido))
            .withRel("guardar")
            .withTitle("Guardar pedido");

        org.springframework.hateoas.Link actualizarLink = linkTo(methodOn(PedidoController.class).actualizar(pedido.getPedidoId(), pedido))
            .withRel("actualizar")
            .withTitle("Actualizar pedido");

        org.springframework.hateoas.Link eliminarLink = linkTo(methodOn(PedidoController.class).eliminar(pedido.getPedidoId()))
            .withRel("eliminar")
            .withTitle("Eliminar pedido");

        return EntityModel.of(pedido, listarLink, buscarLink, guardarLink, actualizarLink, eliminarLink);
    }
}