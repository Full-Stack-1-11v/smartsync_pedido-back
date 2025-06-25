package cl.ecomarket.pedido.assemblers;

import cl.ecomarket.pedido.controller.BoletaControllerV2;
import cl.ecomarket.pedido.dto.BoletaDto;
import cl.ecomarket.pedido.dto.PedidoDto;
import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unused")
public class BoletaModelAssemblerTest {

    @Test
    void toModel_deberiaRetornarEntityModelConLinks() {
        // Arrange
        BoletaModelAssembler assembler = new BoletaModelAssembler();
        PedidoDto pedidoDto = new PedidoDto(1L, true, Date.valueOf("2024-06-25"));
        ProductoDto productoDto = new ProductoDto(1L, "Producto Test", 1000);
        UserDto userDto = new UserDto(1, "Usuario Test", "usuario@test.com");
        BoletaDto boletaDto = new BoletaDto();
        boletaDto.setPedido(pedidoDto);
        boletaDto.setProducto(productoDto);
        boletaDto.setUser(userDto);

        // Act
        EntityModel<BoletaDto> entityModel = assembler.toModel(boletaDto);

        // Assert
        assertThat(entityModel.getContent()).isEqualTo(boletaDto);

        List<Link> links = entityModel.getLinks().toList();
        assertThat(links).isNotEmpty();
        assertThat(links).anyMatch(link -> link.getRel().value().equals("listar"));
        assertThat(links).anyMatch(link -> link.getRel().value().equals("guardar"));
    }
}
