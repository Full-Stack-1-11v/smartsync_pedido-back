package cl.ecomarket.pedido.assemblers;

import cl.ecomarket.pedido.controller.ProductoDtoControllerV2;
import cl.ecomarket.pedido.dto.ProductoDto;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unused")
public class ProductoDTOModelAssemblerTest {

    @Test
    void toModel_deberiaRetornarEntityModelConLinks() {
        // Arrange
        ProductoDTOModelAssembler assembler = new ProductoDTOModelAssembler();
        ProductoDto producto = new ProductoDto(1L, "Producto Test", 1000);

        // Act
        EntityModel<ProductoDto> entityModel = assembler.toModel(producto);

        // Assert
        assertThat(entityModel.getContent()).isEqualTo(producto);

        List<Link> links = entityModel.getLinks().toList();
        assertThat(links).isNotEmpty();
        assertThat(links).anyMatch(link -> link.getRel().value().equals("productos"));
    }
}
