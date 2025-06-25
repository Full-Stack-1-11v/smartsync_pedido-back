package cl.ecomarket.pedido.assemblers;

import cl.ecomarket.pedido.controller.UserDtoControllerV2;
import cl.ecomarket.pedido.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unused")
public class UserDTOModelAssemblerTest {

    @Test
    void toModel_deberiaRetornarEntityModelConLinks() {
        // Arrange
        UserDTOModelAssembler assembler = new UserDTOModelAssembler();
        UserDto user = new UserDto(1, "Usuario Test", "usuario@test.com");

        // Act
        EntityModel<UserDto> entityModel = assembler.toModel(user);

        // Assert
        assertThat(entityModel.getContent()).isEqualTo(user);

        List<Link> links = entityModel.getLinks().toList();
        assertThat(links).isNotEmpty();
        // Ajusta los rel según lo que agregue tu ensamblador
        assertThat(links).anyMatch(link -> link.getRel().value().equals("listar"));
        assertThat(links).anyMatch(link -> link.getRel().value().equals("listarDto"));
    }
}
