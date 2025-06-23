package cl.ecomarket.pedido.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import io.micrometer.common.lang.NonNull;

import cl.ecomarket.pedido.controller.UserDtoController;
import cl.ecomarket.pedido.dto.UserDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler {

    /**
     * Convierte un UserDto en un EntityModel con enlaces HATEOAS a operaciones relacionadas.
     */
    @NonNull
    public EntityModel<UserDto> toModel(@NonNull UserDto userDto) {
        return EntityModel.of(userDto,
                linkTo(methodOn(UserDtoController.class).listarUsuarios()).withRel("listar"),
                linkTo(methodOn(UserDtoController.class).listarUsuariosDto()).withRel("listarDto")
                // Puedes agregar más enlaces aquí si tienes más endpoints (por ejemplo, buscar por ID, crear, actualizar, etc.)
        );
    }
}
