package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cl.ecomarket.pedido.controller.UserDtoController;
import cl.ecomarket.pedido.dto.UserDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserDTOModelAssembler {

    /**
     * Convierte un UserDto en un EntityModel con enlaces HATEOAS a operaciones relacionadas.
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
