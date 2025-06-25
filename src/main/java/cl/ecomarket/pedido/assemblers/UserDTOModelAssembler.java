package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cl.ecomarket.pedido.controller.UserDtoController;
import cl.ecomarket.pedido.dto.UserDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Ensamblador para el modelo de {@link UserDto}, encargado de convertir
 * objetos UserDto en {@link EntityModel} con enlaces HATEOAS relacionados.
 * <p>
 * Permite agregar enlaces útiles para la navegación y manipulación de recursos
 * de usuarios en la API REST.
 * </p>
 */
@Component
public class UserDTOModelAssembler {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public UserDTOModelAssembler() {
        // Constructor por defecto
    }

    /**
     * Convierte un {@link UserDto} en un {@link EntityModel} que incluye enlaces HATEOAS
     * a operaciones relacionadas, como listar usuarios y listar usuarios DTO.
     *
     * @param userDto El DTO de usuario a convertir.
     * @return Un EntityModel que envuelve el UserDto y contiene enlaces HATEOAS.
     */
    public EntityModel<UserDto> toModel(UserDto userDto) {
        org.springframework.hateoas.Link listarLink = linkTo(methodOn(UserDtoController.class).listarUsuarios())
            .withRel("listar")
            .withTitle("Listado de usuarios");

        org.springframework.hateoas.Link listarDtoLink = linkTo(methodOn(UserDtoController.class).listarUsuariosDto())
            .withRel("listarDto")
            .withTitle("Listado de usuarios DTO");

        return EntityModel.of(userDto, listarLink,
                listarDtoLink
                // Puedes agregar más enlaces aquí si tienes más endpoints (por ejemplo, buscar por ID, crear, actualizar, etc.)
        );
    }
}
